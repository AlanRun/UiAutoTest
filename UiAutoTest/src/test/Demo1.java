package test;

import java.io.IOException;

import test.helper.Constant;
import test.helper.LanxinTestHelper;
import uiautotestutils.ATCUiAutoTestCase;
import uiautotestutils.UiAutomatorHelper;
import com.android.uiautomator.core.UiObjectNotFoundException;

public class Demo1 extends ATCUiAutoTestCase {

	LanxinTestHelper helper;

	@Override
	protected void setUp() throws Exception {

		try {
			super.setUp();

			helper = new LanxinTestHelper(this);
			new Constant(getConfigProperty("pkg"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String className, jarName, androidId;
		className = "test.Demo1";
		String testName = "testLauncherApp";
		jarName = "AutoTest";
		androidId = "1";

		new UiAutomatorHelper(jarName, className, testName, androidId);
	}

	public void testLauncherApp() throws UiObjectNotFoundException {

		String name = helper.sendMsg_GroupCard("");
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
