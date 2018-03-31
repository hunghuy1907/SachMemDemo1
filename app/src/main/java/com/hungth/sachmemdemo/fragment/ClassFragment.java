package com.hungth.sachmemdemo.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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
import com.hungth.sachmemdemo.adapter.ClassAdapter;
import com.hungth.sachmemdemo.model.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/3/2018.
 */

public class ClassFragment extends Fragment implements OnItemClickListenner {
    public static final String KEY = "key";
    private Intent intent;
    private View rootView;
    private ClassAdapter classAdapter;
    public List<Class> classNames;
    private RecyclerView rcvClass;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_class, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initializeComponents();
        showClassNames();
    }

    private void initializeComponents() {
        rcvClass = rootView.findViewById(R.id.rcv_class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvClass.setLayoutManager(layoutManager);

        int orient = DividerItemDecoration.VERTICAL;
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), orient);
        rcvClass.addItemDecoration(decoration);
    }

    private void showClassNames() {
        classNames = new ArrayList<>();
        classNames.clear();

        Class class1 = new Class("Lớp 1");
        Class class2 = new Class("Lớp 3");
        Class class3 = new Class("Lớp 4");
        Class class4 = new Class("Lớp 5");
        Class class5 = new Class("Lớp 12");

        classNames.add(class1);
        classNames.add(class2);
        classNames.add(class3);
        classNames.add(class4);
        classNames.add(class5);

        classAdapter = new ClassAdapter(getActivity(), classNames);
        classAdapter.setOnItemClickListenner(this);
        rcvClass.setAdapter(classAdapter);
    }

    @Override
    public void onItemClicked(View itemView, int position) {

        BookFragment bookFragment = new BookFragment();
        replaceFragment(bookFragment, position);
    }

    public void replaceFragment(BookFragment bookFragment, int position) {
        final Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);
        bookFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, bookFragment)
                .addToBackStack(null)
                .commit();

    }

}






