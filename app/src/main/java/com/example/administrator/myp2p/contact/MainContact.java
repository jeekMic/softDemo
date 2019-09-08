package com.example.administrator.myp2p.contact;

import com.example.administrator.myp2p.bean.Question;
import com.example.administrator.myp2p.base.BasePresenter;
import com.example.administrator.myp2p.base.BaseView;

import java.util.List;

public interface MainContact {

    public interface QUestionView extends BaseView {
        void setData(List<Question> data);
    }

    public interface QUestionPresenter{
        void requestData();
    }

}
