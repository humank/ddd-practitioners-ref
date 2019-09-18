package solid.humank.ddd.commons.interfaces;

public interface ITranslator<I,O> {

    O translate(I transRequest);
}
