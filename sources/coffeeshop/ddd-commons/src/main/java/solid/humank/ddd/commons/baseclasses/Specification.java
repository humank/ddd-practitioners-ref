package solid.humank.ddd.commons.baseclasses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;

public class Specification<T> {


    protected T entity;
    protected Predicate<T> predicate;

    protected Specification() {
    }

    public Specification(Predicate<T> predicate) {

        this.predicate = predicate;
    }

    public Specification(T entity, Predicate predicate) {
        this.entity = entity;
        this.predicate = predicate;
    }

    public boolean isSatisfy() {
        return this.predicate.test(entity);
    }

}
