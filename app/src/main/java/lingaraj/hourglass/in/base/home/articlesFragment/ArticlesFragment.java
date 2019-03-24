package lingaraj.hourglass.in.base.home.articlesFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.core.BaseFragment;
import lingaraj.hourglass.in.base.core.BaseViewModelFactory;
import lingaraj.hourglass.in.base.database.Article;
import lingaraj.hourglass.in.base.di.Names;
import lingaraj.hourglass.in.base.home.preview.WebLinkPreviewFragment;
import lingaraj.hourglass.in.base.ui.ScreenNavigator;
import lingaraj.hourglass.in.base.utils.Constants;
import timber.log.Timber;

public class ArticlesFragment extends BaseFragment {

  @Inject BaseViewModelFactory base_view_model_factory;
  @Inject @Named(Names.NAMED_ERROR_COMMON) String error_message_common;

  @VisibleForTesting
  ArticlesViewModel view_model;


  @Inject ScreenNavigator navigator;

  @BindView(R.id.loader) ProgressBar loader;

  @VisibleForTesting
  @BindView(R.id.articles)
  RecyclerView mrecyclerview;

  @BindView(R.id.retry_container) LinearLayout retry_container;

  @BindView(R.id.retry_view) ImageView retry_view;

  @BindView(R.id.message) TextView message_view;

  @BindView(R.id.screen_container) CoordinatorLayout screen_container;

  private ArticlesAdapter madapter;



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
    Context mcontex = view.getContext();
    view_model = base_view_model_factory.create(ArticlesViewModel.class);
    madapter = new ArticlesAdapter(new ItemClick());
    mrecyclerview.setLayoutManager(new LinearLayoutManager(mcontex,LinearLayoutManager.VERTICAL,false));
    mrecyclerview.setAdapter(madapter);
    setLiveDataRecepients();
    view_model.refresh();
  }

  private void setLiveDataRecepients() {
    view_model.liveLoaderStatus().observe(this, isLoading -> {
      if (isLoading!=null && isLoading){
        showLoader();
      }
      else {
        hideLoader();
      }
    });
    view_model.liveArticles().observe(this, articles -> {
      if (articles!=null && articles.size()>0){
         showContent();
         updateData(articles);
         hideLoader();
      }
    });
    view_model.liveErrors().observe(this, this::showError);
  }

  private void showContent() {
    if (mrecyclerview.getVisibility()==View.INVISIBLE){
      mrecyclerview.setVisibility(View.VISIBLE);
      Timber.d("Showing content");
    }
  }

  private void updateData(List<Article> articles) {
    if(madapter!=null){
      madapter.addData(articles);
    }
  }

  public void showLoader(){
    loader.setVisibility(View.VISIBLE);
    Timber.d("Loader Shown");
  }

  public void hideLoader(){
    loader.setVisibility(View.GONE);
    Timber.d("Loader hidden");
  }

  public void showRetry(){
    retry_container.setVisibility(View.VISIBLE);
    Timber.d("Showing retry");
  }

  public void hideRetry(){
    retry_container.setVisibility(View.GONE);
  }
  public void showError(@Nullable String message){
     hideLoader();
     if (message==null){
       message = error_message_common;
     }
     if (madapter.getItemCount()>0){
       //displaying cached data
       Snackbar.make(screen_container,error_message_common,Snackbar.LENGTH_SHORT).show();
     }
     else {
       message_view.setText(message);
       retry_container.setVisibility(View.VISIBLE);
     }

  }

  public class ItemClick implements View.OnClickListener{

    @Override public void onClick(View v) {
     Timber.d("Item Clicked");
     int position = mrecyclerview.getChildAdapterPosition(v);
     String url = madapter.getWebLink(position);

     navigator.showFragment(WebLinkPreviewFragment.newInstance(url),WebLinkPreviewFragment.BACKSTACK_TAG);

    }
  }

  @OnClick(R.id.retry_view)
  public void retryClick(){
    view_model.refresh();
  }
}
