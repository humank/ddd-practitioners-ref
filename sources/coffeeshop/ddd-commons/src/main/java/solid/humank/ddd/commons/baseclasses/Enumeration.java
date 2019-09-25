package solid.humank.ddd.commons.baseclasses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Enumeration implements Comparable {

    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PUBLIC)
    private String name;

    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PUBLIC)
    private int id;

    @Override
    public String toString() {
        return this.name;
    }

    protected Enumeration(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //TODO: provide a method to get all public enumeration types.
    //public static IEnumerable<T> GetAll<T>() where T : Enumeration

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enumeration that = (Enumeration) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public int compareTo(Object other) {

        return this.compareTo(((Enumeration) other).id);
    }
}

