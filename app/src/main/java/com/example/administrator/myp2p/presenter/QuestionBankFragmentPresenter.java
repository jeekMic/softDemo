package com.example.administrator.myp2p.presenter;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.myp2p.base.BasePresenter;
import com.example.administrator.myp2p.bean.Question;
import com.example.administrator.myp2p.bean.TCourse;
import com.example.administrator.myp2p.contact.QuestionBankContact;

import com.example.administrator.myp2p.service.DBService;

import java.lang.ref.WeakReference;
import java.util.List;

public class QuestionBankFragmentPresenter extends BasePresenter implements QuestionBankContact.Presenter{
    protected static QuestionBankContact.View view_question_bank_presenter;
    private MyHandler handler;
    private Runnable runnable;
    private int OK = 1;
    private int OK_POINT = 3;
    public QuestionBankFragmentPresenter(QuestionBankContact.View view_question_bank_presenter) {
        super();
        this.view_question_bank_presenter = view_question_bank_presenter;
        handler = new MyHandler(this);
    }

    @Override
    public void requestQuestion(final String courseId) {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    String sql = "select * from t_course where iyear="+courseId+" and classid=2";
                    List<TCourse> course = DBService.getInstance().getCourse(sql);
                    Message message = Message.obtain();
                    message.obj = course;
                    message.what = OK;
                    handler.sendMessage(message);

                }catch (Exception e){
                    handler.sendEmptyMessage(2);
                }
            }
        };
        submitTask(runnable);
    }

    static class MyHandler extends Handler {
        private WeakReference<QuestionBankFragmentPresenter> presenter;

        public MyHandler(QuestionBankFragmentPresenter presenter) {
            this.presenter = new WeakReference<>(presenter);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    view_question_bank_presenter.loadDataSuccess((List<TCourse>) msg.obj);
                    break;
                case 2:
                    view_question_bank_presenter.loadDataFaild();
                    break;

            }
        }
    }
}
