package fi.suomaafrontieroy.lessonscounter;

import android.support.v4.app.Fragment;

public class LessonListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LessonListFragment();
    }
}
