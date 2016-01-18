package by.reghor.installedapps.service;

import java.util.List;

import by.reghor.installedapps.R;
import by.reghor.installedapps.activity.MainActivity;
import by.reghor.installedapps.entity.AppInfo;
import by.reghor.installedapps.fragment.AppListFragment;
import by.reghor.installedapps.fragment.DummyImageFragment;
import by.reghor.installedapps.fragment.GetAppListBtnFragment;

/**
 * Created by reghor on 1/17/16.
 */

public class UIService {

    private static final UIService instance = new UIService();

    private UIService() {
    }

    public void setActivityMainLayout(MainActivity activity) {

        activity.setContentView(R.layout.activity_main);

        activity.addFragment(R.id.main_ll, new DummyImageFragment(), MainActivity.DUMMY_ICON);
        activity.addFragment(R.id.main_ll, new GetAppListBtnFragment(), MainActivity.GET_APP_LIST_BTN);
    }

    public void setActivityAppListLayout(final MainActivity activity, List<AppInfo> appInfoList) {
        activity.setContentView(R.layout.activity_main_list);
        activity.addFragment(R.id.main_rl_list_of_apps, AppListFragment.newInstance(appInfoList), MainActivity.LIST_OF_APPS);

    }


    public static UIService getInstance() {
        return instance;
    }


}
