package fi.suomaafrontieroy.lessonscounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

public class LessonFragment extends Fragment {

    private static final String ARG_LESSON_ID = "lesson_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private static final String DIALOG_TIME = "DialogTime";
    private static final int REQUEST_TIME = 1;

    private Lesson mLesson;
    private EditText mTitleField;
    private Button mDateButton;
    private Button mTimeButton;

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
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mLesson.getDate());
                dialog.setTargetFragment(LessonFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mTimeButton = (Button)v.findViewById(R.id.lesson_time);
        updateTime();
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment
                        .newInstance(mLesson.getDate());
                dialog.setTargetFragment(LessonFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mLesson.setDate(date);
            updateDate();
        }
        if (requestCode == REQUEST_TIME) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mLesson.setDate(date);
            updateTime();
        }
    }

    private void updateDate() {
        mDateButton.setText(android.text.format.DateFormat.format("dd MMMM yyyy (EEEE)", mLesson.getDate()));
    }

    private void updateTime() {
        mTimeButton.setText(android.text.format.DateFormat.format("HH:mm", mLesson.getDate()));
    }
}
