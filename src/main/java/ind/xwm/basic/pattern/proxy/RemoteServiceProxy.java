package ind.xwm.basic.pattern.proxy;

/**
 * Created by XuWeiman on 2016/12/15.
 * 远程接口的代理类
 */
public class RemoteServiceProxy implements RemoteService {
    private RemoteService remoteService;

    public RemoteServiceProxy() {
        this.remoteService = new RemoteServiceImpl();
    }

    public void queryBalance() {
        System.out.println("访问查询余额接口之前进行日志记录" + this.getClass().getSimpleName());
        remoteService.queryBalance();
    }

    public static void main(String... args) {
        RemoteService remoteService = new RemoteServiceImpl();
        remoteService.queryBalance();
        System.out.println("==========使用代理==========");
        RemoteServiceProxy proxy = new RemoteServiceProxy();
        proxy.queryBalance();
    }
}


interface RemoteService {
    void queryBalance();
}

class RemoteServiceImpl implements RemoteService {
    public void queryBalance() {
        System.out.println("调用远程服务接口查询余额" + this.getClass().getSimpleName());
    }
}