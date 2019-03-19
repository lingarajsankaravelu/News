package lingaraj.hourglass.in.base.di;

import android.app.Activity;

import android.support.v4.app.Fragment;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


import dagger.android.AndroidInjector;
import javax.inject.Inject;
import javax.inject.Provider;
import lingaraj.hourglass.in.base.core.BaseActivity;
import lingaraj.hourglass.in.base.core.BaseFragment;

@ActivityScope
public class ScreenInjector {

    private final Map<Class<? extends Fragment>, Provider<AndroidInjector.Factory<? extends Fragment>>> screenInjectors;
    private final Map<String, AndroidInjector<Fragment>> cache = new HashMap<>();

    @Inject
    ScreenInjector(Map<Class<? extends Fragment>, Provider<AndroidInjector.Factory<? extends Fragment>>> screenInjectors) {
        this.screenInjectors = screenInjectors;
    }

    void inject(Fragment fragment) {
        if (!(fragment instanceof BaseFragment)) {
            throw new IllegalArgumentException("Fragment must extend BaseFragment");
        }

        assert fragment.getArguments() != null;
        String instanceId = fragment.getArguments().getString("instance_id");
        if(instanceId == null) {
            throw new IllegalArgumentException("Fragment must have instance_id in newInstance method");
        }
        if (cache.containsKey(instanceId)) {
            Objects.requireNonNull(cache.get(instanceId)).inject(fragment);
            return;
        }

        //noinspection unchecked
        AndroidInjector.Factory<Fragment> injectorFactory =
                (AndroidInjector.Factory<Fragment>) Objects.requireNonNull(screenInjectors.get(fragment.getClass())).get();
        if(injectorFactory != null) {
            AndroidInjector<Fragment> injector = injectorFactory.create(fragment);
            cache.put(instanceId, injector);
            injector.inject(fragment);
        }
    }

    void clear(Fragment fragment) {
        assert fragment.getArguments() != null;
        AndroidInjector<?> injector = cache.remove(fragment.getArguments().getString("instance_id"));
        if (injector instanceof ScreenComponent) {
            ((ScreenComponent) injector).disposableManager().dispose();
        }
    }

    static ScreenInjector get(Activity activity) {
        if (!(activity instanceof BaseActivity)) {
            throw new IllegalArgumentException("Fragment must be hosted by BaseActivity");
        }
        return ((BaseActivity) activity).getScreenInjector();
    }
}
