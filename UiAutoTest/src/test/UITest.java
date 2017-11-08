package test;

import java.util.ArrayList;
import java.util.Date;

import android.graphics.Point;
import android.graphics.Rect;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

import test.helper.Constant;
import test.helper.LanxinTestHelper;
import uiautotestutils.ATCUiAutoTestCase;
import uiautotestutils.UiAutomatorHelper;
import uiautotestutils.utils.ShellUtils;

public class UITest extends ATCUiAutoTestCase {

	LanxinTestHelper helper;
	private boolean TurnOnNetWork;
	private boolean LogOut;
	private boolean DeleteGroup;

	public void testUI_MainView_Element() throws UiObjectNotFoundException {

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		printLog("step2=Verify Ui Elements");
		assertTrue("Ui Element not exist: Msg icon", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));
		assertTrue("Ui Element not exist: Address icon", waitForResourceId(Constant.ID_TOOLBAR_ADDRESS, 2000));
		assertTrue("Ui Element not exist: Interest icon", waitForResourceId(Constant.ID_TOOLBAR_INTEREST, 2000));
		assertTrue("Ui Element not exist: More icon", waitForResourceId(Constant.ID_TOOLBAR_MORE, 2000));

		assertTrue("Ui Element not exist: " + Constant.TXT_TOOLBAR_MSG,
				waitForTextExists(Constant.TXT_TOOLBAR_MSG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_TOOLBAR_ADDRESS,
				waitForTextExists(Constant.TXT_TOOLBAR_ADDRESS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_TOOLBAR_INTEREST,
				waitForTextExists(Constant.TXT_TOOLBAR_INTEREST, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_TOOLBAR_MORE,
				waitForTextExists(Constant.TXT_TOOLBAR_MORE, 2000));

		assertTrue("Ui Element not exist: Seach icon", waitForResourceId(Constant.ID_TITLEBAR_SEARCH, 2000));

	}

	public void testUI_TitleBar_Element() throws UiObjectNotFoundException {

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Verify Top bar element");
		assertTrue("Ui Element not exist: search icon", waitForResourceId(Constant.ID_TITLEBAR_SEARCH, 2000));
		assertTrue("Ui Element not exist: plus icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
	}

	public void testUI_TitleBar_Search_GeneralElement() throws UiObjectNotFoundException {

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click titlebar_search");
		clickResourceId(Constant.ID_TITLEBAR_SEARCH);
		assertTrue("Cick the search icon failed", waitForTextExists(Constant.TXT_SEARCH_INPUT_TITLE, 3000));
	}
	
	public void testUI_TitleBar_Search_LanxinElement() throws UiObjectNotFoundException {

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click titlebar_search");
		clickResourceId(Constant.ID_TITLEBAR_SEARCH);
		assertTrue("Cick the search icon failed", waitForTextExists(Constant.TXT_SEARCH_INPUT_TITLE, 3000));
		
		printLog("step4=Verify the UI Element");
		assertTrue("The UI Element not exist: " + Constant.TXT_SEARCH_COMMON,
				waitForTextExists(Constant.TXT_SEARCH_COMMON, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_SEARCH_CANCEL,
				waitForTextExists(Constant.TXT_SEARCH_CANCEL, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_SEARCH_NOTIFY,
				waitForTextExists(Constant.TXT_SEARCH_NOTIFY, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_SEARCH_RSS,
				waitForTextExists(Constant.TXT_SEARCH_RSS, 3000));
	}
	
	public void testUI_TitleBar_Search_AddressElement() throws UiObjectNotFoundException {

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click titlebar_search");
		clickResourceId(Constant.ID_TITLEBAR_SEARCH);
		assertTrue("Cick the search icon failed", waitForTextExists(Constant.TXT_SEARCH_INPUT_TITLE, 3000));
		
		printLog("step4=Verify the UI Element");
//		assertTrue("The UI Element not exist: " + Constant.TXT_SEARCH_COMMON,
//				waitForTextExists(Constant.TXT_SEARCH_COMMON, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_SEARCH_CANCEL,
				waitForTextExists(Constant.TXT_SEARCH_CANCEL, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_SEARCH_RECENT_CONTACTS,
				waitForTextExists(Constant.TXT_SEARCH_RECENT_CONTACTS, 3000));
		
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_RSS_ACCOUNT_GRIDVIEW));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_IV_HEAD));
		if (count > 3) {
			assertTrue("The UI Element not exist: " + Constant.TXT_SEARCH_DISPLAY_MORE,
					waitForTextExists(Constant.TXT_SEARCH_DISPLAY_MORE, 3000));
		}
	}

	public void testUI_TitleBar_Plus_Element() throws UiObjectNotFoundException {

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click titlebar_plus");
		clickResourceId(Constant.ID_TITLEBAR_PLUS);

		printLog("step4=Verify Ui element");
		String iconArr[] = getConfigProperty("titlebar_popup_plus").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);

		double diff = getScreenDiffWithReference(Constant.RES_FILE_PATH + "titlebar_popup_plus", x1, y1, x2, y2);
		printLog("diff=" + diff);

		assertTrue("POPUP_PLUS not match expect", diff < Constant.DIFF_MIN);
		// assertTrue("Ui Element not exist: TXT_CREATE_WORK_GROUP",
		// waitForTextExists(Constant.TXT_CREATE_WORK_GROUP, 2000));
		// assertTrue("Ui Element not exist: TXT_ADD_CONTACTS",
		// waitForTextExists(Constant.TXT_ADD_CONTACTS, 2000));
		// assertTrue("Ui Element not exist: TXT_CALL_MEETING",
		// waitForTextExists(Constant.TXT_CALL_MEETING, 2000));
		// assertTrue("Ui Element not exist: TXT_SCAN_QR_CODE",
		// waitForTextExists(Constant.TXT_SCAN_QR_CODE, 2000));
		// assertTrue("Ui Element not exist: TXT_GROUP_CHAT",
		// waitForTextExists(Constant.TXT_GROUP_CHAT, 2000));
	}

	public void testUI_TitleBar_WorkGroup_Create_SelectContacts_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);
	}

	public void testUI_TitleBar_WorkGroup_Create_SelectContacts_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=Verify Ui element");
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: Search icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: Check icon", waitForResourceId(Constant.ID_CHECK, 2000));
		assertTrue("Ui Element not exist: Contacts list", waitForResourceId(Constant.ID_LIST, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_CREATE_WORK_GROUP,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_ORG,
				waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
	}

	public void testUI_TitleBar_WorkGroup_Create_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=Click back icon");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Doule confirm donot exist", waitForTextExists(Constant.TXT_CANCEL_CREATE_GROUP, 2000));

		printLog("step7=Back to Message list");
		clickText(Constant.TXT_YES, true);
		assertTrue("Click yes to back Message list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 2000));
	}

	public void testUI_TitleBar_WorkGroup_Create_Switch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());

		printLog("step7=Verify Ui element");
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: Search icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: CHECKBOX icon", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));
		assertTrue("Ui Element not exist: org path", waitForResourceId(Constant.ID_ORG_PATH, 2000));
		assertTrue("Ui Element not exist: show list icon", waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_CREATE_WORK_GROUP,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_ORG,
				waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

		printLog("step8=click contact icon");
		clickResourceId(Constant.ID_TAB_TOP_CONTACT);

		UiObject contact = getObjectOnResourceId(Constant.ID_TAB_TOP_CONTACT);
		assertTrue("Switch from org to Contacts failed.", contact.isChecked());

		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: Search icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: Check icon", waitForResourceId(Constant.ID_CHECK, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_CREATE_WORK_GROUP,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_ORG,
				waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
	}

	public void testUI_TitleBar_WorkGroup_Create_Switch_CloudUser() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		// cloud user
		String phoneNum = getConfigProperty("phoneNum_cloud");
		String password = getConfigProperty("password_cloud");

		helper.clearAppUserData();

		LogOut = true;
		helper.workeGroup_CreateGroup(phoneNum, password, "cloud_plus");

		printLog("step6=Verify Ui element");
		waitForWindowUpdate(5000);
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: Search icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: Check icon", waitForResourceId(Constant.ID_CHECK, 2000));
		assertTrue("Ui Element not exist: Contacts list", waitForResourceId(Constant.ID_LIST, 2000));
		assertTrue("Ui Element exist: " + Constant.TXT_CREATE_WORK_GROUP,
				!waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		assertTrue("Ui Element exist: " + Constant.TXT_SELECT_BY_ORG,
				!waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
		assertTrue("Ui Element exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				!waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
	}

	public void testUI_TitleBar_WorkGroup_Create_SelectContacts_ContactList() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=Verify Contact list");
		assertTrue("Ui Element not exist: Head image", waitForResourceId(Constant.ID_HEAD_IMAGEE, 3000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

		String name = getTextOnResourceId(Constant.ID_GROUP_MEMBER_NAME, 2000);
		String department = getTextOnResourceId(Constant.ID_GROUP_MEMBER_DEPARTMENT, 2000);

		assertTrue("Contact name is empyt", (name != null && !name.equals("")));
		assertTrue("Contact department is empyt", (department != null && !department.equals("")));
	}

	public void testUI_TitleBar_WorkGroup_Create_SelectContacts_ContactSort() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=Verify Contact sort");
		assertTrue("Ui Element not exist: Head image", waitForResourceId(Constant.ID_HEAD_IMAGEE, 3000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

		UiObject indexBar = getObjectOnResourceId(Constant.ID_INDEX_BAR);
		if (indexBar.exists()) {

			String s1 = indexBar.getChild(new UiSelector().index(1)).getText();
			String s2 = indexBar.getChild(new UiSelector().index(2)).getText();
			String s3 = indexBar.getChild(new UiSelector().index(3)).getText();

			assertTrue("Contact list sort not from A to Z", (s1.compareTo(s2) == -1 && s2.compareTo(s3) == -1));
		} else {
			assertTrue("UI Element not exist: " + Constant.ID_INDEX_BAR, false);
		}
	}

	public void testUI_TitleBar_WorkGroup_Create_SelectContacts_Org() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		UiObject org_path = getObjectOnResourceId(Constant.ID_ORG_PATH);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Ui Element not exist: org path", org_path.waitForExists(2000));
		assertTrue("Ui Element not exist: show list icon", waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));

		UiObject orgName = org_path.getChild(new UiSelector().className("android.widget.TextView").index(0));
		String org_name = null;
		if (orgName.exists()) {
			org_name = orgName.getText();
			assertTrue("Org Name is empty", (org_name != null && !org_name.equals("")));
		} else {
			assertTrue("Org Name is empty", false);
		}

		printLog("step7=Click branch contact");
		while (waitForResourceId(Constant.ID_CHECK_NUMBER, 2000)) {
			clickResourceId(Constant.ID_CHECK_NUMBER);
		}

		UiObject checkbox = getObjectOnResourceIdAndIndex(Constant.ID_CHECKBOX, 1);
		assertTrue("Click branch failed.", checkbox.waitForExists(2000));

		checkbox.clickAndWaitForNewWindow();
		assertTrue("Checkbox not be select!", checkbox.isChecked());
	}

	public void testUI_TitleBar_WorkGroup_NewGroup_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			printLog("step6=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			assertTrue("Select contact2 failed.", contact2Check.isChecked());

			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			printLog("step6=Veirfy UI Element");
			assertTrue("UI Element not exist:" + Constant.TXT_NEWGROUP, waitForTextExists(Constant.TXT_NEWGROUP, 3000));

		} else {
			assertTrue("This account has no contact", false);
		}
	}

	public void testUI_TitleBar_WorkGroup_NewGroup_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			printLog("step6=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			assertTrue("Select contact2 failed.", contact2Check.isChecked());

			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			printLog("step7=Veirfy UI Element");
			assertTrue("UI Element not exist:" + Constant.TXT_NEWGROUP, waitForTextExists(Constant.TXT_NEWGROUP, 3000));
			assertTrue("UI Element not exist:" + Constant.TXT_ADD_MEMBER,
					waitForTextExists(Constant.TXT_ADD_MEMBER, 2000));
			assertTrue("UI Element not exist:" + Constant.TXT_EDIT_HEAD_IMAGE,
					waitForTextExists(Constant.TXT_EDIT_HEAD_IMAGE, 2000));
			assertTrue("UI Element not exist: ID_BACK", waitForResourceId(Constant.ID_BACK, 2000));
			assertTrue("UI Element not exist: ID_TITLEBAR_CONFIRM",
					waitForResourceId(Constant.ID_TITLEBAR_CONFIRM, 2000));
			assertTrue("UI Element not exist: ID_GROUP_REMOVE", waitForResourceId(Constant.ID_GROUP_REMOVE, 2000));
			assertTrue("UI Element not exist: ID_GROUP_GROUP_NAME_INPUT",
					waitForResourceId(Constant.ID_GROUP_GROUP_NAME_INPUT, 2000));
			assertTrue("UI Element not exist: ID_GROUP_HEAD_IMAGE",
					waitForResourceId(Constant.ID_GROUP_HEAD_IMAGE, 2000));
			assertTrue("UI Element not exist: ID_ADD_MEMBER", waitForResourceId(Constant.ID_ADD_MEMBER, 2000));
			assertTrue("UI Element not exist: ID_GROUP_CONTACT_LIST",
					waitForResourceId(Constant.ID_GROUP_CONTACT_LIST, 2000));

		} else {
			assertTrue("This account has no contact", false);
		}
	}

	public void testUI_TitleBar_WorkGroup_NewGroup_Contacts() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			printLog("step6=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			assertTrue("Select contact2 failed.", contact2Check.isChecked());

			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			printLog("step7=Veirfy UI Element");
			assertTrue("UI Element not exist:" + Constant.TXT_NEWGROUP, waitForTextExists(Constant.TXT_NEWGROUP, 3000));
			assertTrue("UI Element not exist:" + Constant.TXT_ADD_MEMBER,
					waitForTextExists(Constant.TXT_ADD_MEMBER, 2000));

			printLog("step6=Veirfy Contact Element");

			UiObject contact = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER, 1);

			if (contact.exists()) {

				UiObject contactHead = contact.getChild(new UiSelector().resourceId(Constant.ID_HEAD_IMAGEE));
				String name = contact.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_NAME)).getText();
				String department = contact.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_DEPARTMENT))
						.getText();

				assertTrue("Contact Head not exist", contactHead.exists());
				assertTrue("Contact Name is empty", (name != null && !name.equals("")));
				assertTrue("Contact Department is empty", (department != null && !department.equals("")));

			} else {
				assertTrue("This group has no contact", false);
			}

		} else {
			assertTrue("This account has no contact", false);
		}
	}

	public void testUI_TitleBar_WorkGroup_NewGroup_Branch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		UiObject org_path = getObjectOnResourceId(Constant.ID_ORG_PATH);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Ui Element not exist: org path", org_path.waitForExists(2000));
		assertTrue("Ui Element not exist: show list icon", waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));

		UiObject checkDepartment = getObjectOnResourceIdAndInstance(Constant.ID_DEPART_CHECKBOX, 0);
		if (checkDepartment.exists()) {

			printLog("step7=Select a branch in org");
			checkDepartment.clickAndWaitForNewWindow();
			assertTrue("Branch not be select", checkDepartment.isChecked());

			printLog("step8=Click confirm button");
			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			assertTrue("UI Element not exist:" + Constant.TXT_NEWGROUP, waitForTextExists(Constant.TXT_NEWGROUP, 3000));
			assertTrue("UI Element not exist:" + Constant.TXT_ADD_MEMBER,
					waitForTextExists(Constant.TXT_ADD_MEMBER, 2000));

			printLog("step9=Veirfy Branch Element");
			UiObject branch = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER, 0);

			String number = getTextOnResourceId(Constant.ID_TITLEBAR_CONFIRM, 2000);
			assertTrue("Group number is 0", !number.startsWith(Constant.TXT_SELECT_CONFIRM0));

			if (branch.exists()) {

				UiObject contactHead = branch.getChild(new UiSelector().resourceId(Constant.ID_HEAD_IMAGEE));
				String name = branch.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_NAME)).getText();
				String department = branch.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_DEPARTMENT))
						.getText();

				assertTrue("branch Head exist", !contactHead.exists());
				assertTrue("branch Name is empty", (name != null && !name.equals("")));
				assertTrue("Department is empty", (department != null && !department.equals("")));

			} else {
				assertTrue("This group has no branch", false);
			}

		} else {
			assertTrue("Department not exist", false);
		}
	}

	public void testUI_TitleBar_GroupChat_Create_SelectContacts_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);
	}

	public void testUI_TitleBar_GroupChat_Create_SelectContacts_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step5=Verify Ui element");
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: Search icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: Check icon", waitForResourceId(Constant.ID_CHECK, 2000));
		assertTrue("Ui Element not exist: Contacts list", waitForResourceId(Constant.ID_LIST, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_CREATE_WORK_GROUP,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_ORG,
				waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
	}

	public void testUI_TitleBar_GroupChat_Create_SelectContacts_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step5=Click back icon");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Doule confirm donot exist", waitForTextExists(Constant.TXT_CANCEL_GROUP_CHAT, 2000));

		printLog("ste6=Back to Message list");
		clickText(Constant.TXT_YES, true);
		assertTrue("Click yes to back Message list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 2000));
	}

	public void testUI_TitleBar_GroupChat_Create_SelectContacts_Switch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());

		printLog("step6=Verify Ui element");
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: Search icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: CHECKBOX icon", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));
		assertTrue("Ui Element not exist: org path", waitForResourceId(Constant.ID_ORG_PATH, 2000));
		assertTrue("Ui Element not exist: show list icon", waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_CREATE_WORK_GROUP,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_ORG,
				waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

		printLog("step7=click contact icon");
		clickResourceId(Constant.ID_TAB_TOP_CONTACT);

		UiObject contact = getObjectOnResourceId(Constant.ID_TAB_TOP_CONTACT);
		assertTrue("Switch from org to Contacts failed.", contact.isChecked());

		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: Search icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: Check icon", waitForResourceId(Constant.ID_CHECK, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_CREATE_WORK_GROUP,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_ORG,
				waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
	}

	public void testUI_TitleBar_GroupChat_Create_SelectContacts_Switch_CloudUser() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum_cloud");
		String password = getConfigProperty("password_cloud");

		helper.clearAppUserData();
		LogOut = true;

		helper.groupChat_CreateGroup(phoneNum, password, "cloud_plus");

		printLog("step5=Verify Ui element");
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: Search icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: Check icon", waitForResourceId(Constant.ID_CHECK, 2000));
		assertTrue("Ui Element not exist: Contacts list", waitForResourceId(Constant.ID_LIST, 2000));
		assertTrue("Ui Element exist: " + Constant.TXT_CREATE_WORK_GROUP,
				!waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		assertTrue("Ui Element exist: " + Constant.TXT_SELECT_BY_ORG,
				!waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
		assertTrue("Ui Element exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				!waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

	}

	public void testUI_TitleBar_GroupChat_Create_SelectContacts_ContactList() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step5=Verify Ui element");
		assertTrue("Ui Element not exist: Head image", waitForResourceId(Constant.ID_HEAD_IMAGEE, 3000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

		String name = getTextOnResourceId(Constant.ID_GROUP_MEMBER_NAME, 2000);
		String department = getTextOnResourceId(Constant.ID_GROUP_MEMBER_DEPARTMENT, 2000);

		assertTrue("Contact name is empyt", (name != null && !name.equals("")));
		assertTrue("Contact department is empyt", (department != null && !department.equals("")));
	}

	public void testUI_TitleBar_GroupChat_Create_SelectContacts_ContactSort() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step5=Verify Ui element");
		assertTrue("Ui Element not exist: Head image", waitForResourceId(Constant.ID_HEAD_IMAGEE, 3000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

		UiObject indexBar = getObjectOnResourceId(Constant.ID_INDEX_BAR);
		if (indexBar.exists()) {

			String s1 = indexBar.getChild(new UiSelector().index(1)).getText();
			String s2 = indexBar.getChild(new UiSelector().index(2)).getText();
			String s3 = indexBar.getChild(new UiSelector().index(3)).getText();

			assertTrue("Contact list sort not from A to Z", (s1.compareTo(s2) == -1 && s2.compareTo(s3) == -1));
		} else {
			assertTrue("UI Element not exist: " + Constant.ID_INDEX_BAR, false);
		}
	}

	public void testUI_TitleBar_GroupChat_Create_SelectContacts_Org() throws UiObjectNotFoundException {

		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step6=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		UiObject org_path = getObjectOnResourceId(Constant.ID_ORG_PATH);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Ui Element not exist: org path", org_path.waitForExists(2000));
		assertTrue("Ui Element not exist: show list icon", waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));

		UiObject orgName = org_path.getChild(new UiSelector().className("android.widget.TextView").index(0));
		String org_name = null;
		if (orgName.exists()) {
			org_name = orgName.getText();
			assertTrue("Org Name is empty", (org_name != null && !org_name.equals("")));
		} else {
			assertTrue("Org Name is empty", false);
		}

		printLog("step7=Click branch contact");
		while (waitForResourceId(Constant.ID_CHECK_NUMBER, 2000)) {
			clickResourceId(Constant.ID_CHECK_NUMBER);
		}

		UiObject checkbox = getObjectOnResourceIdAndIndex(Constant.ID_CHECKBOX, 1);
		assertTrue("Click branch failed.", checkbox.waitForExists(2000));

		checkbox.clickAndWaitForNewWindow();
		assertTrue("Checkbox not be select!", checkbox.isChecked());

	}

	public void testUI_TitleBar_GroupChat_NewGroup_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			printLog("step5=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			assertTrue("Select contact2 failed.", contact2Check.isChecked());

			printLog("step6=Click confirm button");
			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			assertTrue("UI Element not exist:" + Constant.TXT_CREATE_CHAT_GROUP,
					waitForTextExists(Constant.TXT_CREATE_CHAT_GROUP, 3000));

		} else {
			assertTrue("This account has no contact", false);
		}
	}

	public void testUI_TitleBar_GroupChat_NewGroup_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			printLog("step5=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			assertTrue("Select contact2 failed.", contact2Check.isChecked());

			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			printLog("step6=Veirfy UI Element");
			assertTrue("UI Element not exist:" + Constant.TXT_NEWGROUPSEND,
					waitForTextExists(Constant.TXT_NEWGROUPSEND, 3000));
			assertTrue("UI Element not exist:" + Constant.TXT_ADD_TEAM_MEMBER,
					waitForTextExists(Constant.TXT_ADD_TEAM_MEMBER, 2000));
			assertTrue("UI Element not exist:" + Constant.TXT_EDIT_HEAD_IMAGE,
					waitForTextExists(Constant.TXT_EDIT_HEAD_IMAGE, 2000));
			assertTrue("UI Element not exist: ID_BACK", waitForResourceId(Constant.ID_BACK, 2000));
			assertTrue("UI Element not exist: ID_TITLEBAR_CONFIRM",
					waitForResourceId(Constant.ID_TITLEBAR_CONFIRM, 2000));
			assertTrue("UI Element not exist: ID_GROUP_REMOVE", waitForResourceId(Constant.ID_GROUP_REMOVE, 2000));
			assertTrue("UI Element not exist: ID_GROUP_GROUP_NAME_INPUT",
					waitForResourceId(Constant.ID_GROUP_GROUP_NAME_INPUT, 2000));
			assertTrue("UI Element not exist: ID_GROUP_HEAD_IMAGE",
					waitForResourceId(Constant.ID_GROUP_HEAD_IMAGE, 2000));
			assertTrue("UI Element not exist: ID_ADD_MEMBER", waitForResourceId(Constant.ID_ADD_MEMBER, 2000));
			assertTrue("UI Element not exist: ID_GROUP_CONTACT_LIST",
					waitForResourceId(Constant.ID_GROUP_CONTACT_LIST, 2000));

		} else {
			assertTrue("This account has no contact", false);
		}
	}

	public void testUI_TitleBar_GroupChat_NewGroup_Contacts() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			printLog("step5=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			assertTrue("Select contact2 failed.", contact2Check.isChecked());

			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			printLog("step6=Veirfy UI Element");
			assertTrue("UI Element not exist:" + Constant.TXT_NEWGROUPSEND,
					waitForTextExists(Constant.TXT_NEWGROUPSEND, 3000));
			assertTrue("UI Element not exist:" + Constant.TXT_ADD_TEAM_MEMBER,
					waitForTextExists(Constant.TXT_ADD_TEAM_MEMBER, 2000));

			printLog("step6=Veirfy Contact Element");

			UiObject contact = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER, 1);

			if (contact.exists()) {

				UiObject contactHead = contact.getChild(new UiSelector().resourceId(Constant.ID_HEAD_IMAGEE));
				String name = contact.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_NAME)).getText();
				String department = contact.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_DEPARTMENT))
						.getText();

				assertTrue("Contact Head not exist", contactHead.exists());
				assertTrue("Contact Name is empty", (name != null && !name.equals("")));
				assertTrue("Contact Department is empty", (department != null && !department.equals("")));

			} else {
				assertTrue("This group has no contact", false);
			}

		} else {
			assertTrue("This account has no contact", false);
		}
	}

	public void testUI_TitleBar_GroupChat_NewGroup_Branch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		UiObject org_path = getObjectOnResourceId(Constant.ID_ORG_PATH);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Ui Element not exist: org path", org_path.waitForExists(2000));
		assertTrue("Ui Element not exist: show list icon", waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));

		UiObject checkDepartment = getObjectOnResourceIdAndInstance(Constant.ID_DEPART_CHECKBOX, 0);
		if (checkDepartment.exists()) {

			printLog("Select a branch in org");
			checkDepartment.clickAndWaitForNewWindow();
			assertTrue("Branch not be select", checkDepartment.isChecked());

			printLog("step6=Click confirm button");
			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			assertTrue("UI Element not exist:" + Constant.TXT_NEWGROUPSEND,
					waitForTextExists(Constant.TXT_NEWGROUPSEND, 3000));
			assertTrue("UI Element not exist:" + Constant.TXT_ADD_TEAM_MEMBER,
					waitForTextExists(Constant.TXT_ADD_TEAM_MEMBER, 2000));

			printLog("step6=Veirfy Branch Element");

			UiObject branch = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER, 0);

			String number = getTextOnResourceId(Constant.ID_TITLEBAR_CONFIRM, 2000);
			assertTrue("Group number is 0", !number.startsWith(Constant.TXT_SELECT_CONFIRM0));

			if (branch.exists()) {

				UiObject contactHead = branch.getChild(new UiSelector().resourceId(Constant.ID_HEAD_IMAGEE));
				String name = branch.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_NAME)).getText();
				String department = branch.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_DEPARTMENT))
						.getText();

				assertTrue("branch Head exist", !contactHead.exists());
				assertTrue("branch Name is empty", (name != null && !name.equals("")));
				assertTrue("Department is empty", (department != null && !department.equals("")));

			} else {
				assertTrue("This group has no branch", false);
			}

		} else {
			assertTrue("Department not exist", false);
		}
	}

	public void testUI_TitleBar_CallMeeting_Create_SelectContacts_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);
	}

	public void testUI_TitleBar_CallMeeting_Create_SelectContacts_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step5=Verify Ui element");
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: Search icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: Check icon", waitForResourceId(Constant.ID_CHECK, 2000));
		assertTrue("Ui Element not exist: Contacts list", waitForResourceId(Constant.ID_LIST, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_CREATE_WORK_GROUP,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_ORG,
				waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_CONFIRM_CALL,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM_CALL, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
	}

	public void testUI_TitleBar_CallMeeting_Create_SelectContacts_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step6=Click back icon");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Doule confirm donot exist", waitForTextExists(Constant.TXT_CANCEL_CALL_MEETING, 2000));

		printLog("step7=Back to Message list");
		clickText(Constant.TXT_YES, true);
		assertTrue("Click yes to back Message list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 2000));
	}

	public void testUI_TitleBar_CallMeeting_Create_SelectContacts_Switch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());

		printLog("step6=Verify Ui element");
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: Search icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: CHECKBOX icon", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));
		assertTrue("Ui Element not exist: org path", waitForResourceId(Constant.ID_ORG_PATH, 2000));
		assertTrue("Ui Element not exist: show list icon", waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_CREATE_WORK_GROUP,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_ORG,
				waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_CONFIRM_CALL,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM_CALL, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

		printLog("step7=click contact icon");
		clickResourceId(Constant.ID_TAB_TOP_CONTACT);

		UiObject contact = getObjectOnResourceId(Constant.ID_TAB_TOP_CONTACT);
		assertTrue("Switch from org to Contacts failed.", contact.isChecked());

		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: Search icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: Check icon", waitForResourceId(Constant.ID_CHECK, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_CREATE_WORK_GROUP,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_ORG,
				waitForTextExists(Constant.TXT_SELECT_BY_ORG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_CONFIRM_CALL,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM_CALL, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

	}

	public void testUI_TitleBar_CallMeeting_Create_SelectContacts_ContactList() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step5=Verify Ui element");
		assertTrue("Ui Element not exist: Head image", waitForResourceId(Constant.ID_HEAD_IMAGEE, 3000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

		String name = getTextOnResourceId(Constant.ID_GROUP_MEMBER_NAME, 2000);
		String department = getTextOnResourceId(Constant.ID_GROUP_MEMBER_DEPARTMENT, 2000);

		assertTrue("Contact name is empyt", (name != null && !name.equals("")));
		assertTrue("Contact department is empyt", (department != null && !department.equals("")));
	}

	public void testUI_TitleBar_CallMeeting_Create_SelectContacts_ContactSort() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step5=Verify Ui element");
		assertTrue("Ui Element not exist: Head image", waitForResourceId(Constant.ID_HEAD_IMAGEE, 3000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

		UiObject indexBar = getObjectOnResourceId(Constant.ID_INDEX_BAR);
		if (indexBar.exists()) {

			String s1 = indexBar.getChild(new UiSelector().index(1)).getText();
			String s2 = indexBar.getChild(new UiSelector().index(2)).getText();
			String s3 = indexBar.getChild(new UiSelector().index(3)).getText();

			assertTrue("Contact list sort not from A to Z", (s1.compareTo(s2) == -1 && s2.compareTo(s3) == -1));
		} else {
			assertTrue("UI Element not exist: " + Constant.ID_INDEX_BAR, false);
		}

		printLog("step6=Click back icon");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Doule confirm donot exist", waitForTextExists(Constant.TXT_CANCEL_CALL_MEETING, 2000));

		printLog("step7=Back to Message list");
		clickText(Constant.TXT_YES, true);
		assertTrue("Click yes to back Message list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 2000));
	}

	public void testUI_TitleBar_CallMeeting_Create_SelectContacts_Org() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step5=Verify Ui element");
		assertTrue("Ui Element not exist: Head image", waitForResourceId(Constant.ID_HEAD_IMAGEE, 3000));
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 2000));

		printLog("step6=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		UiObject org_path = getObjectOnResourceId(Constant.ID_ORG_PATH);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Ui Element not exist: org path", org_path.waitForExists(2000));
		assertTrue("Ui Element not exist: show list icon", waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));

		UiObject orgName = org_path.getChild(new UiSelector().className("android.widget.TextView").index(0));
		String org_name = null;
		if (orgName.exists()) {
			org_name = orgName.getText();
			assertTrue("Org Name is empty", (org_name != null && !org_name.equals("")));
		} else {
			assertTrue("Org Name is empty", false);
		}

		printLog("step7=Click branch contact");
		while (waitForResourceId(Constant.ID_CHECK_NUMBER, 2000)) {
			clickResourceId(Constant.ID_CHECK_NUMBER);
		}

		UiObject checkbox = getObjectOnResourceIdAndIndex(Constant.ID_CHECKBOX, 1);
		assertTrue("Click branch failed.", checkbox.waitForExists(2000));

		checkbox.clickAndWaitForNewWindow();
		assertTrue("Checkbox not be select!", checkbox.isChecked());
	}

	public void testUI_TitleBar_CallMeeting_NewGroup_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			printLog("step5=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			assertTrue("Select contact2 failed.", contact2Check.isChecked());

			printLog("step6=Click the confirm button");
			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			assertTrue("UI Element not exist:" + Constant.TXT_NEW_CALLMEETING,
					waitForTextExists(Constant.TXT_NEW_CALLMEETING, 3000));

		} else {
			assertTrue("This account has no contact", false);
		}
	}

	public void testUI_TitleBar_CallMeeting_NewGroup_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			printLog("step5=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			assertTrue("Select contact2 failed.", contact2Check.isChecked());

			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			printLog("step6=Veirfy UI Element");
			assertTrue("UI Element not exist:" + Constant.TXT_NEW_CALLMEETING,
					waitForTextExists(Constant.TXT_NEW_CALLMEETING, 3000));
			assertTrue("UI Element not exist:" + Constant.TXT_ADD_MEMBER,
					waitForTextExists(Constant.TXT_ADD_MEMBER, 2000));
			assertTrue("UI Element not exist:" + Constant.TXT_MSG_NOTIFY,
					waitForTextExists(Constant.TXT_MSG_NOTIFY, 2000));
			assertTrue("UI Element not exist:" + Constant.TXT_MEETING_TITLE,
					waitForTextExists(Constant.TXT_MEETING_TITLE, 2000));
			assertTrue("UI Element not exist:" + Constant.TXT_MEETING_TITLE_INPUT,
					waitForTextExists(Constant.TXT_MEETING_TITLE_INPUT, 2000));
			assertTrue("UI Element not exist: ID_BACK", waitForResourceId(Constant.ID_BACK, 2000));
			assertTrue("UI Element not exist: ID_TITLEBAR_CONFIRM",
					waitForResourceId(Constant.ID_TITLEBAR_CONFIRM, 2000));
			assertTrue("UI Element not exist: ID_GROUP_REMOVE", waitForResourceId(Constant.ID_GROUP_REMOVE, 2000));
			assertTrue("UI Element not exist: ID_MSG_NOTIFY_CHECK",
					waitForResourceId(Constant.ID_MSG_NOTIFY_CHECK, 2000));
			assertTrue("UI Element not exist: ID_MEETING_TITLE_INPUT",
					waitForResourceId(Constant.ID_MEETING_TITLE_INPUT, 2000));

		} else {
			assertTrue("This account has no contact", false);
		}
	}

	public void testUI_TitleBar_CallMeeting_NewGroup_Contacts() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject contact2Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);

		if (contact1Check.waitForExists(2000) && contact2Check.waitForExists(2000)) {

			printLog("step5=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			assertTrue("Select contact1 failed.", contact1Check.isChecked());

			contact2Check.clickAndWaitForNewWindow();
			assertTrue("Select contact2 failed.", contact2Check.isChecked());

			printLog("step6=Click the confirm button");
			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			assertTrue("UI Element not exist:" + Constant.TXT_NEW_CALLMEETING,
					waitForTextExists(Constant.TXT_NEW_CALLMEETING, 3000));

			UiObject contact = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER, 1);

			if (contact.exists()) {

				UiObject contactHead = contact.getChild(new UiSelector().resourceId(Constant.ID_HEAD_IMAGEE));
				String name = contact.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_NAME)).getText();
				String department = contact.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_DEPARTMENT))
						.getText();

				assertTrue("Contact Head not exist", contactHead.exists());
				assertTrue("Contact Name is empty", (name != null && !name.equals("")));
				assertTrue("Contact Department is empty", (department != null && !department.equals("")));

			} else {
				assertTrue("This group has no contact", false);
			}

		} else {
			assertTrue("This account has no contact", false);
		}
	}

	public void testUI_TitleBar_CallMeeting_NewGroup_Branch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		UiObject org_path = getObjectOnResourceId(Constant.ID_ORG_PATH);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Ui Element not exist: org path", org_path.waitForExists(2000));
		assertTrue("Ui Element not exist: show list icon", waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));

		UiObject checkDepartment = getObjectOnResourceIdAndInstance(Constant.ID_DEPART_CHECKBOX, 1);
		if (checkDepartment.exists()) {

			printLog("Select a branch in org");
			checkDepartment.clickAndWaitForNewWindow();
			assertTrue("Branch not be select", checkDepartment.isChecked());

			printLog("step6=Click confirm button");
			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			assertTrue("UI Element not exist:" + Constant.TXT_NEW_CALLMEETING,
					waitForTextExists(Constant.TXT_NEW_CALLMEETING, 3000));

			printLog("step6=Veirfy Branch Element");

			UiObject branch = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER, 0);

			String number = getTextOnResourceId(Constant.ID_TITLEBAR_CONFIRM, 2000);
			assertTrue("Group number is 0", !number.startsWith(Constant.TXT_SELECT_CONFIRM0));

			if (branch.exists()) {

				UiObject contactHead = branch.getChild(new UiSelector().resourceId(Constant.ID_HEAD_IMAGEE));
				String name = branch.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_NAME)).getText();
				String department = branch.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_DEPARTMENT))
						.getText();

				assertTrue("branch Head exist", !contactHead.exists());
				assertTrue("branch Name is empty", (name != null && !name.equals("")));
				assertTrue("Department is empty", (department != null && !department.equals("")));

			} else {
				assertTrue("This group has no branch", false);
			}

		} else {
			assertTrue("Department not exist", false);
		}
	}

	public void testUI_TitleBar_AddContacts_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.addContacts_Enter(phoneNum, password);
	}

	public void testUI_TitleBar_AddContacts_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.addContacts_Enter(phoneNum, password);

		printLog("step5=Verify UI Element");
		assertTrue("Ui Element not exist: " + Constant.TXT_FIND_ET, waitForTextExists(Constant.TXT_FIND_ET, 3000));
		assertTrue("Ui Element not exist: " + Constant.TXT_LIST_TITLE, waitForTextExists(Constant.TXT_LIST_TITLE, 2000));
		assertTrue("UI Element not exist: ID_INDEX_BAR", waitForResourceId(Constant.ID_INDEX_BAR, 2000));
		assertTrue("UI Element not exist: ID_BACK", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("UI Element not exist: ID_LIST", waitForResourceId(Constant.ID_LIST, 2000));
	}

	public void testUI_TitleBar_AddContacts_Contact_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.addContacts_Enter(phoneNum, password);

		printLog("step5=Verify UI Element");
		assertTrue("Ui Element not exist: " + Constant.TXT_FIND_ET, waitForTextExists(Constant.TXT_FIND_ET, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_LIST_TITLE, waitForTextExists(Constant.TXT_LIST_TITLE, 2000));

		printLog("step6=Verify Contact UI Element");
		if (waitForResourceId(Constant.ID_CONTACT_EMPTY, 5000)) {
			printLog("The contact list is empty!");
		} else {
			String name = getObjectOnResourceIdAndInstance(Constant.ID_NAME, 0).getText();
			String num = getObjectOnResourceIdAndInstance(Constant.ID_TEL_NUM, 0).getText();

			assertTrue("Contact's name is empyty", (name != null && !name.equals("")));
			assertTrue("Contact's num is empyty", (num != null && !num.equals("")));
		}

	}

	public void testUI_TitleBar_AddContacts_Contact_Sort() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.addContacts_Enter(phoneNum, password);

		printLog("step5=Verify UI Element");
		assertTrue("Ui Element not exist: " + Constant.TXT_FIND_ET, waitForTextExists(Constant.TXT_FIND_ET, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_LIST_TITLE, waitForTextExists(Constant.TXT_LIST_TITLE, 2000));

		printLog("step5=Verify Contact UI Element");
		UiObject list = getObjectOnResourceId(Constant.ID_INDEX_BAR);
		String s1 = list.getChild(new UiSelector().className("android.widget.TextView").index(0)).getText();
		String s2 = list.getChild(new UiSelector().className("android.widget.TextView").index(1)).getText();
		String s3 = list.getChild(new UiSelector().className("android.widget.TextView").index(2)).getText();

		assertTrue("Contact's sort is not A to Z", (s1.compareTo(s2) == -1 && s2.compareTo(s3) == -1));

		printLog("step6=Click back icon");
		clickResourceId(Constant.ID_BACK);

		printLog("step7=Back to Message list");
		assertTrue("Click yes to back Message list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 2000));
	}

	public void testUI_TitleBar_ScanQRCode_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click titlebar_plus");
		clickResourceId(Constant.ID_TITLEBAR_PLUS);
		sleep(500);

		String iconArr[] = getConfigProperty("titlebar_popup_plus").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);

		double diff = getScreenDiffWithReference(Constant.RES_FILE_PATH + "titlebar_popup_plus", x1, y1, x2, y2);
		printLog("diff=" + diff);

		assertTrue("POPUP_PLUS not match expect", diff < Constant.DIFF_MIN);

		printLog("step4=Click " + Constant.TXT_TITLEBAR_SCAN_QR_CODE);

		String pointArr[] = getConfigProperty("scan_qr_code").split(",");
		int x = Integer.parseInt(pointArr[0]);
		int y = Integer.parseInt(pointArr[1]);

		clickPoint(new Point(x, y));
		waitForWindowUpdate(3000);
		assertTrue("Ui Element not exist: " + Constant.TXT_TITLEBAR_SCAN_QR_CODE,
				waitForTextExists(Constant.TXT_TITLEBAR_SCAN_QR_CODE, 8000));

		printLog("step5=Verify UI Element");
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("Ui Element not exist: ID_QRCODE_PREVIEW_VIEW",
				waitForResourceId(Constant.ID_QRCODE_PREVIEW_VIEW, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_QRCODE_TIP, waitForTextExists(Constant.TXT_QRCODE_TIP, 2000));
	}

	public void testUI_TitleBar_ScanQRCode_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click titlebar_plus");
		clickResourceId(Constant.ID_TITLEBAR_PLUS);
		sleep(500);

		String iconArr[] = getConfigProperty("titlebar_popup_plus").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);

		double diff = getScreenDiffWithReference(Constant.RES_FILE_PATH + "titlebar_popup_plus", x1, y1, x2, y2);
		printLog("diff=" + diff);

		assertTrue("POPUP_PLUS not match expect", diff < Constant.DIFF_MIN);

		printLog("step4=Click " + Constant.TXT_TITLEBAR_SCAN_QR_CODE);

		String pointArr[] = getConfigProperty("scan_qr_code").split(",");
		int x = Integer.parseInt(pointArr[0]);
		int y = Integer.parseInt(pointArr[1]);

		clickPoint(new Point(x, y));
		waitForWindowUpdate(3000);
		assertTrue("Ui Element not exist: " + Constant.TXT_TITLEBAR_SCAN_QR_CODE,
				waitForTextExists(Constant.TXT_TITLEBAR_SCAN_QR_CODE, 8000));

		printLog("step5=Click back icon");
		clickResourceId(Constant.ID_BACK);

		printLog("step6=Back to Message list");
		assertTrue("Click yes to back Message list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 2000));
	}

	public void testUI_TitleBar_TitleName() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Verify Title name");
		assertTrue("The title name not expect: " + Constant.TXT_TITLE_NAME,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_TITLE_NAME));
	}

	public void testUI_TitleBar_NoNet() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Turn off netWork");
		turnOffNetWorkByCMD();
		TurnOnNetWork = true;

		sleep(5000);

		assertTrue("UI element not exist: ID_NONET_IMG_ERR", waitForResourceId(Constant.ID_NONET_IMG_ERR, 5000));
		assertTrue("UI element not exist: " + Constant.TXT_NONET_RETRY,
				waitForTextExists(Constant.TXT_NONET_RETRY, 2000));
		assertTrue("UI element not exist: " + Constant.TXT_NONET_TIPS, waitForTextExists(Constant.TXT_NONET_TIPS, 2000));
		assertTrue("UI element not exist: " + Constant.TXT_NONET_TITLE,
				waitForTextExists(Constant.TXT_NONET_TITLE, 2000));
	}

	public void testUI_Msg_MsgList_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Verify UI element");
		assertTrue("The title name not expect: " + Constant.TXT_TITLE_NAME,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_TITLE_NAME));
		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));
		assertTrue("Ui Element not exist: ID_TOOLBAR_ADDRESS", waitForResourceId(Constant.ID_TOOLBAR_ADDRESS, 2000));
		assertTrue("Ui Element not exist: ID_TOOLBAR_INTEREST", waitForResourceId(Constant.ID_TOOLBAR_INTEREST, 2000));
		assertTrue("Ui Element not exist: ID_TOOLBAR_MORE", waitForResourceId(Constant.ID_TOOLBAR_MORE, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_TOOLBAR_MSG,
				waitForTextExists(Constant.TXT_TOOLBAR_MSG, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_TOOLBAR_ADDRESS,
				waitForTextExists(Constant.TXT_TOOLBAR_ADDRESS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_TOOLBAR_INTEREST,
				waitForTextExists(Constant.TXT_TOOLBAR_INTEREST, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_TOOLBAR_MORE,
				waitForTextExists(Constant.TXT_TOOLBAR_MORE, 2000));
		assertTrue("Ui Element not exist: ID_TITLEBAR_SEARCH", waitForResourceId(Constant.ID_TITLEBAR_SEARCH, 2000));
		assertTrue("Ui Element not exist: ID_TITLEBAR_PLUS", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));

	}

	public void testUI_Msg_MsgList_List() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Verify MSG list UI element");
		UiObject msg = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER, 0);
		UiObject head = msg.getChild(new UiSelector().resourceId(Constant.ID_HEAD_IMAGEE));
		UiObject name = msg.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_NAME));
		UiObject time = msg.getChild(new UiSelector().resourceId(Constant.ID_TV_TIME));
		UiObject content = msg.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_DEPARTMENT));

		if (msg.exists()) {
			assertTrue("Msg UI element not exist: Head", head.exists());
			assertTrue("Msg UI element not exist: time", name.exists());
			assertTrue("Msg UI element not exist: name", time.exists());
			assertTrue("Msg UI element not exist: content", content.exists());

			assertTrue("Msg UI element: name is empty", (name.getText() != null && !name.getText().equals("")));
			assertTrue("Msg UI element: time is empty", (time.getText() != null && !time.getText().equals("")));
			assertTrue("Msg UI element: content is empty", (content.getText() != null && !content.getText().equals("")));

		} else {
			assertTrue("Msg list has no msg", false);
		}

	}

	public void testUI_Msg_MsgList_ListTime() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Verify MSG list time");

		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_TV_TIME));
		ArrayList<String> timeList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			UiObject timeObj = getObjectOnResourceIdAndInstance(Constant.ID_TV_TIME, i);
			if (timeObj.exists()) {
				timeList.add(timeObj.getText().trim());
			}
		}

		boolean result = helper.verifyMsgListTimeDisplay(timeList);
		assertTrue("Msg time display not match regulation", result);

	}

	public void testUI_Msg_MsgList_ListSort() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Verify MSG list time");

		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_TV_TIME));
		ArrayList<String> timeList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			UiObject timeObj = getObjectOnResourceIdAndInstance(Constant.ID_TV_TIME, i);
			if (timeObj.exists()) {
				timeList.add(timeObj.getText().trim());
			}
		}

		boolean result = helper.verifyMsgListTimeSort(timeList);
		assertTrue("Msg time sort not match regulation", result);
	}

	public void testUI_Msg_LanxinTeam_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String pkg = getConfigProperty("pkg");
		if (pkg.equals("com.gudong.client")) {
			assertTrue("The test app not exist: " + Constant.TXT_LANXIN_TEAM, false);
		}

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click " + Constant.TXT_LANXIN_TEAM);
		scrollToFindText(Constant.TXT_LANXIN_TEAM, "Vertical", 5).clickAndWaitForNewWindow();
		assertTrue("Click Lanxin Team failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_LANXIN_TEAM));

		printLog("step5=Click back icon");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Back to Msg list failed", waitForResourceId(Constant.ID_LIST, 2000));
	}

	public void testUI_Msg_LanxinTeam_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String pkg = getConfigProperty("pkg");
		if (pkg.equals("com.gudong.client")) {
			assertTrue("The test app not exist: " + Constant.TXT_LANXIN_TEAM, false);
		}

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click " + Constant.TXT_LANXIN_TEAM);
		scrollToFindText(Constant.TXT_LANXIN_TEAM, "Vertical", 5).clickAndWaitForNewWindow();
		assertTrue("Click Lanxin Team failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_LANXIN_TEAM));

		printLog("step4=Verify Lanxin Team UI Element");
		assertTrue("UI Element not exist Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("UI Element not exist Setting icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("UI Element not exist ID_LANXIN_TEAM_LIST", waitForResourceId(Constant.ID_LANXIN_TEAM_LIST, 2000));
		assertTrue("UI Element not exist: " + Constant.ID_LANXIN_TEAM_MORE,
				waitForResourceId(Constant.ID_LANXIN_TEAM_MORE, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_PRODUCT_DETAIL,
				waitForTextExists(Constant.TXT_PRODUCT_DETAIL, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_OPERATION_GUIDE,
				waitForTextExists(Constant.TXT_OPERATION_GUIDE, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_LANXIN_ONLINE,
				waitForTextExists(Constant.TXT_LANXIN_ONLINE, 2000));
	}

	public void testUI_Msg_LanxinTeam_Setting_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String pkg = getConfigProperty("pkg");
		if (pkg.equals("com.gudong.client")) {
			assertTrue("The test app not exist: " + Constant.TXT_LANXIN_TEAM, false);
		}

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click " + Constant.TXT_LANXIN_TEAM);
		scrollToFindText(Constant.TXT_LANXIN_TEAM, "Vertical", 5).clickAndWaitForNewWindow();
		assertTrue("Click Lanxin Team failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_LANXIN_TEAM));

		printLog("step4=Click Setting icon");
		clickResourceId(Constant.ID_TITLEBAR_PLUS);
		assertTrue("Enter lanxin team setting failed.", waitForResourceId(Constant.ID_LANXIN_TEAM_HEAD, 3000));

		printLog("step5=Verify Lanxin team setting UI element");
		assertTrue("Ui Element not exist: ID_LANXIN_TEAM_HEAD", waitForResourceId(Constant.ID_LANXIN_TEAM_HEAD, 2000));
		assertTrue("Ui Element not exist: ID_BACK", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_LANXIN_TEAM_INTRODUCE,
				waitForTextExists(Constant.TXT_LANXIN_TEAM_INTRODUCE, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_LANXIN_TEAM_SUMMARY,
				waitForTextExists(Constant.TXT_LANXIN_TEAM_SUMMARY, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_LANXIN_TEAM_TOP,
				waitForTextExists(Constant.TXT_LANXIN_TEAM_TOP, 2000));
		assertTrue("The title not match " + Constant.TXT_LANXIN_TEAM,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_LANXIN_TEAM));
	}

	public void testUI_Msg_BatchDealMsg_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		String chatName = helper.createPrivateChatBySelectContactInContacts(phoneNum, password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Open a dialogue");
		clickText(chatName, true);
		assertTrue("The dialogue has no message", waitForResourceId(Constant.ID_MSG_VG, 2000));

		printLog("step4=Long click a message");
		longClickResourceId(Constant.ID_MSG_VG);
		assertTrue("Long click message failed", waitForResourceId(Constant.ID_SELECT_DIALOG_LISTVIEW, 3000));

		printLog("step5=Select More in options list");
		clickText(Constant.TXT_SELECT_DIALOG_LIST_MORE, true);
		assertTrue("Select More in option list failed", waitForResourceId(Constant.ID_BATCH_DEAL_MSG, 2000));

		printLog("step6=Verify Ui Element");
		assertTrue("Ui Element not exist: ID_MSG_CHECKBOX", waitForResourceId(Constant.ID_MSG_CHECKBOX, 2000));
		assertTrue("Ui Element not exist: ID_TITLEBAR_TITLE", waitForResourceId(Constant.ID_TITLEBAR_TITLE, 2000));
		assertTrue("Ui Element not exist: ID_TITLEBAR_LEFT_CANCEL",
				waitForResourceId(Constant.ID_TITLEBAR_LEFT_CANCEL, 2000));
		assertTrue("Ui Element not exist: ID_BATCH_DELETE", waitForResourceId(Constant.ID_BATCH_DELETE, 2000));
		assertTrue("Ui Element not exist: ID_BATCH_FAVORITE", waitForResourceId(Constant.ID_BATCH_FAVORITE, 2000));
		assertTrue("Ui Element not exist: ID_BATCH_SHARE", waitForResourceId(Constant.ID_BATCH_SHARE, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_POPUP_SEARCH_CANCEL,
				waitForTextExists(Constant.TXT_POPUP_SEARCH_CANCEL, 2000));
	}

	public void testUI_Address_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Verify org UI Element");
		assertTrue("Ui Element not exist:" + Constant.TXT_ADDRESS_CONTACT,
				waitForTextExists(Constant.TXT_ADDRESS_CONTACT, 3000));
		assertTrue("Ui Element not exist:" + Constant.TXT_TOOLBAR_ADDRESS,
				waitForTextExists(Constant.TXT_TOOLBAR_ADDRESS, 2000));
		assertTrue("Ui Element not exist:" + Constant.TXT_TOOLBAR_MSG,
				waitForTextExists(Constant.TXT_TOOLBAR_MSG, 2000));
		assertTrue("Ui Element not exist:" + Constant.TXT_TOOLBAR_INTEREST,
				waitForTextExists(Constant.TXT_TOOLBAR_INTEREST, 2000));
		assertTrue("Ui Element not exist:" + Constant.TXT_TOOLBAR_MORE,
				waitForTextExists(Constant.TXT_TOOLBAR_MORE, 2000));

		assertTrue("Ui Element not exist: ID_TITLEBAR_SEARCH", waitForResourceId(Constant.ID_TITLEBAR_SEARCH, 2000));
		assertTrue("Ui Element not exist: ID_TITLEBAR_PLUS", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: ID_ORG_PATH", waitForResourceId(Constant.ID_ORG_PATH, 2000));
		assertTrue("Ui Element not exist: ID_SHOW_ORG_LIST", waitForResourceId(Constant.ID_SHOW_ORG_LIST, 2000));
		assertTrue("Ui Element not exist: ID_ADDRESS_FLOAT_TARGET",
				waitForResourceId(Constant.ID_ADDRESS_FLOAT_TARGET, 2000));

		printLog("step4=Click " + Constant.TXT_ADDRESS_CONTACT);
		clickText(Constant.TXT_ADDRESS_CONTACT, true);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step5=Verify contacts UI Element");
		assertTrue("Ui Element not exist:" + Constant.TXT_ADDRESS_CONTACT,
				waitForTextExists(Constant.TXT_ADDRESS_CONTACT, 3000));
		assertTrue("Ui Element not exist:" + Constant.TXT_TOOLBAR_ADDRESS,
				waitForTextExists(Constant.TXT_TOOLBAR_ADDRESS, 2000));
		assertTrue("Ui Element not exist:" + Constant.TXT_TOOLBAR_MSG,
				waitForTextExists(Constant.TXT_TOOLBAR_MSG, 2000));
		assertTrue("Ui Element not exist:" + Constant.TXT_TOOLBAR_INTEREST,
				waitForTextExists(Constant.TXT_TOOLBAR_INTEREST, 2000));
		assertTrue("Ui Element not exist:" + Constant.TXT_TOOLBAR_MORE,
				waitForTextExists(Constant.TXT_TOOLBAR_MORE, 2000));
		if (!waitForTextExists(Constant.TXT_ADDRESS_ADMIN, 2000)) {
			printLog("Ui Element not exist:" + Constant.TXT_ADDRESS_ADMIN);
		}
		assertTrue("Ui Element not exist:" + Constant.TXT_ADDRESS_NEW_CONTACT,
				waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 2000));
		assertTrue("Ui Element not exist:" + Constant.TXT_ADDRESS_TOPCONTACT,
				waitForTextExists(Constant.TXT_ADDRESS_TOPCONTACT, 2000));

		assertTrue("Ui Element not exist: ID_TITLEBAR_SEARCH", waitForResourceId(Constant.ID_TITLEBAR_SEARCH, 2000));
		assertTrue("Ui Element not exist: ID_TITLEBAR_PLUS", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: ID_INDEX_BAR", waitForResourceId(Constant.ID_INDEX_BAR, 2000));

	}

	public void testUI_Address_Contacts_NewContact_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click " + Constant.TXT_ADDRESS_CONTACT);
		clickText(Constant.TXT_ADDRESS_CONTACT, true);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 3000));

		printLog("step4=Click " + Constant.TXT_ADDRESS_NEW_CONTACT);
		clickText(Constant.TXT_ADDRESS_NEW_CONTACT, true);
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Ui Element not exist: " + Constant.TXT_ADDRESS_NEW_CONTACT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_ADDRESS_NEW_CONTACT));
		if (!waitForResourceId(Constant.ID_LIST, 2000)) {
			printLog("Ui Element not exist: List");
		}
	}

	public void testUI_Address_Contacts_List() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click " + Constant.TXT_ADDRESS_CONTACT);
		clickText(Constant.TXT_ADDRESS_CONTACT, true);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 3000));
		assertTrue("Ui Element not exist:" + Constant.TXT_ADDRESS_TOPCONTACT,
				waitForTextExists(Constant.TXT_ADDRESS_TOPCONTACT, 2000));
		assertTrue("Ui Element not exist:" + Constant.TXT_ADDRESS_TOPCONTACT, waitForResourceId(Constant.ID_LIST, 2000));
	}

	public void testUI_Address_Contacts_Sort() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click " + Constant.TXT_ADDRESS_CONTACT);
		clickText(Constant.TXT_ADDRESS_CONTACT, true);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 3000));

		printLog("step4=Verify contact list sort");
		UiObject indexBar = getObjectOnResourceId(Constant.ID_INDEX_BAR);

		if (indexBar.exists()) {

			String s1 = indexBar.getChild(new UiSelector().index(1)).getText();
			String s2 = indexBar.getChild(new UiSelector().index(2)).getText();
			String s3 = indexBar.getChild(new UiSelector().index(3)).getText();

			assertTrue("Contact list sort not from A to Z", (s1.compareTo(s2) == -1 && s2.compareTo(s3) == -1));
		} else {
			assertTrue("UI Element not exist: " + Constant.ID_INDEX_BAR, false);
		}
	}

	public void testUI_Address_Contacts_Details() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click " + Constant.TXT_ADDRESS_CONTACT);
		clickText(Constant.TXT_ADDRESS_CONTACT, true);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 3000));

		printLog("step4=Verify contact details");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_TV_JOB_RANKT));
		for (int i = 0; i < count; i++) {

			UiObject HeadObj = list.getChild(new UiSelector().resourceId(Constant.ID_HEAD_IMAGEE).instance(i));
			UiObject nameObj = list.getChild(new UiSelector().resourceId(Constant.ID_GROUP_MEMBER_NAME).instance(i));
			UiObject departmentObj = list.getChild(new UiSelector().resourceId(Constant.ID_TV_JOB_RANKT).instance(i));
			if (nameObj.exists()) {
				printLog(i + "=" + nameObj.getText());
				assertTrue("Contact name is empty", nameObj.getText() != null && !nameObj.getText().equals(""));
			} else {
				assertTrue("Contact name not exist", false);
			}
			if (HeadObj.exists()) {
			} else {
				assertTrue("Contact head not exist", false);
			}
			if (departmentObj.exists()) {
				assertTrue("Contact department is empty", departmentObj.getText() != null
						&& !departmentObj.getText().equals(""));
			} else {
				assertTrue("Contact department not exist", false);
			}
		}

	}

	public void testUI_Address_Contacts_NameCard_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click " + Constant.TXT_ADDRESS_CONTACT);
		clickText(Constant.TXT_ADDRESS_CONTACT, true);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 3000));

		printLog("step4=Click one contact name card");
		clickResourceId(Constant.ID_GROUP_MEMBER_NAME);
		assertTrue("Click contact name card failed.", waitForTextExists(Constant.TXT_NAME_CARD, 3000));

		printLog("step5=Verify name card element");
		assertTrue("Ui Element not exist: " + Constant.TXT_NAME_CARD, waitForTextExists(Constant.TXT_NAME_CARD, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_NAMECARD_SEND_LINXIN,
				waitForTextExists(Constant.TXT_NAMECARD_SEND_LINXIN, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_NAMECARD_SOFT_PHONE,
				waitForTextExists(Constant.TXT_NAMECARD_SOFT_PHONE, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_NAMECARD_SEND_MAIL,
				waitForTextExists(Constant.TXT_NAMECARD_SEND_MAIL, 2000));

		if (waitForResourceId(Constant.ID_NAMECARD_UNREGISTERED, 3000)) {
			assertTrue("Ui Element not exist: " + Constant.TXT_NAMECARD_INVITE,
					waitForTextExists(Constant.TXT_NAMECARD_INVITE, 2000));
			assertTrue("Ui Element not exist: ID_NAMECARD_INVITE", waitForResourceId(Constant.ID_NAMECARD_INVITE, 2000));
		} else {
			assertTrue("Ui Element not exist: " + Constant.TXT_NAMECARD_CALL,
					waitForTextExists(Constant.TXT_NAMECARD_CALL, 2000));
			assertTrue("Ui Element not exist: ID_NAMECARD_CALL", waitForResourceId(Constant.ID_NAMECARD_CALL, 2000));
		}

		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_SETTING", waitForResourceId(Constant.ID_NAMECARD_SETTING, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_SEND_LINXIN",
				waitForResourceId(Constant.ID_NAMECARD_SEND_LINXIN, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_SEND_MAIL",
				waitForResourceId(Constant.ID_NAMECARD_SEND_MAIL, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_SOFT_PHONE",
				waitForResourceId(Constant.ID_NAMECARD_SOFT_PHONE, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_HEAD", waitForResourceId(Constant.ID_NAMECARD_HEAD, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_NAME", waitForResourceId(Constant.ID_NAMECARD_NAME, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_JOB_CONTENT",
				waitForResourceId(Constant.ID_NAMECARD_JOB_CONTENT, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_DETAILS", waitForResourceId(Constant.ID_NAMECARD_DETAILS, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_INTRODUCE",
				waitForResourceId(Constant.ID_NAMECARD_INTRODUCE, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_SIGN", waitForResourceId(Constant.ID_NAMECARD_SIGN, 2000));
		// assertTrue("Ui Element not exist: ID_NAMECARD_SEX",
		// waitForResourceId(Constant.ID_NAMECARD_SEX, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_COMPANY", waitForResourceId(Constant.ID_NAMECARD_COMPANY, 2000));
		// assertTrue("Ui Element not exist: ID_NAMECARD_ARROWOTHER",
		// waitForResourceId(Constant.ID_NAMECARD_ARROWOTHER, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_POSITION", waitForResourceId(Constant.ID_NAMECARD_POSITION, 2000));

	}

	public void testUI_Address_Contacts_Admin_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click " + Constant.TXT_ADDRESS_CONTACT);
		clickText(Constant.TXT_ADDRESS_CONTACT, true);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 3000));
		assertTrue("UI element not exist: " + Constant.TXT_ADDRESS_ADMIN,
				waitForTextExists(Constant.TXT_ADDRESS_ADMIN, 3000));

		printLog("step4=Click " + Constant.TXT_ADDRESS_ADMIN);
		clickText(Constant.TXT_ADDRESS_ADMIN, true);
		assertTrue("Click Contact admin failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_ADDRESS_ADMIN));

		printLog("step5=Verify admin Ui element");
		assertTrue("UI element not exist: BACK icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("UI element not exist: Head image", waitForResourceId(Constant.ID_HEAD_IMAGEE, 2000));
		assertTrue("UI element not exist: Name", waitForResourceId(Constant.ID_GROUP_MEMBER_NAME, 2000));
		assertTrue("UI element not exist: Department", waitForResourceId(Constant.ID_TV_JOB_RANKT, 2000));

	}

	public void testUI_Interest_Title() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));

		printLog("step3=Verify Interest title");
		assertTrue("Title name is not: " + Constant.TXT_TOOLBAR_MSG,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_TOOLBAR_MSG));
	}

	public void testUI_Interest_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));

		printLog("step3=Verify Interest UI Element");
		assertTrue("Interest UI Element not exist: " + Constant.TXT_INTEREST_CHAT_GROUP,
				waitForTextExists(Constant.TXT_INTEREST_CHAT_GROUP, 2000));
		assertTrue("Interest UI Element not exist: " + Constant.TXT_INTEREST_RSS_ACCOUNT,
				waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 2000));
		assertTrue("Interest UI Element not exist: " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT,
				waitForTextExists(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT, 2000));

		assertTrue("Interest UI Element not exist: ID_TITLEBAR_PLUS",
				waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Interest UI Element not exist: ID_INTEREST_IMAGE",
				waitForResourceId(Constant.ID_INTEREST_IMAGE, 2000));
		assertTrue("Interest UI Element not exist: ID_INTEREST_EXPAND_LIST",
				waitForResourceId(Constant.ID_INTEREST_EXPAND_LIST, 2000));
		assertTrue("Interest UI Element not exist: ID_INTEREST_PERFERENCE",
				waitForResourceId(Constant.ID_INTEREST_PERFERENCE, 2000));

		assertTrue("Interest UI Element not exist: " + Constant.TXT_TOOLBAR_ADDRESS,
				waitForTextExists(Constant.TXT_TOOLBAR_ADDRESS, 2000));
		assertTrue("Interest UI Element not exist: " + Constant.TXT_TOOLBAR_MORE,
				waitForTextExists(Constant.TXT_TOOLBAR_MORE, 2000));
		assertTrue("Interest UI Element not exist: " + Constant.TXT_TOOLBAR_INTEREST,
				waitForTextExists(Constant.TXT_TOOLBAR_INTEREST, 2000));
		assertTrue("Interest UI Element not exist: " + Constant.TXT_TOOLBAR_MSG,
				waitForTextExists(Constant.TXT_TOOLBAR_MSG, 2000));
	}

	public void testUI_Interest_DepartmentDocument_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));
		assertTrue("Interest UI Element not exist: " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT,
				waitForTextExists(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT, 2000));

		printLog("step3=Click " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT);
		clickText(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT, true);
		waitForWindowUpdate(3000);
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000).equals(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT));
	}

	public void testUI_Interest_DepartmentDocument_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));
		assertTrue("Interest UI Element not exist: " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT,
				waitForTextExists(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT, 2000));

		printLog("step3=Click " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT);
		clickText(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT, true);
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 3000).equals(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT));

		printLog("step4=Verify Department Document UI Element");
		assertTrue("Interest UI Element not exist: Back icon", waitForResourceId(Constant.ID_INTEREST_DD_BAKC, 3000));
		assertTrue("Interest UI Element not exist: Find et", waitForResourceId(Constant.ID_INTEREST_DD_FIND, 2000));
		assertTrue("Interest UI Element not exist: Folder name",
				waitForResourceId(Constant.ID_INTEREST_DD_FOLDER_NAME, 2000));
		assertTrue("Interest UI Element not exist: Folder sub",
				waitForResourceId(Constant.ID_INTEREST_DD_FOLDER_SUB, 2000));
		assertTrue("Interest UI Element not exist: Arrow icon", waitForResourceId(Constant.ID_INTEREST_DD_ARROW, 2000));
		assertTrue("Interest UI Element not exist: List", waitForResourceId(Constant.ID_INTEREST_DD_LIST, 2000));
		assertTrue("Interest UI Element not exist: Image icon", waitForResourceId(Constant.ID_INTEREST_DD_IMAGE, 2000));

	}

	public void testUI_Interest_DepartmentDocument_FileList_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));
		assertTrue("Interest UI Element not exist: " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT,
				waitForTextExists(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT, 2000));

		printLog("step3=Click " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT);
		clickText(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT, true);
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT));

		printLog("step4=Click a folder");
		String folderName = getTextOnResourceIdAndInstance(Constant.ID_INTEREST_DD_FOLDER_NAME, 2000, 0);
		clickText(folderName, true);
		assertTrue("Folder name is not match " + folderName, getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000)
				.equals(folderName));
	}

	public void testUI_Interest_DepartmentDocument_FileList_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));
		assertTrue("Interest UI Element not exist: " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT,
				waitForTextExists(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT, 2000));

		printLog("step3=Click " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT);
		clickText(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT, true);
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_INTEREST_DEPARTMENT_DOCUMENT));

		printLog("step4=Click a folder");
		String folderName = getTextOnResourceIdAndInstance(Constant.ID_INTEREST_DD_FOLDER_NAME, 2000, 0);
		clickText(folderName, true);
		assertTrue("Folder name is not match " + folderName, getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000)
				.equals(folderName));

		printLog("step5=Verify Folder UI Element");
		assertTrue("Interest UI Element not exist: Back icon",
				waitForResourceId(Constant.ID_INTEREST_DD_FOLDER_BACK, 3000));
		assertTrue("Interest UI Element not exist: Find et",
				waitForResourceId(Constant.ID_INTEREST_DD_FOLDER_FIND, 2000));
		assertTrue("Interest UI Element not exist: File name",
				waitForResourceId(Constant.ID_INTEREST_DD_FOLDER_FILE_NAME, 2000));
		assertTrue("Interest UI Element not exist: File detail",
				waitForResourceId(Constant.ID_INTEREST_DD_FOLDER_FILE_DETAIL, 2000));
		assertTrue("Interest UI Element not exist: More icon",
				waitForResourceId(Constant.ID_INTEREST_DD_FOLDER_MORE, 2000));
	}

	public void testUI_Interest_GroupChat_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));

		printLog("step3=Click " + Constant.TXT_INTEREST_CHAT_GROUP);
		clickText(Constant.TXT_INTEREST_CHAT_GROUP, true);
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_CHAT_GROUP,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_INTEREST_CHAT_GROUP));
	}

	public void testUI_Interest_GroupChat_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));

		printLog("step3=Click " + Constant.TXT_INTEREST_CHAT_GROUP);
		clickText(Constant.TXT_INTEREST_CHAT_GROUP, true);
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_CHAT_GROUP,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_INTEREST_CHAT_GROUP));

		printLog("step4=Verify Chat group UI Element");
		assertTrue("Interest UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 3000));
		assertTrue("Interest UI Element not exist: Arrow icon", waitForResourceId(Constant.ID_INTEREST_CG_ARROW, 2000));
		assertTrue("Interest UI Element not exist: Find et", waitForResourceId(Constant.ID_INTEREST_CG_FIND, 2000));
		assertTrue("Interest UI Element not exist: Head", waitForResourceId(Constant.ID_INTEREST_CG_HEAD, 2000));
		assertTrue("Interest UI Element not exist: List", waitForResourceId(Constant.ID_INTEREST_CG_LIST, 2000));
		assertTrue("Interest UI Element not exist: name", waitForResourceId(Constant.ID_INTEREST_CG_NAME, 2000));
	}

	public void testUI_Interest_GroupChat_GroupInfo_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));

		printLog("step3=Click " + Constant.TXT_INTEREST_CHAT_GROUP);
		clickText(Constant.TXT_INTEREST_CHAT_GROUP, true);
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_CHAT_GROUP,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_INTEREST_CHAT_GROUP));

		String groupName = getTextOnResourceIdAndInstance(Constant.ID_INTEREST_CG_NAME, 2000, 0);
		printLog("step4=Click a group");
		clickText(groupName, true);
		assertTrue("Click a group failed.", waitForTextExists(Constant.TXT_GROUP_INFO, 2000));

		printLog("step5=Verify group info UI element");
		assertTrue("Title not match " + groupName,
				groupName.contains(getTextOnResourceId(Constant.ID_GROUP_INFO_NAME, 2000)));
		assertTrue("Ui element not exist: " + Constant.TXT_GROUP_JOIN, waitForTextExists(Constant.TXT_GROUP_JOIN, 2000)
				|| waitForTextExists(Constant.TXT_GROUP_APPLYED, 2000));
		assertTrue("Ui element not exist: " + Constant.TXT_GROUP_INTRODUCTION,
				waitForTextExists(Constant.TXT_GROUP_INTRODUCTION, 2000));
		assertTrue("Ui element not exist: " + Constant.TXT_GROUP_OWNER_INFO,
				waitForTextExists(Constant.TXT_GROUP_OWNER_INFO, 2000));

		assertTrue("Ui element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("Ui element not exist: Share icon", waitForResourceId(Constant.ID_GROUP_INFO_SHARE, 2000));
		assertTrue("Ui element not exist: owner head", waitForResourceId(Constant.ID_GROUP_INFO_OWNER_HEAD, 2000));
		assertTrue("Ui element not exist: owner department", waitForResourceId(Constant.ID_GROUP_INFO_OWNER_DEPARTMENT, 2000));
		assertTrue("Ui element not exist: owner company", waitForResourceId(Constant.ID_GROUP_INFO_OWNER_COMPANY, 2000));
		assertTrue("Ui element not exist: owner position", waitForResourceId(Constant.ID_GROUP_INFO_OWNER_POSITION, 2000));
		assertTrue("Ui element not exist: owner name", waitForResourceId(Constant.ID_GROUP_INFO_OWNER_NAME, 2000));
		assertTrue("Ui element not exist: Head icon", waitForResourceId(Constant.ID_GROUP_INFO_HEAD, 2000));

		printLog("step6=Click the Group owner");
		clickResourceId(Constant.ID_GROUP_INFO_OWNER_COMPANY);
		assertTrue("Click contact name card failed.", waitForTextExists(Constant.TXT_NAME_CARD, 3000));

		printLog("step7=Verify name card element");
		assertTrue("Ui Element not exist: " + Constant.TXT_NAME_CARD, waitForTextExists(Constant.TXT_NAME_CARD, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_NAMECARD_CALL,
				waitForTextExists(Constant.TXT_NAMECARD_CALL, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_NAMECARD_SEND_LINXIN,
				waitForTextExists(Constant.TXT_NAMECARD_SEND_LINXIN, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_NAMECARD_SOFT_PHONE,
				waitForTextExists(Constant.TXT_NAMECARD_SOFT_PHONE, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_NAMECARD_SEND_MAIL,
				waitForTextExists(Constant.TXT_NAMECARD_SEND_MAIL, 2000));

		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_SETTING", waitForResourceId(Constant.ID_NAMECARD_SETTING, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_SEND_LINXIN",
				waitForResourceId(Constant.ID_NAMECARD_SEND_LINXIN, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_SEND_MAIL",
				waitForResourceId(Constant.ID_NAMECARD_SEND_MAIL, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_CALL", waitForResourceId(Constant.ID_NAMECARD_CALL, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_SOFT_PHONE",
				waitForResourceId(Constant.ID_NAMECARD_SOFT_PHONE, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_HEAD", waitForResourceId(Constant.ID_NAMECARD_HEAD, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_NAME", waitForResourceId(Constant.ID_NAMECARD_NAME, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_JOB_CONTENT",
				waitForResourceId(Constant.ID_NAMECARD_JOB_CONTENT, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_DETAILS", waitForResourceId(Constant.ID_NAMECARD_DETAILS, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_INTRODUCE",
				waitForResourceId(Constant.ID_NAMECARD_INTRODUCE, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_SIGN", waitForResourceId(Constant.ID_NAMECARD_SIGN, 2000));
		// assertTrue("Ui Element not exist: ID_NAMECARD_SEX",
		// waitForResourceId(Constant.ID_NAMECARD_SEX, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_COMPANY", waitForResourceId(Constant.ID_NAMECARD_COMPANY, 2000));
		// assertTrue("Ui Element not exist: ID_NAMECARD_ARROWOTHER",
		// waitForResourceId(Constant.ID_NAMECARD_ARROWOTHER, 2000));
		assertTrue("Ui Element not exist: ID_NAMECARD_POSITION", waitForResourceId(Constant.ID_NAMECARD_POSITION, 2000));
	}

	public void testUI_Interest_RssAccount_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));

		printLog("step3=Click " + Constant.TXT_INTEREST_RSS_ACCOUNT);
		clickText(Constant.TXT_INTEREST_RSS_ACCOUNT, true);
		waitForWindowUpdate(5000);
		while (waitForTextExists(Constant.TXT_NOTIFY_WAIT, 2000)) {
			sleep(2000);
		}
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_RSS_ACCOUNT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000).equals(Constant.TXT_INTEREST_RSS_ACCOUNT));
	}

	public void testUI_Interest_RssAccount_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));

		printLog("step3=Click " + Constant.TXT_INTEREST_RSS_ACCOUNT);
		clickText(Constant.TXT_INTEREST_RSS_ACCOUNT, true);
		waitForWindowUpdate(3000);
		assertTrue("Ui Element not exist: " + Constant.TXT_INTEREST_RSS_ACCOUNT,
				waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_RSS_ACCOUNT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000).equals(Constant.TXT_INTEREST_RSS_ACCOUNT));

		printLog("step4=Verify Rss account UI element");
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("Ui Element not exist: Head image", waitForResourceId(Constant.ID_RSS_ACCOUNT_HEAD, 2000));
		assertTrue("Ui Element not exist: GRIDVIEW", waitForResourceId(Constant.ID_RSS_ACCOUNT_GRIDVIEW, 2000));
		assertTrue("Ui Element not exist: Name", waitForResourceId(Constant.ID_RSS_ACCOUNT_NAME, 2000));

	}

	public void testUI_Interest_RssAccount_List() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));

		printLog("step3=Click " + Constant.TXT_INTEREST_RSS_ACCOUNT);
		clickText(Constant.TXT_INTEREST_RSS_ACCOUNT, true);
		waitForWindowUpdate(3000);
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_RSS_ACCOUNT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000).equals(Constant.TXT_INTEREST_RSS_ACCOUNT));

		printLog("step4=Verify Rss account list");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_RSS_ACCOUNT_GRIDVIEW));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_RSS_ACCOUNT_NAME));
		for (int i = 0; i < count; i++) {
			assertTrue(i + "rss account head not exist",
					list.getChild(new UiSelector().resourceId(Constant.ID_RSS_ACCOUNT_HEAD).instance(i)).exists());
			UiObject nameObj = list.getChild(new UiSelector().resourceId(Constant.ID_RSS_ACCOUNT_NAME).instance(i));
			assertTrue(i + "rss account name not exist", nameObj.exists());
			assertTrue(i + "rss account name is empty", nameObj.getText() != null && !nameObj.getText().equals(""));

		}
	}

	public void testUI_Interest_RssAccount_Account_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));

		printLog("step3=Click " + Constant.TXT_INTEREST_RSS_ACCOUNT);
		clickText(Constant.TXT_INTEREST_RSS_ACCOUNT, true);
		waitForWindowUpdate(3000);
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_RSS_ACCOUNT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000).equals(Constant.TXT_INTEREST_RSS_ACCOUNT));

		printLog("step4=Click a account");
		String acctountName = getTextOnResourceIdAndInstance(Constant.ID_RSS_ACCOUNT_NAME, 2000, 0);
		clickText(acctountName, true);
		assertTrue("Click a account failed.", getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000)
				.equals(acctountName));
	}

	public void testUI_Interest_RssAccount_Account_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_INTEREST);
		clickText(Constant.TXT_TOOLBAR_INTEREST, true);
		waitForWindowUpdate(5000);
		assertTrue("Click Interest failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));

		printLog("step3=Click " + Constant.TXT_INTEREST_RSS_ACCOUNT);
		clickText(Constant.TXT_INTEREST_RSS_ACCOUNT, true);
		waitForWindowUpdate(3000);
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_RSS_ACCOUNT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000).equals(Constant.TXT_INTEREST_RSS_ACCOUNT));

		printLog("step4=Click a account");
		String acctountName = getTextOnResourceIdAndInstance(Constant.ID_RSS_ACCOUNT_NAME, 2000, 0);
		clickText(acctountName, true);
		assertTrue("Click a account failed.", getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000)
				.equals(acctountName));

		printLog("step5=Verify Rss account info");
		assertTrue("Ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("Ui Element not exist: Head image", waitForResourceId(Constant.ID_RSS_ACCOUNT_HEAD, 2000));
		assertTrue("Ui Element not exist: Name", waitForResourceId(Constant.ID_RSS_ACCOUNT_NAME, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_RSS_INTRODUCTION,
				waitForTextExists(Constant.TXT_RSS_INTRODUCTION, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_RSS_TOP,
				waitForTextContainsExists(Constant.TXT_RSS_TOP, 2000));
	}

	public void testUI_More_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=Verify Ui Element");
		assertTrue("Title not match " + Constant.TXT_TOOLBAR_MSG, getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000)
				.equals(Constant.TXT_TOOLBAR_MSG));

		assertTrue("Ui Element not exist: " + Constant.TXT_MORE_MY_DOCUMENT,
				waitForTextExists(Constant.TXT_MORE_MY_DOCUMENT, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_MORE_MY_FAVORITE,
				waitForTextExists(Constant.TXT_MORE_MY_FAVORITE, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_MORE_SETTINGS,
				waitForTextExists(Constant.TXT_MORE_SETTINGS, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_MORE_HELP, waitForTextExists(Constant.TXT_MORE_HELP, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_MORE_GUIDE, waitForTextExists(Constant.TXT_MORE_GUIDE, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_MORE_CHECK_VERSION,
				waitForTextExists(Constant.TXT_MORE_CHECK_VERSION, 2000));
		assertTrue("Ui Element not exist: " + Constant.TXT_MORE_ABOUT, waitForTextExists(Constant.TXT_MORE_ABOUT, 2000));
//		assertTrue("Ui Element not exist: " + Constant.TXT_MORE_EDIT_SIGN,
//				waitForTextExists(Constant.TXT_MORE_EDIT_SIGN, 2000));

		assertTrue("Ui Element not exist: Plus icon", waitForResourceId(Constant.ID_TITLEBAR_PLUS, 2000));
		assertTrue("Ui Element not exist: Image icon", waitForResourceId(Constant.ID_MORE_IMAGE, 2000));
		assertTrue("Ui Element not exist: PREFERENCE icon", waitForResourceId(Constant.ID_MORE_PREFERENCE, 2000));
		assertTrue("Ui Element not exist: Edit sign", waitForResourceId(Constant.ID_MORE_SUMMARY, 2000));

	}

	public void testUI_More_Person_BasicInfo_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click Person info");
		getObjectOnResourceIdAndInstance(Constant.ID_MORE_PREFERENCE, 0).clickAndWaitForNewWindow();
		assertTrue("Enter person info failed.", waitForTextExists(Constant.TXT_MORE_PERSON_TITLE, 3000));

		printLog("step4=Verify Person info Ui Element");
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_PERSON_HEAD,
				waitForTextExists(Constant.TXT_MORE_PERSON_HEAD, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_PERSON_SEX,
				waitForTextExists(Constant.TXT_MORE_PERSON_SEX, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_PERSON_SIGN,
				waitForTextExists(Constant.TXT_MORE_PERSON_SIGN, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_PERSON_QRCODE,
				waitForTextExists(Constant.TXT_MORE_PERSON_QRCODE, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_PERSON_JOB,
				waitForTextExists(Constant.TXT_MORE_PERSON_JOB, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_PERSON_INTRODUCE,
				waitForTextExists(Constant.TXT_MORE_PERSON_INTRODUCE, 2000));

		assertTrue("UI Element not exist: head arrow", waitForResourceId(Constant.ID_MORE_PERSON_ARROW_HEAD, 2000));
		assertTrue("UI Element not exist: sex arrow", waitForResourceId(Constant.ID_MORE_PERSON_ARROW_SEX, 2000));
		assertTrue("UI Element not exist: sign arrow", waitForResourceId(Constant.ID_MORE_PERSON_ARROW_SIGN, 2000));
		assertTrue("UI Element not exist: introduce arrow",
				waitForResourceId(Constant.ID_MORE_PERSON_ARROW_INTRODUCE, 2000));
		assertTrue("UI Element not exist: job arrow", waitForResourceId(Constant.ID_MORE_PERSON_ARROW_JOB, 2000));

		assertTrue("UI Element not exist: Head icon", waitForResourceId(Constant.ID_MORE_PERSON_HEAD, 2000));
		assertTrue("UI Element not exist: sex", waitForResourceId(Constant.ID_MORE_PERSON_SEX, 2000));
		assertTrue("UI Element not exist: sign ", waitForResourceId(Constant.ID_MORE_PERSON_SIGN, 2000));
		assertTrue("UI Element not exist: qrcode ", waitForResourceId(Constant.ID_MORE_PERSON_QRCODE, 2000));
		assertTrue("UI Element not exist: introduce ", waitForResourceId(Constant.ID_MORE_PERSON_INTRODUCE, 2000));
		assertTrue("UI Element not exist: job ", waitForResourceId(Constant.ID_MORE_PERSON_JOB, 2000));
		assertTrue("UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));

	}

	public void testUI_More_Person_BasicInfo_NotFilled() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click Person info");
		getObjectOnResourceIdAndInstance(Constant.ID_MORE_PREFERENCE, 0).clickAndWaitForNewWindow();
		assertTrue("Enter person info failed.", waitForTextExists(Constant.TXT_MORE_PERSON_TITLE, 3000));

		printLog("step4=Click " + Constant.TXT_MORE_PERSON_INTRODUCE);
		clickText(Constant.TXT_MORE_PERSON_INTRODUCE, true);
		assertTrue("Click set sign  failed.", waitForTextExists(Constant.TXT_SET_SAVE, 3000));

		printLog("step5=Change introduce and save");
		clearEditText(20);
		clickText(Constant.TXT_SET_SAVE, true);
		assertTrue("Set sign failed", waitForTextExists(Constant.TXT_NOT_EDIT, 3000));
	}

	public void testUI_More_Person_QRCode_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click Person info");
		getObjectOnResourceIdAndInstance(Constant.ID_MORE_PREFERENCE, 0).clickAndWaitForNewWindow();
		assertTrue("Enter person info failed.", waitForTextExists(Constant.TXT_MORE_PERSON_TITLE, 3000));

		printLog("step4=click QR code");
		clickText(Constant.TXT_MORE_PERSON_QRCODE, true);
		assertTrue("Enter QR code failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_PERSON_QRCODE));

		printLog("step4=Verify QR code Ui Element");
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_QRCODE_DES,
				waitForTextExists(Constant.TXT_MORE_QRCODE_DES, 2000));

		assertTrue("UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("UI Element not exist: Share icon", waitForResourceId(Constant.ID_MORE_QRCODE_SHARE, 2000));
		if (waitForResourceId(Constant.ID_MORE_QRCODE_SEX, 2000)) {
			printLog("The account not set Sex or not display!");
		}
		assertTrue("UI Element not exist: name", waitForResourceId(Constant.ID_MORE_QRCODE_NAME, 2000));
		assertTrue("UI Element not exist: Head icon", waitForResourceId(Constant.ID_MORE_QRCODE_HEAD, 2000));
		assertTrue("UI Element not exist: Department", waitForResourceId(Constant.ID_MORE_QRCODE_DEPARTMENT, 2000));
		assertTrue("UI Element not exist: IMG", waitForResourceId(Constant.ID_MORE_QRCODE_IMG, 2000));
		assertTrue("UI Element not exist: description", waitForResourceId(Constant.ID_MORE_QRCODE_DES, 2000));

	}

	public void testUI_More_Person_QRCode_Name() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		String personName = getTextOnResourceIdAndInstance(Constant.ID_MORE_TITLE, 2000, 0);

		printLog("step3=click Person info");
		getObjectOnResourceIdAndInstance(Constant.ID_MORE_PREFERENCE, 0).clickAndWaitForNewWindow();
		assertTrue("Enter person info failed.", waitForTextExists(Constant.TXT_MORE_PERSON_TITLE, 3000));

		printLog("step4=click QR code");
		clickText(Constant.TXT_MORE_PERSON_QRCODE, true);
		assertTrue("Enter QR code failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_PERSON_QRCODE));

		printLog("step5=Verify QR code name");
		assertTrue("The name not match " + personName,
				getTextOnResourceId(Constant.ID_MORE_QRCODE_NAME, 2000).equals(personName));

	}

	public void testUI_More_Person_QRCode_SexIcon() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click Person info");
		getObjectOnResourceIdAndInstance(Constant.ID_MORE_PREFERENCE, 0).clickAndWaitForNewWindow();
		assertTrue("Enter person info failed.", waitForTextExists(Constant.TXT_MORE_PERSON_TITLE, 3000));

		printLog("step4=click QR code");
		clickText(Constant.TXT_MORE_PERSON_QRCODE, true);
		assertTrue("Enter QR code failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_PERSON_QRCODE));

		printLog("step5=Verify QR code sex");
		if (waitForResourceId(Constant.ID_MORE_QRCODE_SEX, 2000)) {
			Rect bound = getBoundsByResourceID(Constant.ID_MORE_QRCODE_SEX);
			boolean result = isImageSame(Constant.RES_FILE_PATH + "qrcode_setting", bound.left, bound.top, bound.right,
					bound.bottom);
			assertTrue("The sex icon not match expect", result);
		} else {
			printLog("The account not set sex");
		}

	}

	public void testUI_More_Person_QRCode_Department() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click Person info");
		getObjectOnResourceIdAndInstance(Constant.ID_MORE_PREFERENCE, 0).clickAndWaitForNewWindow();
		assertTrue("Enter person info failed.", waitForTextExists(Constant.TXT_MORE_PERSON_TITLE, 3000));

		printLog("step4=click QR code");
		clickText(Constant.TXT_MORE_PERSON_QRCODE, true);
		assertTrue("Enter QR code failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_PERSON_QRCODE));

		printLog("step5=Verify QR code department");
		String department = getConfigProperty("department");
		assertTrue("The department not match: " + department,
				getTextOnResourceId(Constant.ID_MORE_QRCODE_DEPARTMENT, 2000).equals(department));
	}

	public void testUI_More_Person_QRCode_Img() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click Person info");
		getObjectOnResourceIdAndInstance(Constant.ID_MORE_PREFERENCE, 0).clickAndWaitForNewWindow();
		assertTrue("Enter person info failed.", waitForTextExists(Constant.TXT_MORE_PERSON_TITLE, 3000));

		printLog("step4=click QR code");
		clickText(Constant.TXT_MORE_PERSON_QRCODE, true);
		assertTrue("Enter QR code failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_PERSON_QRCODE));

		printLog("step5=Verify QR code img");
		Rect bound = getBoundsByResourceID(Constant.ID_MORE_QRCODE_IMG);
		boolean result = isImageSame(Constant.RES_FILE_PATH + "qrcode_setting", bound.left, bound.top, bound.right,
				bound.bottom);
		assertTrue("The QRcode not match expect", result);
	}

	public void testUI_More_MyDocument_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_MY_DOCUMENT);
		clickText(Constant.TXT_MORE_MY_DOCUMENT, true);
		assertTrue("Enter My Document failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_MY_DOCUMENT));
	}

	public void testUI_More_MyDocument_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_MY_DOCUMENT);
		clickText(Constant.TXT_MORE_MY_DOCUMENT, true);
		assertTrue("Enter My Document failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_MY_DOCUMENT));

		printLog("step4=Verify My document UI Element");
		assertTrue("UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("UI Element not exist: Upload button", waitForResourceId(Constant.ID_MORE_MY_DOCUMENT_UPLOAD, 2000));

		if (waitForResourceId(Constant.ID_MORE_MY_DOCUMENT_EMPTY, 2000)) {
			printLog("The current My Document is empty!");
			return;
		}

		assertTrue("UI Element not exist: Details", waitForResourceId(Constant.ID_MORE_MY_DOCUMENT_DETAILS, 3000));
		assertTrue("UI Element not exist: File name", waitForResourceId(Constant.ID_MORE_MY_DOCUMENT_NAME, 2000));
		assertTrue("UI Element not exist: Head icon", waitForResourceId(Constant.ID_MORE_MY_DOCUMENT_HEAD, 2000));
		assertTrue("UI Element not exist: upload time", waitForResourceId(Constant.ID_MORE_MY_DOCUMENT_TIME, 2000));
		assertTrue("UI Element not exist: Forbid", waitForResourceId(Constant.ID_MORE_MY_DOCUMENT_FORBID, 2000));
		assertTrue("UI Element not exist: Upload button", waitForResourceId(Constant.ID_MORE_MY_DOCUMENT_UPLOAD, 2000));
		assertTrue("UI Element not exist: File list", waitForResourceId(Constant.ID_MORE_MY_DOCUMENT_LIST, 2000));
	}

	public void testUI_More_MyDocument_Empty() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		helper.clearAppUserData();
		LogOut = true;

		String phoneNum = getConfigProperty("single_phoneNum");
		String password = getConfigProperty("single_password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_MY_DOCUMENT);
		clickText(Constant.TXT_MORE_MY_DOCUMENT, true);
		assertTrue("Enter My Document failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_MY_DOCUMENT));

		printLog("step4=Verify My document UI Element");
		assertTrue("My document has any file", waitForResourceId(Constant.ID_MORE_MY_DOCUMENT_EMPTY, 3000));
	}

	public void testUI_More_MyDocument_File_Sort() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_MY_DOCUMENT);
		clickText(Constant.TXT_MORE_MY_DOCUMENT, true);
		assertTrue("Enter My Document failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_MY_DOCUMENT));

		printLog("step4=Verify My document sort");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_MORE_MY_DOCUMENT_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_MORE_MY_DOCUMENT_TIME));
		ArrayList<String> timeList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			String time = list.getChild(new UiSelector().resourceId(Constant.ID_MORE_MY_DOCUMENT_TIME).instance(i))
					.getText();
			timeList.add(time.trim());
		}

		boolean result = helper.verifyFileListTimeSort(timeList);
		assertTrue("File time sort not match regulation", result);
	}

	public void testUI_More_MyFavorite_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_MY_FAVORITE);
		clickText(Constant.TXT_MORE_MY_FAVORITE, true);
		assertTrue("Enter My Favorite failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000).equals(Constant.TXT_MORE_MY_FAVORITE));
	}

	public void testUI_More_MyFavorite_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_MY_FAVORITE);
		clickText(Constant.TXT_MORE_MY_FAVORITE, true);
		assertTrue("Enter My Favorite failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_MY_FAVORITE));

		printLog("step4=Verify My Favorite UI Element");
		assertTrue("UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));

		if (waitForResourceId(Constant.ID_MORE_MY_FAVORITE_EMPTY, 2000)) {
			printLog("The current My favorite is empty!");
			return;
		}

		assertTrue("UI Element not exist: Export icon", waitForResourceId(Constant.ID_MORE_MY_FAVORITE_EXPORT, 2000));
		assertTrue("UI Element not exist: List", waitForResourceId(Constant.ID_MORE_MY_FAVORITE_LIST, 2000));
		assertTrue("UI Element not exist: Head icon",
				waitForResourceId(Constant.ID_MORE_MY_FAVORITE_MESSAGE_HEAD, 2000));
		assertTrue("UI Element not exist: Message item name",
				waitForResourceId(Constant.ID_MORE_MY_FAVORITE_MESSAGE_ITEM_NAME, 2000));
		assertTrue("UI Element not exist: Message details",
				waitForResourceId(Constant.ID_MORE_MY_FAVORITE_MESSAGE_VG, 2000));
		assertTrue("UI Element not exist: Message send time",
				waitForResourceId(Constant.ID_MORE_MY_FAVORITE_SEND_TIME, 2000));
		assertTrue("UI Element not exist: Message source", waitForResourceId(Constant.ID_MORE_MY_FAVORITE_SOURCE, 2000));
		assertTrue("UI Element not exist: Favorite time", waitForResourceId(Constant.ID_MORE_MY_FAVORITE_TIME, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_MY_FAVORITE_TIME,
				waitForTextExists(Constant.TXT_MORE_MY_FAVORITE_TIME, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_MY_FAVORITE_SEND_TIME,
				waitForTextExists(Constant.TXT_MORE_MY_FAVORITE_SEND_TIME, 2000));
	}

	public void testUI_More_MyFavorite_Empty() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		helper.clearAppUserData();
		LogOut = true;

		String phoneNum = getConfigProperty("single_phoneNum");
		String password = getConfigProperty("single_password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_MY_FAVORITE);
		clickText(Constant.TXT_MORE_MY_FAVORITE, true);
		assertTrue("Enter My Favorite failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_MY_FAVORITE));

		printLog("step4=Verify My Favorite Ui Element");
		assertTrue("My Favorite has any favorite", waitForResourceId(Constant.ID_MORE_MY_FAVORITE_EMPTY, 3000));

	}

	public void testUI_More_MyFavorite_Sort() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_MY_FAVORITE);
		clickText(Constant.TXT_MORE_MY_FAVORITE, true);
		assertTrue("Enter My Favorite failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_MY_FAVORITE));

		printLog("step4=Verify My Favorite sort");

		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_MORE_MY_FAVORITE_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_MORE_MY_FAVORITE_TIME));
		ArrayList<String> timeList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			String time = list.getChild(new UiSelector().resourceId(Constant.ID_MORE_MY_FAVORITE_TIME).instance(i))
					.getText();
			timeList.add(time.trim());
		}
		boolean result = helper.verifyFileListTimeSort(timeList);
		assertTrue("File time sort not match regulation", result);
	}

	public void testUI_More_Settings_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_SETTINGS);
		clickText(Constant.TXT_MORE_SETTINGS, true);
		assertTrue("Enter Settings failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_MORE_SETTINGS));

		printLog("step4=Verify Settings UI Element");
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_ACCEPT,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_ACCEPT, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_CALL,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_CALL, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_DISPLAY,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_DISPLAY, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_DISPLAY_SUM,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_DISPLAY_SUM, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_FONT,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_FONT, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_LOCK,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_LOCK, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_NOTIFY,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_NOTIFY, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_SHAKE,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_SHAKE, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_SOUND,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_SOUND, 2000));
		dragDirection("up", 35);
		dragDirection("up", 35);
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_NUMBER,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_NUMBER, 3000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_PASSWORD,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_PASSWORD, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_SETTINGS_LOGOUT,
				waitForTextExists(Constant.TXT_MORE_SETTINGS_LOGOUT, 2000));
	}

	public void testUI_More_Help_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_HELP);
		clickText(Constant.TXT_MORE_HELP, true);
		waitForWindowUpdate(3000);
		assertTrue("Enter My Favorite failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 8000).equals(Constant.TXT_MORE_HELP_TITLE));
	}

	public void testUI_More_Help_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_HELP);
		clickText(Constant.TXT_MORE_HELP, true);
		waitForWindowUpdate(3000);
		assertTrue("Enter Help failed.", waitForTextExists(Constant.TXT_MORE_HELP_TITLE, 8000));

		printLog("step4=Verify Help UI Element");
		assertTrue("UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("UI Element not exist: Export icon", waitForResourceId(Constant.ID_MORE_HELP_SHARE, 2000));
	}

	public void testUI_More_Guide_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_GUIDE);
		clickText(Constant.TXT_MORE_GUIDE, true);
		waitForWindowUpdate(3000);
		assertTrue("Enter Guide failed.", waitForTextExists(Constant.TXT_NEXT, 3000));

		printLog("step4=Verify Guide UI Element");
		assertTrue("UI Element not exist: Image icon", waitForResourceId(Constant.ID_MORE_GUIDE_IMAGE, 2000));
		// assertTrue("UI Element not exist: Point icon",
		// waitForResourceId(Constant.ID_MORE_GUIDE_POINTS, 2000));
		assertTrue("UI Element not exist: Next button", waitForResourceId(Constant.ID_MORE_GUIDE_NEXT, 2000));

		while (waitForTextExists(Constant.TXT_NEXT, 2000)) {
			clickResourceId(Constant.ID_NEXT);
		}
	}

	public void testUI_More_About_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_MORE_ABOUT);
		clickText(Constant.TXT_MORE_ABOUT, true);
		waitForWindowUpdate(3000);
		assertTrue("Enter More failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 3000).equals(Constant.TXT_MORE_ABOUT));

		printLog("step4=Verify About UI Element");
		assertTrue("UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("UI Element not exist: Img icon", waitForResourceId(Constant.ID_MORE_ABOUT_IMG, 2000));
		assertTrue("UI Element not exist: name", waitForResourceId(Constant.ID_MORE_ABOUT_NAME, 2000));
		assertTrue("UI Element not exist: Version", waitForResourceId(Constant.ID_MORE_ABOUT_VERSION, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_MORE_ABOUT_TEAM,
				waitForTextExists(Constant.TXT_MORE_ABOUT_TEAM, 2000));
	}

	public void testUI_TitleBar_PlatformSwitch_MultiOrg() throws UiObjectNotFoundException {

		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("multi_phoneNum");
		String password = getConfigProperty("multi_password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=Verify the platform switch icon exist.");
		assertTrue("Platform switch icon not exist", waitForResourceId(Constant.ID_PLATFORM_SWITCH, 30000));
	}

	public void testUI_TitleBar_PlatformSwitch_SingleOrg() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("single_phoneNum");
		String password = getConfigProperty("single_password");

		helper.clearAppUserData();
		LogOut = true;

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		assertTrue("Click More failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step3=Verify the platform switch icon not exist.");
		assertTrue("Platform switch icon exist", !waitForResourceId(Constant.ID_PLATFORM_SWITCH, 30000));
	}

	public void testUI_Msg_PrivateChat_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("Case-Setup--createPrivateChatBySelectContactInContacts");
		String chatName = helper.createPrivateChatBySelectContactInContacts(phoneNum, password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click The private chat ");
		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));

		printLog("step4=Verify the private chat UI Element");
		assertTrue("The chat title is empty", !getTextOnResourceIdAndInstance(Constant.ID_TITLEBAR_TITLE, 2000, 0)
				.equals(""));
		assertTrue("The UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("The UI Element not exist: Settings icon", waitForResourceId(Constant.ID_CHAT_SETTINGS, 2000));
		assertTrue("The UI Element not exist: Msg list", waitForResourceId(Constant.ID_CHAT_LIST, 2000));
		assertTrue("The UI Element not exist: Emotion icon", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION, 2000));
		assertTrue("The UI Element not exist: Tool menu", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 2000));
		assertTrue("The UI Element not exist: Msg input editer", waitForResourceId(Constant.ID_CHAT_MSG_INPUT_ET, 2000));
		assertTrue("The UI Element not exist: Msg send voice", waitForResourceId(Constant.ID_CHAT_MSG_SEND_IV, 2000));

		if (!chatName.equals(Constant.TXT_MY_NAME)) {
			assertTrue("The UI Element not exist: Call icon", waitForResourceId(Constant.ID_CHAT_CALL, 2000));
		}
	}

	public void testUI_Msg_PrivateChat_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("Case-Setup--createPrivateChatBySelectContactInContacts");
		String chatName = helper.createPrivateChatBySelectContactInContacts(phoneNum, password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click The private chat ");
		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));

		printLog("step4=Click back icon");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Click back failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));
	}

	public void testUI_Msg_PrivateChat_Title() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("Case-Setup--createPrivateChatBySelectContactInContacts");
		String chatName = helper.createPrivateChatBySelectContactInContacts(phoneNum, password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click The private chat ");
		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));

		printLog("step4=Verify the private chat title");
		assertTrue("The chat title not match: " + chatName,
				getTextOnResourceIdAndInstance(Constant.ID_TITLEBAR_TITLE, 2000, 0).equals(chatName));
	}

	public void testUI_Msg_PrivateChat_ContentDisplay() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		String chatName = helper.createPrivateChatBySelectContactInContacts(phoneNum, password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click The private chat ");
		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));

		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
		int count = 0;
		int last_y = 0;
		String lastMsg = "";
		if (waitForResourceId(Constant.ID_CHAT_MSG_VIEW_INFO, 2000)) {
			count = list.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_VIEW_INFO));
			lastMsg = getObjectOnResourceIdAndInstance(Constant.ID_CHAT_MSG_VIEW_INFO, count - 1).getText();
		}

		printLog("step4=Click input et and input some text");
		Date d = new Date();
		String longtime = String.valueOf(d.getTime());
		clickResourceId(Constant.ID_CHAT_MSG_INPUT_ET);
		waitForWindowUpdate(2000);
		enterTextInEditor(longtime, "android.widget.EditText", 0);

		printLog("step5=Click the send button");
		clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		pressKey("back");
		waitForWindowUpdate(3000);
		assertTrue("Send the msg failed.", waitForTextExists(longtime, 3000));

		printLog("step6=Verify Content display");
		int y = 0;
		y = getObjectOnTextAndInstance(longtime, 0).getBounds().centerY();
		if (!lastMsg.equals("")) {
			last_y = getObjectOnTextAndInstance(lastMsg, 0).getBounds().centerY();
		}
		printLog("y=" + y + "  last_y=" + last_y);
		assertTrue("The msg content display not match expect", !(y < last_y));
	}

	public void testUI_Msg_PrivateChat_ContactHead() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("Case-Setup--createPrivateChatBySelectContactInContacts");
		String chatName = helper.createPrivateChatBySelectContactInContacts(phoneNum, password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click The private chat ");
		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));

		printLog("step4=Click input et and input some text");
		Date d = new Date();
		String longtime = String.valueOf(d.getTime());
		clickResourceId(Constant.ID_CHAT_MSG_INPUT_ET);
		waitForWindowUpdate(2000);
		enterTextInEditor(longtime, "android.widget.EditText", 0);

		printLog("step5=Click the send button");
		clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		pressKey("back");
		waitForWindowUpdate(2000);
		assertTrue("Send the msg failed.", waitForTextExists(longtime, 3000));

		printLog("step6=Click the contact head");
		clickResourceId(Constant.ID_CHAT_CONTACT_HEAD);
		assertTrue("Click the contact head to enter name card failed.", waitForTextExists(Constant.TXT_NAME_CARD, 3000));
	}

	public void testUI_Msg_PrivateChat_TimeDisplay() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("Case-Setup--createPrivateChatBySelectContactInContacts");
		String chatName = helper.createPrivateChatBySelectContactInContacts(phoneNum, password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click The private chat ");
		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));

		printLog("step4=Click input et and input some text");
		Date d = new Date();
		String longtime = String.valueOf(d.getTime());
		clickResourceId(Constant.ID_CHAT_MSG_INPUT_ET);
		waitForWindowUpdate(2000);
		enterTextInEditor(longtime, "android.widget.EditText", 0);

		printLog("step5=Click the send button");
		clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		pressKey("back");
		assertTrue("Send the msg failed.", waitForTextExists(longtime, 3000));

		printLog("step6=Verify time display");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
		if (waitForResourceId(Constant.ID_CHAT_MSG_SEND_TIME, 2000)) {
			int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_SEND_TIME));
			String timeString = getObjectOnResourceIdAndInstance(Constant.ID_CHAT_MSG_SEND_TIME, count - 1).getText();
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add(timeString.trim());
			printLog("Msg time=" + timeString);
			assertTrue("The msg content display not match expect", helper.verifyMsgListTimeDisplay(arrayList));
		} else {
			printLog("The msg time not exist, maybe send gap is to short!");
		}
	}

	public void testUI_Msg_PrivateChat_Settings_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("Case-Setup--createPrivateChatBySelectContactInContacts");
		String chatName = helper.createPrivateChatBySelectContactInContacts(phoneNum, password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click The private chat ");
		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));

		printLog("step4=Click the settings icon in private chat");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the private chat settings icon failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS, 3000));

		printLog("step5=Verify the settings UI Element");
		assertTrue("The UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("The UI Element not exist: Add member icon",
				waitForResourceId(Constant.ID_CHAT_SETTINGS_ADD_MEMBER, 2000));
		assertTrue("The UI Element not exist: Member head icon",
				waitForResourceId(Constant.ID_CHAT_SETTINGS_MEMBER_HEAD, 2000));
		assertTrue("The UI Element not exist: Memeber name",
				waitForResourceId(Constant.ID_CHAT_SETTINGS_MEMBER_NAME, 2000));
		assertTrue("The UI Element not exist: Member list",
				waitForResourceId(Constant.ID_CHAT_SETTINGS_MEMBER_LIST, 2000));
		assertTrue("The UI Element not exist: Msg top icon", waitForResourceId(Constant.ID_CHAT_SETTINGS_MSG_TOP, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_GROUPCHAT_SETTINGS_TOP,
				waitForTextExists(Constant.TXT_GROUPCHAT_SETTINGS_TOP, 2000));
	}

	public void testUI_Msg_WorkGroup_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		String name1 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 0);
		String name2 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 1);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(2000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		printLog("step9=Verify the group UI Element");
		String title = getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 3000);
		assertTrue("The default group title: " + title + " not contain: " + name1, title.contains(name1));
		assertTrue("The default group title: " + title + " not contain: " + name2, title.contains(name2));
		assertTrue("The group UI Element not exist: " + Constant.TXT_WORKGROUP_DIALOG,
				waitForTextExists(Constant.TXT_WORKGROUP_DIALOG, 2000));
		assertTrue("The group UI Element not exist: " + Constant.TXT_WORKGROUP_NOTE,
				waitForTextExists(Constant.TXT_WORKGROUP_NOTE, 2000));
		assertTrue("The group UI Element not exist: " + Constant.TXT_WORKGROUP_DOCUMENT,
				waitForTextExists(Constant.TXT_WORKGROUP_DOCUMENT, 2000));
		assertTrue("The group UI Element not exist: " + Constant.TXT_WORKGROUP_ALBUM,
				waitForTextExists(Constant.TXT_WORKGROUP_ALBUM, 2000));
		assertTrue("The UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("The UI Element not exist: Settings icon", waitForResourceId(Constant.ID_CHAT_SETTINGS, 2000));
		assertTrue("The UI Element not exist: Emotion icon", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION, 2000));
		assertTrue("The UI Element not exist: Tool menu", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 2000));
		assertTrue("The UI Element not exist: Msg input editer", waitForResourceId(Constant.ID_CHAT_MSG_INPUT_ET, 2000));
		assertTrue("The UI Element not exist: Msg send voice", waitForResourceId(Constant.ID_CHAT_MSG_SEND_IV, 2000));

		UiCollection tabs = new UiCollection(new UiSelector().resourceId(Constant.ID_TABS));
		if (tabs.exists()) {
			int count = tabs.getChildCount(new UiSelector().className("android.widget.RelativeLayout"));
			for (int i = 1; i < count; i++) {
				UiObject tab = tabs.getChild(new UiSelector().className("android.widget.RelativeLayout").instance(i));
				assertTrue("The tab not has the count",
						tab.getChild(new UiSelector().resourceId(Constant.ID_WORKGROUP_TABS_COUNT)).exists());
			}
		}
	}

	public void testUI_Msg_WorkGroup_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(2000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));
		String title = getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 3000);
		String groupName = title.split("\\(")[0];

		printLog("step9=Click the back icon");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Back to Msg list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		clickText(groupName, true);
	}

	public void testUI_Msg_WorkGroup_Title() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		String name1 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 0);
		String name2 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 1);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(2000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		printLog("step9=Verify the group Title");
		String title = getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 3000);
		assertTrue("The default group title: " + title + " not contain: " + name1, title.contains(name1));
		assertTrue("The default group title: " + title + " not contain: " + name2, title.contains(name2));
		assertTrue("The default group title: " + title + " not contain: " + Constant.TXT_WORKGROUP_NUM,
				title.contains(Constant.TXT_WORKGROUP_NUM));
	}

	public void testUI_Msg_WorkGroup_Settings_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		String name1 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 0);
		String name2 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 1);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(2000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		printLog("step9=Click the settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the setting icon to enter Settings page failed.",
				waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step10=Verify the Settings UI Elment");
		assertTrue("The UI Element not exist: " + Constant.TXT_SETTINGS_EDIT_HEAD,
				waitForTextExists(Constant.TXT_SETTINGS_EDIT_HEAD, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_MSG_TOP,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_MSG_TOP, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_MSG_MUTE,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_MSG_MUTE, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_GROUP_MUTE,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_GROUP_MUTE, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_HIDE_NUM,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_HIDE_NUM, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_SHARE_LOCATION,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_SHARE_LOCATION, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_ADMIN_CONFIRM,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_ADMIN_CONFIRM, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_NUM,
				waitForTextExists(Constant.TXT_WORKGROUP_NUM, 2000));
		assertTrue("The UI Element not exist: " + name1, waitForTextContainsExists(name1, 2000));
		assertTrue("The UI Element not exist: " + name2, waitForTextContainsExists(name2, 2000));

		assertTrue("The ui Element not exist: Head icon", waitForResourceId(Constant.ID_WORKGROUP_SETTINGS_HEAD, 2000));
		assertTrue("The ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("The ui Element not exist: Group Summary",
				waitForResourceId(Constant.ID_WORKGROUP_SETTINGS_GROUP_SUMMARY, 2000));
		assertTrue("The ui Element not exist: Settings list",
				waitForResourceId(Constant.ID_WORKGROUP_SETTINGS_LIST, 2000));
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_LIST));
		int itemCount = list.getChildCount(new UiSelector().className("android.widget.RelativeLayout"));
		for (int i = 0; i < itemCount; i++) {

			UiCollection item = new UiCollection(new UiSelector().className("android.widget.RelativeLayout"));
			if (item.getChild(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_ITEM_TITLE)).exists()) {
				if (!item.getChild(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON)).exists()) {
					if (!item.getChild(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_ITEM_TITLE))
							.getText().equals(Constant.TXT_WORKGROUP_SETTINGS_GROUP_QRCODE)) {
						assertTrue("The item not has switch button", false);
					}
				}
			}
		}

		dragDirection("up", 35);
		dragDirection("up", 35);
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_GROUP_QRCODE,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_GROUP_QRCODE, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_TRANSFER_ADMIN,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_TRANSFER_ADMIN, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_HELP_ADMIN,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_HELP_ADMIN, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_MANAGE_PERMISSION,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_MANAGE_PERMISSION, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_SEND_MSG_COUNT,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_SEND_MSG_COUNT, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_DELETE,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_DELETE, 2000));
		assertTrue("The ui Element not exist: Group QRcode",
				waitForResourceId(Constant.ID_WORKGROUP_SETTINGS_GROUP_QRCODE, 2000));
		list = new UiCollection(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_LIST));
		itemCount = list.getChildCount(new UiSelector().className("android.widget.RelativeLayout"));
		for (int i = 0; i < itemCount; i++) {

			UiCollection item = new UiCollection(new UiSelector().className("android.widget.RelativeLayout"));
			if (item.getChild(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_ITEM_TITLE)).exists()) {
				if (!item.getChild(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON)).exists()) {
					if (!item.getChild(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_ITEM_TITLE))
							.getText().equals(Constant.TXT_WORKGROUP_SETTINGS_GROUP_QRCODE)) {
						assertTrue("The item not has switch button", false);
					}
				}
			}
		}
		clickResourceId(Constant.ID_BACK);
		waitForWindowUpdate(3000);
	}

	public void testUI_Msg_WorkGroup_ContentDisplay() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(2000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
		int count = 0;
		int last_y = 0;
		String lastMsg = "";
		if (waitForResourceId(Constant.ID_CHAT_MSG_VIEW_LL, 2000)) {
			count = list.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_VIEW_LL));
			lastMsg = getObjectOnResourceIdAndInstance(Constant.ID_CHAT_MSG_VIEW_LL, count - 1).getText();
		}

		Date d = new Date();
		String longtime = String.valueOf(d.getTime());
		clickResourceId(Constant.ID_CHAT_MSG_INPUT_ET);
		// waitForWindowUpdate(2000);
		// enterTextInEditor(longtime, "android.widget.EditText", 0);
		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input text " + longtime, true);

		printLog("step9=Click the send button");
		clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		pressKey("back");
		waitForWindowUpdate(3000);
		assertTrue("Send the msg failed.", waitForTextExists(longtime, 3000));

		printLog("step10=Verify Content display");
		int y = 0;
		y = getObjectOnTextAndInstance(longtime, 0).getBounds().centerY();
		if (!lastMsg.equals("")) {
			last_y = getObjectOnTextAndInstance(lastMsg, 0).getBounds().centerY();
		}
		printLog("y=" + y + "  last_y=" + last_y);
		assertTrue("The msg content display not match expect", !(y < last_y));
	}

	public void testUI_Msg_WorkGroup_ContactHead() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(2000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		Date d = new Date();
		String longtime = String.valueOf(d.getTime());
		clickResourceId(Constant.ID_CHAT_MSG_INPUT_ET);
		// waitForWindowUpdate(2000);
		// enterTextInEditor(longtime, "android.widget.EditText", 0);
		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input text " + longtime, true);

		printLog("step9=Click the send button");
		clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		pressKey("back");
		waitForWindowUpdate(3000);
		assertTrue("Send the msg failed.", waitForTextExists(longtime, 3000));

		printLog("step10=Click the contact head");
		clickResourceId(Constant.ID_CHAT_CONTACT_HEAD);
		waitForWindowUpdate(3000);
		if (isResourceIdExist(Constant.ID_CHAT_SETTINGS)) {
			clickResourceId(Constant.ID_CHAT_SETTINGS);
			waitForWindowUpdate(3000);
		}
		assertTrue("Click the contact head to enter name card failed.", waitForTextExists(Constant.TXT_NAME_CARD, 3000));
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testUI_Msg_WorkGroup_TimeDisplay() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(2000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		Date d = new Date();
		String longtime = String.valueOf(d.getTime());
		clickResourceId(Constant.ID_CHAT_MSG_INPUT_ET);
		// waitForWindowUpdate(2000);
		// enterTextInEditor(longtime, "android.widget.EditText", 0);
		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input text " + longtime, true);

		printLog("step9=Click the send button");
		clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		pressKey("back");
		waitForWindowUpdate(3000);
		assertTrue("Send the msg failed.", waitForTextExists(longtime, 3000));

		printLog("step10=Verify time display");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
		if (waitForResourceId(Constant.ID_CHAT_MSG_SEND_TIME, 2000)) {
			int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_CHAT_MSG_SEND_TIME));
			String timeString = getObjectOnResourceIdAndInstance(Constant.ID_CHAT_MSG_SEND_TIME, count - 1).getText();
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add(timeString.trim());
			printLog("Msg time=" + timeString);
			assertTrue("The msg content display not match expect", helper.verifyMsgListTimeDisplay(arrayList));
		} else {
			printLog("The msg time not exist, maybe send gap is to short!");
		}
	}

	public void testUI_Msg_GroupChat_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup_Member(phoneNum, password);

		String name1 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 0);
		String name2 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 1);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(2000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		printLog("step9=Verify the group UI Element");
		String title = getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 3000);
		assertTrue("The default group title: " + title + " not contain: " + name1, title.contains(name1));
		assertTrue("The default group title: " + title + " not contain: " + name2, title.contains(name2));
		assertTrue("The UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("The UI Element not exist: Settings icon", waitForResourceId(Constant.ID_CHAT_SETTINGS, 2000));
		assertTrue("The UI Element not exist: Emotion icon", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION, 2000));
		assertTrue("The UI Element not exist: Tool menu", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 2000));
		assertTrue("The UI Element not exist: Msg input editer", waitForResourceId(Constant.ID_CHAT_MSG_INPUT_ET, 2000));
		assertTrue("The UI Element not exist: Msg send voice", waitForResourceId(Constant.ID_CHAT_MSG_SEND_IV, 2000));
	}

	public void testUI_Msg_GroupChat_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(2000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		String title = getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 3000);
		String groupName = title.split("\\(")[0];

		printLog("step9=Click the back icon");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Back to Msg list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		clickText(groupName, true);
	}

	public void testUI_Msg_GroupChat_Title() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup_Member(phoneNum, password);

		String name1 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 0);
		String name2 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 1);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(2000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		printLog("step9=Verify the group Title");
		String title = getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 3000);
		assertTrue("The default group title: " + title + " not contain: " + name1, title.contains(name1));
		assertTrue("The default group title: " + title + " not contain: " + name2, title.contains(name2));
		assertTrue("The default group title: " + title + " not contain: " + Constant.TXT_WORKGROUP_NUM,
				title.contains(Constant.TXT_WORKGROUP_NUM));
	}

	public void testUI_Msg_GroupChat_Settings_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup_Member(phoneNum, password);

		String name1 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 0);
		String name2 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 1);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(2000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		printLog("step9=Click the settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the setting icon to enter Settings page failed.",
				waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step10=Verify the Settings UI Elment");
		assertTrue("The ui Element not exist: Head icon", waitForResourceId(Constant.ID_WORKGROUP_SETTINGS_HEAD, 2000));
		assertTrue("The ui Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("The UI Element not exist: " + name1, waitForTextContainsExists(name1, 2000));
		assertTrue("The UI Element not exist: " + name2, waitForTextContainsExists(name2, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_MSG_TOP,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_MSG_TOP, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_SEND_MSG_COUNT,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_SEND_MSG_COUNT, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_GORKGROUP_SETTINGS_MEMBER,
				waitForTextExists(Constant.TXT_GORKGROUP_SETTINGS_MEMBER, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_WORKGROUP_SETTINGS_DELETE,
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_DELETE, 2000));

		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_LIST));
		int itemCount = list.getChildCount(new UiSelector().className("android.widget.RelativeLayout"));
		for (int i = 0; i < itemCount; i++) {

			UiCollection item = new UiCollection(new UiSelector().className("android.widget.RelativeLayout"));
			if (item.getChild(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_ITEM_TITLE)).exists()) {
				if (!item.getChild(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON)).exists()) {
					if (!item.getChild(new UiSelector().resourceId(Constant.ID_WORKGROUP_SETTINGS_ITEM_TITLE))
							.getText().equals(Constant.TXT_WORKGROUP_SETTINGS_GROUP_QRCODE)) {
						assertTrue("The item not has switch button", false);
					}
				}
			}
		}
		clickResourceId(Constant.ID_BACK);
		waitForWindowUpdate(3000);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String className, jarName, androidId;
		className = "test.UITest";
		String testName = "";
		jarName = "AutoTest";
		androidId = "1";

		new UiAutomatorHelper(jarName, className, testName, androidId);

	}

	@Override
	protected void setUp() throws Exception {
		printLog("===SetUp Start===");
		try {
			super.setUp();

			helper = new LanxinTestHelper(this);
			new Constant(getConfigProperty("pkg"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		TurnOnNetWork = false;
		LogOut = false;
		DeleteGroup = false;

		helper.backToHome();
		helper.shutDownLanxinApp();

		helper.registerUiWatchers_OK();
		helper.registerUiWatchers_Wait();
		helper.registerUiWatchers_Crash();
		helper.registerUiWatchers_IKnow();
		helper.registerUiWatchers_NoNet();
		helper.registerUiWatchers_SecurityAlert();
		helper.registerUiWatchers_UpdateNotify();
		printLog("===SetUp End===");
	}

	@Override
	protected void tearDown() throws Exception {
		printLog("===TearDown Start===");
		super.tearDown();

		if (DeleteGroup) {
			helper.deleteGroup();
		}

		if (TurnOnNetWork) {
			makeSureNetConnected();
		}
		if (LogOut) {
			helper.backToHome();
			try {
				helper.clearAppUserData();

			} catch (Exception e) {

			}
		}

		helper.backToHome();
		helper.shutDownLanxinApp();

		helper.removieUiWatchers_OK();
		helper.removieUiWatchers_Wait();
		helper.removieUiWatchers_Crash();
		helper.removieUiWatchers_IKnow();
		helper.removieUiWatchers_NoNet();
		helper.removieUiWatchers_SecurityAlert();
		helper.removieUiWatchers_UpdateNotify();
		printLog("===TearDown End===");
	}

}
