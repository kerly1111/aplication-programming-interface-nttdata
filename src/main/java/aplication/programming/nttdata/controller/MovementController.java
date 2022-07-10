package aplication.programming.nttdata.controller;

import aplication.programming.nttdata.service.interfaces.IMovementService;
import aplication.programming.nttdata.vo.request.MovementRequestVO;
import aplication.programming.nttdata.vo.response.MovementResponseVO;
import aplication.programming.nttdata.vo.response.StatementResponseVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovementController {

    @Resource
    private IMovementService movementService;

    @PostMapping
    public MovementResponseVO create(@RequestBody MovementRequestVO request){
        return this.movementService.create(request);
    }

    @GetMapping
    public List<MovementResponseVO> allMovement(){
        return this.movementService.allMovement();
    }

    @PutMapping("/{idMovement}")
    public String update(@PathVariable Long idMovement, @RequestBody MovementRequestVO request){
        this.movementService.update(idMovement, request);
        return "Movimeinto actualizado exitosamente";
    }

    @DeleteMapping("/{idMovement}")
    public String delete(@PathVariable Long idMovement){
        this.movementService.delete(idMovement);
        return "Movimeinto eliminado exitosamente";
    }

    @GetMapping("/report")
    public List<StatementResponseVO> report(@RequestParam Date dateStart, @RequestParam Date dateEnd,
                                                  @RequestParam String identification){
        return this.movementService.report(dateStart, dateEnd, identification);
    }

}
