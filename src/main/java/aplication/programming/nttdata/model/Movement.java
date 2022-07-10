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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@SuperBuilder
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "movement")
public class Movement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_movement", nullable = false)
    @SequenceGenerator(name = "movement_id_movement_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movement_id_movement_seq")
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(name = "movement_type", nullable = false)
    private String movementType;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Double balance;

    @Column(name = "fk_account", nullable = false)
    private Long idAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account",
            referencedColumnName = "id_account",
            insertable = false,
            updatable = false,
            nullable = false)
    private Account account;

}
