package ind.xwm.basic.pattern.adapter;

/**
 * Created by XuWeiman on 2016/12/15.
 * 橙子实现类
 */
public class Orange implements OrangeType {
	public void peel() {
		System.out.println("吃之前要先剥皮" + this.getClass().getSimpleName());
	}
}
