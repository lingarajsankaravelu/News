package lingaraj.hourglass.in.base.home.articlesFragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.Snackbar;
import android.view.View;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.core.BaseFragment;
import lingaraj.hourglass.in.base.core.BaseViewModelFactory;
import lingaraj.hourglass.in.base.database.Article;
import lingaraj.hourglass.in.base.di.Names;
import lingaraj.hourglass.in.base.ui.ScreenNavigator;
import lingaraj.hourglass.in.base.utils.Constants;
import timber.log.Timber;

public class ArticlesFragment extends BaseFragment {

  @Inject BaseViewModelFactory base_view_model_factory;
  @Inject @Named(Names.NAMED_ERROR_COMMON) String error_message_common;

  @VisibleForTesting
  ArticlesViewModel view_model;

  @Inject ScreenNavigator navigator;

  public static ArticlesFragment newInstance(){
    ArticlesFragment articlesFragment = new ArticlesFragment();
    Bundle bundle = new Bundle();
    bundle.putString(Constants.INSTANCE_ID, UUID.randomUUID().toString());
    articlesFragment.setArguments(bundle);
    return articlesFragment;
  }

  @Override protected int layoutRes() {
    return R.layout.fragment_main;
  }

  @Override protected void onViewBound(View view) {
    super.onViewBound(view);
    view_model = base_view_model_factory.create(ArticlesViewModel.class);
    setLiveDataRecepients();
  }

  private void setLiveDataRecepients() {
    view_model.liveLoaderStatus().observe(this, new Observer<Boolean>() {
      @Override public void onChanged(@Nullable Boolean isLoading) {
        if (isLoading!=null && isLoading){
          showLoader();
        }
        else {
          hideLoader();
        }
      }
    });
    view_model.liveArticles().observe(this, new Observer<List<Article>>() {
      @Override public void onChanged(@Nullable List<Article> articles) {
        if (articles!=null && articles.size()>0){
           updateData(articles);
        }
      }
    });
    view_model.liveErrors().observe(this, error -> showError(error));
  }

  private void updateData(List<Article> articles) {

  }

  public void showLoader(){
    Timber.d("Loader Shown");
  }

  public void hideLoader(){
    Timber.d("Loader hidden");
  }

  public void showError(@Nullable String message){

  }
}
