package lingaraj.hourglass.in.base;

import android.app.Application;
import android.content.Context;
import com.jakewharton.threetenabp.AndroidThreeTen;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.core.ApplicationComponent;
import lingaraj.hourglass.in.base.core.ApplicationModule;
import lingaraj.hourglass.in.base.core.DaggerApplicationComponent;
import lingaraj.hourglass.in.base.di.ActivityInjector;
import timber.log.Timber;

public class BaseApp  extends Application {
  private ApplicationComponent component;
  @Inject ActivityInjector activityInjector;
  private String currentImage;
  private String currentDiagram;

  @Override public void onCreate() {
    super.onCreate();

    if(BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
    AndroidThreeTen.init(this);
    component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    component.inject(this);

  }


  public ActivityInjector getActivityInjector() {
    return activityInjector;
  }


}
