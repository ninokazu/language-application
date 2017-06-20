package org.android.soldesk.intro_slide.word;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;

import org.android.soldesk.intro_slide.R;
import org.android.soldesk.intro_slide.db.DBHelper;
import org.android.soldesk.intro_slide.vo.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shin on 2017-05-10.
 */

public class Japword extends ListActivity {

    DBHelper dbh;
    ArrayList<Word> list;
    List<Word> list2;
    ImageView backbtn;
    ListView listKor;
    CustomChoiceListViewAdapter adapter;
    ImageView deletebtn;
    CheckBox checkBox;

    //final ListViewAdapter adapter = new ListViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korword);
        dbh = new DBHelper(Japword.this, "WORD_TABLE.db", null, 1);
        backbtn = (ImageView) findViewById(R.id.backbtn);
        deletebtn = (ImageView) findViewById(R.id.deletebtn);
        checkBox = (CheckBox) findViewById(R.id.checkBox1);


        adapter = new CustomChoiceListViewAdapter();
        // korword.xml 에서 listview의 id 선언을 "@android:id/list"와 같이 하면 android.R.id.list로 선언해야 한다.
        listKor = (ListView) findViewById(android.R.id.list);
        listKor.setAdapter(adapter);

        list = dbh.wordselect(2);

        for (int i = 0; i < list.size(); i++) {
            adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), list.get(i).getFname()), list.get(i).getName());
        }

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list2=dbh.select();
                if (list2.size()<2) {
                    for (int i=0; i<list.size(); i++) {
                        dbh.insert(list.get(i));
                    }
                }
                finish();


            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checkedItems = listKor.getCheckedItemPositions();
                int count = adapter.getCount() ;

                for (int i = count-1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
                        dbh.wordDelete(list.get(i));
                        list.remove(i) ;
                    }
                }

                // 모든 선택 상태 초기화.
                listKor.clearChoices();

                // adapter가 baseadapter인데 이는 결과값을 바로 출력하지 못하기에 객체를 새로 생성하고 다시 값을 출력함.
                adapter = new CustomChoiceListViewAdapter();
                listKor.setAdapter(adapter);
                for (int i = 0; i < list.size(); i++) {
                    adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), list.get(i).getFname()), list.get(i).getName());
                }
            }


        });

    }
}
