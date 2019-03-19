package lingaraj.hourglass.in.base.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.di.Injector;
import lingaraj.hourglass.in.base.lifecycle.ScreenLifecycleTask;
import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment extends Fragment {

    @Inject
    Set<ScreenLifecycleTask> screenLifecycleTasks;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        Injector.inject(this);
        super.onAttach(context);
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        disposables.addAll(subscriptions());
        for (ScreenLifecycleTask task : screenLifecycleTasks) {
            task.onEnterScope(view.getRootView());
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        disposables.clear();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        for (ScreenLifecycleTask task : screenLifecycleTasks) {
            task.onExitScope();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (ScreenLifecycleTask task : screenLifecycleTasks) {
            task.onDestroy();
        }
        if (!Objects.requireNonNull(getActivity()).isChangingConfigurations()) {
            Injector.clearComponent(this);
        }
    }

    protected void onViewBound(View view) {

    }

    protected Disposable[] subscriptions() {
        return new Disposable[0];
    }

    @LayoutRes
    protected abstract int layoutRes();
}
