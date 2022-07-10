package aplication.programming.nttdata.vo.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NttdataErrorResponseVO {

    private String typeMessage;

    private String message;
}
