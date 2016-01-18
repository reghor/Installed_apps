package by.reghor.installedapps.activity;

import android.os.Bundle;
import android.widget.Toast;

import by.reghor.installedapps.R;
import by.reghor.installedapps.event.DBTroubleEvent;
import by.reghor.installedapps.fragment.DummyImageFragment;
import by.reghor.installedapps.fragment.GetAppListBtnFragment;
import by.reghor.installedapps.service.ManagerService;
import de.greenrobot.event.EventBus;


public class MainActivity extends BaseActivity {

    public static final String DUMMY_ICON = "dummy_icon";
    public static final String GET_APP_LIST_BTN = "get_app_list_btn";
    public static final String LIST_OF_APPS="list_of_apps";

    private int layoutId;

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(R.id.main_ll, new DummyImageFragment(), DUMMY_ICON);
        addFragment(R.id.main_ll, new GetAppListBtnFragment(), GET_APP_LIST_BTN);
    }

    @Override
    protected void onResume() {
        EventBus.getDefault().register(this);
        super.onResume();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setLayoutId(layoutResID);
    }

    @Override
    public void onBackPressed() {
        ManagerService.getInstance().backKeyPressed(this);
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public void onEvent(DBTroubleEvent event){
        Toast.makeText(this,event.getMessage(),Toast.LENGTH_LONG);
    }

}
