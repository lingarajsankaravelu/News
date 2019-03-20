package lingaraj.hourglass.in.base.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface ArticlesDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insertArticles(List<Article> articles);

  @Query("SELECT * FROM "+Article.TABLE_NAME)
  MutableLiveData<List<Article>> getArticles();

}
