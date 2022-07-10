package aplication.programming.nttdata.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPerson is a Querydsl query type for Person
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QPerson extends EntityPathBase<Person> {

    private static final long serialVersionUID = -612331403L;

    public static final QPerson person = new QPerson("person");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath gender = createString("gender");

    public final StringPath identification = createString("identification");

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public QPerson(String variable) {
        super(Person.class, forVariable(variable));
    }

    public QPerson(Path<? extends Person> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerson(PathMetadata metadata) {
        super(Person.class, metadata);
    }

}

