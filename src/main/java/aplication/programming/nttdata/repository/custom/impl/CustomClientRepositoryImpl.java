package aplication.programming.nttdata.repository.custom.impl;

import aplication.programming.nttdata.model.Client;
import aplication.programming.nttdata.model.QClient;
import aplication.programming.nttdata.repository.custom.CustomClientRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class CustomClientRepositoryImpl implements CustomClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private QClient qClient;
    private JPAQuery<Client> query;

    private JPAQueryFactory queryUpdate;

    @Override
    public void update(Long idClient, Client request) {
        this.initQuery(Boolean.TRUE);

        this.queryUpdate.update(this.qClient)
                .set(this.qClient.identification, request.getIdentification())
                .set(this.qClient.name, request.getName())
                .set(this.qClient.gender, request.getGender())
                .set(this.qClient.age, request.getAge())
                .set(this.qClient.address, request.getAddress())
                .set(this.qClient.phone, request.getPhone())
                .set(this.qClient.password, request.getPassword())
                .set(this.qClient.status, request.getStatus())
                .where(this.qClient.id.eq(idClient))
                .execute();
    }

    @Override
    public Optional<Client>  findByIdentification(String identification) {
        this.initQuery(Boolean.FALSE);

        this.query.select(Projections.fields(Client.class,
                this.qClient.id,
                this.qClient.identification,
                this.qClient.name
        ));
        this.query.where(this.qClient.identification.eq(identification));

        return Optional.ofNullable(this.query.fetchFirst());
    }

    private void initQuery(Boolean isUpdate) {
        this.qClient = QClient.client;

        if (isUpdate) {
            this.queryUpdate = new JPAQueryFactory(this.entityManager);
        } else {
            this.query = new JPAQuery<>(this.entityManager);
            this.query.from(this.qClient);
        }
    }
}
