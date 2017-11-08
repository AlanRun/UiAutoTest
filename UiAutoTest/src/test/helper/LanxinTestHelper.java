package test.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import uiautotestutils.ATCUiAutoTestCase;
import uiautotestutils.utils.ShellUtils;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;

public class LanxinTestHelper {

	private ATCUiAutoTestCase IUiAuto;

	public LanxinTestHelper(ATCUiAutoTestCase atc) {
		super();
		IUiAuto = atc;
	}

	/**
	 * press back and home, return home page
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void backToHome() throws UiObjectNotFoundException {
		IUiAuto.printLog("Helper=" + ATCUiAutoTestCase.getCurrentMethodName());
		IUiAuto.pressKey("back");
		IUiAuto.pressKey("back");
		IUiAuto.pressKey("back");
		IUiAuto.pressKey("back");
		IUiAuto.pressKey("Home");
	}

	/**
	 * verify the time of msg display match regulation
	 * 
	 * @param timeList
	 * @return
	 */
	public boolean verifyMsgListTimeDisplay(ArrayList<String> timeList) {
		IUiAuto.printLog("Helper=" + ATCUiAutoTestCase.getCurrentMethodName());
		@SuppressWarnings("serial")
		HashMap<String, Integer> map = new HashMap<String, Integer>() {
			{
				put("星期一", 1);
				put("星期二", 2);
				put("星期三", 3);
				put("星期四", 4);
				put("星期五", 5);
				put("星期六", 6);
				put("星期日", 7);
			}
		};

		for (int i = 0; i < timeList.size(); i++) {
			IUiAuto.printLog(i + "=" + timeList.get(i));
			if (map.containsKey(timeList.get(i))) {
				continue;
			} else if (timeList.get(i).matches("[0-1][0-9]月[0-3][0-9]日")) {
				continue;
			} else if (timeList.get(i).matches("[2][0][01][0-9]年[0-1][0-9]月[0-3][0-9]日")) {
				continue;
			} else if (timeList.get(i).matches("凌晨[0][0-6]:[0-5][0-9]")
					|| timeList.get(i).matches("早晨[0][7-8]:[0-5][0-9]")
					|| timeList.get(i).matches("上午[01][901]:[0-5][0-9]")
					|| timeList.get(i).matches("中午[1][23]:[0-5][0-9]")
					|| timeList.get(i).matches("下午[1][4-8]:[0-5][0-9]")
					|| timeList.get(i).matches("晚上[1-2][90123]:[0-5][0-9]")) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify Msg list time sort match regulation
	 * 
	 * @param timeList
	 * @return
	 * @throws UiObjectNotFoundException 
	 */
	public boolean verifyMsgListTimeSort(ArrayList<String> timeList) throws UiObjectNotFoundException {
		IUiAuto.printLog("Helper=" + ATCUiAutoTestCase.getCurrentMethodName());
		@SuppressWarnings("serial")
		HashMap<String, Integer> map = new HashMap<String, Integer>() {
			{
				put("星期一", 1);
				put("星期二", 2);
				put("星期三", 3);
				put("星期四", 4);
				put("星期五", 5);
				put("星期六", 6);
				put("星期日", 7);
			}
		};

		for (int i = 0; i < timeList.size(); i++) {
			IUiAuto.printLog(i + "=" + timeList.get(i));
			if (map.containsKey(timeList.get(i))) {

			} else if (timeList.get(i).matches("[0-1][0-9]月[0-3][0-9]日")) {
				map.put(timeList.get(i), 0);
			} else if (timeList.get(i).matches("[2][0][01][0-9]年[0-1][0-9]月[0-3][0-9]日")) {
				map.put(timeList.get(i), -1);
			} else if (timeList.get(i).startsWith("凌晨") || timeList.get(i).startsWith("早晨")
					|| timeList.get(i).startsWith("上午") || timeList.get(i).startsWith("中午")
					|| timeList.get(i).startsWith("下午") || timeList.get(i).startsWith("晚上")) {
				map.put(timeList.get(i), 8);
			}
		}

		int count = 0;

		for (int i = 0; i < timeList.size(); i++) {
			IUiAuto.printLog(i + "=" + timeList.get(i));
			if (i > 0) {
				String s1 = timeList.get(i - 1);
				String s2 = timeList.get(i);
				IUiAuto.printLog("s1=" + s1);
				IUiAuto.printLog("s2=" + s2);
				if (map.get(s1) < map.get(s2)) {
					count++;
				} else if (map.get(s1) == map.get(s2)) {
					if (!s1.equals(s2)) {
						if (s1.matches("[0-1][0-9]月[0-3][0-9]日")) {
							if (s1.compareTo(s2) < 0) {
								count++;
							}
						} else if (s1.matches("[2][0][01][0-9]年[0-1][0-9]月[0-3][0-9]日")) {
							if (s1.compareTo(s2) < 0) {
								count++;
							}
						} else {
							// IUiAuto.printLog("length="+s1.length());
							String t1 = s1.substring(2, 6);
							String t2 = s2.substring(2, 6);
							if (t1.compareTo(t2) < 0) {
								count++;
							}
						}
					}
				}
			}
		}
		
		int y = IUiAuto.getCoordinateByText(Constant.TXT_NOTICE).y;
		if (y < 400) {
			if (count > 2) {
				return false;
			} else {
				return true;
			}
		} else {
			if (count > 1) {
				return false;
			} else {
				return true;
			}
		}

	}

	/**
	 * Verify file list time sort match regulation
	 * 
	 * @param timeList
	 * @return
	 */
	public boolean verifyFileListTimeSort(ArrayList<String> timeList) {
		IUiAuto.printLog("Helper=" + ATCUiAutoTestCase.getCurrentMethodName());
		@SuppressWarnings("serial")
		HashMap<String, Integer> map = new HashMap<String, Integer>() {
			{
				put("星期一", 1);
				put("星期二", 2);
				put("星期三", 3);
				put("星期四", 4);
				put("星期五", 5);
				put("星期六", 6);
				put("星期日", 7);
			}
		};

		for (int i = 0; i < timeList.size(); i++) {
			IUiAuto.printLog(i + "=" + timeList.get(i));
			if (map.containsKey(timeList.get(i))) {

			} else if (timeList.get(i).matches("\\w+/\\w+")) {
				map.put(timeList.get(i), 0);
			} else if (timeList.get(i).startsWith("上午")) {
				map.put(timeList.get(i), 8);
			} else if (timeList.get(i).startsWith("下午")) {
				map.put(timeList.get(i), 9);
			} else if (timeList.get(i).startsWith("昨天 上午")) {
				map.put(timeList.get(i), 10);
			} else if (timeList.get(i).startsWith("昨天 下午")) {
				map.put(timeList.get(i), 11);
			} else if (timeList.get(i).matches("\\w+/\\w+/\\w+")) {
				map.put(timeList.get(i), -1);
			}
		}

		int count = 0;

		for (int i = 0; i < timeList.size(); i++) {
			IUiAuto.printLog(i + "=" + timeList.get(i));
			if (i > 0) {
				String s1 = timeList.get(i - 1);
				String s2 = timeList.get(i);
				if (map.get(s1) < map.get(s2)) {
					count++;
				} else if (map.get(s1) == map.get(s2)) {
					if (!s1.equals(s2)) {
						if (s1.matches("\\w+/\\w+")) {
							if (s1.compareTo(s2) < 0) {
								count++;
							}
						} else if (s1.matches("\\w+/\\w+/\\w+")) {
							if (s1.compareTo(s2) < 0) {
								count++;
							}
						} else {
							String t1 = s1.substring(2, s1.length() - 1);
							String t2 = s2.substring(2, s2.length() - 1);
							if (t1.compareTo(t2) == -1) {
								count++;
							}
						}
					}
				}
			}
		}

		if (count > 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Turn on Flight mode
	 * 
	 * @throws UiObjectNotFoundException
	 */
	@SuppressWarnings("static-access")
	public void TurnOnFlightMode() throws UiObjectNotFoundException {
		IUiAuto.printLog("Helper=" + ATCUiAutoTestCase.getCurrentMethodName());
		String pkg = "com.android.settings";
		String cls = pkg + ".SubSettings";

		IUiAuto.launchApp(pkg, cls);

		UiObject check = new UiObject(new UiSelector().resourceId(Constant.ID_SETTING_CHECK));

		IUiAuto.assertTrue("Help-Launch Settings failed.", check.waitForExists(3000));

		if (check.isChecked()) {
			return;
		} else {
			IUiAuto.clickResourceId(Constant.ID_SETTING_CHECK);
		}

		backToHome();
		shutDownLanxinApp();
	}

	/**
	 * Turn off Flight mode
	 * 
	 * @throws UiObjectNotFoundException
	 */
	@SuppressWarnings("static-access")
	public void TurnOffFlightMode() throws UiObjectNotFoundException {
		IUiAuto.printLog("Helper=" + ATCUiAutoTestCase.getCurrentMethodName());
		String pkg = "com.android.settings";
		String cls = pkg + ".SubSettings";

		IUiAuto.launchApp(pkg, cls);

		UiObject check = new UiObject(new UiSelector().resourceId(Constant.ID_SETTING_CHECK));

		IUiAuto.assertTrue("Help-Launch Settings failed.", check.waitForExists(3000));

		if (check.isChecked()) {
			IUiAuto.clickResourceId(Constant.ID_SETTING_CHECK);
		} else {
			return;
		}

		backToHome();
		shutDownLanxinApp();
	}

	/**
	 * Clear app user data
	 * 
	 * @throws UiObjectNotFoundException
	 */
	@SuppressWarnings("static-access")
	public void clearAppUserData() throws UiObjectNotFoundException {
		IUiAuto.printLog("Helper=" + ATCUiAutoTestCase.getCurrentMethodName());
		String pkg = IUiAuto.getConfigProperty("pkg");
		IUiAuto.launchAppDetails(pkg);

		IUiAuto.assertTrue("Launch app Details", IUiAuto.waitForTextExists("「蓝信」", 5 * 1000));

		UiObject clear;
		if (IUiAuto.getUiDevice().getProductName().equals("Che1-CL20")
				|| IUiAuto.getUiDevice().getProductName().equals("RIO-AL00")) {
			clear = IUiAuto.scrollToFindText(Constant.TXT_DELETEDATA, "Vertical", 3);
		} else {
			clear = IUiAuto.scrollToFindText(Constant.TXT_CLEARDATA, "Vertical", 3);
		}
		

		if (!clear.isEnabled()) {
			IUiAuto.sleep(3 * 1000);
		} else {
			if (clear.isEnabled()) {
				clear.clickAndWaitForNewWindow();
				IUiAuto.clickText(Constant.TXT_CONFIRM, true);
			}
		}
		backToHome();

	}
	
	/**
	 * Clear app user data
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void clearAppUserDataByCommand() throws UiObjectNotFoundException {
		IUiAuto.printLog("Helper=" + ATCUiAutoTestCase.getCurrentMethodName());
		String pkg = IUiAuto.getConfigProperty("pkg");
		String cmd = "pm clear " + pkg;
		ShellUtils.execCommand(cmd, false);
	}

	/**
	 * Shut up lanxin application
	 */
	public void shutDownLanxinApp() {
		IUiAuto.printLog("Helper=" + ATCUiAutoTestCase.getCurrentMethodName());
		String pkg = IUiAuto.getConfigProperty("pkg");
		IUiAuto.quitApp(pkg);

	}

	/**
	 * Start Lanxin application
	 * 
	 * @param phoneNum
	 * @param password
	 * @throws UiObjectNotFoundException
	 */
	public void startLanxinApp(String phoneNum, String password) throws UiObjectNotFoundException {
		IUiAuto.printLog("Helper=" + ATCUiAutoTestCase.getCurrentMethodName());
		String pkg = IUiAuto.getConfigProperty("pkg");
		String cls = IUiAuto.getConfigProperty("cls");
		String org = IUiAuto.getConfigProperty("org_name");
		
		IUiAuto.printLog("pkg=" + pkg);
		IUiAuto.printLog("cls=" + cls);
		IUiAuto.printLog("org_name=" + org);

		IUiAuto.makeSureNetConnected();

		// launch app
		IUiAuto.launchApp(pkg, cls);

		IUiAuto.waitForWindowUpdate(6 * 1000);

		if (IUiAuto.waitForTextExists(Constant.TXT_TOOLBAR_ADDRESS, 3000)) {
			return;
		}

		if (IUiAuto.waitForTextExists(Constant.TXT_NOTIFY_UPDATE_LATER, 2000)) {
			IUiAuto.clickText(Constant.TXT_NOTIFY_UPDATE_LATER, true);
			System.err.println("****Lanxin Update Notify appear!!!****");
		}

		if (IUiAuto.waitForTextExists(Constant.TXT_I_KNOW, 2000)) {
			IUiAuto.clickText(Constant.TXT_I_KNOW, true);
		}

		if (IUiAuto.waitForTextExists(Constant.TXT_TOOLBAR_ADDRESS, 2000)) {
			return;
		}

		// 导览UI
		while (IUiAuto.waitForResourceId(Constant.ID_NEXT, 2 * 1000)) {

			IUiAuto.clickResourceId(Constant.ID_NEXT);
		}

		if (IUiAuto.waitForTextExists(Constant.TXT_LOGIN, 2 * 1000)) {
			IUiAuto.clickText(Constant.TXT_LOGIN, true);
		}

		if (IUiAuto.waitForTextExists(Constant.TXT_TOOLBAR_ADDRESS, 2000)) {
			return;
		}

		// Input num, chose platform and input password
		if (IUiAuto.waitForResourceId(Constant.ID_CONFIRM_PHONE_BUTTON, 2 * 1000)) {

			// input phoneNum
			IUiAuto.enterTextInEditor(phoneNum, "android.widget.EditText", 0);
			IUiAuto.clickResourceId(Constant.ID_CONFIRM_PHONE_BUTTON);

			// platform choice
			IUiAuto.waitForWindowUpdate(5000);
			if (IUiAuto.waitForResourceId(Constant.ID_EDITTEXT_PW, 5 * 1000)) {
			} else {
				if (IUiAuto.waitForTextExists(org, 5000)) {
				} else {
					IUiAuto.scrollToFindText(org, "Vertical", 15);
				}
				IUiAuto.clickText(org, true);
			}

			IUiAuto.waitForWindowUpdate(5000);
			ATCUiAutoTestCase.assertTrue("Click next button cann't go password login, pls check the network",
					IUiAuto.waitForResourceId(Constant.ID_EDITTEXT_PW, 10 * 1000));

			// input password
			IUiAuto.clickResourceId(Constant.ID_EDITTEXT_PW);
			IUiAuto.waitForWindowUpdate(2000);
			IUiAuto.clickResourceId(Constant.ID_EDITTEXT_PW);
			UiObject pw = new UiObject(new UiSelector().resourceId(Constant.ID_EDITTEXT_PW));
			pw.setText(password);

			// click OK
			IUiAuto.clickResourceId(Constant.ID_BUTTON_OK);
			IUiAuto.waitForWindowUpdate(3000);
			
			if (IUiAuto.waitForTextExists(Constant.TXT_PWD, 8000)) {
				UiObject editer = IUiAuto.getObjectOnResourceId("com.unicom.gudong.client:id/checkcode");
				for (int i = 0; i < 20; i++) {
					IUiAuto.printLog(Constant.TXT_PWD + "=" + editer.getText());
					if (editer.getText().equals("输入验证码")) {
						IUiAuto.sleep(1000);
					} else {
						break;
					}
				}
				IUiAuto.clickResourceId(Constant.ID_BUTTON_OK);
				IUiAuto.waitForWindowUpdate(3000);
			}

		}

		if (IUiAuto.waitForTextExists(Constant.TXT_SECURITY_ALERT_ALERT_TITLE, 5000)) {
			IUiAuto.clickText(Constant.TXT_SECURITY_ALERT_ACCEPT, true);
		}

		if (IUiAuto.waitForTextExists(Constant.TXT_I_KNOW, 10000)) {
			IUiAuto.clickText(Constant.TXT_I_KNOW, true);
		}

		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Click ok button cann't login, pls check the network",
				IUiAuto.waitForResourceId(Constant.ID_MAIN_VIEW_TOOLBAR, 5 * 1000));

	}

	public void handleGuideSetup() throws UiObjectNotFoundException {
		if (IUiAuto.waitForResourceId(Constant.ID_NEXT, 2 * 1000)) {
			// 导览UI
			while (IUiAuto.getTextOnResourceId(Constant.ID_NEXT, 2 * 1000).equals(Constant.TXT_NEXT)) {

				IUiAuto.clickText(Constant.TXT_NEXT, true);
			}
		}

		if (IUiAuto.waitForTextExists(Constant.TXT_LOGIN, 2 * 1000)) {
			IUiAuto.clickText(Constant.TXT_LOGIN, true);
		}
	}

	// /**
	// * log out lanxin
	// *
	// * @throws UiObjectNotFoundException
	// */
	// public void logOutLanxinBySetting() throws UiObjectNotFoundException {
	// IUiAuto.printLog("Helper=" + ATCUiAutoTestCase.getCurrentMethodName());
	// String pkg = IUiAuto.getConfigProperty("pkg");
	// String cls = IUiAuto.getConfigProperty("cls");
	// IUiAuto.launchApp(pkg, cls);
	//
	// IUiAuto.waitForWindowUpdate(6 * 1000);
	// if (IUiAuto.waitForResourceId(Constant.ID_NEXT, 2 * 1000)) {
	// backToHome();
	// shutDownLanxinApp();
	// return;
	// }
	//
	// if (IUiAuto.waitForResourceId(Constant.ID_MAIN_VIEW_TOOLBAR, 2 * 1000)) {
	// IUiAuto.clickResourceId(Constant.ID_TOOLBAR_MORE);
	// ATCUiAutoTestCase.assertTrue("click TOOLBAR_MORE failed.",
	// IUiAuto.waitForTextExists(Constant.TXT_SETTINGS, 3 * 1000));
	//
	// IUiAuto.clickText(Constant.TXT_SETTINGS, true);
	// IUiAuto.dragDirection("up", 35);
	// IUiAuto.waitForWindowUpdate(2000);
	// IUiAuto.dragDirection("up", 35);
	// ATCUiAutoTestCase.assertTrue("click TXT_SETTINGS failed.",
	// IUiAuto.waitForTextExists(Constant.TXT_LOGOUT, 5 * 1000));
	//
	// IUiAuto.clickText(Constant.TXT_LOGOUT, true);
	// ATCUiAutoTestCase.assertTrue("click TXT_LOGOUT failed.",
	// (IUiAuto.getTextOnResourceId(Constant.ID_BUTTON1,
	// 3000)).equals(Constant.TXT_LOGOUT));
	//
	// IUiAuto.clickText(Constant.TXT_LOGOUT, true);
	// ATCUiAutoTestCase.assertTrue("click second TXT_LOGOUT failed.",
	// IUiAuto.waitForResourceId(Constant.ID_CONFIRM_PHONE_BUTTON, 3 * 1000));
	// backToHome();
	// shutDownLanxinApp();
	// }
	//
	// if (IUiAuto.waitForTextExists(Constant.TXT_NOTIFY_UPDATE_LATER, 2000)) {
	// IUiAuto.clickText(Constant.TXT_NOTIFY_UPDATE_LATER, true);
	// System.err.println("****Lanxin Update Notify appear!!!****");
	// }
	//
	// if (IUiAuto.waitForTextExists(Constant.TXT_I_KNOW, 2000)) {
	// IUiAuto.clickText(Constant.TXT_I_KNOW, true);
	// }
	//
	// if (IUiAuto.waitForResourceId(Constant.ID_NEXT, 2 * 1000)) {
	// backToHome();
	// shutDownLanxinApp();
	// return;
	// }
	//
	// if (IUiAuto.waitForResourceId(Constant.ID_MAIN_VIEW_TOOLBAR, 2 * 1000)) {
	// IUiAuto.clickResourceId(Constant.ID_TOOLBAR_MORE);
	// ATCUiAutoTestCase.assertTrue("click TOOLBAR_MORE failed.",
	// IUiAuto.waitForTextExists(Constant.TXT_SETTINGS, 3 * 1000));
	//
	// IUiAuto.clickText(Constant.TXT_SETTINGS, true);
	// IUiAuto.dragDirection("up", 35);
	// IUiAuto.waitForWindowUpdate(2000);
	// IUiAuto.dragDirection("up", 35);
	// ATCUiAutoTestCase.assertTrue("click TXT_SETTINGS failed.",
	// IUiAuto.waitForTextExists(Constant.TXT_LOGOUT, 5 * 1000));
	//
	// IUiAuto.clickText(Constant.TXT_LOGOUT, true);
	// ATCUiAutoTestCase.assertTrue("click TXT_LOGOUT failed.",
	// (IUiAuto.getTextOnResourceId(Constant.ID_BUTTON1,
	// 3000)).equals(Constant.TXT_LOGOUT));
	//
	// IUiAuto.clickText(Constant.TXT_LOGOUT, true);
	// ATCUiAutoTestCase.assertTrue("click second TXT_LOGOUT failed.",
	// IUiAuto.waitForResourceId(Constant.ID_CONFIRM_PHONE_BUTTON, 3 * 1000));
	// backToHome();
	// shutDownLanxinApp();
	// }
	//
	// }

	public void workeGroup_CreateGroup(String phoneNum, String password) throws UiObjectNotFoundException {

		IUiAuto.printLog("phoneNum=" + phoneNum);
		IUiAuto.printLog("password=" + password);

		IUiAuto.printLog("step1=Start Lanxin App");
		startLanxinApp(phoneNum, password);

		ATCUiAutoTestCase.assertTrue("Ui Element not exist: ID_TOOLBAR_MSG",
				IUiAuto.waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		IUiAuto.printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		IUiAuto.clickResourceId(Constant.ID_TOOLBAR_MSG);
		ATCUiAutoTestCase.assertTrue("Msg list not exist", IUiAuto.waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		IUiAuto.printLog("step3=Click titlebar_plus");
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_PLUS);
		IUiAuto.sleep(500);

		String iconArr[] = IUiAuto.getConfigProperty("titlebar_popup_plus").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);

		double diff = IUiAuto
				.getScreenDiffWithReference(Constant.RES_FILE_PATH + "titlebar_popup_plus", x1, y1, x2, y2);
		IUiAuto.printLog("diff=" + diff);

		ATCUiAutoTestCase.assertTrue("POPUP_PLUS not match expect", diff < Constant.DIFF_MIN);

		IUiAuto.printLog("step4=Click " + Constant.TXT_CREATE_WORK_GROUP);

		String pointArr[] = IUiAuto.getConfigProperty("create_group").split(",");
		int x = Integer.parseInt(pointArr[0]);
		int y = Integer.parseInt(pointArr[1]);

		IUiAuto.clickPoint(new Point(x, y));
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				IUiAuto.waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
	}
	
	public void notification_Create(String phoneNum, String password) throws UiObjectNotFoundException {
		IUiAuto.printLog("phoneNum=" + phoneNum);
		IUiAuto.printLog("password=" + password);

		IUiAuto.printLog("step1=Start Lanxin App");
		startLanxinApp(phoneNum, password);

		ATCUiAutoTestCase.assertTrue("Ui Element not exist: ID_TOOLBAR_MSG",
				IUiAuto.waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		IUiAuto.printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		IUiAuto.clickResourceId(Constant.ID_TOOLBAR_MSG);
		ATCUiAutoTestCase.assertTrue("Msg list not exist", IUiAuto.waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		IUiAuto.printLog("step3=Click titlebar_plus");
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_PLUS);
		IUiAuto.sleep(500);

		String iconArr[] = IUiAuto.getConfigProperty("titlebar_popup_plus").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);

		double diff = IUiAuto
				.getScreenDiffWithReference(Constant.RES_FILE_PATH + "titlebar_popup_plus", x1, y1, x2, y2);
		IUiAuto.printLog("diff=" + diff);

		ATCUiAutoTestCase.assertTrue("POPUP_PLUS not match expect", diff < Constant.DIFF_MIN);

		IUiAuto.printLog("step4=Click " + Constant.TXT_CREATE_NOTICE);

		String pointArr[] = IUiAuto.getConfigProperty("create_notify").split(",");
		int x = Integer.parseInt(pointArr[0]);
		int y = Integer.parseInt(pointArr[1]);

		IUiAuto.clickPoint(new Point(x, y));
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Ui Element not exist: " + Constant.TXT_CREATE_NOTICE,
				IUiAuto.waitForTextExists(Constant.TXT_CREATE_NOTICE, 8000));
	}

	public void workeGroup_CreateGroup(String phoneNum, String password, String image) throws UiObjectNotFoundException {

		IUiAuto.printLog("phoneNum=" + phoneNum);
		IUiAuto.printLog("password=" + password);

		IUiAuto.printLog("step1=Start Lanxin App");
		startLanxinApp(phoneNum, password);

		ATCUiAutoTestCase.assertTrue("Ui Element not exist: ID_TOOLBAR_MSG",
				IUiAuto.waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		IUiAuto.printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		IUiAuto.clickResourceId(Constant.ID_TOOLBAR_MSG);
		ATCUiAutoTestCase.assertTrue("Msg list not exist", IUiAuto.waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		IUiAuto.printLog("step3=Click titlebar_plus");
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_PLUS);
		IUiAuto.sleep(500);

		String iconArr[] = IUiAuto.getConfigProperty("titlebar_popup_plus").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);

		double diff = IUiAuto.getScreenDiffWithReference(Constant.RES_FILE_PATH + image, x1, y1, x2, y2);
		IUiAuto.printLog("diff=" + diff);

		ATCUiAutoTestCase.assertTrue("POPUP_PLUS not match expect", diff < Constant.DIFF_MIN);

		IUiAuto.printLog("step4=Click " + Constant.TXT_CREATE_WORK_GROUP);

		String pointArr[] = IUiAuto.getConfigProperty("create_group").split(",");
		int x = Integer.parseInt(pointArr[0]);
		int y = Integer.parseInt(pointArr[1]);

		IUiAuto.clickPoint(new Point(x, y));
		IUiAuto.waitForWindowUpdate(3000);
	}

	public void groupChat_CreateGroup(String phoneNum, String password) throws UiObjectNotFoundException {

		IUiAuto.printLog("phoneNum=" + phoneNum);
		IUiAuto.printLog("password=" + password);

		IUiAuto.printLog("step1=Start Lanxin App");
		startLanxinApp(phoneNum, password);

		ATCUiAutoTestCase.assertTrue("Ui Element not exist: ID_TOOLBAR_MSG",
				IUiAuto.waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		IUiAuto.printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		IUiAuto.clickResourceId(Constant.ID_TOOLBAR_MSG);
		ATCUiAutoTestCase.assertTrue("Msg list not exist", IUiAuto.waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		IUiAuto.printLog("step3=Click titlebar_plus");
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_PLUS);
		IUiAuto.sleep(500);

		String iconArr[] = IUiAuto.getConfigProperty("titlebar_popup_plus").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);

		double diff = IUiAuto
				.getScreenDiffWithReference(Constant.RES_FILE_PATH + "titlebar_popup_plus", x1, y1, x2, y2);
		IUiAuto.printLog("diff=" + diff);

		ATCUiAutoTestCase.assertTrue("POPUP_PLUS not match expect", diff < Constant.DIFF_MIN);

		IUiAuto.printLog("step4=Click " + Constant.TXT_TITLEBAR_GROUP_CHAT);

		String pointArr[] = IUiAuto.getConfigProperty("group_chat").split(",");
		int x = Integer.parseInt(pointArr[0]);
		int y = Integer.parseInt(pointArr[1]);

		IUiAuto.clickPoint(new Point(x, y));
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				IUiAuto.waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));

	}

	public void groupChat_CreateGroup(String phoneNum, String password, String image) throws UiObjectNotFoundException {

		IUiAuto.printLog("phoneNum=" + phoneNum);
		IUiAuto.printLog("password=" + password);

		IUiAuto.printLog("step1=Start Lanxin App");
		startLanxinApp(phoneNum, password);

		ATCUiAutoTestCase.assertTrue("Ui Element not exist: ID_TOOLBAR_MSG",
				IUiAuto.waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		IUiAuto.printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		IUiAuto.clickResourceId(Constant.ID_TOOLBAR_MSG);
		ATCUiAutoTestCase.assertTrue("Msg list not exist", IUiAuto.waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		IUiAuto.printLog("step3=Click titlebar_plus");
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_PLUS);
		IUiAuto.sleep(500);

		String iconArr[] = IUiAuto.getConfigProperty("titlebar_popup_plus").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);

		double diff = IUiAuto.getScreenDiffWithReference(Constant.RES_FILE_PATH + image, x1, y1, x2, y2);
		IUiAuto.printLog("diff=" + diff);

		ATCUiAutoTestCase.assertTrue("POPUP_PLUS not match expect", diff < Constant.DIFF_MIN);

		IUiAuto.printLog("step4=Click " + Constant.TXT_TITLEBAR_GROUP_CHAT);

		String pointArr[] = IUiAuto.getConfigProperty("group_chat").split(",");
		int x = Integer.parseInt(pointArr[0]);
		int y = Integer.parseInt(pointArr[1]);

		IUiAuto.clickPoint(new Point(x, y));
		IUiAuto.waitForWindowUpdate(3000);

	}

	public void callMeeting_CreateMeeting(String phoneNum, String password) throws UiObjectNotFoundException {

		IUiAuto.printLog("phoneNum=" + phoneNum);
		IUiAuto.printLog("password=" + password);

		IUiAuto.printLog("step1=Start Lanxin App");
		startLanxinApp(phoneNum, password);

		ATCUiAutoTestCase.assertTrue("Ui Element not exist: ID_TOOLBAR_MSG",
				IUiAuto.waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		IUiAuto.printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		IUiAuto.clickResourceId(Constant.ID_TOOLBAR_MSG);
		ATCUiAutoTestCase.assertTrue("Msg list not exist", IUiAuto.waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		IUiAuto.printLog("step3=Click titlebar_plus");
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_PLUS);
		IUiAuto.sleep(500);

		String iconArr[] = IUiAuto.getConfigProperty("titlebar_popup_plus").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);

		double diff = IUiAuto
				.getScreenDiffWithReference(Constant.RES_FILE_PATH + "titlebar_popup_plus", x1, y1, x2, y2);
		IUiAuto.printLog("diff=" + diff);

		ATCUiAutoTestCase.assertTrue("POPUP_PLUS not match expect", diff < Constant.DIFF_MIN);

		IUiAuto.printLog("step4=Click " + Constant.TXT_TITLEBAR_CALL_MEETING);

		String pointArr[] = IUiAuto.getConfigProperty("call_meeting").split(",");
		int x = Integer.parseInt(pointArr[0]);
		int y = Integer.parseInt(pointArr[1]);

		IUiAuto.clickPoint(new Point(x, y));
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS
				+ " maybe Permission denied", IUiAuto.waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
	}

	@SuppressWarnings("static-access")
	public void addContacts_Enter(String phoneNum, String password) throws UiObjectNotFoundException {
		IUiAuto.printLog("phoneNum=" + phoneNum);
		IUiAuto.printLog("password=" + password);

		IUiAuto.printLog("step1=Start Lanxin App");
		startLanxinApp(phoneNum, password);

		IUiAuto.assertTrue("Ui Element not exist: ID_TOOLBAR_MSG",
				IUiAuto.waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		IUiAuto.printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		IUiAuto.clickResourceId(Constant.ID_TOOLBAR_MSG);
		IUiAuto.assertTrue("Msg list not exist", IUiAuto.waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		IUiAuto.printLog("step3=Click titlebar_plus");
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_PLUS);
		IUiAuto.sleep(500);

		String iconArr[] = IUiAuto.getConfigProperty("titlebar_popup_plus").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);

		double diff = IUiAuto
				.getScreenDiffWithReference(Constant.RES_FILE_PATH + "titlebar_popup_plus", x1, y1, x2, y2);
		IUiAuto.printLog("diff=" + diff);

		IUiAuto.assertTrue("POPUP_PLUS not match expect", diff < Constant.DIFF_MIN);

		IUiAuto.printLog("step4=Click " + Constant.TXT_TITLEBAR_ADD_CONTACTS);

		String pointArr[] = IUiAuto.getConfigProperty("add_contacts").split(",");
		int x = Integer.parseInt(pointArr[0]);
		int y = Integer.parseInt(pointArr[1]);

		IUiAuto.clickPoint(new Point(x, y));
		IUiAuto.waitForWindowUpdate(5000);
		IUiAuto.assertTrue("Ui Element not exist: " + Constant.TXT_TITLEBAR_ADD_CONTACTS,
				IUiAuto.waitForTextExists(Constant.TXT_TITLEBAR_ADD_CONTACTS, 5000));
	}

	public void deleteGroup() throws UiObjectNotFoundException {
		IUiAuto.clickResourceId(Constant.ID_CHAT_SETTINGS);
		if (IUiAuto.waitForTextExists(Constant.TXT_GROUPCHAT_SETTINGS_DELETE, 3000)) {
			IUiAuto.clickText(Constant.TXT_GROUPCHAT_SETTINGS_DELETE, true);
		} else {
			IUiAuto.dragDirection("up", 35);
			IUiAuto.dragDirection("up", 35);
			IUiAuto.dragDirection("up", 35);
			IUiAuto.clickText(Constant.TXT_GROUPCHAT_SETTINGS_DELETE, true);
		}
		
		if (IUiAuto.getUiDevice().getProductName().equals("h3gduoszn")) {
			IUiAuto.clickText(Constant.TXT_VERIFY, true);
		} else {
			IUiAuto.clickText(Constant.TXT_CONFIRM, true);
		}

		IUiAuto.waitForWindowUpdate(3000);
	}

	@SuppressWarnings("static-access")
	public void callMeeting_CreateMeeting_Member(String phoneNum, String password) throws UiObjectNotFoundException {

		callMeeting_CreateMeeting(phoneNum, password);

		UiObject contact1Check = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			IUiAuto.printLog("step5=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			IUiAuto.assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			IUiAuto.assertTrue("Select contact2 failed.", contact2Check.isChecked());

			IUiAuto.printLog("step6=Click the confirm button");
			IUiAuto.clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			IUiAuto.assertTrue("UI Element not exist:" + Constant.TXT_NEW_CALLMEETING,
					IUiAuto.waitForTextExists(Constant.TXT_NEW_CALLMEETING, 3000));

		} else {
			IUiAuto.assertTrue("This account has no contact", false);
		}
	}

	@SuppressWarnings("static-access")
	public void callMeeting_CreateMeeting_Branch(String phoneNum, String password) throws UiObjectNotFoundException {
		callMeeting_CreateMeeting(phoneNum, password);

		IUiAuto.printLog("step5=click org icon");
		IUiAuto.clickResourceId(Constant.ID_TAB_ORG);
		IUiAuto.sleep(1000);

		UiObject org = IUiAuto.getObjectOnResourceId(Constant.ID_TAB_ORG);
		UiObject org_path = IUiAuto.getObjectOnResourceId(Constant.ID_ORG_PATH);
		IUiAuto.assertTrue("Switch from Contact to org failed.", org.isChecked());
		IUiAuto.assertTrue("Ui Element not exist: org path", org_path.waitForExists(2000));
		IUiAuto.assertTrue("Ui Element not exist: show list icon",
				IUiAuto.waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));

		UiObject checkDepartment = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_DEPART_CHECKBOX, 1);
		if (checkDepartment.exists()) {

			IUiAuto.printLog("Select a branch in org");
			checkDepartment.clickAndWaitForNewWindow();
			IUiAuto.assertTrue("Branch not be select", checkDepartment.isChecked());

			IUiAuto.printLog("step6=Click confirm button");
			IUiAuto.clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			IUiAuto.waitForWindowUpdate(3000);
			IUiAuto.assertTrue("UI Element not exist:" + Constant.TXT_NEW_CALLMEETING,
					IUiAuto.waitForTextExists(Constant.TXT_NEW_CALLMEETING, 3000));

		} else {
			IUiAuto.assertTrue("Department not exist", false);
		}
	}

	@SuppressWarnings("static-access")
	public void workGroup_CreateGroup_Branch(String phoneNum, String password) throws UiObjectNotFoundException {

		workeGroup_CreateGroup(phoneNum, password);

		IUiAuto.printLog("step5=click org icon");
		IUiAuto.clickResourceId(Constant.ID_TAB_ORG);
		IUiAuto.sleep(1000);

		UiObject org = IUiAuto.getObjectOnResourceId(Constant.ID_TAB_ORG);
		UiObject org_path = IUiAuto.getObjectOnResourceId(Constant.ID_ORG_PATH);
		IUiAuto.assertTrue("Switch from Contact to org failed.", org.isChecked());
		IUiAuto.assertTrue("Ui Element not exist: org path", org_path.waitForExists(2000));
		IUiAuto.assertTrue("Ui Element not exist: show list icon",
				IUiAuto.waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));

		UiObject checkDepartment = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_DEPART_CHECKBOX, 0);
		if (checkDepartment.exists()) {

			IUiAuto.printLog("step6=Select a branch in org");
			checkDepartment.clickAndWaitForNewWindow();
			IUiAuto.assertTrue("Branch not be select", checkDepartment.isChecked());

			IUiAuto.printLog("step7=Click confirm button");
			IUiAuto.clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			IUiAuto.assertTrue("UI Element not exist:" + Constant.TXT_NEWGROUP,
					IUiAuto.waitForTextExists(Constant.TXT_NEWGROUP, 3000));

		} else {
			IUiAuto.assertTrue("Department not exist", false);
		}

	}

	@SuppressWarnings("static-access")
	public void workGroup_CreateGroup_Member(String phoneNum, String password) throws UiObjectNotFoundException {
		workeGroup_CreateGroup(phoneNum, password);

		UiObject contact1Check = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			IUiAuto.printLog("step5=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			IUiAuto.assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			IUiAuto.assertTrue("Select contact2 failed.", contact2Check.isChecked());

			IUiAuto.clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			IUiAuto.printLog("step6=Veirfy UI Element");
			IUiAuto.assertTrue("UI Element not exist:" + Constant.TXT_NEWGROUP,
					IUiAuto.waitForTextExists(Constant.TXT_NEWGROUP, 3000));

		} else {
			IUiAuto.assertTrue("This account has no contact", false);
		}
	}

	@SuppressWarnings("static-access")
	public void groupChat_CreateGroup_Member(String phoneNum, String password) throws UiObjectNotFoundException {
		groupChat_CreateGroup(phoneNum, password);

		UiObject contact1Check = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			IUiAuto.printLog("step5=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			IUiAuto.assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			IUiAuto.assertTrue("Select contact2 failed.", contact2Check.isChecked());

			IUiAuto.printLog("step6=Click confirm button");
			IUiAuto.clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			IUiAuto.assertTrue("UI Element not exist:" + Constant.TXT_CREATE_CHAT_GROUP,
					IUiAuto.waitForTextExists(Constant.TXT_CREATE_CHAT_GROUP, 3000));

		} else {
			IUiAuto.assertTrue("This account has no contact", false);
		}
	}

	@SuppressWarnings("static-access")
	public void groupChat_CreateGroup_Branch(String phoneNum, String password) throws UiObjectNotFoundException {

		groupChat_CreateGroup(phoneNum, password);

		IUiAuto.printLog("step5=click org icon");
		IUiAuto.clickResourceId(Constant.ID_TAB_ORG);
		IUiAuto.sleep(1000);

		UiObject org = IUiAuto.getObjectOnResourceId(Constant.ID_TAB_ORG);
		UiObject org_path = IUiAuto.getObjectOnResourceId(Constant.ID_ORG_PATH);
		IUiAuto.assertTrue("Switch from Contact to org failed.", org.isChecked());
		IUiAuto.assertTrue("Ui Element not exist: org path", org_path.waitForExists(2000));
		IUiAuto.assertTrue("Ui Element not exist: show list icon",
				IUiAuto.waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));

		UiObject checkDepartment = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_DEPART_CHECKBOX, 0);
		if (checkDepartment.exists()) {

			IUiAuto.printLog("Select a branch in org");
			checkDepartment.clickAndWaitForNewWindow();
			IUiAuto.assertTrue("Branch not be select", checkDepartment.isChecked());

			IUiAuto.printLog("step6=Click confirm button");
			IUiAuto.clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			IUiAuto.assertTrue("UI Element not exist:" + Constant.TXT_NEWGROUPSEND,
					IUiAuto.waitForTextExists(Constant.TXT_NEWGROUPSEND, 3000));

		} else {
			IUiAuto.assertTrue("Department not exist", false);
		}

	}

	public void enterTextByCmd(String txt) {
		String cmd = "input text " + txt;
		ShellUtils.execCommand(cmd, true);
	}

	public void writeSDFile(String fileName, int size) throws IOException {
		Date d = new Date();
		long longtime = d.getTime();
		String write_str = String.valueOf(longtime) + String.valueOf(longtime) + String.valueOf(longtime)
				+ String.valueOf(longtime) + String.valueOf(longtime) + String.valueOf(longtime)
				+ String.valueOf(longtime) + String.valueOf(longtime) + String.valueOf(longtime);
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		} else {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		while (file.length() < 1024 * 1024 * size) {
			byte[] bytes = write_str.getBytes();
			fos.write(bytes);
		}
		fos.close();
	}

	public void zipFiles(File[] srcfile, File zipfile) {
		byte[] buf = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTxtAndZipFiles(String zipName) throws IOException {
		IUiAuto.printLog("Helper--Create Txt and zip file");
		File f1 = new File("/sdcard/" + "testFile.txt");
		File f2 = new File(Constant.RES_FILE_PATH + "test.mp3");
		writeSDFile(f1.toString(), 10);

		File[] srcfile = { f1, f2 };

		File zipfile = new File("/sdcard/" + zipName);
		if (zipfile.exists()) {
			zipfile.delete();
		}
		zipFiles(srcfile, zipfile);
	}
	
	public void createTxtAndZipFiles(String zipName, int size) throws IOException {
		IUiAuto.printLog("Helper--Create Txt and zip file");
		File f1 = new File("/sdcard/" + "testFile.txt");
		File f2 = new File(Constant.RES_FILE_PATH + "test.mp3");
		writeSDFile(f1.toString(), size);

		File[] srcfile = { f1, f2 };

		File zipfile = new File("/sdcard/" + zipName);
		if (zipfile.exists()) {
			zipfile.delete();
		}
		zipFiles(srcfile, zipfile);
	}

	public void registerUiWatchers_SecurityAlert() {
		IUiAuto.getUiDevice().registerWatcher("SecurityAlertWatcher", new UiWatcher() {
			public boolean checkForCondition() {
				if (IUiAuto.isTextExist(Constant.TXT_SECURITY_ALERT_ACCEPT)) {
					IUiAuto.printLog("Watcher--Security Alert appear");
					try {
						IUiAuto.clickText(Constant.TXT_SECURITY_ALERT_ACCEPT, true);
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					}
					return true;
				} else {
					return false;
				}
			}
		});
	}

	public void removieUiWatchers_SecurityAlert() {
		IUiAuto.getUiDevice().removeWatcher("SecurityAlertWatcher");
	}

	public void registerUiWatchers_OK() {
		IUiAuto.getUiDevice().registerWatcher("OKWatcher", new UiWatcher() {
			public boolean checkForCondition() {
				if (IUiAuto.isTextExist(Constant.TXT_SETTINGS_CRASH_TITLE)) {
					IUiAuto.printLog("Watcher--Settings crash appear");
					try {
						IUiAuto.clickText(Constant.TXT_SETTINGS_CRASH_COMFIRM, true);
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					}
					return true;
				} else {
					return false;
				}
			}
		});
	}

	public void removieUiWatchers_OK() {
		IUiAuto.getUiDevice().removeWatcher("OKWatcher");
	}

	public void registerUiWatchers_Crash() {
		IUiAuto.getUiDevice().registerWatcher("CrashWatcher", new UiWatcher() {
			public boolean checkForCondition() {
				if (IUiAuto.isTextExist(Constant.TXT_LANXIN_CRASH_TITLE)) {
					try {
						IUiAuto.clickText(Constant.TXT_SETTINGS_CRASH_COMFIRM, true);
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					}
					System.err.println("****Lanxin crash appear!!!****");
					ATCUiAutoTestCase.assertTrue("****Lanxin crash appear!!!****", false);
					return true;
				} else {
					return false;
				}
			}
		});
	}

	public void removieUiWatchers_Crash() {
		IUiAuto.getUiDevice().removeWatcher("CrashWatcher");
	}

	public void registerUiWatchers_UpdateNotify() {
		IUiAuto.getUiDevice().registerWatcher("UpdateWatcher", new UiWatcher() {
			public boolean checkForCondition() {
				if (IUiAuto.isTextExist(Constant.TXT_NOTIFY_UPDATE_LATER)) {
					try {
						IUiAuto.clickText(Constant.TXT_NOTIFY_UPDATE_LATER, true);
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					}
					System.err.println("****Lanxin Update Notify appear!!!****");
					return true;
				} else {
					return false;
				}
			}
		});
	}

	public void removieUiWatchers_UpdateNotify() {
		IUiAuto.getUiDevice().removeWatcher("UpdateWatcher");
	}
	
	public void registerUiWatchers_Allow() {
		IUiAuto.getUiDevice().registerWatcher("AllowWatcher", new UiWatcher() {
			public boolean checkForCondition() {
				if (IUiAuto.isTextExist(Constant.TXT_SECURITY_ALERT_ACCEPT)) {
					try {
						IUiAuto.clickText(Constant.TXT_SECURITY_ALERT_ACCEPT, true);
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					}
					System.err.println("****Lanxin Update Notify appear!!!****");
					return true;
				} else {
					return false;
				}
			}
		});
	}

	public void removieUiWatchers_Allow() {
		IUiAuto.getUiDevice().removeWatcher("AllowWatcher");
	}

	public void registerUiWatchers_IKnow() {
		IUiAuto.getUiDevice().registerWatcher("IKnowWatcher", new UiWatcher() {
			public boolean checkForCondition() {
				if (IUiAuto.isTextExist(Constant.TXT_I_KNOW)) {
					try {
						IUiAuto.clickText(Constant.TXT_I_KNOW, true);
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					}
					System.err.println("****I Know Notify appear!!!****");
					return true;
				} else {
					return false;
				}
			}
		});
	}

	public void removieUiWatchers_IKnow() {
		IUiAuto.getUiDevice().removeWatcher("IKnowWatcher");
	}

	public void registerUiWatchers_Wait() {
		IUiAuto.getUiDevice().registerWatcher("WaitWatcher", new UiWatcher() {
			public boolean checkForCondition() {
				if (IUiAuto.isTextExist(Constant.TXT_NOTIFY_WAIT)) {
					IUiAuto.sleep(2000);
					System.err.println("****Wait Notify appear!!!****");
					return true;
				} else {
					return false;
				}
			}
		});
	}

	public void removieUiWatchers_Wait() {
		IUiAuto.getUiDevice().removeWatcher("WaitWatcher");
	}

	public void registerUiWatchers_NoNet() {
		IUiAuto.getUiDevice().registerWatcher("NoNetWatcher", new UiWatcher() {
			public boolean checkForCondition() {
				if (IUiAuto.isTextExist(Constant.TXT_NONET_TITLE)) {
					try {
						IUiAuto.clickText(Constant.TXT_NONET_RETRY, true);
						System.err.println("****No net Notify appear!!!****");
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					}
					return true;
				} else {
					return false;
				}
			}
		});
	}

	public void removieUiWatchers_NoNet() {
		IUiAuto.getUiDevice().removeWatcher("NoNetWatcher");
	}
	
	public void registerUiWatchers() {
		registerUiWatchers_OK();
		registerUiWatchers_Allow();
		registerUiWatchers_Wait();
		registerUiWatchers_Crash();
		registerUiWatchers_IKnow();
		registerUiWatchers_NoNet();
		registerUiWatchers_SecurityAlert();
		registerUiWatchers_UpdateNotify();
	}
	
	public void removeUiWatchers() {
		removieUiWatchers_OK();
		removieUiWatchers_Allow();
		removieUiWatchers_Wait();
		removieUiWatchers_Crash();
		removieUiWatchers_IKnow();
		removieUiWatchers_NoNet();
		removieUiWatchers_SecurityAlert();
		removieUiWatchers_UpdateNotify();
	}

	public String createPrivateChatBySelectContactInContacts(String phoneNum, String password)
			throws UiObjectNotFoundException {
		IUiAuto.printLog("Helper--" + ATCUiAutoTestCase.getCurrentMethodName());
		String chatName = "";

		IUiAuto.printLog("step1=Start Lanxin App");
		startLanxinApp(phoneNum, password);

		ATCUiAutoTestCase.assertTrue("Ui Element not exist: ID_TOOLBAR_MSG",
				IUiAuto.waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		IUiAuto.printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		IUiAuto.clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		ATCUiAutoTestCase
				.assertTrue("Click Address failed.", IUiAuto.waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		IUiAuto.printLog("step3=Click " + Constant.TXT_ADDRESS_CONTACT);
		IUiAuto.clickText(Constant.TXT_ADDRESS_CONTACT, true);
		ATCUiAutoTestCase.assertTrue("Click Address failed.",
				IUiAuto.waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 3000));

		IUiAuto.printLog("step4=Select one contact");

		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_NAME));

		for (int i = 0; i < count; i++) {

			UiObject nameObj = list.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_NAME).instance(i));
			if (nameObj.exists()) {
				chatName = nameObj.getText();
				IUiAuto.printLog("chatName=" + chatName);
				ATCUiAutoTestCase.assertTrue("Contact name is empty", chatName != null && !chatName.equals(""));
				nameObj.click();
				if (IUiAuto.waitForTextExists(Constant.TXT_NAMECARD_SEND_LINXIN, 3000)) {
					IUiAuto.printLog("step5=Click " + Constant.TXT_NAMECARD_SEND_LINXIN);
					IUiAuto.clickText(Constant.TXT_NAMECARD_SEND_LINXIN, true);
					ATCUiAutoTestCase.assertTrue("Enter Private chat failed.",
							IUiAuto.waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));
					break;
				} else {
					IUiAuto.printLog("The text not exist: " + Constant.TXT_NAMECARD_SEND_LINXIN);
					IUiAuto.clickText(Constant.ID_BACK, true);
					continue;
				}
			} else {
				ATCUiAutoTestCase.assertTrue("Contact name not exist", false);
			}
		}

		sendMsg_Text();

		backToHome();
		shutDownLanxinApp();
		return chatName;
	}

	public String sendMsg_Text() throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click input et and input some text");
		Date d = new Date();
		String longtime = String.valueOf(d.getTime());
		IUiAuto.clickResourceId(Constant.ID_CHAT_MSG_INPUT_ET);
		IUiAuto.waitForWindowUpdate(2000);
		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input text " + longtime, true);

		IUiAuto.printLog("step7=Click the send button");
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		ATCUiAutoTestCase.assertTrue("Send the msg failed.", IUiAuto.waitForTextContainsExists(longtime, 3000));
		return longtime;
	}

	/**
	 * send msg by text
	 * 
	 * @param content
	 *            for input
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public String sendMsg_Text(String content) throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click input et and input some text");
		String longtime = content;
		IUiAuto.clickResourceId(Constant.ID_CHAT_MSG_INPUT_ET);
		IUiAuto.waitForWindowUpdate(2000);
		ShellUtils.execCommand("input keyevent 20", false);
		ShellUtils.execCommand("input keyevent 20", false);
		ShellUtils.execCommand("input keyevent 20", false);
		ShellUtils.execCommand("input text " + longtime, false);

		IUiAuto.printLog("step7=Click the send button");
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		ATCUiAutoTestCase.assertTrue("Send the msg failed.", IUiAuto.waitForTextContainsExists(longtime, 3000));
		return longtime;
	}

	/**
	 * send Msg by voice
	 * 
	 * @param time
	 *            of voice (ms)
	 * @throws UiObjectNotFoundException
	 */
	public void sendMsg_Voice(int t) throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().resourceId(Constant.ID_CHAT_MSG_CONTENT_ET));
		Rect b = obj.getBounds();
		IUiAuto.printLog("step6=Click Voice icon");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MSG_SEND_IV);
		IUiAuto.waitForWindowUpdate(3000);
		IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_VOICE_PRESS, 8000);

		IUiAuto.printLog("step7=Long click hold to talk");
		IUiAuto.swipe(b.centerX(), b.centerY(), b.centerX(), b.centerY(), t / 25);
		ATCUiAutoTestCase.assertTrue("Send the msg failed.",
				IUiAuto.waitForResourceId(Constant.ID_CHAT_MSG_VOICE, 3000));
	}

	/**
	 * send Msg by voice but cancel
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void sendMsg_Voice_Cancel() throws UiObjectNotFoundException {
		UiObject obj = new UiObject(new UiSelector().resourceId(Constant.ID_CHAT_MSG_CONTENT_ET));
		Rect b = obj.getBounds();
		IUiAuto.printLog("step6=Click Voice icon");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MSG_SEND_IV);
		IUiAuto.waitForWindowUpdate(3000);
		IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_VOICE_PRESS, 8000);

		IUiAuto.printLog("step7=Long click hold to talk");
		IUiAuto.swipe(b.centerX(), b.centerY(), b.centerX(), b.centerY() - 500, 300);
		ATCUiAutoTestCase.assertTrue("Send the msg failed.",
				!IUiAuto.waitForResourceId(Constant.ID_CHAT_MSG_VOICE, 3000));
	}

	public void sendMsg_Photo() throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_IMAGE, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_IMAGE);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_IMAGE, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_CHAT_TOOL_PHOTO,
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_PHOTO, 3000));

		IUiAuto.printLog("step8=Select " + Constant.TXT_CHAT_TOOL_PHOTO);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_PHOTO, true);
		IUiAuto.waitForWindowUpdate(3000);
		
		System.out.println("x=" + IUiAuto.getUiDevice().getDisplayWidth());
		System.out.println("y=" + IUiAuto.getUiDevice().getDisplayHeight());
		
		if (IUiAuto.getUiDevice().getProductName().equals("P898S10")) {
			IUiAuto.printLog("step9=Click the shutter button");
			IUiAuto.clickResourceId(Constant.ID_CAMERA_SHUTTER);
			ATCUiAutoTestCase.assertTrue("Click the shutter failed.",
					IUiAuto.waitForResourceId(Constant.ID_CAMERA_OK, 5000));

			IUiAuto.printLog("step10=Click the shutter ok button");
			IUiAuto.clickResourceId(Constant.ID_CAMERA_OK);
			ATCUiAutoTestCase.assertTrue("Click the OK button failed.",
					IUiAuto.waitForResourceId(Constant.ID_BUTTON_OK, 5000));
		} else if (IUiAuto.getUiDevice().getProductName().equals("CP8681_A01")) {
			IUiAuto.printLog("step9=Click the shutter button");
			IUiAuto.clickResourceId("com.android.camera:id/camera_shutter_middle_button");
			ATCUiAutoTestCase.assertTrue("Click the shutter failed.",
					IUiAuto.waitForResourceId("com.android.camera:id/save_btn", 5000));

			IUiAuto.printLog("step10=Click the shutter ok button");
			IUiAuto.clickResourceId("com.android.camera:id/save_btn");
			ATCUiAutoTestCase.assertTrue("Click the OK button failed.",
					IUiAuto.waitForResourceId(Constant.ID_BUTTON_OK, 5000));
		} else if (IUiAuto.getUiDevice().getProductName().equals("8675-FHD")) {
			IUiAuto.printLog("step9=Click the shutter button");
			IUiAuto.clickResourceId(Constant.ID_CAMERA_SHUTTER_ANDROID);
			ATCUiAutoTestCase.assertTrue("Click the shutter failed.",
					IUiAuto.waitForResourceId(Constant.ID_CAMERA_OK_ANDROID, 5000));

			IUiAuto.printLog("step10=Click the shutter ok button");
			IUiAuto.clickResourceId(Constant.ID_CAMERA_OK_ANDROID);
			ATCUiAutoTestCase.assertTrue("Click the OK button failed.",
					IUiAuto.waitForResourceId(Constant.ID_BUTTON_OK, 5000));
		} else if (IUiAuto.getUiDevice().getProductName().equals("Che1-CL20")
				|| IUiAuto.getUiDevice().getProductName().equals("RIO-AL00")) {
			IUiAuto.printLog("step9=Click the shutter button");
			
			if (IUiAuto.waitForTextExists(Constant.TXT_SECURITY_ALERT_ACCEPT, 5000)) {
				IUiAuto.clickText(Constant.TXT_SECURITY_ALERT_ACCEPT, true);
			}
			
			IUiAuto.clickResourceId(Constant.ID_CAMERA_SHUTTER_HUAWEI);
			ATCUiAutoTestCase.assertTrue("Click the shutter failed.",
					IUiAuto.waitForResourceId(Constant.ID_CAMERA_OK_HUAWEI, 5000));

			IUiAuto.printLog("step10=Click the shutter ok button");
			IUiAuto.clickResourceId(Constant.ID_CAMERA_OK_HUAWEI);
			ATCUiAutoTestCase.assertTrue("Click the OK button failed.",
					IUiAuto.waitForResourceId(Constant.ID_BUTTON_OK, 5000));
		} else if (IUiAuto.getUiDevice().getProductName().equals("h3gduoszn")) {
			IUiAuto.printLog("step9=Click the shutter button");
			
			for (int i = 0; i < 8; i++) {
				IUiAuto.clickPoint(1800, 540);
				if (IUiAuto.waitForTextExists(Constant.TXT_SAVE, 5000)) {
					break;
				}
			}
			ATCUiAutoTestCase.assertTrue("Click the shutter failed.",
					IUiAuto.waitForTextExists(Constant.TXT_SAVE, 8000));
			
			try {
				IUiAuto.getUiDevice().setOrientationLeft();
				IUiAuto.waitForWindowUpdate(3000);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

			IUiAuto.printLog("step10=Click the shutter ok button");
			IUiAuto.clickText(Constant.TXT_SAVE, true);
			ATCUiAutoTestCase.assertTrue("Click the OK button failed.",
					IUiAuto.waitForResourceId(Constant.ID_BUTTON_OK, 10000));
		}

		IUiAuto.clickResourceId(Constant.ID_CHAT_SETTINGS);
		ATCUiAutoTestCase.assertTrue("Click the settings failed.",
				IUiAuto.waitForTextExists(Constant.TXT_SEND_ORIGINAL_IMAGE, 5000));

		IUiAuto.printLog("step11=Click " + Constant.TXT_SEND_ORIGINAL_IMAGE);
		IUiAuto.clickText(Constant.TXT_SEND_ORIGINAL_IMAGE, true);
		ATCUiAutoTestCase.assertTrue("Back to the create group failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_SEND, 5000));

		IUiAuto.printLog("step12=Click the send button");
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		ATCUiAutoTestCase.assertTrue("Send the photo failed.",
				IUiAuto.waitForResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER, 8000));
		
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
		int children = 0;
		for (int i = 0; i < 15; i++) {
			children = list.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER));
			if (children > 1) {
				break;
			} else {
				IUiAuto.sleep(1000);
			}
		}
	}

	public void sendMsg_Picture() throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_IMAGE, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_IMAGE);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_IMAGE, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_CHAT_TOOL_PICTURE_SELECT,
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_PICTURE_SELECT, 3000));

		IUiAuto.printLog("step8=Select " + Constant.TXT_CHAT_TOOL_PICTURE_SELECT);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_PICTURE_SELECT, true);
		ATCUiAutoTestCase.assertTrue("Select Picture to enter Album failed.",
				IUiAuto.waitForTextExists(Constant.TXT_PICTURE, 3000));

		IUiAuto.printLog("step9=Select one picture");
		IUiAuto.clickResourceId(Constant.ID_CHECKBOX);
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		ATCUiAutoTestCase.assertTrue("Back to the create group failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_SEND, 5000));

		IUiAuto.printLog("step12=Click the send button");
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		ATCUiAutoTestCase.assertTrue("Send the picture failed.",
				IUiAuto.waitForResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER, 8000));
	}

	public void sendMsg_Photograph(int t) throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_VIDEO, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_VIDEO);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_VIDEO, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_CHAT_TOOL_PHOTOGRAPH,
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_PHOTOGRAPH, 3000));

		IUiAuto.printLog("step8=Select " + Constant.TXT_CHAT_TOOL_PHOTOGRAPH);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_PHOTOGRAPH, true);
		ATCUiAutoTestCase.assertTrue("Select video to enter camera failed.",
				IUiAuto.waitForPkgExist("com.zte.camera", 5000));

		IUiAuto.printLog("step9=Click the shutter button");
		IUiAuto.clickResourceId(Constant.ID_CAMERA_SHUTTER);
		ATCUiAutoTestCase.assertTrue("Click the shutter failed.",
				IUiAuto.waitForResourceId(Constant.ID_CAMERA_VIDEO_STOP, 5000));

		IUiAuto.sleep(t);

		IUiAuto.printLog("step10=Click the vidoe stop button");
		IUiAuto.clickResourceId(Constant.ID_CAMERA_VIDEO_STOP);
		ATCUiAutoTestCase.assertTrue("Click the vidoe stop button.",
				IUiAuto.waitForResourceId(Constant.ID_CAMERA_SHUTTER, 5000));

		IUiAuto.printLog("step10=Click the ok button");
		IUiAuto.clickResourceId(Constant.ID_CAMERA_SHUTTER);
		ATCUiAutoTestCase.assertTrue("Click the OK button failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_SEND, 5000));

		IUiAuto.printLog("step11=Click the send button");
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		ATCUiAutoTestCase.assertTrue("Send the photo failed.",
				IUiAuto.waitForResourceId(Constant.ID_CHAT_MSG_INPUT_VIDEO, 8000));
	}

	public void sendMsg_Video() throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_VIDEO, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_VIDEO);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_VIDEO, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_CHAT_TOOL_VOIDE_SELECT,
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_VOIDE_SELECT, 3000));

		IUiAuto.printLog("step8=Select " + Constant.TXT_CHAT_TOOL_VOIDE_SELECT);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_VOIDE_SELECT, true);
		ATCUiAutoTestCase.assertTrue("Select video to enter selection failed.",
				IUiAuto.waitForTextExists(Constant.TXT_SELECT_ITEM, 3000));

		IUiAuto.printLog("step9=Select one video");
		while (IUiAuto.waitForTextExists(Constant.TXT_SELECT_ITEM, 3000)) {
			IUiAuto.clickPoint(200, 350);
			IUiAuto.waitForWindowUpdate(2000);
		}

		IUiAuto.printLog("step11=Click the send button");
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		ATCUiAutoTestCase.assertTrue("Send the photo failed.",
				IUiAuto.waitForResourceId(Constant.ID_CHAT_MSG_INPUT_VIDEO, 8000));
	}

	public String sendMsg_Document() throws UiObjectNotFoundException, IOException {
		String zipName = "test.zip";
		createTxtAndZipFiles(zipName);

		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_DOCUMENT, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_DOCUMENT);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_DOCUMENT, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_TOOL_DOCUMENT_LOCATION,
				IUiAuto.waitForTextExists(Constant.TXT_TOOL_DOCUMENT_LOCATION, 3000));

		IUiAuto.printLog("step8=Click " + Constant.TXT_TOOL_DOCUMENT_LOCATION);
		IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_LOCATION, true);
		ATCUiAutoTestCase.assertTrue("The text not exsit: " + Constant.TXT_TOOL_DOCUMENT_METHOD,
				IUiAuto.waitForTextExists(Constant.TXT_TOOL_DOCUMENT_METHOD, 3000));

		IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD, true);
		IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD_LOCAL, true);
		IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD_PHONE, true);
		UiObject fileObj = IUiAuto.scrollToFindText(zipName, "Vertical", 9);
		if (fileObj.exists()) {
			IUiAuto.printLog("step9=Select the testZip");
			fileObj.click();
			ATCUiAutoTestCase.assertTrue("Click the testZip failed.",
					IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_SEND, 3000));

			IUiAuto.printLog("step10=Click send button");
			IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
			ATCUiAutoTestCase.assertTrue("Click the send button failed.",
					IUiAuto.waitForResourceId(Constant.ID_CHAT_MSG_STATE, 4000));

			IUiAuto.sleep(3000);

			UiCollection msgList = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
			int statusCount = msgList.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_STATE));

			String iconArr[] = IUiAuto.getConfigProperty("send_status").split(",");

			int x1 = Integer.parseInt(iconArr[0]);
			int y1 = Integer.parseInt(iconArr[1]);
			int x2 = Integer.parseInt(iconArr[2]);
			int y2 = Integer.parseInt(iconArr[3]);
			Rect bound;
			double diff = 1000;

			IUiAuto.printLog("step11=Verify the send status");
			for (int j = 0; j < 40; j++) {
				statusCount = msgList.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_STATE));
				bound = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHAT_MSG_STATE, statusCount - 1)
						.getBounds();
				diff = IUiAuto.getScreenDiffWithReference(Constant.RES_FILE_PATH + "send_ok", x1, y1, x2, y2,
						bound.left, bound.top, bound.right, bound.bottom);
				IUiAuto.printLog("bound=" + bound);
				IUiAuto.printLog("ok diff=" + diff);
				if (diff > 0.1) {
					IUiAuto.sleep(3 * 1000);
				} else {
					IUiAuto.printLog("The document send ok");
					break;
				}
			}

			ATCUiAutoTestCase.assertTrue("Send the document time out", diff < 0.1);
			statusCount = msgList.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_STATE));
			bound = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHAT_MSG_STATE, statusCount - 1).getBounds();
			diff = IUiAuto.getScreenDiffWithReference(Constant.RES_FILE_PATH + "send_error", x1, y1, x2, y2,
					bound.left, bound.top, bound.right, bound.bottom);
			IUiAuto.printLog("bound=" + bound);
			IUiAuto.printLog("error diff=" + diff);
			if (diff < 0.1) {
				ATCUiAutoTestCase.assertTrue("The document send failed", true);
			}

		} else {
			ATCUiAutoTestCase.assertTrue("Cannot find the testZip", false);
		}
		return zipName;
	}
	
	public String uploadDocument_PrivateChat() throws UiObjectNotFoundException, IOException {
		String zipName = "test.zip";

		while (IUiAuto.waitForTextExists(zipName, 2000)) {
			IUiAuto.longClickTextBySwipe(zipName);
			ATCUiAutoTestCase.assertTrue("Long click the msg failed.",
					IUiAuto.waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			IUiAuto.clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			IUiAuto.clickText(Constant.TXT_CONFIRM, true);
		}

		createTxtAndZipFiles(zipName, 1);

		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_DOCUMENT, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_DOCUMENT);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_DOCUMENT, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_TOOL_DOCUMENT_LOCATION,
				IUiAuto.waitForTextExists(Constant.TXT_TOOL_DOCUMENT_LOCATION, 3000));

		IUiAuto.printLog("step8=Click " + Constant.TXT_TOOL_DOCUMENT_LOCATION);
		IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_LOCATION, true);
		
		IUiAuto.printLog("productName=" + IUiAuto.getUiDevice().getProductName());
		
		if (IUiAuto.getUiDevice().getProductName().equals("h3gduoszn")) {
			IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_FILE_CHECK, 0).click();
			IUiAuto.waitForWindowUpdate(2000);
			IUiAuto.clickText(Constant.TXT_COMPLETE, true);
			
			ATCUiAutoTestCase.assertTrue("Click the testZip failed.",
					IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_SEND, 3000));

			IUiAuto.printLog("step10=Click send button");
			IUiAuto.getObjectOnText(Constant.TXT_CHAT_TOOL_SEND).click();
			
			UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
			int children = 0;

			long count = 0;
			for (int i = 0; i < 1000; i++) {
				children = list.getChildCount(new UiSelector().text(zipName));
				if (children==2) {
					break;
				} else {
					IUiAuto.sleep(500);
					count = count + 500;
				}
			}
			IUiAuto.printLog("TimeCount="+count);
			ATCUiAutoTestCase.assertTrue("Upload the document time out", count < 1000 * 500 );
		} else {
			if (IUiAuto.getUiDevice().getProductName().equals("P898S10")) {
				IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD, true);
				IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD_LOCAL, true);
				IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD_PHONE, true);
			} else {
				IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_EXPLORER, true);
				IUiAuto.clickText(Constant.TXT_SDCARD, true);
			}

			UiObject fileObj = IUiAuto.scrollToFindText(zipName, "Vertical", 15);
			if (fileObj.exists()) {
				IUiAuto.printLog("step9=Select the testZip");
				for (int i = 0; i < 3; i++) {
					fileObj.click();
					if (IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_SEND, 3000)) {
						break;
					}
				}
				ATCUiAutoTestCase.assertTrue("Click the testZip failed.",
						IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_SEND, 3000));
				removeUiWatchers();

				IUiAuto.printLog("step10=Click send button");
				IUiAuto.getObjectOnText(Constant.TXT_CHAT_TOOL_SEND).click();
				
				UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
				int children = 0;

				long count = 0;
				for (int i = 0; i < 1000; i++) {
					children = list.getChildCount(new UiSelector().text(zipName));
					if (children==2) {
						break;
					} else {
						IUiAuto.sleep(500);
						count = count + 500;
					}
				}
				IUiAuto.printLog("TimeCount="+count);
				ATCUiAutoTestCase.assertTrue("Upload the document time out", count < 1000 * 500 );
			} else {
				ATCUiAutoTestCase.assertTrue("Cannot find the testZip", false);
			}
		}
		
		return zipName;
	}

	public String sendMsg_Document_Small() throws UiObjectNotFoundException, IOException {
		String fileName = "testFile.txt";
		File f1 = new File("/sdcard/" + fileName);
		writeSDFile(f1.toString(), 1);

		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_DOCUMENT, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_DOCUMENT);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_DOCUMENT, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_TOOL_DOCUMENT_LOCATION,
				IUiAuto.waitForTextExists(Constant.TXT_TOOL_DOCUMENT_LOCATION, 3000));

		IUiAuto.printLog("step8=Click " + Constant.TXT_TOOL_DOCUMENT_LOCATION);
		IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_LOCATION, true);
		ATCUiAutoTestCase.assertTrue("The text not exsit: " + Constant.TXT_TOOL_DOCUMENT_METHOD,
				IUiAuto.waitForTextExists(Constant.TXT_TOOL_DOCUMENT_METHOD, 3000));

		IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD, true);
		IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD_LOCAL, true);
		IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD_PHONE, true);
		UiObject fileObj = IUiAuto.scrollToFindText(fileName, "Vertical", 9);
		if (fileObj.exists()) {
			IUiAuto.printLog("step9=Select the testZip");
			fileObj.click();
			ATCUiAutoTestCase.assertTrue("Click the testZip failed.",
					IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_SEND, 3000));

			IUiAuto.printLog("step10=Click send button");
			IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
			ATCUiAutoTestCase.assertTrue("Click the send button failed.",
					IUiAuto.waitForResourceId(Constant.ID_CHAT_MSG_STATE, 4000));

			IUiAuto.sleep(10000);

			UiCollection msgList = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
			int statusCount = msgList.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_STATE));

			String iconArr[] = IUiAuto.getConfigProperty("samll_status").split(",");

			int x1 = Integer.parseInt(iconArr[0]);
			int y1 = Integer.parseInt(iconArr[1]);
			int x2 = Integer.parseInt(iconArr[2]);
			int y2 = Integer.parseInt(iconArr[3]);
			Rect bound;
			double diff = 1000;

			IUiAuto.printLog("step11=Verify the send status");
			for (int j = 0; j < 40; j++) {
				statusCount = msgList.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_STATE));
				bound = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHAT_MSG_STATE, statusCount - 1)
						.getBounds();
				diff = IUiAuto.getScreenDiffWithReference(Constant.RES_FILE_PATH + "send_small", x1, y1, x2, y2,
						bound.left, bound.top, bound.right, bound.bottom);
				IUiAuto.printLog("bound=" + bound);
				IUiAuto.printLog("ok diff=" + diff);
				if (diff > 0.1) {
					IUiAuto.sleep(3 * 1000);
				} else {
					IUiAuto.printLog("The document send ok");
					break;
				}
			}

			ATCUiAutoTestCase.assertTrue("Send the document time out", diff < 0.1);
			statusCount = msgList.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_STATE));
			bound = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHAT_MSG_STATE, statusCount - 1).getBounds();
			diff = IUiAuto.getScreenDiffWithReference(Constant.RES_FILE_PATH + "send_error", x1, y1, x2, y2,
					bound.left, bound.top, bound.right, bound.bottom);
			IUiAuto.printLog("bound=" + bound);
			IUiAuto.printLog("error diff=" + diff);
			if (diff < 0.1) {
				ATCUiAutoTestCase.assertTrue("The document send failed", true);
			}

		} else {
			ATCUiAutoTestCase.assertTrue("Cannot find the testZip", false);
		}
		return fileName;
	}

	public void sendMsg_Location() throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_LOCATION, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_LOCATION);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_LOCATION, true);
		IUiAuto.waitForWindowUpdate(5000);
		if (IUiAuto.waitForTextExists(Constant.TXT_LOCATION_SEND, 3000)) {
			IUiAuto.clickText(Constant.TXT_LOCATION_SEND, true);
		}
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_LOCATION_SELECT,
				IUiAuto.waitForTextExists(Constant.TXT_LOCATION_SELECT, 5000));

		IUiAuto.printLog("step8=Select " + Constant.TXT_VERIFY);
		IUiAuto.clickText(Constant.TXT_VERIFY, true);
		ATCUiAutoTestCase.assertTrue("Click the confirm failed.",
				IUiAuto.waitForTextExists(Constant.TXT_LOCATION_NOTIFY, 3000));

		IUiAuto.printLog("step9=Click yes");
		IUiAuto.clickText(Constant.TXT_YES, true);
		ATCUiAutoTestCase.assertTrue("Click yes failed.",
				IUiAuto.waitForResourceId(Constant.ID_CHAT_MSG_INPUT_ET, 5000));

		IUiAuto.printLog("step10=Click the send button");
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		ATCUiAutoTestCase.assertTrue("Send the location failed.",
				IUiAuto.waitForTextContainsExists(Constant.TXT_LOCATION_INFO, 8000));
	}

	public void sendMsg_ReportLocation(String title) throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_LOCATION, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_LOCATION);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_LOCATION, true);
		IUiAuto.waitForWindowUpdate(5000);
		if (IUiAuto.waitForTextExists(Constant.TXT_LOCATION_REPORT, 3000)) {
			IUiAuto.clickText(Constant.TXT_LOCATION_REPORT, true);
		}
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_LOCATION_REPORT,
				IUiAuto.waitForTextExists(Constant.TXT_LOCATION_REPORT, 5000));

		IUiAuto.printLog("step8=Select " + Constant.TXT_LOCATION_REPORT_TITLE);
		IUiAuto.clickText(Constant.TXT_LOCATION_REPORT_TITLE, true);
		ShellUtils.execCommand("input text " + title, true);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Report location failed.",
				IUiAuto.waitForTextExists(Constant.TXT_LOCATION_REPORT, 3000));
	}

	public void sendMsg_ShareLocation() throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_LOCATION, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_LOCATION);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_LOCATION, true);
		IUiAuto.waitForWindowUpdate(5000);
		if (IUiAuto.waitForTextExists(Constant.TXT_LOCATION_SHARE, 3000)) {
			IUiAuto.clickText(Constant.TXT_LOCATION_SHARE, true);
		}
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_LOCATION_SHARE_TIP,
				IUiAuto.waitForTextExists(Constant.TXT_LOCATION_SHARE_TIP, 5000));

		IUiAuto.printLog("step8=Select " + Constant.TXT_EXIT);
		IUiAuto.clickText(Constant.TXT_EXIT, true);
		IUiAuto.clickText(Constant.TXT_EXIT, true);
	}

	/**
	 * send msg by link
	 * 
	 * @param link
	 * @throws UiObjectNotFoundException
	 */
	public void sendMsg_Link(String link) throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_LINK, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_LINK);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_LINK, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_LINK_TITLE,
				IUiAuto.waitForTextExists(Constant.TXT_LINK_TITLE, 5000));

		if (IUiAuto.waitForResourceId(Constant.ID_DELETE, 3000)) {
			IUiAuto.clickResourceId(Constant.ID_DELETE);
		}

		IUiAuto.printLog("step8=Select " + Constant.TXT_LINK_INPUT);
		IUiAuto.clickText(Constant.TXT_LINK_INPUT, true);
		ShellUtils.execCommand("input text " + link, true);
		IUiAuto.pressKey("back");

		IUiAuto.printLog("step9=Select " + Constant.TXT_LINK_REVIEW);
		IUiAuto.clickText(Constant.TXT_LINK_REVIEW, true);

		IUiAuto.printLog("step10=Click " + Constant.TXT_CHAT_TOOL_SEND);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		ATCUiAutoTestCase.assertTrue("Click yes failed.",
				IUiAuto.waitForTextContainsExists(Constant.TXT_LINK_BAIDU, 15000));
	}

	/**
	 * send msg by group name card
	 * 
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public String sendMsg_GroupCard(String chatName) throws UiObjectNotFoundException {
		IUiAuto.printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		IUiAuto.clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		IUiAuto.waitForWindowUpdate(5000);
		ATCUiAutoTestCase.assertTrue("Click Interest failed.",
				IUiAuto.waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));

		IUiAuto.printLog("step3=Click " + Constant.TXT_INTEREST_CHAT_GROUP);
		IUiAuto.clickText(Constant.TXT_INTEREST_CHAT_GROUP, true);
		ATCUiAutoTestCase.assertTrue("Title name is not: " + Constant.TXT_INTEREST_CHAT_GROUP, IUiAuto
				.getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_INTEREST_CHAT_GROUP));

		String groupName = IUiAuto.getTextOnResourceIdAndInstance(Constant.ID_INTEREST_CG_NAME, 2000, 0);
		IUiAuto.printLog("step4=Click a group");
		IUiAuto.clickText(groupName, true);
		ATCUiAutoTestCase.assertTrue("Ui element not exist: Share icon",
				IUiAuto.waitForResourceId(Constant.ID_GROUP_INFO_SHARE, 2000));

		IUiAuto.printLog("step5=Click the share icon");
		IUiAuto.clickResourceId(Constant.ID_GROUP_INFO_SHARE);
		ATCUiAutoTestCase.assertTrue("Click the share icon failed",
				IUiAuto.waitForTextExists(Constant.TXT_RELAY_GROUP_NAMECARD, 3000));

		IUiAuto.printLog("step6=Click share by name card");
		IUiAuto.clickText(Constant.TXT_RELAY_GROUP_NAMECARD, true);
		ATCUiAutoTestCase.assertTrue("Click share by name card failed",
				IUiAuto.waitForTextExists(Constant.TXT_RELAY_TITLE, 3000));

		IUiAuto.printLog("step6=Click one current chat");
		IUiAuto.clickTextContains(chatName, true);
		ATCUiAutoTestCase.assertTrue("Clickt the current chat failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CONFIRM, 3000));

		IUiAuto.printLog("step7=Click the confirm");
		IUiAuto.clickText(Constant.TXT_CONFIRM, true);
		IUiAuto.waitForWindowUpdate(3000);
		IUiAuto.pressKey("back");
		IUiAuto.pressKey("back");
		return groupName;
	}

	/**
	 * send msg by emotion
	 * 
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public String sendMsg_Emotion() throws UiObjectNotFoundException {
		IUiAuto.printLog("step4=Click the emotion icon");
		IUiAuto.clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		ATCUiAutoTestCase.assertTrue("Click the emotion failed",
				IUiAuto.waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));

		IUiAuto.printLog("step5=Click some emotion");
		for (int i = 0; i < 3; i++) {
			IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHAT_TOOL_EMOTION_ITEM, i).clickAndWaitForNewWindow();
			IUiAuto.waitForWindowUpdate(1000);
		}
		String emotion = IUiAuto.getObjectOnResourceId(Constant.ID_CHAT_MSG_INPUT_ET).getText();

		IUiAuto.printLog("step6=Click the send button");
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		IUiAuto.waitForWindowUpdate(2000);
		IUiAuto.clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		IUiAuto.pressKey("back");
		IUiAuto.printLog("emotion=" + emotion);
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_VIEW_INFO));
		String info = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHAT_MSG_VIEW_INFO, count - 1).getText();
		ATCUiAutoTestCase.assertTrue("Send the msg failed.", emotion.equals(info));
		return emotion;
	}

	public void createWorkGroupByPlusButton(String phoneNum, String password, String groupName)
			throws UiObjectNotFoundException {
		IUiAuto.printLog("Helper--" + ATCUiAutoTestCase.getCurrentMethodName());

		workGroup_CreateGroup_Member(phoneNum, password);

		IUiAuto.printLog("step7=Click name editview and enter name");
		IUiAuto.clickResourceId(Constant.ID_GROUP_GROUP_NAME_INPUT);
		IUiAuto.enterTextInEditor(groupName, "android.widget.EditText", 0);
		ATCUiAutoTestCase.assertTrue("Enter name failed.", IUiAuto.waitForTextExists(groupName, 3000));

		IUiAuto.printLog("step8=Click Confirm icon");
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		ATCUiAutoTestCase.assertTrue("Double confirm dialog not display",
				IUiAuto.waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		IUiAuto.printLog("step9=Click Confirm");
		IUiAuto.clickText(Constant.TXT_CONFIRM, true);
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Enter chat page failed.", IUiAuto.waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		IUiAuto.printLog("step10=Verify the group name");
		String name = IUiAuto.getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000);
		ATCUiAutoTestCase.assertTrue("The group name" + name + " not match expect", name.contains(groupName));
	}

	public void createGroupChatByPlusButton(String phoneNum, String password, String groupName)
			throws UiObjectNotFoundException {
		IUiAuto.printLog("Helper--" + ATCUiAutoTestCase.getCurrentMethodName());

		groupChat_CreateGroup_Member(phoneNum, password);

		IUiAuto.printLog("step7=Click name editview and enter name");
		IUiAuto.clickResourceId(Constant.ID_GROUP_GROUP_NAME_INPUT);
		IUiAuto.enterTextInEditor(groupName, "android.widget.EditText", 0);
		ATCUiAutoTestCase.assertTrue("Enter name failed.", IUiAuto.waitForTextExists(groupName, 3000));

		IUiAuto.printLog("step8=Click Confirm icon");
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		ATCUiAutoTestCase.assertTrue("Double confirm dialog not display",
				IUiAuto.waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		IUiAuto.printLog("step9=Click Confirm");
		IUiAuto.clickText(Constant.TXT_CONFIRM, true);
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Enter chat page failed.", IUiAuto.waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		IUiAuto.printLog("step10=Verify the group name");
		String name = IUiAuto.getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000);
		ATCUiAutoTestCase.assertTrue("The group name" + name + " not match expect", name.contains(groupName));
	}

	/**
	 * Set the Head icon by photo
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void setHeadIcon_Photo() throws UiObjectNotFoundException {
		IUiAuto.clickText(Constant.TXT_DIALOG_SETHEAD_PHOTO, true);
		ATCUiAutoTestCase.assertTrue("Select photo to enter camera failed.",
				IUiAuto.waitForPkgExist("com.zte.camera", 5000));

		IUiAuto.printLog("step4=Click the shutter button");
		IUiAuto.clickResourceId(Constant.ID_CAMERA_SHUTTER);
		ATCUiAutoTestCase.assertTrue("Click the shutter failed.",
				IUiAuto.waitForResourceId(Constant.ID_CAMERA_OK, 5000));

		IUiAuto.printLog("step5=Click the shutter button");
		IUiAuto.clickResourceId(Constant.ID_CAMERA_OK);
		ATCUiAutoTestCase.assertTrue("Click the OK button failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CONFIRM, 5000));

		IUiAuto.printLog("step6=Click " + Constant.TXT_COMPLETE);
		IUiAuto.clickText(Constant.TXT_CONFIRM, true);
		ATCUiAutoTestCase.assertTrue("Back to the settings failed.",
				IUiAuto.waitForTextContainsExists(Constant.TXT_SETTINGS, 5000));
	}

	public void setHeadIcon_Picture() throws UiObjectNotFoundException {
		IUiAuto.clickText(Constant.TXT_DIALOG_SETHEAD_PICTURE, true);
		ATCUiAutoTestCase.assertTrue("Select picture to enter camera failed.",
				IUiAuto.waitForTextExists(Constant.TXT_SELECT_ITEM, 3000));

		IUiAuto.printLog("step9=Select one picture");
		while (IUiAuto.waitForTextExists(Constant.TXT_SELECT_ITEM, 3000)) {
			IUiAuto.clickPoint(200, 350);
			IUiAuto.waitForWindowUpdate(2000);
		}
		ATCUiAutoTestCase.assertTrue("Select the picture failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CONFIRM, 2000));

		IUiAuto.clickText(Constant.TXT_CONFIRM, true);
		ATCUiAutoTestCase.assertTrue("Back to the settings failed.",
				IUiAuto.waitForTextContainsExists(Constant.TXT_SETTINGS, 8000));
	}

	public void renameGroupName(String newName) throws UiObjectNotFoundException {
		IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_NAME_SET, 0).clickAndWaitForNewWindow();
		ATCUiAutoTestCase.assertTrue("Click set name icon failed.",
				IUiAuto.waitForTextExists(Constant.TXT_SET_GROUPNAME_TITLE, 3000));

		IUiAuto.printLog("step4=set group name");
		IUiAuto.clickResourceId(Constant.ID_WORKGROUP_EDIT_NAME);
		for (int i = 0; i < 20; i++) {
			IUiAuto.pressKeyCode(KeyEvent.KEYCODE_DEL);
		}

		IUiAuto.enterTextInEditor(newName, "android.widget.EditText", 0);
		IUiAuto.clickText(Constant.TXT_SET_GROUPNAME_SAVE, true);
		ATCUiAutoTestCase.assertTrue("Back to the settings failed.",
				IUiAuto.waitForTextExists(Constant.TXT_SETTINGS, 5000));
	}

	public void setGroupSummary(String sum) throws UiObjectNotFoundException {
		IUiAuto.clickResourceId(Constant.ID_WORKGROUP_SETTINGS_GROUP_SUMMARY);
		ATCUiAutoTestCase.assertTrue("Click set summary failed.",
				IUiAuto.waitForTextExists(Constant.TXT_SET_GROUPNAME_TITLE, 3000));

		IUiAuto.printLog("step4=set group summary");
		IUiAuto.clickResourceId(Constant.ID_WORKGROUP_EDIT_SUMMARY);
		for (int i = 0; i < 20; i++) {
			IUiAuto.pressKeyCode(KeyEvent.KEYCODE_DEL);
		}

		IUiAuto.enterTextInEditor(sum, "android.widget.EditText", 1);
		IUiAuto.clickText(Constant.TXT_SET_GROUPNAME_SAVE, true);
		ATCUiAutoTestCase.assertTrue("Back to the settings failed.",
				IUiAuto.waitForTextExists(Constant.TXT_SETTINGS, 5000));
	}

	public void verifyManageMemberUiElement() throws UiObjectNotFoundException {
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_MANAGE_MEMBER_REMOVE,
				IUiAuto.waitForTextExists(Constant.TXT_MANAGE_MEMBER_REMOVE, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_MANAGE_MEMBER_ADD,
				IUiAuto.waitForTextExists(Constant.TXT_MANAGE_MEMBER_ADD, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_MANAGE_MEMBER_COUNT,
				IUiAuto.waitForTextExists(Constant.TXT_MANAGE_MEMBER_COUNT, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_MANAGE_MEMBER_CREATEGROUP,
				IUiAuto.waitForTextExists(Constant.TXT_MANAGE_MEMBER_CREATEGROUP, 2000));

		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Back icon ",
				IUiAuto.waitForResourceId(Constant.ID_BACK, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Search icon ",
				IUiAuto.waitForResourceId(Constant.ID_CHAT_SETTINGS, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Count desc",
				IUiAuto.waitForResourceId(Constant.ID_MANAGE_MEMBER_COUNT_DESC, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Mark icon",
				IUiAuto.waitForResourceId(Constant.ID_MANAGE_MEMBER_MARK, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Index bar",
				IUiAuto.waitForResourceId(Constant.ID_INDEX_BAR, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: List",
				IUiAuto.waitForResourceId(Constant.ID_LIST, 2000));

		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_ITEM));
		for (int i = 0; i < count; i++) {
			UiObject item = list.getChild(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_ITEM).instance(i));
			if (item.exists()) {
				ATCUiAutoTestCase.assertTrue("The head not exist",
						item.getChild(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_HEAD)).exists());
				ATCUiAutoTestCase.assertTrue("The name not exist",
						item.getChild(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_NAME)).exists());
				ATCUiAutoTestCase.assertTrue("The department not exist",
						item.getChild(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_DEPARTMENT)).exists());
			}
		}
	}

	public void verifyManageMemberUiElement_GroupChat() throws UiObjectNotFoundException {
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_MANAGE_MEMBER_REMOVE,
				IUiAuto.waitForTextExists(Constant.TXT_MANAGE_MEMBER_REMOVE, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_MANAGE_MEMBER_ADD,
				IUiAuto.waitForTextExists(Constant.TXT_MANAGE_MEMBER_ADD, 2000));
//		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_MANAGE_MEMBER_COUNT,
//				IUiAuto.waitForTextExists(Constant.TXT_MANAGE_MEMBER_COUNT, 2000));
//		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_MANAGE_MEMBER_CREATEGROUP,
//				IUiAuto.waitForTextExists(Constant.TXT_MANAGE_MEMBER_CREATEGROUP, 2000));

		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Back icon ",
				IUiAuto.waitForResourceId(Constant.ID_BACK, 2000));
//		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Count desc",
//				IUiAuto.waitForResourceId(Constant.ID_MANAGE_MEMBER_COUNT_DESC, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Mark icon",
				IUiAuto.waitForResourceId(Constant.ID_MANAGE_MEMBER_MARK, 2000));
//		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Index bar",
//				IUiAuto.waitForResourceId(Constant.ID_INDEX_BAR, 2000));
//		ATCUiAutoTestCase.assertTrue("The UI Element not exist: List",
//				IUiAuto.waitForResourceId(Constant.ID_LIST, 2000));

		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_ITEM));
		for (int i = 0; i < count; i++) {
			UiObject item = list.getChild(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_ITEM).instance(i));
			if (item.exists()) {
				ATCUiAutoTestCase.assertTrue("The head not exist",
						item.getChild(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_HEAD)).exists());
				ATCUiAutoTestCase.assertTrue("The name not exist",
						item.getChild(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_NAME)).exists());
//				ATCUiAutoTestCase.assertTrue("The department not exist",
//						item.getChild(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_DEPARTMENT)).exists());
			}
		}
	}

	public void verifyManageMemberCountUiElement() throws UiObjectNotFoundException {
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_ACCESS_TYPE_COUNT,
				IUiAuto.waitForTextExists(Constant.TXT_ACCESS_TYPE_COUNT, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_ACCESS_TYPE_TIME,
				IUiAuto.waitForTextExists(Constant.TXT_ACCESS_TYPE_TIME, 2000));

		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Back icon ",
				IUiAuto.waitForResourceId(Constant.ID_BACK, 2000));
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_COUNT_ACCESS_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER));
		for (int i = 0; i < count; i++) {
			ATCUiAutoTestCase.assertTrue("The item not exist",
					list.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER).instance(i)).exists());
			ATCUiAutoTestCase.assertTrue("The count info not exist",
					list.getChild(new UiSelector().resourceId(Constant.ID_COUNT_ACCESS_INFO).instance(i)).exists());
		}
	}

	public void verifyTheMsgSendFailed() throws UiObjectNotFoundException {
		UiCollection msgList = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
		int statusCount = msgList.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_STATE));
		IUiAuto.printLog("count=" + statusCount);
		String iconArr[] = IUiAuto.getConfigProperty("fail_status").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);
		Rect bound;
		double diff = 1000;

		for (int j = 0; j < 2; j++) {
			statusCount = msgList.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_STATE));
			bound = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHAT_MSG_STATE, statusCount - 1).getBounds();
			diff = IUiAuto.getScreenDiffWithReference(Constant.RES_FILE_PATH + "fail_status", x1, y1, x2, y2,
					bound.left, bound.top, bound.right, bound.bottom);
			IUiAuto.printLog("bound=" + bound);
			IUiAuto.printLog("fail diff=" + diff);
			if (diff > 0.1) {
				IUiAuto.sleep(3 * 1000);
			} else {
				IUiAuto.printLog("The msg send failed");
				break;
			}
		}
		if (diff > 0.1) {
			ATCUiAutoTestCase.assertTrue("The msg send successful", false);
		}
	}

	public void sendMsg_Notify(String title) throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_NOTIFY, 3000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_NOTIFY);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_NOTIFY, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_NOTIFY_TITLE,
				IUiAuto.waitForTextExists(Constant.TXT_NOTIFY_TITLE, 5000));

		IUiAuto.printLog("step7=Send " + Constant.TXT_CHAT_TOOL_NOTIFY);
		IUiAuto.clickText(Constant.TXT_NOTIFY_CONTENT, true);
		ShellUtils.execCommand("input text " + title, true);
		IUiAuto.clickResourceId(Constant.ID_NOTIFY_REPLY_MODE);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Send notify failed.", IUiAuto.waitForTextExists(Constant.TXT_NOTIFY_CHECK, 3000));
	}

	public void sendMsg_Vote(String title) throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_NOTIFY, 5000));

		IUiAuto.drag(Constant.TXT_CHAT_TOOL_NOTIFY, Constant.TXT_CHAT_TOOL_LINK, 30);
		IUiAuto.waitForWindowUpdate(3000);
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		IUiAuto.waitForWindowUpdate(3000);
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("drag failed.", IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_VOTE, 8000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_VOTE);
		IUiAuto.sleep(1000);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_VOTE, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_VOTE_TITLE,
				IUiAuto.waitForTextExists(Constant.TXT_VOTE_TITLE, 5000));

		IUiAuto.printLog("step7=Send " + Constant.TXT_CHAT_TOOL_VOTE);
		IUiAuto.clickText(Constant.TXT_VOTE_CONTENT, true);
		ShellUtils.execCommand("input text " + title, true);
		IUiAuto.clickResourceId(Constant.ID_VOTE_ANONYMITY);
		IUiAuto.clickText(Constant.TXT_VOTE_SEND, true);
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Send vote failed.", IUiAuto.waitForTextExists(Constant.TXT_VOTE_JOIN, 3000));
	}

	public void sendMsg_Activity(String title) throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_NOTIFY, 5000));

		IUiAuto.drag(Constant.TXT_CHAT_TOOL_NOTIFY, Constant.TXT_CHAT_TOOL_LINK, 30);
		IUiAuto.waitForWindowUpdate(3000);
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		IUiAuto.waitForWindowUpdate(3000);
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("drag failed.", IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_ACTIVITY, 8000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_ACTIVITY);
		IUiAuto.sleep(1000);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_ACTIVITY, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_ACTIVITY_TITLE,
				IUiAuto.waitForTextExists(Constant.TXT_ACTIVITY_TITLE, 5000));

		IUiAuto.printLog("step7=Send " + Constant.TXT_CHAT_TOOL_ACTIVITY);
		IUiAuto.clickText(Constant.TXT_ACTIVITY_CONTENT, true);
		ShellUtils.execCommand("input text " + title, true);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Send activity failed.",
				IUiAuto.waitForTextExists(Constant.TXT_ACTIVITY_JOIN, 3000));
	}

	public void sendMsg_Survey(String content, String theme) throws UiObjectNotFoundException {
		IUiAuto.printLog("step6=Click Menu tool");
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("Click Menu tool failed.",
				IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_NOTIFY, 5000));

		IUiAuto.drag(Constant.TXT_CHAT_TOOL_NOTIFY, Constant.TXT_CHAT_TOOL_LINK, 25);
		IUiAuto.waitForWindowUpdate(3000);
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		IUiAuto.waitForWindowUpdate(3000);
		IUiAuto.clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		ATCUiAutoTestCase.assertTrue("drag failed.", IUiAuto.waitForTextExists(Constant.TXT_CHAT_TOOL_SURVEY, 8000));

		IUiAuto.printLog("step7=Click " + Constant.TXT_CHAT_TOOL_SURVEY);
		IUiAuto.sleep(1000);
		IUiAuto.clickText(Constant.TXT_CHAT_TOOL_SURVEY, true);
		ATCUiAutoTestCase.assertTrue("The text not exist: " + Constant.TXT_SURVEY_TITLE,
				IUiAuto.waitForTextExists(Constant.TXT_SURVEY_TITLE, 5000));

		IUiAuto.printLog("step7=Send " + Constant.TXT_CHAT_TOOL_SURVEY);
		IUiAuto.clickText(Constant.TXT_SURVEY_CONTENT, true);
		ShellUtils.execCommand("input text " + content, true);
		IUiAuto.pressKey("back");

		IUiAuto.clickText(Constant.TXT_SURVEY_THEME, true);
		ShellUtils.execCommand("input text " + theme, true);
		IUiAuto.pressKey("back");

		IUiAuto.clickText(Constant.TXT_SURVEY_CREATE_VOTE, true);
		IUiAuto.clickText(Constant.TXT_SURVEY_CREATE_VOTE_CONTENT, true);
		ShellUtils.execCommand("input text selection1", true);
		IUiAuto.pressKey("back");

		IUiAuto.clickText(Constant.TXT_SURVEY_CREATE_QUESTION, true);
		IUiAuto.clickText(Constant.TXT_SURVEY_CREATE_QUESTION_CONTENT, true);
		ShellUtils.execCommand("input text question1", true);
		IUiAuto.pressKey("back");

		IUiAuto.clickText(Constant.TXT_SURVEY_SEND, true);
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase
				.assertTrue("Send activity failed.", IUiAuto.waitForTextExists(Constant.TXT_SURVEY_JOIN, 3000));
	}

	public String uploadDocument() throws IOException, UiObjectNotFoundException {
		Date d = new Date();
		String longtime = String.valueOf(d.getTime());

		String fileName = "" + longtime + ".txt";
		File f1 = new File("/sdcard/" + fileName);
		writeSDFile(f1.toString(), 1);

		IUiAuto.clickResourceId(Constant.ID_MORE_MY_DOCUMENT_UPLOAD);
		if (IUiAuto.waitForTextExists(Constant.TXT_TOOL_DOCUMENT_METHOD, 2000)) {
			IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD, true);
		}
		IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD_LOCAL, true);
		IUiAuto.clickText(Constant.TXT_TOOL_DOCUMENT_METHOD_PHONE, true);
		UiObject fileObj = IUiAuto.scrollToFindText(fileName, "Vertical", 9);
		if (fileObj.exists()) {
			IUiAuto.printLog("step9=Select the test document");
			fileObj.click();
		}
		return fileName;
	}

	public String getCurrentType() {
		String iconArr[] = IUiAuto.getConfigProperty("switch_button").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);

		double diff_node = IUiAuto
				.getScreenDiffWithReference(Constant.RES_FILE_PATH + "type_node", x1, y1, x2, y2);
		IUiAuto.printLog("diff_node=" + diff_node);
		double diff_home = IUiAuto
				.getScreenDiffWithReference(Constant.RES_FILE_PATH + "type_home", x1, y1, x2, y2);
		IUiAuto.printLog("diff_home=" + diff_home);
		if (diff_node > diff_home) {
			return "home";
		} else {
			return "node";
		}
	}

	public void verifyCreateNotificationUiElement() {
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_SEND,
				IUiAuto.waitForTextExists(Constant.TXT_SEND, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_CANCEL,
				IUiAuto.waitForTextExists(Constant.TXT_CANCEL, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Add emotion",
				IUiAuto.waitForResourceId(Constant.ID_ADD_EMOTION, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Add image ",
				IUiAuto.waitForResourceId(Constant.ID_ADD_IMAGE, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Add image by camera ",
				IUiAuto.waitForResourceId(Constant.ID_ADD_IMAGE_CAMERA, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Add file ",
				IUiAuto.waitForResourceId(Constant.ID_ADD_FILE, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Add location ",
				IUiAuto.waitForResourceId(Constant.ID_ADD_LOCATION, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Add title ",
				IUiAuto.waitForResourceId(Constant.ID_ADD_TITLE, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Add question ",
				IUiAuto.waitForResourceId(Constant.ID_ADD_QUESTION, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Add voice ",
				IUiAuto.waitForResourceId(Constant.ID_ADD_VOICE, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Notice content ",
				IUiAuto.waitForResourceId(Constant.ID_NOTICE_CONTENT, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Add receiver ",
				IUiAuto.waitForResourceId(Constant.ID_ADD_RECEIVER, 2000));
	}

	public void verifySelectionUiElement() {
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				IUiAuto.waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_SELECT_BY_ORG,
				IUiAuto.waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: " + Constant.TXT_SELECT_CONFIRM0,
				IUiAuto.waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: Back icon",
				IUiAuto.waitForResourceId(Constant.ID_BACK, 2000));
		ATCUiAutoTestCase.assertTrue("The UI Element not exist: search icon",
				IUiAuto.waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
	}
	
	public String createPrivateChatWithSelf(String num) throws UiObjectNotFoundException {
//		IUiAuto.printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
//		IUiAuto.clickResourceId(Constant.ID_TOOLBAR_MSG);
//		ATCUiAutoTestCase.assertTrue("Msg list not exist", IUiAuto.waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));
//
//		IUiAuto.printLog("step3=Click titlebar_search");
//		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_SEARCH);
//		ATCUiAutoTestCase.assertTrue("Cick the search icon failed",
//				IUiAuto.waitForTextExists(Constant.TXT_SEARCH_INPUT_TITLE, 3000));
//
//		IUiAuto.printLog("step4=Input some for search");
//		IUiAuto.clickResourceId(Constant.ID_SEARCH_INPUT);
//		IUiAuto.enterTextInEditor(num, "android.widget.EditText", 0);
//		IUiAuto.waitForWindowUpdate(3000);
//		ATCUiAutoTestCase.assertTrue("Input number not find the contact",
//				IUiAuto.waitForResourceId(Constant.ID_TITLE, 3000));
//		String name = IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_TITLE, 1).getText();
		String myName = IUiAuto.getConfigProperty("my_name");
		IUiAuto.printLog("my_name=" + myName);
		
		IUiAuto.printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		IUiAuto.clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		ATCUiAutoTestCase.assertTrue("Click Address failed.", IUiAuto.waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));
		
		IUiAuto.clickText(Constant.TXT_ADDRESS_ORG, true);
		ATCUiAutoTestCase.assertTrue("Click the Org failed.", IUiAuto.waitForResourceId(Constant.ID_ADDRESS_SWITCH_BUTTON, 3000));
		
		for (int i = 0; i < 5; i++) {
			IUiAuto.clickResourceId(Constant.ID_ADDRESS_SWITCH_BUTTON);
			if (IUiAuto.waitForTextExists(myName, 3000)) {
				break;
			}
		}
		ATCUiAutoTestCase.assertTrue("Click Switch button failed.", IUiAuto.waitForTextExists(myName, 3000));

		IUiAuto.printLog("step3=Click the contact to enter Private chat");
		IUiAuto.printLog("name=" + myName);
		IUiAuto.clickText(myName, true);
		ATCUiAutoTestCase
				.assertTrue("Enter name card failed.", IUiAuto.waitForTextExists(Constant.TXT_NAME_CARD, 3000));
		IUiAuto.clickText(Constant.TXT_NAMECARD_SEND_LINXIN, true);
		ATCUiAutoTestCase.assertTrue("Enter Private chat failed.",
				IUiAuto.waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));

		return myName;
	}
	
	public void createWorkgroupByPrivateChat(String num) throws UiObjectNotFoundException {
		createPrivateChatWithSelf(num);
		
		IUiAuto.printLog("step6=Click Settings icon");
		IUiAuto.clickResourceId(Constant.ID_CHAT_SETTINGS);
		ATCUiAutoTestCase.assertTrue("Enter settings failed.",
				IUiAuto.waitForResourceId(Constant.ID_CHAT_SETTINGS_ADD_MEMBER, 3000));
		
		IUiAuto.printLog("step6=Click Add member");
		IUiAuto.clickResourceId(Constant.ID_CHAT_SETTINGS_ADD_MEMBER);
		ATCUiAutoTestCase.assertTrue("Enter Select failed.",
				IUiAuto.waitForTextExists(Constant.TXT_SELECT_BY_ORG, 6000));
		
		IUiAuto.clickText(Constant.TXT_SELECT_BY_ORG, true);
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_CHECKBOX));
		for (int i = 0; i < 6; i++) {
			if (count > 5) {
				IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHECKBOX, i).clickAndWaitForNewWindow();
			} else {
				if (i < count) {
					IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_CHECKBOX, i).clickAndWaitForNewWindow();
				} else {
					if (IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_DEPART_CHECKBOX, i - count).exists()) {
						IUiAuto.getObjectOnResourceIdAndInstance(Constant.ID_DEPART_CHECKBOX, i - count).clickAndWaitForNewWindow();
					}
				}
			}
		}
		
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		IUiAuto.waitForWindowUpdate(2000);
		IUiAuto.clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		ATCUiAutoTestCase.assertTrue("Double confirm dialog not display",
				IUiAuto.waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		IUiAuto.printLog("step9=Click Confirm");
		IUiAuto.clickText(Constant.TXT_CONFIRM, true);
		IUiAuto.waitForWindowUpdate(3000);
		ATCUiAutoTestCase.assertTrue("Enter chat page failed.", IUiAuto.waitForResourceId(Constant.ID_CHAT_LIST, 4000));
	}
	
	public void selectMembers(int num) throws UiObjectNotFoundException{
		
		UiObject address = new UiObject(new UiSelector().className("android.widget.ListView").instance(0));
		address.getChild(new UiSelector().className("android.widget.RelativeLayout")).click();
		UiCollection team = new UiCollection(new UiSelector().className("android.widget.ListView"));

		int clicktime = 0;
		outFor: for (int i = 0; i < team.getChildCount(); i++) {
			team.getChild(new UiSelector().className("android.widget.RelativeLayout").instance(i)).click();
			UiCollection org = new UiCollection(new UiSelector().className("android.widget.ExpandableListView"));
			for (int x = 0; x < org.getChildCount(); x++) {
				for (int a = 0; a < org.getChild(new UiSelector().className("android.widget.GridView").instance(x))
						.getChildCount(); a++) {
					org.getChild(new UiSelector().className("android.widget.GridView").instance(x))
							.getChild(new UiSelector().className("android.widget.FrameLayout").instance(a)).click();
//					IUiAuto.pressKey("back");
					clicktime = clicktime + 1;
					if (clicktime >= num) {
						break outFor;
					}
				}
			}
//			IUiAuto.pressKey("back");
		}
	}
	
	public long getTimeStamp () {
		Date d = new Date();
		return d.getTime();
	}

	public void clearDucumentCache(String path) {
		IUiAuto.printLog("Helper--Delete " + path);
		File zipfile = new File(path);
		if (zipfile.exists()) {
			deleteDir(zipfile);
		}
	}

	/**
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			if (children == null) {
				return true;
			} else {
				for (int i = 0; i < children.length; i++) {
					boolean success = deleteDir(new File(dir, children[i]));
					if (!success) {
						return false;
					}
				}
			}
		}
		return dir.delete();
	}
	
	public void getMsgSendTime() {
		String[] logs = getMsgSendAndReceiveLogs();
		String s1 ="2016-" + logs[0].split(" ")[0] + " " + logs[0].split(" ")[1];
		String s2 = "2016-" + logs[1].split(" ")[0] + " " + logs[1].split(" ")[1];
		
		Date t1 = null;
		Date t2 = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			t1 = formatter.parse(s1);
			t2 = formatter.parse(s2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long count = t2.getTime() - t1.getTime();
		IUiAuto.printLog("TimeCount=" + count);
	}
	
	public void getMsgReceiveTime() {
		String[] logs = getMsgSendAndReceiveLogs();
		String s1 ="2016-" + logs[0].split(" ")[0] + " " + logs[0].split(" ")[1];
		String s2 = "2016-" + logs[2].split(" ")[0] + " " + logs[2].split(" ")[1];
		
		Date t1 = null;
		Date t2 = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			t1 = formatter.parse(s1);
			t2 = formatter.parse(s2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long count = t2.getTime() - t1.getTime();
		IUiAuto.printLog("TimeCount=" + count);
	}

	public String[] getMsgSendAndReceiveLogs() {
		StringBuilder stringBuilder = new StringBuilder();
		Process p = null;
		BufferedReader reader = null;
		String line = null;

		String send1 = "send:GdpPackage [opcode=5101";
		String received1 = "received:GdpPackage [opcode=5101";
		String received2 = "received:GdpPackage [opcode=5201";
		
		String [] logs = new String [3];
		
		try {
			p = Runtime.getRuntime().exec("logcat -d -v time | grep TAG_NET_PKG");
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			stringBuilder.setLength(0);
			while ((line = reader.readLine()) != null) {
				if (line.indexOf(send1) != -1) {
					logs[0] = line;
					System.out.println(line);
				} else if (line.indexOf(received1) != -1) {
					logs[1] = line;
					System.out.println(line);
				} else if (line.indexOf(received2) != -1) {
					logs[2] = line;
					System.out.println(line);
				}
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
		return logs;
	}
	
	public String getAppVersion() {
		
		String pkg = IUiAuto.getConfigProperty("pkg");
		
		StringBuilder stringBuilder = new StringBuilder();
		Process p = null;
		BufferedReader reader = null;
		String line = null;
		String str = null;
		
		String command = "dumpsys package " + pkg + " | grep versionName";

		try {
			p = Runtime.getRuntime().exec(command);
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			stringBuilder.setLength(0);
			while ((line = reader.readLine()) != null) {
				if (line.trim().startsWith("versionName")) {
					IUiAuto.printLog(line);
					str = line.trim().split("=")[1];
				}
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
		return str;
	}
	
	public int compare(String s1, String s2) {
		if (s1 == null && s2 == null)
			return 0;
		else if (s1 == null)
			return -1;
		else if (s2 == null)
			return 1;
		String[] arr1 = s1.split("[^a-zA-Z0-9]+"), arr2 = s2.split("[^a-zA-Z0-9]+");
		int i1, i2, i3;
		for (int ii = 0, max = Math.min(arr1.length, arr2.length); ii <= max; ii++) {
			if (ii == arr1.length)
				return ii == arr2.length ? 0 : -1;
			else if (ii == arr2.length)
				return 1;
			try {
				i1 = Integer.parseInt(arr1[ii]);
			} catch (Exception x) {
				i1 = Integer.MAX_VALUE;
			}
			try {
				i2 = Integer.parseInt(arr2[ii]);
			} catch (Exception x) {
				i2 = Integer.MAX_VALUE;
			}
			if (i1 != i2) {
				return i1 - i2;
			}
			i3 = arr1[ii].compareTo(arr2[ii]);
			if (i3 != 0)
				return i3;
		}
		return 0;
	}
	
}
