package solid.humank.ddd.commons.baseclasses;

import java.util.function.Predicate;

public class Specification<Entity> {

    private Entity entity;
    private Predicate<Entity> predicate;

    protected Specification() {
    }

    public Specification(Predicate<Entity> predicate) {

        this.predicate = predicate;
    }

    public Specification(Entity entity, Predicate predicate) {
        this.entity = entity;
        this.predicate = predicate;
    }

    public boolean isSatisfy() {
        return this.predicate.test(entity);
    }

    //TODO: void the operator override code snippets. Java doesn't support the feature.
}
