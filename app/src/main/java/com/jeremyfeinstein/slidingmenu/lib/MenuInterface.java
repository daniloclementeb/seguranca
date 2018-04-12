package com.jeremyfeinstein.slidingmenu.lib;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public abstract interface MenuInterface
{
  public abstract void drawFade(Canvas paramCanvas, int paramInt, CustomViewBehind paramCustomViewBehind, View paramView);
  
  public abstract void drawSelector(View paramView, Canvas paramCanvas, float paramFloat);
  
  public abstract void drawShadow(Canvas paramCanvas, Drawable paramDrawable, int paramInt);
  
  public abstract int getAbsLeftBound(CustomViewBehind paramCustomViewBehind, View paramView);
  
  public abstract int getAbsRightBound(CustomViewBehind paramCustomViewBehind, View paramView);
  
  public abstract int getMenuLeft(CustomViewBehind paramCustomViewBehind, View paramView);
  
  public abstract boolean marginTouchAllowed(View paramView, int paramInt1, int paramInt2);
  
  public abstract boolean menuClosedSlideAllowed(int paramInt);
  
  public abstract boolean menuOpenSlideAllowed(int paramInt);
  
  public abstract boolean menuOpenTouchAllowed(View paramView, int paramInt1, int paramInt2);
  
  public abstract boolean menuTouchInQuickReturn(View paramView, int paramInt1, int paramInt2);
  
  public abstract void scrollBehindTo(int paramInt1, int paramInt2, CustomViewBehind paramCustomViewBehind, float paramFloat);
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\jeremyfeinstein\slidingmenu\lib\MenuInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */