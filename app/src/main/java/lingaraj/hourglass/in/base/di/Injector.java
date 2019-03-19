package lingaraj.hourglass.in.base.di;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class Injector {

    private Injector() {

    }

    public static void inject(Activity activity) {
        ActivityInjector.get(activity).inject(activity);
    }

    public static void clearComponent(Activity activity) {
        ActivityInjector.get(activity).clear(activity);
    }

    public static void inject(Fragment fragment) {
        ScreenInjector.get(fragment.getActivity()).inject(fragment);
    }

    public static void clearComponent(Fragment fragment) {
        ScreenInjector.get(fragment.getActivity()).clear(fragment);
    }
}
