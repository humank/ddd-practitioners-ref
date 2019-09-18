package solid.humank.ddd.commons.interfaces;

import java.util.Collection;
import java.util.Collections;

public interface IAggregateRoot {

    Collection<IDomainEvent> domainEvents = Collections.emptyList();
}
