package lingaraj.hourglass.in.base.ui;

import android.support.v4.app.Fragment;

import java.util.List;

public interface ScreenNavigator {

    boolean pop();

    void onBackPressed();

    void finish();

    void showFragment(Fragment tobeHidden,String fragmentTag);

    void loadFragments(Fragment activeFragment, List<Fragment> hiddenFragmentList);

}
