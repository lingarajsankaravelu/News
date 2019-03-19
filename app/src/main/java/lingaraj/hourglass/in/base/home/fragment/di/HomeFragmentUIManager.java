package lingaraj.hourglass.in.base.home.fragment.di;

import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.lifecycle.ScreenLifecycleTask;
import lingaraj.hourglass.in.base.ui.ScreenNavigator;
import lingaraj.hourglass.in.base.utils.ButterKnifeUtils;

public class HomeFragmentUIManager extends ScreenLifecycleTask {

  private ScreenNavigator navigator;
  private Unbinder unbinder;


  @Inject
  HomeFragmentUIManager(ScreenNavigator screenNavigator){
    navigator = screenNavigator;
  }

  @Override public void onEnterScope(View view) {
    super.onEnterScope(view);
    unbinder = ButterKnife.bind(view);
  }

  @Override public void onExitScope() {
    super.onExitScope();
    ButterKnifeUtils.unbind(unbinder);
  }
}
