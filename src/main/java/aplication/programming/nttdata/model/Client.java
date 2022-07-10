package aplication.programming.nttdata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "client")
public class Client extends Person {

    @Id
    @Column(name = "id_client", nullable = false)
    @SequenceGenerator(name = "client_id_client_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_client_seq")
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;
}
