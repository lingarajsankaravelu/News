package lingaraj.hourglass.in.base.api.response;

import java.io.Serializable;
import java.util.List;
import lingaraj.hourglass.in.base.database.Article;

public class NewsResponse implements Serializable {
  private String status;
  private int totalResults;
  private List<Article> articles;

  public String getStatus() {
    return status;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public List<Article> getArticles() {
    return articles;
  }
}
