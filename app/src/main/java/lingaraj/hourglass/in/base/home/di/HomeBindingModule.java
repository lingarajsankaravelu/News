package lingaraj.hourglass.in.base.home.di;

import android.support.v4.app.Fragment;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import lingaraj.hourglass.in.base.home.articlesFragment.ArticlesFragment;
import lingaraj.hourglass.in.base.home.articlesFragment.di.HomeFragmentComponent;

/**
 * Component of all the fragments component which is added to HomeActivity goes here
 */
@Module(subcomponents = { HomeFragmentComponent.class })
public abstract class HomeBindingModule {

  @Binds
  @IntoMap
  @FragmentKey(ArticlesFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindHomeFragment(HomeFragmentComponent.Builder builder);


}
