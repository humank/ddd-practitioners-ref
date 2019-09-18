package solid.humank.ddd.commons.baseclasses;

import solid.humank.ddd.commons.interfaces.IEntity;

import java.util.Comparator;

public abstract class Entity<T> implements IEntity<T>, Comparator<T> {
}
