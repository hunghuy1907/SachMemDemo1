package com.hungth.sachmemdemo.database;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hungth.sachmemdemo.database.GetDataFromSheet;

/**
 * Created by ngoth on 3/30/2018.
 */

public class DemoActivity extends AppCompatActivity{
    GetDataFromSheet getDataFromSheet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataFromSheet= new GetDataFromSheet(this);
        getDataFromSheet.getData();
    }
}
