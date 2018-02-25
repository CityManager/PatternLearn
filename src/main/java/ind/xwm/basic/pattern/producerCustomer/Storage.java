package ind.xwm.basic.pattern.producerCustomer;

public interface Storage<T> {
    int getSeries();

    void storage(T t) throws Exception;

    T takeOut() throws Exception;
}
