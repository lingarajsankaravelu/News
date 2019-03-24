package lingaraj.hourglass.in.base.home.articlesFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.database.Article;
import lingaraj.hourglass.in.base.utils.General;
import lingaraj.hourglass.in.base.utils.Storage;
import timber.log.Timber;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {


  private static final int TYPE_NEWS_DEFAULT = 100;
  private static final int TYPE_NEWS_TRENDING = 200;
  private final ArticlesFragment.ItemClick item_click;
  private Context mContext = null;
  private List<Article> articles = new ArrayList<Article>();

  public ArticlesAdapter(ArticlesFragment.ItemClick clickListener){
    this.item_click = clickListener;
  }

  @NonNull @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
     int chosen_layout = viewType==TYPE_NEWS_DEFAULT?R.layout.card_default:R.layout.card_trending;
     if (mContext==null){
       mContext = viewGroup.getContext();
     }
     View view = LayoutInflater.from(mContext).inflate(chosen_layout,viewGroup,false);
     view.setOnClickListener(this.item_click);
     return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     Article article = articles.get(position);
     holder.title.setText(article.getTitle());
     holder.publisher.setText(article.getAuthor());
     int dimension = 0;

     String time_stamp = article.getPublishedAt();
     if (time_stamp==null){
       holder.hour.setVisibility(View.GONE);
     }
     else {
       holder.hour.setText(mContext.getString(R.string.hour, General.getHourDifference(time_stamp)));
       holder.hour.setVisibility(View.VISIBLE);
     }
    if (holder.getItemViewType()==TYPE_NEWS_DEFAULT){
      dimension = 50;
    }
    else {
      dimension = 100;
    }
    String url = article.getUrlToImage();
    String file_name = Storage.getFileNameWithExtension(url);
    String local_storage_path = Storage.getLocalFilePath(file_name,mContext);
    String picasso_url = null;
    if (local_storage_path!=null){
      picasso_url = local_storage_path;
    }
    else {
      picasso_url = url;
    }
    Picasso.with(mContext)
        .load(Uri.parse(url))
        .networkPolicy(NetworkPolicy.OFFLINE)
        .into(holder.image_view, new Callback() {
          @Override
          public void onSuccess() {

          }

          @Override
          public void onError() {
            // Try again online if cache failed
            Picasso.with(mContext)
                .load(Uri.parse(url))
               /// .placeholder(R.drawable.user_placeholder)
              //  .error(R.drawable.user_placeholder_error)
                .into(holder.image_view);
          }
        });
   /* Target target = new Target() {
      @Override
      public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
        Timber.d("Downloaded");
        Article record = articles.get(holder.getAdapterPosition());
        saveBitmap(bitmap,record.getUrlToImage());
        holder.image_view.setImageBitmap(bitmap);
      }

      @Override
      public void onBitmapFailed(Drawable errorDrawable) {
        Timber.d("Bitmap loading error:");
      }

      @Override
      public void onPrepareLoad(Drawable placeHolderDrawable) {
        Timber.d("On prepare load");
      }
    };
    Picasso.Builder builder = new Picasso.Builder(mContext);
    builder.listener(new Picasso.Listener() {
      @Override
      public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
        Timber.d("Image Load Failed"+exception.toString());
      }
    });

    builder.build().load(picasso_url).resize(dimension,dimension).into(target);
    holder.image_view.setTag(target);*/
  }

  private void saveBitmap(Bitmap bitmap, String urlToImage) {
    String file_name = Storage.getFileNameWithExtension(urlToImage);
    if (Storage.getLocalFilePath(file_name,mContext)==null){
       Storage.saveBitmap(bitmap,mContext,urlToImage).
           subscribeOn(Schedulers.io()).subscribe();
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (position%4==0){
      return TYPE_NEWS_TRENDING;
    }
    else {
     return TYPE_NEWS_DEFAULT;
    }
  }

  @Override public int getItemCount() {
    return articles.size();
  }

  public void addData(List<Article> data) {
    data.addAll(articles);
    articles = data;
    notifyDataSetChanged();
    Timber.d("Articles data updated");
  }

  public String getWebLink(int position) {
    return articles.get(position).getUrl();
  }

  public class ViewHolder  extends RecyclerView.ViewHolder{

    @BindView(R.id.image)
    ImageView image_view;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.publisher)
    TextView publisher;

    @BindView(R.id.hour)
    TextView hour;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }
}
