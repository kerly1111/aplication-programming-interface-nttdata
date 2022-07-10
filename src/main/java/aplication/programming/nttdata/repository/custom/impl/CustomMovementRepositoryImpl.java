package aplication.programming.nttdata.repository.custom.impl;

import aplication.programming.nttdata.model.Account;
import aplication.programming.nttdata.model.Movement;
import aplication.programming.nttdata.model.QAccount;
import aplication.programming.nttdata.model.QMovement;
import aplication.programming.nttdata.repository.custom.CustomMovementRepository;
import aplication.programming.nttdata.vo.request.MovementRequestVO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomMovementRepositoryImpl implements CustomMovementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private QMovement qMovement;

    private JPAQuery<Movement> query;

    private JPAQueryFactory queryUpdate;


    @Override
    public void update(Long idMovement, Movement request) {
        this.initQuery(Boolean.TRUE);

        this.queryUpdate.update(this.qMovement)
                .set(this.qMovement.date, request.getDate())
                .set(this.qMovement.movementType, request.getMovementType())
                .set(this.qMovement.value, request.getValue())
                .set(this.qMovement.balance, request.getBalance())
                .where(this.qMovement.id.eq(idMovement))
                .execute();
    }

    @Override
    public Optional<Movement> findMovementById(Long idMovement) {
        this.initQuery(Boolean.FALSE);

        QAccount qAccount = QAccount.account;

        this.query.select(Projections.fields(Movement.class,
                this.qMovement.date,
                this.qMovement.movementType,
                this.qMovement.value,
                this.qMovement.balance,
                Projections.fields(Account.class,
                        this.qMovement.account.accountNumber,
                        this.qMovement.account.accountType,
                        this.qMovement.account.status
                ).as(qMovement.account.getMetadata().getName())
        ));

        this.query.innerJoin(this.qMovement.account, qAccount);

        this.query.where(this.qMovement.id.eq(idMovement));

        return Optional.ofNullable(this.query.fetchFirst());
    }

    @Override
    public Double getBalance(Long idAccount, String movementType) {
        this.initQuery(Boolean.FALSE);

        return this.query.select(this.qMovement.value.sum())
                .where(this.qMovement.movementType.eq(movementType))
                .where(this.qMovement.idAccount.eq(idAccount))
                .fetchFirst();
    }

    @Override
    public Double getBalanceByDate(Long idAccount, String movementType, Date dateStart, Date dateEnd) {
        this.initQuery(Boolean.FALSE);

        return this.query.select(this.qMovement.value.sum())
                .where(this.qMovement.movementType.eq(movementType))
                .where(this.qMovement.idAccount.eq(idAccount))
                .where(this.qMovement.date.between(dateStart, dateEnd))
                .fetchFirst();
    }

    @Override
    public Double getBalancePrevious(Long idAccount, String movementType, Date dateStart) {
        this.initQuery(Boolean.FALSE);

        return this.query.select(this.qMovement.value.sum())
                .where(this.qMovement.movementType.eq(movementType))
                .where(this.qMovement.idAccount.eq(idAccount))
                .where(this.qMovement.date.before(dateStart))
                .fetchFirst();
    }

    @Override
    public List<Movement> allMovement() {
        this.initQuery(Boolean.FALSE);

        QAccount qAccount = QAccount.account;

        this.query.select(Projections.fields(Movement.class,
                this.qMovement.date,
                this.qMovement.movementType,
                this.qMovement.value,
                this.qMovement.balance,
                Projections.fields(Account.class,
                        this.qMovement.account.accountNumber,
                        this.qMovement.account.accountType,
                        this.qMovement.account.status
                ).as(qMovement.account.getMetadata().getName())
        ));

        this.query.innerJoin(this.qMovement.account, qAccount);

        return this.query.fetch();
    }

    private void initQuery(Boolean isUpdate) {
        this.qMovement = QMovement.movement;

        if (isUpdate) {
            this.queryUpdate = new JPAQueryFactory(this.entityManager);
        } else {
            this.query = new JPAQuery<>(this.entityManager);
            this.query.from(this.qMovement);
        }
    }
}
