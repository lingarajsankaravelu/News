package lingaraj.hourglass.in.base.home.articlesFragment.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import lingaraj.hourglass.in.base.core.ScreenModule;
import lingaraj.hourglass.in.base.di.ScreenComponent;
import lingaraj.hourglass.in.base.di.ScreenScope;
import lingaraj.hourglass.in.base.home.articlesFragment.ArticlesFragment;

@ScreenScope
@Subcomponent(modules = { ScreenModule.class,HomeFragmentScreenModule.class,})
public interface HomeFragmentComponent extends ScreenComponent<ArticlesFragment> {

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<ArticlesFragment>{

  }

}
