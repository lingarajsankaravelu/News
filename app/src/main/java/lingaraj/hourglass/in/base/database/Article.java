package lingaraj.hourglass.in.base.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lingaraj.hourglass.in.base.api.response.Source;
import lingaraj.hourglass.in.base.utils.Constants;

@Entity(tableName = Article.TABLE_NAME)
public class Article {

  public static final String TABLE_NAME  ="ARTICLES";

  @PrimaryKey(autoGenerate = true)
  private long id;

  private String author;

  private String title;

  private String description;
  private String url;
  private String urlToImage;
  private String publishedAt;

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setUrlToImage(String urlToImage) {
    this.urlToImage = urlToImage;
  }

  public void setPublishedAt(String publishedAt) {
    this.publishedAt = publishedAt;
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
