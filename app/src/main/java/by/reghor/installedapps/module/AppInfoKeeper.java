package by.reghor.installedapps.module;

import java.util.ArrayList;
import java.util.List;

import by.reghor.installedapps.entity.AppInfo;

/**
 * Created by reghor on 1/19/16.
 */
public class AppInfoKeeper {
    private List<AppInfo> appInfoList = new ArrayList<>();
    private static final AppInfoKeeper instance = new AppInfoKeeper();

    public List<AppInfo> getAppInfoList() {
        return appInfoList;
    }

    public void setAppInfoList(List<AppInfo> appInfoList) {
        this.appInfoList = appInfoList;
    }

    public static AppInfoKeeper getInstance() {
        return instance;
    }


}
