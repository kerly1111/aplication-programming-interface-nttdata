package aplication.programming.nttdata.repository.custom;

import aplication.programming.nttdata.model.Client;

import java.util.Optional;

public interface CustomClientRepository {

    void update(Long idClient, Client request);

    Optional<Client> findByIdentification(String identification);
}
