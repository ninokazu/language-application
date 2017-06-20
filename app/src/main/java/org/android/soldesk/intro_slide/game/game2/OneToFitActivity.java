package org.android.soldesk.intro_slide.game.game2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.android.soldesk.intro_slide.R;
import org.android.soldesk.intro_slide.game.game3.WordPop;

import java.util.*;

public class OneToFitActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_start = null; //start 버튼
    private Button btns[] = new Button[25]; // 버튼 1~25
    private TextView tv_top = null;
    private TextView tv_time = null;
    private Dialog input_dlg = null; // popup Dialog
    private StopWatch sw = null;
    private StopWatch sw_2 = null;
    private StopWatch sw_3 = null;
    private int ArrNum[] = new int[25];
    private int iStep = 1; // 1~50 현재진행 숫자
    private int iNext = 1;
    private boolean bStart = false; // 시작여부
    private boolean reset = false;
    private boolean bBlink = false;
    private boolean bBlink_2 = false;
    private int btnNum[] = new int[51];
    ImageView btnMenu, gameinfo_2;
    Button btn_reset; //reset 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_to_fit);
        setBinddingButtons();

        iStep = 1;
        bStart = false;
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_time.setText("00:00:00");
        tv_time.setTextColor(Color.RED);

        sw = new StopWatch();
        sw_2 = new StopWatch();
        sw_3 = new StopWatch();

        btnMenu = (ImageView) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bStart = false;
                finish();
            }
        });

        gameinfo_2 = (ImageView) findViewById(R.id.gameinfo_2);
        gameinfo_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Alertpop = new AlertDialog.Builder(v.getContext());
                Alertpop.setMessage("게임 설명 \n\n" + "1. Start 버튼을 누르면 게임이 시작됩니다. \n" +
                        "2. 1부터 50까지의 숫자를 순서대로 입력합니다. \n\n" +
                        "3. 게임을 리셋하고 싶을때는 STOP 을 누르면 다시 Start 할 수 있습니다. \n\n" +
                        "4. 게임이 끝나고 나면 STOP버튼을 두번 누르면 다시 Start 버튼 으로 시작 할수 있습니다.")
                        .setPositiveButton("나가기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();

                Alertpop.show();
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void setBinddingButtons() {
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
        Resources res = getResources();
        Drawable shape = res.getDrawable(R.drawable.ping);

        for (int i = 0; i < 25; i++) {
            btns[i] = (Button) findViewById(R.id.btn_1 + i);
            btns[i].setOnClickListener(this);
            btns[i].setVisibility(View.INVISIBLE);
            btns[i].setTextSize(20);
            btns[i].setBackgroundColor(Color.parseColor("#ffdddd"));
            btns[i].setTextColor(Color.BLACK);
            btns[i].setPadding(1, 1, 1, 1);
            btns[i].setBackgroundDrawable(shape);
        }
    }

    public void InitValue() {
        iStep = 1;
        bStart = false;
        btn_start.setText("Start");
        tv_time.setText("00:00:00");
    }

    @Override
    public void onClick(View v) {

        if (v == btn_start) {
            if (!bStart) {
                InitValue();
                sw.refresh();
                sw_2.refresh();
                sw_3.refresh();
            }

            bStart = !bStart;

            if (bStart) {

                //1~25버튼 생성
                initNumberArr(1);
                shakeNumber();
                int bWidth = btns[1].getWidth();
                int bheight = btns[1].getHeight();

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        btns[i * 5 + j].setVisibility(View.VISIBLE);
                        btns[i * 5 + j].layout(i * bWidth + 6, j * bheight, i * bWidth + 6 + bWidth, j * bheight + bheight);
                        btns[i * 5 + j].setText("" + ArrNum[i * 5 + j]);
                        btns[i * 5 + j].setTextColor(Color.BLACK);

                        btnNum[ArrNum[i * 5 + j]] = i * 5 + j;
                        btns[i * 5 + j].setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    }
                }

                startThread();
                sw.start();
                sw_2.start();
                sw_3.start();
                // 26~50 버튼 생성

                initNumberArr(26);
                shakeNumber();


            } else {
                stopThread();
                stopThread2();
            }

            btn_start.setText(bStart ? "Stop" : "Start");


        } else {

            int x = Integer.parseInt(((Button) v).getText().toString());

            if (x == iStep) {
                stopThread2();
                sw_2.refresh();
                sw_2.start();
                ((Button) v).setTextColor(Color.BLACK);
                if (iStep >= 26) {
                    if (((Button) v).getVisibility() == View.VISIBLE) {
                        ((Button) v).setVisibility(View.INVISIBLE);
                    } else {
                        ((Button) v).setVisibility(View.VISIBLE);

                    }
                } else {
                    ((Button) v).setText("" + ArrNum[25 - iStep]);
                    btnNum[ArrNum[25 - iStep]] = btnNum[iStep];
                }
                iStep++;
            }
            if (iStep == 51) {
                bStart = false;

                sw.stop();
            }
        }
    }

    public void initNumberArr(int nStartNum) {
        for (int i = 0; i < 25; i++) {
            ArrNum[i] = i + nStartNum;
        }
    }

    public void shakeNumber() {

        int x = 0;
        int y = 0;
        int tmp = 0;

        Random ran = new Random();

        for (int i = 0; i < 100; i++) {
            x = ran.nextInt(25);
            y = ran.nextInt(25);

            if (x == y) continue;

            tmp = ArrNum[x];
            ArrNum[x] = ArrNum[y];
            ArrNum[y] = tmp;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private volatile Thread theThread1;

    public synchronized void startThread() {
        if (theThread1 == null) {
            theThread1 = new Thread(null, backgroundTread1, "startThread");
            theThread1.start();
        }
    }

    public synchronized void stopThread() {
        if (theThread1 != null) {
            Thread tmpThread = theThread1;
            theThread1 = null;
            tmpThread.interrupt();
        }
    }

    private Runnable backgroundTread1 = new Runnable() {
        @Override
        public void run() {
            if (Thread.currentThread() == theThread1) {
                while (bStart) {
                    try {
                        theHandle.sendMessage(theHandle.obtainMessage());
                        Thread.sleep(100);
                    } catch (final InterruptedException e) {
                        return;
                    } catch (final Exception e) {
                        return;
                    }
                }
            }
        }

        Handler theHandle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                double ell = sw.getFormatF();
                String strTime = String.format("%02d:%02d:%02d", (int) (ell / 60), (int) (ell % 60),
                        (int) ((ell * 100) % 100));
                tv_time.setText(strTime);
                if (sw_2.getElapsedTimeMilli() >= 3000) startThread2();
                super.handleMessage(msg);
            }
        };
    };

    private volatile Thread theThread2;

    public synchronized void startThread2() {
        if (theThread2 == null) {
            theThread2 = new Thread(null, backgroundTread2, "startThread");
            bBlink = true;
            theThread2.start();
        }
    }

    public synchronized void stopThread2() {
        if (theThread2 != null) {
            Thread tmpThread2 = theThread2;
            theThread2 = null;
            bBlink = false;
            tmpThread2.interrupt();
        }
    }

    private Runnable backgroundTread2 = new Runnable() {
        @Override
        public void run() {
            if (Thread.currentThread() == theThread2) {
                while (bBlink) {
                    try {
                        if (iStep > 25) iNext = iStep - 25;
                        else iNext = iStep;
                        theHandle2.sendMessage(theHandle2.obtainMessage());
                        Thread.sleep(500);
                    } catch (final InterruptedException e) {
                        return;
                    } catch (final Exception e) {
                        return;
                    }
                }
            }
        }

        Handler theHandle2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (bBlink_2 == true) {
                    btns[btnNum[iStep]].setTextColor(Color.parseColor("#0100FF"));
                    bBlink_2 = false;
                } else {
                    btns[btnNum[iStep]].setTextColor(Color.parseColor("#FFE400"));
                    bBlink_2 = true;
                }
                super.handleMessage(msg);
            }
        };
    };
}
