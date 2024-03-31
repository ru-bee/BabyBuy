package com.ruby.valentine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class yes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FFFFFFFF"));
        actionBar.setBackgroundDrawable(colorDrawable);

        ImageView pigImageView = findViewById(R.id.pigImageView);
        Glide.with(this).asGif().load(R.drawable.pig).into(pigImageView);

        ImageView kissuImageView = findViewById(R.id.kissuImageView);
        Glide.with(this).asGif().load(R.drawable.kissu).into(kissuImageView);

        ImageView kissssImageView = findViewById(R.id.kissssImageView);
        Glide.with(this).asGif().load(R.drawable.kissssss).into(kissssImageView);

        ImageView yesImageView = findViewById(R.id.yesImageView);
        Glide.with(this).asGif().load(R.drawable.yes).into(yesImageView);
    }
}