package aplication.programming.nttdata.repository;

import aplication.programming.nttdata.model.Account;
import aplication.programming.nttdata.repository.custom.CustomAccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>, CustomAccountRepository {
}
