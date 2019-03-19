package lingaraj.hourglass.in.base.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import java.util.List;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.di.ActivityScope;
import lingaraj.hourglass.in.base.lifecycle.ActivityLifecycleTask;

@ActivityScope
public class DefaultScreenNavigator extends ActivityLifecycleTask implements ScreenNavigator {

    private FragmentManager fragmentManager;
    private AppCompatActivity activity;

    @Inject
    DefaultScreenNavigator() {

    }

    @Override
    public void onCreate(AppCompatActivity activity) {
        this.activity = activity;
        init(activity.getSupportFragmentManager(), ((ScreenProvider) activity).initialScreen());
    }

    private void init(FragmentManager fragmentManager, Fragment rootScreen) {
        this.fragmentManager = fragmentManager;
        if (fragmentManager.getFragments().size() == 0) {
            fragmentManager.beginTransaction()
                    .replace(R.id.screen_container, rootScreen)
                    .commit();
        }
    }

    @Override
    public boolean pop() {
        return fragmentManager != null && fragmentManager.popBackStackImmediate();
    }


    @Override
    public void onBackPressed() {
        if (fragmentManager != null) {
            pop();
        }
    }


    @Override
    public void finish() {
        activity.finish();
    }


    @Override
    public void onDestroy(AppCompatActivity activity) {
        fragmentManager = null;
    }


    @Override
    public void goToRegister() {
      }

    @Override
    public void goToLogin() {
     }


    @Override
    public void loadFragments(Fragment activeFragment, List<Fragment> hiddenFragmentList) {
       }

    @Override
    public void goToBookExplorePage() {
     }

    @Override
    public void goToTopicExplorePage() {
        }

    @Override
    public void openPostQuestionPage() {
    }

    @Override
    public void openPracticePage() {
    }

    @Override
    public void openRevisionScreen(String revisionId) {
      }

    @Override
    public void openChapterDetails() {
     }

    @Override
    public void searchText(String searchString) {
    }

    @Override
    public void openSearch() {
        }


    @Override
    public void openYoutubePlayActivity(String videoId) {
     }

    @Override
    public void showFragment(Fragment active, Fragment tobeShown) {
     }


    @Override
    public void goHome() {
      }


}
