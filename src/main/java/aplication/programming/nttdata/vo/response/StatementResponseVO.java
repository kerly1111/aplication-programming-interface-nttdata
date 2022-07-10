package aplication.programming.nttdata.vo.response;

import jdk.jfr.Name;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StatementResponseVO {

    @Name(value = "Cliente")
    private String client;

    private String accountNumber;

    private String accountType;

    private Double initialBalance;

    private Double totalCredits;

    private Double totalDebits;

    private Double balance;

    private Boolean status;
}
