package aplication.programming.nttdata.service.interfaces;

import aplication.programming.nttdata.vo.request.MovementRequestVO;
import aplication.programming.nttdata.vo.response.MovementResponseVO;
import aplication.programming.nttdata.vo.response.StatementResponseVO;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;
import java.util.List;

@Validated
public interface IMovementService {

    MovementResponseVO create(MovementRequestVO request);

    List<MovementResponseVO> allMovement();

    void update(Long idMovement, MovementRequestVO request);

    void delete(Long idMovement);

    List<StatementResponseVO> report(Date dateStart, Date dateEnd, String identification);

}
