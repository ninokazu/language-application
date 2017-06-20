package org.android.soldesk.intro_slide.game.game1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.android.soldesk.intro_slide.R;

/**
 * Created by Hi on 2017-05-17.
 */

public class PopupActivity extends Activity {
    TextView txt;
    Button reStartbtn,endbtn;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_activity);

        reStartbtn = (Button) findViewById(R.id.reStartbtn);
        endbtn = (Button) findViewById(R.id.endbtn);

        intent = getIntent();

        txt = (TextView) findViewById(R.id.txtText);
        int count = intent.getIntExtra("count",0);
        txt.setText("당신의 승 : " + count);

        reStartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                setResult(2,intent);
                finish();
            }
        });
        endbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

}
