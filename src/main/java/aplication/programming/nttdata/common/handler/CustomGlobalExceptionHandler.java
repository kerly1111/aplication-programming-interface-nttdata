package aplication.programming.nttdata.common.handler;

import aplication.programming.nttdata.common.exception.NttdataException;
import aplication.programming.nttdata.vo.response.NttdataErrorResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(NttdataException.class)
    public ResponseEntity<NttdataErrorResponseVO> methodArgumentNotValidException(NttdataException e) {
        NttdataErrorResponseVO exception;

        exception = NttdataErrorResponseVO.builder()
                .typeMessage("Error")
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<NttdataErrorResponseVO> methodArgumentNotValidException(ConstraintViolationException e){
        NttdataErrorResponseVO exception;

        exception = NttdataErrorResponseVO.builder()
                .typeMessage("Error de restricciones")
                .message(e.getConstraintViolations().iterator().next().getMessage())
                .build();
        return ResponseEntity.badRequest().body(exception);
    }
}
