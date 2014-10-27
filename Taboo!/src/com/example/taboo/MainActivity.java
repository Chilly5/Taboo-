package com.example.taboo;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class MainActivity extends Activity {
	
	int width = 0;
	int height = 0;
	final double xrange = 1.0;
	int dmin = 5000, drange = 10000, tmin = 0, trange = 5000;
	double amin = 0.1, arange = 0.5, ymin = 0;
	String[] rainwords = {"Vijanayagara","Gopati","Delhi","Tughluq","Bahmani","Ghandara","Orissa","Calicut"};
	RelativeLayout relativelayout;
	RelativeLayout relativelayout2;
	RelativeLayout relativelayout3;


	RelativeLayout.LayoutParams title_dimensions;
	int i = 0;
	int j = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getTheDisplay();
        relativelayout = new RelativeLayout(this);
        relativelayout2 = new RelativeLayout(this);
        relativelayout2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        relativelayout3 = new RelativeLayout(this);
        relativelayout3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        


        addMenu();
		addRain();
		setContentView(relativelayout);
	}
	
	@Override
	public void onBackPressed() {
	    //pressing the back button will take you back to main menu
		if (i == 1) {
			i -= 1;
			animationfadeslow(relativelayout3);
			animationfadeout(relativelayout2);
		}
		else{
			finish();
		}
	    return;
	}

	public void rainstring(TextView x){
		x.setText(rainwords[(int) (Math.random()*7)]);
	}
	
	public void addRain(){
		RotateAnimation rotate= (RotateAnimation)AnimationUtils.loadAnimation(this,R.anim.rotate);

		TextView raintxt1 = new TextView(this);
		raintxt1.setAnimation(rotate);
		raintxt1.startAnimation(rotate);
		animationrain(raintxt1);
		rainstring(raintxt1);
		relativelayout.addView(raintxt1);
		
		TextView raintxt2 = new TextView(this);
		raintxt2.setAnimation(rotate);
		animationrain(raintxt2);
		rainstring(raintxt2);
		relativelayout.addView(raintxt2);
		
		TextView raintxt3 = new TextView(this);
		raintxt3.setAnimation(rotate);
		animationrain(raintxt3);
		rainstring(raintxt3);
		relativelayout.addView(raintxt3);

		TextView raintxt4 = new TextView(this);
		raintxt4.setAnimation(rotate);
		animationrain(raintxt4);
		rainstring(raintxt4);
		relativelayout.addView(raintxt4);
		
		TextView raintxt5 = new TextView(this);
		TextView raintxt6 = new TextView(this);
		TextView raintxt7 = new TextView(this);
		TextView raintxt8 = new TextView(this);
	}
	
	public void animationrain(View i) {
	    float xcoord = (float) (xrange*width*Math.random());
	    
	    ScaleAnimation scaler = new ScaleAnimation(1, 10, 1, 10, Animation.RELATIVE_TO_SELF, (float)0.5, Animation.RELATIVE_TO_SELF, (float)0.5);
		scaler.setDuration(0);

	    TranslateAnimation raindown = new TranslateAnimation(xcoord, xcoord, (float) -height, (float) height);
		raindown.setDuration((long) (dmin + (Math.random() * (drange))));
		i.startAnimation(raindown);
	}
	
	public void animationfade(View i) {
		AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ; 
		fadeIn.setDuration(1000);
		fadeIn.setFillAfter(true);
		i.startAnimation(fadeIn);
	}
	
	public void animationfadeslow(View j) {
		AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ; 
		fadeIn.setDuration(500);
		fadeIn.setFillAfter(true);
		fadeIn.setStartOffset(250);
		j.startAnimation(fadeIn);
	}
	
	public void animationfadeout(View i) {
		AlphaAnimation fadeOut = new AlphaAnimation(1.0f , 0.0f ) ; 
		fadeOut.setDuration(500);
		i.startAnimation(fadeOut);
		i.setVisibility(View.GONE);
	}
	
	
	public void addMenu() {
		
		//title
		final ImageView title = new ImageView(this);
		title.setImageResource(R.drawable.blacklogo2);
		animationfade(title);
		RelativeLayout.LayoutParams title_dimensions = new RelativeLayout.LayoutParams(2*width/3, height/6);		
		title_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
		title.setLayoutParams(title_dimensions);
		title.setY(height/10);
		relativelayout3.addView(title);
		
		//instantiate menu buttons
		final TextView txt1 = new TextView(this);
		final TextView txt2 = new TextView(this);
		final TextView txt3 = new TextView(this);
		final TextView txt4 = new TextView(this);
		txt1.setText("Play");
		txt2.setText("Instructions");
		txt3.setText("Settings");
		txt4.setText("Buzzer");
		
		final TextView setup = new TextView(this);
		final ImageView justplaybutton = new ImageView(this);


		//set fonts of menu buttons
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/GeosansLight.ttf");
		txt1.setTypeface(type);
		txt2.setTypeface(type);
		txt3.setTypeface(type);
		txt4.setTypeface(type);
		setup.setTypeface(type);

		
		//start fade-in animation of menu buttons
		animationfadeslow(txt1);
		animationfadeslow(txt2);
		animationfadeslow(txt3);
		animationfadeslow(txt4);

		//dimensions of menu buttons
		RelativeLayout.LayoutParams menu_dimensions1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
	    menu_dimensions1.addRule(RelativeLayout.CENTER_HORIZONTAL);
	    txt1.setLayoutParams(menu_dimensions1);
	    txt1.setTextSize(height/35);
		txt1.setGravity(Gravity.CENTER);
		txt1.setY(5*height/11);
		
		RelativeLayout.LayoutParams menu_dimensions2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
	    menu_dimensions2.addRule(RelativeLayout.CENTER_HORIZONTAL);
	    txt2.setLayoutParams(menu_dimensions2);
	    txt2.setTextSize(height/35);
		txt2.setGravity(Gravity.CENTER);
		txt2.setY(5*height/11+height/10);
		
		RelativeLayout.LayoutParams menu_dimensions3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
	    menu_dimensions3.addRule(RelativeLayout.CENTER_HORIZONTAL);
		txt3.setLayoutParams(menu_dimensions3);
	    txt3.setTextSize(height/35);
		txt3.setGravity(Gravity.CENTER);
		txt3.setY(5*height/11+height/10+height/10);
		
		RelativeLayout.LayoutParams menu_dimensions4 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
	    menu_dimensions4.addRule(RelativeLayout.CENTER_HORIZONTAL);
		txt4.setLayoutParams(menu_dimensions4);
	    txt4.setTextSize(height/35);
		txt4.setGravity(Gravity.CENTER);
		txt4.setY(5*height/11+height/10+height/10+height/10);
				
		//highlights button that is pressed
		txt1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                	txt1.setTextColor(Color.YELLOW);
                } else {
                	txt1.setTextColor(Color.BLACK);
                }
                return false;
            }
        });
		
		//what happens when play button is pressed
		txt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				i += 1; //for back button
				if (j > 0){
					animationfadeout(relativelayout3);
					animationfadeslow(relativelayout2);
					}
				else{
					j += 1;

					animationfadeout(relativelayout3);
					animationfadeslow(relativelayout2);
					
					//setup title
					setup.setText("Setup");
					RelativeLayout.LayoutParams setup_dimensions = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
				    setup_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
					setup.setLayoutParams(setup_dimensions);
				    setup.setTextSize(height/30);
					setup.setGravity(Gravity.CENTER);
					setup.setY(height/16);
					relativelayout2.addView(setup);

					justplaybutton.setImageResource(R.drawable.buttonicon_justplay);
					RelativeLayout.LayoutParams button_dimensions = new RelativeLayout.LayoutParams(2*width/3, 9*height/40);		
					button_dimensions.addRule(RelativeLayout.CENTER_HORIZONTAL);
					justplaybutton.setLayoutParams(button_dimensions);
					justplaybutton.setY(height/16 + height/6);
					relativelayout2.addView(justplaybutton);
					
					justplaybutton.setOnClickListener(new OnClickListener() {
						public void onClick(View v){
							start_button();
						}
					});
					
					//display setup menu
					relativelayout.addView(relativelayout2);
				}
			}
		});
		txt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				//play_button(v);
			}
		});
		txt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				//play_button(v);
			}
		});
		txt4.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				//play_button(v);
			}
		});
		relativelayout3.addView(txt1);
		relativelayout3.addView(txt2);
		relativelayout3.addView(txt3);
		relativelayout3.addView(txt4);
		relativelayout.addView(relativelayout3);
	}
	
	public void start_button() {
	    // Do something in response to button
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
		overridePendingTransition(0,0);
	}
	
	private void getTheDisplay() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
    }
	
	


	

}
