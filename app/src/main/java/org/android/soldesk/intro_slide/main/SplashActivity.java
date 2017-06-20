package org.android.soldesk.intro_slide.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.android.soldesk.intro_slide.Intro_slideActivity;
import org.android.soldesk.intro_slide.R;

/**
 * Created by shin on 2017-05-23.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(2000); //대기 초 설정
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, Intro_slideActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadein);

        //startActivity(new Intent(this, Intro_slideActivity.class));
        finish();
    }
}
