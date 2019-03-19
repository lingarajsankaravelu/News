package lingaraj.hourglass.in.base.api.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import lingaraj.hourglass.in.base.api.APIRouter;
import retrofit2.Retrofit;

@Module
public abstract class APIModule {
  @Singleton
  @Provides
  static APIRouter provideAPIRouter(Retrofit retrofit){
    return retrofit.create(APIRouter.class);
  }
}
