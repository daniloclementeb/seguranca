package com.jeremyfeinstein.slidingmenu.lib.app;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class SlidingFragmentActivity
  extends SherlockFragmentActivity
  implements SlidingActivityBase
{
  private SlidingActivityHelper mHelper;
  
  public View findViewById(int paramInt)
  {
    View localView = super.findViewById(paramInt);
    if (localView != null) {
      return localView;
    }
    return this.mHelper.findViewById(paramInt);
  }
  
  public SlidingMenu getSlidingMenu()
  {
    return this.mHelper.getSlidingMenu();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mHelper = new SlidingActivityHelper(this);
    this.mHelper.onCreate(paramBundle);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = this.mHelper.onKeyUp(paramInt, paramKeyEvent);
    if (bool) {
      return bool;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  public void onPostCreate(Bundle paramBundle)
  {
    super.onPostCreate(paramBundle);
    this.mHelper.onPostCreate(paramBundle);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.mHelper.onSaveInstanceState(paramBundle);
  }
  
  public void setBehindContentView(int paramInt)
  {
    setBehindContentView(getLayoutInflater().inflate(paramInt, null));
  }
  
  public void setBehindContentView(View paramView)
  {
    setBehindContentView(paramView, new ViewGroup.LayoutParams(-1, -1));
  }
  
  public void setBehindContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    this.mHelper.setBehindContentView(paramView, paramLayoutParams);
  }
  
  public void setContentView(int paramInt)
  {
    setContentView(getLayoutInflater().inflate(paramInt, null));
  }
  
  public void setContentView(View paramView)
  {
    setContentView(paramView, new ViewGroup.LayoutParams(-1, -1));
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setContentView(paramView, paramLayoutParams);
    this.mHelper.registerAboveContentView(paramView, paramLayoutParams);
  }
  
  public void setSlidingActionBarEnabled(boolean paramBoolean)
  {
    this.mHelper.setSlidingActionBarEnabled(paramBoolean);
  }
  
  public void showContent()
  {
    this.mHelper.showContent();
  }
  
  public void showMenu()
  {
    this.mHelper.showMenu();
  }
  
  public void showSecondaryMenu()
  {
    this.mHelper.showSecondaryMenu();
  }
  
  public void toggle()
  {
    this.mHelper.toggle();
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\jeremyfeinstein\slidingmenu\lib\app\SlidingFragmentActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */