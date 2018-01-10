package ind.xwm.basic.pattern.decorator;

/**
 *
 * @author XuWeiman
 *
 */
public class DecoratorLearning {
	public static void main(String... args) {
		Girl chineseGile = new ChineseGirl();
		Girl japaneseGile = new JapaneseGirl();
		Girl girl = new Science(new Art(chineseGile));
		System.out.println(girl.getDescription());
		((Science)girl).experimentalize();
		System.out.println(japaneseGile.getDescription());
	}
}
