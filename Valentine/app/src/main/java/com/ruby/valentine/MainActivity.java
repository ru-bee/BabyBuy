package com.ruby.valentine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private boolean isButtonClicked = false;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FFFFFFFF"));
        actionBar.setBackgroundDrawable(colorDrawable);

        ImageView gifImageView = findViewById(R.id.gifImageView);
        Glide.with(this).asGif().load(R.drawable.jumping).into(gifImageView);


        Button yesBtn = findViewById(R.id.yesBtn);
        Button noBtn = findViewById(R.id.noBtn);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                growButton(yesBtn, 1.2f); // Initial scale factor
                Intent intent = new Intent(MainActivity.this, yes.class);
                startActivity(intent);

            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float currentScaleX = yesBtn.getScaleX();
                float currentScaleY = yesBtn.getScaleY();
                float newScaleFactor = currentScaleX * 1.2f; // Incremental scale factor
                growButton(yesBtn, newScaleFactor);

                if (!isButtonClicked) {
                    isButtonClicked = true;
                    loopMoveButton(noBtn, 200); // Move the button by 200 pixels (adjust as needed)
                } else {
                    isButtonClicked = false;
                }
            }
        });
    }

    private void growButton(final Button button, final float scaleFactor) {
        // Change the scale to make the button appear larger with looping animation
        button.animate().scaleX(scaleFactor).scaleY(scaleFactor).setDuration(200).withEndAction(new Runnable() {
            @Override
            public void run() {
                // Reset the scale to its original size and restart the animation with the updated scale
                button.animate().scaleX(1.0f).scaleY(1.0f).setDuration(0);
                growButton(button, scaleFactor);
            }
        }).start();
    }

    private void loopMoveButton(final Button button, final float translationX) {
        if (isButtonClicked) {
            // Animate the translation of the button
            ObjectAnimator animator = ObjectAnimator.ofFloat(button, "translationX", translationX);
            animator.setDuration(500); // Set the duration of the animation in milliseconds

            // Set up a listener to restart the animation when it ends
            animator.addListener(new android.animation.AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    super.onAnimationEnd(animation);
                    if (isButtonClicked) {
                        button.setTranslationX(0); // Reset the translation
                        loopMoveButton(button, translationX); // Restart the animation
                    }
                }
            });

            animator.start();
        } else {
            handler.removeCallbacksAndMessages(null); // Stop the handler callbacks
        }
    }


}