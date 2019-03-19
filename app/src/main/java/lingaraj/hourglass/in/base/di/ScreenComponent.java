package lingaraj.hourglass.in.base.di;

import dagger.android.AndroidInjector;
import lingaraj.hourglass.in.base.lifecycle.DisposableManager;

public interface ScreenComponent<T> extends AndroidInjector<T> {

    @ScreenScope
    DisposableManager disposableManager();
}
