package com.hungth.sachmemdemo.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.hungth.sachmemdemo.R;
import com.hungth.sachmemdemo.adapter.ClassAdapter;
import com.hungth.sachmemdemo.database.GetDataFromSheet;
import com.hungth.sachmemdemo.fragment.BookFragment;
import com.hungth.sachmemdemo.fragment.ClassFragment;
import com.hungth.sachmemdemo.model.Data;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ClassFragment classFragment;
    private BookFragment bookFragment;

    private ClassAdapter classAdapter;
    private List<String> classNames;
    private List<Data> datas;
    private RecyclerView rcvClass;
    private GetDataFromSheet getDataFromSheet;

    public List<Data> getDatas() {
        return datas;
    }

    public List<String> getClassNames() {
        return classNames;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromSheet = new GetDataFromSheet(this);
        datas = getDataFromSheet.getDatas();
        classNames = getDataFromSheet.getStringDataQuestion();
        showMainFragment();

    }

    private void showMainFragment() {
        classFragment = new ClassFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_frame, classFragment).commit();

//
        bookFragment = new BookFragment();

//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.main_frame, bookFragment).commit();
    }

}
