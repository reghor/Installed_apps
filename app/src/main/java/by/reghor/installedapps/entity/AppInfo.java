package by.reghor.installedapps.entity;

import android.graphics.drawable.Drawable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;


@DatabaseTable(tableName = "appinfo")
public class AppInfo {

    @DatabaseField(id = true, index = true)
    private String packageName;
    @DatabaseField
    private Date lastCheckedDate;
    @DatabaseField
    private Status status;

    private Drawable icon;
    @DatabaseField
    private String appName;

    public AppInfo(String packageName, Date lastCheckedDate, Status status, Drawable icon, String appName) {
        this.packageName = packageName;
        this.lastCheckedDate = lastCheckedDate;
        this.status = status;
        this.icon = icon;
        this.appName = appName;
    }

    public AppInfo() {

    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Date getLastCheckedDate() {
        return lastCheckedDate;
    }

    public void setLastCheckedDate(Date lastCheckedDate) {
        this.lastCheckedDate = lastCheckedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppInfo appInfo = (AppInfo) o;

        if (packageName != null ? !packageName.equals(appInfo.packageName) : appInfo.packageName != null)
            return false;
        if (lastCheckedDate != null ? !lastCheckedDate.equals(appInfo.lastCheckedDate) : appInfo.lastCheckedDate != null)
            return false;
        if (status != appInfo.status) return false;
        if (icon != null ? !icon.equals(appInfo.icon) : appInfo.icon != null) return false;
        return !(appName != null ? !appName.equals(appInfo.appName) : appInfo.appName != null);

    }

    @Override
    public int hashCode() {
        int result = packageName != null ? packageName.hashCode() : 0;
        result = 31 * result + (lastCheckedDate != null ? lastCheckedDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (appName != null ? appName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "packageName='" + packageName + '\'' +
                ", lastCheckedDate=" + lastCheckedDate +
                ", status=" + status +
                ", icon=" + icon +
                ", appName='" + appName + '\'' +
                '}';
    }
}
