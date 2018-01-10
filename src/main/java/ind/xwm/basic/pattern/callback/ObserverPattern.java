package ind.xwm.basic.pattern.callback;


import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式：下面的代码需要跟踪 Observable的代码才能知道是怎么通知到订阅者（观察者）的
 */
public class ObserverPattern {

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        Subscriber Subscriber = new Subscriber();
        publisher.addObserver(Subscriber);
        publisher.updateContent("这个是我最新的动态");

    }
}

class Publisher extends Observable {
    private String content;

    public void updateContent(String content) {
        this.content = content;
        this.setChanged();  // 设置信息发布
        this.notifyObservers();
    }
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers() {
        System.out.println(this.getClass().getSimpleName() + "--开始通知 订阅者，有信息发布");
        super.notifyObservers(content);
    }
}


class Subscriber implements Observer {
    public void update(Observable o, Object arg) {
        // Observer 接口的这个 update 回调方法，还只有了调用者引用，简直不要太方便
        System.out.println(this.getClass().getSimpleName() + "--得到发布者最新的发布通知：" + String.valueOf(arg));
    }
}
