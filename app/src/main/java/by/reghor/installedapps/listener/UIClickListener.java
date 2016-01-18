package by.reghor.installedapps.listener;

import android.view.View;

import by.reghor.installedapps.service.ManagerService;


public class UIClickListener implements View.OnClickListener {

    private static final UIClickListener instance = new UIClickListener();

    private UIClickListener() {

    }

    @Override
    public void onClick(View v) {
        ManagerService.getInstance().buttonClicked(v);
    }

//    private void testOutOrmLiteDatabase(Context context) throws SQLException {
//        IEntityHandler handler = DBController.getHandler(context);
//        Toast.makeText(context, handler.toString(), Toast.LENGTH_LONG);
//        Date currDateTime = new Date(System.currentTimeMillis());
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(currDateTime);
//        calendar.add(Calendar.DATE, 14);
//
//        Date dueDate = calendar.getTime();
//
//
//        List<AppInfo> apps = handler.getAllAppInfo();
//        Toast.makeText(context, "CURRENT NUMBER OF APPS: " + apps.size(), Toast.LENGTH_LONG);
//        handler.removeAppInfo("package 1");
//        apps = handler.getAllAppInfo();
//        Toast.makeText(context, "ANOTHER NUMBER OF APPS: " + apps.size(), Toast.LENGTH_LONG);
//
//    }

    public static UIClickListener getInstance() {
        return instance;
    }

}
