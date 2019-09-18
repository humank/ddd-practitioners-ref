package solid.humank.ddd.commons.interfaces;

@FunctionalInterface
public interface ISelector {

    public <T>T select(IAggregateRoot aggregateRoot);
}
