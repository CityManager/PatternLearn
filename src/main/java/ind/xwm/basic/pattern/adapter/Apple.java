package ind.xwm.basic.pattern.adapter;

/**
 * Created by XuWeiman on 2016/12/15.
 * 苹果接口实现类
 */
public class Apple implements AppleType {
	public void eat() {
		System.out.println("大口啃吃" + this.getClass().getSimpleName());
	}
}
