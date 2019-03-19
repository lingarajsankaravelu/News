package lingaraj.hourglass.in.base.ui;

import android.support.v4.app.Fragment;

import java.util.List;

public interface ScreenNavigator {

    boolean pop();

    void onBackPressed();

    void finish();

    void goToRegister();

    void goToLogin();

    void goHome();

    void showFragment(Fragment active, Fragment tobeHidden);

    void loadFragments(Fragment activeFragment, List<Fragment> hiddenFragmentList);

    void goToBookExplorePage();

    void openSearch();

//    void closeSearch();

    void openYoutubePlayActivity(String videoId);

    void goToTopicExplorePage();

    void openPostQuestionPage();

    void openPracticePage();

    void openRevisionScreen(String revisionId);

    void openChapterDetails();

    void searchText(String searchString);
}
