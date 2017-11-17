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
        mLessons = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            Lesson lesson = new Lesson();
            lesson.setTitle("Lesson #" + i);
            mLessons.add(lesson);
        }
    }

    public List<Lesson> getLessons() {
        return mLessons;
    }

    public Lesson getLesson(UUID id) {
        for (Lesson lesson : mLessons) {
            if (lesson.getId().equals(id)) {
                return lesson;
            }
        }
        return null;
    }
}
