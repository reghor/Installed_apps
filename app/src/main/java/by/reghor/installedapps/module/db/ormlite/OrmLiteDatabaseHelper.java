package by.reghor.installedapps.module.db.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import by.reghor.installedapps.R;
import by.reghor.installedapps.entity.AppInfo;


public class OrmLiteDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "accounts";
    private static final int DATABASE_VERSION = 1;


    private Dao<AppInfo, String> dao;

    public OrmLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,
                R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource, AppInfo.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, AppInfo.class, false);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Dao<AppInfo, String> getDao() throws SQLException {
        if (dao == null) {
            dao = getDao(AppInfo.class);
        }
        return dao;
    }
}
