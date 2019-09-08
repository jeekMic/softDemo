package com.example.administrator.myp2p.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.myp2p.bean.Question;
import com.example.administrator.myp2p.contact.MainContact;
import com.example.administrator.myp2p.contact.QuestionSearchContact;
import com.example.administrator.myp2p.service.DBService;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ISearchPresenter implements QuestionSearchContact.Presenter {
    protected static QuestionSearchContact.View view;
    private ExecutorService services;
    private Runnable runable;
    private String sql;
    private MyHandler handler;
    private static int OK =1;
    private static int FAIL =2;
    private static int OK_POINT =3;
    private static int OK_CASE =4;
    public ISearchPresenter(QuestionSearchContact.View view) {
        this.view = view;
        handler = new MyHandler(this);
    }

    @Override
    public void requestQuestion(String courseId) {
        //这里加载数据操作
        //根据年份和上下午来搜索题目
        sql = "select  * from t_question where courseid="+courseId+" order by rank";
        Log.e("sql::","   "+sql);
        runable = new Runnable() {
            @Override
            public void run() {
                try {
                    List<Question> questionList = DBService.getInstance().getQuestionBySQL(sql);
                    Message message = Message.obtain();
                    message.obj = questionList;
                    message.what = OK;
                    handler.sendMessage(message);
                }catch (Exception e){
                    handler.sendEmptyMessage(FAIL);
                    view.loadDataFaild();
                }

            }
        };
        submitTask(runable);
    }

    /**
     * 查询知识点对应的题目
     * @param pointId
     */
    @Override
    public void requestPointion(String pointId) {
        sql = "select  * from t_question where courseid2="+pointId;
        Log.e("sql::","   "+sql);
        runable = new Runnable() {
            @Override
            public void run() {
                try {
                    List<Question> questionList = DBService.getInstance().getQuestionBySQL(sql);
                    Message message = Message.obtain();
                    message.obj = questionList;
                    message.what = OK_POINT;
                    handler.sendMessage(message);
                }catch (Exception e){
                    handler.sendEmptyMessage(FAIL);
                    view.loadDataFaild();
                }

            }
        };
        submitTask(runable);
    }

    @Override
    public void requestCase(String courseId) {
        sql = "select  * from t_question where courseid="+courseId;
        Log.e("sql::","   "+sql);
        runable = new Runnable() {
            @Override
            public void run() {
                try {
                    List<Question> questionList = DBService.getInstance().getQuestionBySQL(sql);
                    Message message = Message.obtain();
                    message.obj = questionList;
                    message.what = OK_CASE;
                    handler.sendMessage(message);
                }catch (Exception e){
                    handler.sendEmptyMessage(FAIL);
                    view.loadDataFaild();
                }

            }
        };
        submitTask(runable);
    }

    private void submitTask(Runnable runable){
        services = Executors.newFixedThreadPool(2);
        services.execute(runable);

    }

    static class MyHandler extends Handler {
        private WeakReference<ISearchPresenter> presenter;

        public MyHandler(ISearchPresenter presenter) {
            this.presenter = new WeakReference<>(presenter);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    view.loadDataSuccess((List<Question>) msg.obj);
                    break;
                case 2:
                    view.loadDataFaild();
                    break;
                case 3:
                    view.loadDataPointSuccess((List<Question>) msg.obj);
                    break;
                case 4:
                    view.loadDataCaseSuccess((List<Question>) msg.obj);
                    break;
            }
        }
    }
}
