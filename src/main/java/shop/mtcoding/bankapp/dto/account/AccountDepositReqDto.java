package shop.mtcoding.bankapp.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDepositReqDto {
    private Long amount;
    private String dAccountNumber;
}
