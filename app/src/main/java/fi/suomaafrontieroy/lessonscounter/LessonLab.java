package fi.suomaafrontieroy.lessonscounter;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LessonLab {
    private static LessonLab sLessonLab;

    private List<Lesson> mLessons;

    public static LessonLab get(Context context) {
        if (sLessonLab == null) {
            sLessonLab = new LessonLab(context);
        }
        return sLessonLab;
    }

    private LessonLab (Context context) {
        //mAppContext = appContext;
        mLessons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Lesson lesson = new Lesson();
            lesson.setTitle("Crime #" + i);
            mLessons.add(lesson);
        }
    }

    public List<Lesson> getCrimes() {
        return mLessons;
    }

    public Lesson getCrime(UUID id) {
        for (Lesson crime : mLessons) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
