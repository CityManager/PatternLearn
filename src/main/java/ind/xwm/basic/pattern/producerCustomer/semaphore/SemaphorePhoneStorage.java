package ind.xwm.basic.pattern.producerCustomer.semaphore;

import ind.xwm.basic.pattern.producerCustomer.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphorePhoneStorage<T> implements Storage<T> {
    private static int MAX = 20;
    private static final Semaphore notFull = new Semaphore(MAX);
    private static final Semaphore notEmpty = new Semaphore(0);
    private static final Semaphore lock = new Semaphore(1);

    private int series;
    private List<T> storageList = new ArrayList<>();
    private int index = -1;

    @Override
    public synchronized int getSeries() {
        return series;
    }

    @Override
    public void storage(T t) throws Exception {
        long threadID = Thread.currentThread().getId();
        try {
            notFull.acquire();  // 申请非满信号
            lock.acquire();    // 申请唯一执行信号
            if (index >= MAX) {
                System.out.println("线程" + threadID + ":缓存区满，无法存放产品");
            } else {
                storageList.add(t);
                series++;
                index++;
                System.out.println("线程" + threadID + ":成功将产品存放进缓存区");
            }
        } finally {
            lock.release();  // 释放唯一执行信号
            notEmpty.release(); // 释放非空信号（应该说不能叫释放吧，应该为给非空信号组发一个信号）
        }

    }

    @Override
    public T takeOut() throws Exception {
        long threadID = Thread.currentThread().getId();
        T t = null;
        try {
            notEmpty.acquire();
            lock.acquire();
            if (index < 0) {
                System.out.println("线程" + threadID + ":缓存区空，无法获取产品");
            } else {
                t = storageList.remove(index);
                index--;
                System.out.println("线程" + threadID + ":成功将产品从缓存区取出");
            }
        } finally {
            lock.release();
            notFull.release();
        }
        return t;
    }
}
