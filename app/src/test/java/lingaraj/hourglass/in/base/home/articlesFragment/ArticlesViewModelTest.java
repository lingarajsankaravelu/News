package lingaraj.hourglass.in.base.home.articlesFragment;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import java.util.List;
import java.util.Observer;
import java.util.function.Consumer;
import javax.inject.Provider;
import lingaraj.hourglass.in.base.api.NewsAPIRequester;
import lingaraj.hourglass.in.base.api.di.APIRequester;
import lingaraj.hourglass.in.base.api.response.NewsResponse;
import lingaraj.hourglass.in.base.database.AppDatabase;
import lingaraj.hourglass.in.base.database.Article;
import lingaraj.hourglass.in.base.database.ArticlesDao;
import lingaraj.hourglass.in.testutils.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class ArticlesViewModelTest {


  private ArticlesViewModel view_model;
  private ArticleFragmentRepository articles_repo;
  private String error_response_common = "Please Try again!";
  @Mock ArticlesDao articlesDao;
  @Mock AppDatabase database;
  @Mock Provider<NewsAPIRequester> requesterProvider;
  @Mock LiveData<List<Article>> articlesConsumer;
  @Mock MutableLiveData<Boolean> loadingConsumer;
  @Mock MutableLiveData<String> erroConsumer;
  @Mock Consumer<NewsResponse> onSuccessConsumer;
  @Mock Consumer<Throwable> errorConsumer;



 @Rule
  public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

  @Before public void setUp() throws Exception {
    articles_repo = new ArticleFragmentRepository(requesterProvider,database);
    view_model = new ArticlesViewModel(articles_repo,error_response_common);
    when(view_model.liveLoaderStatus()).thenReturn(loadingConsumer);
    when(view_model.liveErrors()).thenReturn(erroConsumer);
    when(view_model.liveArticles()).thenReturn(articlesConsumer);

  }

  @Test
  public void newsLoaded() throws Exception{
    NewsResponse newsResponse = TestUtil.getTestResponse();
    verify(requesterProvider).get().getArticles();
    verify(onSuccessConsumer).accept(newsResponse);
    verifyZeroInteractions();


  }
}