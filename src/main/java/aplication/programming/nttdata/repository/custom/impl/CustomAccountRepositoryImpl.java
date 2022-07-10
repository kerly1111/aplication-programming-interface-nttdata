package aplication.programming.nttdata.repository.custom.impl;

import aplication.programming.nttdata.model.Account;
import aplication.programming.nttdata.model.Client;
import aplication.programming.nttdata.model.QAccount;
import aplication.programming.nttdata.model.QClient;
import aplication.programming.nttdata.repository.custom.CustomAccountRepository;
import aplication.programming.nttdata.vo.request.AccountRequestVO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomAccountRepositoryImpl implements CustomAccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private QAccount qAccount;

    private JPAQuery<Account> query;

    private JPAQueryFactory queryUpdate;

    @Override
    public void update(Long idAccount, AccountRequestVO request) {
        this.initQuery(Boolean.TRUE);

        this.queryUpdate.update(this.qAccount)
                .set(this.qAccount.accountNumber, request.getAccountNumber())
                .set(this.qAccount.accountType, request.getAccountType())
                .set(this.qAccount.initialBalance, request.getInitialBalance())
                .set(this.qAccount.status, request.getStatus())
                .where(this.qAccount.id.eq(idAccount))
                .execute();
    }

    @Override
    public List<Account> allAccount() {
        this.initQuery(Boolean.FALSE);

        QClient qClient = QClient.client;

        this.query.select(Projections.fields(Account.class,
                this.qAccount.accountNumber,
                this.qAccount.accountType,
                this.qAccount.initialBalance,
                this.qAccount.status,
                Projections.fields(Client.class,
                    this.qAccount.client.identification,
                    this.qAccount.client.name
                ).as(this.qAccount.client.getMetadata().getName())
        ));

        this.query.innerJoin(this.qAccount.client, qClient);

        return this.query.fetch();
    }

    @Override
    public Optional<Account> findAccountById(Long idAccount) {
        this.initQuery(Boolean.FALSE);

        QClient qClient = QClient.client;

        this.query.select(Projections.fields(Account.class,
                this.qAccount.accountNumber,
                this.qAccount.accountType,
                this.qAccount.initialBalance,
                this.qAccount.status,
                Projections.fields(Client.class,
                        this.qAccount.client.identification,
                        this.qAccount.client.name
                ).as(this.qAccount.client.getMetadata().getName())
        ));

        this.query.innerJoin(this.qAccount.client, qClient);

        this.query.where(this.qAccount.id.eq(idAccount));

        return Optional.ofNullable(this.query.fetchFirst());
    }

    @Override
    public Optional<Account> findByNumberByType(String number, String type) {
        this.initQuery(Boolean.FALSE);

        this.query.select(Projections.fields(Account.class,
                this.qAccount.id,
                this.qAccount.accountNumber,
                this.qAccount.accountType,
                this.qAccount.initialBalance,
                this.qAccount.status
        ));

        this.query.where(this.qAccount.accountNumber.eq(number))
                .where(this.qAccount.accountType.eq(type));

        return Optional.ofNullable(this.query.fetchFirst());
    }

    @Override
    public List<Account> findAccountByClientId(Long idClient) {
        this.initQuery(Boolean.FALSE);

        QClient qClient = QClient.client;

        this.query.select(Projections.fields(Account.class,
                this.qAccount.id,
                this.qAccount.accountNumber,
                this.qAccount.accountType,
                this.qAccount.initialBalance,
                this.qAccount.status,
                Projections.fields(Client.class,
                        this.qAccount.client.identification,
                        this.qAccount.client.name
                ).as(this.qAccount.client.getMetadata().getName())
        ));

        this.query.innerJoin(this.qAccount.client, qClient);

        this.query.where(this.qAccount.idClient.eq(idClient));

        return this.query.fetch();
    }

    private void initQuery(Boolean isUpdate) {
        this.qAccount = QAccount.account;

        if (isUpdate) {
            this.queryUpdate = new JPAQueryFactory(this.entityManager);
        } else {
            this.query = new JPAQuery<>(this.entityManager);
            this.query.from(this.qAccount);
        }
    }
}
