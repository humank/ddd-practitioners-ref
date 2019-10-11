package solid.humank.ddd.commons.interfaces;

import java.util.List;

public interface ITranslator<O,I> {

    List<O> translate(List<I> transRequest);
}
