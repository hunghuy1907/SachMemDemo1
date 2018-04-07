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
import com.hungth.sachmemdemo.model.Data;
import com.hungth.sachmemdemo.view.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 3/10/2018.
 */

public class ReadAndTickFragment extends Fragment implements View.OnClickListener {
    private TextView txtSelectQuestion;
    private List<String> strSelects;
    private List<Data> datas;
    private List<Data> datasSelect;
    private int resultTrue;
    private Button btnSelectB;
    private Button btnSelectA;
    private Button btnSelectC;
    private int index = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_read_and_tick, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();
        initQuestion(0);
    }

    private void initializeComponents() {
        txtSelectQuestion = getActivity().findViewById(R.id.txt_question_select);

        btnSelectA = getActivity().findViewById(R.id.btn_select_A);
        btnSelectB = getActivity().findViewById(R.id.btn_select_B);
        btnSelectC = getActivity().findViewById(R.id.btn_select_C);
        btnSelectA.setOnClickListener(this);
        btnSelectB.setOnClickListener(this);
        btnSelectC.setOnClickListener(this);

        datasSelect = new ArrayList<>();
        strSelects = ((MainActivity) getActivity()).getClassNames();
        datas = ((MainActivity) getActivity()).getDatas();
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getNote().equals("Select") || datas.get(i).getNote().equals("Select and keep order")) {
                datasSelect.add(datas.get(i));
            }
        }
    }

    private void slow() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                    Log.d("thread" , "hhhhhh");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void initQuestion(int index) {
        String s = datasSelect.get(index).getQuestionTextAndAnswer();
        int indexOfAnswer = s.indexOf("{") + 1;
        int indexOfEndAnswer = s.indexOf("}");
        String question = s.substring(0, indexOfAnswer - 2);
        txtSelectQuestion.setText(question);
        String answer = s.substring(indexOfAnswer, indexOfEndAnswer);
        String[] selects = answer.split("/");
        List<Integer> selected = new ArrayList<>();
        for (int i = 0; i < selects.length; i++) {
            selected.add(Integer.parseInt(selects[i]));
        }
        String trueLocation = s.substring(indexOfEndAnswer);
        for (int i = 0; i < trueLocation.length(); i++) {
            if (trueLocation.charAt(i) >= '0' && trueLocation.charAt(i) <= 3) {
                resultTrue = Integer.parseInt(trueLocation.charAt(i) + "");
                return;
            }
        }
        btnSelectA.setText(selects[0]);
        btnSelectB.setText(selects[1]);
        btnSelectC.setText(selects[2]);
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
                long time= System.currentTimeMillis();
                while (System.currentTimeMillis()-time<3){

                }
                index++;
                initQuestion(index);
                break;

            case R.id.btn_select_B:
                if (resultTrue == 1) {
                    btnSelectB.setBackgroundResource(R.drawable.bg_btn_chosse_is_true);
                    Toast.makeText(getActivity(), "Đúng rồi", Toast.LENGTH_SHORT).show();
                } else {
                    btnSelectB.setBackgroundResource(R.drawable.bg_btn_chosse_is_false);
                    Toast.makeText(getActivity(), "Sai rồi", Toast.LENGTH_SHORT).show();
                }
                slow();
                index++;
                initQuestion(index);
                break;

            case R.id.btn_select_C:
                if (resultTrue == 2) {
                    btnSelectC.setBackgroundResource(R.drawable.bg_btn_chosse_is_true);
                    Toast.makeText(getActivity(), "Đúng rồi", Toast.LENGTH_SHORT).show();
                } else {
                    btnSelectC.setBackgroundResource(R.drawable.bg_btn_chosse_is_false);
                    Toast.makeText(getActivity(), "Sai rồi", Toast.LENGTH_SHORT).show();
                }
                slow();
                index++;
                initQuestion(index);
                break;

            default:
                break;
        }
    }
}
