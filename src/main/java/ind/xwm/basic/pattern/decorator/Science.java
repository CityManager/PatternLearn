package ind.xwm.basic.pattern.decorator;

/**
 * Created by XuWeiman on 2016/12/15.
 *
 */
public class Science extends GirlDecorator {
	private Girl girl;
	public Science(Girl girl) {
		this.girl = girl;
	}
	public String getDescription() {
		return girl.getDescription() + " +Like Science";
	}

	public void experimentalize() {
		System.out.println("科学家女孩正在做实验~");
	}
}
