package lingaraj.hourglass.in.base.ui;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import lingaraj.hourglass.in.base.lifecycle.ActivityLifecycleTask;

@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifecycleTask bindScreenNavigatorTask(DefaultScreenNavigator screenNavigator);
}
