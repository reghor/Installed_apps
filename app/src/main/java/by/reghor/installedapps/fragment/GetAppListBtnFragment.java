package by.reghor.installedapps.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.reghor.installedapps.R;
import by.reghor.installedapps.listener.UIClickListener;


/**
 * Created by reghor on 1/16/16.
 */
public class GetAppListBtnFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_main_get_list_btn, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setSettings();
    }

    private void setSettings(){
        View view = getView();
        if(view != null) {
            view.findViewById(R.id.get_app_list).setOnClickListener(UIClickListener.getInstance());
        }
    }
}
