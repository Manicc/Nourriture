package cn.edu.bjtu.svnteen.nourriture.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * 既不输入账号，也不输入密码，判断是否有提示
 * @author Tans
 */
public class Runner2 extends UiAutomatorTestCase {
	public void testDemo() throws UiObjectNotFoundException {
		getUiDevice().pressHome();
		// 打开"Nourriture"
		UiObject nourriture = new UiObject(new UiSelector().text("Nourriture"));
		nourriture.click();

		// 进入"我的"
		UiObject mine = new UiObject(new UiSelector().text("我的"));
		mine.click();
		// 进入"登录"
		UiObject login = new UiObject(new UiSelector().text("登录"));
		login.click();

		// 点击登陆
		UiObject trueLogin = new UiObject(new UiSelector().text("登陆"));
		trueLogin.click();

	}
}
