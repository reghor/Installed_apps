package by.reghor.installedapps.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import by.reghor.installedapps.R;
import by.reghor.installedapps.adapter.AppInfoListAdapter;
import by.reghor.installedapps.entity.AppInfo;


public class AppListFragment extends ListFragment {

    private List<AppInfo> items;


    public static AppListFragment newInstance(List<AppInfo> appInfoList) {
        AppListFragment fragment = new AppListFragment();
        fragment.setItems(appInfoList);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        Toast.makeText(this.getContext(),"n of apps"+items.size(),Toast.LENGTH_LONG);

        // initialize and set the list adapter
        setListAdapter(new AppInfoListAdapter(getActivity(), items));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_app_list, container, false);
    }



    public List<AppInfo> getItems() {
        return items;
    }

    public void setItems(List<AppInfo> items) {
        this.items = items;
    }




}
