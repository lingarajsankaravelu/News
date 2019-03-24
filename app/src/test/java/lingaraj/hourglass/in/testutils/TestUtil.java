package lingaraj.hourglass.in.testutils;

import com.google.gson.Gson;
import java.io.InputStream;
import lingaraj.hourglass.in.base.api.response.NewsResponse;

public class TestUtil {

  public static NewsResponse getTestResponse(){
    try {
      InputStream inputStream = TestUtil.class.getClassLoader().getResourceAsStream(
          "mocks/articles.json");
      int size = inputStream.available();
      byte[] buffer = new byte[size];
      inputStream.read(buffer);
      inputStream.close();
      String articles_json = new String(buffer, "UTF-8");
      Gson gson = new Gson();
      return gson.fromJson(articles_json,NewsResponse.class);
    }
    catch (Exception e){
      return null;
    }

  }



}
