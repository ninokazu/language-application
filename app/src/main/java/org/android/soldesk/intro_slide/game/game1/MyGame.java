package org.android.soldesk.intro_slide.game.game1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.android.soldesk.intro_slide.R;

public class MyGame extends ActionBarActivity implements View.OnClickListener{

    ImageView rock,scissors,paper;
    ImageView winlose,complay;
    TextView wincount;
    String rspResult,wlResult;
    String[] rsp = {"rock","scissors","paper"};
    String[] wl = {"win","lose"};
    int random;
    int count=0;
    Handler handler = new Handler(); // Thread 에서 화면에 그리기 위해서 필요
    int value = 0; // progressBar 값
    int add = 1; // 증가량, 방향
    int max=40;
    Thread t;
    ProgressBar pb;
    ImageView btnMenu,btnMyGamePop;
    Boolean boo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);
        boo = true;
        rock = (ImageView) findViewById(R.id.rockbtn);
        scissors = (ImageView) findViewById(R.id.scissorsbtn);
        paper = (ImageView) findViewById(R.id.paperbtn);
        pb = (ProgressBar) findViewById(R.id.progress);
        winlose = (ImageView)findViewById(R.id.winlose);
        complay = (ImageView)findViewById(R.id.complay);
        wincount = (TextView)findViewById(R.id.wincount);

        btnMenu = (ImageView) findViewById(R.id.btnMenu);

        btnMyGamePop = (ImageView) findViewById(R.id.btnMyGamePop);



        startRSP();

        pb.setMax(max);
        t = new Thread(new Runnable() {
            @Override
            public void run() { // Thread 로 작업할 내용을 구현
                while(boo) {
                    value = value + add;


                    handler.post(new Runnable() {
                        @Override
                        public void run() { // 화면에 변경하는 작업을 구현
                            pb.setProgress(value);
                            if (value==max) {
                                popUpMenu();

                            }
                        }
                    });

                    try {
                        Thread.sleep(100); // 시간지연
                    } catch (InterruptedException e) {    }
                }// end of while

            }
        });
        t.start(); // 쓰레드 시작
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boo=false;
                finish();
            }
        });
        btnMyGamePop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Alertpop = new AlertDialog.Builder(v.getContext());
                Alertpop.setMessage("게임 설명 \n\n" + "1. 기존의 가위바위보와 방식이 다릅니다. \n" +
                        "2. LOSE가 나온 경우 가위바위보에서 져야 합니다. \n" +
                        "예) '가위'가 나왔을 경우, 져야 하기 때문에 '보자기'를 선택합니다. \n\n"+
                        "3. WIN이 나온 경우 가위바위보에서 이겨야 합니다. \n" +
                        "예) '보자기'가 나왔을 경우, 이겨야 하기 때문에 '가위'를 선택합니다. \n\n" +
                        "4. 자신이 몇번 이기는 지 게임하러 가볼까요 ?!")
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

    private void popUpInfo() {
        Intent intent = new Intent(this, MyGamePop.class);
        intent.putExtra("count", count);
        count=0;
        value=max;
        startActivityForResult(intent, 4);
    }

    public void startRSP(){
        if(value<=30){
            max--;
        }
        pb.setMax(max);
        value=0;
        random = (int)(Math.random()*3);
        rspResult = rsp[random];
        random = (int)(Math.random()*2);
        wlResult = wl[random];

        if(rspResult.equals("rock")){
            complay.setImageResource(R.drawable.rock);
        }else if(rspResult.equals("scissors")){
            complay.setImageResource(R.drawable.scissors);
        }else if(rspResult.equals("paper")){
            complay.setImageResource(R.drawable.paper);
        }

        if(wlResult.equals("win")){
            winlose.setImageResource(R.drawable.win);
        }else if(wlResult.equals("lose")){
            winlose.setImageResource(R.drawable.lose);
        }
        rock.setOnClickListener(this);
        scissors.setOnClickListener(this);
        paper.setOnClickListener(this);
    }
    public void popUpMenu(){
        Intent intent = new Intent(this, PopupActivity.class);
        intent.putExtra("count", count);
        count=0;
        value=max;
        startActivityForResult(intent, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rockbtn :
                if(wlResult.equals("win")){
                    if(rspResult.equals("scissors")){
                        count++;
                        wincount.setText("승 : "+count);
                        startRSP();
                    }else{
                        popUpMenu();
                        count=0;
                        wincount.setText("승 : "+count);
                    }
                }else{
                    if(rspResult.equals("paper")){
                        count++;
                        wincount.setText("승 : "+count);
                        startRSP();
                    }else{
                        popUpMenu();
                        count=0;
                        wincount.setText("승 : "+count);
                    }
                }
                break;
            case R.id.scissorsbtn :
                if(wlResult.equals("win")){
                    if(rspResult.equals("paper")){
                        count++;
                        wincount.setText("승 : "+count);
                        startRSP();
                    }else{
                        popUpMenu();
                        count=0;
                        wincount.setText("승 : "+count);
                    }
                }else{
                    if(rspResult.equals("rock")){
                        count++;
                        wincount.setText("승 : "+count);
                        startRSP();
                    }else{
                        popUpMenu();
                        count=0;
                        wincount.setText("승 : "+count);
                    }
                }
                break;
            case R.id.paperbtn :
                if(wlResult.equals("win")){
                    if(rspResult.equals("rock")){
                        count++;
                        wincount.setText("승 : "+count);
                        startRSP();
                    }else{
                        popUpMenu();
                        count=0;
                        wincount.setText("승 : "+count);
                    }
                }else{
                    if(rspResult.equals("scissors")){
                        count++;
                        wincount.setText("승 : "+count);
                        startRSP();
                    }else{
                        popUpMenu();
                        count=0;
                        wincount.setText("승 : "+count);
                    }
                }
                break;
        }

    }
    @Override
    public void onBackPressed() {

        return;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                finish();
            }else if(resultCode==2){
                count=0;
                wincount.setText("승 : "+count);
                startRSP();
            }
        }else if(requestCode==4){
            startRSP();
        }
    }
}
