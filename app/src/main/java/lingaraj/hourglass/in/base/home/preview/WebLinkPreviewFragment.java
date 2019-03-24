package lingaraj.hourglass.in.base.home.preview;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import java.util.UUID;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.core.BaseFragment;
import lingaraj.hourglass.in.base.utils.Constants;

public class WebLinkPreviewFragment extends BaseFragment {

  public class Keys {
    static final String WEBLINK = "WEB_LINK";
  }

  @BindView(R.id.web_view)
  WebView webView;
  public static final String BACKSTACK_TAG = "WEB_PREVIEW_FRAG";

  @Override protected int layoutRes() {
    return R.layout.fragment_webview;
  }

  public static WebLinkPreviewFragment newInstance(String webLink){
    WebLinkPreviewFragment webLinkPreviewFragment = new WebLinkPreviewFragment();
    Bundle bundle = new Bundle();
    bundle.putString(Constants.INSTANCE_ID, UUID.randomUUID().toString());
    bundle.putString(Keys.WEBLINK,webLink);
    webLinkPreviewFragment.setArguments(bundle);
    return webLinkPreviewFragment;
  }

  @Override protected void onViewBound(View view) {
    super.onViewBound(view);
    Bundle bundle = getArguments();
     String  web_url = null;
     if (bundle!=null){
      web_url = bundle.getString(Keys.WEBLINK);
     }

     if (web_url==null) {
       throw new NullPointerException("WEB LINK CANNOT BE NULL");
     }
     else {
       webView.loadUrl(web_url);
       webView.getSettings().setJavaScriptEnabled(true);

       // Set WebView client
       webView.setWebChromeClient(new WebChromeClient());

       webView.setWebViewClient(new WebViewClient() {

         @Override
         public boolean shouldOverrideUrlLoading(WebView view, String url) {
           view.loadUrl(url);
           return true;
         }
       });
     }


    }
}
