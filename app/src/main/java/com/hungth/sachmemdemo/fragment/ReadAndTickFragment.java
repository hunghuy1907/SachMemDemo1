package com.hungth.sachmemdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hungth.sachmemdemo.database.GetDataFromSheet;
import com.hungth.sachmemdemo.R;
import com.hungth.sachmemdemo.view.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/10/2018.
 */

public class ReadAndTickFragment extends Fragment implements View.OnClickListener {
    private TextView txtSelectQuestion;

    private List<String> strSelects;
    private List<String> notes;

    private int resultTrue;
    private Button btnSelectB;
    private Button btnSelectA;
    private Button btnSelectC;
    private GetDataFromSheet getDataFromSheet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_read_and_tick, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeComponents();
        try {
            getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initQuestion();
        
    }

    private void initializeComponents() {
        txtSelectQuestion = getActivity().findViewById(R.id.txt_question_select);

        btnSelectA = getActivity().findViewById(R.id.btn_select_A);
        btnSelectB = getActivity().findViewById(R.id.btn_select_B);
        btnSelectC = getActivity().findViewById(R.id.btn_select_C);
        btnSelectA.setOnClickListener(this);
        btnSelectB.setOnClickListener(this);
        btnSelectC.setOnClickListener(this);


//        getDataFromSheet = new GetDataFromSheet();
    }

    private void getData() throws IOException {

//        strSelects = getDataFromSheet.getData();
        notes = new ArrayList<>();
//        strSelects.add("\"5 cộng 3 bằng mấy?\n" +
//                "{8/3/5}\"");
    }

    private void initQuestion() {
        strSelects= new ArrayList<>();
//        GetDataFromSheet getDataFromSheet = new GetDataFromSheet((MainActivity) getParentFragment().getActivity());
        strSelects = ((MainActivity)getActivity()).getClassNames();

        Log.d("ghghg",  ""+strSelects.size());
        String s = strSelects.get(0);
        int indexOfAnswer = s.indexOf("{") - 1;
        String question = s.substring(1, indexOfAnswer);
        txtSelectQuestion.setText(question);
        String answer = s.substring(indexOfAnswer);
        List<Integer> selected = new ArrayList<>();
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) >= '0' && answer.charAt(i) <= '9') {
                selected.add(Integer.parseInt((answer.charAt(i) + "").toString()));
            }
        }
        btnSelectA.setText(selected.get(0).toString());
        btnSelectB.setText(selected.get(1).toString());
        btnSelectC.setText(selected.get(2).toString());

        if (selected.size() > 3) {
            resultTrue = selected.get(3);
        } else {
            resultTrue = 0;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select_A:
                if (resultTrue == 0) {
                    btnSelectA.setBackgroundResource(R.drawable.bg_btn_chosse_is_true);
                    Toast.makeText(getActivity(), "Đúng rồi", Toast.LENGTH_SHORT).show();
                } else {
                    btnSelectA.setBackgroundResource(R.drawable.bg_btn_chosse_is_false);
                    Toast.makeText(getActivity(), "Sai rồi", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_select_B:
                if (resultTrue == 1) {
                    btnSelectB.setBackgroundResource(R.drawable.bg_btn_chosse_is_true);
                    Toast.makeText(getActivity(), "Đúng rồi", Toast.LENGTH_SHORT).show();
                } else {
                    btnSelectB.setBackgroundResource(R.drawable.bg_btn_chosse_is_false);
                    Toast.makeText(getActivity(), "Sai rồi", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_select_C:
                if (resultTrue == 2) {
                    btnSelectC.setBackgroundResource(R.drawable.bg_btn_chosse_is_true);
                    Toast.makeText(getActivity(), "Đúng rồi", Toast.LENGTH_SHORT).show();
                } else {
                    btnSelectC.setBackgroundResource(R.drawable.bg_btn_chosse_is_false);
                    Toast.makeText(getActivity(), "Sai rồi", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }
}
