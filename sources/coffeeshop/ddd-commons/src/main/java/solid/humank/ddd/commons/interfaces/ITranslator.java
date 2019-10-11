package solid.humank.ddd.commons.interfaces;

import java.util.List;

public interface ITranslator<O,I> {

    O translate(I transRequest);
}
