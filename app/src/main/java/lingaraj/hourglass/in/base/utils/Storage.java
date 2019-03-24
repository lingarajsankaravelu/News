package lingaraj.hourglass.in.base.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.Observable;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import io.reactivex.Single;
import java.io.File;
import java.io.FileOutputStream;
import timber.log.Timber;

public class Storage {


  @Nullable
  public static String getLocalFilePath(String filename, Context mContext){
    ContextWrapper context_wrapper = new ContextWrapper(mContext);
    String absolute_path  = context_wrapper.getFilesDir().getAbsolutePath();
    File file = new File(absolute_path,filename);
    if (file.exists()){
      return file.getAbsolutePath();
    }
    else {
      return null;
    }
  }

  public static String getFileNameWithExtension(String image_url) {
    Timber.d("Splitting_image_url"+image_url);
    String splitted_image_name = image_url.substring(image_url.lastIndexOf("/")+1);
    Timber.d("Image Name with extension" + splitted_image_name.trim());
    return  splitted_image_name;
  }

  public static Single<Boolean> saveBitmap(Bitmap bitmap,Context mcontext,String urlImage){
    ContextWrapper contextWrapper =new ContextWrapper(mcontext);
    boolean file_saved = false;
    String fileName = getFileNameWithExtension(urlImage);
    File local_file_path = new File(contextWrapper.getFilesDir(),fileName);
    try {
        FileOutputStream fos = new FileOutputStream(local_file_path);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        fos.close();
        Timber.d("Image Saved to internal storage:"+local_file_path.getAbsolutePath());
        file_saved = true;
    }
    catch (Exception e){
      Timber.d(e.toString());
    }

    return Single.just(file_saved);
  }

}
