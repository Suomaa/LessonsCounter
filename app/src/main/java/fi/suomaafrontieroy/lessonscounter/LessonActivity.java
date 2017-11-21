package fi.suomaafrontieroy.lessonscounter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class LessonActivity extends SingleFragmentActivity {

    private static final String EXTRA_LESSON_ID = "fi.suomaafrontieroy.lessonscounter.lesson_id";

    @Override
    protected Fragment createFragment() {
        UUID lessonId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_LESSON_ID);
        return LessonFragment.newInstance(lessonId);
    }

    public static Intent newIntent(Context packageContext, UUID lessonID) {
        Intent intent = new Intent(packageContext, LessonActivity.class);
        intent.putExtra(EXTRA_LESSON_ID, lessonID);
        return intent;
    }
}
