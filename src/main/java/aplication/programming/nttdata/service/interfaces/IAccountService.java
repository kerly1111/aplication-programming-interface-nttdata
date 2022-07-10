package aplication.programming.nttdata.service.interfaces;

import aplication.programming.nttdata.vo.request.AccountRequestVO;
import aplication.programming.nttdata.vo.response.AccountResponseVO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IAccountService {

    AccountResponseVO create(AccountRequestVO request);

    List<AccountResponseVO> allAccount();

    void update(Long idAccount, AccountRequestVO request);

    void delete(Long idAccount);
}
