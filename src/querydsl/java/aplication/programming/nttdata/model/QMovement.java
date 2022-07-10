package aplication.programming.nttdata.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovement is a Querydsl query type for Movement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovement extends EntityPathBase<Movement> {

    private static final long serialVersionUID = -586797617L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMovement movement = new QMovement("movement");

    public final QAccount account;

    public final NumberPath<Double> balance = createNumber("balance", Double.class);

    public final DatePath<java.sql.Date> date = createDate("date", java.sql.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idAccount = createNumber("idAccount", Long.class);

    public final StringPath movementType = createString("movementType");

    public final NumberPath<Double> value = createNumber("value", Double.class);

    public QMovement(String variable) {
        this(Movement.class, forVariable(variable), INITS);
    }

    public QMovement(Path<? extends Movement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMovement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMovement(PathMetadata metadata, PathInits inits) {
        this(Movement.class, metadata, inits);
    }

    public QMovement(Class<? extends Movement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new QAccount(forProperty("account"), inits.get("account")) : null;
    }

}

