package aplication.programming.nttdata.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NttdataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NttdataException(String message){
        super(message);
    }
}
