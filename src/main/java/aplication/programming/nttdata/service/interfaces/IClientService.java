package aplication.programming.nttdata.service.interfaces;

import aplication.programming.nttdata.model.Client;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IClientService {

    Client create(Client request);

    List<Client> allClient();

    void update(Long idClient, Client request);

    void delete(Long idClient);
}
