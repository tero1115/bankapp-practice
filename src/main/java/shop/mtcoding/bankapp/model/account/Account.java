package shop.mtcoding.bankapp.model.account;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bankapp.handler.ex.CustomException;

@Getter
@Setter
public class Account {
    private Integer id;
    private String number;
    private String password;
    private Long balance;
    private Integer userId;
    private Timestamp createdAt;

    public void withdraw(Long amount) {
        this.balance = this.balance - amount;
    }

    public void checkPassword(String password) {
        if (!this.password.equals(password)) {
            throw new CustomException("출금 계좌 비밀번호가 맞지 않습니다", HttpStatus.BAD_REQUEST);
        }
    }

    public void checkBalance(Long amount) {
        if (this.balance < amount) {
            throw new CustomException("잔액이 부족합니다", HttpStatus.BAD_REQUEST);
        }
    }
}