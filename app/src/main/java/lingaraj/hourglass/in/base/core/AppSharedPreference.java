package lingaraj.hourglass.in.base.core;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.utils.Constants;

public class AppSharedPreference {

  private SharedPreferences mSharedPreference;
  private String KEY_TOKEN = "SERVER_TOKEN";
  public AppSharedPreference(Context applicationContext){
  if (applicationContext instanceof Activity){
    throw new IllegalStateException("Need Application Context");
  }
  else {
    mSharedPreference = applicationContext.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
  }
  }

  public void setToken(String token){
    mSharedPreference.edit().putString(KEY_TOKEN,token).apply();

  }

  public String getToken(){
   return mSharedPreference.getString(KEY_TOKEN,null);
  }




}
