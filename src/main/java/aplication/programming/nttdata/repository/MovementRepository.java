package aplication.programming.nttdata.repository;

import aplication.programming.nttdata.model.Movement;
import aplication.programming.nttdata.repository.custom.CustomMovementRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long>, CustomMovementRepository {
}
