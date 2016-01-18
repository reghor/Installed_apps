package by.reghor.installedapps.util;

import java.util.Comparator;

import by.reghor.installedapps.entity.AppInfo;

/**
 * Created by reghor on 1/18/16.
 */
public class AppInfoNameComparator implements Comparator<AppInfo> {
    @Override
    public int compare(AppInfo lhs, AppInfo rhs) {
        return lhs.getAppName().compareTo(rhs.getAppName());
    }
}
