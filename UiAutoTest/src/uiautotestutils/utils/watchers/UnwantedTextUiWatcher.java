package uiautotestutils.utils.watchers;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;

/**
 * To remove unwanted UI contains "text", override action() method to operate the exception
 * @author 28100520
 *
 */
public abstract class UnwantedTextUiWatcher implements UiWatcher {

	private String text;
	
	/**
	 * 
	 * @param info
	 */
	public UnwantedTextUiWatcher(String info){
		this.text = info;
	}
	@Override
	public boolean checkForCondition() {
		// TODO Auto-generated method stub
		UiObject obj = new UiObject(new UiSelector().text(text));

		if (obj.exists()) {
			try {
				//new UiObject(new UiSelector().text(touchText)).clickAndWaitForNewWindow(Constant.TIMEOUT);
				action();
				return true;
			} catch (Exception e) {
				// Ignore
			}
		}

		return false;
	}
	
	public abstract void action();

}
