package com.example.administrator.thread;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private int second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView) findViewById(R.id.tv);
        Date thelast=new Date(117,5,23);
        Date now=new Date();
        second=(int)(thelast.getTime()-now.getTime())/1000;
    }
    public void anr(View v){
        for(int i=0;i<10000;i++){
            BitmapFactory.decodeResource(getResources(),R.drawable.android);
        }
    }
    public void threadclass(View v){
        class Threadsample extends Thread{
            Random ran;
            public Threadsample(String name){
                super(name);
                ran=new Random();
            }
            public void run(){
                for(int i=0;i<10;i++){
                    System.out.println(i+" "+getName());
                    try {
                        sleep(ran.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(getName()+"ok");
            }
        }
        Threadsample thread1=new Threadsample("line 1");
        thread1.start();
        Threadsample thread2=new Threadsample("line 2");
        thread2.start();
    }
    public void runnableclass(View v){
        class runnableexample implements Runnable{
            Random ran;
            String name;
            public runnableexample(String rname){
                this.name=rname;
                ran=new Random();
            }
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println(i+" "+name);
                    try {
                        Thread.sleep(ran.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name+"ok");
            }
        }
        Thread thread1=new Thread(new runnableexample("line 1"));
        thread1.start();
        Thread thread2=new Thread(new runnableexample("line 2"));
        thread2.start();
    }
    public void timetaskclass(View v){
        class mythread extends TimerTask{
            Random ran;
            String name;
            public mythread(String tname){
                this.name=tname;
                ran=new Random();
            }
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println(i+" "+name);
                    try {
                        Thread.sleep(ran.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name+"ok");
            }
        }
        Timer t1=new Timer();
        Timer t2=new Timer();
        mythread th1=new mythread("line 1");
        mythread th2=new mythread("line 2");
        t1.schedule(th1,0);
        t2.schedule(th2,0);
    }
    public void showmess(String mess){
        tv.setText(mess);
    }
    public void handlerclass(View v){
        final Handler myh=new Handler(){
            public void handlerclass(Message mess){
                super.handleMessage(mess);
                switch (mess.what){
                    case 1:showmess(String.valueOf(mess.arg1+mess.getData().get("attach").toString()));
                }
            }
        };
        class myhand extends TimerTask{
            int count;
            double ach1=1,ach2=2;
            public myhand(int second){
                this.count=second;
            }
            @Override
            public void run() {
                Message mess=Message.obtain().obtain();
                mess.what=1;
                mess.arg1=count--;
                ach1=ach1*1.01;
                ach2=ach2*1.02;
                Bundle b=new Bundle();
                b.putString("attach","");
                mess.setData(b);

            }
        }
    }
}
