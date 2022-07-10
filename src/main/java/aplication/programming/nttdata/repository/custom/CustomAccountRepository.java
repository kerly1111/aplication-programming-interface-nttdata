package aplication.programming.nttdata.repository.custom;

import aplication.programming.nttdata.model.Account;
import aplication.programming.nttdata.vo.request.AccountRequestVO;

import java.util.List;
import java.util.Optional;

public interface CustomAccountRepository {

    void update(Long idAccount, AccountRequestVO request);

    List<Account> allAccount();

    Optional<Account> findAccountById(Long idAccount);

    Optional<Account> findByNumberByType(String number, String type);

    List<Account> findAccountByClientId(Long idClient);
}