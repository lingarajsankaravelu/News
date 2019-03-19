package lingaraj.hourglass.in.base.core;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

public class BaseViewModelFactory implements ViewModelProvider.Factory {

  private final Map<Class<? extends ViewModel>, Provider<ViewModel>> mProviderMap;

  @Inject
  BaseViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
    mProviderMap = providerMap;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    return (T) mProviderMap.get(modelClass).get();
  }
}