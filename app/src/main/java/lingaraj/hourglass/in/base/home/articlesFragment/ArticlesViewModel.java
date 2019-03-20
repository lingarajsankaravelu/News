package lingaraj.hourglass.in.base.home.articlesFragment;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import lingaraj.hourglass.in.base.database.Article;
import lingaraj.hourglass.in.base.di.Names;

public class ArticlesViewModel extends ViewModel {

  private final ArticleFragmentRepository repository;

  private final MutableLiveData<String> error = new MutableLiveData<>();
  private final MutableLiveData<Boolean> loader = new MutableLiveData<>();
  private   LiveData<List<Article>> articleLiveData;
  private final String common_error_response;
  MutableLiveData<String> liveErrors(){
    return this.error;
  }
  LiveData<List<Article>> liveArticles(){
    return this.articleLiveData;
  }

  MutableLiveData<Boolean> liveLoaderStatus(){
    return this.loader;
  }



  public ArticlesViewModel(ArticleFragmentRepository articleFragmentRepository,@Named(Names.NAMED_ERROR_COMMON) String errorResponseCommon){
    repository = articleFragmentRepository;
    common_error_response = errorResponseCommon;
    loader.postValue(true);
    this.articleLiveData = repository.getArticles();
  }

  @SuppressLint("CheckResult")
  public void refresh(){
    repository.refreshArticles()
        .subscribeOn(Schedulers.io())
        .doOnSubscribe(__->loader.postValue(true))
        .doOnError(__->error.postValue(common_error_response))
        .doOnSuccess(__->loader.postValue(false))
        .subscribe();
  }










}
