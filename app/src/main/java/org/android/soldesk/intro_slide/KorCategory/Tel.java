package org.android.soldesk.intro_slide.KorCategory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.android.soldesk.intro_slide.R;


public class Tel extends Activity {

    EditText editOp;
    Button btn_send;
    ImageView btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tel);


        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                editOp = (EditText) findViewById(R.id.editOp);
                String message = editOp.getText().toString();
                btn_send(message);
            }
        });

        btnMenu = (ImageView) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void btn_send(String message) {

        String[] to = new String[]{"sol.ricecake@gmail.com"};
        String subject = ("앱에 관한 문의사항입니다. ");
        Intent emailInt = new Intent(Intent.ACTION_SEND);
        emailInt.putExtra(Intent.EXTRA_EMAIL, to);
        emailInt.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailInt.putExtra(Intent.EXTRA_TEXT, message);
        emailInt.setType("message/rfc822");
        startActivity(Intent.createChooser(emailInt, "Email"));

    }

}