package aplication.programming.nttdata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_account", nullable = false)
    @SequenceGenerator(name = "person_id_person_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_person_seq")
    private Long id;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "initial_balance", nullable = false)
    private Double initialBalance;

    @Column(nullable = false)
    private Boolean status;

    @Column(name = "fk_client", nullable = false)
    private Long idClient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_client",
            referencedColumnName = "id_client",
            insertable = false,
            updatable = false,
            nullable = false)
    private Client client;

    @OneToMany(mappedBy = "account")
    private List<Movement> movements;

}
