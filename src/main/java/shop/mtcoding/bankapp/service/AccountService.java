package shop.mtcoding.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.bankapp.dto.account.AccountDepositReqDto;
import shop.mtcoding.bankapp.dto.account.AccountSaveReqDto;
import shop.mtcoding.bankapp.dto.account.AccountWithdrawReqDto;
import shop.mtcoding.bankapp.handler.ex.CustomException;
import shop.mtcoding.bankapp.model.account.Account;
import shop.mtcoding.bankapp.model.account.AccountRepository;
import shop.mtcoding.bankapp.model.history.History;
import shop.mtcoding.bankapp.model.history.HistoryRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Transactional
    public void 계좌입금(AccountDepositReqDto accountDepositReqDto) {
        // 1. 계좌존재 여부
        Account accountPS = accountRepository.findByNumber(accountDepositReqDto.getDAccountNumber());
        if (accountPS == null) {
            throw new CustomException("계좌가 존재하지 않습니다", HttpStatus.BAD_REQUEST);
        }

        // 2. 입금(balance 변경(플러스))
        accountPS.deposit(accountDepositReqDto.getAmount());
        accountRepository.updateById(accountPS);

        // 3. 히스토리 (거래내역)
        History history = new History();
        history.setAmount(accountDepositReqDto.getAmount()); // 입금금액
        history.setWAccountId(null);
        history.setDAccountId(accountPS.getId()); // 입금계좌의 Id
        history.setWBalance(null);
        history.setDBalance(accountPS.getBalance()); // 입금계좌의 잔액

        historyRepository.insert(history);
    }

    @Transactional
    public int 계좌출금(AccountWithdrawReqDto accountWithdrawReqDto) {
        // 1. 계좌존재 여부
        Account accountPS = accountRepository.findByNumber(accountWithdrawReqDto.getWAccountNumber());
        if (accountPS == null) {
            throw new CustomException("계좌가 존재하지 않습니다", HttpStatus.BAD_REQUEST);
        }

        // 2. 계좌비밀번호 체크
        accountPS.checkPassword(accountWithdrawReqDto.getWAccountPassword());

        // 3. 잔액확인
        accountPS.checkBalance(accountWithdrawReqDto.getAmount());

        // 4. 출금(balance 변경(마이너스))
        accountPS.withdraw(accountWithdrawReqDto.getAmount());
        accountRepository.updateById(accountPS);

        // 5. 히스토리 (거래내역)
        History history = new History();
        history.setAmount(accountWithdrawReqDto.getAmount()); // 출금금액
        history.setWAccountId(accountPS.getId()); // 출금계좌의 Id
        history.setDAccountId(null);
        history.setWBalance(accountPS.getBalance()); // 출금계좌의 잔액
        history.setDBalance(null);

        historyRepository.insert(history);

        // 6. 해당 계좌의 id를 return
        return accountPS.getId();
    }

    @Transactional
    public void 계좌생성(AccountSaveReqDto accountSaveReqDto, int principalId) {
        Account account = accountSaveReqDto.toModel(principalId);
        int result = accountRepository.insert(account);

        if (result != 1) {
            throw new CustomException("계좌생성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
