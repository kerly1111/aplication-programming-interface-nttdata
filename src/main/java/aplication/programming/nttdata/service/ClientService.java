package aplication.programming.nttdata.service;

import aplication.programming.nttdata.common.exception.NttdataException;
import aplication.programming.nttdata.model.Client;
import aplication.programming.nttdata.repository.ClientRepository;
import aplication.programming.nttdata.service.interfaces.IClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientService implements IClientService {

    @Resource
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public Client create(Client request) {
        try{
            Long idClient = this.clientRepository.save(Client.builder()
                    .identification(request.getIdentification())
                    .name(request.getName())
                    .gender(request.getGender())
                    .age(request.getAge())
                    .address(request.getAddress())
                    .phone(request.getPhone())
                    .password(request.getPassword())
                    .status(request.getStatus())
                    .build()).getId();

            return this.clientRepository.findById(idClient)
                    .orElseThrow(() -> new NttdataException("Error al crear usuario"));
        } catch (Exception e) {
            throw new NttdataException(e.getMessage());
        }
    }

    @Override
    public List<Client> allClient() {
        try{
            return this.clientRepository.findAll();
        } catch (Exception e) {
            throw new NttdataException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Long idClient, Client request) {
        try{
            this.clientRepository.update(idClient, request);
        } catch (Exception e) {
            throw new NttdataException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long idClient) {
        try{
            this.clientRepository.deleteById(idClient);
        } catch (Exception e) {
            throw new NttdataException(e.getMessage());
        }
    }
}
