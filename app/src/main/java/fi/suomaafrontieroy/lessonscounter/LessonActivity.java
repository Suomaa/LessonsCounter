package fi.suomaafrontieroy.lessonscounter;

import android.support.v4.app.Fragment;

public class LessonActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LessonFragment();
    }
}
