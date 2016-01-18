package by.reghor.installedapps.service;

import android.app.Activity;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.reghor.installedapps.entity.AppInfo;
import by.reghor.installedapps.entity.Status;
import by.reghor.installedapps.event.DBTroubleEvent;
import by.reghor.installedapps.module.SystemInfoRetriever;
import by.reghor.installedapps.module.db.IEntityHandler;
import by.reghor.installedapps.module.db.ormlite.OrmLiteEntityHandlerImpl;
import by.reghor.installedapps.util.AppInfoNameComparator;
import de.greenrobot.event.EventBus;

/**
 * Created by reghor on 1/17/16.
 */
public class AppInfoService {

    private static final AppInfoService instance = new AppInfoService();

    private AppInfoService() {

    }

    public static AppInfoService getInstance() {
        return instance;
    }

    public List<AppInfo> getAppInfoListFromSystem(Activity activity) {
        List<AppInfo> appInfoList = SystemInfoRetriever.getInstance().getAppInfoList(activity);
        return appInfoList;
    }

    public List<AppInfo> getAppInfoFromDB(Activity activity) {
        try {
            List<AppInfo> appInfoList = OrmLiteEntityHandlerImpl.getInstance(activity).getAllAppInfo();
            for (AppInfo appInfo : appInfoList) {
                appInfo.setIcon(SystemInfoRetriever.getInstance().getIcon(activity, appInfo.getPackageName()));
            }

            return appInfoList;
        } catch (SQLException e) {
            EventBus.getDefault().post(new DBTroubleEvent("can not connect to database"));
        }
        return new ArrayList<>();
    }

    public List<AppInfo> getMergedAppInfoList(Activity activity) {
        List<AppInfo> appInfoListSystem = getAppInfoListFromSystem(activity);
        List<AppInfo> appInfoListDB = getAppInfoFromDB(activity);
        List<AppInfo> result = new ArrayList<>();
        //setting old, new apps

        for (AppInfo appInfoSystem : appInfoListSystem) {
            if (appInfoListContatins(appInfoListDB, appInfoSystem)) {
                appInfoSystem.setStatus(Status.OLD);
            } else {
                appInfoSystem.setStatus(Status.NEW);
            }
        }
        //setting deleted apps
        for (AppInfo appInfoDB : appInfoListDB) {
            if (!appInfoListContatins(appInfoListSystem, appInfoDB)) {
                if (appInfoDB.getStatus().equals(Status.DELETED)) {
                    appInfoDB.setStatus(Status.TO_BE_REMOVED);
                } else {
                    appInfoDB.setStatus(Status.DELETED);
                }
                result.add(appInfoDB);
            }
        }


        result.addAll(appInfoListSystem);
        saveChangesToDBAppInfoList(result, activity);
        Collections.sort(result, new AppInfoNameComparator());
        return result;
    }

    public void saveChangesToDBAppInfoList(List<AppInfo> appInfoList, Activity activity) {
        IEntityHandler entityHandler = OrmLiteEntityHandlerImpl.getInstance(activity);
        List<Integer> toBeDeleted=new ArrayList<>();
        int i=0;
        for (AppInfo appInfo : appInfoList) {
            try {
                if (appInfo.getStatus().equals(Status.NEW)) {
                    entityHandler.createAppInfo(appInfo);
                } else if (appInfo.getStatus().equals(Status.TO_BE_REMOVED)) {
                    entityHandler.removeAppInfo(appInfo.getPackageName());
                    toBeDeleted.add(toBeDeleted.indexOf(appInfo));
                } else {
                    entityHandler.editAppInfo(appInfo);
                }
            } catch (SQLException e) {
                Log.e("saving_to_db", "can not save to db");
            }
            i++;
        }
        for (Integer numberTBD:toBeDeleted){
            appInfoList.remove(numberTBD);
        }
    }


    private boolean appInfoListContatins(List<AppInfo> appInfoList, AppInfo appInfo) {
        boolean found = false;
        for (AppInfo appInfoCurrent : appInfoList) {
            if (appInfoCurrent.getPackageName().equals(appInfo.getPackageName())) {
                found = true;
                break;
            }
        }
        return found;
    }


}
