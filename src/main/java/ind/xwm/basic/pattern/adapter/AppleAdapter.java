package ind.xwm.basic.pattern.adapter;

/**
 * Created by XuWeiman on 2016/12/15.
 * 将橙子适配为苹果的适配器
 */
public class AppleAdapter implements AppleType {
	private OrangeType orange = new Orange();
	public void eat() {
		orange.peel();
		System.out.println("大口啃吃" + orange.getClass().getSimpleName());
	}

	public static void main(String... args) {
		AppleType apple = new Apple();
		apple.eat();
		System.out.println("=====================");
		AppleType adapter = new AppleAdapter();
		adapter.eat();
	}
}
