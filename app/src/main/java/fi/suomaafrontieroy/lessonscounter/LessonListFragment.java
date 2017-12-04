package fi.suomaafrontieroy.lessonscounter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LessonListFragment extends Fragment {

    private RecyclerView mLessonRecyclerView;
    private LessonAdapter mAdapter;
    private int mLastAdapterClickPosition = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_list, container,
                false);
        mLessonRecyclerView = (RecyclerView) view
                .findViewById(R.id.lesson_recycler_view);
        mLessonRecyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_lesson_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_lesson:
                Lesson lesson = new Lesson();
                LessonLab.get(getActivity()).addLesson(lesson);
                Intent intent = LessonActivity
                        .newIntent(getActivity(), lesson.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        LessonLab lessonLab = LessonLab.get(getActivity());
        List lessons = lessonLab.getLessons();

        if (mAdapter == null) {
            mAdapter = new LessonAdapter(lessons);
            mLessonRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.replaceList(lessons);
            mAdapter.notifyItemChanged(mLastAdapterClickPosition);
        }
    }

    private class LessonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Lesson mLesson;

        private TextView mTitleTextView;
        private TextView mDateTextView;

        private LessonHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_lesson, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.lesson_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.lesson_date);
        }

        public void bind(Lesson lesson) {
            mLesson = lesson;
            mTitleTextView.setText(mLesson.getTitle());
            mDateTextView.setText(DateFormat.format("dd MMMM yyyy (EEEE) HH:mm", mLesson.getDate()));
        }

        @Override
        public void onClick(View v) {
            mLastAdapterClickPosition = getAdapterPosition();
            Intent intent = LessonActivity.newIntent(getActivity(), mLesson.getId());
            startActivity(intent);
        }
    }

    private class LessonAdapter extends RecyclerView.Adapter<LessonHolder> {
        private List<Lesson> mLessons;

        public LessonAdapter(List<Lesson> lessons) {
            mLessons = lessons;
        }

        @Override
        public LessonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new LessonHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(LessonHolder holder, int position) {
            Lesson lesson = mLessons.get(position);
            holder.bind(lesson);
        }
        @Override
        public int getItemCount() {
            return mLessons.size();
        }

        public void replaceList(List<Lesson> lessons) {
            mLessons = lessons;
        }
    }
}
