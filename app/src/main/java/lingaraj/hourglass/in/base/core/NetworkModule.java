package lingaraj.hourglass.in.base.core;

import android.net.Uri;
import dagger.Module;
import dagger.Provides;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import lingaraj.hourglass.in.base.BuildConfig;
import lingaraj.hourglass.in.base.utils.Constants;
import okhttp3.Call;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {



  @Provides
  @Singleton
  static OkHttpClient provideHttpClient(HttpLoggingInterceptor logging) {
    ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
        .tlsVersions(TlsVersion.TLS_1_0)
        .cipherSuites(
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
            CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA,
            CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA)
        .build();
      return new OkHttpClient.Builder().addInterceptor(logging) .connectionSpecs(Collections.singletonList(spec))
        .protocols(Arrays.asList(Protocol.HTTP_1_1))
        .readTimeout(3, TimeUnit.MINUTES)
        .connectTimeout(3, TimeUnit.MINUTES).build();

  }

  @Provides
  @Singleton
  static HttpLoggingInterceptor providesLoggingInterceptor(){
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    if (BuildConfig.DEBUG) {
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    }
    else {
      logging.setLevel(HttpLoggingInterceptor.Level.NONE);
    }
    return logging;


  }


  @Provides
  @Singleton
  static Call.Factory providesCallFactory() {
    return new OkHttpClient.Builder().build();
  }


  @Provides
  @Singleton
  static Retrofit providesRetrofit(OkHttpClient okHttpClient,Call.Factory  callFactory){
    return new Retrofit.Builder()
        .callFactory(callFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(Uri.parse(Constants.BASE_URL).toString())
        .build();

  }



}
