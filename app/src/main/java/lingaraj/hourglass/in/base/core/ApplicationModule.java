package lingaraj.hourglass.in.base.core;

import android.content.Context;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import lingaraj.hourglass.in.base.BaseApp;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.di.Names;

@Module
public class ApplicationModule {

    private final BaseApp application;
    private final String errorMessageCommon;
    private final String messageCommonLoading;
    private final AppSharedPreference appSharedPreference;
    private final Gson gson;


    public ApplicationModule(BaseApp application) {
        this.application = application;
        this.appSharedPreference = new AppSharedPreference(application);
        this.errorMessageCommon = application.getString(R.string.error_message_common);
        this.messageCommonLoading = application.getString(R.string.message_common_loading);
        this.gson = new Gson();
    }

    @Provides
    Gson providesGson(){
        return this.gson;
    }

    @Provides
    AppSharedPreference providesAppSharedPreference(){ return this.appSharedPreference; }

    @Provides
    Context provideApplicationContext() {
        return application;
    }

    @Provides @Named(Names.NAMED_ERROR_COMMON) String providesErrorMessageCommon(){
        return this.errorMessageCommon;
    }

    @Provides @Named(Names.NAMED_LOADING) String providesMessageLoading(){
        return this.messageCommonLoading;
    }

}
