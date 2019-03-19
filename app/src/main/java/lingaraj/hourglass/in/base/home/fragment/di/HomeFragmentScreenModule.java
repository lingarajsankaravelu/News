package lingaraj.hourglass.in.base.home.fragment.di;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import lingaraj.hourglass.in.base.lifecycle.ScreenLifecycleTask;

@Module
public abstract class HomeFragmentScreenModule {

  @Binds
  @IntoSet
  abstract ScreenLifecycleTask bindUiManagerTask(HomeFragmentUIManager homeFragmentUIManager);
}
