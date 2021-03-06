package lingaraj.hourglass.in.base.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import java.util.List;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.core.BaseFragment;
import lingaraj.hourglass.in.base.di.ActivityScope;
import lingaraj.hourglass.in.base.lifecycle.ActivityLifecycleTask;

@ActivityScope
public class DefaultScreenNavigator extends ActivityLifecycleTask implements ScreenNavigator {

    private FragmentManager fragmentManager;
    private AppCompatActivity activity;


    @Inject
    DefaultScreenNavigator() {

    }

    @Override
    public void onCreate(AppCompatActivity activity) {
        this.activity = activity;
        init(activity.getSupportFragmentManager(), ((ScreenProvider) activity).initialScreen());
    }

    private void init(FragmentManager fragmentManager, Fragment rootScreen) {
        this.fragmentManager = fragmentManager;
        if (fragmentManager.getFragments().size() == 0) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, rootScreen)
                    .commit();
        }
    }

    @Override
    public boolean pop() {
        return fragmentManager != null && fragmentManager.popBackStackImmediate();
    }


    @Override
    public void onBackPressed() {
        if (fragmentManager != null) {
            pop();
        }
    }


    @Override
    public void finish() {
        activity.finish();
    }


    @Override
    public void onDestroy(AppCompatActivity activity) {
        fragmentManager = null;
    }




    @Override
    public void loadFragments(Fragment activeFragment, List<Fragment> hiddenFragmentList) {
       }


    @Override
    public void showFragment(Fragment tobeShown,String fragmentTag) {
        if (fragmentManager!=null){
            fragmentManager.beginTransaction().add(R.id.fragment_container,tobeShown).addToBackStack(fragmentTag).commit();
        }
     }




}
