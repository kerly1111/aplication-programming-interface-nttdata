package aplication.programming.nttdata.vo.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BalanceValueVO {

    private Double initialBalance;

    private Double totalCredits;

    private Double totalDebits;

    private Double balance;
}
