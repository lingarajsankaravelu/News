package lingaraj.hourglass.in.base.api;

import io.reactivex.Single;
import lingaraj.hourglass.in.base.api.response.Status;
import retrofit2.http.GET;

public interface APIRouter {

  @GET("/results")
  Single<Status> getResults(String id);
}
