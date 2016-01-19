package by.reghor.installedapps.module;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.reghor.installedapps.entity.AppInfo;

/**
 * Created by reghor on 1/18/16.
 */
public class SystemInfoRetriever {
    private static final SystemInfoRetriever instance = new SystemInfoRetriever();
    public static final String dateFormat="yyyy-MM-dd hh:mm:ss";
    private static final DateFormat dateFormatter=new SimpleDateFormat(dateFormat);

    private SystemInfoRetriever() {

    }

    public List<AppInfo> getAppInfoList(Activity activity) {

        PackageManager pm = activity.getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(0);
        List<AppInfo> result = new ArrayList<>();
        for (ApplicationInfo applicationInfo : apps) {
            AppInfo appInfo = new AppInfo();
            appInfo.setIcon(getIcon(applicationInfo.loadIcon(pm)));
            appInfo.setAppName(getApplicationName(activity, applicationInfo.packageName));
            appInfo.setPackageName(applicationInfo.packageName);
            appInfo.setIcon(getIcon(activity, applicationInfo.packageName));
            appInfo.setLastCheckedDate(new Date(System.currentTimeMillis()));
            result.add(appInfo);
        }
        return result;
    }


    public String getApplicationName(Activity activity, String packageName) {
//        final PackageManager pm = activity.getApplicationContext().getPackageManager();
//        ApplicationInfo ai;
//        try {
//            ai = pm.getApplicationInfo( activity.getApplicationContext().getPackageName(), 0);
//        } catch (final PackageManager.NameNotFoundException e) {
//            ai = null;
//        }
        PackageManager packageManager = activity.getApplicationContext().getPackageManager();
        String result = "";
        try {
            ApplicationInfo app = packageManager.getApplicationInfo(packageName, 0);
            result = packageManager.getApplicationLabel(app).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return result;
//        return (String) (ai != null ? pm.getApplicationLabel(ai) : "???");
    }

    public Bitmap getIcon(Activity activity, String packageName) {
        Bitmap result = null;
        try {

            Drawable drawable = activity.getApplicationContext().getPackageManager().getApplicationIcon(packageName);
            result = getIcon(drawable);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Bitmap getIcon(Drawable icon) {
        Bitmap APKicon;
        if (icon instanceof BitmapDrawable) {
            APKicon = ((BitmapDrawable) icon).getBitmap();
        } else {
            Bitmap bitmap = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            icon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            icon.draw(canvas);
            APKicon = bitmap;
        }
        return APKicon;
    }

    public static SystemInfoRetriever getInstance() {
        return instance;
    }
}
