package com.actionbarsherlock.internal.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout.LayoutParams;
import com.actionbarsherlock.internal.widget.IcsLinearLayout;

public class ActionMenuView
  extends IcsLinearLayout
  implements MenuBuilder.ItemInvoker, MenuView
{
  static final int GENERATED_ITEM_PADDING = 4;
  private static final boolean IS_FROYO;
  static final int MIN_CELL_SIZE = 56;
  private boolean mFirst = true;
  private boolean mFormatItems;
  private int mFormatItemsWidth;
  private int mGeneratedItemPadding;
  private MenuBuilder mMenu;
  private int mMinCellSize;
  private ActionMenuPresenter mPresenter;
  private boolean mReserveOverflow;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 8) {}
    for (boolean bool = true;; bool = false)
    {
      IS_FROYO = bool;
      return;
    }
  }
  
  public ActionMenuView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionMenuView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setBaselineAligned(false);
    float f = paramContext.getResources().getDisplayMetrics().density;
    this.mMinCellSize = ((int)(56.0F * f));
    this.mGeneratedItemPadding = ((int)(4.0F * f));
  }
  
  static int measureChildForCells(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    paramInt4 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt3) - paramInt4, View.MeasureSpec.getMode(paramInt3));
    paramInt3 = 0;
    if (paramInt2 > 0)
    {
      paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1 * paramInt2, Integer.MIN_VALUE), paramInt4);
      int i = paramView.getMeasuredWidth();
      paramInt2 = i / paramInt1;
      paramInt3 = paramInt2;
      if (i % paramInt1 != 0) {
        paramInt3 = paramInt2 + 1;
      }
    }
    ActionMenuItemView localActionMenuItemView;
    if ((paramView instanceof ActionMenuItemView))
    {
      localActionMenuItemView = (ActionMenuItemView)paramView;
      if ((localLayoutParams.isOverflowButton) || (localActionMenuItemView == null) || (!localActionMenuItemView.hasText())) {
        break label141;
      }
    }
    label141:
    for (boolean bool = true;; bool = false)
    {
      localLayoutParams.expandable = bool;
      localLayoutParams.cellsUsed = paramInt3;
      paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt3 * paramInt1, 1073741824), paramInt4);
      return paramInt3;
      localActionMenuItemView = null;
      break;
    }
  }
  
  private void onMeasureExactFormat(int paramInt1, int paramInt2)
  {
    int i9 = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    int i7 = View.MeasureSpec.getSize(paramInt2);
    int i = getPaddingLeft();
    int j = getPaddingRight();
    int i11 = getPaddingTop() + getPaddingBottom();
    int i10 = paramInt1 - (i + j);
    paramInt1 = i10 / this.mMinCellSize;
    i = this.mMinCellSize;
    if (paramInt1 == 0)
    {
      setMeasuredDimension(i10, 0);
      return;
    }
    int i12 = this.mMinCellSize + i10 % i / paramInt1;
    i = 0;
    int m = 0;
    int k = 0;
    int n = 0;
    j = 0;
    long l1 = 0L;
    int i13 = getChildCount();
    int i1 = 0;
    label137:
    int i2;
    label142:
    long l2;
    label160:
    label173:
    float f2;
    if (i1 >= i13)
    {
      if ((j == 0) || (n != 2)) {
        break label709;
      }
      i1 = 1;
      paramInt2 = 0;
      i2 = paramInt1;
      l2 = l1;
      if (k > 0)
      {
        if (i2 > 0) {
          break label715;
        }
        l2 = l1;
      }
      if ((j != 0) || (n != 1)) {
        break label1032;
      }
      paramInt1 = 1;
      j = paramInt2;
      if (i2 > 0)
      {
        j = paramInt2;
        if (l2 != 0L) {
          if ((i2 >= n - 1) && (paramInt1 == 0))
          {
            j = paramInt2;
            if (m <= 1) {}
          }
          else
          {
            float f3 = Long.bitCount(l2);
            f2 = f3;
            if (paramInt1 == 0)
            {
              float f1 = f3;
              if ((1L & l2) != 0L)
              {
                f1 = f3;
                if (!((LayoutParams)getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                  f1 = f3 - 0.5F;
                }
              }
              f2 = f1;
              if ((1 << i13 - 1 & l2) != 0L)
              {
                f2 = f1;
                if (!((LayoutParams)getChildAt(i13 - 1).getLayoutParams()).preventEdgeOffset) {
                  f2 = f1 - 0.5F;
                }
              }
            }
            if (f2 <= 0.0F) {
              break label1037;
            }
          }
        }
      }
    }
    label540:
    label703:
    label709:
    label715:
    label891:
    label1032:
    label1037:
    for (j = (int)(i2 * i12 / f2);; j = 0)
    {
      k = 0;
      if (k < i13) {
        break label1043;
      }
      j = paramInt2;
      if (j != 0)
      {
        paramInt2 = View.MeasureSpec.makeMeasureSpec(i7 - i11, i9);
        paramInt1 = 0;
        if (paramInt1 < i13) {
          break label1211;
        }
      }
      paramInt1 = i7;
      if (i9 != 1073741824) {
        paramInt1 = i;
      }
      setMeasuredDimension(i10, paramInt1);
      return;
      localObject = getChildAt(i1);
      if (((View)localObject).getVisibility() == 8)
      {
        l2 = l1;
        i2 = m;
        i3 = j;
        i1 += 1;
        j = i3;
        m = i2;
        l1 = l2;
        break;
      }
      boolean bool = localObject instanceof ActionMenuItemView;
      int i4 = n + 1;
      if (bool) {
        ((View)localObject).setPadding(this.mGeneratedItemPadding, 0, this.mGeneratedItemPadding, 0);
      }
      localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
      localLayoutParams.expanded = false;
      localLayoutParams.extraPixels = 0;
      localLayoutParams.cellsUsed = 0;
      localLayoutParams.expandable = false;
      localLayoutParams.leftMargin = 0;
      localLayoutParams.rightMargin = 0;
      if ((bool) && (((ActionMenuItemView)localObject).hasText()))
      {
        bool = true;
        localLayoutParams.preventEdgeOffset = bool;
        if (!localLayoutParams.isOverflowButton) {
          break label703;
        }
      }
      int i5;
      for (n = 1;; n = paramInt1)
      {
        int i14 = measureChildForCells((View)localObject, i12, n, paramInt2, i11);
        i5 = Math.max(m, i14);
        m = k;
        if (localLayoutParams.expandable) {
          m = k + 1;
        }
        if (localLayoutParams.isOverflowButton) {
          j = 1;
        }
        i6 = paramInt1 - i14;
        int i8 = Math.max(i, ((View)localObject).getMeasuredHeight());
        paramInt1 = i6;
        k = m;
        i3 = j;
        i2 = i5;
        i = i8;
        l2 = l1;
        n = i4;
        if (i14 != 1) {
          break;
        }
        l2 = l1 | 1 << i1;
        paramInt1 = i6;
        k = m;
        i3 = j;
        i2 = i5;
        i = i8;
        n = i4;
        break;
        bool = false;
        break label540;
      }
      i1 = 0;
      break label137;
      int i3 = Integer.MAX_VALUE;
      long l3 = 0L;
      int i6 = 0;
      i4 = 0;
      if (i4 >= i13)
      {
        l1 |= l3;
        l2 = l1;
        if (i6 > i2) {
          break label160;
        }
        paramInt1 = 0;
        if (paramInt1 < i13) {
          break label891;
        }
        paramInt2 = 1;
        break label142;
      }
      localObject = (LayoutParams)getChildAt(i4).getLayoutParams();
      if (!((LayoutParams)localObject).expandable)
      {
        l2 = l3;
        paramInt1 = i6;
        i5 = i3;
      }
      for (;;)
      {
        i4 += 1;
        i3 = i5;
        i6 = paramInt1;
        l3 = l2;
        break;
        if (((LayoutParams)localObject).cellsUsed < i3)
        {
          i5 = ((LayoutParams)localObject).cellsUsed;
          l2 = 1 << i4;
          paramInt1 = 1;
        }
        else
        {
          i5 = i3;
          paramInt1 = i6;
          l2 = l3;
          if (((LayoutParams)localObject).cellsUsed == i3)
          {
            l2 = l3 | 1 << i4;
            paramInt1 = i6 + 1;
            i5 = i3;
          }
        }
      }
      localObject = getChildAt(paramInt1);
      localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
      if ((1 << paramInt1 & l3) == 0L)
      {
        paramInt2 = i2;
        l2 = l1;
        if (localLayoutParams.cellsUsed == i3 + 1)
        {
          l2 = l1 | 1 << paramInt1;
          paramInt2 = i2;
        }
      }
      for (;;)
      {
        paramInt1 += 1;
        i2 = paramInt2;
        l1 = l2;
        break;
        if ((i1 != 0) && (localLayoutParams.preventEdgeOffset) && (i2 == 1)) {
          ((View)localObject).setPadding(this.mGeneratedItemPadding + i12, 0, this.mGeneratedItemPadding, 0);
        }
        localLayoutParams.cellsUsed += 1;
        localLayoutParams.expanded = true;
        paramInt2 = i2 - 1;
        l2 = l1;
      }
      paramInt1 = 0;
      break label173;
    }
    label1043:
    if ((1 << k & l2) == 0L) {
      paramInt1 = paramInt2;
    }
    for (;;)
    {
      k += 1;
      paramInt2 = paramInt1;
      break;
      localObject = getChildAt(k);
      localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
      if ((localObject instanceof ActionMenuItemView))
      {
        localLayoutParams.extraPixels = j;
        localLayoutParams.expanded = true;
        if ((k == 0) && (!localLayoutParams.preventEdgeOffset)) {
          localLayoutParams.leftMargin = (-j / 2);
        }
        paramInt1 = 1;
      }
      else if (localLayoutParams.isOverflowButton)
      {
        localLayoutParams.extraPixels = j;
        localLayoutParams.expanded = true;
        localLayoutParams.rightMargin = (-j / 2);
        paramInt1 = 1;
      }
      else
      {
        if (k != 0) {
          localLayoutParams.leftMargin = (j / 2);
        }
        paramInt1 = paramInt2;
        if (k != i13 - 1)
        {
          localLayoutParams.rightMargin = (j / 2);
          paramInt1 = paramInt2;
        }
      }
    }
    label1211:
    Object localObject = getChildAt(paramInt1);
    LayoutParams localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
    if (!localLayoutParams.expanded) {}
    for (;;)
    {
      paramInt1 += 1;
      break;
      ((View)localObject).measure(View.MeasureSpec.makeMeasureSpec(localLayoutParams.cellsUsed * i12 + localLayoutParams.extraPixels, 1073741824), paramInt2);
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return (paramLayoutParams != null) && ((paramLayoutParams instanceof LayoutParams));
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    return false;
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    LayoutParams localLayoutParams = new LayoutParams(-2, -2);
    localLayoutParams.gravity = 16;
    return localLayoutParams;
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof LayoutParams))
    {
      paramLayoutParams = new LayoutParams((LayoutParams)paramLayoutParams);
      if (paramLayoutParams.gravity <= 0) {
        paramLayoutParams.gravity = 16;
      }
      return paramLayoutParams;
    }
    return generateDefaultLayoutParams();
  }
  
  public LayoutParams generateOverflowButtonLayoutParams()
  {
    LayoutParams localLayoutParams = generateDefaultLayoutParams();
    localLayoutParams.isOverflowButton = true;
    return localLayoutParams;
  }
  
  public int getWindowAnimations()
  {
    return 0;
  }
  
  protected boolean hasDividerBeforeChildAt(int paramInt)
  {
    boolean bool2;
    if (paramInt == 0) {
      bool2 = false;
    }
    View localView2;
    boolean bool1;
    do
    {
      do
      {
        return bool2;
        View localView1 = getChildAt(paramInt - 1);
        localView2 = getChildAt(paramInt);
        bool2 = false;
        bool1 = bool2;
        if (paramInt < getChildCount())
        {
          bool1 = bool2;
          if ((localView1 instanceof ActionMenuChildView)) {
            bool1 = false | ((ActionMenuChildView)localView1).needsDividerAfter();
          }
        }
        bool2 = bool1;
      } while (paramInt <= 0);
      bool2 = bool1;
    } while (!(localView2 instanceof ActionMenuChildView));
    return bool1 | ((ActionMenuChildView)localView2).needsDividerBefore();
  }
  
  public void initialize(MenuBuilder paramMenuBuilder)
  {
    this.mMenu = paramMenuBuilder;
  }
  
  public boolean invokeItem(MenuItemImpl paramMenuItemImpl)
  {
    return this.mMenu.performItemAction(paramMenuItemImpl, 0);
  }
  
  public boolean isExpandedFormat()
  {
    return this.mFormatItems;
  }
  
  public boolean isOverflowReserved()
  {
    return this.mReserveOverflow;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (IS_FROYO) {
      super.onConfigurationChanged(paramConfiguration);
    }
    this.mPresenter.updateMenuView(false);
    if ((this.mPresenter != null) && (this.mPresenter.isOverflowMenuShowing()))
    {
      this.mPresenter.hideOverflowMenu();
      this.mPresenter.showOverflowMenu();
    }
  }
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.mPresenter.dismissPopupMenus();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if ((!IS_FROYO) && (this.mFirst))
    {
      this.mFirst = false;
      requestLayout();
      return;
    }
    super.onDraw(paramCanvas);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!this.mFormatItems)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    int n = getChildCount();
    int m = (paramInt2 + paramInt4) / 2;
    paramInt4 = 0;
    paramInt2 = paramInt3 - paramInt1 - getPaddingRight() - getPaddingLeft();
    int j = 0;
    int i = 0;
    View localView;
    LayoutParams localLayoutParams;
    if (i >= n)
    {
      if ((n == 1) && (j == 0))
      {
        localView = getChildAt(0);
        paramInt2 = localView.getMeasuredWidth();
        paramInt4 = localView.getMeasuredHeight();
        paramInt1 = (paramInt3 - paramInt1) / 2 - paramInt2 / 2;
        paramInt3 = m - paramInt4 / 2;
        localView.layout(paramInt1, paramInt3, paramInt1 + paramInt2, paramInt3 + paramInt4);
      }
    }
    else
    {
      localView = getChildAt(i);
      if (localView.getVisibility() == 8) {}
      for (;;)
      {
        i += 1;
        break;
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (localLayoutParams.isOverflowButton)
        {
          int k = localView.getMeasuredWidth();
          j = k;
          if (hasDividerBeforeChildAt(i)) {
            j = k + 0;
          }
          k = localView.getMeasuredHeight();
          int i1 = getWidth() - getPaddingRight() - localLayoutParams.rightMargin;
          int i2 = m - k / 2;
          localView.layout(i1 - j, i2, i1, i2 + k);
          paramInt2 -= j;
          j = 1;
        }
        else
        {
          paramInt2 -= localView.getMeasuredWidth() + localLayoutParams.leftMargin + localLayoutParams.rightMargin;
          paramInt4 += 1;
        }
      }
    }
    if (j != 0)
    {
      paramInt1 = 0;
      label302:
      paramInt1 = paramInt4 - paramInt1;
      if (paramInt1 <= 0) {
        break label391;
      }
      paramInt1 = paramInt2 / paramInt1;
      label315:
      paramInt4 = Math.max(0, paramInt1);
      paramInt2 = getPaddingLeft();
      paramInt1 = 0;
      label329:
      if (paramInt1 < n)
      {
        localView = getChildAt(paramInt1);
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        paramInt3 = paramInt2;
        if (localView.getVisibility() != 8) {
          if (!localLayoutParams.isOverflowButton) {
            break label396;
          }
        }
      }
    }
    for (paramInt3 = paramInt2;; paramInt3 = paramInt2 + (localLayoutParams.rightMargin + paramInt3 + paramInt4))
    {
      paramInt1 += 1;
      paramInt2 = paramInt3;
      break label329;
      break;
      paramInt1 = 1;
      break label302;
      label391:
      paramInt1 = 0;
      break label315;
      label396:
      paramInt2 += localLayoutParams.leftMargin;
      paramInt3 = localView.getMeasuredWidth();
      i = localView.getMeasuredHeight();
      j = m - i / 2;
      localView.layout(paramInt2, j, paramInt2 + paramInt3, j + i);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    boolean bool2 = this.mFormatItems;
    if (View.MeasureSpec.getMode(paramInt1) == 1073741824) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.mFormatItems = bool1;
      if (bool2 != this.mFormatItems) {
        this.mFormatItemsWidth = 0;
      }
      int i = View.MeasureSpec.getMode(paramInt1);
      if ((this.mFormatItems) && (this.mMenu != null) && (i != this.mFormatItemsWidth))
      {
        this.mFormatItemsWidth = i;
        this.mMenu.onItemsChanged(true);
      }
      if (!this.mFormatItems) {
        break;
      }
      onMeasureExactFormat(paramInt1, paramInt2);
      return;
    }
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public void setOverflowReserved(boolean paramBoolean)
  {
    this.mReserveOverflow = paramBoolean;
  }
  
  public void setPresenter(ActionMenuPresenter paramActionMenuPresenter)
  {
    this.mPresenter = paramActionMenuPresenter;
  }
  
  public static abstract interface ActionMenuChildView
  {
    public abstract boolean needsDividerAfter();
    
    public abstract boolean needsDividerBefore();
  }
  
  public static class LayoutParams
    extends LinearLayout.LayoutParams
  {
    public int cellsUsed;
    public boolean expandable;
    public boolean expanded;
    public int extraPixels;
    public boolean isOverflowButton;
    public boolean preventEdgeOffset;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      this.isOverflowButton = false;
    }
    
    public LayoutParams(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      super(paramInt2);
      this.isOverflowButton = paramBoolean;
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      this.isOverflowButton = paramLayoutParams.isOverflowButton;
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\view\menu\ActionMenuView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */