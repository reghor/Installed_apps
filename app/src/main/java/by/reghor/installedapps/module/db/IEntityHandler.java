package by.reghor.installedapps.module.db;

import java.sql.SQLException;
import java.util.List;

import by.reghor.installedapps.entity.AppInfo;

/**
 * Created by reghor on 1/16/16.
 */
public interface IEntityHandler {
    List<AppInfo> getAllAppInfo() throws SQLException;
    AppInfo getAppInfo(String packageName) throws SQLException;
    int createAppInfo(AppInfo appInfo) throws SQLException;
    void removeAppInfo(String packageName) throws SQLException;
    void editAppInfo(AppInfo appInfo) throws SQLException;
}
