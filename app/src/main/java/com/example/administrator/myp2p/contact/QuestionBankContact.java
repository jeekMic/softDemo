package com.example.administrator.myp2p.contact;


import com.example.administrator.myp2p.bean.Question;
import com.example.administrator.myp2p.bean.TCourse;

import java.util.List;

public interface QuestionBankContact {
    interface Presenter {
        void requestQuestion(String courseId);
    }
    interface View{
        void loadDataSuccess(List<TCourse> data);
        void loadDataFaild();
    }
}
