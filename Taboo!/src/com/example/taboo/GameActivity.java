package com.example.taboo;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameActivity extends Activity {
	
	RelativeLayout relativelayout1;
	RelativeLayout relativelayout2;
	
	RelativeLayout cardlayout1;
	RelativeLayout cardlayout2;
	
	int card_counter = 0;
	
	int width = 0;
	int height = 0;
	
	int view_width = 0;	
	int view_height = 0;
	
	int red_score = 0;
	int blue_score = 0;
	int num_round = 1;
	int team_number = 0;
	
	String current_team;
		
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
		//relativelayout1 is the base layout
        relativelayout1 = new RelativeLayout(this);
        //relativelayout2 is used for the ready page
        relativelayout2 = new RelativeLayout(this);
        relativelayout2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //cardlayout1 is used for the first draw, and every other draw after
        cardlayout1 = new RelativeLayout(this);
        //cardlayout2 is used for the second draw, and every other draw after
        cardlayout2 = new RelativeLayout(this);
        getTheDisplay();
        readypage();
        
		setContentView(relativelayout1);
	}
	
	private void getviewwidth(View v){
		Display display = getWindowManager().getDefaultDisplay();
		v.measure(display.getWidth(), display.getHeight());
		
		view_width = v.getMeasuredWidth(); // view width
		view_height = v.getMeasuredHeight(); //view height

	}
	
	public void roundfadein(final View i, final View j, final View k, final View l, final View m, final View n, final View o, final View p) {
		final AlphaAnimation roundfade = new AlphaAnimation(0.0f , 1.0f ) ; 
		roundfade.setDuration(1000);
		roundfade.setFillAfter(true);
		i.startAnimation(roundfade);
		
		getviewwidth(k);
		
		roundfade.setAnimationListener(new AnimationListener() {
		    @Override
		    public void onAnimationEnd(Animation animation) {
		    	
			    TranslateAnimation roundmove = new TranslateAnimation(0, 0, 0, (float) -(2*height)/10);
				roundmove.setDuration(800);
				roundmove.setFillAfter(true);
				i.startAnimation(roundmove);
				
		    	j.setX((width/5) + (width/4) + view_width);
		    	k.setX((-width/5) + (width/4));
		    	
		    	l.setX((width/5) + (width/4) + view_width);
		    	m.setX((-width/5) + (width/4));

				AnimationSet animSet1 = new AnimationSet(true);
		    	
		    	final AlphaAnimation scorefade = new AlphaAnimation(0.0f , 1.0f ) ; 
				scorefade.setDuration(800);
				//scorefade.setFillAfter(true);
				scorefade.setStartOffset(400);
				animSet1.addAnimation(scorefade);
				
			    TranslateAnimation scoremove1 = new TranslateAnimation(-width/5, 0, 0, 0);
				scoremove1.setDuration(800);
				scoremove1.setFillAfter(true);
				animSet1.addAnimation(scoremove1);
				
				j.startAnimation(animSet1);
				
				AnimationSet animSet2 = new AnimationSet(true);
		    	
				animSet2.addAnimation(scorefade);
				
			    TranslateAnimation scoremove2 = new TranslateAnimation(width/5, 0, 0, 0);
				scoremove2.setDuration(800);
				scoremove2.setFillAfter(true);
				animSet2.addAnimation(scoremove2);
				
				k.startAnimation(animSet2);
				
				animSet1.setAnimationListener(new AnimationListener() {
				    @Override
				    public void onAnimationEnd(Animation animation) {
				    	j.setVisibility(View.VISIBLE);
				    	k.setVisibility(View.VISIBLE);
				    }
					@Override
					public void onAnimationRepeat(Animation arg0) {
					}
					@Override
					public void onAnimationStart(Animation arg0) {
					}
				});
				
				final AlphaAnimation textfade = new AlphaAnimation(0.0f , 1.0f ) ; 
				textfade.setDuration(800);
				textfade.setFillAfter(true);
				textfade.setStartOffset(800);
				l.startAnimation(textfade);
				m.startAnimation(textfade);
				
				final AlphaAnimation barfade = new AlphaAnimation(0.0f , 1.0f ) ; 
				barfade.setDuration(800);
				barfade.setFillAfter(true);
				barfade.setStartOffset(400);
				n.startAnimation(barfade);
				
				final AnimationSet animSet3 = new AnimationSet(true);
				
				final AlphaAnimation readyfade = new AlphaAnimation(0.0f , 1.0f ) ; 
				readyfade.setDuration(1500);
				readyfade.setFillAfter(true);
				animSet3.addAnimation(readyfade);
				
				final AlphaAnimation readyfadeout = new AlphaAnimation(1.0f , 0.0f ) ; 
				readyfadeout.setDuration(1500);
				readyfadeout.setStartOffset(2000);
				animSet3.addAnimation(readyfadeout);
				
				final AlphaAnimation nextfade = new AlphaAnimation(0.0f , 1.0f ) ; 
				nextfade.setDuration(800);
				nextfade.setFillAfter(true);
				nextfade.setStartOffset(1200);
				p.startAnimation(nextfade);
				


				nextfade.setAnimationListener(new AnimationListener() {
				    @Override
				    public void onAnimationEnd(Animation animation) {
				    	o.startAnimation(animSet3);
				    	readybutton(j, k);
				    }
					@Override
					public void onAnimationRepeat(Animation arg0) {
					}
					@Override
					public void onAnimationStart(Animation arg0) {
					}
				});
				
		    	animSet3.setAnimationListener(new AnimationListener() {
				    @Override
				    public void onAnimationEnd(Animation animation) {
				    	o.startAnimation(animSet3);
				    }
					@Override
					public void onAnimationRepeat(Animation arg0) {
					}
					@Override
					public void onAnimationStart(Animation arg0) {
					}
				});
		    }

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void readypage() {
		declare_team();
		
		//round number text
		TextView roundtext = new TextView(this);
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/GeosansLight.ttf");
		roundtext.setTypeface(type);
		roundtext.setText("Round" + " " + Integer.toString(num_round));
		RelativeLayout.LayoutParams word_dimensions = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
	    word_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
		roundtext.setLayoutParams(word_dimensions);
	    roundtext.setTextSize(height/38);
		roundtext.setGravity(Gravity.CENTER);
		roundtext.setY((7*height)/20);
		relativelayout2.addView(roundtext);
		relativelayout1.addView(relativelayout2);
		
		TextView red_score_text = new TextView(this);
		red_score_text.setText(Integer.toString(red_score));
		red_score_text.setTypeface(type);
    	red_score_text.setTextColor(Color.RED);
		red_score_text.setLayoutParams(word_dimensions);
	    red_score_text.setTextSize(height/45);
		red_score_text.setGravity(Gravity.CENTER);
		red_score_text.setY((4*height)/10);
		red_score_text.setVisibility(View.INVISIBLE);
		relativelayout1.addView(red_score_text);
		
		TextView red_score = new TextView(this);
		red_score.setText("Red Score");
		red_score.setTypeface(type);
		red_score.setTextColor(Color.RED);
		red_score.setLayoutParams(word_dimensions);
		red_score.setTextSize(height/65);
		red_score.setGravity(Gravity.CENTER);
		red_score.setY(((3*height)/10) + height/50);
		red_score.setVisibility(View.INVISIBLE);
		relativelayout2.addView(red_score);
		
		TextView blue_score_text = new TextView(this);
		blue_score_text.setText(Integer.toString(blue_score));
		blue_score_text.setTypeface(type);
    	blue_score_text.setTextColor(Color.BLUE);
		blue_score_text.setLayoutParams(word_dimensions);
	    blue_score_text.setTextSize(height/45);
		blue_score_text.setGravity(Gravity.CENTER);
		blue_score_text.setY((4*height)/10);
		blue_score_text.setVisibility(View.INVISIBLE);
		relativelayout1.addView(blue_score_text);
		
		TextView blue_score = new TextView(this);
		blue_score.setText("Blue Score");
		blue_score.setTypeface(type);
		blue_score.setTextColor(Color.BLUE);
		blue_score.setLayoutParams(word_dimensions);
		blue_score.setTextSize(height/65);
		blue_score.setGravity(Gravity.CENTER);
		blue_score.setY(((3*height)/10) + (height/50));
		blue_score.setVisibility(View.INVISIBLE);
		relativelayout2.addView(blue_score);
		
		TextView tapready = new TextView(this);
		tapready.setText("tap the screen to start");
		tapready.setTypeface(type);
		RelativeLayout.LayoutParams ready_dimensions = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, (7*height/40));		
		ready_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
		tapready.setLayoutParams(ready_dimensions);
		tapready.setTextSize(height/85);
		tapready.setTextColor(Color.GRAY);
		tapready.setGravity(Gravity.CENTER);
		tapready.setY((7*height)/10);
		tapready.setVisibility(View.INVISIBLE);
		relativelayout2.addView(tapready);
		
		final ImageView blackbar = new ImageView(this);
		blackbar.setImageResource(R.drawable.blackbar);
		RelativeLayout.LayoutParams bar_dimensions = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, (7*height/40));		
		bar_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
		blackbar.setLayoutParams(bar_dimensions);
		blackbar.setY((13*height)/40);
		blackbar.setVisibility(View.INVISIBLE);
		relativelayout2.addView(blackbar);
		
		TextView nexteam = new TextView(this);
		nexteam.setText(current_team + " " + "is next");
		nexteam.setTypeface(type);
		nexteam.setLayoutParams(word_dimensions);
		nexteam.setTextSize(height/85);
		nexteam.setGravity(Gravity.CENTER);
		nexteam.setY((11*height)/20);
		nexteam.setVisibility(View.INVISIBLE);
		relativelayout2.addView(nexteam);
		
		roundfadein(roundtext, red_score_text, blue_score_text, red_score, blue_score, blackbar, tapready, nexteam);
		
	}
	
	public void declare_team() {
		if (team_number == 0){
			current_team = "Blue team";
		}
		else {
			current_team = "Red team";
		}
	}
	
	public void animationfadeout(View i) {
		AlphaAnimation fadeOut = new AlphaAnimation(1.0f , 0.0f ) ; 
		fadeOut.setDuration(500);
		i.startAnimation(fadeOut);
		i.setVisibility(View.GONE);
	}
	
	public void readybutton(final View j, final View k) {
		ImageView readybutton = new ImageView(this);
		RelativeLayout.LayoutParams readybutton_dimensions = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);		
		readybutton_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
		readybutton.setLayoutParams(readybutton_dimensions);
		relativelayout2.addView(readybutton);
		
	    ScaleAnimation scaler = new ScaleAnimation(1, width/10, 1, height/10);
		scaler.setDuration(500);
		
		final AnimationSet animSet4 = new AnimationSet(true);
	    TranslateAnimation scoremoving1 = new TranslateAnimation(0, view_width - (3*width/10), 0, -(4*height)/10);
	    scoremoving1.setDuration(500);
	    scoremoving1.setFillAfter(true);
	    animSet4.setFillAfter(true);
		animSet4.addAnimation(scoremoving1);
		//animSet4.addAnimation(scaler);
		
		final AnimationSet animSet5 = new AnimationSet(true);
	    TranslateAnimation scoremoving2 = new TranslateAnimation(0, (3*width/10) - view_width, 0, -(4*height)/10);
	    scoremoving2.setDuration(500);
	    scoremoving2.setFillAfter(true);
	    animSet5.setFillAfter(true);
		animSet5.addAnimation(scoremoving2);
		//animSet5.addAnimation(scaler);
		
		readybutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				animationfadeout(relativelayout2);
				k.startAnimation(animSet4);
				j.startAnimation(animSet5);
				draw_card();
			}
		});
	}
	
	public void draw_card() {
		ImageView card_image = new ImageView(this);
		card_image.setImageResource(R.drawable.card_image2);
		
		AnimationSet carddrawset = new AnimationSet(true);
			TranslateAnimation carddraw = new TranslateAnimation(0, 0, height + (9*height)/10, height/10);
			carddraw.setDuration(500);
			carddraw.setFillAfter(true);
			carddrawset.addAnimation(carddraw);
		
			AlphaAnimation cardfadein = new AlphaAnimation(0.0f , 1.0f ) ; 
			cardfadein.setDuration(500);
			carddrawset.addAnimation(cardfadein);
		
		card_image.startAnimation(carddrawset);
			
		RelativeLayout.LayoutParams card_dimensions = new RelativeLayout.LayoutParams((9*width)/10, (9*height)/10);		
		card_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		if (card_counter > 0){
			card_counter -= 1;
			
		} else {
			card_counter +=1;
			cardlayout1.setLayoutParams(card_dimensions);
			cardlayout1.addView(card_image);
			relativelayout1.addView(cardlayout1);
		}
		
		
	}
	
	private void getTheDisplay() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
    }
	
	public void card_draw() {
		ImageView card = new ImageView(this);
		card.setImageResource(R.drawable.card_image);
		
	}

	
}
