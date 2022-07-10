package aplication.programming.nttdata.vo.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MovementResponseVO {

    private String accountNumber;

    private String accountType;

    private Date date;

    private String movementType;

    private Double value;

    private Double balance;

    private Boolean status;
}
