package aplication.programming.nttdata.controller;

import aplication.programming.nttdata.model.Client;
import aplication.programming.nttdata.service.interfaces.IClientService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Resource
    private IClientService clientService;

    @PostMapping
    public Client create(@RequestBody Client request){
        return this.clientService.create(request);
    }

    @GetMapping
    public List<Client> allClient(){
        return this.clientService.allClient();
    }

    @PutMapping("/{idClient}")
    public String update(@PathVariable Long idClient, @RequestBody Client request){
        this.clientService.update(idClient, request);
        return "Usuario actualizado exitosamente";
    }

    @DeleteMapping("/{idClient}")
    public String delete(@PathVariable Long idClient){
        this.clientService.delete(idClient);
        return "Usuario eliminado exitosamente";
    }
}
