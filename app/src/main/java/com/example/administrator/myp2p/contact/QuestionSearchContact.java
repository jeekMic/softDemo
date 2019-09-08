package com.example.administrator.myp2p.contact;

import android.widget.BaseAdapter;

import com.example.administrator.myp2p.base.BasePresenter;
import com.example.administrator.myp2p.bean.Question;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface QuestionSearchContact {

    interface Presenter {
        void requestQuestion(String courseId);
        void requestPointion(String pointId);
        void requestCase(String courseId);
    }
    interface View{
        void loadDataSuccess(List<Question> data);
        void loadDataPointSuccess(List<Question> data);
        void loadDataCaseSuccess(List<Question> data);
        void loadDataFaild();
    }

}
