package solid.humank.ddd.commons.baseclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public abstract class PropertyComparer<T> {

    protected List<Object> container = new ArrayList<>();

    /**
     * Ensure return a Collection which contains all of the properties in the sub-class.
     * Will leverage the returned collection to do property comparison.
     *
     * @return
     */
    protected abstract Iterable<Object> getEqualityComponents();

    @Override
    public boolean equals(Object obj) {


        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ValueObject that = (ValueObject) obj;

        return this.getEqualityComponents().equals(((ValueObject) obj).getEqualityComponents());
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (Object component : this.getEqualityComponents()) {
            result = result ^ component.hashCode();
        }
        return result;
    }
}
