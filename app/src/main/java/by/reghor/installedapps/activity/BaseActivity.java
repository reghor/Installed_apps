package by.reghor.installedapps.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import by.reghor.installedapps.R;

/**
 * Created by reghor on 1/16/16.
 */
public abstract class BaseActivity extends FragmentActivity {

    public void addFragment(@IdRes int containerViewId,
                            @NonNull Fragment fragment,
                            @NonNull String fragmentTag) {
       FragmentTransaction fragmentTransaction= getFragmentManagerSupport()
                .beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
                fragmentTransaction.add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    public void replaceFragment(@IdRes int containerViewId,
                                @NonNull Fragment fragment,
                                @NonNull String fragmentTag,
                                @Nullable String backStackStateName) {
        getFragmentManagerSupport()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }

    public void removeFragment(@NonNull Fragment fragment, @Nullable String backStackStateName) {
        getFragmentManagerSupport()
                .beginTransaction()
                .remove(fragment)
                .addToBackStack(null)
                .commit();
    }

    public FragmentManager getFragmentManagerSupport() {
        return this.getSupportFragmentManager();

    }
}
