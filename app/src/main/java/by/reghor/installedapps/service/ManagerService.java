package by.reghor.installedapps.service;

import android.view.View;

import java.util.List;

import by.reghor.installedapps.R;
import by.reghor.installedapps.activity.MainActivity;
import by.reghor.installedapps.entity.AppInfo;
import by.reghor.installedapps.module.AppInfoKeeper;

/**
 * Created by reghor on 1/18/16.
 */
public class ManagerService {

    private static final ManagerService instance = new ManagerService();
    private UIService uiService = UIService.getInstance();
    private AppInfoService appInfoService = AppInfoService.getInstance();

    private ManagerService() {

    }

    public void buttonClicked(View view) {
        switch (view.getId()) {
            case R.id.get_app_list:
                MainActivity activity = (MainActivity) view.getContext();
                List<AppInfo> appInfoList=appInfoService.getMergedAppInfoList(activity);
                AppInfoKeeper.getInstance().setAppInfoList(appInfoList);

                uiService.setActivityAppListLayout(activity, appInfoList);
                break;
        }
    }

    public void backKeyPressed(MainActivity activity){
        switch (activity.getLayoutId()) {
            case R.layout.activity_main: {
                activity.finish();
            }
            case R.layout.activity_main_list: {
                uiService.setActivityMainLayout(activity);
            }
        }

    }

    public static ManagerService getInstance() {
        return instance;
    }

}
