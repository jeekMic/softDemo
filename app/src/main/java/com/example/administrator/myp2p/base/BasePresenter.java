package com.example.administrator.myp2p.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasePresenter {
    public ExecutorService services;

    public BasePresenter() {
        services = Executors.newFixedThreadPool(2);
    }
    public  void submitTask(Runnable runable){
        services.execute(runable);

    }
}
