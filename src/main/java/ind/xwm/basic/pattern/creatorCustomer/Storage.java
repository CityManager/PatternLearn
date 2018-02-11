package ind.xwm.basic.pattern.creatorCustomer;

public interface Storage<T> {
    int getSeries();
    void storage(T t) throws Exception;
    T takeOut() throws Exception;
}
