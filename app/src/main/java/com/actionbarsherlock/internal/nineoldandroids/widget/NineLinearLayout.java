package com.actionbarsherlock.internal.nineoldandroids.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.actionbarsherlock.internal.nineoldandroids.view.animation.AnimatorProxy;

public class NineLinearLayout
  extends LinearLayout
{
  private final AnimatorProxy mProxy;
  
  public NineLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (AnimatorProxy.NEEDS_PROXY) {}
    for (paramContext = AnimatorProxy.wrap(this);; paramContext = null)
    {
      this.mProxy = paramContext;
      return;
    }
  }
  
  public float getAlpha()
  {
    if (AnimatorProxy.NEEDS_PROXY) {
      return this.mProxy.getAlpha();
    }
    return super.getAlpha();
  }
  
  public float getTranslationX()
  {
    if (AnimatorProxy.NEEDS_PROXY) {
      return this.mProxy.getTranslationX();
    }
    return super.getTranslationX();
  }
  
  public void setAlpha(float paramFloat)
  {
    if (AnimatorProxy.NEEDS_PROXY)
    {
      this.mProxy.setAlpha(paramFloat);
      return;
    }
    super.setAlpha(paramFloat);
  }
  
  public void setTranslationX(float paramFloat)
  {
    if (AnimatorProxy.NEEDS_PROXY)
    {
      this.mProxy.setTranslationX(paramFloat);
      return;
    }
    super.setTranslationX(paramFloat);
  }
  
  public void setVisibility(int paramInt)
  {
    if (this.mProxy != null)
    {
      if (paramInt != 8) {
        break label23;
      }
      clearAnimation();
    }
    for (;;)
    {
      super.setVisibility(paramInt);
      return;
      label23:
      if (paramInt == 0) {
        setAnimation(this.mProxy);
      }
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\nineoldandroids\widget\NineLinearLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */