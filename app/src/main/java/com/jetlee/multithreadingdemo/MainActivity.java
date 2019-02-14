package com.jetlee.multithreadingdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.NewThreadWorker;

public class MainActivity extends AppCompatActivity {
    Handler handler;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);

        // 提交到master的信息

        handler = new MyHandler();
        handler.sendMessage(new Message());
        // 这是test2的提交
//        NewThreadWorker
        Single.just(1)
//                .subscribeOn()
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        return String.valueOf(integer);
                    }
                })
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        // 这是test3的提交
                    }

                    @Override
                    public void onSuccess(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        Log.e("1000", "看看吧");
//        Observable.map()

    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("1000", "来了位");
            textView.setText("来到了Handler");
            // 是不是真的啊
            // 第二次看看
        }
    }


}
