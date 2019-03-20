package lingaraj.hourglass.in.base.api;

import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.api.response.NewsResponse;
import lingaraj.hourglass.in.base.database.Article;
import lingaraj.hourglass.in.base.utils.Constants;

public class NewsAPIRequester {
  private final APIRouter router;

  @Inject
  NewsAPIRequester(APIRouter apiRouter){
    this.router = apiRouter;
  }

  public Single<NewsResponse> getArticles(){
    return router.getResults(Constants.COUNTRY_INDIA,Constants.API_KEY);
  }
}
