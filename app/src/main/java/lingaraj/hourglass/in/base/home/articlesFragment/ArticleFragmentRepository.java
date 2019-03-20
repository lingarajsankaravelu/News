package lingaraj.hourglass.in.base.home.articlesFragment;

import android.arch.lifecycle.MutableLiveData;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import lingaraj.hourglass.in.base.api.NewsAPIRequester;
import lingaraj.hourglass.in.base.api.response.NewsResponse;
import lingaraj.hourglass.in.base.database.AppDatabase;
import lingaraj.hourglass.in.base.database.Article;

public class ArticleFragmentRepository {

  private final AppDatabase database;
  private final Provider<NewsAPIRequester> api_requester;


  @Inject
  ArticleFragmentRepository(Provider<NewsAPIRequester> apiRequesterProvider,AppDatabase appDatabase){
    this.database = appDatabase;
    this.api_requester = apiRequesterProvider;
  }


  MutableLiveData<List<Article>> getArticles(){
    return database.articlesDao().getArticles();
  }


  Single<NewsResponse> refreshArticles(){
     return api_requester.get().getArticles().subscribeOn(Schedulers.io()).map(newsResponse -> {
       if (newsResponse!=null && newsResponse.getArticles().size()>0){ ;
         Completable.fromAction(new Action() {
           @Override public void run() throws Exception {
               database.articlesDao().insertArticles(newsResponse.getArticles());
           }
         }).subscribeOn(Schedulers.computation()).subscribe();
       }
       return newsResponse;
     });
  }


}
