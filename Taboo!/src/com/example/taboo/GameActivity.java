package com.example.taboo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class GameActivity extends Activity {
	
	String[] words = {
            "Pataliputra",
            "Magadha",
            "Kanishka",
            "Gandhara",
            "Ghuri"
        };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}
	
	//ImageView imageView = (ImageView) findViewById(R.id.imageView3);
	//imageView.setImageResource(R.drawable.card_image);
	
}
