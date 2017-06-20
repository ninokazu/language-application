package org.android.soldesk.intro_slide;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.android.soldesk.intro_slide.KorCategory.JapCategory;
import org.android.soldesk.intro_slide.KorCategory.KorCategory;
import org.android.soldesk.intro_slide.db.DBHelper;
import org.android.soldesk.intro_slide.vo.Word;

import java.util.ArrayList;
import java.util.List;


public class StudyLanguageActivity extends AppCompatActivity {

    ImageView btnKor, btnJap;
    DBHelper dbh;
    List<Word> list, list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_language);
        dbh = new DBHelper(StudyLanguageActivity.this, "WORD_TABLE.db", null, 1);
        btnKor = (ImageView) findViewById(R.id.btnKor);
        btnJap = (ImageView) findViewById(R.id.btnJap);


        list = new ArrayList<>();
        //텍스트, 이미지 파일, 녹음파일, 챕터, 한/일 구별
        wordList();


        btnKor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KorCategory.class);
                list2 = dbh.select();
                if (list2.size() < 2) {
                    for (int i = 0; i < list.size(); i++) {
                        dbh.insert(list.get(i));
                    }
                }
                startActivity(intent);
            }
        });
        btnJap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JapCategory.class);
                list2 = dbh.select();
                if (list2.size() < 2) {
                    for (int i = 0; i < list.size(); i++) {
                        dbh.insert(list.get(i));
                    }
                }
                startActivity(intent);
            }
        });

        /*dbh.drop();
        dbh.create();*/
    }

    // 단어생성
    public void wordList() {
        // 한글
        // Kchap 1
        list.add(new Word("형,오빠", getResources().getIdentifier("bigbro", "drawable", getPackageName()), getResources().getIdentifier("bigbro", "raw", getPackageName()), 1, 1));
        list.add(new Word("언니,누나", getResources().getIdentifier("bigsister", "drawable", getPackageName()), getResources().getIdentifier("bigsister", "raw", getPackageName()), 1, 1));
        list.add(new Word("남자", getResources().getIdentifier("boy", "drawable", getPackageName()), getResources().getIdentifier("boy", "raw", getPackageName()), 1, 1));
        list.add(new Word("어린이", getResources().getIdentifier("children", "drawable", getPackageName()), getResources().getIdentifier("children", "raw", getPackageName()), 1, 1));
        list.add(new Word("아빠", getResources().getIdentifier("dad", "drawable", getPackageName()), getResources().getIdentifier("dad", "raw", getPackageName()), 1, 1));
        list.add(new Word("여자", getResources().getIdentifier("girl", "drawable", getPackageName()), getResources().getIdentifier("girl", "raw", getPackageName()), 1, 1));
        list.add(new Word("할아버지", getResources().getIdentifier("grandf", "drawable", getPackageName()), getResources().getIdentifier("grandf", "raw", getPackageName()), 1, 1));
        list.add(new Word("할머니", getResources().getIdentifier("grandm", "drawable", getPackageName()), getResources().getIdentifier("grandm", "raw", getPackageName()), 1, 1));
        list.add(new Word("엄마", getResources().getIdentifier("mom", "drawable", getPackageName()), getResources().getIdentifier("mom", "raw", getPackageName()), 1, 1));
        list.add(new Word("노인", getResources().getIdentifier("older", "drawable", getPackageName()), getResources().getIdentifier("older", "raw", getPackageName()), 1, 1));
        list.add(new Word("양파", getResources().getIdentifier("onion", "drawable", getPackageName()), getResources().getIdentifier("onion", "raw", getPackageName()), 1, 1));
        list.add(new Word("토끼", getResources().getIdentifier("rabbit", "drawable", getPackageName()), getResources().getIdentifier("rabbit", "raw", getPackageName()), 1, 1));
        list.add(new Word("축구공", getResources().getIdentifier("soccerball", "drawable", getPackageName()), getResources().getIdentifier("soccerball", "raw", getPackageName()), 1, 1));
        list.add(new Word("딸기", getResources().getIdentifier("strawberry", "drawable", getPackageName()), getResources().getIdentifier("strawberry", "raw", getPackageName()), 1, 1));
        list.add(new Word("학생", getResources().getIdentifier("student", "drawable", getPackageName()), getResources().getIdentifier("student", "raw", getPackageName()), 1, 1));
        list.add(new Word("선생님", getResources().getIdentifier("teacher", "drawable", getPackageName()), getResources().getIdentifier("teacher", "raw", getPackageName()), 1, 1));
        list.add(new Word("토마토", getResources().getIdentifier("tomato", "drawable", getPackageName()), getResources().getIdentifier("tomato", "raw", getPackageName()), 1, 1));
        list.add(new Word("수박", getResources().getIdentifier("watermelon", "drawable", getPackageName()), getResources().getIdentifier("watermelon", "raw", getPackageName()), 1, 1));
        list.add(new Word("무", getResources().getIdentifier("whiteradish", "drawable", getPackageName()), getResources().getIdentifier("whiteradish", "raw", getPackageName()), 1, 1));
        list.add(new Word("동생", getResources().getIdentifier("youngsis_bro", "drawable", getPackageName()), getResources().getIdentifier("youngsis_bro", "raw", getPackageName()), 1, 1));

        // Kchap 2
        list.add(new Word("영,공", getResources().getIdentifier("zero", "drawable", getPackageName()), getResources().getIdentifier("zero", "raw", getPackageName()), 2, 1));
        list.add(new Word("일,하나", getResources().getIdentifier("one", "drawable", getPackageName()), getResources().getIdentifier("one", "raw", getPackageName()), 2, 1));
        list.add(new Word("이,둘", getResources().getIdentifier("two", "drawable", getPackageName()), getResources().getIdentifier("two", "raw", getPackageName()), 2, 1));
        list.add(new Word("삼,셋", getResources().getIdentifier("three", "drawable", getPackageName()), getResources().getIdentifier("three", "raw", getPackageName()), 2, 1));
        list.add(new Word("사,넷", getResources().getIdentifier("four", "drawable", getPackageName()), getResources().getIdentifier("four", "raw", getPackageName()), 2, 1));
        list.add(new Word("오,다섯", getResources().getIdentifier("five", "drawable", getPackageName()), getResources().getIdentifier("five", "raw", getPackageName()), 2, 1));
        list.add(new Word("육,여섯", getResources().getIdentifier("six", "drawable", getPackageName()), getResources().getIdentifier("six", "raw", getPackageName()), 2, 1));
        list.add(new Word("칠,일곱", getResources().getIdentifier("seven", "drawable", getPackageName()), getResources().getIdentifier("seven", "raw", getPackageName()), 2, 1));
        list.add(new Word("팔,여덟", getResources().getIdentifier("eight", "drawable", getPackageName()), getResources().getIdentifier("eight", "raw", getPackageName()), 2, 1));
        list.add(new Word("구,아홉", getResources().getIdentifier("nine", "drawable", getPackageName()), getResources().getIdentifier("nine", "raw", getPackageName()), 2, 1));
        list.add(new Word("십,열", getResources().getIdentifier("ten", "drawable", getPackageName()), getResources().getIdentifier("ten", "raw", getPackageName()), 2, 1));
        list.add(new Word("이십,스물", getResources().getIdentifier("twenty", "drawable", getPackageName()), getResources().getIdentifier("twenty", "raw", getPackageName()), 2, 1));
        list.add(new Word("삼십,서른", getResources().getIdentifier("thirty", "drawable", getPackageName()), getResources().getIdentifier("thirty", "raw", getPackageName()), 2, 1));
        list.add(new Word("사십,마흔", getResources().getIdentifier("fourty", "drawable", getPackageName()), getResources().getIdentifier("fourty", "raw", getPackageName()), 2, 1));
        list.add(new Word("오십,쉰", getResources().getIdentifier("fifty", "drawable", getPackageName()), getResources().getIdentifier("fifty", "raw", getPackageName()), 2, 1));
        list.add(new Word("육십,예순", getResources().getIdentifier("sixty", "drawable", getPackageName()), getResources().getIdentifier("sixty", "raw", getPackageName()), 2, 1));
        list.add(new Word("칠십,일흔", getResources().getIdentifier("seventy", "drawable", getPackageName()), getResources().getIdentifier("seventy", "raw", getPackageName()), 2, 1));
        list.add(new Word("팔십,여든", getResources().getIdentifier("eighty", "drawable", getPackageName()), getResources().getIdentifier("eighty", "raw", getPackageName()), 2, 1));
        list.add(new Word("구십,아흔", getResources().getIdentifier("ninety", "drawable", getPackageName()), getResources().getIdentifier("ninety", "raw", getPackageName()), 2, 1));
        list.add(new Word("백", getResources().getIdentifier("hundred", "drawable", getPackageName()), getResources().getIdentifier("hundred", "raw", getPackageName()), 2, 1));

        // Kchap 3
        list.add(new Word("개미", getResources().getIdentifier("ant", "drawable", getPackageName()), getResources().getIdentifier("ant", "raw", getPackageName()), 3, 1));
        list.add(new Word("사과", getResources().getIdentifier("apple", "drawable", getPackageName()), getResources().getIdentifier("apple", "raw", getPackageName()), 3, 1));
        list.add(new Word("바나나", getResources().getIdentifier("banana", "drawable", getPackageName()), getResources().getIdentifier("banana", "raw", getPackageName()), 3, 1));
        list.add(new Word("양배추", getResources().getIdentifier("cabbage", "drawable", getPackageName()), getResources().getIdentifier("cabbage", "raw", getPackageName()), 3, 1));
        list.add(new Word("밤", getResources().getIdentifier("chestnut", "drawable", getPackageName()), getResources().getIdentifier("chestnut", "raw", getPackageName()), 3, 1));
        list.add(new Word("옥수수", getResources().getIdentifier("corn", "drawable", getPackageName()), getResources().getIdentifier("corn", "raw", getPackageName()), 3, 1));
        list.add(new Word("오이", getResources().getIdentifier("cucumber", "drawable", getPackageName()), getResources().getIdentifier("cucumber", "raw", getPackageName()), 3, 1));
        list.add(new Word("사슴", getResources().getIdentifier("deer", "drawable", getPackageName()), getResources().getIdentifier("deer", "raw", getPackageName()), 3, 1));
        list.add(new Word("비둘기", getResources().getIdentifier("dove", "drawable", getPackageName()), getResources().getIdentifier("dove", "raw", getPackageName()), 3, 1));
        list.add(new Word("개구리", getResources().getIdentifier("frog", "drawable", getPackageName()), getResources().getIdentifier("frog", "raw", getPackageName()), 3, 1));
        list.add(new Word("귤", getResources().getIdentifier("mandarin", "drawable", getPackageName()), getResources().getIdentifier("mandarin", "raw", getPackageName()), 3, 1));
        list.add(new Word("버섯", getResources().getIdentifier("mushroom", "drawable", getPackageName()), getResources().getIdentifier("mushroom", "raw", getPackageName()), 3, 1));
        list.add(new Word("공책", getResources().getIdentifier("note", "drawable", getPackageName()), getResources().getIdentifier("note", "raw", getPackageName()), 3, 1));
        list.add(new Word("복숭아", getResources().getIdentifier("peach", "drawable", getPackageName()), getResources().getIdentifier("peach", "raw", getPackageName()), 3, 1));
        list.add(new Word("배", getResources().getIdentifier("pear", "drawable", getPackageName()), getResources().getIdentifier("pear", "raw", getPackageName()), 3, 1));
        list.add(new Word("펭귄", getResources().getIdentifier("penguin", "drawable", getPackageName()), getResources().getIdentifier("penguin", "raw", getPackageName()), 3, 1));
        list.add(new Word("감", getResources().getIdentifier("persimmon", "drawable", getPackageName()), getResources().getIdentifier("persimmon", "raw", getPackageName()), 3, 1));
        list.add(new Word("호박", getResources().getIdentifier("pumkin", "drawable", getPackageName()), getResources().getIdentifier("pumkin", "raw", getPackageName()), 3, 1));
        list.add(new Word("가위", getResources().getIdentifier("scissors_1", "drawable", getPackageName()), getResources().getIdentifier("scissors_1", "raw", getPackageName()), 3, 1));
        list.add(new Word("양", getResources().getIdentifier("sheep", "drawable", getPackageName()), getResources().getIdentifier("sheep", "raw", getPackageName()), 3, 1));

        // Kchap 4
        list.add(new Word("벌", getResources().getIdentifier("bee", "drawable", getPackageName()), getResources().getIdentifier("bee", "raw", getPackageName()), 4, 1));
        list.add(new Word("책", getResources().getIdentifier("book", "drawable", getPackageName()), getResources().getIdentifier("book", "raw", getPackageName()), 4, 1));
        list.add(new Word("나비", getResources().getIdentifier("butterfly", "drawable", getPackageName()), getResources().getIdentifier("butterfly", "raw", getPackageName()), 4, 1));
        list.add(new Word("당근", getResources().getIdentifier("carrots", "drawable", getPackageName()), getResources().getIdentifier("carrots", "raw", getPackageName()), 4, 1));
        list.add(new Word("고양이", getResources().getIdentifier("cat", "drawable", getPackageName()), getResources().getIdentifier("cat", "raw", getPackageName()), 4, 1));
        list.add(new Word("시계", getResources().getIdentifier("clock", "drawable", getPackageName()), getResources().getIdentifier("clock", "raw", getPackageName()), 4, 1));
        list.add(new Word("게", getResources().getIdentifier("crab", "drawable", getPackageName()), getResources().getIdentifier("crab", "raw", getPackageName()), 4, 1));
        list.add(new Word("돌고래", getResources().getIdentifier("dolphin", "drawable", getPackageName()), getResources().getIdentifier("dolphin", "raw", getPackageName()), 4, 1));
        list.add(new Word("가지", getResources().getIdentifier("eggpla", "drawable", getPackageName()), getResources().getIdentifier("eggpla", "raw", getPackageName()), 4, 1));
        list.add(new Word("지우개", getResources().getIdentifier("eraser", "drawable", getPackageName()), getResources().getIdentifier("eraser", "raw", getPackageName()), 4, 1));
        list.add(new Word("마늘", getResources().getIdentifier("garlic", "drawable", getPackageName()), getResources().getIdentifier("garlic", "raw", getPackageName()), 4, 1));
        list.add(new Word("포도", getResources().getIdentifier("grape", "drawable", getPackageName()), getResources().getIdentifier("grape", "raw", getPackageName()), 4, 1));
        list.add(new Word("파인애플", getResources().getIdentifier("pain", "drawable", getPackageName()), getResources().getIdentifier("pine", "raw", getPackageName()), 4, 1));
        list.add(new Word("연필", getResources().getIdentifier("pencil", "drawable", getPackageName()), getResources().getIdentifier("pencil", "raw", getPackageName()), 4, 1));
        list.add(new Word("감자", getResources().getIdentifier("potato", "drawable", getPackageName()), getResources().getIdentifier("potato", "raw", getPackageName()), 4, 1));
        list.add(new Word("달팽이", getResources().getIdentifier("snail", "drawable", getPackageName()), getResources().getIdentifier("snail", "raw", getPackageName()), 4, 1));
        list.add(new Word("고구마", getResources().getIdentifier("sweetpotato", "drawable", getPackageName()), getResources().getIdentifier("sweetpotato", "raw", getPackageName()), 4, 1));
        list.add(new Word("잠자리", getResources().getIdentifier("tombo", "drawable", getPackageName()), getResources().getIdentifier("tombo", "raw", getPackageName()), 4, 1));
        list.add(new Word("거북이", getResources().getIdentifier("turtle", "drawable", getPackageName()), getResources().getIdentifier("turtle", "raw", getPackageName()), 4, 1));
        list.add(new Word("우산", getResources().getIdentifier("umbrella", "drawable", getPackageName()), getResources().getIdentifier("umbrella", "raw", getPackageName()), 4, 1));

        // Kchap 5
        list.add(new Word("곰", getResources().getIdentifier("bear", "drawable", getPackageName()), getResources().getIdentifier("bear", "raw", getPackageName()), 5, 1));
        list.add(new Word("나뭇가지", getResources().getIdentifier("bough", "drawable", getPackageName()), getResources().getIdentifier("bough", "raw", getPackageName()), 5, 1));
        list.add(new Word("닭", getResources().getIdentifier("chicken", "drawable", getPackageName()), getResources().getIdentifier("chicken", "raw", getPackageName()), 5, 1));
        list.add(new Word("소", getResources().getIdentifier("cow", "drawable", getPackageName()), getResources().getIdentifier("cow", "raw", getPackageName()), 5, 1));
        list.add(new Word("색연필", getResources().getIdentifier("crayons", "drawable", getPackageName()), getResources().getIdentifier("crayons", "raw", getPackageName()), 5, 1));
        list.add(new Word("코끼리", getResources().getIdentifier("elephant", "drawable", getPackageName()), getResources().getIdentifier("elephant", "raw", getPackageName()), 5, 1));
        list.add(new Word("가족", getResources().getIdentifier("family", "drawable", getPackageName()), getResources().getIdentifier("family", "raw", getPackageName()), 5, 1));
        list.add(new Word("꽃", getResources().getIdentifier("flower", "drawable", getPackageName()), getResources().getIdentifier("flower", "raw", getPackageName()), 5, 1));
        list.add(new Word("자라다", getResources().getIdentifier("grow", "drawable", getPackageName()), getResources().getIdentifier("grow", "raw", getPackageName()), 5, 1));
        list.add(new Word("말", getResources().getIdentifier("horse", "drawable", getPackageName()), getResources().getIdentifier("horse", "raw", getPackageName()), 5, 1));
        list.add(new Word("잎", getResources().getIdentifier("leaf", "drawable", getPackageName()), getResources().getIdentifier("leaf", "raw", getPackageName()), 5, 1));
        list.add(new Word("돼지", getResources().getIdentifier("pig", "drawable", getPackageName()), getResources().getIdentifier("pig", "raw", getPackageName()), 5, 1));
        list.add(new Word("장미", getResources().getIdentifier("rose", "drawable", getPackageName()), getResources().getIdentifier("rose", "raw", getPackageName()), 5, 1));
        list.add(new Word("울다", getResources().getIdentifier("sad", "drawable", getPackageName()), getResources().getIdentifier("sad", "raw", getPackageName()), 5, 1));
        list.add(new Word("가죽", getResources().getIdentifier("skin", "drawable", getPackageName()), getResources().getIdentifier("skin", "raw", getPackageName()), 5, 1));
        list.add(new Word("거미", getResources().getIdentifier("spider", "drawable", getPackageName()), getResources().getIdentifier("spider", "raw", getPackageName()), 5, 1));
        list.add(new Word("꼬리", getResources().getIdentifier("tail", "drawable", getPackageName()), getResources().getIdentifier("tail", "raw", getPackageName()), 5, 1));
        list.add(new Word("호랑이", getResources().getIdentifier("tiger", "drawable", getPackageName()), getResources().getIdentifier("tiger", "raw", getPackageName()), 5, 1));
        list.add(new Word("나무", getResources().getIdentifier("tree", "drawable", getPackageName()), getResources().getIdentifier("tree", "raw", getPackageName()), 5, 1));
        list.add(new Word("고래", getResources().getIdentifier("whale", "drawable", getPackageName()), getResources().getIdentifier("whale", "raw", getPackageName()), 5, 1));

        // 일본어
        // Jchap 1
        list.add(new Word("おにいさん", getResources().getIdentifier("bigbro", "drawable", getPackageName()), getResources().getIdentifier("bigbro_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("おねえさん", getResources().getIdentifier("bigsister", "drawable", getPackageName()), getResources().getIdentifier("bigsis_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("おとこ", getResources().getIdentifier("boy", "drawable", getPackageName()), getResources().getIdentifier("boy_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("こども", getResources().getIdentifier("children", "drawable", getPackageName()), getResources().getIdentifier("child_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("おとうさん", getResources().getIdentifier("dad", "drawable", getPackageName()), getResources().getIdentifier("dad_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("おんな", getResources().getIdentifier("girl", "drawable", getPackageName()), getResources().getIdentifier("girl_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("おじいさん", getResources().getIdentifier("grandf", "drawable", getPackageName()), getResources().getIdentifier("grandfa_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("おばあさん", getResources().getIdentifier("grandm", "drawable", getPackageName()), getResources().getIdentifier("grandm_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("おかあさん", getResources().getIdentifier("mom", "drawable", getPackageName()), getResources().getIdentifier("mom_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("ろうじん", getResources().getIdentifier("older", "drawable", getPackageName()), getResources().getIdentifier("older_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("たまねぎ", getResources().getIdentifier("onion", "drawable", getPackageName()), getResources().getIdentifier("onion_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("おとうと", getResources().getIdentifier("youngbro_ja", "drawable", getPackageName()), getResources().getIdentifier("youngbro_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("さる", getResources().getIdentifier("monkey_ja", "drawable", getPackageName()), getResources().getIdentifier("monkey_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("いちご", getResources().getIdentifier("strawberry", "drawable", getPackageName()), getResources().getIdentifier("strawberry_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("がくせい", getResources().getIdentifier("student", "drawable", getPackageName()), getResources().getIdentifier("student_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("せんせい", getResources().getIdentifier("teacher", "drawable", getPackageName()), getResources().getIdentifier("teacher_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("とまと", getResources().getIdentifier("tomato", "drawable", getPackageName()), getResources().getIdentifier("tomato_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("すいか", getResources().getIdentifier("watermelon", "drawable", getPackageName()), getResources().getIdentifier("watermelon_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("だいこん", getResources().getIdentifier("whiteradish", "drawable", getPackageName()), getResources().getIdentifier("whiteradish_ja", "raw", getPackageName()), 1, 2));
        list.add(new Word("いもうと", getResources().getIdentifier("youngsister_ja", "drawable", getPackageName()), getResources().getIdentifier("youngsister_ja", "raw", getPackageName()), 1, 2));

        // Jchap 2
        list.add(new Word("れい,ぜろ", getResources().getIdentifier("zero", "drawable", getPackageName()), getResources().getIdentifier("zero_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("いち", getResources().getIdentifier("one", "drawable", getPackageName()), getResources().getIdentifier("one_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("に", getResources().getIdentifier("two", "drawable", getPackageName()), getResources().getIdentifier("two_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("さん", getResources().getIdentifier("three", "drawable", getPackageName()), getResources().getIdentifier("three_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("よん、し", getResources().getIdentifier("four", "drawable", getPackageName()), getResources().getIdentifier("four_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("ご", getResources().getIdentifier("five", "drawable", getPackageName()), getResources().getIdentifier("five_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("ろく", getResources().getIdentifier("six", "drawable", getPackageName()), getResources().getIdentifier("six_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("なな、しち", getResources().getIdentifier("seven", "drawable", getPackageName()), getResources().getIdentifier("seven_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("はち", getResources().getIdentifier("eight", "drawable", getPackageName()), getResources().getIdentifier("eight_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("く、きゅう", getResources().getIdentifier("nine", "drawable", getPackageName()), getResources().getIdentifier("nine_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("じゅう", getResources().getIdentifier("ten", "drawable", getPackageName()), getResources().getIdentifier("ten_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("にじゅう", getResources().getIdentifier("twenty", "drawable", getPackageName()), getResources().getIdentifier("twenty_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("さんじゅう", getResources().getIdentifier("thirty", "drawable", getPackageName()), getResources().getIdentifier("thirty_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("よんじゅう", getResources().getIdentifier("fourty", "drawable", getPackageName()), getResources().getIdentifier("fourty_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("ごじゅう", getResources().getIdentifier("fifty", "drawable", getPackageName()), getResources().getIdentifier("fifty_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("ろくじゅう", getResources().getIdentifier("sixty", "drawable", getPackageName()), getResources().getIdentifier("sixty_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("ななじゅう", getResources().getIdentifier("seventy", "drawable", getPackageName()), getResources().getIdentifier("seventy_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("はちじゅう", getResources().getIdentifier("eighty", "drawable", getPackageName()), getResources().getIdentifier("eighty_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("きゅうじゅう", getResources().getIdentifier("ninety", "drawable", getPackageName()), getResources().getIdentifier("ninety_ja", "raw", getPackageName()), 2, 2));
        list.add(new Word("ひゃく", getResources().getIdentifier("hundred", "drawable", getPackageName()), getResources().getIdentifier("hundred_ja", "raw", getPackageName()), 2, 2));

        // Jchap 3
        list.add(new Word("あり", getResources().getIdentifier("ant", "drawable", getPackageName()), getResources().getIdentifier("ant_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("りんご", getResources().getIdentifier("apple", "drawable", getPackageName()), getResources().getIdentifier("apple_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("ばなな", getResources().getIdentifier("banana", "drawable", getPackageName()), getResources().getIdentifier("banana_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("キャベツ", getResources().getIdentifier("cabbage", "drawable", getPackageName()), getResources().getIdentifier("cabbage_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("くり", getResources().getIdentifier("chestnut", "drawable", getPackageName()), getResources().getIdentifier("chestnut_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("とうもろこし", getResources().getIdentifier("corn", "drawable", getPackageName()), getResources().getIdentifier("corn_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("きゅうり", getResources().getIdentifier("cucumber", "drawable", getPackageName()), getResources().getIdentifier("cucumber_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("しか", getResources().getIdentifier("deer", "drawable", getPackageName()), getResources().getIdentifier("deer_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("はと", getResources().getIdentifier("dove", "drawable", getPackageName()), getResources().getIdentifier("dove_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("かえる", getResources().getIdentifier("frog", "drawable", getPackageName()), getResources().getIdentifier("frog_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("みかん", getResources().getIdentifier("mandarin", "drawable", getPackageName()), getResources().getIdentifier("mandarin_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("きのこ", getResources().getIdentifier("mushroom", "drawable", getPackageName()), getResources().getIdentifier("mushroom_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("ノート", getResources().getIdentifier("note", "drawable", getPackageName()), getResources().getIdentifier("note_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("もも", getResources().getIdentifier("peach", "drawable", getPackageName()), getResources().getIdentifier("peach_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("なし", getResources().getIdentifier("pear", "drawable", getPackageName()), getResources().getIdentifier("pear_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("ペンギン", getResources().getIdentifier("penguin", "drawable", getPackageName()), getResources().getIdentifier("penguin_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("かき", getResources().getIdentifier("persimmon", "drawable", getPackageName()), getResources().getIdentifier("persimmon_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("かぼちゃ", getResources().getIdentifier("pumkin", "drawable", getPackageName()), getResources().getIdentifier("pumkin_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("はさみ", getResources().getIdentifier("scissors_1", "drawable", getPackageName()), getResources().getIdentifier("scissor_ja", "raw", getPackageName()), 3, 2));
        list.add(new Word("ひつじ", getResources().getIdentifier("sheep", "drawable", getPackageName()), getResources().getIdentifier("sheep_ja", "raw", getPackageName()), 3, 2));

        // Jchap 4
        list.add(new Word("はち", getResources().getIdentifier("bee", "drawable", getPackageName()), getResources().getIdentifier("bee_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("ほん", getResources().getIdentifier("book", "drawable", getPackageName()), getResources().getIdentifier("book_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("ちょうちょう", getResources().getIdentifier("butterfly", "drawable", getPackageName()), getResources().getIdentifier("butterfly_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("にんじん", getResources().getIdentifier("carrots", "drawable", getPackageName()), getResources().getIdentifier("carrots_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("ねこ", getResources().getIdentifier("cat", "drawable", getPackageName()), getResources().getIdentifier("cat_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("とけい", getResources().getIdentifier("clock", "drawable", getPackageName()), getResources().getIdentifier("clock_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("かに", getResources().getIdentifier("crab", "drawable", getPackageName()), getResources().getIdentifier("crab_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("いるか", getResources().getIdentifier("dolphin", "drawable", getPackageName()), getResources().getIdentifier("dolphin_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("なす", getResources().getIdentifier("eggpla", "drawable", getPackageName()), getResources().getIdentifier("eggpla_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("けしごむ", getResources().getIdentifier("eraser", "drawable", getPackageName()), getResources().getIdentifier("eraser_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("にんにく", getResources().getIdentifier("garlic", "drawable", getPackageName()), getResources().getIdentifier("garlic_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("ぶどう", getResources().getIdentifier("grape", "drawable", getPackageName()), getResources().getIdentifier("grape_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("パイナップル", getResources().getIdentifier("pain", "drawable", getPackageName()), getResources().getIdentifier("pain_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("えんぴつ", getResources().getIdentifier("pencil", "drawable", getPackageName()), getResources().getIdentifier("pencil_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("じゃがいも", getResources().getIdentifier("potato", "drawable", getPackageName()), getResources().getIdentifier("potato_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("かたつむり", getResources().getIdentifier("snail", "drawable", getPackageName()), getResources().getIdentifier("snail_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("さつまいも", getResources().getIdentifier("sweetpotato", "drawable", getPackageName()), getResources().getIdentifier("sweetpotato_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("とんぼ", getResources().getIdentifier("tombo", "drawable", getPackageName()), getResources().getIdentifier("tombo_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("かめ", getResources().getIdentifier("turtle", "drawable", getPackageName()), getResources().getIdentifier("turtle_ja", "raw", getPackageName()), 4, 2));
        list.add(new Word("かさ", getResources().getIdentifier("umbrella", "drawable", getPackageName()), getResources().getIdentifier("umbrella_ja", "raw", getPackageName()), 4, 2));

        // Jchap 5
        list.add(new Word("くま", getResources().getIdentifier("bear", "drawable", getPackageName()), getResources().getIdentifier("bear_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("えだ", getResources().getIdentifier("bough", "drawable", getPackageName()), getResources().getIdentifier("bough_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("とり", getResources().getIdentifier("chicken", "drawable", getPackageName()), getResources().getIdentifier("chicken_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("うし", getResources().getIdentifier("cow", "drawable", getPackageName()), getResources().getIdentifier("cow_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("いろえんぴつ", getResources().getIdentifier("crayons", "drawable", getPackageName()), getResources().getIdentifier("crayons_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("ぞう", getResources().getIdentifier("elephant", "drawable", getPackageName()), getResources().getIdentifier("elephant_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("かぞく", getResources().getIdentifier("family", "drawable", getPackageName()), getResources().getIdentifier("family_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("はな", getResources().getIdentifier("flower", "drawable", getPackageName()), getResources().getIdentifier("flower_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("そだつ", getResources().getIdentifier("grow", "drawable", getPackageName()), getResources().getIdentifier("grow_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("うま", getResources().getIdentifier("horse", "drawable", getPackageName()), getResources().getIdentifier("horse_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("はっぱ", getResources().getIdentifier("leaf", "drawable", getPackageName()), getResources().getIdentifier("leaf_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("ぶた", getResources().getIdentifier("pig", "drawable", getPackageName()), getResources().getIdentifier("pig_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("ばら", getResources().getIdentifier("rose", "drawable", getPackageName()), getResources().getIdentifier("rose_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("なく", getResources().getIdentifier("sad", "drawable", getPackageName()), getResources().getIdentifier("sad_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("かわ", getResources().getIdentifier("skin", "drawable", getPackageName()), getResources().getIdentifier("skin_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("くも", getResources().getIdentifier("spider", "drawable", getPackageName()), getResources().getIdentifier("spider_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("しっぽ", getResources().getIdentifier("tail", "drawable", getPackageName()), getResources().getIdentifier("tail_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("とら", getResources().getIdentifier("tiger", "drawable", getPackageName()), getResources().getIdentifier("tiger_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("き", getResources().getIdentifier("tree", "drawable", getPackageName()), getResources().getIdentifier("tree_ja", "raw", getPackageName()), 5, 2));
        list.add(new Word("くじら", getResources().getIdentifier("whale", "drawable", getPackageName()), getResources().getIdentifier("whale_ja", "raw", getPackageName()), 5, 2));
    }

}
