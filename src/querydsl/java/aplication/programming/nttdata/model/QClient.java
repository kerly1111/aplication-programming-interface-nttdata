package aplication.programming.nttdata.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClient is a Querydsl query type for Client
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClient extends EntityPathBase<Client> {

    private static final long serialVersionUID = -978327317L;

    public static final QClient client = new QClient("client");

    public final QPerson _super = new QPerson(this);

    public final ListPath<Account, QAccount> accounts = this.<Account, QAccount>createList("accounts", Account.class, QAccount.class, PathInits.DIRECT2);

    //inherited
    public final StringPath address = _super.address;

    //inherited
    public final NumberPath<Integer> age = _super.age;

    //inherited
    public final StringPath gender = _super.gender;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath identification = _super.identification;

    //inherited
    public final StringPath name = _super.name;

    public final StringPath password = createString("password");

    //inherited
    public final StringPath phone = _super.phone;

    public final BooleanPath status = createBoolean("status");

    public QClient(String variable) {
        super(Client.class, forVariable(variable));
    }

    public QClient(Path<? extends Client> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClient(PathMetadata metadata) {
        super(Client.class, metadata);
    }

}

