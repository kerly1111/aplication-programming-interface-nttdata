package aplication.programming.nttdata.controller;

import aplication.programming.nttdata.service.interfaces.IAccountService;
import aplication.programming.nttdata.vo.request.AccountRequestVO;
import aplication.programming.nttdata.vo.response.AccountResponseVO;
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
@RequestMapping("/cuentas")
public class AccountController {

    @Resource
    private IAccountService accountService;

    @PostMapping
    public AccountResponseVO create(@RequestBody AccountRequestVO request){
        return this.accountService.create(request);
    }

    @GetMapping
    public List<AccountResponseVO> allAccount(){
        return this.accountService.allAccount();
    }

    @PutMapping("/{idAccount}")
    public String update(@PathVariable Long idAccount, @RequestBody AccountRequestVO request){
        this.accountService.update(idAccount, request);
        return "Cuenta actualizada exitosamente";
    }

    @DeleteMapping("/{idAccount}")
    public String delete(@PathVariable Long idAccount){
        this.accountService.delete(idAccount);
        return "Cuenta eliminado exitosamente";
    }
}
