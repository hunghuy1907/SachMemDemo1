package com.hungth.sachmemdemo.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungth.sachmemdemo.view.OnItemClickListenner;
import com.hungth.sachmemdemo.R;
import com.hungth.sachmemdemo.adapter.LessonAdapter;
import com.hungth.sachmemdemo.model.Lesson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/27/2018.
 */

public class LessonFragment extends Fragment implements OnItemClickListenner{
    private static final String KEY = "key";
    private View rootView;
    private List<Lesson> lessons;
    private RecyclerView rcvLesson;
    private LessonAdapter lessonAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_unit, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initializeComponents();
        Bundle bundle = getArguments();
        int position = bundle.getInt(UnitFragment.KEY);
        showLessonNames(position);

    }

    private void showLessonNames(int position) {
        switch (position) {
            case 0:
                setDataForUnit1();
                break;

            case 1:

                break;

            case 2:

                break;
                
            default:
                break;
        }
    }

    private void setDataForUnit1() {
        lessons = new ArrayList<>();
        
        Lesson lesson = new Lesson("Lesson 1");
        Lesson lesson1 = new Lesson("Lesson 2");
        Lesson lesson2 = new Lesson("Lesson 3");
        
        lessons.add(lesson);
        lessons.add(lesson1);
        lessons.add(lesson2);

        lessonAdapter = new LessonAdapter(getActivity(), lessons);
        lessonAdapter.setOnItemClickListenner(this);
        rcvLesson.setAdapter(lessonAdapter);
        
    }

    private void initializeComponents() {
        rcvLesson = rootView.findViewById(R.id.rcv_unit);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvLesson.setLayoutManager(layoutManager);

        int orient = DividerItemDecoration.VERTICAL;
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), orient);
        rcvLesson.addItemDecoration(decoration);
    }

    @Override
    public void onItemClicked(View itemView, int position) {
        SkillFragment skillFragment = new SkillFragment();
        replaceFragment(skillFragment, position);
    }

    public void replaceFragment(SkillFragment skillFragment, int position) {
        final Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);
        skillFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, skillFragment)
                .addToBackStack(null)
                .commit();

    }
}
