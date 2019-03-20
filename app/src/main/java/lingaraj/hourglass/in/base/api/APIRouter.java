package lingaraj.hourglass.in.base.api;

import io.reactivex.Single;
import lingaraj.hourglass.in.base.api.response.NewsResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIRouter {

  @GET("/v2/top-headlines")
  Single<NewsResponse> getResults(@Query("country") String country,@Query("apiKey") String apiKey);
}
