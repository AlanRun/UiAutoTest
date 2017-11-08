package uiautotestutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import test.helper.Constant;
import uiautotestutils.utils.ConfigProperty;
import uiautotestutils.utils.ShellUtils;

import junit.framework.Assert;

import android.app.Instrumentation;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * Main class for development of UI Automator tests Ui Automator has fully
 * support for Views which can be detected by accessibility service, like menu,
 * string label and also for view class name ATCUiAutoTestCase is an extension
 * of UiAutomatorTestCase, can be used in conjunction with UiAutomatorTestCase
 * 
 */
public class ATCUiAutoTestCase extends UiAutomatorTestCase {
	public long TIMEOUT = Constant.TIMEOUT;
	private String AUTOTEST_TAG = Constant.TAG;
	Instrumentation inst;
	public boolean DEBUG_LOG = false;

	/**
	 * Run in end of each test case, this case is for capturing screenshot when
	 * running parameter is "1" Example:
	 * {@code adb shell uiautomator xxxx.jar -c XXXXTest -e screenshot 1}
	 */
	@Override
	protected void tearDown() throws Exception {
		if (getParams().getString("screenshot") != null)
			if (getParams().getString("screenshot").contentEquals("1")) {
				takeScreenshot(Constant.SCREENSHOTS);
			}
		super.tearDown();
	}

	public ATCUiAutoTestCase() {
		this(20000, Constant.TAG);
	}

	public ATCUiAutoTestCase(long timeout) {
		this(timeout, Constant.TAG);
	}

	/**
	 * Get method name who invoke this method
	 * 
	 * @return method name
	 */
	public static String getCurrentMethodName() {
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}

	/**
	 * Get class name witch invoke method in
	 * 
	 * @return class name
	 */
	public static String getCurrentClassName() {
		return Thread.currentThread().getStackTrace()[3].getClassName();
	}

	/**
	 * Constructor method
	 * 
	 * @param timeout
	 *            global timeout value
	 * @param tagName
	 *            Global Android Log TAG
	 */
	public ATCUiAutoTestCase(long timeout, String tagName) {

		this.TIMEOUT = timeout;
		this.AUTOTEST_TAG = tagName;
	}

	/**
	 * This method is to click Class name on screen by instance
	 * 
	 * @param cls
	 *            you can get from uiautomatorviewer "class" name in Node
	 *            details, Example:
	 *            {@code clickClass("android.widget.ImageView", 2, true);}
	 * 
	 * @param instance
	 *            Set the search criteria to match the widget by its instance
	 *            number. The instance value must be 0 or greater, where the
	 *            first instance is 0. For example, to simulate a user click on
	 *            the third image that is enabled in a UI screen, you could
	 *            specify a a search criteria where the instance is 2
	 * 
	 * @param isNewWindow
	 *            It will wait for new window open, default timeout is 20s
	 * 
	 * @throws {@link UiObjectNotFoundException} if the class not found
	 */
	public void clickClass(String cls, int instance, boolean isNewWindow) throws UiObjectNotFoundException {
		UiObject obj = new UiScrollable(new UiSelector().className(cls).instance(instance));
		if (!isNewWindow)
			obj.click();
		else
			obj.clickAndWaitForNewWindow(TIMEOUT);
	}

	/**
	 * This method is to click the specified text label on screen
	 * 
	 * @param text
	 *            you can get from uiautomatorviewer "text" name in details
	 * @param isNewWindow
	 *            {@code true} to wait new window open after clicking
	 * 
	 * @throws {@link UiObjectNotFoundException} if the text not found
	 */
	public void clickText(String text, boolean isNewWindow) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().text(text));
		if (!isNewWindow)
			obj.click();
		else
			obj.clickAndWaitForNewWindow(TIMEOUT);
	}

	/**
	 * This method is to click text label contains some strings on screen,
	 * example: There is a "ABCDE FGHD" on the screen, you find "FGHD", you can
	 * 
	 * @param text
	 *            text: you can get from uiautomatorviewer "text" name
	 * 
	 * @param isNewWindow
	 *            {@code true} to wait new window open after clicking
	 * 
	 * @throws {@link UiObjectNotFoundException} if the text not found
	 */
	public void clickTextContains(String text, boolean isNewWindow) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().textContains(text));
		if (!isNewWindow)
			obj.click();
		else
			obj.clickAndWaitForNewWindow(TIMEOUT);
	}

	/**
	 * This method is to click label contains some Regular Express strings on
	 * screen
	 * 
	 * @param regExp
	 *            you can get from uiautomatorviewer "text" name in text details
	 *            *
	 * 
	 * @param isNewWindow
	 *            {@code true} to wait new window open after clicking
	 * 
	 * @throws {@link UiObjectNotFoundException} if the text not found
	 */
	public void clickTextRegExp(String regExp, boolean isNewWindow) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().textMatches(regExp));
		if (!isNewWindow)
			obj.click();
		else
			obj.clickAndWaitForNewWindow(TIMEOUT);
	}

	/**
	 * This method is to click content description contains some text strings on
	 * screen
	 * 
	 * @param text
	 *            you can get from uiautomatorviewer "content-desc" name in Node
	 *            details *
	 * 
	 * @param isNewWindow
	 *            {@code true} to wait new window open after clicking
	 * 
	 * @throws {@link UiObjectNotFoundException} if the text not found
	 */
	public void clickContentDescContains(String text, boolean isNewWindow) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().descriptionContains(text));
		if (!isNewWindow)
			obj.click();
		else
			obj.clickAndWaitForNewWindow(TIMEOUT);
	}

	/**
	 * This method is to click content description strings("content-desc") on
	 * screen
	 * 
	 * @param text
	 *            you can get from uiautomatorviewer "content-desc" name in Node
	 *            details *
	 * 
	 * @param isNewWindow
	 *            {@code true} to wait new window open after clicking
	 * 
	 * @throws {@link UiObjectNotFoundException} if the text not found
	 */
	public void clickContentDesc(String text, boolean isNewWindow) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().description(text));
		if (!isNewWindow)
			obj.click();
		else
			obj.clickAndWaitForNewWindow(TIMEOUT);
	}

	/**
	 * This method is to click child Class View strings on screen
	 * 
	 * @param className
	 *            The class name will click
	 * @param instance
	 *            the instance number on the screen
	 * @param isNewWindow
	 *            {@code true} to wait new window open after clicking
	 * 
	 * @throws {@link UiObjectNotFoundException} if the class not found
	 */
	public void clickChildItemByInstance(String className, int instance, boolean isNewWindow)
			throws UiObjectNotFoundException {
		UiCollection uiCol = new UiCollection(new UiSelector());
		UiObject obj = uiCol.getChildByInstance(new UiSelector().className(className), instance);
		if (!isNewWindow)
			obj.click();
		else
			obj.clickAndWaitForNewWindow(TIMEOUT);
	}

	/**
	 * This method is to click child Class View is strings on screen, this use
	 * for many same text
	 * 
	 * @param className
	 *            you can get from uiautomatorviewer "cls" name in Node details
	 *            *
	 * 
	 * @param text
	 *            the child text you look for.
	 * @throws {@link UiObjectNotFoundException} if the class not found
	 */
	public void clickChildByText(String className, String text, boolean isNewWindow) throws UiObjectNotFoundException {
		UiCollection uiColl = new UiCollection(new UiSelector());
		UiObject obj = uiColl.getChildByText(new UiSelector().className(className), text);
		if (!isNewWindow) {
			obj.click();
		} else {
			obj.clickAndWaitForNewWindow(TIMEOUT);
		}
	}

	/**
	 * Performs a swipe from one coordinate to another using the number of steps
	 * to determine smoothness and speed. Each step execution is throttled to
	 * 5ms per step. So for a 100 steps, the swipe will take about 1/2 second to
	 * complete.
	 * 
	 * @param steps
	 *            is the number of move steps sent to the system
	 */
	public void drag(int startX, int startY, int endX, int endY, int steps) {
		getUiDevice().drag(startX, startY, endX, endY, steps);
	}

	/**
	 * Performs a swipe from one coordinate to another using the number of steps
	 * to determine smoothness and speed. Each step execution is throttled to
	 * 5ms per step. So for a 100 steps, the swipe will take about 1/2 second to
	 * complete.
	 * 
	 * @param steps
	 *            is the number of move steps sent to the system
	 */
	public void swipe(int startX, int startY, int endX, int endY, int steps) {
		getUiDevice().swipe(startX, startY, endX, endY, steps);
	}

	/**
	 * Performs a swipe from one coordinate to another using the number of steps
	 * to determine smoothness and speed. Each step execution is throttled to
	 * 5ms per step. So for a 100 steps, the swipe will take about 1/2 second to
	 * complete.
	 * 
	 * @param steps
	 *            is the number of move steps sent to the system
	 * @param isDrag
	 *            true drag, false swipe.
	 */
	public void drag(int startX, int startY, int endX, int endY, int steps, boolean isDrag) {
		if (isDrag) {
			getUiDevice().drag(startX, startY, endX, endY, steps);
		} else {
			getUiDevice().swipe(startX, startY, endX, endY, steps);
		}
	}

	/**
	 * Performs a swipe from one relative coordinate to another using the number
	 * of steps to determine smoothness and speed. Each step execution is
	 * throttled to 5ms per step. So for a 100 steps, the swipe will take about
	 * 1/2 second to complete. Example:
	 * {@code dragRelative(0.1f, 0.2f, 0.5f, 0.555f)}
	 * 
	 * @param steps
	 *            is the number of move steps sent to the system
	 */
	public void dragRelative(float startX, float startY, float endX, float endY, int steps) {
		drag(toScreenX(startX), toScreenY(startY), toScreenX(endX), toScreenY(endY), steps);
	}

	/**
	 * Performs a swipe from one coordinate to another using the number of steps
	 * to determine smoothness and speed.
	 * 
	 * @param segmentSteps
	 *            is the number of move steps sent to the system, Each step
	 *            execution is throttled to 5ms per step. So for a 100 steps,
	 *            the swipe will take about 1/2 second to complete.
	 */
	public void drag(Point[] segments, int segmentSteps) {
		getUiDevice().swipe(segments, segmentSteps);

	}

	/**
	 * Drag screen by direction, left, right, up, or down
	 * 
	 * @param direction
	 *            The direction drag, drag to "left", "right", "up", "down"
	 *            example:{@code dragDirection("left", 50)} steps
	 * @param steps
	 *            Speed: 1=5ms, 100=1/2 seconds, normally=20
	 */
	public void dragDirection(String direction, int steps) {
		int startX = toScreenX(0.25f);
		int endX = toScreenX(0.75f);
		int startY = toScreenY(0.25f);
		int endY = toScreenY(0.75f);
		int H = getUiDevice().getDisplayHeight(); // y
		int W = getUiDevice().getDisplayWidth(); // x
		if (direction.equalsIgnoreCase("left")) {
			drag(toScreenX(0.8f), H / 2, toScreenX(0.2f), H / 2, steps);
		} else if (direction.equalsIgnoreCase("right")) {
			drag(toScreenX(0.2f), H / 2, toScreenX(0.8f), H / 2, steps);
		} else if (direction.equalsIgnoreCase("up")) {
			drag(W / 2, endY, W / 2, startY, steps);
		} else if (direction.equalsIgnoreCase("down")) {
			drag(W / 2, startY, W / 2, endY, steps);
		} else {
			drag(endX, H / 2, startX, H / 2, steps);
		}
	}

	/**
	 * Get center coordinate of a class by it's instance number on the screen
	 * 
	 * @param clsName
	 *            class name is the specified class will search
	 * @param instance
	 *            the class instance number on the screen
	 * @return Point the Point of the center coordinate
	 */
	public Point getCoordinateByView(String clsName, int instance) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().className(clsName).instance(instance));
		Point pt = new Point();
		pt.x = obj.getBounds().centerX();
		pt.y = obj.getBounds().centerY();
		return pt;
	}

	/**
	 * Drag from text to text (from coordinate)
	 * 
	 * @param from
	 *            text you drag start from
	 * @param end
	 *            text you drag stop
	 * @param steps
	 *            speed of drag, 100 is 1/2 seconds, normally, 15~20
	 * @throws {@link UiObjectNotFoundException} if the string not found
	 */
	public void drag(String from, String end, int steps) throws UiObjectNotFoundException {
		Point[] segments = { getCoordinateByText(from), getCoordinateByText(end) };
		drag(segments, steps);
	}

	/**
	 * Drag from view to view by Class name
	 * 
	 * @param clsFrom
	 *            drag start from class name
	 * @param instanceFrom
	 *            drag start from class instance
	 * @param clsTo
	 *            drag end of class name
	 * @param instanceTo
	 *            drag end of class indoex
	 * @param segmentSteps
	 *            speed of drag motions, 100 is 0.5s, normally ~20
	 * @throws {@link UiObjectNotFoundException} if the class not found
	 */
	public void dragViewByClsToView(String clsFrom, int instanceFrom, String clsTo, int instanceTo, int segmentSteps)
			throws UiObjectNotFoundException {
		Point[] segments = { getCoordinateByView(clsFrom, instanceFrom), getCoordinateByView(clsTo, instanceTo) };
		drag(segments, segmentSteps);
	}

	/**
	 * Unlock the screen in lock screen
	 * 
	 * @throws RemoteException
	 */
	public void disableLockScreen() throws RemoteException {
		if (!getUiDevice().isScreenOn()) {
			getUiDevice().wakeUp();
			waitForWindowUpdate(2000);
		}
		UiObject unlockArea = new UiObject(new UiSelector().descriptionContains("Expand unlock area"));
		if (unlockArea.exists()) {
			try {
				drag(unlockArea.getBounds().centerX(), unlockArea.getBounds().centerY(), toScreenX(0.5f),
						toScreenY(0.5f), 14);
			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
			}
		}
		UiObject obj = new UiObject(new UiSelector().className("android.appwidget.AppWidgetHostView"));
		UiObject obj1 = new UiObject(new UiSelector().textContains("Swipe up or down to unlock"));
		if (obj1.exists() || obj.exists()) {

			drag(toScreenX(0.5f), toScreenY(0.7f), toScreenX(0.5f), toScreenY(0.2f), 50);
		}

	}

	/**
	 * wake up screen
	 */
	public void wakeUp() throws RemoteException {
		if (!getUiDevice().isScreenOn()) {
			getUiDevice().wakeUp();
		}
	}

	/**
	 * To relative size of X coordinate like 0.55f, it means 0.55x(width=1080)
	 * 
	 * @param normalizedX
	 *            the ratio of the width, like 0.50f is center of the width
	 * @return {@code int} the x coodinate
	 */
	public int toScreenX(float normalizedX) {
		Float f1 = (normalizedX * Integer.valueOf(getUiDevice().getDisplayWidth()));
		return f1.intValue();
	}

	/**
	 * To relative size of Y coordinate like 0.55f, it means 0.55x(height=1080)
	 * 
	 * @param normalizedY
	 *            the ratio of the height, like 0.50f is center of the height
	 * @return {@code int} the y coodinate
	 */
	public int toScreenY(float normalizedY) {
		Float f1 = normalizedY * Integer.valueOf(getUiDevice().getDisplayHeight());
		return f1.intValue();
	}

	/**
	 * Press Key of Home, back, delete, menu, down, up, left, right, center,
	 * search, recentapp power, volumeup, volumedown, mute, camera, etc.
	 * 
	 * @param Key
	 *            can be Home, back, delete, menu, down, up, left, right,
	 *            center, search, recentapp power, volumeup, volumedown, mute,
	 *            camera, enter,
	 */
	public void pressKey(String Key) {
		if (Key.equalsIgnoreCase("home")) {
			getUiDevice().pressHome();

		} else if (Key.equalsIgnoreCase("back")) {
			getUiDevice().pressBack();
		} else if (Key.equalsIgnoreCase("delete")) {
			getUiDevice().pressDelete();
		} else if (Key.equalsIgnoreCase("enter")) {
			getUiDevice().pressEnter();
		} else if (Key.equalsIgnoreCase("menu")) {
			getUiDevice().pressMenu();
		} else if (Key.equalsIgnoreCase("down")) {
			getUiDevice().pressDPadDown();
		} else if (Key.equalsIgnoreCase("up")) {
			getUiDevice().pressDPadUp();
		} else if (Key.equalsIgnoreCase("left")) {
			getUiDevice().pressDPadLeft();
		} else if (Key.equalsIgnoreCase("right")) {
			getUiDevice().pressDPadRight();
		} else if (Key.equalsIgnoreCase("center")) {
			getUiDevice().pressDPadCenter();
		} else if (Key.equalsIgnoreCase("search")) {
			getUiDevice().pressSearch();
		} else if (Key.equalsIgnoreCase("recentapp")) {
			try {
				getUiDevice().pressRecentApps();
			} catch (RemoteException e) {

			}
		} else if (Key.equalsIgnoreCase("power")) {
			pressKeyCode(KeyEvent.KEYCODE_POWER);
		} else if (Key.equalsIgnoreCase("volumeup")) {
			pressKeyCode(KeyEvent.KEYCODE_VOLUME_UP);
		} else if (Key.equalsIgnoreCase("volumedown")) {
			pressKeyCode(KeyEvent.KEYCODE_VOLUME_DOWN);
		} else if (Key.equalsIgnoreCase("mute")) {
			pressKeyCode(KeyEvent.KEYCODE_VOLUME_MUTE);
		} else if (Key.equalsIgnoreCase("camera")) {
			pressKeyCode(KeyEvent.KEYCODE_CAMERA);
		} else if (Key.equalsIgnoreCase("enter")) {
			pressKeyCode(KeyEvent.KEYCODE_ENTER);
		} else {
			return;
		}
	}

	/**
	 * click coordinate (x,y) on the screen
	 * 
	 * @param x
	 *            x of coordinate
	 * @param y
	 *            y of coordinate
	 */
	public void clickPoint(int x, int y) {
		getUiDevice().click(x, y);
	}

	/**
	 * Click relative point on the screen, such as
	 * {@code clickPointByRelative(0.5f, 0.5f)}, it's on the center of the
	 * screen. you can get x/width, y/height to get the value
	 * 
	 * @param x
	 *            x {@code float} type coordinate of width
	 * @param y
	 *            y {@code float} type coordinate of height
	 */
	public void clickPointByRelative(float x, float y) {
		clickPoint(toScreenX(x), toScreenY(y));
	}

	/**
	 * Click coordinate of the point
	 * 
	 * @param pt
	 *            {@code Point} of target you click
	 */
	public void clickPoint(Point pt) {
		getUiDevice().click(pt.x, pt.y);
	}

	/**
	 * Open launcher and find application by app name
	 * 
	 * @param text
	 *            app name appears in launcher
	 * @return {@code true} if find the app name
	 * @throws UiObjectNotFoundException
	 * 
	 */
	@Deprecated
	public boolean openLauncherAndFind(String text) throws UiObjectNotFoundException {
		pressKey("Home");
		UiObject allAppsButton = new UiObject(new UiSelector().description("Applications"));
		allAppsButton.clickAndWaitForNewWindow();
		UiScrollable apps = new UiScrollable(new UiSelector().scrollable(true));

		apps.setAsHorizontalList();// apps.setAsVerticalList();
		apps.flingToBeginning(5);
		for (int i = 0; i < 10; i++) {
			apps.flingForward();
			UiObject target = new UiObject(new UiSelector().className("android.widget.TextView").text(text));
			if (target.exists()) {
				target.clickAndWaitForNewWindow();
				return true;
			}

		}
		return false;
	}

	/**
	 * Launch Application by package name and class name
	 * 
	 * @param pkg
	 *            package name, like "com.android.calculator2"
	 * @param cls
	 *            cls name of the launchable Activity of the package, like
	 *            ".Calculator",if cls starts with '.' launchApp method will
	 *            connect pkg with cls.
	 */
	public void launchApp(String pkg, String cls) {
		String prog = "am start -n " + pkg + "/" + cls;
		try {
			Process process = Runtime.getRuntime().exec(prog);
			process.waitFor();
			waitForWindowUpdate(6000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch application details in Settings
	 * 
	 * @param pkg
	 *            package name
	 */
	public void launchAppDetails(String pkg) {
		// am start -a android.settings.APPLICATION_DETAILS_SETTINGS -d
		// package:com.unicom.gudong.client
		String action = "android.settings.APPLICATION_DETAILS_SETTINGS";

		String prog = "am start -a " + action + " -d package:" + pkg;
		try {
			Process process = Runtime.getRuntime().exec(prog);
			process.waitFor();
			waitForWindowUpdate(6000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Long click "text"
	 * 
	 * @param text
	 *            the specific text will be long clicked
	 * 
	 */
	public void longClickText(String text) throws UiObjectNotFoundException {
		longTap(getCoordinateByText(text).x, getCoordinateByText(text).y, 5000);
		waitForWindowUpdate(TIMEOUT);
	}

	/**
	 * Long click "text" by swipe
	 * 
	 * @param text
	 *            the specific text will be long clicked
	 * 
	 */
	public void longClickTextBySwipe(String text) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().text(text));
		if (obj.waitForExists(5000)) {
		}
		swipe(obj.getBounds().centerX(), obj.getBounds().centerY(), obj.getBounds().centerX(), obj.getBounds()
				.centerY(), 50);
		waitForWindowUpdate(2000);
	}

	/**
	 * Long click "text" by swipe
	 * 
	 * @param text
	 * @param timeout
	 *            of long click, (ms)
	 * @throws UiObjectNotFoundException
	 */
	public void longClickTextBySwipe(String text, int timeout) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().text(text));
		swipe(obj.getBounds().centerX(), obj.getBounds().centerY(), obj.getBounds().centerX(), obj.getBounds()
				.centerY(), timeout / 25);
		waitForWindowUpdate(2000);
	}

	/**
	 * Long click "text" by swipe
	 * 
	 * @param text
	 *            the specific text will be long clicked
	 * 
	 */
	public void longClickResourceIdBySwipe(String id, int timeout) throws UiObjectNotFoundException {
		UiObject obj = getObjectOnResourceId(id);
		swipe(obj.getBounds().centerX(), obj.getBounds().centerY(), obj.getBounds().centerX(), obj.getBounds()
				.centerY(), timeout / 25);
		waitForWindowUpdate(2000);
	}
	
	/**
	 * Long click "text" by swipe
	 * 
	 * @param text
	 *            the specific text will be long clicked
	 * 
	 */
	public void longClickResourceIdBySwipe(String id) throws UiObjectNotFoundException {
		UiObject obj = getObjectOnResourceId(id);
		swipe(obj.getBounds().centerX(), obj.getBounds().centerY(), obj.getBounds().centerX(), obj.getBounds()
				.centerY(), 50);
		waitForWindowUpdate(2000);
	}

	/**
	 * Long click content-desc "text"
	 * 
	 * @param text
	 *            the specific content-desc will be long clicked
	 * 
	 */
	public void longClickContentDesc(String text) throws UiObjectNotFoundException {
		longTap(getCoordinateByDes(text).x, getCoordinateByDes(text).y, 5000);
	}

	/**
	 * Long click text contains "text"
	 * 
	 * @param text
	 *            the specific text will be long clicked
	 */
	public void longClickTextContains(String text) throws UiObjectNotFoundException {
		UiObject uiobj = new UiObject(new UiSelector().textContains(text));
		longTap(uiobj.getBounds().centerX(), uiobj.getBounds().centerY(), 3000);
	}

	/**
	 * Press Navigation up (in action bar) standard android behavior
	 * 
	 * @throws {{@link UiObjectNotFoundException} if not found
	 */
	public void navigationUp() throws UiObjectNotFoundException {
		UiObject uiobj = new UiObject(new UiSelector().description("Navigate up"));
		uiobj.clickAndWaitForNewWindow();
	}

	/**
	 * Click More options menu, standard android behavior
	 * 
	 * @throws {{@link UiObjectNotFoundException} if not found
	 */
	public void moreMenu() throws UiObjectNotFoundException {
		UiObject uiobj = new UiObject(new UiSelector().description("More options"));
		if (uiobj.exists()) {
			uiobj.clickAndWaitForNewWindow();
		} else {
			pressKey("Menu");
		}
	}

	/**
	 * press key code test
	 * 
	 * @param key
	 *            {@code int} is from {@link KeyEvent}
	 */
	public void pressKeyCode(int key) {
		getUiDevice().pressKeyCode(key);
	}

	/**
	 * Open status bar(notification bar)
	 */
	public void openStatusBar() {
		int w = getUiDevice().getDisplayWidth();
		int h = getUiDevice().getDisplayHeight();
		getUiDevice().swipe(w / 2, 30, w / 2, h, 30);
	}

	/**
	 * Open Application from Recent Apps window by press recent virtual key
	 * 
	 * @param appName
	 *            the specific application name will be openned
	 * @throws {@link UiObjectNotFoundException} if not found
	 */
	public void openAppFromRecentApps(String appName) throws UiObjectNotFoundException {
		pressKey("recentapp");
		Point pt = getCoordinateByText(appName);
		if (getUiDevice().getDisplayRotation() == 0) {
			clickPoint(pt.x + toScreenX(0.2f), pt.y + toScreenY(0.15f));
		} else {
			clickPoint(pt.x, pt.y - toScreenY(0.2f));
		}
	}

	/**
	 * click icon by row and column on launcher, you can use it when can not get
	 * anything on screen*
	 * 
	 * @param col
	 *            X;
	 * @param row
	 *            Y;
	 * @param page
	 *            scroll page number;
	 */
	public void clickLauncher(int col, int row, int page) {
		// for on screen
		col = col - 1;
		row = row - 1;
		sleep(1500);
		clickPointByRelative(0.493f, 0.95f);// press Launcher

		for (int i = 0; i < page - 1; i++) {
			sleep(1000);
			scroll("left");
		}
		sleep(1200);
		int startX = toScreenX(0.0277f);
		int startY = toScreenY(0.0911f);
		int W = getUiDevice().getDisplayWidth() - toScreenX(0.0277f) * 2;
		int H = getUiDevice().getDisplayHeight() - toScreenY(0.0911f) - toScreenY(0.131f);
		Float x = startX + col * W / 4 + (float) W / 8;
		Float y = startY + row * H / 5 + (float) W / 10;
		getUiDevice().click(x.intValue(), y.intValue());
	}

	/**
	 * Drag screen to what orientation, orientation can be "left", "right",
	 * "up", "down" such as "left" is from right of screen to left of screen
	 * 
	 * @param orientation
	 *            orientation can be "left", "right", "up", "down"
	 * 
	 * 
	 */
	public void scroll(String orientation) {
		try {
			int W = getUiDevice().getDisplayWidth();
			int H = getUiDevice().getDisplayHeight();
			if (orientation.equalsIgnoreCase("left")) {
				drag(3 * W / 4, H / 2, W / 4, H / 2, 20);
			} else if (orientation.equalsIgnoreCase("right")) {
				drag(W / 4, H / 2, 3 * W / 4, H / 2, 20);
			} else if (orientation.equalsIgnoreCase("up")) {
				drag(W / 2, 3 * H / 4, W / 2, H / 4, 20);
			} else if (orientation.equalsIgnoreCase("down")) {
				drag(W / 2, H / 4, W / 2, 3 * H / 4, 20);
			} else {
				drag(3 * W / 4, H / 2, W / 4, H / 2, 20);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("scroll has an exception");
		}
	}

	/**
	 * set current Orientation Landscape, it only works when auto screen
	 * disabled.
	 * 
	 */
	public void setOrientationLandscape() throws RemoteException {

		if (getUiDevice().getDisplayRotation() == 0) {
			getUiDevice().setOrientationLeft();
			sleep(1000);
		}
	}

	/**
	 * set current Orientation Portrait, it works when disable auto rotate
	 * 
	 */
	public void setOrientationPortrait() throws RemoteException {
		int oriet = getUiDevice().getDisplayRotation();
		if (oriet == 1 || oriet == 3) {
			getUiDevice().setOrientationNatural();
		}
	}

	/**
	 * Enters text in an EditText matching the specified instance.
	 * 
	 * @param text
	 *            the text to enter in the like {@code android.widget.EditText}
	 *            field
	 * 
	 * @param cls
	 *            Text editor on the screen, please use uiautomatorviewer to
	 *            find it often as "android.widget.EditText" or
	 *            {@code "android.widget.MultiAutoCompleteTextView"}
	 *            {@code "android.widget.ExtractEditText",}
	 *            {@code "android.widget.AutoCompleteTextView"}
	 * @param instance
	 *            number of instance, begin from 0
	 * 
	 * 
	 *            enterTextInEditor("ABCDEFGHIJK, abcde;zgdidsgds76204781?",
	 *            "android.widget.EditText", 1); //enterTextInEditor("123567",
	 *            "android.widget.MultiAutoCompleteTextView", 1); user
	 *            pressKey("back") after the lines, because IME will mask half
	 *            of the screen.
	 * @throws UiObjectNotFoundException
	 * 
	 */
	public void enterTextInEditor(String text, String cls, int instance) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().className(cls).instance(instance));
		obj.setText(text);
	}

	// /**
	// * Enters text a editor matching the specified instance, precondition:
	// focus
	// * on in editor fist
	// *
	// * @param text
	// * the specific text
	// */
	// public void enterText(String text) {
	// Instrumentation inst = new Instrumentation();
	// inst.sendStringSync(text);
	// }

	/**
	 * Fling to Beginning on scrollable object, the object should be scrollable
	 * 
	 * @param cls
	 *            Class name of scrollable object, most in
	 *            "android.widget.ListView"
	 * @throws {@link UiObjectNotFoundException} if when scrollable object is
	 *         not found
	 */
	public void flingToBegining(String cls) throws UiObjectNotFoundException {
		UiScrollable scroll = new UiScrollable(new UiSelector().className(cls));
		if (scroll.isScrollable()) {
			scroll.flingToBeginning(10);
		}
	}

	/**
	 * scroll to the beginning at a list
	 * 
	 * @param direction
	 *            scroll direction as "Vertical" or "Horizontal", default is
	 *            Horizontal
	 * @param maxScrolls
	 *            max try times
	 */
	public void scrollToBegining(String direction, int maxScrolls) {
		if (direction.contains("Vertical")) {
			for (int i = 0; i < maxScrolls; i++) {
				dragDirection("down", 20);
			}
		} else {
			for (int i = 0; i < maxScrolls; i++) {
				dragDirection("right", 20);
			}
		}
	}

	/**
	 * Fling to end, only works on scrollable views
	 * 
	 * @param cls
	 *            class name of scrollable, most in "android.widget.ListView",
	 *            max try times is 10
	 * @throws {@link UiObjectNotFoundException} if the scrollalbe object not
	 *         found
	 */
	public void flingToEnd(String cls) throws UiObjectNotFoundException {
		UiScrollable scroll = new UiScrollable(new UiSelector().className(cls));
		scroll.flingToEnd(10);
	}

	/**
	 * Scroll up in scrollable View, most in {@code "android.widget.ListView"}
	 * 
	 * @param cls
	 *            scrollable class Name, most in
	 *            {@code "android.widget.ListView" }
	 * @param steps
	 * @throws {@link UiObjectNotFoundException} if the scrollalbe object not
	 *         found
	 */
	public void scrollUpOnView(String cls, int steps) throws UiObjectNotFoundException {
		UiScrollable scroll = new UiScrollable(new UiSelector().className(cls));
		scroll.swipeUp(steps);

	}

	/**
	 * Scroll down in scrollable View, most in {@code "android.widget.ListView"}
	 * 
	 * @param cls
	 *            scrollable class Name, most in
	 *            {@code "android.widget.ListView" }
	 * @param steps
	 * @throws {@link UiObjectNotFoundException} if the scrollalbe object not
	 *         found
	 */
	public void scrollDownOnView(String cls, int steps) throws UiObjectNotFoundException {
		UiScrollable scroll = new UiScrollable(new UiSelector().className(cls));
		scroll.swipeDown(steps);

	}

	/**
	 * get Child UI Ojbect in ScrollView test failed
	 * {@code getChildObjInScrollView("android.widget.ListView", "About phone", 5).clickAndWaitForNewWindow();}
	 * 
	 * @param scrollCls
	 *            scrollable class name, the class should have scrollable = true
	 * @param text
	 *            the text you want to found
	 * 
	 * @return UiObject UI Object you can operator
	 * @throws UiObjectNotFoundException
	 */
	public UiObject getChildObjInScrollView(String scrollCls, String text, int maxScrolls)
			throws UiObjectNotFoundException {

		UiScrollable scroll = new UiScrollable(new UiSelector().className(scrollCls));
		scroll.setMaxSearchSwipes(maxScrolls);
		scroll.flingToBeginning(maxScrolls);
		for (int i = 0; i < maxScrolls; i++) {
			UiObject child = new UiObject(new UiSelector().text(text));
			if (child.exists()) {
				break;
			}
			scroll.scrollForward();
		}
		return (new UiObject(new UiSelector().text(text)));
	}

	/**
	 * Scroll and find a text on scrollable object(get scrollable=true by
	 * uiautomatorviewer(monitor), the method use nature drag
	 * 
	 * @param text
	 *            text you want to find
	 * @param direction
	 *            the direction is scroll direction, value should be
	 *            "Horizontal" or "Vertical"
	 * @param maxScrolls
	 *            the value max tries.
	 * @return UiObject return text UiObject you can operate
	 * @throws {@link UiObjectNotFoundException} The text not found
	 * 
	 *         {@code getChildObjInScrollView} similar method
	 */
	public UiObject scrollToFindText(String text, String direction, int maxScrolls) throws UiObjectNotFoundException {
		if (direction.equalsIgnoreCase("Horizontal")) {
			scrollToBegining("Horizontal", 3);

			for (int i = 0; i < maxScrolls; i++) {
				UiObject child = new UiObject(new UiSelector().text(text));
				if (child.exists()) {
					return child;
				}
				dragDirection("left", 35);
			}
		} else if (direction.equalsIgnoreCase("Vertical")) {
			scrollToBegining("Vertical", 3);

			for (int i = 0; i < maxScrolls; i++) {
				UiObject child = new UiObject(new UiSelector().text(text));
				if (child.exists()) {
					return child;
				}
				dragDirection("down", 35);
			}

			for (int i = 0; i < maxScrolls; i++) {
				UiObject child = new UiObject(new UiSelector().text(text));
				if (child.exists()) {
					return child;
				}
				dragDirection("up", 35);
			}
		}

		return (new UiObject(new UiSelector().text(text)));
	}

	/**
	 * Find view by class name and instance, return UiObject can be operated or
	 * verfication
	 * 
	 * @param clsName
	 *            class Name w
	 * @param instance
	 * @return UiObject you can operate the UiObject like click or other
	 *         operations
	 */
	public UiObject findView(String clsName, int instance) {
		UiObject obj = new UiObject(new UiSelector().className(clsName).instance(instance));
		return obj;
	}

	/**
	 * find Text and return UiObject, then you can operate this UiObject
	 * 
	 * @param text
	 *            find the text object
	 * @return UiOject You can operate with this object example:
	 *         {@code assertTrue("Click About Phone failed", findText("Apps").exists())}
	 *         {@code findText("About phone").clickAndWaitForNewWindow());}
	 */
	public UiObject findText(String text) {
		UiObject obj = new UiObject(new UiSelector().text(text));
		return obj;
	}

	/**
	 * find object contain Text and return UiObject, then you can operate this
	 * UiObject
	 * 
	 * @param text
	 * @return UiOject You can operate with this object example:
	 *         assertTrue("Click About Phone failed", findText("Apps").exists())
	 *         findText("About phone").clickAndWaitForNewWindow());
	 */
	public UiObject findTextContains(String text) {
		UiObject obj = new UiObject(new UiSelector().textContains(text));
		return obj;
	}

	/**
	 * Take screenshot of the device, the picture will be generated in /sdcard/
	 * 
	 * @param storePath
	 *            The storage path in /sdcard/uiAutoTest/, if it's empty, it
	 *            will be in /sdcard/uiAutoTest/uitestName_2013xxxxxxx.png
	 * @return {@code true} if capture successfully.
	 */

	public boolean takeScreenshot(String storePath) {
		Log.v(AUTOTEST_TAG, "takeScreenshot.... ");
		String baseDir = "/sdcard";
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		// String currentDateandTime = sdf.format(new Date());
		//
		// String fileName = getName() + "_" + currentDateandTime.toString()
		// + ".png";
		String fileName = getName() + ".png";

		String path = "";
		if (storePath.isEmpty()) {
			path = baseDir + File.separator + Constant.SCREENSHOTS + File.separator + fileName;
		} else {
			path = baseDir + File.separator + Constant.SCREENSHOTS + File.separator + storePath + File.separator
					+ fileName;
		}
		Process process = null;
		File f = new File(path);
		try {
			if (!f.getParentFile().exists()) {
				process = Runtime.getRuntime().exec("mkdir -p " + f.getParentFile().getAbsolutePath());
				process.waitFor();
			}
			return getUiDevice().takeScreenshot(f, 0.5f, 30);
		} catch (IOException e) {
			Log.v(AUTOTEST_TAG, "filename: " + fileName);
			e.printStackTrace();
		} catch (InterruptedException e) {
			Log.v(AUTOTEST_TAG, "filename: " + fileName);
			e.printStackTrace();
		}
		process.destroy();
		return false;
	}

	/**
	 * Assert string is presenting on screen.
	 * 
	 * @param string
	 *            The text to verify on screen, if not found, test case will
	 *            fail
	 * 
	 */
	public void assertTextPresent(String string) {
		assertTrue("Text \"" + string + "\" is not existing", findText(string).exists());
	}

	/**
	 * assert sub-string is presenting on screen.
	 * 
	 * @param string
	 *            The sub-string on screen to verify, if not found, test case
	 *            will fail
	 * 
	 */
	public void assertTextContainsPresent(String string) {
		assertTrue("Text \"" + string + "\" is not existing", findTextContains(string).exists());
	}

	/**
	 * Assert view class name is present on screen
	 * 
	 * @param cls
	 *            View class name on screen to verify, if not matched, failure
	 *            will happen
	 */
	public void assertViewMatches(String cls) {
		UiObject obj = new UiObject(new UiSelector().className(cls));
		assertTrue("Class View: \"" + cls + "\" is not existing", obj.exists());
	}

	/**
	 * Assert by current package name
	 * 
	 * @param pkgName
	 *            package is appear or not, often verify the app is opened or
	 *            not.
	 */
	public void assertPackageAppear(String pkgName) {
		assertEquals("Package name: \"" + pkgName + "\" is not current package", pkgName, getUiDevice()
				.getCurrentPackageName());

	}

	/**
	 * Get Coordinate by Text, the Coordinate is center of the text bounds
	 * Example: {@code clickPoint(getCoordinateByText("About phone"));}
	 * 
	 * @param text
	 *            The text you want to find
	 * @return Point Center of the text
	 * @throws {@link UiObjectNotFoundException} if the text not found.
	 */
	public Point getCoordinateByText(String text) throws UiObjectNotFoundException {
		Rect rect = findText(text).getBounds();
		Point pt = new Point(rect.centerX(), rect.centerY());
		return pt;
	}

	public Rect getBoundsByResourceID(String id) throws UiObjectNotFoundException {

		UiObject obj = new UiObject(new UiSelector().resourceId(id));
		if (obj.exists()) {
			return obj.getBounds();
		}
		return null;
	}

	/**
	 * Get Coordinate by content-desc, the Coordinate is center of the
	 * content-desc bounds Example:
	 * {@code clickPoint(getCoordinateByText("About phone"));}
	 * 
	 * @param text
	 *            The content-desc you want to find
	 * @return Point Center of the content-desc text
	 * @throws {@link UiObjectNotFoundException} if the text not found.
	 */
	public Point getCoordinateByDes(String text) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().descriptionContains(text));
		Rect rect = obj.getBounds();
		Point pt = new Point(rect.centerX(), rect.centerY());
		return pt;
	}

	/**
	 * get coordinate of child item in complex view
	 * 
	 * @param parentCls
	 *            parent class name
	 * @param parentInstance
	 * 
	 * @param targetCls
	 *            target class name you want to get
	 * @param targetInstance
	 *            target class instance you want to get
	 * @return Point of the target class coordinate
	 * @throws UiObjectNotFoundException
	 */
	public Point getCoordinateFromChildView(String parentCls, int parentInstance, String targetCls, int targetInstance)
			throws UiObjectNotFoundException {

		UiCollection uiCol = new UiCollection(new UiSelector().className(parentCls).instance(parentInstance));
		UiObject obj = uiCol.getChildByInstance(new UiSelector().className(targetCls), targetInstance);

		Point pt = new Point(obj.getBounds().centerX(), obj.getBounds().centerY());
		return pt;
	}

	/**
	 * Get number of className in parent class name(parentCls and it's instance
	 * on the screen).
	 * 
	 * @param parentCls
	 *            Parent class name
	 * @param instanceParent
	 *            instance of the parent class on the screen
	 * @param className
	 *            The class name you want to get.
	 * @return {@code int} the number of the items on the parent class
	 */
	public int getItemsNumber(String parentCls, int instanceParent, String className) {
		UiCollection uiCol = new UiCollection(new UiSelector().className(parentCls).instance(instanceParent));
		return uiCol.getChildCount(new UiSelector().className(className));
	}

	/**
	 * check if the view contains the text by regular expression
	 * 
	 * @param regexp
	 *            text regular expression
	 * @return {@code true} if the screen has the text contains regexp
	 */
	public boolean isViewWithTextContainsRegExp(String regexp) {
		UiObject obj = new UiObject(new UiSelector().textMatches(regexp));
		return obj.exists();
	}

	/**
	 * check if the view with class
	 * 
	 * @param name
	 *            class of view
	 * @return {@code true} if the screen has the class
	 */
	public boolean isViewWithClass(String name) {
		UiObject obj = new UiObject(new UiSelector().className(name));
		return obj.exists();
	}

	/**
	 * check if the view with resource id
	 * 
	 * @param id
	 *            resource id
	 * @return {@code true} if the screen has the class
	 */
	public boolean isViewWithResourceID(String id) {
		UiObject obj = new UiObject(new UiSelector().resourceId(id));
		return obj.exists();
	}

	/**
	 * Reads the UI element's package text property of specific class name
	 * 
	 * @param cls
	 *            class name of the view
	 * @param instance
	 *            Instance is from 0, the first is 0, if you find TextView is
	 *            3rd, you should set 2
	 * @return {@code String} the text on the class Name
	 * @throws {@link UiObjectNotFoundException} if the class not found
	 */
	public String getTextOnView(String cls, int instance) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().className(cls).instance(instance));
		return obj.getText();
	}

	/**
	 * check if the view contains the text, if true the text presents on the
	 * screen
	 * 
	 * @param text
	 * @return {@code true} if text contains on screen, else not
	 */
	public boolean isViewWithTextContains(String text) {
		UiObject obj = new UiObject(new UiSelector().textContains(text));
		return obj.exists();
	}

	/**
	 * check if the view with text, if true the text presents on the screen
	 * 
	 * @param text
	 * @return {@code true} if text on screen, else not
	 */
	public boolean isViewWithText(String text) {
		UiObject obj = new UiObject(new UiSelector().text(text));
		return obj.exists();
	}

	/**
	 * if UI leave your application, the method will press Back key try to get
	 * back. Usage Example:
	 * {@code dismissUnWantedWindow("com.android.calculator2");} when finished
	 * case, should
	 * {@code UiDevice.getInstance().removeWatcher("dismissWatcher")}
	 * 
	 * @param targetPkg
	 *            your target package name
	 */
	public void dismissUnWantedWindow(final String targetPkg) {
		UiWatcher dismissWatcher = new UiWatcher() {
			@Override
			public boolean checkForCondition() {
				for (int i = 0; i < 2; i++) {
					if (!getUiDevice().getCurrentPackageName().equalsIgnoreCase(targetPkg)) {
						pressKey("Back");
					}
				}
				return getUiDevice().getCurrentPackageName().equalsIgnoreCase(targetPkg);
			}
		};
		UiDevice.getInstance().registerWatcher("dismissWatcher", dismissWatcher);
		UiDevice.getInstance().runWatchers();
	}

	/**
	 * Fail the test
	 * 
	 * @param msg
	 *            the failure messages
	 */
	public void failTest(String msg) {
		Assert.fail(msg);
	}

	/**
	 * Wait for text exists with timeout(in ms)
	 * 
	 * @param text
	 *            text you want to wait
	 * @param timeout
	 *            timeout in ms
	 * @return {@code true} if the text appears in timeout time
	 */
	public boolean waitForTextExists(String text, int timeout) {
		for (int i = 0; i < timeout / 100; i++) {
			if (isViewWithText(text)) {
				break;
			} else {
				sleep(100);
			}
		}
		return isViewWithText(text);
	}

	/**
	 * Wait for text exists with timeout(in ms)
	 * 
	 * @param text
	 *            text you want to wait
	 * @param timeout
	 *            timeout in ms
	 * @return {@code true} if the text appears in timeout time
	 */
	public boolean waitForTextContainsExists(String text, int timeout) {
		for (int i = 0; i < timeout / 100; i++) {
			if (isViewWithTextContains(text)) {
				break;
			} else {
				sleep(100);
			}
		}
		return isViewWithTextContains(text);
	}

	/**
	 * Wait for class exists with timeout(in ms)
	 * 
	 * @param cls
	 *            class you want to wait
	 * @param timeout
	 *            timeout in ms
	 * @return {@code true} if the text appears in timeout time
	 */
	public boolean waitForClassExists(String cls, int timeout) {
		for (int i = 0; i < timeout / 100; i++) {
			if (isViewWithClass(cls)) {
				break;
			} else {
				sleep(100);
			}
		}
		return isViewWithClass(cls);
	}

	/**
	 * Wait for text gone from the screen with timeout
	 * 
	 * @param text
	 *            the text wait to go out of screen
	 * @param timeout
	 * @return {@code false} if the text still on the screen in timeout time.
	 */
	public boolean waitForTextGone(String text, int timeout) {
		UiObject obj = new UiObject(new UiSelector().textContains(text));
		return obj.waitUntilGone(timeout);
	}

	/**
	 * Long Tap a coordinate, getUiDevice.longTap() might be added in android
	 * next release
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param time
	 *            time of the long tap
	 */
	public void longTap(int x, int y, int time) {
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent event = MotionEvent.obtain(downTime, eventTime, 0, x, y, 0);
		Instrumentation inst = new Instrumentation();
		try {
			inst.sendPointerSync(event);
		} catch (SecurityException e) {
			Assert.assertTrue("Click can not be completed! Something is in the way e.g. the keyboard.", false);
		}
		eventTime = SystemClock.uptimeMillis();
		event = MotionEvent.obtain(downTime, eventTime, 2, x + 1.0F, y + 1.0F, 0);
		inst.sendPointerSync(event);
		if (time > 0) {
			sleep(time);
		} else {
			sleep((int) ((ViewConfiguration.getLongPressTimeout() * 2.5F)));
		}
		eventTime = SystemClock.uptimeMillis();
		event = MotionEvent.obtain(downTime, eventTime, 1, x, y, 0);
		inst.sendPointerSync(event);
		sleep(500);
	}

	/**
	 * Check the CheckBox is checked or not,
	 * 
	 * @param instance
	 *            instance number on the screen, start from 0
	 * @return {@code true} means checked, {@code false} means not checked.
	 * @throws {@link UiObjectNotFoundException} if the text box is not found
	 */
	public boolean isCheckedOfCheckBox(int instance) throws UiObjectNotFoundException {
		UiCollection uiC = new UiCollection(new UiSelector());
		UiObject obj = uiC.getChildByInstance(new UiSelector().className("android.widget.CheckBox"), instance);
		return obj.isChecked();
	}

	/**
	 * Check the Switch widget(android.widget.Switch) is checked or not,
	 * 
	 * @param instance
	 *            instance number on the screen, start from 0
	 * @return {@code true} means checked, {@code false} means not checked.
	 * @throws {@link UiObjectNotFoundException} if the widget is not found
	 */
	public boolean isCheckedOfSwitch(int instance) throws UiObjectNotFoundException {
		UiCollection uiC = new UiCollection(new UiSelector());
		UiObject obj = uiC.getChildByInstance(new UiSelector().className("android.widget.Switch"), instance);
		return obj.isChecked();

	}

	/**
	 * Check the CheckedTextView is checked or not,
	 * 
	 * @param instance
	 *            instance number on the screen, start from 0
	 * @return {@code true} means checked, {@code false} means not checked.
	 * @throws {@link UiObjectNotFoundException} if the widget is not found
	 */

	public boolean isCheckedOfCheckBoxTextView(int instance) throws UiObjectNotFoundException {
		UiCollection uiC = new UiCollection(new UiSelector());
		UiObject obj = uiC.getChildByInstance(new UiSelector().className("android.widget.CheckedTextView"), instance);
		return obj.isChecked();
	}

	/**
	 * Check the android.widget.CheckedTextView is check or not by its text
	 * 
	 * @param checkedTextViewString
	 *            android.widget.CheckedTextView string
	 * @return {@code true} if is checked. otherwise if unchecked.
	 * @throws UiObjectNotFoundException
	 */
	public boolean isCheckedTextView(String checkedTextViewString) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().className("android.widget.CheckedTextView").text(
				checkedTextViewString));
		if (obj.exists())
			return obj.isChecked();
		return false;
	}

	/**
	 * inject long key event
	 * 
	 * @param keycode
	 *            {@code KeyEvent}
	 * @param sleepTime
	 *            Sleep time for press the Keycode
	 * @throws Exception
	 */
	public void injectLongKeyEvent(int keycode, int sleepTime) throws Exception {
		KeyEvent kd = new KeyEvent(KeyEvent.ACTION_DOWN, keycode);
		Instrumentation inst = new Instrumentation();
		inst.sendKeySync(kd);
		if (sleepTime < ViewConfiguration.getLongPressTimeout()) {
			sleep(ViewConfiguration.getLongPressTimeout() * 2 + 100);
		} else {
			sleep(sleepTime);
		}
		KeyEvent kup = new KeyEvent(KeyEvent.ACTION_UP, keycode);
		inst.sendKeySync(kup);
	}

	/**
	 * clear text in text editor
	 * 
	 * @param instance
	 *            the text edit in the screen..
	 * @param num
	 *            Max Number of text you want to clear
	 * @throws {@link UiObjectNotFoundException} if the
	 *         "android.widget.EditText" not found
	 */
	public void clearEditText(int instance, int num) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().className("android.widget.EditText").instance(instance));
		if (obj.getText().isEmpty())
			return;

		obj.clickBottomRight();
		try {
			Instrumentation inst = new Instrumentation();
			for (int i = 0; i < num; i++) {
				inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DEL); // pressKeyCode(KeyEvent.KEYCODE_DEL);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * clear text in text editor
	 * 
	 * @param num
	 *            Max Number of text you want to clear
	 * @throws {@link UiObjectNotFoundException} if the
	 *         "android.widget.EditText" not found
	 */
	public void clearEditText(int num) throws UiObjectNotFoundException {
		for (int i = 0; i < num; i++) {
			ShellUtils.execCommand("input keyevent 67", true);
		}
	}

	/**
	 * Waits for a window content update event to occur. with timeout used from
	 * waiting wait for UI change often use you click some coordinate and need
	 * wait the screen updates
	 * 
	 * @param timeout
	 *            for the wait time
	 * @return {@code true} wait successfully
	 */
	public boolean waitForWindowUpdate(long timeout) {
		if (timeout <= 0)
			return getUiDevice().waitForWindowUpdate(null, TIMEOUT);
		else
			return getUiDevice().waitForWindowUpdate(null, timeout);
	}

	public void turnOnWlanByCMD() {
		System.out.println("svc wifi enable");
		String prog = "svc wifi enable";
		ShellUtils.execCommand(prog, true);
	}
	
	public void turnOnDataByCMD() {
		System.out.println("svc data enable");
		String prog = "svc data enable";
		ShellUtils.execCommand(prog, true);
	}
	
	public void turnOffDataByCMD() {
		String prog = "";
		prog = "svc data disable";
		ShellUtils.execCommand(prog, true);
	}
	
	public void turnOffWifiByCMD() {
		String prog = "";
		prog = "svc wifi disable";
		ShellUtils.execCommand(prog, true);
	}

	public void turnOffNetWorkByCMD() {
		for (int i = 0; i < 3; i++) {
			System.out.println("svc network disable");
			
			turnOffDataByCMD();

			turnOffWifiByCMD();
			
			if (!ping()) {
				break;
			}
		}

		if (ping()) {
			System.out.println("The network well!");
		}
	}

	public void makeSureNetConnected() {

		for (int i = 0; i < 5; i++) {
			if (!ping()) {
				turnOnWlanByCMD();
				sleep(10000);
			} else {
				return;
			}
		}

		if (!ping()) {
			System.out.println("The network not well!");
		}
	}

	public boolean ping() {
		String result = null;
		try {
			String ip = "www.baidu.com";
			Process p = Runtime.getRuntime().exec("ping -c 5 -w 100 " + ip);// ping
																			// 5
																			// times

			InputStream input = p.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			StringBuffer stringBuffer = new StringBuffer();
			String content = "";
			while ((content = in.readLine()) != null) {
				stringBuffer.append(content);
			}
			// System.out.println("result content : " +
			// stringBuffer.toString());
			// PING status
			int status = p.waitFor();
			if (status == 0) {
				result = "successful~";
				return true;
			} else {
				result = "failed--Cannot reach the IP address";
			}
		} catch (IOException e) {
			result = "failed--IOException";
		} catch (InterruptedException e) {
			result = "failed--InterruptedException";
		} finally {
			System.out.println("Ping result = " + result);
		}
		return false;
	}

	/**
	 * Overide of runTest of testcase, to test try times a gain when failure
	 * setUp and tearDown will don't run again after failure, so openApp should
	 * be in test This method also catch screenshot when failure. -e retrytimes
	 * 1 screenshot path is
	 * /sdcard/Constant.SCREENSHOTS/TestClassName/testxxx.png
	 */
	@Override
	protected void runTest() throws Throwable {
		String className = getClass().getName();

		int retryTimes = 0;
		boolean firstTime = true;
		if (getParams().getString("retrytimes") != null) {
			retryTimes = Integer.parseInt(getParams().getString("retrytimes"));
		}
		if (getParams().getString("logcat") == "true") {

			String fileName = getName() + "_" + "logcat.log";

			String path = "/sdcard/" + Constant.SCREENSHOTS + File.separator + fileName;
			Process process = null;
			File f = new File(path);
			try {
				if (!f.getParentFile().exists()) {
					process = Runtime.getRuntime().exec("mkdir -p " + f.getParentFile().getAbsolutePath());
					process.waitFor();
					saveLogcat(path);
				}

			} catch (IOException e) {
				Log.e(AUTOTEST_TAG, "IO exception filename: " + fileName);
				e.printStackTrace();
			} catch (InterruptedException e) {
				Log.v(AUTOTEST_TAG, "InterruptedException filename: " + fileName);
				e.printStackTrace();
			} catch (Exception e) {
				Log.e(AUTOTEST_TAG, "Unkown Exception, filename: " + fileName);
				e.printStackTrace();
			}
			process.destroy();

		}

		while (retryTimes >= 0) {
			try {
				if (!firstTime) {
					for (int i = 0; i < 5; i++)
						pressKey("back");
				}
				firstTime = false;
				super.runTest();
				break;
			} catch (Throwable e) {
				takeScreenshot(className);
				if (retryTimes >= 1) {
					retryTimes--;
					continue;
				} else {
					System.out.println("runTest() throws an exception");
					throw e;
				}
			}
		}

	}

	private StringBuilder getLog(StringBuilder stringBuilder) {
		Process p = null;
		BufferedReader reader = null;
		String line = null;

		try {
			p = Runtime.getRuntime().exec("logcat -d -v time | grep TAG_NET_PKG");
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			stringBuilder.setLength(0);
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();

		}
		p.destroy();
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder;
	}

	/**
	 * Waits for the specified log message to appear.
	 * 
	 * @param logMessage
	 *            the log message to wait for
	 * @param timeout
	 *            the time to wait for
	 * @return {@code true} if the log message appears. otherwise {@code false}
	 */
	public boolean waitForLogMessage(String logMessage, int timeout) {
		StringBuilder stringBuilder = new StringBuilder();
		long endTime = SystemClock.uptimeMillis() + timeout;
		while (SystemClock.uptimeMillis() < endTime) {
			if (getLog(stringBuilder).lastIndexOf(logMessage) != -1) {
				return true;
			}
			sleep(500);
		}
		return false;
	}

	/**
	 * assert content-desc string is presenting on screen.
	 * 
	 * @param text
	 *            The content-desc to verify
	 * 
	 */
	public void assertContentDescExist(String text) {
		UiObject obj = new UiObject(new UiSelector().description(text));
		assertTrue(text + " Not found.", obj.exists());
	}

	/**
	 * check if content-desc string is presenting on screen.
	 * 
	 * @param text
	 *            The screen to verify
	 * @throws InterruptedException
	 * 
	 */
	public boolean isContentDescExist(String text) {
		UiObject obj = new UiObject(new UiSelector().description(text));
		return obj.exists();
	}

	/**
	 * Compute the percentage of difference between current Rect images of the
	 * same size reference:
	 * http://rosettacode.org/wiki/Percentage_difference_between_images
	 * 
	 * @param file1
	 *            Absolution path like /data/local/tmp/ref/xxx1.png
	 * @param file2
	 *            Absolution path like /data/local/tmp/ref/xxx2.png
	 * @param x1
	 *            start x coordinate of screen
	 * @param y1
	 *            start y coordinate of screen
	 * @param x2
	 *            end x coordinate of screen
	 * @param y2
	 *            end y coordinate of screen
	 * @return Difference, normally we have different value < 3
	 */

	public double getScreenDiffWithReference(String file1, String file2, int x1, int y1, int x2, int y2) {
		Bitmap refBitmap = BitmapFactory.decodeFile(file1);// /data/local/tmp/ref/xxx.png
		Bitmap bm = BitmapFactory.decodeFile(file2);// /data/local/tmp/ref/xxx.png
		Bitmap expectBm = Bitmap.createBitmap(refBitmap, x1, y1, x2 - x1, y2 - y1);
		Bitmap runtimeBitmap = Bitmap.createBitmap(bm, x1, y1, x2 - x1, y2 - y1);

		int width1 = runtimeBitmap.getWidth();
		int height1 = runtimeBitmap.getHeight();

		long diff = 0;

		for (int i = 0; i < width1 - 1; i++)
			for (int j = 0; j < height1 - 1; j++) {
				int r1 = Color.red(runtimeBitmap.getPixel(i, j));
				int g1 = Color.green(runtimeBitmap.getPixel(i, j));
				int b1 = Color.blue(runtimeBitmap.getPixel(i, j));

				int r2 = Color.red(expectBm.getPixel(i, j));
				int g2 = Color.green(expectBm.getPixel(i, j));
				int b2 = Color.blue(expectBm.getPixel(i, j));

				diff += Math.abs(r1 - r2);
				diff += Math.abs(g1 - g2);
				diff += Math.abs(b1 - b2);
			}

		double n = width1 * height1 * 3;
		double p = diff / n / 255.0;

		return p * 100.0;
	}

	/**
	 * Compute the percentage of difference between current Rect images of the
	 * same size reference:
	 * http://rosettacode.org/wiki/Percentage_difference_between_images
	 * 
	 * @param file1
	 *            Absolution path like /data/local/tmp/ref/xxx1.png
	 * @param x1
	 *            start x coordinate of screen
	 * @param y1
	 *            start y coordinate of screen
	 * @param x2
	 *            end x coordinate of screen
	 * @param y2
	 *            end y coordinate of screen
	 * @return Difference, normally we have different value < 3
	 */

	public double getScreenDiffWithReference(String file1, int x1, int y1, int x2, int y2) {
		Bitmap refBitmap = BitmapFactory.decodeFile(file1 + ".png");// /data/local/tmp/ref/xxx.png

		String runtimeBitmapPath = file1 + "_runtime.png";
		File runtimeFile = new File(runtimeBitmapPath);
		getUiDevice().takeScreenshot(runtimeFile, 1f, 100);

		Bitmap bm = BitmapFactory.decodeFile(runtimeBitmapPath);
		Bitmap expectBm = Bitmap.createBitmap(refBitmap, x1, y1, x2 - x1, y2 - y1);
		Bitmap runtimeBitmap = Bitmap.createBitmap(bm, x1, y1, x2 - x1, y2 - y1);

		int width1 = runtimeBitmap.getWidth();
		int height1 = runtimeBitmap.getHeight();

		long diff = 0;

		for (int i = 0; i < width1 - 1; i++)
			for (int j = 0; j < height1 - 1; j++) {
				int r1 = Color.red(runtimeBitmap.getPixel(i, j));
				int g1 = Color.green(runtimeBitmap.getPixel(i, j));
				int b1 = Color.blue(runtimeBitmap.getPixel(i, j));

				int r2 = Color.red(expectBm.getPixel(i, j));
				int g2 = Color.green(expectBm.getPixel(i, j));
				int b2 = Color.blue(expectBm.getPixel(i, j));

				diff += Math.abs(r1 - r2);
				diff += Math.abs(g1 - g2);
				diff += Math.abs(b1 - b2);
			}

		double n = width1 * height1 * 3;
		double p = diff / n / 255.0;

		return p * 100.0;
	}

	public double getScreenDiffWithReference(String file1, int x1, int y1, int x2, int y2, int x3, int y3, int x4,
			int y4) {
		Bitmap refBitmap = BitmapFactory.decodeFile(file1 + ".png");// /data/local/tmp/ref/xxx.png

		String runtimeBitmapPath = file1 + "_runtime.png";
		File runtimeFile = new File(runtimeBitmapPath);
		getUiDevice().takeScreenshot(runtimeFile, 1f, 100);

		Bitmap bm = BitmapFactory.decodeFile(runtimeBitmapPath);
		Bitmap expectBm = Bitmap.createBitmap(refBitmap, x1, y1, x2 - x1, y2 - y1);
		Bitmap runtimeBitmap = Bitmap.createBitmap(bm, x1, y1, x2 - x1, y2 - y1);

		int width1 = runtimeBitmap.getWidth();
		int height1 = runtimeBitmap.getHeight();

		long diff = 0;

		for (int i = 0; i < width1 - 1; i++)
			for (int j = 0; j < height1 - 1; j++) {
				int r1 = Color.red(runtimeBitmap.getPixel(i, j));
				int g1 = Color.green(runtimeBitmap.getPixel(i, j));
				int b1 = Color.blue(runtimeBitmap.getPixel(i, j));

				int r2 = Color.red(expectBm.getPixel(i, j));
				int g2 = Color.green(expectBm.getPixel(i, j));
				int b2 = Color.blue(expectBm.getPixel(i, j));

				diff += Math.abs(r1 - r2);
				diff += Math.abs(g1 - g2);
				diff += Math.abs(b1 - b2);
			}

		double n = width1 * height1 * 3;
		double p = diff / n / 255.0;

		return p * 100.0;
	}

	/**
	 * Get pixels of a bitmap object
	 * 
	 * @param bm
	 *            Bitmap
	 * @return pixels
	 */
	public int[] getPixels(Bitmap bm) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		int[] pixels = new int[width * height];
		bm.getPixels(pixels, 0, width, 0, 0, width, height);
		return pixels;
	}

	/**
	 * Target: Image compare
	 * 
	 * @param string
	 *            sourceBitmapLocation
	 * @param int x1, y1 - the left-top point of your target area
	 * @param int x2, y2 - the right-bottom point of your target area
	 * @return boolean
	 */
	public boolean isImageSame(String sourceBitmapLocation, int x1, int y1, int x2, int y2) {
		Bitmap sourceBitmap = BitmapFactory.decodeFile(sourceBitmapLocation + ".png");
		Bitmap runtimeBitmap;

		String runtimeBitmapPath = sourceBitmapLocation + "_runtime.png";
		File runtimeFile = new File(runtimeBitmapPath);
		getUiDevice().takeScreenshot(runtimeFile, 1f, 100);
		runtimeBitmap = BitmapFactory.decodeFile(runtimeBitmapPath);

		boolean isSame = Bitmap.createBitmap(sourceBitmap, x1, y1, x2 - x1, y2 - y1).sameAs(
				Bitmap.createBitmap(runtimeBitmap, x1, y1, x2 - x1, y2 - y1));

		return isSame;
	}
	
	public boolean isImageSame(String sourceBitmapLocation) {
		Bitmap sourceBitmap = BitmapFactory.decodeFile(sourceBitmapLocation);
		Bitmap runtimeBitmap;

		String runtimeBitmapPath = sourceBitmapLocation + "_runtime.png";
		File runtimeFile = new File(runtimeBitmapPath);
		getUiDevice().takeScreenshot(runtimeFile, 1f, 100);
		runtimeBitmap = BitmapFactory.decodeFile(runtimeBitmapPath);
		
		int x = getUiDevice().getDisplayWidth();
		int y = getUiDevice().getDisplayHeight();

		boolean isSame = Bitmap.createBitmap(sourceBitmap, 0, 0, x, y).sameAs(
				Bitmap.createBitmap(runtimeBitmap, 0, 0, x, y));

		return isSame;
	}
	
	/**
	 * 
	 * @param timeout
	 * @return
	 */
	public boolean isImageSame(int timeout) {
		Bitmap sourceBitmap;
		Bitmap runtimeBitmap;

		String sourceBitmapPath = Constant.RES_FILE_PATH + "_runtime1.png";
		File sourceFile = new File(sourceBitmapPath);
		getUiDevice().takeScreenshot(sourceFile, 1f, 100);
		sourceBitmap = BitmapFactory.decodeFile(sourceBitmapPath);

		sleep(timeout);

		String runtimeBitmapPath = Constant.RES_FILE_PATH + "_runtime2.png";
		File runtimeFile = new File(runtimeBitmapPath);
		getUiDevice().takeScreenshot(runtimeFile, 1f, 100);
		runtimeBitmap = BitmapFactory.decodeFile(runtimeBitmapPath);

		int x = getUiDevice().getDisplayWidth();
		int y = getUiDevice().getDisplayHeight();

		boolean isSame = Bitmap.createBitmap(sourceBitmap, 0, 0, x, y).sameAs(
				Bitmap.createBitmap(runtimeBitmap, 0, 0, x, y));

		return isSame;
	}

	/**
	 * get property from config.properties
	 */
	public String getConfigProperty(String key) {
		ConfigProperty cp = new ConfigProperty("/data/local/tmp/config.properties");
		String value = cp.getProperty(key);

		return value;
	}

	/**
	 * Quit Application by package name
	 * 
	 * @param pkg
	 *            package name, like "com.android.calculator2"
	 */
	public void quitApp(String pkg) {
		String prog = "am force-stop " + pkg;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(prog);
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		process.destroy();
	}

	/**
	 * Clears the log.
	 */
	public void clearLog() {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("logcat -c");
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.destroy();
	}

	/**
	 * Click resource id on the screen
	 * 
	 * @param id
	 *            The resource id on the screen
	 * @throws UiObjectNotFoundException
	 *             if the resource id not found
	 */
	public void clickResourceId(String id) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().resourceId(id));
		obj.clickAndWaitForNewWindow();
	}

	/**
	 * Long click resource id on the screen
	 * 
	 * @param id
	 *            The resource id on the screen
	 * @throws UiObjectNotFoundException
	 *             if the resource id not found
	 */
	public void longClickResourceId(String id) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().resourceId(id));
		obj.longClick();
	}

	/**
	 * Long click resource id on the screen
	 * 
	 * @param regId
	 *            The resource id regular express on the screen
	 * @throws UiObjectNotFoundException
	 *             if the resource id not found
	 */
	public void clickResourceIdMatches(String regId) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().resourceIdMatches(regId));
		obj.clickAndWaitForNewWindow();
	}

	/**
	 * Get text of resource id
	 * 
	 * @param id
	 *            resource id
	 * @param timeout
	 * @return {@code String} text screen of the resource id
	 * @throws UiObjectNotFoundException
	 *             if the resource id not found
	 */
	public String getTextOnResourceId(String id, long timeout) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().resourceId(id));
		if (obj.waitForExists(timeout)) {
			return obj.getText();
		} else {
			return null;
		}
	}

	public String getTextOnResourceIdAndInstance(String id, long timeout, int ins) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().resourceId(id).instance(ins));
		if (obj.waitForExists(timeout)) {
			return obj.getText();
		} else {
			return null;
		}
	}

	/**
	 * Get Object of resource id
	 * 
	 * @param id
	 *            resource id
	 * @return {@code UiObject} The UiObject of the resource id
	 */
	public UiObject getObjectOnResourceId(String id) {
		UiObject obj = new UiObject(new UiSelector().resourceId(id));
		if (obj.exists()) {
			return obj;
		} else {
			return null;
		}
	}

	/**
	 * Get Object of text
	 * 
	 * @param text
	 *            text
	 * @return {@code UiObject} The UiObject of the text
	 */
	public UiObject getObjectOnTextContains(String text) {
		UiObject obj = new UiObject(new UiSelector().textContains(text));
		if (obj.exists()) {
			return obj;
		} else {
			return null;
		}
	}
	
	/**
	 * Get Object of text
	 * 
	 * @param text
	 *            text
	 * @return {@code UiObject} The UiObject of the text
	 */
	public UiObject getObjectOnText(String text) {
		UiObject obj = new UiObject(new UiSelector().text(text));
		if (obj.exists()) {
			return obj;
		} else {
			return null;
		}
	}

	/**
	 * Get Object of text
	 * 
	 * @param text
	 * @param inst
	 * @return {@code UiObject} The UiObject of the text
	 */
	public UiObject getObjectOnTextAndInstance(String text, int inst) {
		UiObject obj = new UiObject(new UiSelector().textContains(text).instance(inst));
		if (obj.exists()) {
			return obj;
		} else {
			return null;
		}
	}

	public UiObject getObjectOnResourceIdAndInstance(String id, int inst) {
		UiObject obj = new UiObject(new UiSelector().resourceId(id).instance(inst));
		if (obj.exists()) {
			return obj;
		} else {
			return null;
		}
	}

	public UiObject getObjectOnResourceIdAndIndex(String id, int in) {
		UiObject obj = new UiObject(new UiSelector().resourceId(id).index(in));
		if (obj.exists()) {
			return obj;
		} else {
			return null;
		}
	}

	/**
	 * Check if the resource id exists or not
	 * 
	 * @param id
	 *            resource id
	 * @return boolean true is existing, otherwise not exists
	 */
	public boolean isResourceIdExist(String id) {
		return new UiObject(new UiSelector().resourceId(id)).exists();
	}

	/**
	 * Check if the text exists or not
	 * 
	 * @param id
	 *            text
	 * @return boolean true is existing, otherwise not exists
	 */
	public boolean isTextExist(String txt) {
		return new UiObject(new UiSelector().text(txt)).exists();
	}

	/**
	 * Wait for content description in timeout
	 * 
	 * @param description
	 *            content-des on the screen
	 * @param timeout
	 *            maximum time to wait
	 * @return true is existing within timeout, false is not existing within
	 *         timeout
	 */
	public boolean waitForContentDescription(String description, long timeout) {
		UiObject obj = new UiObject(new UiSelector().description(description));
		return obj.waitForExists(timeout);
	}

	/**
	 * Wait for Resource ID in timeout
	 * 
	 * @param id
	 *            Resource id on the screen
	 * @param timeout
	 *            maximum time to wait
	 * @return true is existing within timeout, false is not existing within
	 *         timeout
	 */
	public boolean waitForResourceId(String id, long timeout) {
		for (int i = 0; i < timeout / 100; i++) {
			if (isResourceIdExist(id)) {
				return true;
			} else {
				sleep(100);
			}
		}
		return isResourceIdExist(id);
	}

	/**
	 * Wait for Resource ID in timeout
	 * 
	 * @param id
	 *            Resource id on the screen
	 * @param timeout
	 *            maximum time to wait
	 * @return true is existing within timeout, false is not existing within
	 *         timeout
	 */
	public boolean waitForPkgExist(String name, long timeout) {
		UiObject obj = new UiObject(new UiSelector().packageName(name));
		return obj.waitForExists(timeout);
	}

	/**
	 * Get corrdinate of resouce id
	 * 
	 * @param id
	 *            Resource id on the screen
	 * @return Rect Rect of resource id object
	 * @throws UiObjectNotFoundException
	 */
	public Rect getCoordinateOfResourceId(String id) throws UiObjectNotFoundException {
		return new UiObject(new UiSelector().resourceId(id)).getBounds();
	}

	/**
	 * Save Log in filePath
	 * 
	 * @param filePath
	 *            filePath save /sdcard/xxx/xxx.txt
	 * @return true if save successfully
	 */
	public boolean saveLogcat(String filePath) {
		Process proc = null;
		int returncode = -1;

		final String command = String.format("logcat -d -v time -f %s *:V -s", filePath);
		Log.d(AUTOTEST_TAG, "Starting : " + command);
		try {
			proc = Runtime.getRuntime().exec(command);
			returncode = proc.waitFor();
		} catch (IOException e) {
			Log.e(AUTOTEST_TAG,
					"Could not save. Please make sure that an sdcard with enough space (atleast 250Kb)is inserted");
			e.printStackTrace();
		} catch (InterruptedException e) {
			Log.e(AUTOTEST_TAG, "Could not save because we were interrupted");
			e.printStackTrace();
		}

		proc.destroy();
		if (returncode == 0) {
			Log.d(AUTOTEST_TAG, "Logcat End: Return code:" + returncode);
			return true;
		} else {
			Log.e(AUTOTEST_TAG, "Error! Logcat returned: " + returncode);
			return false;
		}
	}

	/**
	 * Execute cmd in adb shell
	 * 
	 */
	public int executeCmd(String cmd) {
		Process proc = null;
		int returncode = -1;
		try {
			proc = Runtime.getRuntime().exec(cmd);
			returncode = proc.waitFor();

		} catch (Exception e) {
			Log.e(AUTOTEST_TAG, "Could not save because we were interrupted");
			e.printStackTrace();
		}
		proc.destroy();
		return returncode;
	}
	
	public void printLog(String msg) {
		System.out.println(msg);
		Log.i(Constant.TAG, msg);
	}

	public void setDebugEnable(boolean flag) {
		DEBUG_LOG = flag;
	}

}
