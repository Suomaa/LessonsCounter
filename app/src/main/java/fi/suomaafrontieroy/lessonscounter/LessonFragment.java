package fi.suomaafrontieroy.lessonscounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import java.text.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

public class LessonFragment extends Fragment {

    private static final String ARG_LESSON_ID = "lesson_id";

    private Lesson mLesson;
    private EditText mTitleField;
    private Button mDateButton;

    public static LessonFragment newInstance(UUID lessonId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_LESSON_ID, lessonId);

        LessonFragment fragment = new LessonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInsatnceState) {
        super.onCreate(savedInsatnceState);
        UUID lessonId = (UUID) getArguments().getSerializable(ARG_LESSON_ID);
        mLesson = LessonLab.get(getActivity()).getLesson(lessonId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lesson, container, false);

        mTitleField = (EditText)v.findViewById(R.id.lesson_title);
        mTitleField.setText(mLesson.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence c, int i, int i1, int i2) {
                mLesson.setTitle(c.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.lesson_date);
        mDateButton.setText(mLesson.getDate().toString());
        mDateButton.setEnabled(false);

        return v;
    }

}
