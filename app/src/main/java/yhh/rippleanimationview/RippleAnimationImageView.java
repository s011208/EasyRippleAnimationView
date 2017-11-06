package yhh.rippleanimationview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class RippleAnimationImageView extends RelativeLayout {
    private int mainColor;
    private int centerImage;
    private int innerRadius;
    private int outerRadius;

    private AnimationView animationView;
    private ValueAnimator valueAnimator;
    private int animatorValue;

    public RippleAnimationImageView(Context context) {
        this(context, null);
    }

    public RippleAnimationImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleAnimationImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        animationView = new AnimationView(context);
        animationView.setBackgroundColor(Color.BLACK);
        addView(animationView, 500, 500);

        valueAnimator = ValueAnimator.ofInt(60, 150);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animatorValue = (int) valueAnimator.getAnimatedValue();
                animationView.invalidate();
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setInnerRadius(int radius) {
        innerRadius = radius;
    }

    public void setOuterRadius(int radius) {
        outerRadius = radius;
    }

    public void setMainColor(int color) {
        mainColor = color;
    }

    public void start() {
        valueAnimator.start();
    }

    private class AnimationView extends View {
        private Paint innerPaint;
        private Paint outerPaint;
        private int paintColor = Color.argb(0x60, 0xff, 0x77, 0x44);

        public AnimationView(Context context) {
            super(context);
            innerPaint = new Paint();
            innerPaint.setColor(paintColor);

            outerPaint = new Paint();
            outerPaint.setColor(paintColor);
            outerPaint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int s = 90;
            innerPaint.setAlpha(255 - animatorValue);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, animatorValue > s ? s : animatorValue, innerPaint);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, animatorValue < s ? 0 : animatorValue, outerPaint);
        }
    }
}
