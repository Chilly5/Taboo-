package com.example.taboo;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
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
	
	int time = 10;
	int time_initial = 10;
	
	int counter = 60;
	private final long startTime = 30 * 1000;
	private final long interval = 1 * 1000;
	public TextView textCounter;
	
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
        //measure height and width of screen
        getTheDisplay();
        //starts the ready page
        readypageinst();
        
		setContentView(relativelayout1);
	}
	
	private void getviewwidth(View v){
		Display display = getWindowManager().getDefaultDisplay();
		v.measure(display.getWidth(), display.getHeight());
		
		view_width = v.getMeasuredWidth(); // view width
		view_height = v.getMeasuredHeight(); //view height

	}
	
	public void roundfadein (final TextView roundtext, final TextView red_score_text, final TextView blue_score_text, final TextView red_score_title, final TextView blue_score_title, final TextView tapready, final TextView nexteam, final ImageView blackbar, final ImageView readybutton) {
		final AlphaAnimation roundfade = new AlphaAnimation(0.0f , 1.0f ) ; 
		roundfade.setDuration(1000);
		roundfade.setFillAfter(true);
		roundtext.startAnimation(roundfade);
		
		getviewwidth(blue_score_text);
		
		roundfade.setAnimationListener(new AnimationListener() {
		    @Override
		    public void onAnimationEnd(Animation animation) {
		    	
			    TranslateAnimation roundmove = new TranslateAnimation(0, 0, 0, (float) -(2*height)/10);
				roundmove.setDuration(800);
				roundmove.setFillAfter(true);
				roundtext.startAnimation(roundmove);
				
				red_score_text.setX((width/5) + (width/4) + view_width);
				blue_score_text.setX((-width/5) + (width/4));
		    	
				red_score_title.setX((width/5) + (width/4) + view_width);
				blue_score_title.setX((-width/5) + (width/4));

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
				
				red_score_text.startAnimation(animSet1);
				
				AnimationSet animSet2 = new AnimationSet(true);
		    	
				animSet2.addAnimation(scorefade);
				
			    TranslateAnimation scoremove2 = new TranslateAnimation(width/5, 0, 0, 0);
				scoremove2.setDuration(800);
				scoremove2.setFillAfter(true);
				animSet2.addAnimation(scoremove2);
				
				blue_score_text.startAnimation(animSet2);
				
				animSet1.setAnimationListener(new AnimationListener() {
				    @Override
				    public void onAnimationEnd(Animation animation) {
				    	red_score_text.setVisibility(View.VISIBLE);
				    	blue_score_text.setVisibility(View.VISIBLE);
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
				red_score_title.startAnimation(textfade);
				blue_score_title.startAnimation(textfade);
				
				final AlphaAnimation nextfade = new AlphaAnimation(0.0f , 1.0f ) ; 
				nextfade.setDuration(800);
				nextfade.setFillAfter(true);
				nextfade.setStartOffset(1200);
				nexteam.startAnimation(nextfade);
				
				final AnimationSet animSet3 = new AnimationSet(true);
				
				final AlphaAnimation readyfade = new AlphaAnimation(0.0f , 1.0f ) ; 
				readyfade.setDuration(1500);
				readyfade.setFillAfter(true);
				animSet3.addAnimation(readyfade);
				
				final AlphaAnimation readyfadeout = new AlphaAnimation(1.0f , 0.0f ) ; 
				readyfadeout.setDuration(1500);
				readyfadeout.setStartOffset(2000);
				animSet3.addAnimation(readyfadeout);
				
				final AlphaAnimation barfade = new AlphaAnimation(0.0f , 1.0f ) ; 
				barfade.setDuration(800);
				barfade.setFillAfter(true);
				barfade.setStartOffset(400);
				blackbar.startAnimation(barfade);
		
				nextfade.setAnimationListener(new AnimationListener() {
				    @Override
				    public void onAnimationEnd(Animation animation) {
				    	tapready.startAnimation(animSet3);
				    	readybutton(roundtext, red_score_text, blue_score_text, red_score_title, blue_score_title, tapready, nexteam, blackbar, readybutton);
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
				    	tapready.startAnimation(animSet3);
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
	
	public void readypageinst(){
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/GeosansLight.ttf");
		
		TextView roundtext = new TextView(this);
		roundtext.setTypeface(type);
		
		TextView red_score_text = new TextView(this);
		red_score_text.setTypeface(type);

		TextView red_score_title = new TextView(this);
		red_score_title.setTypeface(type);
		
		TextView blue_score_text = new TextView(this);
		blue_score_text.setTypeface(type);

		TextView blue_score_title = new TextView(this);
		blue_score_title.setTypeface(type);
		
		TextView tapready = new TextView(this);
		tapready.setTypeface(type);

		TextView nexteam = new TextView(this);
		nexteam.setTypeface(type);
		
		ImageView blackbar = new ImageView(this);
		blackbar.setImageResource(R.drawable.blackbar);
		
		ImageView readybutton = new ImageView(this);
		
		readypage(roundtext, red_score_text, blue_score_text, red_score_title, blue_score_title, tapready, nexteam, blackbar, readybutton);
	}
	
	
	public void readypage(TextView roundtext, TextView red_score_text, TextView blue_score_text, TextView red_score_title, TextView blue_score_title, TextView tapready, TextView nexteam, ImageView blackbar, ImageView readybutton) {
		declare_team();
		
		//round number text
		roundtext.setText("Round" + " " + Integer.toString(num_round));
		RelativeLayout.LayoutParams word_dimensions = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
	    word_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
		roundtext.setLayoutParams(word_dimensions);
	    roundtext.setTextSize(height/38);
		roundtext.setGravity(Gravity.CENTER);
		roundtext.setY((7*height)/20);
		relativelayout2.addView(roundtext);
		relativelayout1.addView(relativelayout2);
		
		red_score_text.setText(Integer.toString(red_score));
    	red_score_text.setTextColor(Color.RED);
		red_score_text.setLayoutParams(word_dimensions);
	    red_score_text.setTextSize(height/45);
		red_score_text.setGravity(Gravity.CENTER);
		red_score_text.setY((4*height)/10);
		red_score_text.setVisibility(View.INVISIBLE);
		relativelayout1.addView(red_score_text);
		
		red_score_title.setText("Red Score");
		red_score_title.setTextColor(Color.RED);
		red_score_title.setLayoutParams(word_dimensions);
		red_score_title.setTextSize(height/65);
		red_score_title.setGravity(Gravity.CENTER);
		red_score_title.setY(((3*height)/10) + height/50);
		red_score_title.setVisibility(View.INVISIBLE);
		relativelayout2.addView(red_score_title);
		
		blue_score_text.setText(Integer.toString(blue_score));
    	blue_score_text.setTextColor(Color.BLUE);
		blue_score_text.setLayoutParams(word_dimensions);
	    blue_score_text.setTextSize(height/45);
		blue_score_text.setGravity(Gravity.CENTER);
		blue_score_text.setY((4*height)/10);
		blue_score_text.setVisibility(View.INVISIBLE);
		relativelayout1.addView(blue_score_text);
		
		blue_score_title.setText("Blue Score");
		blue_score_title.setTextColor(Color.BLUE);
		blue_score_title.setLayoutParams(word_dimensions);
		blue_score_title.setTextSize(height/65);
		blue_score_title.setGravity(Gravity.CENTER);
		blue_score_title.setY(((3*height)/10) + (height/50));
		blue_score_title.setVisibility(View.INVISIBLE);
		relativelayout2.addView(blue_score_title);

		tapready.setText("tap the screen to start");
		RelativeLayout.LayoutParams ready_dimensions = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, (7*height/40));		
		ready_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
		tapready.setLayoutParams(ready_dimensions);
		tapready.setTextSize(height/85);
		tapready.setTextColor(Color.GRAY);
		tapready.setGravity(Gravity.CENTER);
		tapready.setY((7*height)/10);
		tapready.setVisibility(View.INVISIBLE);
		relativelayout2.addView(tapready);
		
		nexteam.setText(current_team + " " + "is next");
		nexteam.setLayoutParams(word_dimensions);
		nexteam.setTextSize(height/85);
		nexteam.setGravity(Gravity.CENTER);
		nexteam.setY((11*height)/20);
		nexteam.setVisibility(View.INVISIBLE);
		relativelayout2.addView(nexteam);
		
		RelativeLayout.LayoutParams bar_dimensions = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, (7*height/40));		
		bar_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
		blackbar.setLayoutParams(bar_dimensions);
		blackbar.setY((13*height)/40);
		blackbar.setVisibility(View.INVISIBLE);
		relativelayout2.addView(blackbar);
		
		roundfadein(roundtext, red_score_text, blue_score_text, red_score_title, blue_score_title, tapready, nexteam, blackbar, readybutton);
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
	
	public void animationfadein(View i) {
		AlphaAnimation fadein = new AlphaAnimation(0.0f , 1.0f ) ; 
		fadein.setDuration(500);
		i.startAnimation(fadein);
		i.setVisibility(View.VISIBLE);
	}
	

	public void readybutton (final TextView roundtext, final TextView red_score_text, final TextView blue_score_text, final TextView red_score_title, final TextView blue_score_title, final TextView tapready, final TextView nexteam, final ImageView blackbar, final ImageView readybutton) {
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
				blue_score_text.startAnimation(animSet4);
				red_score_text.startAnimation(animSet5);
				draw_card();
				timer(roundtext, red_score_text, blue_score_text, red_score_title, blue_score_title, tapready, nexteam, blackbar, readybutton);
			}
		});
	}
	
	public void draw_card() {
		ImageView card_image = new ImageView(this);
		card_image.setImageResource(R.drawable.card_image2);
		
		AnimationSet carddrawset = new AnimationSet(true);
		
			AlphaAnimation cardfadein = new AlphaAnimation(0.0f , 1.0f ) ; 
			cardfadein.setDuration(1000);
			carddrawset.addAnimation(cardfadein);
		
			TranslateAnimation carddraw = new TranslateAnimation(0, 0, (1*height)/10, 0);
			carddraw.setDuration(1000);
			carddraw.setFillAfter(true);
			carddrawset.addAnimation(carddraw);
		
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
	
	public void timer(final TextView roundtext, final TextView red_score_text, final TextView blue_score_text, final TextView red_score_title, final TextView blue_score_title, final TextView tapready, final TextView nexteam, final ImageView blackbar, final ImageView readybutton){
		final TextView timer = new TextView(this);
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/GeosansLight.ttf");
		timer.setTypeface(type);
		timer.setText(Integer.toString(time));
		RelativeLayout.LayoutParams timer_dimensions = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
		timer_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
		timer.setLayoutParams(timer_dimensions);
		timer.setTextSize(height/55);
		timer.setGravity(Gravity.CENTER);
		timer.setY(height/80);
		
		relativelayout1.addView(timer);
		
		AlphaAnimation timerfadeinitial = new AlphaAnimation(0.0f , 1.0f ) ; 
		timerfadeinitial.setDuration(1000);
		
		AlphaAnimation timerfadein = new AlphaAnimation(5.0f , 1.0f ) ; 
		timerfadein.setDuration(1000);
		if (time <= 5 && time != 0) {
			//timer.setTextColor(Color.RED);
			AnimationSet timerend = new AnimationSet(true);
			AlphaAnimation timerfadeend = new AlphaAnimation(0.0f , 1.0f ) ; 
			timerfadeend.setDuration(1000);
			timerend.addAnimation(timerfadeend);
			ScaleAnimation timerscaleend = new ScaleAnimation(2, 1, 2, 1, Animation.RELATIVE_TO_SELF, (float)0.5, Animation.RELATIVE_TO_SELF, (float)0.5);
			timerscaleend.setDuration(1000);
			timerend.addAnimation(timerscaleend);
			timer.startAnimation(timerend);
			
			timerend.setAnimationListener(new AnimationListener() {
			    @Override
			    public void onAnimationEnd(Animation animation) {
			    	time -= 1;
			        timer.setVisibility(View.GONE) ;
			    	timer2(roundtext, red_score_text, blue_score_text, red_score_title, blue_score_title, tapready, nexteam, blackbar, readybutton);
			    }
				@Override
				public void onAnimationRepeat(Animation arg0) {
				}
				@Override
				public void onAnimationStart(Animation arg0) {
				}
			});
		} else if (time == 0) {
			timer.setVisibility(View.GONE);
			num_round += 1;
			cardlayout1.setVisibility(View.GONE);
			cardlayout2.setVisibility(View.GONE);
			//animationfadein(relativelayout2);
			readypage(roundtext, red_score_text, blue_score_text, red_score_title, blue_score_title, tapready, nexteam, blackbar, readybutton);
		} else if (time == time_initial) {
			timer.startAnimation(timerfadeinitial);
			timerfadeinitial.setAnimationListener(new AnimationListener() {
			    @Override
			    public void onAnimationEnd(Animation animation) {
			    	time -= 1;
			        timer.setVisibility(View.GONE) ;
			    	timer2(roundtext, red_score_text, blue_score_text, red_score_title, blue_score_title, tapready, nexteam, blackbar, readybutton);
			    }
				@Override
				public void onAnimationRepeat(Animation arg0) {
				}
				@Override
				public void onAnimationStart(Animation arg0) {
				}
			});
		} else {
			timer.startAnimation(timerfadein);
			timerfadein.setAnimationListener(new AnimationListener() {
			    @Override
			    public void onAnimationEnd(Animation animation) {
			    	time -= 1;
			        timer.setVisibility(View.GONE) ;
			    	timer2(roundtext, red_score_text, blue_score_text, red_score_title, blue_score_title, tapready, nexteam, blackbar, readybutton);
			    	
			    }
				@Override
				public void onAnimationRepeat(Animation arg0) {
				}
				@Override
				public void onAnimationStart(Animation arg0) {
				}
			});
		}
	}
	
	public void timer2(final TextView roundtext, final TextView red_score_text, final TextView blue_score_text, final TextView red_score_title, final TextView blue_score_title, final TextView tapready, final TextView nexteam, final ImageView blackbar, final ImageView readybutton){
		final TextView timer2 = new TextView(this);
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/GeosansLight.ttf");
		timer2.setTypeface(type);
		timer2.setText(Integer.toString(time));
		RelativeLayout.LayoutParams timer_dimensions = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
		timer_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
		timer2.setLayoutParams(timer_dimensions);
		timer2.setTextSize(height/55);
		timer2.setGravity(Gravity.CENTER);
		timer2.setY(height/80);
		
		relativelayout1.addView(timer2);
		
		AlphaAnimation timerfadein = new AlphaAnimation(5.0f , 1.0f ) ; 
		timerfadein.setDuration(1000);
		
		if (time <= 5 && time != 0) {
			//timer2.setTextColor(Color.RED);
			AnimationSet timerend = new AnimationSet(true);
			AlphaAnimation timerfadeend = new AlphaAnimation(0.0f , 1.0f ) ; 
			timerfadeend.setDuration(1000);
			timerend.addAnimation(timerfadeend);
			ScaleAnimation timerscaleend = new ScaleAnimation(2, 1, 2, 1, Animation.RELATIVE_TO_SELF, (float)0.5, Animation.RELATIVE_TO_SELF, (float)0.5);
			timerscaleend.setDuration(1000);
			timerend.addAnimation(timerscaleend);
			timer2.startAnimation(timerend);
			
			timerend.setAnimationListener(new AnimationListener() {
			    @Override
			    public void onAnimationEnd(Animation animation) {
			    	time -= 1;
			    	timer2.setVisibility(View.GONE) ;
			    	timer(roundtext, red_score_text, blue_score_text, red_score_title, blue_score_title, tapready, nexteam, blackbar, readybutton);
			    	
			    }
				@Override
				public void onAnimationRepeat(Animation arg0) {
				}
				@Override
				public void onAnimationStart(Animation arg0) {
				}
			});
		} else if (time == 0) {
			timer2.setVisibility(View.GONE);
			num_round += 1;
			cardlayout1.setVisibility(View.GONE);
			cardlayout2.setVisibility(View.GONE);
			readypage(roundtext, red_score_text, blue_score_text, red_score_title, blue_score_title, tapready, nexteam, blackbar, readybutton);

			//animationfadein(relativelayout2);
			//readypage();
		} else {
			timer2.startAnimation(timerfadein);
			timerfadein.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					time -= 1;
					timer2.setVisibility(View.GONE) ;
					timer(roundtext, red_score_text, blue_score_text, red_score_title, blue_score_title, tapready, nexteam, blackbar, readybutton);
				}
				@Override
				public void onAnimationRepeat(Animation arg0) {
				}
				@Override
				public void onAnimationStart(Animation arg0) {
				}
			});
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
