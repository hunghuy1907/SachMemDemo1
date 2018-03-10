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
import com.hungth.sachmemdemo.adapter.UnitAdapter;
import com.hungth.sachmemdemo.model.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/27/2018.
 */

public class UnitFragment extends Fragment implements OnItemClickListenner {
    public static final String KEY = "key";

    private View rootView;
    private List<Unit> units;
    private RecyclerView rcvUnit;
    private UnitAdapter unitAdapter;

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
        int position = bundle.getInt(BookFragment.KEY);
        showUnitNames(position);

    }

    private void showUnitNames(int position) {
        switch (position) {
            case 0:
                setDataForBook1();
                break;

            case 1:
                setDataForBook2();
                break;

            case 2:
                setDataForBook3();
                break;

            case 3:
                setDataForBook4();
                break;

            default:
                break;
        }
    }

    private void setDataForBook4() {
        units = new ArrayList<>();
        Unit unit = new Unit("Unit 1 Hello");
        Unit unit1 = new Unit("Unit 2 What's your name");
        Unit unit2 = new Unit("Unit 3 This is Tony");
        Unit unit3 = new Unit("Unit 4 How old are you");
        Unit unit4 = new Unit("Unit 5 Are they your friend");

        units.add(unit);
        units.add(unit1);
        units.add(unit2);
        units.add(unit3);
        units.add(unit4);

        unitAdapter = new UnitAdapter(getActivity(), units);
        unitAdapter.setOnItemClickListenner(this);
        rcvUnit.setAdapter(unitAdapter);
    }

    private void setDataForBook3() {
        units = new ArrayList<>();
        Unit unit = new Unit("Unit 1 Hello");
        Unit unit1 = new Unit("Unit 2 What's your name");
        Unit unit2 = new Unit("Unit 3 This is Tony");
        Unit unit3 = new Unit("Unit 4 How old are you");
        Unit unit4 = new Unit("Unit 5 Are they your friend");

        units.add(unit);
        units.add(unit1);
        units.add(unit2);
        units.add(unit3);
        units.add(unit4);

        unitAdapter = new UnitAdapter(getActivity(), units);
        unitAdapter.setOnItemClickListenner(this);
        rcvUnit.setAdapter(unitAdapter);
    }

    private void setDataForBook2() {
        units = new ArrayList<>();
        Unit unit = new Unit("Unit 11 This is my family");
        Unit unit1 = new Unit("Unit 12 This is my house");
        Unit unit2 = new Unit("Unit 13 Where's my book");
        Unit unit3 = new Unit("Unit 14 Are there any posters in the room");

        units.add(unit);
        units.add(unit1);
        units.add(unit2);
        units.add(unit3);

        unitAdapter = new UnitAdapter(getActivity(), units);
        unitAdapter.setOnItemClickListenner(this);
        rcvUnit.setAdapter(unitAdapter);
    }

    private void setDataForBook1() {
        units = new ArrayList<>();
        Unit unit = new Unit("Unit 1 Hello");
        Unit unit1 = new Unit("Unit 2 What's your name");
        Unit unit2 = new Unit("Unit 3 This is Tony");
        Unit unit3 = new Unit("Unit 4 How old are you");

        units.add(unit);
        units.add(unit1);
        units.add(unit2);
        units.add(unit3);

        unitAdapter = new UnitAdapter(getActivity(), units);
        unitAdapter.setOnItemClickListenner(this);
        rcvUnit.setAdapter(unitAdapter);
    }

    private void initializeComponents() {
        rcvUnit = rootView.findViewById(R.id.rcv_unit);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvUnit.setLayoutManager(layoutManager);

        int orient = DividerItemDecoration.VERTICAL;
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), orient);
        rcvUnit.addItemDecoration(decoration);
    }


    @Override
    public void onItemClicked(View itemView, int position) {
        LessonFragment lessonFragment = new LessonFragment();
        replaceFragment(lessonFragment, position);
    }

    private void replaceFragment(LessonFragment lessonFragment, int position) {
        final Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);
        lessonFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, lessonFragment)
                .addToBackStack(null)
                .commit();
    }
}
