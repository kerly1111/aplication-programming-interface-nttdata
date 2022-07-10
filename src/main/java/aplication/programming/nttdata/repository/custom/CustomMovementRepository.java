package aplication.programming.nttdata.repository.custom;

import aplication.programming.nttdata.model.Movement;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CustomMovementRepository {

    void update(Long idMovement, Movement request);

    Optional<Movement> findMovementById(Long idMovement);

    Double getBalance(Long idAccount, String movementType);

    Double getBalanceByDate(Long idAccount, String movementType, Date dateStart, Date dateEnd);

    Double getBalancePrevious(Long idAccount, String movementType, Date dateStart);

    List<Movement> allMovement();
}
