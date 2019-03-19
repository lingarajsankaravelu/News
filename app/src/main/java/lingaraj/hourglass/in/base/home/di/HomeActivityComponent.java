package lingaraj.hourglass.in.base.home.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import lingaraj.hourglass.in.base.core.AppViewModelsModule;
import lingaraj.hourglass.in.base.di.ActivityScope;
import lingaraj.hourglass.in.base.home.HomeActivity;
import lingaraj.hourglass.in.base.ui.ActivityViewInterceptorModule;
import lingaraj.hourglass.in.base.ui.NavigationModule;

@ActivityScope
@Subcomponent(modules = { AppViewModelsModule.class , ActivityViewInterceptorModule.class,HomeBindingModule.class,
    NavigationModule.class
})
public abstract interface HomeActivityComponent extends AndroidInjector<HomeActivity> {

  @Subcomponent.Builder abstract class Builder extends AndroidInjector.Builder<HomeActivity>{

    @Override public void seedInstance(HomeActivity instance) {

    }
  }

}
