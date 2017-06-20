package org.android.soldesk.intro_slide.chapter;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.android.soldesk.intro_slide.R;
import org.android.soldesk.intro_slide.db.DBHelper;
import org.android.soldesk.intro_slide.vo.Word;

import java.util.ArrayList;
import java.util.List;

public class JChapter4 extends AppCompatActivity {

    List<Word> chap1 = new ArrayList<Word>();
    ImageView btnNext;
    ImageView c1Img1, c1MenuDel, c1MenuWord, btnHome, c1Clear;
    TextView c1Text1, korStudySub;
    int saveNum, random;
    int soundId;
    final SoundPool sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

    DBHelper dbh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter1);

        dbh = new DBHelper(JChapter4.this, "WORD_TABLE.db", null, 1);

        btnNext = (ImageView) findViewById(R.id.btnNext);
        korStudySub = (TextView) findViewById(R.id.korStudySub);
        btnHome = (ImageView) findViewById(R.id.btnHome);
        c1Img1 = (ImageView) findViewById(R.id.c1Img1);
        c1Text1 = (TextView) findViewById(R.id.c1Text1);
        c1MenuDel = (ImageView) findViewById(R.id.c1MenuDel);
        c1MenuWord = (ImageView) findViewById(R.id.c1MenuWord);
        c1Clear = (ImageView) findViewById(R.id.c1Clear);

        Typeface type_E = Typeface.createFromAsset(getAssets(), "TypoWriterBoldDemo.otf");
        korStudySub.setTypeface(type_E);
        Typeface type_K = Typeface.createFromAsset(getAssets(), "sdmiSaeng.ttf");
        c1Text1.setTypeface(type_K);
        korStudySub.setText("Capter 4");
        saveNum = 0;
        random = 0;
        chap1 = dbh.chapselect(4, 2);
        c1Img1.setImageResource(chap1.get(random).getFname());
        c1Text1.setText(chap1.get(random).getName());


        if (chap1.get(random).getWordcheck() == 1) {
            c1MenuWord.setImageResource(R.drawable.bookmarkplus);
        } else {
            c1MenuWord.setImageResource(R.drawable.bookmark);
        }
        if (chap1.get(random).getClear() == 1) {
            c1Clear.setImageResource(R.drawable.checkbtnplus);
        } else {
            c1Clear.setImageResource(R.drawable.checkbtn);
        }

        c1Img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int waitLimit = 1000;
                int waitCounter = 0;
                int throttle = 10;
                soundId = sound.load(getApplicationContext(), chap1.get(random).getSname(), 1);
                sound.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
                while (sound.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f) == 0 && waitCounter < waitLimit) {
                    waitCounter++;
                    SystemClock.sleep(throttle);
                }
            }
        });

        c1MenuDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chap1.size() > 1) {
                    chap1.remove(random);
                    Toast.makeText(getApplication(), "삭제 되었습니다.", Toast.LENGTH_SHORT).show();

                }

                nextt();

            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextt();


            }
        });


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(2, intent);    // int 값은 무작위로 선택 가능 추후 값을 조회시 이용 가능
                finish();
            }
        });

        c1Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbh.clearUpdate(chap1.get(random));
                chap1 = dbh.chapselect(4, 2);
                if (chap1.get(random).getClear() == 1) {
                    c1Clear.setImageResource(R.drawable.checkbtnplus);
                    Toast.makeText(getApplication(), "외웠니? 외운거 맞아?", Toast.LENGTH_SHORT).show();

                } else {
                    c1Clear.setImageResource(R.drawable.checkbtn);
                    Toast.makeText(getApplication(), "까먹었어? 왜? 다시!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        c1MenuWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh.wordUpdate(chap1.get(random));
                chap1 = dbh.chapselect(4, 2);
                if (chap1.get(random).getWordcheck() == 1) {
                    c1MenuWord.setImageResource(R.drawable.bookmarkplus);
                    Toast.makeText(getApplication(), "단어장 추가", Toast.LENGTH_SHORT).show();
                } else {
                    c1MenuWord.setImageResource(R.drawable.bookmark);
                    Toast.makeText(getApplication(), "단어장 삭제", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void nextt() {
        if (chap1.size() > 1) {
            random = (int) (Math.random() * chap1.size());
            while (saveNum == random) {
                random = (int) (Math.random() * chap1.size());
                if (saveNum != random) {
                    break;
                }
            }
            if (chap1.get(random).getWordcheck() == 1) {
                c1MenuWord.setImageResource(R.drawable.bookmarkplus);
            } else {
                c1MenuWord.setImageResource(R.drawable.bookmark);
            }
            if (chap1.get(random).getClear() == 1) {
                c1Clear.setImageResource(R.drawable.checkbtnplus);
            } else {
                c1Clear.setImageResource(R.drawable.checkbtn);
            }
            Log.i("", saveNum + "------------" + random);
            saveNum = random;

            c1Img1.setImageResource(chap1.get(random).getFname());
            c1Text1.setText(chap1.get(random).getName());
        } else {
            Toast.makeText(getApplication(), "마지막 단어입니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(2, intent);
        finish();
        return;
    }
}
