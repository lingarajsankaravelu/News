package lingaraj.hourglass.in.base.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.io.Serializable;
import lingaraj.hourglass.in.base.api.response.Source;
import lingaraj.hourglass.in.base.utils.Constants;

@Entity(tableName = Article.TABLE_NAME)
public class Article {

  public static final String TABLE_NAME  ="ARTICLES";


  private String Author;

  @PrimaryKey
  private String title;

  private String description;
  private String url;
  private String urlToImage;
  private String publishedAt;

  public String getAuthor() {
    return Author;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getUrl() {
    return url;
  }

  public String getUrlToImage() {
    return urlToImage;
  }

  public String getPublishedAt() {
    return publishedAt;
  }
}
