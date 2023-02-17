package shop.mtcoding.bankapp.model.account;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.bankapp.dto.account.AccountDetailRespDto;

@Mapper
public interface AccountRepository {

    public AccountDetailRespDto findByIdWithUser(int id);

    public int insert(Account user);

    public int updateById(Account user);

    public int deleteById(int id);

    public List<Account> findAll();

    public Account findById(int id);

    public List<Account> findByUserId(Integer id);

    public Account findByNumber(String number);

}