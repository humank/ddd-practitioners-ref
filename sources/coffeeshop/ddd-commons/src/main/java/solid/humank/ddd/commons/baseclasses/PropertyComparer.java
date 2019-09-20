package solid.humank.ddd.commons.baseclasses;

public abstract class PropertyComparer<T extends ValueObject> {

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
