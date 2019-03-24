package lingaraj.hourglass.in.base.utils;

import android.support.annotation.NonNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import timber.log.Timber;

public class General {

  public static int getHourDifference(@NonNull String timeStamp){
      try {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = inputFormat.parse(timeStamp);
        Calendar calendar = Calendar.getInstance();
        long current_time_in_milliseconds = calendar.getTimeInMillis();
        calendar.setTime(date);
        return (int) Math.abs(current_time_in_milliseconds - calendar.getTimeInMillis());
      }
      catch (Exception e) {
        Timber.d(e.toString());
        return 0;
      }
  }
}
