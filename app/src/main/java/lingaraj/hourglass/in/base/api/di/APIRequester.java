package lingaraj.hourglass.in.base.api.di;

import io.reactivex.Single;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.api.APIRouter;
import lingaraj.hourglass.in.base.api.response.Status;

public class APIRequester {
  private final APIRouter apiRouter;

  @Inject
  APIRequester(APIRouter router){
    this.apiRouter = router;
  }

  public Single<Status> getResult(){
    return apiRouter.getResults(null);
  }
}
