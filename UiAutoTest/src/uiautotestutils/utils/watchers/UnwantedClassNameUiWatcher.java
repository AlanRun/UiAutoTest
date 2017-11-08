package uiautotestutils.utils.watchers;


import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;

/**
 * often using to remove a specific class popup exception block the current test
 * @author 28100520
 *
 */
public abstract class UnwantedClassNameUiWatcher  implements UiWatcher {
	private String mClassName;
	
	public UnwantedClassNameUiWatcher(String className){
		this.mClassName = className;
	}
	@Override
	public boolean checkForCondition() {
		// TODO Auto-generated method stub
		UiObject obj = new UiObject(new UiSelector().className(mClassName));
		try{
			if(obj.exists()){
				action();
				return true;
			}
			
		}catch (Exception e){
			
		}
		return false;
	}
	public abstract void action();
	

}
