package aplication.programming.nttdata.service;

import aplication.programming.nttdata.common.exception.NttdataException;
import aplication.programming.nttdata.model.Account;
import aplication.programming.nttdata.repository.AccountRepository;
import aplication.programming.nttdata.repository.ClientRepository;
import aplication.programming.nttdata.service.interfaces.IAccountService;
import aplication.programming.nttdata.vo.request.AccountRequestVO;
import aplication.programming.nttdata.vo.response.AccountResponseVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Resource
    private AccountRepository accountRepository;

    @Resource
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public AccountResponseVO create(AccountRequestVO request) {
        try{
            Long idAccount = this.accountRepository.save(Account.builder()
                    .accountNumber(request.getAccountNumber())
                    .accountType(request.getAccountType())
                    .initialBalance(request.getInitialBalance())
                    .status(request.getStatus())
                    .idClient(this.clientRepository.findByIdentification(request.getIdentification())
                            .orElseThrow(() -> new NttdataException("El usuario asignado a la cuenta no existe."))
                            .getId())
                    .build()).getId();

            Account account = this.accountRepository.findAccountById(idAccount)
                    .orElseThrow(() -> new NttdataException("Error al crear la cuenta"));

            return AccountResponseVO.builder()
                    .accountNumber(account.getAccountNumber())
                    .accountType(account.getAccountType())
                    .initialBalance(account.getInitialBalance())
                    .status(account.getStatus())
                    .name(account.getClient().getName())
                    .build();
        } catch (Exception e) {
            throw new NttdataException(e.getMessage());
        }
    }

    @Override
    public List<AccountResponseVO> allAccount() {
        List<AccountResponseVO> accounts = new ArrayList<>();

        this.accountRepository.allAccount()
                .forEach(account -> accounts.add(AccountResponseVO.builder()
                        .accountNumber(account.getAccountNumber())
                        .accountType(account.getAccountType())
                        .initialBalance(account.getInitialBalance())
                        .status(account.getStatus())
                        .name(account.getClient().getName())
                        .build()
                ));

        return accounts;
    }

    @Override
    @Transactional
    public void update(Long idAccount, AccountRequestVO request) {
        try{
            this.accountRepository.update(idAccount, request);
        } catch (Exception e) {
            throw new NttdataException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long idAccount) {
        try{
            this.accountRepository.deleteById(idAccount);
        } catch (Exception e) {
            throw new NttdataException(e.getMessage());
        }
    }
}
