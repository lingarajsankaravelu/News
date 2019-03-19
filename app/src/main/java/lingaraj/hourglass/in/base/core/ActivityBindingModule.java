package lingaraj.hourglass.in.base.core;


import android.app.Activity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import lingaraj.hourglass.in.base.home.HomeActivity;
import lingaraj.hourglass.in.base.home.di.HomeActivityComponent;

/**
 * All  activities component get binded to dagger map here.
 */
@Module(subcomponents = {
    HomeActivityComponent.class
})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivityInjector(HomeActivityComponent.Builder builder);

}

