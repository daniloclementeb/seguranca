package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import com.actionbarsherlock.R.id;
import com.actionbarsherlock.R.styleable;
import com.actionbarsherlock.internal.nineoldandroids.widget.NineFrameLayout;

public class ActionBarContainer
  extends NineFrameLayout
{
  private ActionBarView mActionBarView;
  private Drawable mBackground;
  private boolean mIsSplit;
  private boolean mIsStacked;
  private boolean mIsTransitioning;
  private Drawable mSplitBackground;
  private Drawable mStackedBackground;
  private View mTabContainer;
  
  public ActionBarContainer(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionBarContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setBackgroundDrawable(null);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SherlockActionBar);
    this.mBackground = paramContext.getDrawable(R.styleable.SherlockActionBar_background);
    this.mStackedBackground = paramContext.getDrawable(R.styleable.SherlockActionBar_backgroundStacked);
    if (((this.mStackedBackground instanceof ColorDrawable)) && (Build.VERSION.SDK_INT < 11)) {
      this.mStackedBackground = new IcsColorDrawable((ColorDrawable)this.mStackedBackground);
    }
    if (getId() == R.id.abs__split_action_bar)
    {
      this.mIsSplit = true;
      this.mSplitBackground = paramContext.getDrawable(R.styleable.SherlockActionBar_backgroundSplit);
    }
    paramContext.recycle();
    boolean bool;
    if (this.mIsSplit) {
      if (this.mSplitBackground == null) {
        bool = true;
      }
    }
    for (;;)
    {
      setWillNotDraw(bool);
      return;
      bool = false;
      continue;
      if ((this.mBackground == null) && (this.mStackedBackground == null)) {
        bool = true;
      } else {
        bool = false;
      }
    }
  }
  
  public View getTabContainer()
  {
    return this.mTabContainer;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if ((getWidth() == 0) || (getHeight() == 0)) {}
    do
    {
      do
      {
        return;
        if (!this.mIsSplit) {
          break;
        }
      } while (this.mSplitBackground == null);
      this.mSplitBackground.draw(paramCanvas);
      return;
      if (this.mBackground != null) {
        this.mBackground.draw(paramCanvas);
      }
    } while ((this.mStackedBackground == null) || (!this.mIsStacked));
    this.mStackedBackground.draw(paramCanvas);
  }
  
  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mActionBarView = ((ActionBarView)findViewById(R.id.abs__action_bar));
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    super.onHoverEvent(paramMotionEvent);
    return true;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return (this.mIsTransitioning) || (super.onInterceptTouchEvent(paramMotionEvent));
  }
  
  public void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    int i;
    if ((this.mTabContainer != null) && (this.mTabContainer.getVisibility() != 8))
    {
      paramInt2 = 1;
      if ((this.mTabContainer != null) && (this.mTabContainer.getVisibility() != 8))
      {
        paramInt4 = getMeasuredHeight();
        i = this.mTabContainer.getMeasuredHeight();
        if ((this.mActionBarView.getDisplayOptions() & 0x2) != 0) {
          break label205;
        }
        int j = getChildCount();
        paramInt4 = 0;
        if (paramInt4 < j) {
          break label159;
        }
        this.mTabContainer.layout(paramInt1, 0, paramInt3, i);
      }
    }
    for (;;)
    {
      paramInt3 = 0;
      paramInt1 = 0;
      if (!this.mIsSplit) {
        break label225;
      }
      if (this.mSplitBackground != null)
      {
        this.mSplitBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        paramInt1 = 1;
      }
      if (paramInt1 != 0) {
        invalidate();
      }
      return;
      paramInt2 = 0;
      break;
      label159:
      View localView = getChildAt(paramInt4);
      if (localView == this.mTabContainer) {}
      for (;;)
      {
        paramInt4 += 1;
        break;
        if (!this.mActionBarView.isCollapsed()) {
          localView.offsetTopAndBottom(i);
        }
      }
      label205:
      this.mTabContainer.layout(paramInt1, paramInt4 - i, paramInt3, paramInt4);
    }
    label225:
    paramInt1 = paramInt3;
    if (this.mBackground != null)
    {
      this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
      paramInt1 = 1;
    }
    if ((paramInt2 != 0) && (this.mStackedBackground != null)) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      this.mIsStacked = paramBoolean;
      if (!paramBoolean) {
        break;
      }
      this.mStackedBackground.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
      paramInt1 = 1;
      break;
    }
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mActionBarView == null) {}
    for (;;)
    {
      return;
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)this.mActionBarView.getLayoutParams();
      if (this.mActionBarView.isCollapsed()) {}
      for (paramInt1 = 0; (this.mTabContainer != null) && (this.mTabContainer.getVisibility() != 8) && (View.MeasureSpec.getMode(paramInt2) == Integer.MIN_VALUE); paramInt1 = this.mActionBarView.getMeasuredHeight() + localLayoutParams.topMargin + localLayoutParams.bottomMargin)
      {
        paramInt2 = View.MeasureSpec.getSize(paramInt2);
        setMeasuredDimension(getMeasuredWidth(), Math.min(this.mTabContainer.getMeasuredHeight() + paramInt1, paramInt2));
        return;
      }
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    super.onTouchEvent(paramMotionEvent);
    return true;
  }
  
  public void setPrimaryBackground(Drawable paramDrawable)
  {
    this.mBackground = paramDrawable;
    invalidate();
  }
  
  public void setSplitBackground(Drawable paramDrawable)
  {
    this.mSplitBackground = paramDrawable;
    invalidate();
  }
  
  public void setStackedBackground(Drawable paramDrawable)
  {
    this.mStackedBackground = paramDrawable;
    invalidate();
  }
  
  public void setTabContainer(ScrollingTabContainerView paramScrollingTabContainerView)
  {
    if (this.mTabContainer != null) {
      removeView(this.mTabContainer);
    }
    this.mTabContainer = paramScrollingTabContainerView;
    if (paramScrollingTabContainerView != null)
    {
      addView(paramScrollingTabContainerView);
      ViewGroup.LayoutParams localLayoutParams = paramScrollingTabContainerView.getLayoutParams();
      localLayoutParams.width = -1;
      localLayoutParams.height = -2;
      paramScrollingTabContainerView.setAllowCollapse(false);
    }
  }
  
  public void setTransitioning(boolean paramBoolean)
  {
    this.mIsTransitioning = paramBoolean;
    if (paramBoolean) {}
    for (int i = 393216;; i = 262144)
    {
      setDescendantFocusability(i);
      return;
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\widget\ActionBarContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */