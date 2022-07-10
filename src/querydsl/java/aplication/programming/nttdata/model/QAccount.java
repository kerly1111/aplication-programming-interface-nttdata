package aplication.programming.nttdata.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = 1993685261L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccount account = new QAccount("account");

    public final StringPath accountNumber = createString("accountNumber");

    public final StringPath accountType = createString("accountType");

    public final QClient client;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idClient = createNumber("idClient", Long.class);

    public final NumberPath<Double> initialBalance = createNumber("initialBalance", Double.class);

    public final ListPath<Movement, QMovement> movements = this.<Movement, QMovement>createList("movements", Movement.class, QMovement.class, PathInits.DIRECT2);

    public final BooleanPath status = createBoolean("status");

    public QAccount(String variable) {
        this(Account.class, forVariable(variable), INITS);
    }

    public QAccount(Path<? extends Account> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccount(PathMetadata metadata, PathInits inits) {
        this(Account.class, metadata, inits);
    }

    public QAccount(Class<? extends Account> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.client = inits.isInitialized("client") ? new QClient(forProperty("client")) : null;
    }

}

