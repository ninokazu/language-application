package org.android.soldesk.intro_slide.word;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;


import org.android.soldesk.intro_slide.R;

/**
 * Created by shin on 2017-05-13.
 */

public class CheckableLinearLayout extends LinearLayout implements Checkable{
    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        // mIsChecked = false;
    }

    @Override
    public void setChecked(boolean checked) {
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox1);

        if (cb.isChecked() != checked) {
            cb.setChecked(checked);

        }
        // CheckBox 가 아닌 View의 상태 변경
    }

    @Override
    public boolean isChecked() {

        CheckBox cb = (CheckBox) findViewById(R.id.checkBox1);
        return cb.isChecked();
        // return mIsChecked;
    }

    @Override
    public void toggle() {
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox1);
        setChecked(cb.isChecked() ? false : true);

        //setChecked(mIsChecked ? false : true);
    }
}
