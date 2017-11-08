package uiautotestutils.utils.watchers;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiWatcher;

public abstract class UnwantedUiObjectWatcher implements UiWatcher {
	UiObject uiObj;
	public UnwantedUiObjectWatcher(UiObject uiObj){
		this.uiObj = uiObj;
	}
	@Override
	public boolean checkForCondition() {
		// TODO Auto-generated method stub
		if(uiObj.exists()){ 
			action();
			return true;
		} else {
			return false;
		}
	}
	public abstract boolean action();
}
