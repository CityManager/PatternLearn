package ind.xwm.basic.pattern.decorator;

/**
 * Created by XuWeiman on 2016/12/15.
 *
 */
public class Art extends GirlDecorator {
	private Girl girl;
	public Art(Girl girl) {
		this.girl = girl;
	}
	public String getDescription() {
		return girl.getDescription() + " +Like Art";
	}
	public void draw() {
		System.out.println("艺术生女孩正在画画~");
	}
}
