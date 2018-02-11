package ind.xwm.basic.pattern.creatorCustomer.awaitSignal;

import ind.xwm.basic.pattern.creatorCustomer.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantPhoneStorage<T> implements Storage<T> {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int MAX = 20;

    private int series;
    private int index = -1;
    private List<T> storageList = new ArrayList<T>(MAX);

    public int getSeries() {
        lock.lock();
        try {
            return series;
        } finally {
            lock.unlock();
        }

    }

    public void storage(T t) throws Exception {
        long threadID = Thread.currentThread().getId();
        lock.lock();
        try {
            while (index >= MAX) {
                System.out.println("线程" + threadID + ":缓存区满，无法存放产品");
                condition.await();
            }
            storageList.add(t);
            series++;
            index++;
            condition.signalAll();
            System.out.println("线程" + threadID + ":成功将产品存放进缓存区");
        } finally {
            lock.unlock();
        }
    }

    public T takeOut() throws Exception {
        long threadID = Thread.currentThread().getId();
        lock.lock();
        try {
            while (index < 0) {
                System.out.println("线程" + threadID + ":缓存区空，无法获取产品");
                condition.await(); // 锁等待
            }
            T t = storageList.remove(index);
            index--;
            condition.signalAll(); // 发信号通知其他所有等在锁的线程
            System.out.println("线程" + threadID + ":成功将产品从缓存区取出");
            return t;
        } finally {
            lock.unlock();
        }

    }
}
