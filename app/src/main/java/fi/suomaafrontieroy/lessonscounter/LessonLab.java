package fi.suomaafrontieroy.lessonscounter;


import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LessonLab {
    private static LessonLab sLessonLab;

    private Map<UUID, Lesson> mLessons;

    public static LessonLab get(Context context) {
        if (sLessonLab == null) {
            sLessonLab = new LessonLab(context);
        }
        return sLessonLab;
    }

    private LessonLab (Context context) {
        mLessons = new LinkedHashMap<>();
    }

    public void addLesson(Lesson l) {
        mLessons.put(l.getId(), l);
    }

    public List<Lesson> getLessons() {
        return new ArrayList<>(mLessons.values());
    }

    public Lesson getLesson(UUID id) {
        return mLessons.get(id);
    }
}
