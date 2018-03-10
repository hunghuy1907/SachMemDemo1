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

import com.hungth.sachmemdemo.OnItemClickListenner;
import com.hungth.sachmemdemo.R;
import com.hungth.sachmemdemo.adapter.SkillAdapter;
import com.hungth.sachmemdemo.model.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/9/2018.
 */

public class SkillFragment extends Fragment implements OnItemClickListenner{

    public static final String KEY = "key";

    private View rootView;
    private List<Skill> skills;
    private RecyclerView rcvSkill;
    private SkillAdapter SkillAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_skill, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initializeComponents();
        Bundle bundle = getArguments();
        int position = bundle.getInt(BookFragment.KEY);
        showSkillNames(position);

    }

    private void showSkillNames(int position) {
        switch (position) {
            case 0:
                setDataForLessson1();
                break;

            case 1:
                
                break;

            case 2:
                
                break;


            default:
                break;
        }
    }

    private void setDataForLessson1() {
        skills = new ArrayList<>();
        Skill Skill = new Skill("1 Look, Listen and repeat.");
        Skill Skill1 = new Skill("2 Point and say.");
        Skill Skill2 = new Skill("3 Let'talk.");
        Skill Skill3 = new Skill("4 listen and tick");
        Skill Skill4 = new Skill("5 Read and complete");
        Skill Skill5 = new Skill("6 Let's sing");

        skills.add(Skill);
        skills.add(Skill1);
        skills.add(Skill2);
        skills.add(Skill3);
        skills.add(Skill4);

        SkillAdapter = new SkillAdapter(getActivity(), skills);
        SkillAdapter.setOnItemClickListenner(this);
        rcvSkill.setAdapter(SkillAdapter);
    }

 

    private void initializeComponents() {
        rcvSkill = rootView.findViewById(R.id.rcv_skill);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvSkill.setLayoutManager(layoutManager);

        int orient = DividerItemDecoration.VERTICAL;
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), orient);
        rcvSkill.addItemDecoration(decoration);
    }


    @Override
    public void onItemClicked(View itemView, int position) {
        switch (position) {
            case 0:
                replaceFragment();
                break;

            case 1:
                replaceFragment1();
                break;

            case 2:
                replaceFragment2();
                break;

            case 3:
                replaceFragment3();
                break;

            default:
                break;
        }

    }

    private void replaceFragment1() {
        PointAndSayFragment pointAndSayFragment = new PointAndSayFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, pointAndSayFragment)
                .addToBackStack(null)
                .commit();
    }

    private void replaceFragment2() {
        ReadAndTickFragment readAndTickFragment = new ReadAndTickFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, readAndTickFragment)
                .addToBackStack(null)
                .commit();
    }

    private void replaceFragment3() {
       ListenAndTickFragment listenAndTickFragment = new ListenAndTickFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, listenAndTickFragment)
                .addToBackStack(null)
                .commit();
    }

    private void replaceFragment() {
        LookListenRepeatFragment lookListenRepeatFragment = new LookListenRepeatFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, lookListenRepeatFragment)
                .addToBackStack(null)
                .commit();
    }
}
