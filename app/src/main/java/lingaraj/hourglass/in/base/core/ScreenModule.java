package lingaraj.hourglass.in.base.core;

import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;
import lingaraj.hourglass.in.base.di.ScreenScope;
import lingaraj.hourglass.in.base.lifecycle.DisposableManager;
import lingaraj.hourglass.in.base.lifecycle.ScreenLifecycleTask;

@Module
public abstract class ScreenModule {

    @ScreenScope
    @Provides
    static DisposableManager provideDisposableManager() {
        return new DisposableManager();
    }

    @Multibinds
    abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}
