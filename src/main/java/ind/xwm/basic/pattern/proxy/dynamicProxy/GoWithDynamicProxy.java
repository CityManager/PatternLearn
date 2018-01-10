package ind.xwm.basic.pattern.proxy.dynamicProxy;

import java.lang.reflect.Proxy;

public class GoWithDynamicProxy {
    public static void main(String... args) {
        ClassLoader loader = GoWithDynamicProxy.class.getClassLoader();
        ServiceOneAble serviceOneObject = new ServiceOneAble() {
            public void say() {
                System.out.println("Say something..." + this.getClass().getSimpleName());
            }
        };
        Object proxy = Proxy.newProxyInstance(loader, new Class[]{ServiceOneAble.class}, new MyHandler(serviceOneObject));
        ServiceOneAble serviceOne = (ServiceOneAble) proxy;
        serviceOne.say();
        System.out.println("=============================");
        ServiceTwoAble serviceTwoObject = new ServiceTwoAble() {
            public void run() {
                System.out.println("run run run " + this.getClass().getSimpleName());
            }
        };
        proxy = Proxy.newProxyInstance(loader, new Class[]{ServiceTwoAble.class}, new MyHandler(serviceTwoObject));
        ServiceTwoAble serviceTwo = (ServiceTwoAble) proxy;
        serviceTwo.run();
    }
}
