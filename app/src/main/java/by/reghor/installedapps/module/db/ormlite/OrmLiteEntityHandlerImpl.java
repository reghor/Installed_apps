package by.reghor.installedapps.module.db.ormlite;

import android.content.Context;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import by.reghor.installedapps.R;
import by.reghor.installedapps.module.db.IEntityHandler;
import by.reghor.installedapps.entity.AppInfo;

/**
 * Created by reghor on 1/16/16.
 */
public class OrmLiteEntityHandlerImpl implements IEntityHandler {

    private OrmLiteDatabaseHelper todoOpenDatabaseHelper;

    private Dao<AppInfo, String> appInfoDao;

    private static OrmLiteEntityHandlerImpl instance;

    private OrmLiteEntityHandlerImpl(Context context) {

        todoOpenDatabaseHelper = OpenHelperManager.getHelper(context,
                OrmLiteDatabaseHelper.class);
        try {
            appInfoDao = todoOpenDatabaseHelper.getDao();
        } catch (SQLException e) {
            String message = context.getResources().getString(R.string.db_error_init_message);
            Toast.makeText(context, "", Toast.LENGTH_LONG);
        }
    }

    public static OrmLiteEntityHandlerImpl getInstance(Context context) {

        return instance  = new OrmLiteEntityHandlerImpl(context);

    }

    @Override
    public List<AppInfo> getAllAppInfo() throws SQLException {
        return appInfoDao.queryForAll();
    }

    @Override
    public AppInfo getAppInfo(String packageName) throws SQLException {
        return appInfoDao.queryForId(packageName);
    }

    @Override
    public int createAppInfo(AppInfo appInfo) throws SQLException {
        return appInfoDao.create(appInfo);
    }

    @Override
    public void removeAppInfo(String packageName) throws SQLException {
        appInfoDao.deleteById(packageName);
    }

    @Override
    public void editAppInfo(AppInfo appInfo) throws SQLException {
        appInfoDao.update(appInfo);
    }
}
