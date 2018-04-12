package com.actionbarsherlock.internal.widget;

import android.view.View;

final class IcsView
{
  public static int getMeasuredStateInt(View paramView)
  {
    return paramView.getMeasuredWidth() & 0xFF000000 | paramView.getMeasuredHeight() >> 16 & 0xFF00;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\widget\IcsView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */