package com.jeremyfeinstein.slidingmenu.lib.app;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public abstract interface SlidingActivityBase
{
  public abstract SlidingMenu getSlidingMenu();
  
  public abstract void setBehindContentView(int paramInt);
  
  public abstract void setBehindContentView(View paramView);
  
  public abstract void setBehindContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams);
  
  public abstract void setSlidingActionBarEnabled(boolean paramBoolean);
  
  public abstract void showContent();
  
  public abstract void showMenu();
  
  public abstract void showSecondaryMenu();
  
  public abstract void toggle();
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\jeremyfeinstein\slidingmenu\lib\app\SlidingActivityBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */