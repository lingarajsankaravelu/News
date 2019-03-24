package lingaraj.hourglass.in.base.home.preview.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import lingaraj.hourglass.in.base.core.ScreenModule;
import lingaraj.hourglass.in.base.di.ScreenComponent;
import lingaraj.hourglass.in.base.di.ScreenScope;
import lingaraj.hourglass.in.base.home.preview.WebLinkPreviewFragment;

@ScreenScope
@Subcomponent(modules = { ScreenModule.class })
public abstract interface PreviewComponent extends ScreenComponent<WebLinkPreviewFragment> {

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<WebLinkPreviewFragment>{

  }

}
