package lingaraj.hourglass.in.base.home;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.core.BaseActivity;
import lingaraj.hourglass.in.base.home.fragment.HomeFragment;
import timber.log.Timber;

public class HomeActivity extends BaseActivity {

  @Override protected int layoutRes() {
    Timber.d("Layout Supplied");
    return R.layout.activity_main;
  }

  @Override public Fragment initialScreen() {
    return HomeFragment.newInstance();
  }
}
