package fi.suomaafrontieroy.lessonscounter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LessonListFragment extends Fragment {

    private RecyclerView mLessonRecyclerView;
    private LessonAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_list, container,
                false);
        mLessonRecyclerView = (RecyclerView) view
                .findViewById(R.id.lesson_recycler_view);
        mLessonRecyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        LessonLab lessonLab = LessonLab.get(getActivity());
        List<Lesson> lessons = lessonLab.getCrimes();
        mAdapter = new LessonAdapter(lessons);
        mLessonRecyclerView.setAdapter(mAdapter);
    }

    private class LessonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Lesson mLesson;

        public LessonHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_lesson_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_lesson_date_text_view);
        }

        public void bindLesson(Lesson lesson) {
            mLesson = lesson;
            mTitleTextView.setText(mLesson.getTitle());
            mDateTextView.setText(mLesson.getDate().toString());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),
                    mLesson.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private class LessonAdapter extends RecyclerView.Adapter<LessonHolder> {
        private List<Lesson> mLessons;
        public LessonAdapter(List<Lesson> crimes) {
            mLessons = crimes;
        }

        @Override
        public LessonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_lesson, parent, false);
            return new LessonHolder(view);
        }
        @Override
        public void onBindViewHolder(LessonHolder holder, int position) {
            Lesson lesson = mLessons.get(position);
            holder.bindLesson(lesson);
        }
        @Override
        public int getItemCount() {
            return mLessons.size();
        }
    }
}
