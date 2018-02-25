package ind.xwm.basic.pattern.producerCustomer.blockQueue;

import ind.xwm.basic.pattern.producerCustomer.Storage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueuePhoneStorage<T> implements Storage<T> {
    private int series;

    private BlockingQueue<T> phoneQueue = new ArrayBlockingQueue<>(10);

    @Override
    public synchronized int getSeries() {
        return series++;
    }

    @Override
    public void storage(T t) throws Exception {
        phoneQueue.put(t);
    }

    @Override
    public T takeOut() throws Exception {
        return phoneQueue.take();
    }
}
