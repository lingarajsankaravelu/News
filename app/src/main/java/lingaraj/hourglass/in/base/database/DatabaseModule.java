package lingaraj.hourglass.in.base.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class DatabaseModule {
  @Provides
  @Singleton
  static AppDatabase provideAppDatabase(Context context){
    return Room.databaseBuilder(context,AppDatabase.class,"news-room-database").allowMainThreadQueries().build();
  }
}
