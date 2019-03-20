package lingaraj.hourglass.in.base.home;

import android.support.v4.app.Fragment;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.core.BaseActivity;
import lingaraj.hourglass.in.base.home.articlesFragment.ArticlesFragment;
import timber.log.Timber;

public class HomeActivity extends BaseActivity {

  @Override protected int layoutRes() {
    Timber.d("Layout Supplied");
    return R.layout.activity_main;
  }

  @Override public Fragment initialScreen() {
    return ArticlesFragment.newInstance();
  }
}
