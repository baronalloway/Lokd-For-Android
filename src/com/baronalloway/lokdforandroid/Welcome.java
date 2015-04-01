package com.baronalloway.lokdforandroid;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class Welcome extends Activity {
	ImageButton startButton;
	ImageView logoView;
	TextView welcomeTitle;
	TextView welcomeText;
	TextSwitcher instructionText;
	int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
              setContentView(R.layout.activity_welcome);
        // Show the Up button in the action bar.
        setupActionBar();
        
       welcomeTitle = (TextView)findViewById(R.id.welcome_title);
      
       //welcome Text
       
       logoView = (ImageView)findViewById(R.id.logoView);
       startButton = (ImageButton)findViewById(R.id.welcomeButton);
       instructionText = (TextSwitcher)findViewById(R.id.instructionText);
       welcomeText = (TextView)findViewById(R.id.welcome_text);
       
     Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
       Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
       instructionText.setInAnimation(in);
       instructionText.setOutAnimation(out);
 
       instructionText.setFactory(new ViewFactory(){

		@Override
		public View makeView() {
			TextView theText = new TextView(Welcome.this);
			theText.setTextSize(25);
			theText.setTextColor(Color.BLACK);
			return theText;
		}});
       
       
        
        startButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0f);
				alphaAnim.setDuration (600);
				alphaAnim.setInterpolator(new AccelerateInterpolator());
				alphaAnim.setAnimationListener(new AnimationListener(){

					@Override
					public void onAnimationEnd(Animation arg0) {
						logoView.setAlpha(0f);
						welcomeTitle.setAlpha(0f);
						welcomeText.setAlpha(0f);
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						//Do Nothing
						
					}

					@Override
					public void onAnimationStart(Animation arg0) {
						//Do Nothing
						
					}});
				
				if(i == 0)
				{
				logoView.startAnimation(alphaAnim);
				welcomeTitle.startAnimation(alphaAnim);
				welcomeText.startAnimation(alphaAnim);
				
				
				instructionText.setText("Lokd allows you to take pictures of everything in your wallet and store them, encrypted.");
				}
				else if(i == 1)
				{
					instructionText.setText("On the next screen, youll be presented with a password box. Enter your password.");
				}
				else if(i ==2)
				{
					instructionText.setText("It can be anything, just make sure to keep it in a safe place!");
				}
				
				else
				{
					finish();
				}
				
				i++;
			}});
        
        
        
        
        
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

   

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
