package lingaraj.hourglass.in.base.api.di;

import io.reactivex.Single;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.api.APIRouter;
import lingaraj.hourglass.in.base.api.response.NewsResponse;
import lingaraj.hourglass.in.base.api.response.Status;
import lingaraj.hourglass.in.base.utils.Constants;

public class APIRequester {
  private final APIRouter apiRouter;

  @Inject
  APIRequester(APIRouter router){
    this.apiRouter = router;
  }

  public Single<NewsResponse> getResult(){
    return apiRouter.getResults(Constants.COUNTRY_INDIA,Constants.API_KEY);
  }
}
