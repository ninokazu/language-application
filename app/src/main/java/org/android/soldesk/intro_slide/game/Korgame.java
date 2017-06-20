package org.android.soldesk.intro_slide.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.android.soldesk.intro_slide.R;
import org.android.soldesk.intro_slide.game.game1.MyGame;
import org.android.soldesk.intro_slide.game.game2.OneToFitActivity;
import org.android.soldesk.intro_slide.game.game3.WordGameActivity;

/**
 * Created by shin on 2017-05-10.
 */

public class Korgame extends AppCompatActivity{

    ImageView btnMenu;
    ImageView btngame1, btngame2, btngame3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korgame);

        btnMenu = (ImageView) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btngame1 = (ImageView) findViewById(R.id.btngame1);
        btngame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyGame.class);
                startActivity(intent);
            }
        });
        btngame2 = (ImageView) findViewById(R.id.btngame2);
        btngame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OneToFitActivity.class);
                startActivity(intent);
            }
        });
        btngame3 = (ImageView) findViewById(R.id.btngame3);
        btngame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WordGameActivity.class);
                startActivity(intent);
            }
        });
    }
}
