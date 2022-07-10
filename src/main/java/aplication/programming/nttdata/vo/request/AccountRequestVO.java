package aplication.programming.nttdata.vo.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AccountRequestVO {

    private Long id;

    @NotNull(message = "The field accountNumber can not be null.")
    private String accountNumber;

    @NotNull(message = "The field accountType can not be null.")
    private String accountType;

    @NotNull(message = "The field initialBalance can not be null.")
    private Double initialBalance;

    @NotNull(message = "The field status can not be null.")
    private Boolean status;

    @NotNull(message = "The field identification can not be null.")
    private String identification;

    @NotNull(message = "The field name can not be null.")
    private String name;

}
