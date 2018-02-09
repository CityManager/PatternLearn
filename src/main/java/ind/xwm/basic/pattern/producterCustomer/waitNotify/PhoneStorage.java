package ind.xwm.basic.pattern.producterCustomer.waitNotify;

import java.util.ArrayList;
import java.util.List;

/**
 * phone 产品缓存区
 */
public class PhoneStorage<T> {
    private static int MAX = 20;

    private int series;
    private int index = -1;


    private List<T> storages = new ArrayList<T>(MAX);

    public synchronized int getSeries() {
        return series;
    }

    public synchronized void storage(T t) throws Exception {
        long threadID = Thread.currentThread().getId();
        while (index >= MAX) {
            System.out.println("线程" + threadID + ":缓存区满，无法存放产品");
            this.wait();
        }
        storages.add(t);
        series++;
        index++;
        this.notifyAll();  // 是否会 自动 notify ？
        System.out.println("线程" + threadID + ":成功将产品存放进缓存区");
    }


    public synchronized T takeOut() throws Exception {
        long threadID = Thread.currentThread().getId();
        while (index < 0) {
            System.out.println("线程" + threadID + ":缓存区空，无法获取产品");
            this.wait();
        }
        T t = storages.remove(index);
        index--;
        this.notifyAll();
        System.out.println("线程" + threadID + ":成功将产品从缓存区取出");
        return t;
    }
}
