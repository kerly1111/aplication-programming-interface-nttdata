package aplication.programming.nttdata.repository;

import aplication.programming.nttdata.model.Client;
import aplication.programming.nttdata.repository.custom.CustomClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>, CustomClientRepository {
}
