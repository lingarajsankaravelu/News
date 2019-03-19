package lingaraj.hourglass.in.base.ui;

import dagger.Module;
import dagger.Provides;
import lingaraj.hourglass.in.base.ui.ActivityViewInterceptor;

@Module
public abstract class ActivityViewInterceptorModule {

    @Provides
    static ActivityViewInterceptor provideActivityViewInterceptor() {
        return ActivityViewInterceptor.DEFAULT;
    }
}
