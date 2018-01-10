package ind.xwm.basic.pattern.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by XuWeiman on 2016/12/15.
 * 使用动态代理
 */
public class MyHandler implements InvocationHandler {
	private Object target;

	public MyHandler(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("使用动态代理处理器来对接口方法进行调用");
		return method.invoke(target, args);
	}
}
