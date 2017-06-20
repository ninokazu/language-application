package org.android.soldesk.intro_slide.game.game3;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.SystemClock;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.android.soldesk.intro_slide.R;
import org.android.soldesk.intro_slide.db.DBHelper;
import org.android.soldesk.intro_slide.vo.Word;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JapWordGameActivity extends Activity {

    TextView tv_info, tv_word;
    EditText et_guess;
    ImageView btnMenu, btnWordPop, b_check, b_new, tv_image;
    boolean bStart = false; // 시작여부
    DBHelper db;
    Random rd;

    String currentWord;

    List<Word> dic;
    int random = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_game);
        db = new DBHelper(JapWordGameActivity.this, "WORD_TABLE.db", null, 1);
        dic = db.gameselect(2);

        btnWordPop = (ImageView) findViewById(R.id.btnWordPop);
        btnWordPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Alertpop = new AlertDialog.Builder(v.getContext());
                Alertpop.setMessage("게임 설명 \n\n" + "1. 단어를 순서대로 정확히 입력하는 게임입니다. \n" +
                        "2. CHECK를 눌러 정답을 확인합니다. \n\n" +
                        "3. 모르는 문제일 경우 NEXT를 눌러 다음 문제로 넘어갑니다. \n\n" +
                        "4. 공부한 단어를 맞추러 가볼까요 ?!")
                        .setPositiveButton("나가기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();

                Alertpop.show();
            }
        });

        btnMenu = (ImageView) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bStart=false;
                finish();
            }
        });

        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_word = (TextView) findViewById(R.id.tv_word);


        et_guess = (EditText) findViewById(R.id.et_guess);

        b_check = (ImageView) findViewById(R.id.b_check);
        b_new = (ImageView) findViewById(R.id.b_new);
        tv_image = (ImageView) findViewById(R.id.tv_image);

        rd = new Random();

        newGame();

        b_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_guess.getText().toString().equalsIgnoreCase(currentWord)) {

                    AlertDialog.Builder myAlert = new AlertDialog.Builder(v.getContext());
                    myAlert.setMessage("정답입니다").setPositiveButton("계속하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dialog.dismiss();
                            newGame();
                        }
                    }).create();

                    myAlert.show();

                    //Toast.makeText(getApplication(), "정답입니다~", Toast.LENGTH_SHORT).show();
                    //tv_info.setText("참잘했어요~");
                    b_check.setEnabled(false);
                    b_new.setEnabled(true);
                } else {

                    AlertDialog.Builder myAlert = new AlertDialog.Builder(v.getContext());
                    myAlert.setMessage("틀렸습니다.").setPositiveButton("다시하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();

                    myAlert.show();
                    //Toast.makeText(getApplication(), "다시하세요!", Toast.LENGTH_SHORT).show();
                    //tv_info.setText("다시 하세요! ");
                }
            }
        });

        b_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
    }

    /*public void popUpMenu() {
        Intent intent = new Intent(this, WordPop.class);
        startActivity(intent);
    }*/

    // shuffle alg
    private String shuffleWord(String word) {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }
        return shuffled;
    }

    private void newGame() {

        // 이미지와 글씨, 소리를 같은 번호로 불러옴

        if (dic.size() > 1) {
            random = (int) (Math.random() * dic.size());
        }
        tv_image.setImageResource(dic.get(random).getFname());

        // get random void from the dic
        currentWord = dic.get(random).getName();

        tv_image.setOnClickListener(new View.OnClickListener() {

            int soundId;
            final SoundPool sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

            @Override
            public void onClick(View v) {

                int waitLimit = 1000;
                int waitCounter = 0;
                int throttle = 10;
                soundId = sound.load(getApplicationContext(), dic.get(random).getSname(), 1);
                sound.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
                while (sound.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f) == 0 && waitCounter < waitLimit) {
                    waitCounter++;
                    SystemClock.sleep(throttle);
                }
            }
        });

        //show the shuffled word
        tv_word.setText(shuffleWord(currentWord));

        //clear the text filed
        et_guess.setText("");

        // switch buttons
        b_new.setEnabled(true);
        b_check.setEnabled(true);
    }
}