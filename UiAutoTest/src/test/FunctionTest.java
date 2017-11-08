package test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

import android.graphics.Point;
import android.graphics.Rect;
import test.helper.Constant;
import test.helper.LanxinTestHelper;
import uiautotestutils.ATCUiAutoTestCase;
import uiautotestutils.UiAutomatorHelper;
import uiautotestutils.utils.ShellUtils;

public class FunctionTest extends ATCUiAutoTestCase {

	LanxinTestHelper helper;
	private boolean LogOut;
	private boolean DeleteGroup;
	private boolean TurnOnNetWork;

	public void testFunction_TitleBar_Search_ResultSort() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG",
				waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click titlebar_search");
		clickResourceId(Constant.ID_TITLEBAR_SEARCH);
		assertTrue("Cick the search icon failed", waitForTextExists(Constant.TXT_SEARCH_INPUT_TITLE, 3000));

		printLog("step4=Input some for search");
		clickResourceId(Constant.ID_SEARCH_INPUT);
		enterTextInEditor("186", "android.widget.EditText", 0);
		waitForWindowUpdate(3000);
		assertTrue("Input number not find the contact", waitForTextContainsExists(Constant.TXT_MY_NAME, 3000));
	}

	public void testFunction_TitleBar_Search_Cancel() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG",
				waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step3=Click titlebar_search");
		clickResourceId(Constant.ID_TITLEBAR_SEARCH);
		assertTrue("Cick the search icon failed", waitForTextExists(Constant.TXT_SEARCH_INPUT_TITLE, 3000));

		printLog("step4=Input number for search");
		clickResourceId(Constant.ID_SEARCH_INPUT);
		enterTextInEditor("000000000", "android.widget.EditText", 0);
		waitForWindowUpdate(3000);
		assertTrue("Input number not find the contact", waitForTextContainsExists(Constant.TXT_SEARCH_NO_RESULT, 3000));

		printLog("step5=Click the cancel");
		clickText(Constant.TXT_SEARCH_CANCEL, true);
		assertTrue("Click the cancel to back the msg list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));
	}
	
	public void testFunction_TitleBar_Search_Address_DisplayMore() throws UiObjectNotFoundException {

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.ID_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click titlebar_search");
		clickResourceId(Constant.ID_TITLEBAR_SEARCH);
		assertTrue("Cick the search icon failed", waitForTextExists(Constant.TXT_SEARCH_INPUT_TITLE, 3000));
		
		printLog("step4=Click " + Constant.TXT_SEARCH_DISPLAY_MORE);
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_RSS_ACCOUNT_GRIDVIEW));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_IV_HEAD));
		if (count > 3) {
			assertTrue("The UI Element not exist: " + Constant.TXT_SEARCH_DISPLAY_MORE,
					waitForTextExists(Constant.TXT_SEARCH_DISPLAY_MORE, 3000));
			
			clickText(Constant.TXT_SEARCH_DISPLAY_MORE, true);
			assertTrue("Click Close display failed.", waitForTextExists(Constant.TXT_SEARCH_DISPLAY_CLOSE, 3000));
			
			count = list.getChildCount(new UiSelector().resourceId(Constant.ID_IV_HEAD));
			assertTrue("Display more not work will", count > 4);
		}
		
		printLog("step5=Click one contact");
		getObjectOnResourceIdAndInstance(Constant.ID_IV_HEAD, 0).clickAndWaitForNewWindow();
		assertTrue("Click contact to enter name card failed.", waitForTextExists(Constant.TXT_NAME_CARD, 3000));
	}

	public void testFunction_TitleBar_WorkGroup_Create_SelectContacts_SelectMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));

		} else {
			assertTrue("No any contacts", false);
		}
	}

	public void testFunction_TitleBar_WorkGroup_Create_SelectContacts_CancelSelectMember1()
			throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_MEMBER_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_MEMBER_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the member");
			name.click();
			assertTrue("Un-select a member failed.", !checkIcon.isChecked());
			assertTrue("Select name display", name.waitUntilGone(2000));

		} else {
			assertTrue("No any contacts", false);
		}

	}

	public void testFunction_TitleBar_WorkGroup_Create_SelectContacts_CancelSelectMember2()
			throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=Select a member");
		UiObject checkIcon = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_MEMBER_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_MEMBER_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the member");
			checkIcon.click();
			assertTrue("Un-select a member failed.", !checkIcon.isChecked());
			assertTrue("Select Name display", name.waitUntilGone(2000));
		}
	}

	public void testFunction_TitleBar_WorkGroup_Create_SelectContacts_SelectBranch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Org has no branch", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));

		printLog("step6=select a branch");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_DEPART_CHECKBOX).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a branch failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_BRANCH_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_BRANCH_NAME, 2000));

		} else {
			assertTrue("No any branch", false);
		}
	}

	public void testFunction_TitleBar_WorkGroup_Create_SelectContacts_CancelSelectBranch1()
			throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Org has no branch", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));

		printLog("step6=select a branch");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_DEPART_CHECKBOX).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a branch failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_BRANCH_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_BRANCH_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_BRANCH_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the branch");
			name.click();
			assertTrue("Un-select a branch failed.", !checkIcon.isChecked());
			assertTrue("Select name display", name.waitUntilGone(2000));

		} else {
			assertTrue("No any branch", false);
		}
	}

	public void testFunction_TitleBar_WorkGroup_Create_SelectContacts_CancelSelectBranch2()
			throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Org has no branch", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));

		printLog("step6=select a branch");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_DEPART_CHECKBOX).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a branch failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_BRANCH_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_BRANCH_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_BRANCH_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the branch");
			checkIcon.click();
			assertTrue("Un-select a branch failed.", !checkIcon.isChecked());
			assertTrue("Select name display", name.waitUntilGone(2000));

		} else {
			assertTrue("No any branch", false);
		}
	}

	public void testFunction_TitleBar_WorkGroup_Create_SelectContacts_ConfirmIcon1() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=Verify Confirm icon");
		assertTrue("Confirm icon can be click.", !getObjectOnResourceId(Constant.ID_TITLEBAR_CONFIRM).isEnabled());
		assertTrue("Confirm icon display not match expect: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
	}

	public void testFunction_TitleBar_WorkGroup_Create_SelectContacts_ConfirmIcon2() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workeGroup_CreateGroup(phoneNum, password);

		printLog("step6=Verify Confirm icon");
		assertTrue("Confirm icon display not match expect: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));

		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Confirm icon cannot be click.", getObjectOnResourceId(Constant.ID_TITLEBAR_CONFIRM).isEnabled());
			assertTrue("Confirm icon display not match expect: " + Constant.TXT_SELECT_CONFIRM1,
					waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM1, 2000));
		} else {
			assertTrue("No any contacts", false);
		}
	}

	public void testFunction_TitleBar_WorkGroup_NewGroup_SetHeadIcon_Photo() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workGroup_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click the head icon");
		clickResourceId(Constant.ID_GROUP_HEAD_IMAGE);
		assertTrue("Click Head icon to set Head failed.", waitForTextExists(Constant.TXT_DIALOG_SETHEAD_TITLE, 2000));

		printLog("step8=Select " + Constant.TXT_DIALOG_SETHEAD_PHOTO);
		clickText(Constant.TXT_DIALOG_SETHEAD_PHOTO, true);
		assertTrue("Select photo to enter camera failed.", waitForPkgExist("com.zte.camera", 5000));

		printLog("step9=Click the shutter button");
		clickResourceId(Constant.ID_CAMERA_SHUTTER);
		assertTrue("Click the shutter failed.", waitForResourceId(Constant.ID_CAMERA_OK, 5000));

		printLog("step10=Click the shutter button");
		clickResourceId(Constant.ID_CAMERA_OK);
		assertTrue("Click the OK button failed.", waitForTextExists(Constant.TXT_CONFIRM, 5000));

		printLog("step11=Click " + Constant.TXT_CONFIRM);
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Back to the create group failed.", waitForTextExists(Constant.TXT_NEWGROUP, 5000));
	}

	public void testFunction_TitleBar_WorkGroup_NewGroup_SetHeadIcon_Picture() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.workGroup_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click the head icon");
		clickResourceId(Constant.ID_GROUP_HEAD_IMAGE);
		assertTrue("Click Head icon to set Head failed.", waitForTextExists(Constant.TXT_DIALOG_SETHEAD_TITLE, 2000));

		printLog("step8=Select " + Constant.TXT_DIALOG_SETHEAD_PICTURE);
		clickText(Constant.TXT_DIALOG_SETHEAD_PICTURE, true);
		assertTrue("Select picture to enter camera failed.", waitForTextExists(Constant.TXT_SELECT_ITEM, 3000));

		printLog("step9=Select one picture");
		while (waitForTextExists(Constant.TXT_SELECT_ITEM, 3000)) {
			clickPoint(200, 350);
			waitForWindowUpdate(2000);
		}
		assertTrue("Select the picture failed.", waitForTextExists(Constant.TXT_CONFIRM, 2000));

		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Back to the create group failed.", waitForTextExists(Constant.TXT_NEWGROUP, 5000));
	}

	public void testFunction_TitleBar_WorkGroup_NewGroup_SetGroupName() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click name editview and enter name");
		clickResourceId(Constant.ID_GROUP_GROUP_NAME_INPUT);
		enterTextInEditor(groupName, "android.widget.EditText", 0);
		assertTrue("Enter name failed.", waitForTextExists(groupName, 3000));

		printLog("step8=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step9=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		printLog("step10=Verify the group name");
		String name = getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000);
		assertTrue("The group name" + name + " not match expect", name.contains(groupName));
	}

	public void testFunction_TitleBar_WorkGroup_NewGroup_DefaultGroupName() throws UiObjectNotFoundException {

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

		printLog("step9=Verify the group name");
		String title = getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 3000);
		assertTrue("The default group title: " + title + " not contain: " + name1, title.contains(name1));
		assertTrue("The default group title: " + title + " not contain: " + name2, title.contains(name2));
	}

	public void testFunction_TitleBar_WorkGroup_NewGroup_AddMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click Add icon");
		clickResourceId(Constant.ID_ADD_MEMBER);
		assertTrue("Click add icon failed.", waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 3000));

		printLog("step8=Verify Selected member");
		assertTrue("Selected member1 can be click.", !getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0)
				.isEnabled());
		assertTrue("Selected member2 can be click.", !getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1)
				.isEnabled());

	}

	public void testFunction_TitleBar_WorkGroup_NewGroup_DeleteMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		String name1 = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 0).getText();
		printLog("step7=Click Delete icon");
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_REMOVE, 0).click();
		assertTrue("Click remove icon failed.", waitForTextExists(Constant.TXT_GROUP_DELETE_MEMBER_TITLE, 3000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Remove the member failed.", waitForTextGone(name1, 2000));
	}

	public void testFunction_TitleBar_WorkGroup_NewGroup_DeleteBranch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Branch(phoneNum, password);

		String name1 = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 0).getText();
		printLog("step7=Click Delete icon");
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_REMOVE, 0).click();
		assertTrue("Click remove icon failed.", waitForTextExists(Constant.TXT_GROUP_DELETE_MEMBER_TITLE, 3000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Remove the branch failed.", waitForTextGone(name1, 2000));
	}

	public void testFunction_TitleBar_WorkGroup_NewGroup_DoubleConfirm_Confirm() throws UiObjectNotFoundException {
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
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));
	}

	public void testFunction_TitleBar_WorkGroup_NewGroup_DoubleConfirm_Cancel() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 3000));

		printLog("step8=Click Cancel");
		clickText(Constant.TXT_CANCEL, true);
		assertTrue("Click cancel back to new group failed", waitForTextExists(Constant.TXT_NEWGROUP, 2000));
	}

	public void testFunction_TitleBar_WorkGroup_NewGroup_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click Back icon");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Click back icon failed.", waitForTextExists(Constant.TXT_CANCEL_CREATE_GROUP, 3000));
	}

	public void testFunction_TitleBar_WorkGroup_NewGroup_SingleMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.workGroup_CreateGroup_Member(phoneNum, password);

		String name1 = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 0).getText();
		printLog("step7=Click Delete icon");
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_REMOVE, 0).click();
		assertTrue("Click remove icon failed.", waitForTextExists(Constant.TXT_GROUP_DELETE_MEMBER_TITLE, 3000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Remove the member failed.", waitForTextGone(name1, 2000));

		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 2000));
	}

	public void testFunction_TitleBar_GroupChat_Create_SelectContacts_SelectMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));

		} else {
			assertTrue("No any contacts", false);
		}
	}

	public void testFunction_TitleBar_GroupChat_Create_SelectContacts_CancelSelectMember1()
			throws UiObjectNotFoundException {

		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_MEMBER_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_MEMBER_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the member");
			name.click();
			assertTrue("Un-select a member failed.", !checkIcon.isChecked());
			assertTrue("Select name display", name.waitUntilGone(2000));

		} else {
			assertTrue("No any contacts", false);
		}

	}

	public void testFunction_TitleBar_GroupChat_Create_SelectContacts_CancelSelectMember2()
			throws UiObjectNotFoundException {

		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step6=Select a member");
		UiObject checkIcon = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_MEMBER_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_MEMBER_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the member");
			checkIcon.click();
			assertTrue("Un-select a member failed.", !checkIcon.isChecked());
			assertTrue("Select Name display", name.waitUntilGone(2000));
		}
	}

	public void testFunction_TitleBar_GroupChat_Create_SelectContacts_SelectBranch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Org has no branch", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));

		printLog("step6=select a branch");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_DEPART_CHECKBOX).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a branch failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_BRANCH_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_BRANCH_NAME, 2000));

		} else {
			assertTrue("No any branch", false);
		}
	}

	public void testFunction_TitleBar_GroupChat_Create_SelectContacts_CancelSelectBranch1()
			throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Org has no branch", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));

		printLog("step6=select a branch");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_DEPART_CHECKBOX).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a branch failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_BRANCH_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_BRANCH_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_BRANCH_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the branch");
			name.click();
			assertTrue("Un-select a branch failed.", !checkIcon.isChecked());
			assertTrue("Select name display", name.waitUntilGone(2000));

		} else {
			assertTrue("No any branch", false);
		}
	}

	public void testFunction_TitleBar_GroupChat_Create_SelectContacts_CancelSelectBranch2()
			throws UiObjectNotFoundException {

		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Org has no branch", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));

		printLog("step6=select a branch");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_DEPART_CHECKBOX).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a branch failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_BRANCH_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_BRANCH_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_BRANCH_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the branch");
			checkIcon.click();
			assertTrue("Un-select a branch failed.", !checkIcon.isChecked());
			assertTrue("Select name display", name.waitUntilGone(2000));

		} else {
			assertTrue("No any branch", false);
		}
	}

	public void testFunction_TitleBar_GroupChat_Create_SelectContacts_ConfirmIcon1() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step6=Verify Confirm icon");
		assertTrue("Confirm icon can be click.", !getObjectOnResourceId(Constant.ID_TITLEBAR_CONFIRM).isEnabled());
		assertTrue("Confirm icon display not match expect: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
	}

	public void testFunction_TitleBar_GroupChat_Create_SelectContacts_ConfirmIcon2() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.groupChat_CreateGroup(phoneNum, password);

		printLog("step6=Verify Confirm icon");
		assertTrue("Confirm icon display not match expect: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));

		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Confirm icon cannot be click.", getObjectOnResourceId(Constant.ID_TITLEBAR_CONFIRM).isEnabled());
			assertTrue("Confirm icon display not match expect: " + Constant.TXT_SELECT_CONFIRM1,
					waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM1, 2000));
		} else {
			assertTrue("No any contacts", false);
		}
	}

	public void testFunction_TitleBar_GroupChat_NewGroup_SetHeadIcon_Photo() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.groupChat_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click the head icon");
		clickResourceId(Constant.ID_GROUP_HEAD_IMAGE);
		assertTrue("Click Head icon to set Head failed.", waitForTextExists(Constant.TXT_DIALOG_SETHEAD_TITLE, 2000));

		printLog("step8=Select " + Constant.TXT_DIALOG_SETHEAD_PHOTO);
		clickText(Constant.TXT_DIALOG_SETHEAD_PHOTO, true);
		assertTrue("Select photo to enter camera failed.", waitForPkgExist("com.zte.camera", 5000));

		printLog("step9=Click the shutter button");
		clickResourceId(Constant.ID_CAMERA_SHUTTER);
		assertTrue("Click the shutter failed.", waitForResourceId(Constant.ID_CAMERA_OK, 5000));

		printLog("step10=Click the shutter button");
		clickResourceId(Constant.ID_CAMERA_OK);
		assertTrue("Click the OK button failed.", waitForTextExists(Constant.TXT_CONFIRM, 5000));

		printLog("step11=Click " + Constant.TXT_CONFIRM);
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Back to the create group failed.", waitForTextExists(Constant.TXT_NEWGROUPSEND, 5000));
	}

	public void testFunction_TitleBar_GroupChat_NewGroup_SetHeadIcon_Picture() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.groupChat_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click the head icon");
		clickResourceId(Constant.ID_GROUP_HEAD_IMAGE);
		assertTrue("Click Head icon to set Head failed.", waitForTextExists(Constant.TXT_DIALOG_SETHEAD_TITLE, 2000));

		printLog("step8=Select " + Constant.TXT_DIALOG_SETHEAD_PICTURE);
		clickText(Constant.TXT_DIALOG_SETHEAD_PICTURE, true);
		assertTrue("Select picture to enter Album failed.", waitForTextExists(Constant.TXT_SELECT_ITEM, 3000));

		printLog("step9=Select one picture");
		while (waitForTextExists(Constant.TXT_SELECT_ITEM, 3000)) {
			clickPoint(200, 350);
			waitForWindowUpdate(2000);
		}
		assertTrue("Select the picture failed.", waitForTextExists(Constant.TXT_CONFIRM, 2000));

		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Back to the create group failed.", waitForTextExists(Constant.TXT_NEWGROUPSEND, 5000));
	}

	public void testFunction_TitleBar_GroupChat_NewGroup_SetGroupName() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");
		helper.groupChat_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click name editview and enter name");
		clickResourceId(Constant.ID_GROUP_GROUP_NAME_INPUT);
		enterTextInEditor(groupName, "android.widget.EditText", 0);
		assertTrue("Enter name failed.", waitForTextExists(groupName, 3000));

		printLog("step8=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 3000));

		printLog("step9=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		printLog("step10=Verify the group name");
		String name = getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 3000);
		assertTrue("The default group name " + name + " not match expect", name.contains(groupName));
	}

	public void testFunction_TitleBar_GroupChat_NewGroup_DefaultGroupName() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.groupChat_CreateGroup_Member(phoneNum, password);

		String name1 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 0);
		String name2 = getTextOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2000, 1);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 3000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(3000);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		printLog("step9=Verify the group name");
		String title = getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 3000);
		assertTrue("The default group title" + title + " not contain : " + name1, title.contains(name1));
		assertTrue("The default group title" + title + " not contain : " + name2, title.contains(name2));
	}

	public void testFunction_TitleBar_GroupChat_NewGroup_AddMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.groupChat_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click Add icon");
		clickResourceId(Constant.ID_ADD_MEMBER);
		assertTrue("Click add icon failed.", waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 3000));

		printLog("step8=Verify Selected member");
		assertTrue("Selected member1 can be click.", !getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0)
				.isEnabled());
		assertTrue("Selected member2 can be click.", !getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1)
				.isEnabled());

	}

	public void testFunction_TitleBar_GroupChat_NewGroup_DeleteMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.groupChat_CreateGroup_Member(phoneNum, password);

		String name1 = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 0).getText();
		printLog("step7=Click Delete icon");
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_REMOVE, 0).click();
		assertTrue("Click remove icon failed.", waitForTextExists(Constant.TXT_GROUP_DELETE_MEMBER_TITLE, 3000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Remove the member failed.", waitForTextGone(name1, 2000));
	}

	public void testFunction_TitleBar_GroupChat_NewGroup_DeleteBranch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.groupChat_CreateGroup_Branch(phoneNum, password);

		String name1 = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 0).getText();
		printLog("step7=Click Delete icon");
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_REMOVE, 0).click();
		assertTrue("Click remove icon failed.", waitForTextExists(Constant.TXT_GROUP_DELETE_MEMBER_TITLE, 3000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Remove the branch failed.", waitForTextGone(name1, 2000));
	}

	public void testFunction_TitleBar_GroupChat_NewGroup_DoubleConfirm_Confirm() throws UiObjectNotFoundException {
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
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 5000));
	}

	public void testFunction_TitleBar_GroupChat_NewGroup_DoubleConfirm_Cancel() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.groupChat_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 3000));

		printLog("step8=Click Cancel");
		clickText(Constant.TXT_CANCEL, true);
		assertTrue("Click cancel back to new group failed", waitForTextExists(Constant.TXT_NEWGROUPSEND, 2000));
	}

	public void testFunction_TitleBar_GroupChat_NewGroup_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.groupChat_CreateGroup_Member(phoneNum, password);

		printLog("step7=Click Back icon");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Click back icon failed.", waitForTextExists(Constant.TXT_CANCEL_GROUP_CHAT, 3000));
	}

	public void testFunction_TitleBar_GroupChat_NewGroup_SingleMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.groupChat_CreateGroup_Member(phoneNum, password);

		String name1 = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 0).getText();
		printLog("step7=Click Delete icon");
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_REMOVE, 0).click();
		assertTrue("Click remove icon failed.", waitForTextExists(Constant.TXT_GROUP_DELETE_MEMBER_TITLE, 3000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Remove the member failed.", waitForTextGone(name1, 2000));

		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));
	}

	public void testFunction_TitleBar_CallMeeting_Create_SelectContacts_SelectMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));

		} else {
			assertTrue("No any contacts", false);
		}
	}

	public void testFunction_TitleBar_CallMeeting_Create_SelectContacts_CancelSelectMember1()
			throws UiObjectNotFoundException {

		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_MEMBER_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_MEMBER_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the member");
			name.click();
			assertTrue("Un-select a member failed.", !checkIcon.isChecked());
			assertTrue("Select name display", name.waitUntilGone(2000));

		} else {
			assertTrue("No any contacts", false);
		}

	}

	public void testFunction_TitleBar_CallMeeting_Create_SelectContacts_CancelSelectMember2()
			throws UiObjectNotFoundException {

		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step6=Select a member");
		UiObject checkIcon = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_MEMBER_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_MEMBER_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the member");
			checkIcon.click();
			assertTrue("Un-select a member failed.", !checkIcon.isChecked());
			assertTrue("Select Name display", name.waitUntilGone(2000));
		}
	}

	public void testFunction_TitleBar_CallMeeting_Create_SelectContacts_SelectBranch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Org has no branch", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));

		printLog("step6=select a branch");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_DEPART_CHECKBOX).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a branch failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_BRANCH_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_BRANCH_NAME, 2000));

		} else {
			assertTrue("No any branch", false);
		}
	}

	public void testFunction_TitleBar_CallMeeting_Create_SelectContacts_CancelSelectBranch1()
			throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Org has no branch", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));

		printLog("step6=select a branch");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_DEPART_CHECKBOX).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a branch failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_BRANCH_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_BRANCH_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_BRANCH_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the branch");
			name.click();
			assertTrue("Un-select a branch failed.", !checkIcon.isChecked());
			assertTrue("Select name display", name.waitUntilGone(2000));

		} else {
			assertTrue("No any branch", false);
		}
	}

	public void testFunction_TitleBar_CallMeeting_Create_SelectContacts_CancelSelectBranch2()
			throws UiObjectNotFoundException {

		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step5=click org icon");
		clickResourceId(Constant.ID_TAB_ORG);
		sleep(1000);

		UiObject org = getObjectOnResourceId(Constant.ID_TAB_ORG);
		assertTrue("Switch from Contact to org failed.", org.isChecked());
		assertTrue("Org has no branch", waitForResourceId(Constant.ID_DEPART_CHECKBOX, 2000));

		printLog("step6=select a branch");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_DEPART_CHECKBOX).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a branch failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_BRANCH_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_BRANCH_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_BRANCH_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the branch");
			checkIcon.click();
			assertTrue("Un-select a branch failed.", !checkIcon.isChecked());
			assertTrue("Select name display", name.waitUntilGone(2000));

		} else {
			assertTrue("No any branch", false);
		}
	}

	public void testFunction_TitleBar_CallMeeting_Create_SelectContacts_ConfirmIcon1() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step6=Verify Confirm icon");
		assertTrue("Confirm icon can be click.", !getObjectOnResourceId(Constant.ID_TITLEBAR_CONFIRM).isEnabled());
		assertTrue("Confirm icon display not match expect: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));
	}

	public void testFunction_TitleBar_CallMeeting_Create_SelectContacts_ConfirmIcon2() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting(phoneNum, password);

		printLog("step6=Verify Confirm icon");
		assertTrue("Confirm icon display not match expect: " + Constant.TXT_SELECT_CONFIRM0,
				waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));

		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Confirm icon cannot be click.", getObjectOnResourceId(Constant.ID_TITLEBAR_CONFIRM).isEnabled());
			assertTrue("Confirm icon display not match expect: " + Constant.TXT_SELECT_CONFIRM1,
					waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM1, 2000));
		} else {
			assertTrue("No any contacts", false);
		}
	}

	public void testFunction_TitleBar_CallMeeting_NewGroup_SetMeetingTitle() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String title = getConfigProperty("meeting_title");
		helper.callMeeting_CreateMeeting_Member(phoneNum, password);

		printLog("step7=Click title editview and enter name");
		clickResourceId(Constant.ID_MEETING_TITLE_INPUT);
		enterTextInEditor(title, "android.widget.EditText", 0);
		assertTrue("Enter name failed.", waitForTextExists(title, 3000));
	}

	public void testFunction_TitleBar_CallMeeting_NewGroup_Notify() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.callMeeting_CreateMeeting_Member(phoneNum, password);

		printLog("step7=Click msg notify");
		clickResourceId(Constant.ID_MSG_NOTIFY_CHECK);

		Rect bound = getBoundsByResourceID(Constant.ID_MSG_NOTIFY_CHECK);
		boolean result = isImageSame(Constant.RES_FILE_PATH + "callmeeting", bound.left, bound.top, bound.right,
				bound.bottom);
		assertTrue("Click msg notify failed.", result);
	}

	public void testFunction_TitleBar_CallMeeting_NewGroup_AddMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.callMeeting_CreateMeeting_Member(phoneNum, password);

		printLog("step7=Click Add icon");
		clickResourceId(Constant.ID_ADD_MEMBER);
		assertTrue("Click add icon failed.", waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 3000));

		printLog("step8=Verify Selected member");
		assertTrue("Selected member1 can be click.", !getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0)
				.isEnabled());
		assertTrue("Selected member2 can be click.", !getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1)
				.isEnabled());
	}

	public void testFunction_TitleBar_CallMeeting_NewGroup_DeleteMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.callMeeting_CreateMeeting_Member(phoneNum, password);

		String name1 = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 0).getText();
		printLog("step7=Click Delete icon");
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_REMOVE, 0).click();
		assertTrue("Click remove icon failed.", waitForTextExists(Constant.TXT_GROUP_DELETE_MEMBER_TITLE, 3000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Remove the member failed.", waitForTextGone(name1, 2000));
	}

	public void testFunction_TitleBar_CallMeeting_NewGroup_DeleteBranch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.callMeeting_CreateMeeting_Branch(phoneNum, password);

		String name1 = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 0).getText();
		printLog("step7=Click Delete icon");
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_REMOVE, 0).click();
		assertTrue("Click remove icon failed.", waitForTextExists(Constant.TXT_GROUP_DELETE_MEMBER_TITLE, 3000));

		printLog("step8=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Remove the branch failed.", waitForTextGone(name1, 2000));
	}

	// public void
	// testFunction_TitleBar_CallMeeting_NewGroup_DoubleConfirm_Cancel() throws
	// UiObjectNotFoundException {
	// printLog("CaseName=" + getName());
	// String phoneNum = getConfigProperty("phoneNum");
	// String password = getConfigProperty("password");
	// helper.callMeeting_CreateMeeting_Member(phoneNum, password);
	//
	// printLog("step7=Click Confirm icon");
	// clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
	// assertTrue("Double confirm dialog not display",
	// waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 3000));
	//
	// printLog("step8=Click Cancel");
	// clickText(Constant.TXT_CANCEL, true);
	// assertTrue("Click cancel back to new group failed",
	// waitForTextExists(Constant.TXT_NEWGROUP, 2000));
	// }

	public void testFunction_TitleBar_CallMeeting_NewGroup_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		helper.callMeeting_CreateMeeting_Member(phoneNum, password);

		printLog("step7=Click Back icon");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Click back icon failed.", waitForTextExists(Constant.TXT_CANCEL_CALL_MEETING, 3000));
	}

	public void testFunction_TitleBar_AddContacts_ContactDetails() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.addContacts_Enter(phoneNum, password);

		System.out.println("step5=Click one contact");
		String name = getTextOnResourceIdAndInstance(Constant.ID_NAME, 2000, 0);
		clickText(name, true);
		assertTrue("Enter contact detail failed.", waitForTextExists(Constant.TXT_ADD_TO_CONTACTS, 3000));

		System.out.println("step6=Verify contact UI Element");
		assertTrue("The UI Element not exist: " + Constant.TXT_ADD_CONTACT_TIPS,
				waitForTextExists(Constant.TXT_ADD_CONTACT_TIPS, 2000));
		assertTrue("The UI Element not exist: Back icon ", waitForResourceId(Constant.ID_BACK, 2000));
		assertTrue("The UI Element not exist: Mobile ", waitForResourceId(Constant.ID_MOBILE, 2000));
		assertTrue("The UI Element not exist: Name ", waitForResourceId(Constant.ID_NAME, 2000));
		assertTrue("The UI Element not exist: Image ", waitForResourceId(Constant.ID_IMAGE, 2000));
	}

	public void testFunction_TitleBar_AddContacts_Search_ContactInAddress() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.addContacts_Enter(phoneNum, password);

		System.out.println("step5=Click Search to enter name");
		clickResourceId(Constant.ID_FIND_ET);
		String name = "test1";
		ShellUtils.execCommand("input text " + name, true);
		clickResourceId(Constant.ID_FIND_ET);
		clickResourceId(Constant.ID_FIND_ET);
		assertTrue("Enter contact detail failed.", waitForTextExists(Constant.TXT_ADD_TO_CONTACTS, 3000));
	}

	public void testFunction_TitleBar_AddContacts_Search_ContactOutAddress() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.addContacts_Enter(phoneNum, password);

		System.out.println("step5=Click Search to enter name");
		clickResourceId(Constant.ID_FIND_ET);
		String number = "13100003333";
		ShellUtils.execCommand("input text " + number, true);
		clickResourceId(Constant.ID_FIND_ET);
		clickResourceId(Constant.ID_FIND_ET);
		assertTrue("Enter contact detail failed.", waitForTextExists(Constant.TXT_ADD_TO_CONTACTS, 3000));
	}

	public void testFunction_Msg_MsgList_DeleteMsg() throws UiObjectNotFoundException {
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

		printLog("step3=Long click the msg and delete");
		longClickTextBySwipe(chatName);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
		clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
		UiObject obj = new UiObject(new UiSelector().text(chatName));
		assertTrue("Delete the msg failed.", obj.waitUntilGone(3000));
	}

	public void testFunction_Msg_MsgList_Draft() throws UiObjectNotFoundException {

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
		assertTrue("Entet the text failed.", waitForTextExists(longtime, 3000));

		printLog("step5=Click the back to msg list");
		clickResourceId(Constant.ID_BACK);
		waitForWindowUpdate(3000);
		assertTrue("Back to msg list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));
		assertTrue("No " + Constant.TXT_MSG_DRAFT + " in msg list",
				waitForTextExists(Constant.TXT_MSG_DRAFT + longtime, 3000));

		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));
		clickText(Constant.TXT_CHAT_TOOL_SEND, true);
		assertTrue("Send the msg failed.", waitForTextExists(longtime, 3000));
	}

	public void testFunction_Msg_MsgList_MsgTop() throws UiObjectNotFoundException {
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

		printLog("step5=Click the msg top icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS_MSG_TOP);
		waitForWindowUpdate(1000);
		assertTrue("Click the msg top icon failed.", getObjectOnResourceId(Constant.ID_CHAT_SETTINGS_MSG_TOP)
				.isSelected());

		printLog("step6=Un-select the msg top icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS_MSG_TOP);
		waitForWindowUpdate(1000);
		assertTrue("Click the msg top icon failed.", !getObjectOnResourceId(Constant.ID_CHAT_SETTINGS_MSG_TOP)
				.isSelected());
	}

	public void testFunction_Msg_PrivateChat_Settings_AddMember() throws UiObjectNotFoundException {
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

		printLog("step5=Click Add member icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS_ADD_MEMBER);
		assertTrue("Click Add icon to enter Select contact UI failed.",
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 3000));

		printLog("step6=Select other contacts");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_CHECK));
		for (int i = 0; i < count; i++) {
			UiObject check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, i);
			if (check.exists()) {

				if (check.isChecked()) {
					continue;
				} else {
					check.click();
					break;
				}
			} else {
				assertTrue("No more contacts or the Network not well", false);
			}
		}
		assertTrue("Select the contact failed.", waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM2, 3000));

		printLog("step7=Click confirm button");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("UI Element not exist:" + Constant.TXT_NEWGROUP, waitForTextExists(Constant.TXT_NEWGROUP, 3000));

		String groupName = getConfigProperty("workgroup_name");
		printLog("step8=Click name editview and enter name");
		clickResourceId(Constant.ID_GROUP_GROUP_NAME_INPUT);
		enterTextInEditor(groupName, "android.widget.EditText", 0);
		assertTrue("Enter name failed.", waitForTextExists(groupName, 3000));

		printLog("step9=Click Confirm icon");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Double confirm dialog not display",
				waitForResourceId(Constant.ID_GROUP_CREATE_DOUBLE_CONFIRM_TITLE, 2000));

		printLog("step10=Click Confirm");
		clickText(Constant.TXT_CONFIRM, true);
		DeleteGroup = true;
		waitForWindowUpdate(3000);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));

		printLog("step11=Verify the group name");
		String name = getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000);
		assertTrue("The group name" + name + " not match expect", name.contains(groupName));

	}

	public void testFunction_Msg_PrivateChat_Settings_MsgTop() throws UiObjectNotFoundException {
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

		printLog("step5=Click the msg top icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS_MSG_TOP);
		waitForWindowUpdate(1000);
		assertTrue("Click the msg top icon failed.", getObjectOnResourceId(Constant.ID_CHAT_SETTINGS_MSG_TOP)
				.isSelected());

		printLog("step6=Un-select the msg top icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS_MSG_TOP);
		waitForWindowUpdate(1000);
		assertTrue("Click the msg top icon failed.", !getObjectOnResourceId(Constant.ID_CHAT_SETTINGS_MSG_TOP)
				.isSelected());
	}

	public void testFunction_Msg_PrivateChat_MsgRelay_NewChat() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String longtime = helper.sendMsg_Text();
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RELAY, 3000));

		printLog("step5=Select relay");
		clickText(Constant.TXT_SELECT_DIALOG_LIST_RELAY, true);
		assertTrue("Click the relay to enter option failed.", waitForTextExists(Constant.TXT_RELAY_TITLE, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_MY_NAME,
				waitForTextContainsExists(Constant.TXT_MY_NAME, 3000));
//		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_TO_WEIXIN_FRIEND,
//				waitForTextContainsExists(Constant.TXT_RELAY_TO_WEIXIN_FRIEND, 2000));
//		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_WEIXIN_MOMENT,
//				waitForTextContainsExists(Constant.TXT_RELAY_WEIXIN_MOMENT, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_NEW_CHAT,
				waitForTextContainsExists(Constant.TXT_RELAY_NEW_CHAT, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_RECENT_CHAT,
				waitForTextContainsExists(Constant.TXT_RELAY_RECENT_CHAT, 2000));

		printLog("step6=Click " + Constant.TXT_RELAY_NEW_CHAT);
		clickText(Constant.TXT_RELAY_NEW_CHAT, true);
		assertTrue("Click the relay new chat failed.", waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 3000));

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);

		if (contact1Check.waitForExists(2000)) {

			printLog("step7=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			assertTrue("Select contact1 failed.", contact1Check.isChecked());

			printLog("step8=Click confirm button");
			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			waitForWindowUpdate(3000);
			assertTrue("Relay the msg failed.", waitForTextExists(longtime, 3000));

		} else {
			assertTrue("This account has no contact", false);
		}
	}

	public void testFunction_Msg_PrivateChat_MsgRelay_RecentChat() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String longtime = helper.sendMsg_Text();
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RELAY, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_RELAY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_RELAY, true);
		assertTrue("Click the relay to enter option failed.", waitForTextExists(Constant.TXT_RELAY_TITLE, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_RECENT_CHAT,
				waitForTextContainsExists(Constant.TXT_RELAY_RECENT_CHAT, 2000));

		printLog("step6=Click one current chat");
		clickTextContains(Constant.TXT_MY_NAME, true);
		assertTrue("Clickt the current chat failed.", waitForTextExists(Constant.TXT_CONFIRM, 3000));

		printLog("step7=Click the confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(3000);
		assertTrue("Relay the msg failed.", waitForTextExists(longtime, 3000));
	}

	public void testFunction_Msg_PrivateChat_MsgReply() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String longtime = helper.sendMsg_Text();
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_REPLY, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_REPLY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_REPLY, true);
		waitForWindowUpdate(3000);
		clickText(Constant.TXT_CHAT_TOOL_SEND, true);
//		String text = getTextOnResourceId(Constant.ID_CHAT_MSG_INPUT_ET, 3000);
//		assertTrue("The " + text + " not has " + longtime, text.contains(longtime));
//		assertTrue("The " + text + " not has " + chatName, text.contains(Constant.TXT_MY_NAME));
//		assertTrue("The " + text + " not has " + Constant.TXT_REPLY, text.contains(Constant.TXT_REPLY));
		assertTrue("The text not has " + longtime, waitForTextContainsExists(longtime, 2000));
		assertTrue("The text not has " + Constant.TXT_MY_NAME, waitForTextContainsExists(Constant.TXT_MY_NAME, 2000));
		assertTrue("The text not has " + Constant.TXT_REPLY, waitForTextContainsExists(Constant.TXT_REPLY, 2000));
	}

	public void testFunction_Msg_PrivateChat_MsgFavorite() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String longtime = helper.sendMsg_Text();
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_FAVORITE, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_FAVORITE);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_FAVORITE, true);

		printLog("step6=Click back");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Back to Msg failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step7=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		waitForWindowUpdate(3000);
		assertTrue("Click the More failed.", waitForTextExists(Constant.TXT_MORE_MY_FAVORITE, 3000));

		printLog("step8=Click " + Constant.TXT_MORE_MY_FAVORITE);
		clickText(Constant.TXT_MORE_MY_FAVORITE, true);
		waitForWindowUpdate(3000);
		assertTrue("Msg favorite failed.", waitForTextExists(longtime, 3000));
	}

	public void testFunction_Msg_PrivateChat_MsgRecall_RightNow() throws UiObjectNotFoundException {
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

		printLog("step4=Send some text");
		String longtime = helper.sendMsg_Text();

		printLog("step6=Long click the msg");
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RECALL, 3000));

		printLog("step7=Select " + Constant.TXT_SELECT_DIALOG_LIST_RECALL);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_RECALL, true);
		assertTrue("Recall the msg failed.", waitForTextGone(longtime, 3000));
		assertTrue("Recall notify not exist: " + Constant.TXT_RECALL_NOTIFY,
				waitForTextContainsExists(Constant.TXT_RECALL_NOTIFY, 3000));
	}

	public void testFunction_Msg_PrivateChat_MsgRecall_Later() throws UiObjectNotFoundException {
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

		printLog("step4=Send some text");
		String longtime = helper.sendMsg_Text();

		sleep(2 * 60 * 1000);

		printLog("step6=Long click the msg");
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RECALL, 3000));

		printLog("step7=Select " + Constant.TXT_SELECT_DIALOG_LIST_RECALL);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_RECALL, true);
		assertTrue("The msg be recall.", waitForTextExists(longtime, 3000));
	}

	public void testFunction_Msg_PrivateChat_MsgDelete() throws UiObjectNotFoundException {
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

		printLog("step4=Send some text");
		String longtime = helper.sendMsg_Text();

		printLog("step6=Long click the msg");
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));

		printLog("step7=Select " + Constant.TXT_SELECT_DIALOG_LIST_DELETE);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
		clickText(Constant.TXT_CONFIRM, true);
		if (waitForTextExists(longtime, 2000)) {
			longClickTextBySwipe(longtime);
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}
		assertTrue("Delete the msg failed.", waitForTextGone(longtime, 3000));
	}

	public void testFunction_Msg_PrivateChat_MsgTpye_Voice() throws UiObjectNotFoundException {
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

		while (waitForResourceId(Constant.ID_CHAT_MSG_VOICE_IMAGE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VOICE_IMAGE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send voice");
		helper.sendMsg_Voice(3000);

		printLog("step5=Verify the UI Element");
		assertTrue("The UI Element not exist: Voice icon", waitForResourceId(Constant.ID_CHAT_MSG_VOICE_IMAGE, 2000));
		assertTrue("The UI Element not exist: unread icon", waitForResourceId(Constant.ID_CHAT_MSG_VOICE_READED, 2000));
	}

	public void testFunction_Msg_PrivateChat_MsgTpye_Picture() throws UiObjectNotFoundException {
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

		while (waitForResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_MULTI_FILE_COVER);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send picture");
		helper.sendMsg_Photo();

		printLog("step5=Click the picture");
		clickResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER);
		sleep(3000);
		swipe(500, 500, 500, 500, 100);
		assertTrue("The UI element not exist: " + Constant.TXT_SELECT_DIALOG_LIST_FAVORITE,
				waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_FAVORITE, 3000));
		assertTrue("The UI element not exist: " + Constant.TXT_SELECT_DIALOG_LIST_FAVORITE,
				waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_FAVORITE, 3000));
		assertTrue("The UI element not exist: " + Constant.TXT_SELECT_DIALOG_LIST_RELAY,
				waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RELAY, 3000));
	}

	public void testFunction_Msg_PrivateChat_MsgTpye_Video() throws UiObjectNotFoundException {
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

		while (waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_TITLE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VIDEO_TITLE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send video");
		helper.sendMsg_Photograph(3000);

		printLog("step5=Verify the UI Element");
		assertTrue("The UI Element not exist: video icon", waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_ICON, 2000));
		assertTrue("The UI Element not exist: title", waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_TITLE, 2000));
		assertTrue("The UI Element not exist: size", waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_SIZE, 2000));
	}

	public void testFunction_Msg_PrivateChat_MsgType_Document() throws UiObjectNotFoundException, IOException {
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

		printLog("step4=Send one document");
		helper.sendMsg_Document();

		// TODO add check
	}

	public void testFunction_Msg_PrivateChat_MsgTpye_Location() throws UiObjectNotFoundException {
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

		printLog("step4=Send location");
		helper.sendMsg_Location();

		printLog("step5=click the location");
		clickTextContains(Constant.TXT_LOCATION_INFO, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_LOCATION_DETAIL,
				waitForTextExists(Constant.TXT_LOCATION_DETAIL, 3000));
		assertTrue("Enter location detail failed.", waitForResourceId(Constant.ID_CHAT_MSG_LOCATION_MAP, 3000));

		printLog("step6=click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("The UI element not exist: " + Constant.TXT_SELECT_DIALOG_LIST_FAVORITE,
				waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_FAVORITE, 3000));
		assertTrue("The UI element not exist: " + Constant.TXT_SELECT_DIALOG_LIST_RELAY,
				waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RELAY, 3000));
	}

	public void testFunction_Msg_PrivateChat_MsgTpye_Link() throws UiObjectNotFoundException {
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

		printLog("step4=Send link");
		String link = "www.baidu.com";
		helper.sendMsg_Link(link);

		printLog("step5=click the link");
		clickText(Constant.TXT_LINK_BAIDU_DETAIL, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_LINK_BAIDU,
				waitForTextExists(Constant.TXT_LINK_BAIDU, 15000));
	}

	public void testFunction_Msg_PrivateChat_MsgTpye_GroupCard_NoJoin() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("Case-Setup--createPrivateChatBySelectContactInContacts");
		String chatName = helper.createPrivateChatBySelectContactInContacts(phoneNum, password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Send group name card");
		String name = helper.sendMsg_GroupCard(Constant.TXT_MY_NAME);
		printLog("groupname=" + name);

		printLog("step3=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step4=Click The private chat ");
		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));
		String card = getTextOnResourceId(Constant.ID_MSG_QUN_CARD_NAME, 2000);
		assertTrue("Send the group name card failed.", name.contains(card));

		printLog("step5=click the name card");
		clickText(card, true);
		assertTrue("Click the group name card failed.", waitForTextExists(Constant.TXT_GROUP_JOIN, 3000)
				|| waitForTextExists(Constant.TXT_GROUP_APPLYED, 3000));
	}

	public void testFunction_Msg_PrivateChat_SendBar_SendText() throws UiObjectNotFoundException {
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

		printLog("step4=Send link");
		helper.sendMsg_Text();
	}

	public void testFunction_Msg_PrivateChat_SendBar_SendEmotion() throws UiObjectNotFoundException {
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

		String emotion = helper.sendMsg_Emotion();
	}

	public void testFunction_Msg_PrivateChat_SendBar_SendVoice() throws UiObjectNotFoundException {
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

		while (waitForResourceId(Constant.ID_CHAT_MSG_VOICE_IMAGE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VOICE_IMAGE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send voice");
		helper.sendMsg_Voice(3000);
	}

	public void testFunction_Msg_PrivateChat_SendBar_SendVoice_Cancel() throws UiObjectNotFoundException {
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

		while (waitForResourceId(Constant.ID_CHAT_MSG_VOICE_IMAGE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VOICE_IMAGE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send voice");
		helper.sendMsg_Voice_Cancel();
	}

	public void testFunction_Msg_PrivateChat_SendBar_SendPicture() throws UiObjectNotFoundException {
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

		while (waitForResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_MULTI_FILE_COVER);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send picture");
		helper.sendMsg_Picture();
	}

	public void testFunction_Msg_PrivateChat_SendBar_SendPhoto() throws UiObjectNotFoundException {
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

		printLog("step4=Send picture");
		helper.sendMsg_Photo();
	}

	public void testFunction_Msg_PrivateChat_SendBar_SendVideo() throws UiObjectNotFoundException {
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

		while (waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_TITLE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VIDEO_TITLE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send video");
		helper.sendMsg_Video();
	}

	public void testFunction_Msg_PrivateChat_SendBar_SendPhotograph() throws UiObjectNotFoundException {
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

		printLog("step4=Send video");
		helper.sendMsg_Photograph(3000);
	}

	public void testFunction_Msg_PrivateChat_SendBar_SendDocument() throws UiObjectNotFoundException, IOException {
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

		printLog("step4=Send one document");
		helper.sendMsg_Document();
	}

	public void testFunction_Msg_PrivateChat_SendBar_SendLocation() throws UiObjectNotFoundException {
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

		while (waitForTextExists(Constant.TXT_LOCATION_INFO, 2000)) {
			longClickTextBySwipe(Constant.TXT_LOCATION_INFO);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send location");
		helper.sendMsg_Location();
	}

	public void testFunction_Msg_PrivateChat_SendBar_SendLink() throws UiObjectNotFoundException {
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

		while (waitForTextExists(Constant.TXT_LINK_BAIDU_DETAIL, 2000)) {
			longClickTextBySwipe(Constant.TXT_LINK_BAIDU_DETAIL);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send link");
		String link = "www.baidu.com";
		helper.sendMsg_Link(link);
	}
	
	public void testFunction_Msg_PrivateChat_SendBar_Emotion_Enter() throws UiObjectNotFoundException {
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
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
	}
	
	public void testFunction_Msg_PrivateChat_SendBar_Emotion_Favorite_Enter() throws UiObjectNotFoundException {
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

		printLog("step3=Click The private chat");
		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
		
		printLog("step6=Click Add emotion icon");
		getObjectOnResourceIdAndInstance(Constant.ID_CHAT_TOOL_EMOTION_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_EDIT, 3000));
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_MY_FAVORITE_EMOTION, 3000));
	}
	
	public void testFunction_Msg_PrivateChat_SendBar_Emotion_Favorite_Add() throws UiObjectNotFoundException {
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

		printLog("step3=Click The private chat");
		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
		
		printLog("step6=Click Add emotion icon");
		getObjectOnResourceIdAndInstance(Constant.ID_CHAT_TOOL_EMOTION_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_MY_FAVORITE_EMOTION, 3000));
		
		printLog("step7=Click Add icon");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_EMTION_FAVORITE_LIST));
		int children = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		if (children > 1) {
			printLog("The added emotion exist.");
			return;
		}
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, children -1).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_PICTURE, waitForTextExists(Constant.TXT_PICTURE, 3000));
		assertTrue("The Ui Element not exist: " + Constant.TXT_ALL_PICTURE, waitForTextExists(Constant.TXT_ALL_PICTURE, 3000));
		
		printLog("step8=Select one picture");
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_USE, waitForTextExists(Constant.TXT_USE, 3000));
		clickText(Constant.TXT_USE, true);
		int children2 = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		assertTrue("Add emotion failed.", children2 == children + 1);
	}
	
	public void testFunction_Msg_PrivateChat_SendBar_Emotion_Favorite_Delete() throws UiObjectNotFoundException {
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

		printLog("step3=Click The private chat");
		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
		
		printLog("step6=Click Add emotion icon");
		getObjectOnResourceIdAndInstance(Constant.ID_CHAT_TOOL_EMOTION_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_MY_FAVORITE_EMOTION, 3000));
		
		printLog("step7=Click Add icon");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_EMTION_FAVORITE_LIST));
		int children = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		if (children < 2) {
			getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, children -1).click();
			assertTrue("The Ui Element not exist: " + Constant.TXT_PICTURE, waitForTextExists(Constant.TXT_PICTURE, 3000));
			assertTrue("The Ui Element not exist: " + Constant.TXT_ALL_PICTURE, waitForTextExists(Constant.TXT_ALL_PICTURE, 3000));
			
			printLog("step8=Select one picture");
			getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, 0).click();
			assertTrue("The Ui Element not exist: " + Constant.TXT_USE, waitForTextExists(Constant.TXT_USE, 3000));
			clickText(Constant.TXT_USE, true);
			int children2 = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
			assertTrue("Add emotion failed.", children2 == children + 1);
		}
		children = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		
		printLog("step9=Delete the emotion");
		clickText(Constant.TXT_EDIT, true);
		clickResourceId(Constant.ID_GROUP_ALBUM_ITEM);
		clickResourceId(Constant.ID_EMOTION_DELETE);
		clickText(Constant.TXT_CONFIRM, true);
		int children3 = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		assertTrue("Add emotion failed.", children3 == children - 1);
	}

	public void testFunction_Msg_WorkGroup_Settings_SetHeadIcon_ByPhoto() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Click " + Constant.TXT_SETTINGS_EDIT_HEAD);
		clickText(Constant.TXT_SETTINGS_EDIT_HEAD, true);
		assertTrue("Click set Head icon failed.", waitForTextExists(Constant.TXT_DIALOG_SETHEAD_TITLE, 3000));

		printLog("step4=Click " + Constant.TXT_DIALOG_SETHEAD_PHOTO);
		helper.setHeadIcon_Photo();
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_SetHeadIcon_ByPicture() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Click " + Constant.TXT_SETTINGS_EDIT_HEAD);
		clickText(Constant.TXT_SETTINGS_EDIT_HEAD, true);
		assertTrue("Click set Head icon failed.", waitForTextExists(Constant.TXT_DIALOG_SETHEAD_TITLE, 3000));

		printLog("step4=Click " + Constant.TXT_DIALOG_SETHEAD_PICTURE);
		helper.setHeadIcon_Picture();
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_SetGruopName() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Rename group name");
		String newName = "renamegroup";
		helper.renameGroupName(newName);
		assertTrue("Back to the settings failed.", waitForTextContainsExists(newName, 3000));
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_SetGroupSummary() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=set group summary");
		String sum = "autotestgroup";
		helper.setGroupSummary(sum);
		assertTrue("Back to the settings failed.", waitForTextContainsExists(sum, 3000));
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_ManageMember_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER);
		clickText(Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER, true);
		assertTrue("Enter manage member failed.", waitForTextExists(Constant.TXT_MANAGE_MEMBER_TITLE, 3000));

		printLog("step4=Verify the UI Element");
		helper.verifyManageMemberUiElement();
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_ManageMember_AddMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER);
		clickText(Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER, true);
		assertTrue("Enter manage member failed.", waitForTextExists(Constant.TXT_MANAGE_MEMBER_TITLE, 3000));

		printLog("step4=Click " + Constant.TXT_MANAGE_MEMBER_ADD);
		clickText(Constant.TXT_MANAGE_MEMBER_ADD, true);
		assertTrue("Click add to enter Select UI failed.", waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 3000));

		printLog("step5=Verify the selected cannot be select");
		assertTrue("Selected contact cannot be select", !getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0)
				.isEnabled());
		assertTrue("Selected contact cannot be select", !getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1)
				.isEnabled());

		printLog("step5=Select one contact");
		UiObject one = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 2);
		String name = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2).getText();
		one.clickAndWaitForNewWindow();
		assertTrue("Select the contact failed.", one.isChecked());

		printLog("step6=Click the confirm button");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Add the contact failed.", waitForTextExists(name, 5000));
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_ManageMember_RemoveMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER);
		clickText(Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER, true);
		assertTrue("Enter manage member failed.", waitForTextExists(Constant.TXT_MANAGE_MEMBER_TITLE, 3000));

		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_LIST));
		int count = list.getChildCount(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_ITEM));

		printLog("step4=Click " + Constant.TXT_MANAGE_MEMBER_REMOVE);
		clickText(Constant.TXT_MANAGE_MEMBER_REMOVE, true);
		assertTrue("Click the remove failed.", waitForResourceId(Constant.ID_GROUP_REMOVE, 2000));

		printLog("step5=Click the remove button");
		clickResourceId(Constant.ID_GROUP_REMOVE);
		waitForWindowUpdate(2000);
		int now = list.getChildCount(new UiSelector().resourceId(Constant.ID_MANAGE_MEMBER_ITEM));
		assertTrue("Remove the member failed.", count == now + 1);
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_ManageMember_AccessCount() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER);
		clickText(Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER, true);
		assertTrue("Enter manage member failed.", waitForTextExists(Constant.TXT_MANAGE_MEMBER_TITLE, 3000));

		printLog("step4=Click " + Constant.TXT_MANAGE_MEMBER_COUNT);
		clickText(Constant.TXT_MANAGE_MEMBER_COUNT, true);
		assertTrue("Click the Count failed.", waitForTextContainsExists(Constant.TXT_MANAGE_MEMBER_COUNT, 3000));

		printLog("step5=Verify the UI Element");
		helper.verifyManageMemberCountUiElement();
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_ManageMember_CreateGroup() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER);
		clickText(Constant.TXT_WORKGROUP_SETTINGS_MANAGE_MEMBER, true);
		assertTrue("Enter manage member failed.", waitForTextExists(Constant.TXT_MANAGE_MEMBER_TITLE, 3000));

		printLog("step4=Click " + Constant.TXT_MANAGE_MEMBER_CREATEGROUP);
		clickText(Constant.TXT_MANAGE_MEMBER_CREATEGROUP, true);
		assertTrue("Click create group failed.", waitForResourceId(Constant.ID_CHECK, 3000));

		printLog("step5=Select members");
		UiObject c1 = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		UiObject c2 = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1);
		c1.clickAndWaitForNewWindow();
		c2.clickAndWaitForNewWindow();
		assertTrue("Select members failed", c1.isChecked() && c2.isChecked());

		printLog("step6=Click " + Constant.TXT_CONFIRM);
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Click " + Constant.TXT_CONFIRM + " failed.", waitForTextExists(Constant.TXT_NEWGROUP, 3000));
		clickResourceId(Constant.ID_BACK);
		clickText(Constant.TXT_YES, true);
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_MsgTop() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Click " + Constant.TXT_WORKGROUP_SETTINGS_MSG_TOP);
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 1);
		btn.clickAndWaitForNewWindow();
		assertTrue("Click the msg top failed.", btn.isSelected());
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_MsgMute() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Click " + Constant.TXT_WORKGROUP_SETTINGS_MSG_MUTE);
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 2);
		btn.clickAndWaitForNewWindow();
		assertTrue("Click the msg mute failed.", btn.isSelected());
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_AllowBeSearch() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		dragDirection("up", 35);
		dragDirection("up", 35);

		printLog("step3=Click " + Constant.TXT_WORKGROUP_SETTINGS_BE_SEARCH);
		UiObject btn2 = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 2);
		UiObject btn3 = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 3);
		btn2.clickAndWaitForNewWindow();
		assertTrue("Click the button failed.", btn2.isSelected());
		assertTrue("Click the button failed.", btn3.isSelected());

		UiObject adminConfirm = getObjectOnText(Constant.TXT_WORKGROUP_SETTINGS_ADMIN_CONFIRM);
		assertTrue("The admin comfirm is not enabled", adminConfirm.isEnabled());

		UiObject groupQRcode = getObjectOnText(Constant.TXT_WORKGROUP_SETTINGS_ADMIN_CONFIRM);
		assertTrue("The group QRcode is not enabled", groupQRcode.isEnabled());

		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_ShareGroup() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		dragDirection("up", 35);
		dragDirection("up", 35);

		printLog("step3=Click " + Constant.TXT_WORKGROUP_SETTINGS_BE_SEARCH);
		UiObject btn2 = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 2);
		btn2.clickAndWaitForNewWindow();
		assertTrue("Click the button failed.", btn2.isSelected());

		printLog("step4=Click Share icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the share icon failed.", waitForTextExists(Constant.TXT_RELAY_GROUP_NAMECARD, 3000));

		printLog("step5=Click " + Constant.TXT_RELAY_GROUP_NAMECARD);
		clickText(Constant.TXT_RELAY_GROUP_NAMECARD, true);
		waitForWindowUpdate(3000);
		assertTrue("Enter share option failed.", waitForTextExists(groupName, 3000));

		clickText(groupName, true);
		clickText(Constant.TXT_CONFIRM, true);
		clickResourceId(Constant.ID_BACK);
		assertTrue("Share group name card failed", waitForTextExists(Constant.TXT_INVITE_JOIN_GROUP, 3000));
	}

	public void testFunction_Msg_WorkGroup_Settings_GroupMute() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Click " + Constant.TXT_WORKGROUP_SETTINGS_GROUP_MUTE);
		clickText(Constant.TXT_WORKGROUP_SETTINGS_GROUP_MUTE, true);
		assertTrue("Enter " + Constant.TXT_WORKGROUP_SETTINGS_GROUP_MUTE + " failed",
				waitForTextExists(Constant.TXT_WORKGROUP_SETTINGS_GROUP_MUTE, 3000));

		printLog("step4=Select one member");
		clickTextContains(Constant.TXT_MY_NAME, true);
		if (getConfigProperty("pkg").equals("com.unicom.gudong.client")) {
			assertTrue("Select member failed.", getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1).isChecked());
		} else {
			assertTrue("Select member failed.", getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0).isChecked());
		}

		printLog("step5=Click " + Constant.TXT_COMPLETE);
		clickText(Constant.TXT_COMPLETE, true);
		waitForWindowUpdate(3000);
		assertTrue("Back settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step6=Back to group chat and send some text");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Enter chat page failed.", waitForResourceId(Constant.ID_CHAT_LIST, 4000));
		helper.sendMsg_Text();

		printLog("step7=Verify the msg cannot send");
		helper.verifyTheMsgSendFailed();
	}

	public void testFunction_Msg_WorkGroup_Settings_ManagePermission() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		dragDirection("up", 35);
		dragDirection("up", 35);

		printLog("step3=Click " + Constant.TXT_WORKGROUP_SETTINGS_MANAGE_PERMISSION);
		UiObject btn2 = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 6);
		btn2.clickAndWaitForNewWindow();
		assertTrue("Click the button failed.", !btn2.isSelected());
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_ShareLocation() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		dragDirection("up", 35);
		dragDirection("up", 35);

		printLog("step3=Click " + Constant.TXT_WORKGROUP_SETTINGS_SHARE_LOCATION);
		UiObject btn2 = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 1);
		btn2.clickAndWaitForNewWindow();
		assertTrue("Click the button failed.", btn2.isSelected());
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_HelpAdmin() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		dragDirection("up", 35);
		dragDirection("up", 35);

		printLog("step3=Click " + Constant.TXT_WORKGROUP_SETTINGS_HELP_ADMIN);
		clickText(Constant.TXT_WORKGROUP_SETTINGS_HELP_ADMIN, true);
		assertTrue("Click the button failed.", waitForTextExists(Constant.TXT_SET_HELP_ADMIN, 3000));

		printLog("step4=Set one member to help admin");
		UiObject m = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		m.clickAndWaitForNewWindow();
		assertTrue("Select the member failed", m.isChecked());
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Back to settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_HideNum() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		dragDirection("up", 35);
		dragDirection("up", 35);

		printLog("step3=Click " + Constant.TXT_WORKGROUP_SETTINGS_HIDE_NUM);
		UiObject btn2 = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 0);
		btn2.clickAndWaitForNewWindow();
		assertTrue("Click the button failed.", btn2.isSelected());
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_MsgCount() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		dragDirection("up", 35);
		dragDirection("up", 35);

		printLog("step3=Get current Msg count");
		UiObject btn2 = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 7);
		int count1 = Integer.parseInt(btn2.getText());
		printLog("count1=" + count1);

		printLog("step4=Back group chat to send text");
		clickResourceId(Constant.ID_BACK);
		helper.sendMsg_Text();

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		dragDirection("up", 35);
		dragDirection("up", 35);

		printLog("step3=Get current Msg count");
		btn2 = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 7);
		int count2 = Integer.parseInt(btn2.getText());
		printLog("count2=" + count2);
		assertTrue("The msg count not expect infact", count2 == count1);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_Settings_DeleteGroup() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_GROUPCHAT_SETTINGS_DELETE);
		dragDirection("up", 35);
		dragDirection("up", 35);
		clickText(Constant.TXT_GROUPCHAT_SETTINGS_DELETE, true);
		waitForWindowUpdate(3000);
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));
	}

	public void testFunction_Msg_WorkGroup_MsgRelay_NewChat() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Send some msg");
		String longtime = helper.sendMsg_Text();

		printLog("step3=Long click the msg");
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RELAY, 3000));

		printLog("step4=Select relay");
		clickText(Constant.TXT_SELECT_DIALOG_LIST_RELAY, true);
		assertTrue("Click the relay to enter option failed.", waitForTextExists(Constant.TXT_RELAY_TITLE, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_MY_NAME,
				waitForTextContainsExists(Constant.TXT_MY_NAME, 3000));
//		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_TO_WEIXIN_FRIEND,
//				waitForTextContainsExists(Constant.TXT_RELAY_TO_WEIXIN_FRIEND, 2000));
//		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_WEIXIN_MOMENT,
//				waitForTextContainsExists(Constant.TXT_RELAY_WEIXIN_MOMENT, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_NEW_CHAT,
				waitForTextContainsExists(Constant.TXT_RELAY_NEW_CHAT, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_RECENT_CHAT,
				waitForTextContainsExists(Constant.TXT_RELAY_RECENT_CHAT, 2000));

		printLog("step5=Click " + Constant.TXT_RELAY_NEW_CHAT);
		clickText(Constant.TXT_RELAY_NEW_CHAT, true);
		assertTrue("Click the relay new chat failed.", waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 3000));

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);

		if (contact1Check.waitForExists(2000)) {

			printLog("step6=Select contacts");
			contact1Check.clickAndWaitForNewWindow();
			assertTrue("Select contact1 failed.", contact1Check.isChecked());

			printLog("step7=Click confirm button");
			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			waitForWindowUpdate(3000);
			assertTrue("Relay the msg failed.", waitForTextExists(longtime, 3000));

		} else {
			assertTrue("This account has no contact", false);
		}
	}

	public void testFunction_Msg_WorkGroup_MsgRelay_RecentChat() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Send some msg");
		String longtime = helper.sendMsg_Text();

		printLog("step3=Long click the msg");
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RELAY, 3000));

		printLog("step4=Select " + Constant.TXT_SELECT_DIALOG_LIST_RELAY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_RELAY, true);
		assertTrue("Click the relay to enter option failed.", waitForTextExists(Constant.TXT_RELAY_TITLE, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_RECENT_CHAT,
				waitForTextContainsExists(Constant.TXT_RELAY_RECENT_CHAT, 2000));

		printLog("step5=Click one current chat");
		clickTextContains(groupName, true);
		assertTrue("Clickt the current chat failed.", waitForTextExists(Constant.TXT_CONFIRM, 3000));

		printLog("step6=Click the confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(3000);
		assertTrue("Relay the msg failed.", waitForTextExists(longtime, 3000));
	}

	public void testFunction_Msg_WorkGroup_MsgReply() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Send some msg");
		String longtime = helper.sendMsg_Text();

		printLog("step3=Long click the msg");
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RELAY, 3000));

		printLog("step4=Select " + Constant.TXT_SELECT_DIALOG_LIST_REPLY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_REPLY, true);
		waitForWindowUpdate(3000);
		clickText(Constant.TXT_CHAT_TOOL_SEND, true);
//		String text = getTextOnResourceId(Constant.ID_CHAT_MSG_INPUT_ET, 3000);
//		assertTrue("The " + text + " not has " + longtime, text.contains(longtime));
//		assertTrue("The " + text + " not has " + chatName, text.contains(Constant.TXT_MY_NAME));
//		assertTrue("The " + text + " not has " + Constant.TXT_REPLY, text.contains(Constant.TXT_REPLY));
		assertTrue("The text not has " + longtime, waitForTextContainsExists(longtime, 2000));
		assertTrue("The text not has " + Constant.TXT_MY_NAME, waitForTextContainsExists(Constant.TXT_MY_NAME, 2000));
		assertTrue("The text not has " + Constant.TXT_REPLY, waitForTextContainsExists(Constant.TXT_REPLY, 2000));
	}

	public void testFunction_Msg_WorkGroup_MsgFavorite() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Send some msg");
		String longtime = helper.sendMsg_Text();

		printLog("step3=Long click the msg");
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RELAY, 3000));

		printLog("step4=Select " + Constant.TXT_SELECT_DIALOG_LIST_FAVORITE);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_FAVORITE, true);

		printLog("step6=Click back");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Back to Msg failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step7=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		waitForWindowUpdate(3000);
		assertTrue("Click the More failed.", waitForTextExists(Constant.TXT_MORE_MY_FAVORITE, 3000));

		printLog("step8=Click " + Constant.TXT_MORE_MY_FAVORITE);
		clickText(Constant.TXT_MORE_MY_FAVORITE, true);
		waitForWindowUpdate(3000);
		assertTrue("Msg favorite failed.", waitForTextExists(longtime, 3000));
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));
		clickText(groupName, true);
	}

	public void testFunction_Msg_WorkGroup_MsgRecall_RightNow() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Send some msg");
		String longtime = helper.sendMsg_Text();

		printLog("step3=Long click the msg");
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RECALL, 3000));

		printLog("step7=Select " + Constant.TXT_SELECT_DIALOG_LIST_RECALL);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_RECALL, true);
		assertTrue("Recall the msg failed.", waitForTextGone(longtime, 3000));
		assertTrue("Recall notify not exist: " + Constant.TXT_RECALL_NOTIFY,
				waitForTextContainsExists(Constant.TXT_RECALL_NOTIFY, 3000));
	}

	public void testFunction_Msg_WorkGroup_MsgRecall_Later() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Send some text");
		String longtime = helper.sendMsg_Text();

		sleep(2 * 60 * 1000);

		printLog("step3=Long click the msg");
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RECALL, 3000));

		printLog("step4=Select " + Constant.TXT_SELECT_DIALOG_LIST_RECALL);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_RECALL, true);
		assertTrue("The msg be recall.", waitForTextExists(longtime, 3000));
	}

	public void testFunction_Msg_WorkGroup_MsgDelete() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Send some text");
		String longtime = helper.sendMsg_Text();

		printLog("step3=Long click the msg");
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));

		printLog("step7=Select " + Constant.TXT_SELECT_DIALOG_LIST_DELETE);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
		clickText(Constant.TXT_CONFIRM, true);
		if (waitForTextExists(longtime, 2000)) {
			longClickTextBySwipe(longtime);
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}
		assertTrue("Delete the msg failed.", waitForTextGone(longtime, 3000));
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Voice() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_VOICE_IMAGE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VOICE_IMAGE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step2=Send voice");
		helper.sendMsg_Voice(3000);

		printLog("step3=Verify the UI Element");
		assertTrue("The UI Element not exist: Voice icon", waitForResourceId(Constant.ID_CHAT_MSG_VOICE_IMAGE, 2000));
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Picture() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_MULTI_FILE_COVER);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send picture");
		helper.sendMsg_Photo();

		printLog("step5=Click the picture");
		sleep(3000);
		clickResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER);
		sleep(3000);
		swipe(500, 500, 500, 500, 200);
		assertTrue("The UI element not exist: " + Constant.TXT_SELECT_DIALOG_LIST_FAVORITE,
				waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_FAVORITE, 3000));
		assertTrue("The UI element not exist: " + Constant.TXT_SELECT_DIALOG_LIST_FAVORITE,
				waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_FAVORITE, 3000));
		assertTrue("The UI element not exist: " + Constant.TXT_SELECT_DIALOG_LIST_RELAY,
				waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RELAY, 3000));
		pressKey("back");
		pressKey("back");
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Video() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_TITLE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VIDEO_TITLE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send video");
		helper.sendMsg_Video();

		printLog("step5=Verify the UI Element");
		assertTrue("The UI Element not exist: video icon", waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_ICON, 2000));
		assertTrue("The UI Element not exist: title", waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_TITLE, 2000));
		assertTrue("The UI Element not exist: size", waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_SIZE, 2000));
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Document() throws UiObjectNotFoundException, IOException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step4=Send one document");
		helper.sendMsg_Document();

		// TODO add check
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Location() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_LOCATION_INFO, 2000)) {
			longClickTextBySwipe(Constant.TXT_LOCATION_INFO);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send location");
		helper.sendMsg_Location();

		printLog("step5=click the location");
		clickTextContains(Constant.TXT_LOCATION_INFO, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_LOCATION_DETAIL,
				waitForTextExists(Constant.TXT_LOCATION_DETAIL, 3000));
		assertTrue("Enter location detail failed.", waitForResourceId(Constant.ID_CHAT_MSG_LOCATION_MAP, 3000));

		printLog("step6=click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("The UI element not exist: " + Constant.TXT_SELECT_DIALOG_LIST_FAVORITE,
				waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_FAVORITE, 3000));
		assertTrue("The UI element not exist: " + Constant.TXT_SELECT_DIALOG_LIST_RELAY,
				waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RELAY, 3000));
		pressKey("back");
		pressKey("back");
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_ReportLocation() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_LOCATION_REPORT, 2000)) {
			longClickTextBySwipe(Constant.TXT_LOCATION_REPORT, 50);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Report location");
		String title = "autotest";
		helper.sendMsg_ReportLocation(title);

		printLog("step5=Click the report location");
		clickText(Constant.TXT_LOCATION_REPORT, true);
		assertTrue("Enter report location failed", waitForTextExists(Constant.TXT_LOCATION_REPORT_DETAIL, 3000));

		printLog("step5=Click stop report location");
		clickText(Constant.TXT_LOCATION_REPORT_STOP, true);
		clickText(Constant.TXT_YES, true);
		assertTrue("Stop report location failed", waitForTextGone(Constant.TXT_LOCATION_REPORT_STOP, 3000));
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Link() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_LINK_BAIDU_DETAIL, 2000)) {
			longClickTextBySwipe(Constant.TXT_LINK_BAIDU_DETAIL);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send link");
		String link = "www.baidu.com";
		helper.sendMsg_Link(link);

		printLog("step5=click the link");
		clickText(Constant.TXT_LINK_BAIDU_DETAIL, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_LINK_BAIDU,
				waitForTextExists(Constant.TXT_LINK_BAIDU, 15000));
		pressKey("back");
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_GroupCard_Joined() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		dragDirection("up", 35);
		dragDirection("up", 35);

		printLog("step3=Click " + Constant.TXT_WORKGROUP_SETTINGS_BE_SEARCH);
		UiObject btn2 = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 2);
		btn2.clickAndWaitForNewWindow();
		assertTrue("Click the button failed.", btn2.isSelected());

		printLog("step4=Click Share icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the share icon failed.", waitForTextExists(Constant.TXT_RELAY_GROUP_NAMECARD, 3000));

		printLog("step5=Click " + Constant.TXT_RELAY_GROUP_NAMECARD);
		clickText(Constant.TXT_RELAY_GROUP_NAMECARD, true);
		waitForWindowUpdate(3000);
		assertTrue("Enter share option failed.", waitForTextExists(groupName, 3000));

		clickText(groupName, true);
		clickText(Constant.TXT_CONFIRM, true);
		clickResourceId(Constant.ID_BACK);
		assertTrue("Share group name card failed", waitForTextExists(Constant.TXT_INVITE_JOIN_GROUP, 3000));

		printLog("step6=Click " + Constant.TXT_INVITE_JOIN_GROUP);
		clickText(Constant.TXT_INVITE_JOIN_GROUP, true);
		assertTrue("Click the group name card failed.", waitForTextExists(Constant.TXT_GROUP_JOINED, 3000));
		pressKey("back");
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_GroupCard_NoJoin() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		clickResourceId(Constant.ID_BACK);

		printLog("step2=Send group name card");
		String name = helper.sendMsg_GroupCard(groupName);

		printLog("step3=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step4=Click The group chat ");
		clickText(groupName, true);
		assertTrue("Enter group chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));
		String card = getTextOnResourceId(Constant.ID_MSG_QUN_CARD_NAME, 2000);
		assertTrue("Send the group name card failed.", name.contains(card));

		printLog("step5=click the name card");
		clickText(card, true);
		assertTrue("Click the group name card failed.", waitForTextExists(Constant.TXT_GROUP_JOIN, 3000)
				|| waitForTextExists(Constant.TXT_GROUP_APPLYED, 3000));
		pressKey("back");
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Notify() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_NOTIFY_CHECK, 2000)) {
			longClickTextBySwipe(Constant.TXT_NOTIFY_CHECK);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send notify");
		String title = "testnotify";
		helper.sendMsg_Notify(title);

		printLog("step5=click the notify");
		clickText(Constant.TXT_NOTIFY_CHECK, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_NOTIFY_DETAIL,
				waitForTextExists(Constant.TXT_NOTIFY_DETAIL, 3000));
		assertTrue("The UI Element not exist: " + title, waitForTextExists(title, 2000));
		assertTrue("The UI Element not exist: Creater" + Constant.ID_NOTIFY_CREATER,
				waitForResourceId(Constant.ID_NOTIFY_CREATER, 3000));

		printLog("step6=Click the reply to check detail");
		clickResourceId(Constant.ID_NOTIFY_VOTE_INFO);
		assertTrue("The UI Element not exist: " + Constant.TXT_NOTIFY_REPLY_MEMBER,
				waitForTextExists(Constant.TXT_NOTIFY_REPLY_MEMBER, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_NOTIFY_UNREPLAY_MEMBER,
				waitForTextExists(Constant.TXT_NOTIFY_UNREPLAY_MEMBER, 3000));

		clickText(Constant.TXT_NOTIFY_UNREPLAY_MEMBER, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_NOTIFY_REMIND_UNREPLAY_MEMBER,
				waitForTextExists(Constant.TXT_NOTIFY_REMIND_UNREPLAY_MEMBER, 3000));
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Vote() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_VOTE_JOIN, 2000)) {
			longClickTextBySwipe(Constant.TXT_VOTE_JOIN, 50);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send notify");
		String title = "testvote";
		helper.sendMsg_Vote(title);

		printLog("step5=click the vote");
		clickText(Constant.TXT_VOTE_JOIN, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_VOTE_DETAIL,
				waitForTextExists(Constant.TXT_VOTE_DETAIL, 3000));
		assertTrue("The UI Element not exist: " + title, waitForTextExists(title, 2000));
		assertTrue("The UI Element not exist: Creater" + Constant.ID_VOTE_CREATER,
				waitForResourceId(Constant.ID_VOTE_CREATER, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_VOTE_CONFIRM,
				waitForTextExists(Constant.TXT_VOTE_CONFIRM, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_VOTE_STOP,
				waitForTextExists(Constant.TXT_VOTE_STOP, 3000));

		printLog("step6=Click the reply to check detail");
		clickResourceId(Constant.ID_NOTIFY_VOTE_INFO);
		assertTrue("The UI Element not exist: " + Constant.TXT_VOTE_JOIN_MEMBER,
				waitForTextExists(Constant.TXT_VOTE_JOIN_MEMBER, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_VOTE_UNJOIN_MEMBER,
				waitForTextExists(Constant.TXT_VOTE_UNJOIN_MEMBER, 3000));

		clickText(Constant.TXT_VOTE_UNJOIN_MEMBER, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_VOTE_WORKGROUP,
				waitForTextExists(Constant.TXT_VOTE_WORKGROUP, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_VOTE_REMIND_UNJOIN_MEMBER,
				waitForTextExists(Constant.TXT_VOTE_REMIND_UNJOIN_MEMBER, 3000));
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Activity() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_ACTIVITY_JOIN, 2000)) {
			longClickTextBySwipe(Constant.TXT_ACTIVITY_JOIN, 50);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send notify");
		String title = "testactivity";
		helper.sendMsg_Activity(title);

		printLog("step5=click the activity");
		clickText(Constant.TXT_ACTIVITY_JOIN, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_ACTIVITY_DETAIL,
				waitForTextExists(Constant.TXT_ACTIVITY_DETAIL, 3000));
		assertTrue("The UI Element not exist: " + title, waitForTextExists(title, 2000));
		assertTrue("The UI Element not exist: Creater" + Constant.ID_VOTE_CREATER,
				waitForResourceId(Constant.ID_VOTE_CREATER, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_ACTIVITY_CONFIRM,
				waitForTextExists(Constant.TXT_ACTIVITY_CONFIRM, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_ACTIVITY_STOP,
				waitForTextExists(Constant.TXT_ACTIVITY_STOP, 3000));

		printLog("step6=Click the reply to check detail");
		clickResourceId(Constant.ID_NOTIFY_VOTE_INFO);
		assertTrue("The UI Element not exist: " + Constant.TXT_VOTE_JOIN_MEMBER,
				waitForTextExists(Constant.TXT_VOTE_JOIN_MEMBER, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_VOTE_UNJOIN_MEMBER,
				waitForTextExists(Constant.TXT_VOTE_UNJOIN_MEMBER, 3000));

		clickText(Constant.TXT_VOTE_UNJOIN_MEMBER, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_VOTE_WORKGROUP,
				waitForTextExists(Constant.TXT_VOTE_WORKGROUP, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_VOTE_REMIND_UNJOIN_MEMBER,
				waitForTextExists(Constant.TXT_VOTE_REMIND_UNJOIN_MEMBER, 3000));
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Survey_Creater() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_VOTE_JOIN, 2000)) {
			longClickTextBySwipe(Constant.TXT_VOTE_JOIN, 50);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send notify");
		String title = "admin";
		String theme = "testsurvey";
		helper.sendMsg_Survey(title, theme);

		printLog("step5=click the survey");
		clickText(Constant.TXT_SURVEY_JOIN, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_SURVEY_RESULT,
				waitForTextExists(Constant.TXT_SURVEY_RESULT, 5000));
		assertTrue("The UI Element not exist: " + Constant.CONTENT_SURVEY_STOP,
				waitForContentDescription(Constant.CONTENT_SURVEY_STOP, 3000));
		assertTrue("The UI Element not exist: " + Constant.CONTENT_SURVEY_PUBLISH,
				waitForContentDescription(Constant.CONTENT_SURVEY_PUBLISH, 3000));
		assertTrue("The UI Element not exist: " + Constant.CONTENT_SURVEY_EXPORT,
				waitForContentDescription(Constant.CONTENT_SURVEY_EXPORT, 3000));
		assertTrue("The UI Element not exist: " + Constant.CONTENT_SURVEY_SUMMARY,
				waitForContentDescription(Constant.CONTENT_SURVEY_SUMMARY, 3000));

		printLog("step6=Click summary to check detail");
		clickContentDesc(Constant.CONTENT_SURVEY_SUMMARY, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_SURVEY_JOIN_MEMBER,
				waitForTextExists(Constant.TXT_SURVEY_JOIN_MEMBER, 3000));
		assertTrue("The UI Element not exist: " + Constant.CONTENT_SURVEY_UNJOIN_MEMBER,
				waitForContentDescription(Constant.CONTENT_SURVEY_UNJOIN_MEMBER, 3000));
		assertTrue("The UI Element not exist: " + Constant.CONTENT_SURVEY_REMIND_UNJOIN_MEMBER,
				waitForContentDescription(Constant.CONTENT_SURVEY_REMIND_UNJOIN_MEMBER, 3000));

		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Note() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step4=Send notify");
		String title = "testnotify";
		helper.sendMsg_Notify(title);

		printLog("step5=Click " + Constant.TXT_WORKGROUP_NOTE);
		clickText(Constant.TXT_WORKGROUP_NOTE, true);
		assertTrue("Enter note list failed.", waitForTextExists(title, 3000));

		printLog("step6=Click one note");
		clickText(title, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_NOTIFY_DETAIL,
				waitForTextExists(Constant.TXT_NOTIFY_DETAIL, 3000));
		assertTrue("The UI Element not exist: " + title, waitForTextExists(title, 2000));
		assertTrue("The UI Element not exist: Creater" + Constant.ID_NOTIFY_CREATER,
				waitForResourceId(Constant.ID_NOTIFY_CREATER, 3000));

		printLog("step6=Click the reply to check detail");
		clickResourceId(Constant.ID_NOTIFY_VOTE_INFO);
		assertTrue("The UI Element not exist: " + Constant.TXT_NOTIFY_REPLY_MEMBER,
				waitForTextExists(Constant.TXT_NOTIFY_REPLY_MEMBER, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_NOTIFY_UNREPLAY_MEMBER,
				waitForTextExists(Constant.TXT_NOTIFY_UNREPLAY_MEMBER, 3000));

		clickText(Constant.TXT_NOTIFY_UNREPLAY_MEMBER, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_NOTIFY_REMIND_UNREPLAY_MEMBER,
				waitForTextExists(Constant.TXT_NOTIFY_REMIND_UNREPLAY_MEMBER, 3000));
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Files() throws UiObjectNotFoundException, IOException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step4=Send one document");
		String fileName = helper.sendMsg_Document_Small();

		printLog("step5=Click " + Constant.TXT_WORKGROUP_DOCUMENT);
		clickText(Constant.TXT_WORKGROUP_DOCUMENT, true);
		assertTrue("Enter Files list failed.", waitForTextExists(fileName, 3000));

		printLog("step6=Click one document");
		clickText(fileName, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_FILE_REVIEW,
				waitForTextExists(Constant.TXT_FILE_REVIEW, 3000));
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_WorkGroup_MsgTpye_Album() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step4=Send picture");
		helper.sendMsg_Photo();

		printLog("step5=Click " + Constant.TXT_WORKGROUP_ALBUM);
		clickText(Constant.TXT_WORKGROUP_ALBUM, true);
		assertTrue("Enter Album list failed.", waitForResourceId(Constant.ID_GROUP_ALBUM_ITEM, 3000));

		printLog("step6=Click one picture");
		clickResourceId(Constant.ID_GROUP_ALBUM_ITEM);
		assertTrue("Review the picture failed", waitForResourceId(Constant.ID_GV_PAGER, 8000));
		pressKey("back");
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendText() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Send some msg");
		helper.sendMsg_Text();
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendEmotion() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		String emotion = helper.sendMsg_Emotion();
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendVoice() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_VOICE_IMAGE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VOICE_IMAGE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send voice");
		helper.sendMsg_Voice(3000);
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendVoice_Cancel() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_VOICE_IMAGE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VOICE_IMAGE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send voice");
		helper.sendMsg_Voice_Cancel();
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendPicture() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_MULTI_FILE_COVER);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send picture");
		helper.sendMsg_Photo();
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendVideo() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_TITLE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VIDEO_TITLE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send video");
		helper.sendMsg_Video();
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendPhotograph() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_TITLE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VIDEO_TITLE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send video");
		helper.sendMsg_Photograph(3000);
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendPhoto() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_MULTI_FILE_COVER);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send picture");
		helper.sendMsg_Photo();
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendDocument() throws UiObjectNotFoundException, IOException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step4=Send one document");
		helper.sendMsg_Document_Small();
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendLocation() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.TXT_LOCATION_INFO, 2000)) {
			longClickResourceIdBySwipe(Constant.TXT_LOCATION_INFO);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send location");
		helper.sendMsg_Location();
	}

	public void testFunction_Msg_WorkGroup_SendBar_ReportLocation() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_LOCATION_REPORT, 2000)) {
			longClickTextBySwipe(Constant.TXT_LOCATION_REPORT, 50);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Report location");
		String title = "autotest";
		helper.sendMsg_ReportLocation(title);
	}

	public void testFunction_Msg_WorkGroup_SendBar_ShareLocation() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_LOCATION_SHARE_TIP, 2000)) {
			longClickTextBySwipe(Constant.TXT_LOCATION_SHARE_TIP, 50);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Share location");
		helper.sendMsg_ShareLocation();
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendLink() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.TXT_LINK_BAIDU_DETAIL, 2000)) {
			longClickResourceIdBySwipe(Constant.TXT_LINK_BAIDU_DETAIL);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send link");
		String link = "www.baidu.com";
		helper.sendMsg_Link(link);
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendNotify() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.TXT_NOTIFY_CHECK, 2000)) {
			longClickResourceIdBySwipe(Constant.TXT_NOTIFY_CHECK);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send notify");
		String title = "testnotify";
		helper.sendMsg_Notify(title);
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendVote() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.TXT_VOTE_JOIN, 2000)) {
			longClickTextBySwipe(Constant.TXT_VOTE_JOIN, 50);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send vote");
		String title = "testvote";
		helper.sendMsg_Vote(title);
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendActivity() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_ACTIVITY_JOIN, 2000)) {
			longClickTextBySwipe(Constant.TXT_ACTIVITY_JOIN, 50);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send notify");
		String title = "testactivity";
		helper.sendMsg_Activity(title);
	}

	public void testFunction_Msg_WorkGroup_SendBar_SendSurvey() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_VOTE_JOIN, 2000)) {
			longClickTextBySwipe(Constant.TXT_VOTE_JOIN, 50);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send notify");
		String title = "admin";
		String theme = "testsurvey";
		helper.sendMsg_Survey(title, theme);
	}
	
	public void testFunction_Msg_WorkGroup_SendBar_Emotion_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
	}
	
	public void testFunction_Msg_WorkGroup_SendBar_Emotion_Favorite_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
		
		printLog("step6=Click Add emotion icon");
		getObjectOnResourceIdAndInstance(Constant.ID_CHAT_TOOL_EMOTION_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_EDIT, 3000));
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_MY_FAVORITE_EMOTION, 3000));
		
		clickResourceId(Constant.ID_BACK);
	}
	
	public void testFunction_Msg_WorkGroup_SendBar_Emotion_Favorite_Add() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
		
		printLog("step6=Click Add emotion icon");
		getObjectOnResourceIdAndInstance(Constant.ID_CHAT_TOOL_EMOTION_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_MY_FAVORITE_EMOTION, 3000));
		
		printLog("step7=Click Add icon");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_EMTION_FAVORITE_LIST));
		int children = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		if (children > 1) {
			printLog("The added emotion exist.");
			return;
		}
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, children -1).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_PICTURE, waitForTextExists(Constant.TXT_PICTURE, 3000));
		assertTrue("The Ui Element not exist: " + Constant.TXT_ALL_PICTURE, waitForTextExists(Constant.TXT_ALL_PICTURE, 3000));
		
		printLog("step8=Select one picture");
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_USE, waitForTextExists(Constant.TXT_USE, 3000));
		clickText(Constant.TXT_USE, true);
		int children2 = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		assertTrue("Add emotion failed.", children2 == children + 1);
		
		clickResourceId(Constant.ID_BACK);
	}
	
	public void testFunction_Msg_WorkGroup_SendBar_Emotion_Favorite_Delete() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("workgroup_name");

		printLog("step1=Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
		
		printLog("step6=Click Add emotion icon");
		getObjectOnResourceIdAndInstance(Constant.ID_CHAT_TOOL_EMOTION_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_MY_FAVORITE_EMOTION, 3000));
		
		printLog("step7=Click Add icon");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_EMTION_FAVORITE_LIST));
		int children = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		if (children < 2) {
			getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, children -1).click();
			assertTrue("The Ui Element not exist: " + Constant.TXT_PICTURE, waitForTextExists(Constant.TXT_PICTURE, 3000));
			assertTrue("The Ui Element not exist: " + Constant.TXT_ALL_PICTURE, waitForTextExists(Constant.TXT_ALL_PICTURE, 3000));
			
			printLog("step8=Select one picture");
			getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, 0).click();
			assertTrue("The Ui Element not exist: " + Constant.TXT_USE, waitForTextExists(Constant.TXT_USE, 3000));
			clickText(Constant.TXT_USE, true);
			int children2 = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
			assertTrue("Add emotion failed.", children2 == children + 1);
		}
		children = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		
		printLog("step9=Delete the emotion");
		clickText(Constant.TXT_EDIT, true);
		clickResourceId(Constant.ID_GROUP_ALBUM_ITEM);
		clickResourceId(Constant.ID_EMOTION_DELETE);
		clickText(Constant.TXT_CONFIRM, true);
		int children3 = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		assertTrue("Add emotion failed.", children3 == children - 1);
		
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_GroupChat_Settings_SetHeadIcon_ByPicture() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Click " + Constant.TXT_SETTINGS_EDIT_HEAD);
		clickText(Constant.TXT_SETTINGS_EDIT_HEAD, true);
		assertTrue("Click set Head icon failed.", waitForTextExists(Constant.TXT_DIALOG_SETHEAD_TITLE, 3000));

		printLog("step4=Click " + Constant.TXT_DIALOG_SETHEAD_PHOTO);
		helper.setHeadIcon_Photo();
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_GroupChat_Settings_SetHeadIcon_ByPhoto() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Click " + Constant.TXT_SETTINGS_EDIT_HEAD);
		clickText(Constant.TXT_SETTINGS_EDIT_HEAD, true);
		assertTrue("Click set Head icon failed.", waitForTextExists(Constant.TXT_DIALOG_SETHEAD_TITLE, 3000));

		printLog("step4=Click " + Constant.TXT_DIALOG_SETHEAD_PICTURE);
		helper.setHeadIcon_Picture();
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_GroupChat_Settings_SetGruopName() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Rename group name");
		String newName = "renamegroup";
		helper.renameGroupName(newName);
		assertTrue("Back to the settings failed.", waitForTextContainsExists(newName, 3000));
		clickResourceId(Constant.ID_BACK);
		assertTrue("Back to the chat failed.", waitForTextContainsExists(newName, 3000));
	}

	public void testFunction_Msg_GroupChat_Settings_MsgTop() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Click " + Constant.TXT_WORKGROUP_SETTINGS_MSG_TOP);
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 1);
		btn.clickAndWaitForNewWindow();
		assertTrue("Click the msg top failed.", btn.isSelected());
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_GroupChat_Settings_ManageMember_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_GROUPCHAT_SETTINGS_MANAGE_MEMBER);
		clickText(Constant.TXT_GROUPCHAT_SETTINGS_MANAGE_MEMBER, true);
		assertTrue("Enter manage member failed.", waitForTextExists(Constant.TXT_MANAGE_MEMBER_TITLE, 3000));

		printLog("step4=Verify the UI Element");
		helper.verifyManageMemberUiElement_GroupChat();
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_GroupChat_Settings_ManageMember_AddMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_GROUPCHAT_SETTINGS_MANAGE_MEMBER);
		clickText(Constant.TXT_GROUPCHAT_SETTINGS_MANAGE_MEMBER, true);
		assertTrue("Enter manage member failed.", waitForTextExists(Constant.TXT_MANAGE_MEMBER_TITLE, 3000));

		printLog("step4=Click " + Constant.TXT_MANAGE_MEMBER_ADD);
		clickText(Constant.TXT_MANAGE_MEMBER_ADD, true);
		assertTrue("Click add to enter Select UI failed.", waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 3000));

		printLog("step5=Verify the selected cannot be select");
		assertTrue("Selected contact cannot be select", !getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0)
				.isEnabled());
		assertTrue("Selected contact cannot be select", !getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 1)
				.isEnabled());

		printLog("step5=Select one contact");
		UiObject one = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 2);
		String name = getObjectOnResourceIdAndInstance(Constant.ID_GROUP_MEMBER_NAME, 2).getText();
		one.clickAndWaitForNewWindow();
		assertTrue("Select the contact failed.", one.isChecked());

		printLog("step6=Click the confirm button");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Add the contact failed.", waitForTextExists(name, 5000));
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_GroupChat_Settings_MsgCount() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Get current Msg count");
		UiObject btn2 = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 3);
		int count1 = Integer.parseInt(btn2.getText());
		printLog("count1=" + count1);

		printLog("step4=Back group chat to send text");
		clickResourceId(Constant.ID_BACK);
		helper.sendMsg_Text();

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=Get current Msg count");
		btn2 = getObjectOnResourceIdAndInstance(Constant.ID_WORKGROUP_SETTINGS_SWITCHBUTTON, 3);
		while (btn2.getText().equals(Constant.TXT_QUERY_ING)) {
			sleep(1000);
		}
		int count2 = Integer.parseInt(btn2.getText());
		printLog("count2=" + count2);
		assertTrue("The msg count not expect infact", count2 == count1);
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_GroupChat_Settings_DeleteGroup() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);

		printLog("step2=Click settings icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("Click the icon to enter settings failed.", waitForTextExists(Constant.TXT_SETTINGS, 3000));

		printLog("step3=click " + Constant.TXT_GROUPCHAT_SETTINGS_DELETE);
		clickText(Constant.TXT_GROUPCHAT_SETTINGS_DELETE, true);
		waitForWindowUpdate(3000);
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));
	}

	public void testFunction_Msg_GroupChat_SendBar_SendText() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Send some msg");
		helper.sendMsg_Text();
	}

	public void testFunction_Msg_GroupChat_SendBar_SendEmotion() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step2=Send some emotion");
		String emotion = helper.sendMsg_Emotion();
	}

	public void testFunction_Msg_GroupChat_SendBar_SendVoice() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_VOICE_IMAGE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VOICE_IMAGE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send voice");
		helper.sendMsg_Voice(3000);
	}

	public void testFunction_Msg_GroupChat_SendBar_SendVoice_Cancel() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_VOICE_IMAGE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VOICE_IMAGE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send voice");
		helper.sendMsg_Voice_Cancel();
	}

	public void testFunction_Msg_GroupChat_SendBar_SendPicture() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_MULTI_FILE_COVER);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send picture");
		helper.sendMsg_Photo();
	}

	public void testFunction_Msg_GroupChat_SendBar_SendVideo() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_TITLE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VIDEO_TITLE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send video");
		helper.sendMsg_Video();
	}

	public void testFunction_Msg_GroupChat_SendBar_SendPhoto() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_MULTI_FILE_COVER);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send picture");
		helper.sendMsg_Photo();
	}

	public void testFunction_Msg_GroupChat_SendBar_SendPhotograph() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_TITLE, 2000)) {
			longClickResourceIdBySwipe(Constant.ID_CHAT_MSG_VIDEO_TITLE);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send video");
		helper.sendMsg_Photograph(3000);
	}

	public void testFunction_Msg_GroupChat_SendBar_SendDocument() throws UiObjectNotFoundException, IOException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		printLog("step4=Send one document");
		helper.sendMsg_Document_Small();
	}

	public void testFunction_Msg_GroupChat_SendBar_SendLocation() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.TXT_LOCATION_INFO, 2000)) {
			longClickResourceIdBySwipe(Constant.TXT_LOCATION_INFO);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send location");
		helper.sendMsg_Location();
	}

	public void testFunction_Msg_GroupChat_SendBar_SendLink() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForResourceId(Constant.TXT_LINK_BAIDU_DETAIL, 2000)) {
			longClickResourceIdBySwipe(Constant.TXT_LINK_BAIDU_DETAIL);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send link");
		String link = "www.baidu.com";
		helper.sendMsg_Link(link);
	}

	public void testFunction_Msg_GroupChat_SendBar_SendNotify() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		while (waitForTextExists(Constant.TXT_NOTIFY_CHECK, 2000)) {
			longClickTextBySwipe(Constant.TXT_NOTIFY_CHECK);
			assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_DELETE, 3000));
			clickText(Constant.TXT_SELECT_DIALOG_LIST_DELETE, true);
			clickText(Constant.TXT_CONFIRM, true);
		}

		printLog("step4=Send notify");
		String title = "testnotify";
		helper.sendMsg_Notify(title);
	}
	
	public void testFunction_Msg_GroupChat_SendBar_Emotion_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
	}
	
	public void testFunction_Msg_GroupChat_SendBar_Emotion_Favorite_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
		
		printLog("step6=Click Add emotion icon");
		getObjectOnResourceIdAndInstance(Constant.ID_CHAT_TOOL_EMOTION_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_EDIT, 3000));
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_MY_FAVORITE_EMOTION, 3000));
		
		clickResourceId(Constant.ID_BACK);
	}
	
	public void testFunction_Msg_GroupChat_SendBar_Emotion_Favorite_Add() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
		
		printLog("step6=Click Add emotion icon");
		getObjectOnResourceIdAndInstance(Constant.ID_CHAT_TOOL_EMOTION_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_MY_FAVORITE_EMOTION, 3000));
		
		printLog("step7=Click Add icon");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_EMTION_FAVORITE_LIST));
		int children = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		if (children > 1) {
			printLog("The added emotion exist.");
			return;
		}
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, children -1).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_PICTURE, waitForTextExists(Constant.TXT_PICTURE, 3000));
		assertTrue("The Ui Element not exist: " + Constant.TXT_ALL_PICTURE, waitForTextExists(Constant.TXT_ALL_PICTURE, 3000));
		
		printLog("step8=Select one picture");
		getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_USE, waitForTextExists(Constant.TXT_USE, 3000));
		clickText(Constant.TXT_USE, true);
		int children2 = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		assertTrue("Add emotion failed.", children2 == children + 1);
		
		clickResourceId(Constant.ID_BACK);
	}
	
	public void testFunction_Msg_GroupChat_SendBar_Emotion_Favorite_Delete() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String groupName = getConfigProperty("groupchat_name");

		printLog("step1=create group chat by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;
		
		printLog("step4=Click the emotion icon");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("Click the emotion failed", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_GALLERY, 3000));
		
		printLog("step5=Click emotion favorite icon");
		UiCollection tab = new UiCollection(new UiSelector().resourceId(Constant.ID_EMOTION_TAB));
		UiObject favo = tab.getChild(new UiSelector().resourceId(Constant.ID_EMOTION_VICON).instance(1));
		favo.click();
		assertTrue("Click the emotion favrite icon failed.", favo.isSelected());
		
		printLog("step6=Click Add emotion icon");
		getObjectOnResourceIdAndInstance(Constant.ID_CHAT_TOOL_EMOTION_ITEM, 0).click();
		assertTrue("The Ui Element not exist: " + Constant.TXT_EDIT, waitForTextExists(Constant.TXT_MY_FAVORITE_EMOTION, 3000));
		
		printLog("step7=Click Add icon");
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_EMTION_FAVORITE_LIST));
		int children = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		if (children < 2) {
			getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, children -1).click();
			assertTrue("The Ui Element not exist: " + Constant.TXT_PICTURE, waitForTextExists(Constant.TXT_PICTURE, 3000));
			assertTrue("The Ui Element not exist: " + Constant.TXT_ALL_PICTURE, waitForTextExists(Constant.TXT_ALL_PICTURE, 3000));
			
			printLog("step8=Select one picture");
			getObjectOnResourceIdAndInstance(Constant.ID_GROUP_ALBUM_ITEM, 0).click();
			assertTrue("The Ui Element not exist: " + Constant.TXT_USE, waitForTextExists(Constant.TXT_USE, 3000));
			clickText(Constant.TXT_USE, true);
			int children2 = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
			assertTrue("Add emotion failed.", children2 == children + 1);
		}
		children = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		
		printLog("step9=Delete the emotion");
		clickText(Constant.TXT_EDIT, true);
		clickResourceId(Constant.ID_GROUP_ALBUM_ITEM);
		clickResourceId(Constant.ID_EMOTION_DELETE);
		clickText(Constant.TXT_CONFIRM, true);
		int children3 = list.getChildCount(new UiSelector().resourceId(Constant.ID_GROUP_ALBUM_ITEM));
		assertTrue("Add emotion failed.", children3 == children - 1);
		
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_Msg_LinkCard_ReviewCard() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String link = "www.baidu.com";
		helper.sendMsg_Text(link);
		longClickTextBySwipe(link);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_COPY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_COPY, true);
		assertTrue("Select copy failed.", waitForTextGone(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step6=Click the plus button in toolbar");
		clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		assertTrue("The link card not exist.", waitForTextContainsExists(Constant.TXT_LINKCARD_TITLE, 3000));

		printLog("step7=Verify the UI Element");
		assertTrue("The UI Element not exist: " + Constant.TXT_LINK_BAIDU_DETAIL,
				waitForTextContainsExists(Constant.TXT_LINK_BAIDU_DETAIL, 15000));
		assertTrue("The UI Element not exist: Icon", waitForResourceId(Constant.ID_MORE_ICON, 3000));
		assertTrue("The UI Element not exist: Summary", waitForResourceId(Constant.ID_MORE_SUMMARY, 3000));
	}

	public void testFunction_Msg_LinkCard_SendCard() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String link = "www.163.com";
		helper.sendMsg_Text(link);
		longClickTextBySwipe(link);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_COPY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_COPY, true);
		assertTrue("Select copy failed.", waitForTextGone(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step6=Click the plus button in toolbar");
		clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		assertTrue("The link card not exist.", waitForTextContainsExists(Constant.TXT_LINKCARD_TITLE, 3000));

		printLog("step7=Click the link Card");
		clickResourceId(Constant.ID_MORE_SUMMARY);
		assertTrue("Click yes failed.", waitForTextContainsExists(Constant.TXT_LINK_NETEAST, 5000));

		printLog("step8=click the link");
		clickText(Constant.TXT_LINK_NETEAST, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_LINK_NETEAST_DETAIL,
				waitForTextContainsExists(Constant.TXT_LINK_NETEAST_DETAIL, 3000));
	}

	public void testFunction_Msg_LinkCard_SameLink() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String link = "www.163.com";
		helper.sendMsg_Text(link);
		longClickTextBySwipe(link);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_COPY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_COPY, true);
		assertTrue("Select copy failed.", waitForTextGone(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step6=Click the plus button in toolbar");
		clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		assertTrue("The link card not exist.", !waitForTextContainsExists(Constant.TXT_LINKCARD_TITLE, 3000));
	}

	public void testFunction_Msg_LinkCard_NotLink() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String longTime = helper.sendMsg_Text();
		longClickTextBySwipe(longTime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_COPY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_COPY, true);
		assertTrue("Select copy failed.", waitForTextGone(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step6=Click the plus button in toolbar");
		clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		assertTrue("The link card not exist.", !waitForTextContainsExists(Constant.TXT_LINKCARD_TITLE, 3000));
	}

	public void testFunction_Msg_LinkCard_InvalidLink() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String link = "aaaaaaaaa";
		helper.sendMsg_Text(link);
		longClickTextBySwipe(link);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_COPY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_COPY, true);
		assertTrue("Select copy failed.", waitForTextGone(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step6=Click the plus button in toolbar");
		clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		assertTrue("The link card not exist.", !waitForTextContainsExists(Constant.TXT_LINKCARD_TITLE, 3000));
	}

	public void testFunction_Msg_LinkCard_NotPlusButton() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String link = "www.baidu.com";
		helper.sendMsg_Text(link);
		longClickTextBySwipe(link);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_COPY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_COPY, true);
		assertTrue("Select copy failed.", waitForTextGone(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step6=Click the plus button in toolbar");
		clickResourceId(Constant.ID_CHAT_TOOL_EMOTION);
		assertTrue("The link card not exist.", !waitForTextContainsExists(Constant.TXT_LINKCARD_TITLE, 3000));
	}

	public void testFunction_Msg_LinkCard_SendLink() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String link = "www.163.com";
		helper.sendMsg_Text(link);
		longClickTextBySwipe(link);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_COPY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_COPY, true);
		assertTrue("Select copy failed.", waitForTextGone(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step6=Click the plus button in toolbar");
		clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		assertTrue("The link card not exist.", waitForTextContainsExists(Constant.TXT_CHAT_TOOL_LINK, 3000));

		printLog("step7=Click " + Constant.TXT_CHAT_TOOL_LINK);
		clickText(Constant.TXT_CHAT_TOOL_LINK, true);
		assertTrue("The text not exist: " + Constant.TXT_LINK_TITLE, waitForTextExists(Constant.TXT_LINK_TITLE, 5000));

		printLog("step8=Verify UI Element");
		clickText(Constant.TXT_REVIEW, true);
		assertTrue("The text not exist: " + Constant.TXT_LINK_NETEAST,
				waitForTextExists(Constant.TXT_LINK_NETEAST, 5000));
		assertTrue("The text not exist: " + link, waitForTextExists(link, 5000));
	}

	public void testFunction_Msg_LinkCard_SendCard_WorkGroup() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		String groupName = getConfigProperty("workgroup_name");

		printLog("Case-Setup--Create work group by plus button");
		helper.createWorkGroupByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		clickResourceId(Constant.ID_BACK);
		assertTrue("Back Msg list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

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

		printLog("step4=Long click the msg");
		String link = "www.baidu.com";
		helper.sendMsg_Text(link);
		longClickTextBySwipe(link);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_COPY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_COPY, true);
		assertTrue("Select copy failed.", waitForTextGone(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step6=Back to Msg list");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Back Msg list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step7=Enter workgroup");
		clickText(groupName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));

		printLog("step8=Click the plus button in toolbar");
		clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		assertTrue("The link card not exist.", waitForTextContainsExists(Constant.TXT_LINKCARD_TITLE, 3000));

		printLog("step9=Click the link Card");
		clickResourceId(Constant.ID_MORE_SUMMARY);
		assertTrue("Click yes failed.", waitForTextContainsExists(Constant.TXT_LINK_BAIDU_DETAIL, 15000));

		printLog("step10=click the link");
		clickText(Constant.TXT_LINK_BAIDU_DETAIL, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_LINK_BAIDU,
				waitForTextContainsExists(Constant.TXT_LINK_BAIDU, 15000));
		pressKey("back");
	}

	public void testFunction_Msg_LinkCard_SendCard_GroupChat() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());

		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		String groupName = getConfigProperty("workgroup_name");

		printLog("Case-Setup--Create work group by plus button");
		helper.createGroupChatByPlusButton(phoneNum, password, groupName);
		DeleteGroup = true;

		clickResourceId(Constant.ID_BACK);
		assertTrue("Back Msg list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

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

		printLog("step4=Long click the msg");
		String link = "www.163.com";
		helper.sendMsg_Text(link);
		longClickTextBySwipe(link);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_COPY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_COPY, true);
		assertTrue("Select copy failed.", waitForTextGone(Constant.TXT_SELECT_DIALOG_LIST_COPY, 3000));

		printLog("step6=Back to Msg list");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Back Msg list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step7=Enter groupchat");
		clickText(groupName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));

		printLog("step8=Click the plus button in toolbar");
		clickResourceId(Constant.ID_CHAT_MENU_TOOL);
		assertTrue("The link card not exist.", waitForTextContainsExists(Constant.TXT_LINKCARD_TITLE, 3000));

		printLog("step9=Click the link Card");
		clickResourceId(Constant.ID_MORE_SUMMARY);
		assertTrue("Click yes failed.", waitForTextContainsExists(Constant.TXT_LINK_NETEAST, 10000));

		printLog("step10=click the link");
		clickText(Constant.TXT_LINK_NETEAST, true);
		assertTrue("The UI Element not exist: " + Constant.TXT_LINK_NETEAST_DETAIL,
				waitForTextContainsExists(Constant.TXT_LINK_NETEAST_DETAIL, 10000));
		pressKey("back");
	}

	public void testFunction_Msg_BatchDealMsg_SelectMore() throws UiObjectNotFoundException {
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

		printLog("step4=Send some msg");
		String longtime = helper.sendMsg_Text();

		printLog("step5=Long click a message");
		longClickTextBySwipe(longtime);
		assertTrue("Long click message failed", waitForResourceId(Constant.ID_SELECT_DIALOG_LISTVIEW, 3000));

		printLog("step6=Select More in options list");
		clickText(Constant.TXT_SELECT_DIALOG_LIST_MORE, true);
		assertTrue("Select More in option list failed", waitForResourceId(Constant.ID_BATCH_DEAL_MSG, 2000));

		printLog("step7=Select more msg");
		UiObject msg = getObjectOnResourceIdAndInstance(Constant.ID_MSG_CHECKBOX, 0);
		msg.clickAndWaitForNewWindow();
		assertTrue("Select more msg failed.", msg.isChecked());
	}

	public void testFunction_Msg_BatchDealMsg_MsgRelay() throws UiObjectNotFoundException {
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

		printLog("step4=Send some msg");
		String longtime = helper.sendMsg_Text();

		printLog("step5=Long click a message");
		longClickTextBySwipe(longtime);
		assertTrue("Long click message failed", waitForResourceId(Constant.ID_SELECT_DIALOG_LISTVIEW, 3000));

		printLog("step6=Select More in options list");
		clickText(Constant.TXT_SELECT_DIALOG_LIST_MORE, true);
		assertTrue("Select More in option list failed", waitForResourceId(Constant.ID_BATCH_DEAL_MSG, 2000));

		printLog("step7=Select share");
		clickResourceId(Constant.ID_BATCH_SHARE);
		assertTrue("Click the relay to enter option failed.", waitForTextExists(Constant.TXT_RELAY_TITLE, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_RECENT_CHAT,
				waitForTextContainsExists(Constant.TXT_RELAY_RECENT_CHAT, 2000));

		printLog("step8=Click one current chat");
		clickTextContains(chatName, true);
		assertTrue("Clickt the current chat failed.", waitForTextExists(Constant.TXT_CONFIRM, 3000));

		printLog("step9=Click the confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(3000);
		assertTrue("Relay the msg failed.", waitForTextExists(longtime, 3000));
	}

	public void testFunction_Msg_BatchDealMsg_MsgFavorite() throws UiObjectNotFoundException {
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

		printLog("step4=Send some msg");
		String longtime = helper.sendMsg_Text();

		printLog("step5=Long click a message");
		longClickTextBySwipe(longtime);
		assertTrue("Long click message failed", waitForResourceId(Constant.ID_SELECT_DIALOG_LISTVIEW, 3000));

		printLog("step6=Select More in options list");
		clickText(Constant.TXT_SELECT_DIALOG_LIST_MORE, true);
		assertTrue("Select More in option list failed", waitForResourceId(Constant.ID_BATCH_DEAL_MSG, 2000));

		printLog("step6=Select favorite");
		clickResourceId(Constant.ID_BATCH_FAVORITE);

		printLog("step7=Click back");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Back to Msg failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step8=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		waitForWindowUpdate(3000);
		assertTrue("Click the More failed.", waitForTextExists(Constant.TXT_MORE_MY_FAVORITE, 3000));

		printLog("step9=Click " + Constant.TXT_MORE_MY_FAVORITE);
		clickText(Constant.TXT_MORE_MY_FAVORITE, true);
		waitForWindowUpdate(3000);
		assertTrue("Msg favorite failed.", waitForTextExists(longtime, 5000));
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));
		clickText(chatName, true);
	}

	public void testFunction_Msg_BatchDealMsg_MsgDelete() throws UiObjectNotFoundException {
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

		printLog("step4=Send some msg");
		String longtime = helper.sendMsg_Text();

		while (waitForTextExists(longtime, 3000)) {
			printLog("step5=Long click a message");
			longClickTextBySwipe(longtime);
			assertTrue("Long click message failed", waitForResourceId(Constant.ID_SELECT_DIALOG_LISTVIEW, 3000));

			printLog("step6=Select More in options list");
			clickText(Constant.TXT_SELECT_DIALOG_LIST_MORE, true);
			assertTrue("Select More in option list failed", waitForResourceId(Constant.ID_BATCH_DEAL_MSG, 2000));

			printLog("step7=Select Delete");
			clickResourceId(Constant.ID_BATCH_DELETE);
		}
		assertTrue("Delete the msg failed.", waitForTextGone(longtime, 3000));
	}

	public void testFunction_Msg_BatchDealMsg_Cancel() throws UiObjectNotFoundException {
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

		printLog("step4=Send some msg");
		String longtime = helper.sendMsg_Text();

		printLog("step5=Long click a message");
		longClickTextBySwipe(longtime);
		assertTrue("Long click message failed", waitForResourceId(Constant.ID_SELECT_DIALOG_LISTVIEW, 3000));

		printLog("step6=Select More in options list");
		clickText(Constant.TXT_SELECT_DIALOG_LIST_MORE, true);
		assertTrue("Select More in option list failed", waitForResourceId(Constant.ID_BATCH_DEAL_MSG, 2000));

		printLog("step7=Cancel batch deal");
		clickText(Constant.TXT_CANCEL, true);
		assertTrue("Long click message failed", !waitForResourceId(Constant.ID_BATCH_DEAL_MSG, 2000));
	}

	public void testFunction_Address_Org_DragSwitchButton() throws UiObjectNotFoundException {
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

		printLog("step3=Drag switch button");
		Rect p1 = getCoordinateOfResourceId(Constant.ID_ADDRESS_SWITCH_BUTTON);
		printLog("p1" + p1);
		drag(p1.centerX(), p1.centerY(), p1.centerX(), p1.centerY() - 500, 35);
		Rect p2 = getCoordinateOfResourceId(Constant.ID_ADDRESS_SWITCH_BUTTON);
		printLog("p2" + p2);
		assertTrue("The swithc button can be drag", p1.equals(p2));
	}

	public void testFunction_Address_Org_SwitchButton_TypeNode() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String pkg = getConfigProperty("pkg");
		if (pkg.equals("com.gudong.client")) {
			printLog("This apk is test apk, the case not fit");
			return;
		}

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));
		
		String type = helper.getCurrentType();

		if (type.equals("home")) {
			printLog("step3=Click switch button");
			clickResourceId(Constant.ID_ADDRESS_SWITCH_BUTTON);
		}
		sleep(1000);
		type = helper.getCurrentType();
		assertTrue("The current type is not node", type.equals("node"));
		
		printLog("step4=Switch button");
		clickResourceId(Constant.ID_ADDRESS_SWITCH_BUTTON);
		sleep(1000);
		type = helper.getCurrentType();
		assertTrue("The current type is not home", type.equals("home"));
	}

	public void testFunction_Address_Org_SwitchButton_RootNode() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String pkg = getConfigProperty("pkg");
		if (pkg.equals("com.unicom.gudong.client")) {
			printLog("This apk is release apk, the case not fit");
			return;
		}

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));
		
		String type = helper.getCurrentType();
		type = helper.getCurrentType();
		assertTrue("The current type is not node", type.equals("node"));
		
		printLog("step4=Enter branch");
		clickResourceId(Constant.ID_ADDRESS_DEPART_TITLE);
		sleep(1000);
		type = helper.getCurrentType();
		assertTrue("The current type is not home", type.equals("home"));
	}
	
	public void testFunction_Address_Org_SwitchButton_TypeHome() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		String pkg = getConfigProperty("pkg");
		if (pkg.equals("com.gudong.client")) {
			printLog("This apk is test apk, the case not fit");
			return;
		}

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));
		
		String type = helper.getCurrentType();

		if (type.equals("node")) {
			printLog("step3=Click switch button");
			clickResourceId(Constant.ID_ADDRESS_SWITCH_BUTTON);
		}
		sleep(1000);
		type = helper.getCurrentType();
		assertTrue("The current type is not node", type.equals("home"));
		
		printLog("step4=Switch button");
		clickResourceId(Constant.ID_ADDRESS_SWITCH_BUTTON);
		sleep(1000);
		type = helper.getCurrentType();
		assertTrue("The current type is not home", type.equals("node"));
	}
	
	public void testFunction_Address_Contacts_CloudUser_NoAdmin() throws UiObjectNotFoundException {
		helper.clearAppUserData();
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum_cloud");
		String password = getConfigProperty("password_cloud");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);
		LogOut = true;
		assertTrue("Ui Element not exist: ID_TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Cloud user login failed.", !waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click " + Constant.TXT_ADDRESS_CONTACT);
		clickText(Constant.TXT_ADDRESS_CONTACT, true);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 3000));
		assertTrue("UI element exist: " + Constant.TXT_ADDRESS_ADMIN,
				!waitForTextExists(Constant.TXT_ADDRESS_ADMIN, 3000));
	}
	
	public void testFunction_Address_Contacts_TopContacts_Index() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);
		assertTrue("Ui Element not exist: TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Cloud user login failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click " + Constant.TXT_ADDRESS_CONTACT);
		clickText(Constant.TXT_ADDRESS_CONTACT, true);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 3000));
		
		printLog("step4=Verify the Index display");
		assertTrue("The UI Element not exist: " + Constant.TXT_TOP, waitForTextExists(Constant.TXT_TOP, 3000));
	}
	
	public void testFunction_Address_Contacts_Search_ByNumber() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);
		assertTrue("Ui Element not exist: TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Cloud user login failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click " + Constant.TXT_ADDRESS_CONTACT);
		clickText(Constant.TXT_ADDRESS_CONTACT, true);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 3000));
		
		printLog("step4=Click Search icon");
		clickResourceId(Constant.ID_TITLEBAR_SEARCH);
		assertTrue("Click search icon failed.", waitForResourceId(Constant.ID_SEARCH_INPUT, 3000));
		
		printLog("step4=Enter number to search contact");
		clickResourceId(Constant.ID_SEARCH_INPUT);
		enterTextInEditor(phoneNum, "android.widget.EditText", 0);
		assertTrue("Search by number failed.", waitForTextExists(Constant.TXT_MY_NAME, 3000));
	}
	
	public void testFunction_Address_Contacts_Search_ByNumber_ContactDetails() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);
		assertTrue("Ui Element not exist: TOOLBAR_MSG", waitForResourceId(Constant.ID_TOOLBAR_MSG, 3000));

		printLog("step2=Click " + Constant.TXT_TOOLBAR_ADDRESS);
		clickResourceId(Constant.ID_TOOLBAR_ADDRESS);
		assertTrue("Cloud user login failed.", waitForTextExists(Constant.TXT_ADDRESS_ORG, 3000));

		printLog("step3=Click " + Constant.TXT_ADDRESS_CONTACT);
		clickText(Constant.TXT_ADDRESS_CONTACT, true);
		assertTrue("Click Address failed.", waitForTextExists(Constant.TXT_ADDRESS_NEW_CONTACT, 3000));
		
		printLog("step4=Click Search icon");
		clickResourceId(Constant.ID_TITLEBAR_SEARCH);
		assertTrue("Click search icon failed.", waitForResourceId(Constant.ID_SEARCH_INPUT, 3000));
		
		printLog("step4=Enter number to search contact");
		clickResourceId(Constant.ID_SEARCH_INPUT);
		enterTextInEditor(phoneNum, "android.widget.EditText", 0);
		assertTrue("Search by number failed.", waitForTextExists(Constant.TXT_MY_NAME, 3000));
		
		printLog("step5=Click the search result to check contact details");
		clickText(Constant.TXT_MY_NAME, true);
		assertTrue("Enter name card failed.", waitForTextExists(Constant.TXT_NAME_CARD, 3000));
		
		printLog("step6=Verify the contact details");
		assertTrue("The position is empty", !getTextOnResourceId(Constant.ID_NAMECARD_POSITION, 3000).equals(""));
		assertTrue("The company is empty", !getTextOnResourceId(Constant.ID_NAMECARD_COMPANY, 3000).equals(""));
	}

	public void testFunction_Interest_GroupChat_Apply() throws UiObjectNotFoundException {
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
		assertTrue("Click group chat failed.", waitForTextExists(Constant.TXT_INTEREST_CHAT_GROUP, 3000));
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_CHAT_GROUP,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_INTEREST_CHAT_GROUP));

		String groupName = getTextOnResourceIdAndInstance(Constant.ID_INTEREST_CG_NAME, 2000, 0);
		printLog("step4=Click a group");
		clickText(groupName, true);
		assertTrue("Click a group failed.", waitForTextExists(Constant.TXT_GROUP_INFO, 2000));

		System.err.println("step5=Apply for group");
		if (waitForTextExists(Constant.TXT_GROUP_JOIN, 3000)) {
			clickText(Constant.TXT_GROUP_JOIN, true);
		}
		assertTrue("Click Apply for group failed.", waitForTextExists(Constant.TXT_GROUP_APPLYED, 3000));
	}

	public void testFunction_Interest_GroupChat_Search() throws UiObjectNotFoundException {
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
		assertTrue("Click group chat failed.", waitForTextExists(Constant.TXT_INTEREST_CHAT_GROUP, 3000));
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_CHAT_GROUP,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(Constant.TXT_INTEREST_CHAT_GROUP));

		printLog("step4=Click the search edittext");
		clickText(Constant.TXT_GROUP_FIND_ET, true);
		String groupName = "test";
//		ShellUtils.execCommand("input keyevent 20", true);
//		ShellUtils.execCommand("input keyevent 20", true);
		ShellUtils.execCommand("input text " + groupName, true);
		ShellUtils.execCommand("input keyevent 66", true);
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_CHAT_LIST));
		int count = list.getChildCount(new UiSelector().text(groupName));
		assertTrue("Search failed.", count > 0);
	}

	public void testFunction_Interest_RssAccount_RssTop() throws UiObjectNotFoundException {
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
		assertTrue("Click Rss failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_RSS_ACCOUNT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000).equals(Constant.TXT_INTEREST_RSS_ACCOUNT));
		assertTrue("The list has no rss accont.", waitForResourceId(Constant.ID_RSS_ACCOUNT_HEAD, 2000));

		printLog("step4=Click a account");
		String acctountName = getTextOnResourceIdAndInstance(Constant.ID_RSS_ACCOUNT_NAME, 2000, 0);
		clickText(acctountName, true);
		assertTrue("Click the account failed.", waitForTextExists(acctountName, 3000));
		assertTrue("Enter the account failed.",
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 2000).equals(acctountName));

		printLog("step5=Click " + Constant.TXT_RSS_TOP);
		if (waitForTextExists(Constant.txt_RSS_CANCEL, 3000)) {
			clickText(Constant.txt_RSS_CANCEL, true);
			assertTrue("Click " + Constant.txt_RSS_CANCEL + " failed.", waitForTextExists(Constant.TXT_RSS_TOP, 3000));
		} else {
			clickText(Constant.TXT_RSS_TOP, true);
			assertTrue("Click " + Constant.TXT_RSS_TOP + " failed.", waitForTextExists(Constant.txt_RSS_CANCEL, 3000));
		}
	}

	public void testFunction_Interest_RssAccount_NoAccount() throws UiObjectNotFoundException {
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
		assertTrue("Click RSS failed.", waitForTextExists(Constant.TXT_INTEREST_RSS_ACCOUNT, 3000));
		assertTrue("Title name is not: " + Constant.TXT_INTEREST_RSS_ACCOUNT,
				getTextOnResourceId(Constant.ID_TITLEBAR_TITLE, 5000).equals(Constant.TXT_INTEREST_RSS_ACCOUNT));

		printLog("step4=Verify no any rss account");
		if (waitForResourceId(Constant.ID_EMPTY, 3000)) {
			printLog("The list no rss accont.");
		} else {
			printLog("The list has rss accont.");
		}
	}

	public void testFunction_More_Person_BasicInfo_SetHeadIcon_ByPhoto() throws UiObjectNotFoundException {
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

		printLog("step4=Click " + Constant.TXT_MORE_PERSON_HEAD);
		clickText(Constant.TXT_MORE_PERSON_HEAD, true);
		assertTrue("Click set Head icon failed.", waitForTextExists(Constant.TXT_DIALOG_SETHEAD_TITLE, 3000));

		printLog("step5=Click " + Constant.TXT_DIALOG_SETHEAD_PHOTO);
		helper.setHeadIcon_Photo();
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_More_Person_BasicInfo_SetHeadIcon_ByPicture() throws UiObjectNotFoundException {
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

		printLog("step4=Click " + Constant.TXT_MORE_PERSON_HEAD);
		clickText(Constant.TXT_MORE_PERSON_HEAD, true);
		assertTrue("Click set Head icon failed.", waitForTextExists(Constant.TXT_DIALOG_SETHEAD_TITLE, 3000));

		printLog("step5=Click " + Constant.TXT_DIALOG_SETHEAD_PICTURE);
		helper.setHeadIcon_Picture();
		clickResourceId(Constant.ID_BACK);
	}

	public void testFunction_More_Person_BasicInfo_SetSex() throws UiObjectNotFoundException {
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

		printLog("step4=Click " + Constant.TXT_MORE_PERSON_SEX);
		clickText(Constant.TXT_MORE_PERSON_SEX, true);
		assertTrue("Click set sex  failed.", waitForTextExists(Constant.TXT_SET_SAVE, 3000));

		printLog("step5=Change sex and save");
		clickText(Constant.TXT_SET_SEX_FEMALE, true);
		clickText(Constant.TXT_SET_SAVE, true);
		assertTrue("Set sex failed", waitForTextExists(Constant.TXT_SET_SEX_FEMALE, 3000));

		printLog("step6=Change sex and saveagain");
		clickText(Constant.TXT_MORE_PERSON_SEX, true);
		assertTrue("Click set sex  failed.", waitForTextExists(Constant.TXT_SET_SAVE, 3000));
		clickText(Constant.TXT_SET_SEX_MALE, true);
		clickText(Constant.TXT_SET_SAVE, true);
		assertTrue("Set sex failed", waitForTextExists(Constant.TXT_SET_SEX_MALE, 3000));
	}

	public void testFunction_More_Person_BasicInfo_SetSign() throws UiObjectNotFoundException {
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

		printLog("step4=Click " + Constant.TXT_MORE_PERSON_SIGN);
		clickText(Constant.TXT_MORE_PERSON_SIGN, true);
		assertTrue("Click set sign  failed.", waitForTextExists(Constant.TXT_SET_SAVE, 3000));

		printLog("step5=Change sign and save");
		Date d = new Date();
		String longtime = String.valueOf(d.getTime());
		clearEditText(20);
		enterTextInEditor(longtime, "android.widget.EditText", 0);
		clickText(Constant.TXT_SET_SAVE, true);
		assertTrue("Set sign failed", waitForTextExists(longtime, 3000));
	}

	public void testFunction_More_Person_BasicInfo_SetIntroduction() throws UiObjectNotFoundException {
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
		Date d = new Date();
		String longtime = String.valueOf(d.getTime());
		clearEditText(20);
		enterTextInEditor(longtime, "android.widget.EditText", 0);
		clickText(Constant.TXT_SET_SAVE, true);
		assertTrue("Set sign failed", waitForTextExists(longtime, 3000));
	}

	public void testFunction_More_Person_BasicInfo_SetJob() throws UiObjectNotFoundException {
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

		printLog("step4=Click " + Constant.TXT_MORE_PERSON_JOB);
		clickText(Constant.TXT_MORE_PERSON_JOB, true);
		assertTrue("Click set job  failed.", waitForTextExists(Constant.TXT_SET_SAVE, 3000));

		while (waitForResourceId(Constant.ID_JOB_REMOVE, 2000)) {
			clickResourceId(Constant.ID_JOB_REMOVE);
		}
		printLog("step5=add work");
		clickText(Constant.TXT_WORK_ADD, true);
		Point place = getCoordinateByText(Constant.TXT_WORK_PLACE);
		clickPoint(place.x + 200, place.y);
		String s1 = "workplace";
		ShellUtils.execCommand("input text " + s1, true);
		assertTrue("Set workplace failed", waitForTextExists(s1, 3000));
		pressKey("back");

		Point position = getCoordinateByText(Constant.TXT_WORK_POSITION);
		clickPoint(position.x + 200, position.y);
		String s2 = "workposition";
		ShellUtils.execCommand("input text " + s2, true);
		assertTrue("Set work position failed", waitForTextExists(s2, 3000));
		pressKey("back");

		Point time = getCoordinateByText(Constant.TXT_WORK_TIME);
		clickPoint(time.x + 200, time.y);
		pressKey("back");

		sleep(2000);
		printLog("step6=add edu");
		clickText(Constant.TXT_EDU_ADD, true);
		while (!waitForTextExists(Constant.TXT_EDU_PLACE, 1000)) {
			clickText(Constant.TXT_EDU_ADD, true);
		}
		place = getCoordinateByText(Constant.TXT_EDU_PLACE);
		clickPoint(place.x + 200, place.y);
		String s3 = "school";
		ShellUtils.execCommand("input text " + s3, true);
		assertTrue("Set school failed", waitForTextExists(s3, 3000));
		pressKey("back");

		position = getCoordinateByText(Constant.TXT_EDU_POSITION);
		clickPoint(position.x + 200, position.y);
		String s4 = "class";
		ShellUtils.execCommand("input text " + s4, true);
		assertTrue("Set class failed", waitForTextExists(s4, 3000));
		pressKey("back");

		time = getCoordinateByText(Constant.TXT_EDU_TIME);
		clickPoint(time.x + 200, time.y);

		printLog("step7=Save edit and check");
		clickText(Constant.TXT_SET_SAVE, true);
		while (waitForTextExists(Constant.TXT_SET_SAVE, 1000)) {
			clickText(Constant.TXT_SET_SAVE, true);
		}
		assertTrue("Set workplace failed", waitForTextContainsExists(s1, 3000));
		assertTrue("Set work position failed", waitForTextContainsExists(s2, 3000));
		assertTrue("Set school failed", waitForTextContainsExists(s3, 3000));
		assertTrue("Set class failed", waitForTextContainsExists(s4, 3000));
	}

	public void testFunction_More_Person_BasicInfo_NotEdit() throws UiObjectNotFoundException {
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

	public void testFunction_More_Person_QRCode_Relay() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("Case-Setup--createPrivateChatBySelectContactInContacts");
		String chatName = helper.createPrivateChatBySelectContactInContacts(phoneNum, password);

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

		printLog("step5=Click the More icon");
		clickResourceId(Constant.ID_MORE_QRCODE_SHARE);
		assertTrue("UI Element not exist: " + Constant.TXT_SELECT_DIALOG_LIST_RELAY,
				waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_RELAY, 2000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_RELAY);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_RELAY, true);
		assertTrue("Click the relay to enter option failed.", waitForTextExists(Constant.TXT_RELAY_TITLE, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_RECENT_CHAT,
				waitForTextContainsExists(Constant.TXT_RELAY_RECENT_CHAT, 2000));

		printLog("step6=Click one current chat");
		clickTextContains(chatName, true);
		assertTrue("Clickt the current chat failed.", waitForTextExists(Constant.TXT_CONFIRM, 3000));

		printLog("step7=Click the confirm");
		clickText(Constant.TXT_CONFIRM, true);

		printLog("step8=Back to Msg list and check send successful");
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		clickText(chatName, true);
		assertTrue("Enter Private chat failed.", waitForResourceId(Constant.ID_CHAT_MENU_TOOL, 3000));
		assertTrue("Save QRcode failed.", waitForResourceId(Constant.ID_CHAT_MSG_MULTI_FILE_COVER, 8000));
	}

	public void testFunction_More_Person_QRCode_Save() throws UiObjectNotFoundException {
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

		printLog("step5=Click the More icon");
		clickResourceId(Constant.ID_MORE_QRCODE_SHARE);
		assertTrue("UI Element not exist: " + Constant.TXT_SET_SAVE, waitForTextExists(Constant.TXT_SET_SAVE, 2000));

		printLog("step5=Select " + Constant.TXT_SET_SAVE);
		clickText(Constant.TXT_SET_SAVE, true);
		String iconArr[] = getConfigProperty("qrcode").split(",");

		int x1 = Integer.parseInt(iconArr[0]);
		int y1 = Integer.parseInt(iconArr[1]);
		int x2 = Integer.parseInt(iconArr[2]);
		int y2 = Integer.parseInt(iconArr[3]);
		double diff = getScreenDiffWithReference(Constant.RES_FILE_PATH + "qrcode.jpg",
				Constant.RES_APK_PATH + "camera/qrcode.jpg", x1, y1, x2, y2);
		printLog("diff=" + diff);
		assertTrue("The save qrcode not match expect", diff < 0.1);
	}

	public void testFunction_More_MyDocument_DocumentUpload() throws UiObjectNotFoundException, IOException {
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

		printLog("step4=click Upload");
		String file = helper.uploadDocument();
		assertTrue("Upload the document faile.d", waitForTextExists(file, 5000));
	}

	public void testFunction_More_MyDocument_DocumentDetail() throws UiObjectNotFoundException {
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

		printLog("step4=Select one document");
		String file = getObjectOnResourceIdAndInstance(Constant.ID_MORE_MY_DOCUMENT_NAME, 0).getText();
		clickText(file, true);
		assertTrue("UI Element not exist: " + Constant.TXT_FILE_REVIEW,
				waitForTextExists(Constant.TXT_FILE_REVIEW, 3000));
		assertTrue("UI Element not exist: " + Constant.TXT_OPEN, waitForTextExists(Constant.TXT_OPEN, 2000));

		printLog("step5=Click share icon");
		clickResourceId(Constant.ID_CHAT_SETTINGS);
		assertTrue("UI Element not exist: " + Constant.TXT_SHARE, waitForTextExists(Constant.TXT_SHARE, 3000));
		assertTrue("UI Element not exist: " + Constant.TXT_SHARE_TO_GROUP,
				waitForTextExists(Constant.TXT_SHARE_TO_GROUP, 2000));
		assertTrue("UI Element not exist: " + Constant.TXT_SAVE_TO_MY_DOCUMENT,
				waitForTextExists(Constant.TXT_SAVE_TO_MY_DOCUMENT, 2000));

		printLog("step6=Select " + Constant.TXT_SHARE_TO_GROUP);
		clickText(Constant.TXT_SHARE_TO_GROUP, true);
		assertTrue("Click the relay to enter option failed.", waitForTextExists(Constant.TXT_RELAY_TITLE, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_RECENT_CHAT,
				waitForTextContainsExists(Constant.TXT_RELAY_RECENT_CHAT, 2000));

		printLog("step7=Click one current chat");
		clickTextContains(Constant.TXT_MY_NAME, true);
		assertTrue("Clickt the current chat failed.", waitForTextExists(Constant.TXT_CONFIRM, 3000));

		printLog("step8=Click the confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(3000);

		clickResourceId(Constant.ID_BACK);
		clickResourceId(Constant.ID_BACK);

		printLog("step9=Click " + Constant.TXT_TOOLBAR_MSG);
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		System.err.println("step10=click the dialog to check share");
		clickText(Constant.TXT_MY_NAME, true);
		assertTrue("Relay the msg failed.", waitForTextContainsExists(file, 3000));
	}

	public void testFunction_More_MyDocument_DocumentDelete() throws UiObjectNotFoundException {
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

		printLog("step4=Long click one document");
		String file = getObjectOnResourceIdAndInstance(Constant.ID_MORE_MY_DOCUMENT_NAME, 0).getText();
		longClickTextBySwipe(file);
		assertTrue("Long click the document failed.", waitForTextExists(Constant.TXT_DELETE, 3000));

		System.err.println("Step5=Delete the document and verify");
		clickText(Constant.TXT_DELETE, true);
		assertTrue("Delete the document failed.", waitForTextGone(file, 5000));
	}

	public void testFunction_More_MyFavorite_Favorite() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		String longtime = helper.sendMsg_Text();
		longClickTextBySwipe(longtime);
		assertTrue("Long click the msg failed.", waitForTextExists(Constant.TXT_SELECT_DIALOG_LIST_FAVORITE, 3000));

		printLog("step5=Select " + Constant.TXT_SELECT_DIALOG_LIST_FAVORITE);
		clickText(Constant.TXT_SELECT_DIALOG_LIST_FAVORITE, true);

		printLog("step6=Click back");
		clickResourceId(Constant.ID_BACK);
		assertTrue("Back to Msg failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		printLog("step7=Click " + Constant.TXT_TOOLBAR_MORE);
		clickText(Constant.TXT_TOOLBAR_MORE, true);
		waitForWindowUpdate(3000);
		assertTrue("Click the More failed.", waitForTextExists(Constant.TXT_MORE_MY_FAVORITE, 3000));

		printLog("step8=Click " + Constant.TXT_MORE_MY_FAVORITE);
		clickText(Constant.TXT_MORE_MY_FAVORITE, true);
		waitForWindowUpdate(3000);
		assertTrue("Msg favorite failed.", waitForTextExists(longtime, 3000));
	}

	public void testFunction_More_MyFavorite_ExportToDocument() throws UiObjectNotFoundException {
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

		System.err.println("step4=Click " + Constant.TXT_EXPORT);
		clickText(Constant.TXT_EXPORT, true);
		assertTrue("Click the export failed.", waitForResourceId(Constant.ID_CHECK, 3000));

		printLog("step5=Select some msg");
		UiObject check = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		check.clickAndWaitForNewWindow(500);
		assertTrue("Select msg failed.", check.isChecked());

		printLog("step6=Click " + Constant.TXT_CONFIRM);
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Enter edit file name", waitForTextExists(Constant.TXT_EDIT_FILE_NAME, 3000));

		printLog("step7=Enter file name and save");
		clickText(Constant.TXT_INPUT_FILE_NAME, true);
		String fileName = "testsavefavorite";
		enterTextInEditor(fileName, "android.widget.EditText", 0);
		clickText(Constant.TXT_SAVE, true);
		assertTrue("Enter to My Document failed.", waitForTextExists(Constant.TXT_MORE_MY_DOCUMENT, 5000));
		assertTrue("Export favorite to document failed.", waitForTextExists(fileName, 5000));
	}

	public void testFunction_More_MyFavorite_LongClick_CancelFavorite() throws UiObjectNotFoundException {
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

		printLog("step4=Long click the msg");
		sleep(2000);
		String msg = getObjectOnResourceIdAndInstance(Constant.ID_CHAT_MSG_VIEW_INFO, 0).getText();
		longClickTextBySwipe(msg);
		assertTrue("The UI Element not exist: " + Constant.TXT_COPY, waitForTextExists(Constant.TXT_COPY, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY, waitForTextExists(Constant.TXT_RELAY, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_CANCEL_FAVORITE,
				waitForTextExists(Constant.TXT_CANCEL_FAVORITE, 2000));

		printLog("step5=Click " + Constant.TXT_CANCEL_FAVORITE);
		clickText(Constant.TXT_CANCEL_FAVORITE, true);
		assertTrue("Cancel favorite msg failed.", waitForTextGone(msg, 3000));
	}

	public void testFunction_More_Settings_MsgNotify_Open() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!button.isSelected()) {
			button.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", button.isSelected());
	}

	public void testFunction_More_Settings_MsgNotify_Close() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to close msg notify");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (button.isSelected()) {
			button.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Close msg notify failed.", !button.isSelected());
	}

	public void testFunction_More_Settings_MsgDisplay_Open() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step4=Click the button to open msg display");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 1);
		if (!button.isSelected()) {
			button.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg display failed.", button.isSelected());
	}

	public void testFunction_More_Settings_MsgDisplay_Close() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step5=Click the button to close msg display");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 1);
		if (button.isSelected()) {
			button.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Close msg display failed.", !button.isSelected());
	}

	public void testFunction_More_Settings_SoundNotify_OpenOrClose() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step5=Click the button to open or close sound notify");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 2);
		if (button.isSelected()) {
			button.clickAndWaitForNewWindow(1000);
			assertTrue("Close sound notify failed.", !button.isSelected());
		} else {
			button.clickAndWaitForNewWindow(1000);
			assertTrue("Open sound notify failed.", button.isSelected());
		}
	}

	public void testFunction_More_Settings_HandSet_OpenOrClose() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step5=Click the button to open or close Handset");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 3);
		if (button.isSelected()) {
			button.clickAndWaitForNewWindow(1000);
			assertTrue("Close Handset failed.", !button.isSelected());
		} else {
			button.clickAndWaitForNewWindow(1000);
			assertTrue("Open Handset failed.", button.isSelected());
		}
	}

	public void testFunction_More_Settings_ShakeNotify_OpenOrClose() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step5=Click the button to open or close shake notify");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 4);
		if (button.isSelected()) {
			button.clickAndWaitForNewWindow(1000);
			assertTrue("Close shake notify failed.", !button.isSelected());
		} else {
			button.clickAndWaitForNewWindow(1000);
			assertTrue("Open shake notify failed.", button.isSelected());
		}
	}

	public void testFunction_More_Settings_CallNotify_OpenOrClose() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step5=Click the button to open or close call notify");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 5);
		if (button.isSelected()) {
			button.clickAndWaitForNewWindow(1000);
			assertTrue("Close call notify failed.", !button.isSelected());
		} else {
			button.clickAndWaitForNewWindow(1000);
			assertTrue("Open call notify failed.", button.isSelected());
		}
	}

	public void testFunction_More_Settings_MsgAccept_OpenOrClose() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step5=Click the button to open or close msg accept");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 6);
		if (button.isSelected()) {
			button.clickAndWaitForNewWindow(1000);
			assertTrue("Close msg accept failed.", !button.isSelected());
		} else {
			button.clickAndWaitForNewWindow(1000);
			assertTrue("Open msg accept failed.", button.isSelected());
		}
	}

	public void testFunction_More_Settings_ScreenLock_Default() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step5=Check the screen lock button");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 8);
		assertTrue("screen lock button is selected.", !button.isSelected());
	}

	public void testFunction_More_Settings_ChangePassword_Enter() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step5=Check the change password button");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 9);
		button.clickAndWaitForNewWindow(2000);
		assertTrue("Enter change password page failed.", waitForTextExists(Constant.TXT_PASSWORD_TITLE, 3000));
	}

	public void testFunction_More_Settings_ChangePassword_Element() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step5=Check the change password button");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 9);
		button.clickAndWaitForNewWindow(2000);
		assertTrue("Enter change password page failed.", waitForTextExists(Constant.TXT_PASSWORD_TITLE, 3000));

		printLog("step6=Verify the UI Element");
		assertTrue("The UI Element not exist: " + Constant.TXT_PASSWORD_OLD,
				waitForTextExists(Constant.TXT_PASSWORD_OLD, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_PASSWORD_DISPLAY,
				waitForTextExists(Constant.TXT_PASSWORD_DISPLAY, 2000));
		assertTrue("The UI Element not exist: " + Constant.ID_PASSWORD_OLD,
				waitForResourceId(Constant.ID_PASSWORD_OLD, 2000));
		assertTrue("The UI Element not exist: " + Constant.ID_PASSWORD_NEW,
				waitForResourceId(Constant.ID_PASSWORD_NEW, 2000));
		assertTrue("The UI Element not exist: " + Constant.ID_PASSWORD_RENEW,
				waitForResourceId(Constant.ID_PASSWORD_RENEW, 2000));
	}

	public void testFunction_More_Settings_ChangePassword_WrongOldPassword() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step5=Check the change password button");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 9);
		button.clickAndWaitForNewWindow(2000);
		assertTrue("Enter change password page failed.", waitForTextExists(Constant.TXT_PASSWORD_TITLE, 3000));

		printLog("step6=Change password and enter wrong old password");
		String wrong = "111111";
		String newPwd = "111aaa";
		clickResourceId(Constant.ID_PASSWORD_OLD);
		enterTextInEditor(wrong, "android.widget.EditText", 0);
		clickResourceId(Constant.ID_PASSWORD_NEW);
		enterTextInEditor(newPwd, "android.widget.EditText", 1);
		clickResourceId(Constant.ID_PASSWORD_RENEW);
		enterTextInEditor(newPwd, "android.widget.EditText", 2);
		clickText(Constant.TXT_CONFIRM, true);
		assertTrue("Enter wrong old password failed.", waitForTextExists(Constant.TXT_PASSWORD_TITLE, 5000));
	}

	public void testFunction_More_Settings_ChangePassword_PasswordDisplay() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		printLog("step5=Click the change password button");
		UiObject button = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 9);
		button.clickAndWaitForNewWindow(2000);
		assertTrue("Enter change password page failed.", waitForTextExists(Constant.TXT_PASSWORD_TITLE, 3000));

		printLog("step6=Input password and click display pwd");
		String wrong = "111111";
		clickResourceId(Constant.ID_PASSWORD_OLD);
		enterTextInEditor(wrong, "android.widget.EditText", 0);
		clickText(Constant.TXT_PASSWORD_DISPLAY, true);
		assertTrue("The password not display", waitForTextExists(wrong, 5000));
	}

	public void testFunction_More_Settings_ChangeNumber_Enter() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		dragDirection("up", 35);
		
		printLog("step5=Click the change number button");
		clickText(Constant.TXT_MORE_SETTINGS_NUMBER, true);
		assertTrue("Enter change number page failed.", waitForTextExists(Constant.TXT_MORE_SETTINGS_NUMBER, 3000));

		printLog("step6=Verify the Change number page Element");
		assertTrue("The UI Element not exist: " + Constant.TXT_NEXT, waitForTextExists(Constant.TXT_NEXT, 5000));
		assertTrue("The UI Element not exist: " + Constant.TXT_CHANGE_NUMBER_INTRO,
				waitForTextExists(Constant.TXT_CHANGE_NUMBER_INTRO, 5000));
		assertTrue("The UI Element not exist: Back icon", waitForResourceId(Constant.ID_BACK, 2000));
	}
	
	public void testFunction_More_Settings_LogOut() throws UiObjectNotFoundException {
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

		printLog("step4=Click the button to open msg notify");
		UiObject btn = getObjectOnResourceIdAndInstance(Constant.ID_MORE_SETTINGS_ITEM_PERFERENCE, 0);
		if (!btn.isSelected()) {
			btn.clickAndWaitForNewWindow(1000);
		}
		assertTrue("Open msg notify failed.", btn.isSelected());

		dragDirection("up", 35);
		assertTrue("click TXT_SETTINGS failed.", waitForTextExists(Constant.TXT_LOGOUT, 5 * 1000));

		clickText(Constant.TXT_LOGOUT, true);
		assertTrue("click TXT_LOGOUT failed.", (getTextOnResourceId(Constant.ID_BUTTON1, 3000)).equals(Constant.TXT_LOGOUT));

		clickText(Constant.TXT_LOGOUT, true);
		assertTrue("click second TXT_LOGOUT failed.",
				waitForResourceId(Constant.ID_CONFIRM_PHONE_BUTTON, 3 * 1000));
	}
	
	public void testFunction_More_Help_Share_NewChat() throws UiObjectNotFoundException {
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

		printLog("step4=Click the Share icon");
		clickResourceId(Constant.ID_MORE_HELP_SHARE);
		assertTrue("Click the relay to enter option failed.", waitForTextExists(Constant.TXT_RELAY_TITLE, 3000));

		printLog("step5=Click " + Constant.TXT_RELAY_NEW_CHAT);
		clickText(Constant.TXT_RELAY_NEW_CHAT, true);
		assertTrue("Click the relay new chat failed.", waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 3000));

		UiObject contact1Check = getObjectOnResourceIdAndInstance(Constant.ID_MANAGE_MEMBER_NAME, 0);
		String name = contact1Check.getText();

		if (contact1Check.waitForExists(2000)) {

			printLog("step6=Select contacts");
			contact1Check.clickAndWaitForNewWindow();

			printLog("step7=Click confirm button");
			clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
			waitForWindowUpdate(3000);
		} else {
			assertTrue("This account has no contact", false);
		}

		printLog("step8=Back to msg list to check result of share");
		pressKey("back");
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		clickText(name, true);
		assertTrue("The ui element not exist: " + Constant.TXT_MORE_HELP_TITLE,
				waitForTextExists(Constant.TXT_MORE_HELP_TITLE, 3000));
	}

	public void testFunction_More_Help_Share_RecentChat() throws UiObjectNotFoundException {
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

		printLog("step4=Click the Share icon");
		clickResourceId(Constant.ID_MORE_HELP_SHARE);
		assertTrue("Click the relay to enter option failed.", waitForTextExists(Constant.TXT_RELAY_TITLE, 3000));
		assertTrue("The UI Element not exist: " + Constant.TXT_RELAY_RECENT_CHAT,
				waitForTextContainsExists(Constant.TXT_RELAY_RECENT_CHAT, 2000));

		printLog("step5=Click one current chat");
		clickTextContains(Constant.TXT_MY_NAME, true);
		assertTrue("Clickt the current chat failed.", waitForTextExists(Constant.TXT_CONFIRM, 3000));

		printLog("step6=Click the confirm");
		clickText(Constant.TXT_CONFIRM, true);
		waitForWindowUpdate(3000);

		printLog("step7=Back to msg list to check result of share");
		pressKey("back");
		clickResourceId(Constant.ID_TOOLBAR_MSG);
		assertTrue("Msg list not exist", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));

		clickText(Constant.TXT_MY_NAME, true);
		assertTrue("The ui element not exist: " + Constant.TXT_MORE_HELP_TITLE,
				waitForTextExists(Constant.TXT_MORE_HELP_TITLE, 3000));
	}

	public void testFunction_LaunchApp_LoginAgain() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		helper.clearAppUserData();
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);

		printLog("step2=Back to home shut down app");
		helper.backToHome();
		helper.shutDownLanxinApp();

		printLog("step3=Launch app again");
		helper.startLanxinApp(phoneNum, password);
	}

	public void testFunction_LaunchApp_ClearUserData() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		helper.clearAppUserData();
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		printLog("step1=Start Lanxin App");
		helper.startLanxinApp(phoneNum, password);
	}

	public void testFunction_LaunchApp_InvaildNumber() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		helper.clearAppUserData();
		String phoneNum = "13100000";

		printLog("phoneNum=" + phoneNum);

		printLog("step1=Start Lanxin App");
		String pkg = getConfigProperty("pkg");
		String cls = getConfigProperty("cls");

		makeSureNetConnected();

		// launch app
		launchApp(pkg, cls);

		waitForWindowUpdate(6 * 1000);
		if (waitForTextExists(Constant.TXT_NOTIFY_UPDATE_LATER, 2000)) {
			clickText(Constant.TXT_NOTIFY_UPDATE_LATER, true);
			System.err.println("****Lanxin Update Notify appear!!!****");
		}

		if (waitForTextExists(Constant.TXT_I_KNOW, 2000)) {
			clickText(Constant.TXT_I_KNOW, true);
		}

		// 导览UI
		while (getTextOnResourceId(Constant.ID_NEXT, 2 * 1000).equals(Constant.TXT_NEXT)) {
			clickText(Constant.TXT_NEXT, true);
		}

		if (waitForTextExists(Constant.TXT_LOGIN, 2 * 1000)) {
			clickText(Constant.TXT_LOGIN, true);
		}

		printLog("step2=Input number");
		enterTextInEditor(phoneNum, "android.widget.EditText", 0);
		clickResourceId(Constant.ID_CONFIRM_PHONE_BUTTON);
		assertTrue("The number not invalid.", waitForTextExists(Constant.TXT_INVALID_TITLE, 5 * 1000));
	}

	public void testFunction_LaunchApp_MultiOrg() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		helper.clearAppUserData();
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		String pkg = getConfigProperty("pkg");
		String cls = getConfigProperty("cls");
		makeSureNetConnected();

		printLog("step1=launch app");
		launchApp(pkg, cls);

		waitForWindowUpdate(6 * 1000);

		while (waitForTextExists(Constant.TXT_NEXT, 2000)) {
			clickText(Constant.TXT_NEXT, true);
		}

		if (waitForTextExists(Constant.TXT_LOGIN, 2 * 1000)) {
			clickText(Constant.TXT_LOGIN, true);
		}

		printLog("step2=Input num ");
		enterTextInEditor(phoneNum, "android.widget.EditText", 0);
		clickResourceId(Constant.ID_CONFIRM_PHONE_BUTTON);

		printLog("step3=chose platform");
		waitForWindowUpdate(5000);
		if (waitForTextExists(Constant.TXT_PLATFORM_NAME1, 5000)) {
			clickText(Constant.TXT_PLATFORM_NAME1, true);
		} else if (waitForTextExists(Constant.TXT_PLATFORM_NAME3, 1000)) {
			clickText(Constant.TXT_PLATFORM_NAME3, true);
		}

		waitForWindowUpdate(5000);
		assertTrue("Click next button cann't go password login, pls check the network",
				waitForResourceId(Constant.ID_EDITTEXT_PW, 5 * 1000));

		printLog("step4=input password");
		clickResourceId(Constant.ID_EDITTEXT_PW);
		waitForWindowUpdate(2000);
		clickResourceId(Constant.ID_EDITTEXT_PW);
		UiObject pw = new UiObject(new UiSelector().resourceId(Constant.ID_EDITTEXT_PW));
		pw.setText(password);

		clickResourceId(Constant.ID_BUTTON_OK);
		waitForWindowUpdate(3000);

		if (waitForTextExists(Constant.TXT_SECURITY_ALERT_ALERT_TITLE, 5000)) {
			clickText(Constant.TXT_SECURITY_ALERT_ACCEPT, true);
		}

		if (waitForTextExists(Constant.TXT_I_KNOW, 3000)) {
			clickText(Constant.TXT_I_KNOW, true);
		}

		waitForWindowUpdate(3000);
		assertTrue("Click ok button cann't login, pls check the network",
				waitForResourceId(Constant.ID_MAIN_VIEW_TOOLBAR, 5 * 1000));
	}

	public void testFunction_LaunchApp_SingleOrg() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		helper.clearAppUserData();
		String phoneNum = getConfigProperty("single_phoneNum");
		String password = getConfigProperty("single_password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		String pkg = getConfigProperty("pkg");
		String cls = getConfigProperty("cls");
		makeSureNetConnected();

		printLog("step1=launch app");
		launchApp(pkg, cls);

		waitForWindowUpdate(6 * 1000);

		while (getTextOnResourceId(Constant.ID_NEXT, 2 * 1000).equals(Constant.TXT_NEXT)) {
			clickText(Constant.TXT_NEXT, true);
		}

		if (waitForTextExists(Constant.TXT_LOGIN, 2 * 1000)) {
			clickText(Constant.TXT_LOGIN, true);
		}

		printLog("step2=Input num ");
		enterTextInEditor(phoneNum, "android.widget.EditText", 0);
		clickResourceId(Constant.ID_CONFIRM_PHONE_BUTTON);

		waitForWindowUpdate(5000);
		assertTrue("Click next button cann't go password login, pls check the network",
				waitForResourceId(Constant.ID_EDITTEXT_PW, 5 * 1000));

		printLog("step3=input password");
		clickResourceId(Constant.ID_EDITTEXT_PW);
		waitForWindowUpdate(2000);
		clickResourceId(Constant.ID_EDITTEXT_PW);
		UiObject pw = new UiObject(new UiSelector().resourceId(Constant.ID_EDITTEXT_PW));
		pw.setText(password);

		clickResourceId(Constant.ID_BUTTON_OK);
		waitForWindowUpdate(3000);

		LogOut = true;

		if (waitForTextExists(Constant.TXT_SECURITY_ALERT_ALERT_TITLE, 5000)) {
			clickText(Constant.TXT_SECURITY_ALERT_ACCEPT, true);
		}

		if (waitForTextExists(Constant.TXT_I_KNOW, 3000)) {
			clickText(Constant.TXT_I_KNOW, true);
		}

		waitForWindowUpdate(3000);
		assertTrue("Click ok button cann't login, pls check the network",
				waitForResourceId(Constant.ID_MAIN_VIEW_TOOLBAR, 5 * 1000));
	}

	public void testFunction_LaunchApp_NoNet() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		helper.clearAppUserData();
		String phoneNum = getConfigProperty("single_phoneNum");
		String password = getConfigProperty("single_password");

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		String pkg = getConfigProperty("pkg");
		String cls = getConfigProperty("cls");
		turnOffNetWorkByCMD();
		TurnOnNetWork = true;

		printLog("step1=launch app");
		launchApp(pkg, cls);

		waitForWindowUpdate(6 * 1000);

		while (getTextOnResourceId(Constant.ID_NEXT, 2 * 1000).equals(Constant.TXT_NEXT)) {
			clickText(Constant.TXT_NEXT, true);
		}

		if (waitForTextExists(Constant.TXT_LOGIN, 2 * 1000)) {
			clickText(Constant.TXT_LOGIN, true);
		}

		printLog("step2=Input num ");
		enterTextInEditor(phoneNum, "android.widget.EditText", 0);
		clickResourceId(Constant.ID_CONFIRM_PHONE_BUTTON);

		waitForWindowUpdate(5000);
		assertTrue("The network avaiable", !waitForResourceId(Constant.ID_EDITTEXT_PW, 5 * 1000));
	}

	public void testFunction_LaunchApp_NetException() throws UiObjectNotFoundException {
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

	public void testFunction_LaunchApp_WrongPassword() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		helper.clearAppUserData();
		String phoneNum = getConfigProperty("phoneNum");
		String password = "123456";

		printLog("phoneNum=" + phoneNum);
		printLog("password=" + password);

		String pkg = getConfigProperty("pkg");
		String cls = getConfigProperty("cls");
		makeSureNetConnected();

		printLog("step1=launch app");
		launchApp(pkg, cls);

		waitForWindowUpdate(6 * 1000);

		while (getTextOnResourceId(Constant.ID_NEXT, 5 * 1000).equals(Constant.TXT_NEXT)) {
			clickText(Constant.TXT_NEXT, true);
		}

		if (waitForTextExists(Constant.TXT_LOGIN, 2 * 1000)) {
			clickText(Constant.TXT_LOGIN, true);
		}

		printLog("step2=Input num ");
		enterTextInEditor(phoneNum, "android.widget.EditText", 0);
		clickResourceId(Constant.ID_CONFIRM_PHONE_BUTTON);

		printLog("step3=chose platform");
		waitForWindowUpdate(5000);
		if (waitForTextExists(Constant.TXT_PLATFORM_NAME1, 5000)) {
			clickText(Constant.TXT_PLATFORM_NAME1, true);
		} else if (waitForTextExists(Constant.TXT_PLATFORM_NAME3, 1000)) {
			clickText(Constant.TXT_PLATFORM_NAME3, true);
		}

		waitForWindowUpdate(5000);
		assertTrue("Click next button cann't go password login, pls check the network",
				waitForResourceId(Constant.ID_EDITTEXT_PW, 5 * 1000));

		printLog("step4=input password");
		clickResourceId(Constant.ID_EDITTEXT_PW);
		waitForWindowUpdate(2000);
		clickResourceId(Constant.ID_EDITTEXT_PW);
		UiObject pw = new UiObject(new UiSelector().resourceId(Constant.ID_EDITTEXT_PW));
		pw.setText(password);

		clickResourceId(Constant.ID_BUTTON_OK);
		waitForWindowUpdate(3000);

		if (waitForTextExists(Constant.TXT_SECURITY_ALERT_ALERT_TITLE, 5000)) {
			clickText(Constant.TXT_SECURITY_ALERT_ACCEPT, true);
		}

		if (waitForTextExists(Constant.TXT_I_KNOW, 3000)) {
			clickText(Constant.TXT_I_KNOW, true);
		}

		waitForWindowUpdate(3000);
		assertTrue("The password is right.", !waitForResourceId(Constant.ID_MAIN_VIEW_TOOLBAR, 5 * 1000));
	}
	
	public void testFunction_TitleBar_Notice_Create_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
	}
	
	public void testFunction_TitleBar_Notice_Create_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Verify the UI Elment");
		helper.verifyCreateNotificationUiElement();
	}
	
	public void testFunction_TitleBar_Notice_Create_ClickNoticeContent() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the notice content");
		clickResourceId(Constant.ID_NOTICE_CONTENT);
		assertTrue("The add voice not disappear", !waitForResourceId(Constant.ID_ADD_VOICE, 2000));
	}

	public void testFunction_TitleBar_Notice_Create_AddReceiver_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		
		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
	}

	public void testFunction_TitleBar_Notice_Create_AddReceiver_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		
		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Verify selection UI Element");
		helper.verifySelectionUiElement();
	}

	public void testFunction_TitleBar_Notice_Create_AddReceiver_SelectMember_Display() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		
		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));
			assertTrue("The confirme button not expect: " + Constant.TXT_SELECT_CONFIRM1,
					waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM1, 2000));
		} else {
			assertTrue("No any contacts", false);
		}
	}

	public void testFunction_TitleBar_Notice_Create_AddReceiver_CancelSelectMember1() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		
		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Select a member");
		UiObject checkIcon = getObjectOnResourceIdAndInstance(Constant.ID_CHECK, 0);
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_MEMBER_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_MEMBER_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the member");
			checkIcon.click();
			assertTrue("Un-select a member failed.", !checkIcon.isChecked());
			assertTrue("Select Name display", name.waitUntilGone(2000));
		}
	}
	
	public void testFunction_TitleBar_Notice_Create_AddReceiver_CancelSelectMember2() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		
		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_MEMBER_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_MEMBER_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());

			printLog("steps7=Un-select the member");
			name.click();
			assertTrue("Un-select a member failed.", !checkIcon.isChecked());
			assertTrue("Select name display", name.waitUntilGone(2000));

		} else {
			assertTrue("No any contacts", false);
		}
	}
	
	public void testFunction_TitleBar_Notice_Create_AddReceiver_SelectMember() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		
		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));
		} else {
			assertTrue("No any contacts", false);
		}
	}
	
	public void testFunction_TitleBar_Notice_Create_AddReceiver_MemberSelected() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		
		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));
		} else {
			assertTrue("No any contacts", false);
		}
		
		printLog("step7=Click the confirm button");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Select one member failed.", waitForTextExists(Constant.TXT_SELECT_ONE, 3000));
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		waitForWindowUpdate(2000);
		assertTrue("The Selected can be delete", !checkIcon.isEnabled());
	}
	
	public void testFunction_TitleBar_Notice_Create_AddReceiver_NoMemberSelected() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		
		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Select a member");
		UiObject btn = getObjectOnResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("The confirm button can be click.", !btn.isEnabled());
	}
	
	public void testFunction_TitleBar_Notice_Create_AddReceiver_MemberSelected_Display()
			throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));

		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));
		} else {
			assertTrue("No any contacts", false);
		}

		printLog("step7=Click the confirm button");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Select one member failed.", waitForTextExists(Constant.TXT_SELECT_ONE, 3000));

		printLog("step8=Verify the selected member display");
		assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
		assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddReceiver_MemberSelected_Delete()
			throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));

		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));
		} else {
			assertTrue("No any contacts", false);
		}

		printLog("step7=Click the confirm button");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Select one member failed.", waitForTextExists(Constant.TXT_SELECT_ONE, 3000));

		printLog("step8=Click the member icon to delete");
		clickResourceId(Constant.ID_SELECT_MEMBER_NAME);
		assertTrue("Delete one member failed.", waitForTextExists(Constant.TXT_SELECT_NO, 3000));
		assertTrue("Select List exist", !waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
		assertTrue("Select Name exist", !waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddReceiver_CancelSelectMember_Display() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");
		
		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			UiObject list = getObjectOnResourceId(Constant.ID_SELECT_MEMBER_LIST);
			UiObject name = list.getChild(new UiSelector().resourceId(Constant.ID_SELECT_MEMBER_NAME).instance(0));
			assertTrue("Select Name not display", name.exists());
			assertTrue("The UI Element not exist: " + Constant.TXT_SELECT_CONFIRM1,
					waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM1, 2000));

			printLog("steps7=Un-select the member");
			name.click();
			assertTrue("The UI Element not exist: " + Constant.TXT_SELECT_CONFIRM0,
					waitForTextContainsExists(Constant.TXT_SELECT_CONFIRM0, 2000));

		} else {
			assertTrue("No any contacts", false);
		}
	}
	
	public void testFunction_TitleBar_Notice_Create_ClickNoticeContent_Display() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the notice content");
		clickResourceId(Constant.ID_NOTICE_CONTENT);
		assertTrue("The add voice not disappear", !waitForResourceId(Constant.ID_ADD_VOICE, 2000));
		
		printLog("step6=Verify the UI Element");
		assertTrue("The UI Element not exist: " + Constant.TXT_SEND, waitForTextExists(Constant.TXT_SEND, 2000));
		assertTrue("The UI Element not exist: " + Constant.TXT_CANCEL, waitForTextExists(Constant.TXT_CANCEL, 2000));
		assertTrue("The UI Element not exist: Add emotion", waitForResourceId(Constant.ID_ADD_EMOTION, 2000));
		assertTrue("The UI Element not exist: Add image ", waitForResourceId(Constant.ID_ADD_IMAGE, 2000));
		assertTrue("The UI Element not exist: Add image by camera ",
				waitForResourceId(Constant.ID_ADD_IMAGE_CAMERA, 2000));
		assertTrue("The UI Element not exist: Add file ", waitForResourceId(Constant.ID_ADD_FILE, 2000));
		assertTrue("The UI Element not exist: Add location ", waitForResourceId(Constant.ID_ADD_LOCATION, 2000));
		assertTrue("The UI Element not exist: Add title ", waitForResourceId(Constant.ID_ADD_TITLE, 2000));
		assertTrue("The UI Element not exist: Add question ", waitForResourceId(Constant.ID_ADD_QUESTION, 2000));
		assertTrue("The UI Element not exist: Notice content ", waitForResourceId(Constant.ID_NOTICE_CONTENT, 2000));
		assertTrue("The UI Element not exist: Add receiver ", waitForResourceId(Constant.ID_ADD_RECEIVER, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_ClickNoticeContent_InputContent() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the notice content");
		clickResourceId(Constant.ID_NOTICE_CONTENT);
		assertTrue("The add voice not disappear", !waitForResourceId(Constant.ID_ADD_VOICE, 2000));
		
		printLog("step6=Input some content");
		String content = "autotest";
		enterTextInEditor(content, "android.widget.EditText", 0);
		assertTrue("The ui element not exist: " + content, waitForTextExists(content, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_LongClickVoice() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Long click the voice icon");
		longClickResourceIdBySwipe(Constant.ID_ADD_VOICE, 5000);
	}
	
	public void testFunction_TitleBar_Notice_Create_LongClickVoice_Display() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Long click the voice icon");
		longClickResourceIdBySwipe(Constant.ID_ADD_VOICE, 5000);
		assertTrue("The UI Element not exist: Add emotion",
				!waitForResourceId(Constant.ID_ADD_EMOTION, 2000));
		assertTrue("The UI Element not exist: Add image ",
				!waitForResourceId(Constant.ID_ADD_IMAGE, 2000));
		assertTrue("The UI Element not exist: Add image by camera ",
				!waitForResourceId(Constant.ID_ADD_IMAGE_CAMERA, 2000));
		assertTrue("The UI Element not exist: Add file ",
				!waitForResourceId(Constant.ID_ADD_FILE, 2000));
		assertTrue("The UI Element not exist: Add location ",
				!waitForResourceId(Constant.ID_ADD_LOCATION, 2000));
		assertTrue("The UI Element not exist: Add title ",
				!waitForResourceId(Constant.ID_ADD_TITLE, 2000));
		assertTrue("The UI Element not exist: Add question ",
				!waitForResourceId(Constant.ID_ADD_QUESTION, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_LongClickVoice_LongVoice() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Long click the voice icon");
		longClickResourceIdBySwipe(Constant.ID_ADD_VOICE, 30000);
		assertTrue("The UI Element not exist: Voice wave icon",
				waitForResourceId(Constant.ID_VOICE_WAVE, 2000));
		assertTrue("The UI Element not exist: Voice Length icon",
				waitForResourceId(Constant.ID_VOICE_LENGTH, 2000));
		assertTrue("The UI Element not exist: Voice delete icon",
				waitForResourceId(Constant.ID_VOICE_DELETE, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_LongClickVoice_PlayVoice() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Long click the voice icon");
		longClickResourceIdBySwipe(Constant.ID_ADD_VOICE, 3000);
		assertTrue("The UI Element not exist: Voice wave icon",
				waitForResourceId(Constant.ID_VOICE_WAVE, 2000));
		
		printLog("Step6=Click the voie to play");
		clickResourceId(Constant.ID_VOICE_WAVE);
	}
	
	public void testFunction_TitleBar_Notice_Create_LongClickVoice_DeleteVoice() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Long click the voice icon");
		longClickResourceIdBySwipe(Constant.ID_ADD_VOICE, 3000);
		assertTrue("The UI Element not exist: Voice wave icon",
				waitForResourceId(Constant.ID_VOICE_WAVE, 2000));
		
		printLog("Step6=Click Delete icon");
		clickResourceId(Constant.ID_VOICE_DELETE);
		helper.verifyCreateNotificationUiElement();
	}
	
	public void testFunction_TitleBar_Notice_Create_AddAttachment() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click Add image");
		clickResourceId(Constant.ID_ADD_IMAGE);
		assertTrue("The add voice not disappear", !waitForResourceId(Constant.ID_ADD_VOICE, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddEmotion() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click Add emotion");
		clickResourceId(Constant.ID_ADD_EMOTION);
		assertTrue("CLick add emotion failed.", waitForResourceId(Constant.ID_CHAT_TOOL_EMOTION_ITEM, 3000));
		for (int i = 0; i < 3; i++) {
			getObjectOnResourceIdAndInstance(Constant.ID_CHAT_TOOL_EMOTION_ITEM, i).clickAndWaitForNewWindow();
			waitForWindowUpdate(1000);
		}
		String content = getTextOnResourceId(Constant.ID_NOTICE_CONTENT, 2000);
		printLog("emotion=" + content);
	}
	
	public void testFunction_TitleBar_Notice_Create_AddLocation() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click Add location");
		clickResourceId(Constant.ID_ADD_LOCATION);
		assertTrue("The text not exist: " + Constant.TXT_LOCATION_SELECT,
				waitForTextExists(Constant.TXT_LOCATION_SELECT, 5000));

		printLog("step6=Select " + Constant.TXT_VERIFY);
		clickText(Constant.TXT_VERIFY, true);
		assertTrue("Click the confirm failed.", waitForTextExists(Constant.TXT_LOCATION_NOTIFY, 3000));

		printLog("step7=Click yes");
		clickText(Constant.TXT_YES, true);
		assertTrue("Send the location failed.", waitForTextContainsExists(Constant.TXT_LOCATION_INFO, 8000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddFile() throws UiObjectNotFoundException, IOException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		String fileName = "testFile.txt";
		File f1 = new File("/sdcard/" + fileName);
		helper.writeSDFile(f1.toString(), 1);

		printLog("step5=Click Add file");
		clickResourceId(Constant.ID_ADD_FILE);
		assertTrue("The text not exist: " + Constant.TXT_TOOL_DOCUMENT_LOCATION,
				waitForTextExists(Constant.TXT_TOOL_DOCUMENT_LOCATION, 3000));

		printLog("step6=Click " + Constant.TXT_TOOL_DOCUMENT_LOCATION);
		clickText(Constant.TXT_TOOL_DOCUMENT_LOCATION, true);
		assertTrue("The text not exsit: " + Constant.TXT_TOOL_DOCUMENT_METHOD,
				waitForTextExists(Constant.TXT_TOOL_DOCUMENT_METHOD, 3000));

		clickText(Constant.TXT_TOOL_DOCUMENT_METHOD, true);
		clickText(Constant.TXT_TOOL_DOCUMENT_METHOD_LOCAL, true);
		clickText(Constant.TXT_TOOL_DOCUMENT_METHOD_PHONE, true);
		UiObject fileObj = scrollToFindText(fileName, "Vertical", 9);
		if (fileObj.exists()) {
			printLog("step7=Select the testZip");
			fileObj.click();
		}
		assertTrue("The text not exsit: " + fileName, waitForTextExists(fileName, 3000));
		assertTrue("The UI Element not exist: icon", waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_ICON, 2000));
		assertTrue("The UI Element not exist: title", waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_TITLE, 2000));
		assertTrue("The UI Element not exist: size", waitForResourceId(Constant.ID_CHAT_MSG_VIDEO_SIZE, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddImage() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click Add image");
		clickResourceId(Constant.ID_ADD_IMAGE);
		assertTrue("Select Picture to enter Album failed.", waitForTextExists(Constant.TXT_PICTURE, 3000));

		printLog("step6=Select one picture");
		clickPoint(200, 350);
		waitForWindowUpdate(2000);
		assertTrue("Send the picture failed.", waitForResourceId(Constant.ID_NOTICE_IMAGE, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddImage_Camera() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click Add image");
		clickResourceId(Constant.ID_ADD_IMAGE_CAMERA);
		assertTrue("Select photo to enter camera failed.",
				waitForPkgExist("com.zte.camera", 5000));

		printLog("step4=Click the shutter button");
		clickResourceId(Constant.ID_CAMERA_SHUTTER);
		assertTrue("Click the shutter failed.",
				waitForResourceId(Constant.ID_CAMERA_OK, 5000));

		printLog("step5=Click the shutter button");
		clickResourceId(Constant.ID_CAMERA_OK);
		assertTrue("Send the picture failed.", waitForResourceId(Constant.ID_NOTICE_IMAGE, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddTitle_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click Add Title");
		clickResourceId(Constant.ID_ADD_TITLE);
		assertTrue("The Ui Element not exist: " + Constant.TXT_INPUT_TITLE, waitForTextExists(Constant.TXT_INPUT_TITLE, 3000));
		assertTrue("The Ui Element not exist: " + Constant.TXT_INPUT_CONTENT, waitForTextExists(Constant.TXT_INPUT_CONTENT, 2000));
		assertTrue("The Ui Element not exist: Clear icon ", waitForResourceId(Constant.ID_NOTICE_TITLE_CLEAR, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddTitle() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click Add Title");
		clickResourceId(Constant.ID_ADD_TITLE);
		assertTrue("Add title icon is enabled" , !getObjectOnResourceId(Constant.ID_ADD_TITLE).isEnabled());
	}
	
	public void testFunction_TitleBar_Notice_Create_AddTitle_InputTitle() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click Add Title");
		clickResourceId(Constant.ID_ADD_TITLE);
		assertTrue("Add title icon is enabled" , !getObjectOnResourceId(Constant.ID_ADD_TITLE).isEnabled());
		
		printLog("step6=Input title");
		String title = "testtile";
		enterTextInEditor(title, "android.widget.EditText", 0);
		assertTrue("Input title failed", waitForTextExists(title, 3000));
		assertTrue("The input title not disappear", waitForTextGone(Constant.TXT_INPUT_TITLE, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddTitle_DeleteTitle() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click Add Title");
		clickResourceId(Constant.ID_ADD_TITLE);
		assertTrue("Add title icon is enabled" , !getObjectOnResourceId(Constant.ID_ADD_TITLE).isEnabled());
		
		printLog("step6=Delete title");
		clickResourceId(Constant.ID_NOTICE_TITLE_CLEAR);
		assertTrue("Add title icon is enabled" , getObjectOnResourceId(Constant.ID_ADD_TITLE).isEnabled());
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_Option() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		assertTrue("The Ui Element not exist: " + Constant.TXT_ESSAY_QUESTION,
				waitForTextExists(Constant.TXT_ESSAY_QUESTION, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Verify the UI Element");
		assertTrue("The Ui Element not exist: " + Constant.TXT_CANCEL,
				waitForTextExists(Constant.TXT_CANCEL, 2000));
		assertTrue("The Ui Element exist: Add Voice icon",
				!waitForResourceId(Constant.ID_ADD_VOICE, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_Cancel() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Click " + Constant.TXT_CANCEL);
		clickText(Constant.TXT_CANCEL, true);
		clickText(Constant.TXT_YES, true);
		assertTrue("Cancel failed.", !waitForTextExists(Constant.TXT_ANONYMITY, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_Anonymity_Enter() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Verify anonymity");
		assertTrue("Switch button not exist.", getObjectOnResourceId(Constant.ID_SWITCH_BUTTON).exists());
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_Anonymity_Default() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Verify anonymity");
		assertTrue("The anonymity default not close", !getObjectOnResourceId(Constant.ID_SWITCH_BUTTON).isSelected());
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_OpenAnonymity() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Open anonymity");
		clickText(Constant.TXT_ANONYMITY, true);
		assertTrue("Open the anonymity failed.", getObjectOnResourceId(Constant.ID_SWITCH_BUTTON).isSelected());
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_CloseAnonymity() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Close anonymity");
		clickText(Constant.TXT_ANONYMITY, true);
		assertTrue("Open the anonymity failed.", getObjectOnResourceId(Constant.ID_SWITCH_BUTTON).isSelected());
		clickText(Constant.TXT_ANONYMITY, true);
		assertTrue("Close the anonymity failed.", !getObjectOnResourceId(Constant.ID_SWITCH_BUTTON).isSelected());
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_MultipleChoice_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Verify UI Element");
		assertTrue("The Ui Element not exist: " + Constant.TXT_CHOICE_LIMIT1,
				waitForTextExists(Constant.TXT_CHOICE_LIMIT1, 2000));
		assertTrue("The Ui Element not exist: " + Constant.TXT_OPTION1,
				waitForTextExists(Constant.TXT_OPTION1, 2000));
		assertTrue("The Ui Element not exist: " + Constant.TXT_OPTION2,
				waitForTextExists(Constant.TXT_OPTION2, 2000));
		assertTrue("The Ui Element not exist: Add option",
				waitForResourceId(Constant.ID_ADD_OPTION, 2000));
		assertTrue("The Ui Element not exist: Delete topic",
				waitForResourceId(Constant.ID_DELETE_TOPIC, 2000));
	}

	public void testFunction_TitleBar_Notice_Create_AddQuestion_MultipleChoice_AddOption() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Add option");
		clickResourceId(Constant.ID_ADD_OPTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_OPTION3,
				waitForTextExists(Constant.TXT_OPTION3, 2000));
	}

	public void testFunction_TitleBar_Notice_Create_AddQuestion_MultipleChoice_AddOption_Delete() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Add option");
		clickResourceId(Constant.ID_ADD_OPTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_OPTION3,
				waitForTextExists(Constant.TXT_OPTION3, 2000));
		
		printLog("step8=Delete option");
		clickResourceId(Constant.ID_DELETE_OPTION);
		assertTrue("The Ui Element exist: " + Constant.TXT_OPTION3,
				!waitForTextExists(Constant.TXT_OPTION3, 2000));
		assertTrue("The Ui Element exist: Delete option",
				!waitForResourceId(Constant.ID_DELETE_OPTION, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_MultipleChoice_DeleteOption() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Add option");
		clickResourceId(Constant.ID_ADD_OPTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_OPTION3,
				waitForTextExists(Constant.TXT_OPTION3, 2000));
		
		printLog("step8=Delete option");
		getObjectOnResourceIdAndInstance(Constant.ID_DELETE_OPTION,2).clickAndWaitForNewWindow();
		assertTrue("The Ui Element exist: " + Constant.TXT_OPTION3,
				!waitForTextExists(Constant.TXT_OPTION3, 2000));
		assertTrue("The Ui Element exist: Delete option",
				!waitForResourceId(Constant.ID_DELETE_OPTION, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_MultipleChoice_ChoiceLimit_Default() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		assertTrue("The default not match: " + Constant.TXT_CHOICE_LIMIT1, waitForTextExists(Constant.TXT_CHOICE_LIMIT1, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_MultipleChoice_ChoiceLimit_OptionDisplay() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_TOPIC_LIST));
		int items = list.getChildCount(new UiSelector().resourceId(Constant.ID_OPTION_CONTENT));
		
		printLog("step7=Click " + Constant.TXT_CHOICE_LIMIT1);
		clickText(Constant.TXT_CHOICE_LIMIT1, true);
		UiCollection optionsList = new UiCollection(new UiSelector().resourceId(Constant.ID_SELECT_DIALOG_LISTVIEW));
		int options = optionsList.getChildCount(new UiSelector().resourceId(Constant.ID_TEXT1));
		assertTrue("The option not match expect", items == options);
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_MultipleChoice_ChoiceLimit_AddOption() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step8=Click Add option");
		clickResourceId(Constant.ID_ADD_OPTION);
		UiCollection list = new UiCollection(new UiSelector().resourceId(Constant.ID_TOPIC_LIST));
		int items = list.getChildCount(new UiSelector().resourceId(Constant.ID_OPTION_CONTENT));
		assertTrue("Add option Failed.", items == 3);
		
		printLog("step8=Click " + Constant.TXT_CHOICE_LIMIT1);
		clickText(Constant.TXT_CHOICE_LIMIT1, true);
		UiCollection optionsList = new UiCollection(new UiSelector().resourceId(Constant.ID_SELECT_DIALOG_LISTVIEW));
		int options = optionsList.getChildCount(new UiSelector().resourceId(Constant.ID_TEXT1));
		assertTrue("The option not match expect", items == options);
		
		printLog("step9=Click " + Constant.TXT_CHOICE_LIMIT3);
		getObjectOnResourceIdAndInstance(Constant.ID_TEXT1, options - 1).click();
		assertTrue("Select max option failed.", waitForTextExists(Constant.TXT_CHOICE_LIMIT3, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_MultipleChoice_InputContent() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_MULTIPLE_CHOICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_MULTIPLE_CHOICE);
		clickText(Constant.TXT_MULTIPLE_CHOICE, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step8=Enter topic title");
		String title = "testtitle";
		clickResourceId(Constant.ID_TOPIC_TITLE);
		ShellUtils.execCommand("input text " + title, true);
		assertTrue("Enter title failed.", waitForTextExists(title, 3000));
		
		pressKey("back");
		
		printLog("step9=Enter option content1");
		String content1 = "testcontent";
		getObjectOnResourceIdAndInstance(Constant.ID_OPTION_CONTENT, 0).click();
		ShellUtils.execCommand("input text " + content1, true);
		assertTrue("Enter title failed.", waitForTextExists(content1, 3000));
		
		pressKey("back");
		
		printLog("step10=Enter option content1");
		String content2 = "testcontent";
		getObjectOnResourceIdAndInstance(Constant.ID_OPTION_CONTENT, 1).click();
		ShellUtils.execCommand("input text " + content2, true);
		assertTrue("Enter title failed.", waitForTextExists(content2, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_EssayQuestion_Element() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_ESSAY_QUESTION, 3000));
		
		printLog("step6=Click " + Constant.TXT_ESSAY_QUESTION);
		clickText(Constant.TXT_ESSAY_QUESTION, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Verify UI Element");
		assertTrue("The Ui Element not exist: " + Constant.TXT_TOPIC_TITLE_ESSAY,
				waitForTextExists(Constant.TXT_TOPIC_TITLE_ESSAY, 2000));
		assertTrue("The Ui Element not exist: Delete topic",
				waitForResourceId(Constant.ID_DELETE_TOPIC, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_EssayQuestion_InputTitle() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_ESSAY_QUESTION, 3000));
		
		printLog("step6=Click " + Constant.TXT_ESSAY_QUESTION);
		clickText(Constant.TXT_ESSAY_QUESTION, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Enter topic title");
		String title = "testtitle";
		clickResourceId(Constant.ID_TOPIC_TITLE);
		ShellUtils.execCommand("input text " + title, true);
		assertTrue("Enter title failed.", waitForTextExists(title, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_EssayQuestion_DeleteOne() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_ESSAY_QUESTION, 3000));
		
		printLog("step6=Click " + Constant.TXT_ESSAY_QUESTION);
		clickText(Constant.TXT_ESSAY_QUESTION, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Click Delete button");
		clickResourceId(Constant.ID_DELETE_TOPIC);
		assertTrue("Delete question failed.", !waitForTextExists(Constant.TXT_ANONYMITY, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_EssayQuestion_DeleteAll() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_ESSAY_QUESTION, 3000));
		
		printLog("step6=Click " + Constant.TXT_ESSAY_QUESTION);
		clickText(Constant.TXT_ESSAY_QUESTION, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step7=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_ESSAY_QUESTION, 3000));
		
		printLog("step8=Click " + Constant.TXT_ESSAY_QUESTION);
		clickText(Constant.TXT_ESSAY_QUESTION, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step9=Delete all question");
		while (waitForResourceId(Constant.ID_DELETE_TOPIC, 2000)) {
			clickResourceId(Constant.ID_DELETE_TOPIC);
		}
		assertTrue("Delete question failed.", !waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		assertTrue("Delete question failed.", waitForResourceId(Constant.ID_ADD_VOICE, 2000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_EssayQuestion_Back() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_ESSAY_QUESTION, 3000));
		
		printLog("step6=Click " + Constant.TXT_ESSAY_QUESTION);
		clickText(Constant.TXT_ESSAY_QUESTION, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step6=Click " + Constant.TXT_CANCEL);
		clickText(Constant.TXT_CANCEL, true);
		assertTrue("Click cancel failed.", waitForTextExists(Constant.TXT_CANCEL_CREATE_NOTICE, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_EssayQuestion_BackMsgList() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_ESSAY_QUESTION, 3000));
		
		printLog("step6=Click " + Constant.TXT_ESSAY_QUESTION);
		clickText(Constant.TXT_ESSAY_QUESTION, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step6=Click " + Constant.TXT_CANCEL);
		clickText(Constant.TXT_CANCEL, true);
		assertTrue("Click cancel failed.", waitForTextExists(Constant.TXT_CANCEL_CREATE_NOTICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_YES);
		clickText(Constant.TXT_YES, true);
		assertTrue("Back to Msg list failed.", waitForResourceId(Constant.ID_MESSAGE_LIST, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddQuestion_EssayQuestion_BackNotice() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_ESSAY_QUESTION, 3000));
		
		printLog("step6=Click " + Constant.TXT_ESSAY_QUESTION);
		clickText(Constant.TXT_ESSAY_QUESTION, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step6=Click " + Constant.TXT_CANCEL);
		clickText(Constant.TXT_CANCEL, true);
		assertTrue("Click cancel failed.", waitForTextExists(Constant.TXT_CANCEL_CREATE_NOTICE, 3000));
		
		printLog("step6=Click " + Constant.TXT_NO);
		clickText(Constant.TXT_NO, true);
		assertTrue("Back to notice failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_NoReceiver() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);

		printLog("step5=Click " + Constant.TXT_SEND);
		clickText(Constant.TXT_SEND, true);
		assertTrue("No receiver but create notice sucessful.", waitForResourceId(Constant.ID_ADD_VOICE, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddReceiver_NoContent() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));
		} else {
			assertTrue("No any contacts", false);
		}
		
		printLog("step7=Click the confirm button");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Select one member failed.", waitForTextExists(Constant.TXT_SELECT_ONE, 3000));
		
		printLog("step5=Click " + Constant.TXT_SEND);
		clickText(Constant.TXT_SEND, true);
		assertTrue("No receiver but create notice sucessful.", waitForResourceId(Constant.ID_ADD_VOICE, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddReceiver_AddTitle_NoContent() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));
		} else {
			assertTrue("No any contacts", false);
		}
		
		printLog("step7=Click the confirm button");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Select one member failed.", waitForTextExists(Constant.TXT_SELECT_ONE, 3000));
		
		printLog("step8=Click Add Title");
		clickResourceId(Constant.ID_ADD_TITLE);
		assertTrue("Add title icon is enabled" , !getObjectOnResourceId(Constant.ID_ADD_TITLE).isEnabled());
		
		printLog("step9=Click " + Constant.TXT_SEND);
		clickText(Constant.TXT_SEND, true);
		assertTrue("No content but create notice sucessful.", waitForResourceId(Constant.ID_ADD_RECEIVER, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddReceiver_AddQuestion_NoContent() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));
		} else {
			assertTrue("No any contacts", false);
		}
		
		printLog("step7=Click the confirm button");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Select one member failed.", waitForTextExists(Constant.TXT_SELECT_ONE, 3000));
		
		printLog("step8=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_ESSAY_QUESTION, 3000));
		
		printLog("step9=Click " + Constant.TXT_ESSAY_QUESTION);
		clickText(Constant.TXT_ESSAY_QUESTION, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step10=Click " + Constant.TXT_SEND);
		clickText(Constant.TXT_SEND, true);
		assertTrue("No content but create notice sucessful.", waitForResourceId(Constant.ID_ADD_RECEIVER, 3000));
	}
	
	public void testFunction_TitleBar_Notice_Create_AddReceiver_AddTitle_AddQuestion_InputSpace() throws UiObjectNotFoundException {
		printLog("CaseName=" + getName());
		String phoneNum = getConfigProperty("phoneNum");
		String password = getConfigProperty("password");

		helper.notification_Create(phoneNum, password);
		
		printLog("step5=Click the add receiver button");
		clickResourceId(Constant.ID_ADD_RECEIVER);
		assertTrue("Ui Element not exist: " + Constant.TXT_SELECT_BY_CONTACTS,
				waitForTextExists(Constant.TXT_SELECT_BY_CONTACTS, 8000));
		
		printLog("step6=Select a member");
		UiObject checkIcon = new UiObject(new UiSelector().resourceId(Constant.ID_CHECK).instance(0));
		if (checkIcon.exists()) {
			checkIcon.click();
			assertTrue("Select a member failed.", checkIcon.isChecked());
			assertTrue("Select List not display", waitForResourceId(Constant.ID_SELECT_MEMBER_LIST, 2000));
			assertTrue("Select Name not display", waitForResourceId(Constant.ID_SELECT_MEMBER_NAME, 2000));
		} else {
			assertTrue("No any contacts", false);
		}
		
		printLog("step7=Click the confirm button");
		clickResourceId(Constant.ID_TITLEBAR_CONFIRM);
		assertTrue("Select one member failed.", waitForTextExists(Constant.TXT_SELECT_ONE, 3000));
		
		printLog("step8=Input content");
		clickResourceId(Constant.ID_NOTICE_CONTENT);
		String content = " ";
		enterTextInEditor(content, "android.widget.EditText", 0);
		assertTrue("The input content not disappear", waitForTextGone(Constant.TXT_INPUT_CONTENT, 2000));
		
		pressKey("back");
		
		printLog("step9=Click Add Title");
		clickResourceId(Constant.ID_ADD_TITLE);
		assertTrue("Add title icon is enabled" , !getObjectOnResourceId(Constant.ID_ADD_TITLE).isEnabled());
		
		printLog("step10=Input title");
		String title = " ";
		enterTextInEditor(title, "android.widget.EditText", 0);
		assertTrue("The input title not disappear", waitForTextGone(Constant.TXT_INPUT_TITLE, 2000));
		
		pressKey("back");
		
		printLog("step11=Click Add Question");
		clickResourceId(Constant.ID_ADD_QUESTION);
		assertTrue("The Ui Element not exist: " + Constant.TXT_MULTIPLE_CHOICE,
				waitForTextExists(Constant.TXT_ESSAY_QUESTION, 3000));
		
		printLog("step12=Click " + Constant.TXT_ESSAY_QUESTION);
		clickText(Constant.TXT_ESSAY_QUESTION, true);
		assertTrue("Click add qustion failed.", waitForTextExists(Constant.TXT_ANONYMITY, 3000));
		
		printLog("step13=");
		clickResourceId(Constant.ID_TOPIC_TITLE);
		String question = " ";
		enterTextInEditor(question, "android.widget.EditText", 2);
		assertTrue("The input question not disappear", waitForTextGone(Constant.TXT_TOPIC_TITLE_ESSAY, 2000));
		
		printLog("step10=Click " + Constant.TXT_SEND);
		clickText(Constant.TXT_SEND, true);
		assertTrue("No content but create notice sucessful.", waitForResourceId(Constant.ID_ADD_RECEIVER, 3000));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String className, jarName, androidId;
		className = "test.FunctionTest";
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
		LogOut = false;
		DeleteGroup = false;
		TurnOnNetWork = false;

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
