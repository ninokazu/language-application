package org.android.soldesk.intro_slide.KorCategory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.android.soldesk.intro_slide.R;
import org.android.soldesk.intro_slide.StudyLanguageActivity;
import org.android.soldesk.intro_slide.chapter.Chapter1;
import org.android.soldesk.intro_slide.chapter.Chapter2;
import org.android.soldesk.intro_slide.chapter.Chapter3;
import org.android.soldesk.intro_slide.chapter.Chapter4;
import org.android.soldesk.intro_slide.chapter.Chapter5;
import org.android.soldesk.intro_slide.chapter.JChapter1;
import org.android.soldesk.intro_slide.chapter.JChapter2;
import org.android.soldesk.intro_slide.chapter.JChapter3;
import org.android.soldesk.intro_slide.chapter.JChapter4;
import org.android.soldesk.intro_slide.chapter.JChapter5;
import org.android.soldesk.intro_slide.db.DBHelper;
import org.android.soldesk.intro_slide.game.Japgame;
import org.android.soldesk.intro_slide.game.Korgame;
import org.android.soldesk.intro_slide.vo.Word;
import org.android.soldesk.intro_slide.word.Japword;
import org.android.soldesk.intro_slide.word.Korword;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shin on 2017-04-27.
 */

public class JapCategory extends Activity {

    //슬라이드 열기/닫기 플래그
    boolean isPageOpen = false;
    //슬라이드 열기 애니메이션
    Animation translateLeftAnim;
    //슬라이드 닫기 애니메이션
    Animation translateRightAnim;
    //슬라이드 레이아웃
    LinearLayout slidingPage01, slidingPage02;
    DBHelper db;
    ImageView menu;
    int pro;
    ImageView btnLanguage, btnkorword, btnkorgame, btnTel;
    ProgressBar progressBar;
    List<Word> list;
    TextView statusbar, studytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korcategory);

        //UI
        slidingPage01 = (LinearLayout) findViewById(R.id.slidingPage01);
        slidingPage02 = (LinearLayout) findViewById(R.id.slidingPage02);

        // 학습 상태
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // 메뉴버튼
        menu = (ImageView) findViewById(R.id.btnMenu);

        //애니메이션
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        //애니메이션 리스너 설정
        SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animationListener);
        translateRightAnim.setAnimationListener(animationListener);
        db = new DBHelper(JapCategory.this, "WORD_TABLE.db", null, 1);

        statusbar = (TextView) findViewById(R.id.statusbar);
        studytext = (TextView) findViewById(R.id.studytext);

        //폰트
        Typeface type_K = Typeface.createFromAsset(getAssets(), "sdmiSaeng.ttf");
        studytext.setTypeface(type_K);

        // 학습현황
        list = db.proSelect(2);
        if (list == null) {
            pro = 0;
        } else {
            pro = list.size();
        }

        statusbar.setText(pro + " / " + 100);
        studytext.setText("학습 진행 현황 : " + Math.round((((double) pro / 100) * 100)) + ".0 %");
        progressBar.setProgress(pro);


        // 리스트뷰
        ListView listKor;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter();

        // 리스트뷰 참조 및 Adapter 달기
        listKor = (ListView) findViewById(R.id.listKor);
        listKor.setAdapter(adapter);

        // 첫 번째 아이템 추가
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.p1), "1단계");
        // 두 번째 아이템 추가
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.p1), "2단계");
        // 세 번째 아이템 추가
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.p1), "3단계");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.p1), "4단계");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.p1), "5단계");

        listKor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item

                switch (position) {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), JChapter1.class);
                        startActivityForResult(intent, 1);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(), JChapter2.class);
                        startActivityForResult(intent1, 1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getApplicationContext(), JChapter3.class);
                        startActivityForResult(intent2, 1);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getApplicationContext(), JChapter4.class);
                        startActivityForResult(intent3, 1);
                        break;
                    case 4:
                        Intent intent4 = new Intent(getApplicationContext(), JChapter5.class);
                        startActivityForResult(intent4, 1);
                        break;
                }


            }
        });

        listKor.bringChildToFront(slidingPage02);

        // kor카테고리 버튼이동
        btnLanguage = (ImageView) findViewById(R.id.btnLanguage);
        btnkorword = (ImageView) findViewById(R.id.btnKorWord);
        btnkorgame = (ImageView) findViewById(R.id.btnKorGame);
        btnTel = (ImageView) findViewById(R.id.btnTel);

        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnkorword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Japword.class);
                slidingPage01.startAnimation(translateRightAnim);
                slidingPage02.startAnimation(translateRightAnim);
                invisible();
                startActivity(intent);
            }
        });

        btnkorgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Japgame.class);
                slidingPage01.startAnimation(translateRightAnim);
                slidingPage02.startAnimation(translateRightAnim);
                invisible();
                startActivity(intent);
            }
        });

        btnTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Tel.class);
                slidingPage01.startAnimation(translateRightAnim);
                slidingPage02.startAnimation(translateRightAnim);
                invisible();
                startActivityForResult(intent,2);
            }
        });

    }

    public void onSlidingPage02(View v) {
        if (isPageOpen) {
            //애니메이션 시작
            slidingPage01.startAnimation(translateRightAnim);
            slidingPage02.startAnimation(translateRightAnim);
            invisible();
        }
    }

    public void invisible(){
        btnLanguage.setVisibility(View.INVISIBLE);
        btnkorword.setVisibility(View.INVISIBLE);
        btnkorgame.setVisibility(View.INVISIBLE);
        btnTel.setVisibility(View.INVISIBLE);
    }
    public void visible(){
        btnLanguage.setVisibility(View.VISIBLE);
        btnkorword.setVisibility(View.VISIBLE);
        btnkorgame.setVisibility(View.VISIBLE);
        btnTel.setVisibility(View.VISIBLE);
    }

    //버튼
    public void onButton1Clicked(View v) {
        //닫기
        if (isPageOpen) {
            //애니메이션 시작
            slidingPage01.startAnimation(translateRightAnim);
            slidingPage02.startAnimation(translateRightAnim);
            invisible();
            Log.i("btnmenu", "닫힘");
        }
        //열기
        else {
            slidingPage01.setVisibility(View.VISIBLE);
            slidingPage01.startAnimation(translateLeftAnim);
            slidingPage02.setVisibility(View.VISIBLE);
            slidingPage02.startAnimation(translateLeftAnim);
            visible();
            Log.i("btnmenu", "열림");
        }
    }

    //애니메이션 리스너
    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationStart(Animation animation) {
            //슬라이드 열기->닫기
            if (isPageOpen) {
                slidingPage01.setVisibility(View.INVISIBLE);
                slidingPage02.setVisibility(View.INVISIBLE);
                menu.setImageResource(R.drawable.btnmenu_2);
                slidingPage01.setClickable(false);
                slidingPage02.setClickable(false);
                isPageOpen = false;
            }
            //슬라이드 닫기->열기
            else {
                slidingPage01.setClickable(true);
                slidingPage02.setClickable(true);
                menu.setImageResource(R.drawable.btnmenu);
                isPageOpen = true;
            }
        }
    }

    // 홈버튼 클릭시 DB를 학습 진행 현황에 반영
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode==2){
                list = db.proSelect(2);
                if (list == null) {
                    pro = 0;
                } else {
                    pro = list.size();
                }
            }
            statusbar.setText(pro + " / " + 100);
            studytext.setText("학습 진행 현황 : " + Math.round((((double) pro / 100) * 100)) + ".0 %");
            progressBar.setProgress(pro);

        }
    }
}
