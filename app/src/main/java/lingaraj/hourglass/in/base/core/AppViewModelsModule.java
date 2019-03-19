package lingaraj.hourglass.in.base.core;

import android.arch.lifecycle.ViewModel;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import javax.inject.Provider;
import lingaraj.hourglass.in.base.home.fragment.di.HomeFragmentViewModel;

/**
 * All view models binded to dagger here
 */

@Module
public class AppViewModelsModule {

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @MapKey
  @interface ViewModelKey {
    Class<? extends ViewModel> value();
  }

  @Provides BaseViewModelFactory viewModelFactory(
      Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
    return new BaseViewModelFactory(providerMap);
  }


  @Provides
  @IntoMap
  @ViewModelKey(HomeFragmentViewModel.class)
  ViewModel providesHomeFragmentViewModel() {
    return new HomeFragmentViewModel();
  }
}
