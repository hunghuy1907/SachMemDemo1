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
import com.hungth.sachmemdemo.adapter.BookAdapter;
import com.hungth.sachmemdemo.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/7/2018.
 */

public class BookFragment extends Fragment implements OnItemClickListenner{
    public static final String KEY = "key";
    private View rootView;
    private RecyclerView rcvBooks;
    private List<Book> bookNames;
    private BookAdapter bookAdapter;

    private ClassFragment classFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_book, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();
        setDataforClass3();

        Bundle bundle = getArguments();
        int position = bundle.getInt(ClassFragment.KEY);
        showBookOfClass(position);
    }

    public void showBookOfClass(int position) {
        classFragment = new ClassFragment();

        switch (position) {
            case 0:
                setDataforClass1();
                break;

            case 1:
                setDataforClass3();
                break;

            case 2:
                setDataforClass4();
                break;

            case 3:
                setDataforClass5();
                break;

            case 4:
                setDataforClass12();
                break;

            default:
                break;

        }
    }

    private void initializeComponents() {
        rcvBooks = rootView.findViewById(R.id.rcv_book);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvBooks.setLayoutManager(layoutManager);

        int orient = DividerItemDecoration.VERTICAL;
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), orient);
        rcvBooks.addItemDecoration(decoration);
    }

    public void setDataforClass3() {
        bookNames = new ArrayList<>();

        Book book = new Book("Tiếng Anh 3 Tập 1");
        Book book2 = new Book("Tiếng Anh 3 Tập 2");
        Book book3 = new Book("Tiếng Anh Sách bài tập 2");
        bookNames.add(book);
        bookNames.add(book2);
        bookNames.add(book3);
        bookAdapter = new BookAdapter(getActivity(), bookNames);
        bookAdapter.setOnItemClickListenner(this);
        rcvBooks.setAdapter(bookAdapter);
    }

    public void setDataforClass1() {
        bookNames = new ArrayList<>();

        Book book = new Book("Toán 1 Vở bài tập");
        Book book2 = new Book("Tiếng Anh 1 Macmillan");
        Book book3 = new Book("Tiếng Anh 1 Sách bài tập Macmillan");
        Book book4 = new Book("Tiếng Anh 1 ABC");
        bookNames.add(book);
        bookNames.add(book2);
        bookNames.add(book3);
        bookNames.add(book4);
        bookAdapter = new BookAdapter(getActivity(), bookNames);
        bookAdapter.setOnItemClickListenner(this);
        rcvBooks.setAdapter(bookAdapter);
    }

    public void setDataforClass4() {
        bookNames = new ArrayList<>();

        Book book = new Book("Tiếng Anh 4 Tập 1");
        Book book2 = new Book("Tiếng Anh 3 Tập 2");
        Book book3 = new Book("Tiếng Anh 4 Sách bài tập");
        bookNames.add(book);
        bookNames.add(book2);
        bookNames.add(book3);
        bookAdapter = new BookAdapter(getActivity(), bookNames);
        bookAdapter.setOnItemClickListenner(this);
        rcvBooks.setAdapter(bookAdapter);
    }

    public void setDataforClass5() {
        bookNames = new ArrayList<>();

        Book book = new Book("Smart Time Grade 5");
        Book book2 = new Book("Tiếng Anh 5 Tập 1");
        Book book3 = new Book("Tiếng Anh 5 Tập 2");
        Book book4 = new Book("Tiếng Anh 5 Sách bài tập");
        bookNames.add(book);
        bookNames.add(book2);
        bookNames.add(book3);
        bookNames.add(book4);
        bookAdapter = new BookAdapter(getActivity(), bookNames);
        rcvBooks.setAdapter(bookAdapter);
    }

    public void setDataforClass12() {
        bookNames = new ArrayList<>();

        Book book = new Book("Smart Time Grade 12");
        Book book2 = new Book("Hướng dẫn ôn tập tốt nghiệp THPT 2016-2017 môn Tiếng Anh");
        Book book3 = new Book("Hướng dẫn ôn tập kì thi THPT quốc gia năm học 2017-2018 môn Tiếng Anh");
        Book book4 = new Book("Tiếng Anh 12 Tập 1");
        Book book5 = new Book("Tiếng Anh 12 Tập 1 Sách bài tập");
        Book book6 = new Book("Tiếng Anh 12 Tập 2");
        Book book7 = new Book("Tiếng Anh 12 Tập 2 Sách bài tập");
        bookNames.add(book);
        bookNames.add(book2);
        bookNames.add(book3);
        bookNames.add(book4);
        bookNames.add(book5);
        bookNames.add(book6);
        bookNames.add(book7);
        bookAdapter = new BookAdapter(getActivity(), bookNames);
        rcvBooks.setAdapter(bookAdapter);
    }

    @Override
    public void onItemClicked(View itemView, int position) {
        UnitFragment unitFragment = new UnitFragment();
        replaceFragment(unitFragment, position);

    }

    public void replaceFragment(UnitFragment unitFragment, int position) {
        Bundle bundle1 = getArguments();
        int positionOfClass = bundle1.getInt(ClassFragment.KEY);
        if (positionOfClass != 1)
            return;
        final Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);
        unitFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, unitFragment)
                .addToBackStack(null)
                .commit();

    }
}
