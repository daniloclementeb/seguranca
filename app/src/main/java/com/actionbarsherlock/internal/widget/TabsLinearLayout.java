package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout.LayoutParams;

public class TabsLinearLayout
  extends IcsLinearLayout
{
  private static final int LinearLayout_measureWithLargestChild = 0;
  private static final int[] R_styleable_LinearLayout = { 16843476 };
  private boolean mUseLargestChild;
  
  public TabsLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R_styleable_LinearLayout);
    this.mUseLargestChild = paramContext.getBoolean(0, false);
    paramContext.recycle();
  }
  
  private void useLargestChildHorizontal()
  {
    int n = getChildCount();
    int i = 0;
    int j = 0;
    int k;
    if (j >= n)
    {
      k = 0;
      j = 0;
    }
    View localView;
    int m;
    for (;;)
    {
      if (j >= n)
      {
        setMeasuredDimension(k + (getPaddingLeft() + getPaddingRight()), getMeasuredHeight());
        return;
        i = Math.max(getChildAt(j).getMeasuredWidth(), i);
        j += 1;
        break;
      }
      localView = getChildAt(j);
      m = k;
      if (localView != null)
      {
        if (localView.getVisibility() != 8) {
          break label104;
        }
        m = k;
      }
      j += 1;
      k = m;
    }
    label104:
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)localView.getLayoutParams();
    if (localLayoutParams.weight > 0.0F)
    {
      localView.measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(localView.getMeasuredHeight(), 1073741824));
      k += i;
    }
    for (;;)
    {
      m = k + (localLayoutParams.leftMargin + localLayoutParams.rightMargin);
      break;
      k += localView.getMeasuredWidth();
    }
  }
  
  public boolean isMeasureWithLargestChildEnabled()
  {
    return this.mUseLargestChild;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (getChildCount() <= 2) {}
    do
    {
      return;
      paramInt1 = View.MeasureSpec.getMode(paramInt1);
    } while ((!this.mUseLargestChild) || (paramInt1 != 0) || (getOrientation() != 0));
    useLargestChildHorizontal();
  }
  
  public void setMeasureWithLargestChildEnabled(boolean paramBoolean)
  {
    this.mUseLargestChild = paramBoolean;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\widget\TabsLinearLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */