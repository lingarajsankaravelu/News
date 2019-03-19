package lingaraj.hourglass.in.base.core;

import dagger.Component;
import javax.inject.Singleton;
import lingaraj.hourglass.in.base.BaseApp;
import lingaraj.hourglass.in.base.api.di.APIModule;

/**
 * All singleton Modules goes here i.e application,ActivityBindingModule, database and network module
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
     NetworkModule.class,APIModule.class

})
public interface ApplicationComponent {
 void inject(BaseApp application);
}
