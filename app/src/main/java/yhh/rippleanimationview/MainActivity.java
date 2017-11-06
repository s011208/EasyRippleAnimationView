package yhh.rippleanimationview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RippleAnimationImageView anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anim = (RippleAnimationImageView) findViewById(R.id.anim);
        anim.setMainColor(Color.RED);
        anim.setInnerRadius(50);
        anim.setOuterRadius(200);
        anim.start();
    }
}
