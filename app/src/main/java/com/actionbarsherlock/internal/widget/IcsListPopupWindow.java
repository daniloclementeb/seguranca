package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import com.actionbarsherlock.R.attr;

public class IcsListPopupWindow
{
  private static final int EXPAND_LIST_TIMEOUT = 250;
  public static final int POSITION_PROMPT_ABOVE = 0;
  public static final int POSITION_PROMPT_BELOW = 1;
  private ListAdapter mAdapter;
  private Context mContext;
  private View mDropDownAnchorView;
  private int mDropDownHeight = -2;
  private int mDropDownHorizontalOffset;
  private DropDownListView mDropDownList;
  private Drawable mDropDownListHighlight;
  private int mDropDownVerticalOffset;
  private boolean mDropDownVerticalOffsetSet;
  private int mDropDownWidth = -2;
  private Handler mHandler = new Handler();
  private final ListSelectorHider mHideSelector = new ListSelectorHider(null);
  private AdapterView.OnItemClickListener mItemClickListener;
  private AdapterView.OnItemSelectedListener mItemSelectedListener;
  private int mListItemExpandMaximum = Integer.MAX_VALUE;
  private boolean mModal;
  private DataSetObserver mObserver;
  private final PopupWindowCompat mPopup;
  private int mPromptPosition = 0;
  private View mPromptView;
  private final ResizePopupRunnable mResizePopupRunnable = new ResizePopupRunnable(null);
  private final PopupScrollListener mScrollListener = new PopupScrollListener(null);
  private Rect mTempRect = new Rect();
  private final PopupTouchInterceptor mTouchInterceptor = new PopupTouchInterceptor(null);
  
  public IcsListPopupWindow(Context paramContext)
  {
    this(paramContext, null, R.attr.listPopupWindowStyle);
  }
  
  public IcsListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this.mContext = paramContext;
    this.mPopup = new PopupWindowCompat(paramContext, paramAttributeSet, paramInt);
    this.mPopup.setInputMethodMode(1);
  }
  
  public IcsListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    this.mContext = paramContext;
    if (Build.VERSION.SDK_INT < 11) {}
    for (this.mPopup = new PopupWindowCompat(new ContextThemeWrapper(paramContext, paramInt2), paramAttributeSet, paramInt1);; this.mPopup = new PopupWindowCompat(paramContext, paramAttributeSet, paramInt1, paramInt2))
    {
      this.mPopup.setInputMethodMode(1);
      return;
    }
  }
  
  private int buildDropDown()
  {
    int j = 0;
    int i = 0;
    Object localObject3;
    Object localObject2;
    View localView;
    Object localObject1;
    if (this.mDropDownList == null)
    {
      localObject3 = this.mContext;
      if (this.mModal)
      {
        bool = false;
        this.mDropDownList = new DropDownListView((Context)localObject3, bool);
        if (this.mDropDownListHighlight != null) {
          this.mDropDownList.setSelector(this.mDropDownListHighlight);
        }
        this.mDropDownList.setAdapter(this.mAdapter);
        this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
        this.mDropDownList.setFocusable(true);
        this.mDropDownList.setFocusableInTouchMode(true);
        this.mDropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
          public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            if (paramAnonymousInt != -1)
            {
              paramAnonymousAdapterView = IcsListPopupWindow.this.mDropDownList;
              if (paramAnonymousAdapterView != null) {
                IcsListPopupWindow.DropDownListView.access$0(paramAnonymousAdapterView, false);
              }
            }
          }
          
          public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
        });
        this.mDropDownList.setOnScrollListener(this.mScrollListener);
        if (this.mItemSelectedListener != null) {
          this.mDropDownList.setOnItemSelectedListener(this.mItemSelectedListener);
        }
        localObject2 = this.mDropDownList;
        localView = this.mPromptView;
        localObject1 = localObject2;
        if (localView != null)
        {
          localObject1 = new LinearLayout((Context)localObject3);
          ((LinearLayout)localObject1).setOrientation(1);
          localObject3 = new LinearLayout.LayoutParams(-1, 0, 1.0F);
        }
        switch (this.mPromptPosition)
        {
        default: 
          label220:
          localView.measure(View.MeasureSpec.makeMeasureSpec(this.mDropDownWidth, Integer.MIN_VALUE), 0);
          localObject2 = (LinearLayout.LayoutParams)localView.getLayoutParams();
          i = localView.getMeasuredHeight() + ((LinearLayout.LayoutParams)localObject2).topMargin + ((LinearLayout.LayoutParams)localObject2).bottomMargin;
          this.mPopup.setContentView((View)localObject1);
          label272:
          j = 0;
          localObject1 = this.mPopup.getBackground();
          if (localObject1 != null)
          {
            ((Drawable)localObject1).getPadding(this.mTempRect);
            k = this.mTempRect.top + this.mTempRect.bottom;
            j = k;
            if (!this.mDropDownVerticalOffsetSet)
            {
              this.mDropDownVerticalOffset = (-this.mTempRect.top);
              j = k;
            }
          }
          if (this.mPopup.getInputMethodMode() != 2) {
            break;
          }
        }
      }
    }
    for (boolean bool = true;; bool = false)
    {
      k = getMaxAvailableHeight(this.mDropDownAnchorView, this.mDropDownVerticalOffset, bool);
      if (this.mDropDownHeight != -1) {
        break label484;
      }
      return k + j;
      bool = true;
      break;
      ((LinearLayout)localObject1).addView((View)localObject2, (ViewGroup.LayoutParams)localObject3);
      ((LinearLayout)localObject1).addView(localView);
      break label220;
      ((LinearLayout)localObject1).addView(localView);
      ((LinearLayout)localObject1).addView((View)localObject2, (ViewGroup.LayoutParams)localObject3);
      break label220;
      localObject1 = (ViewGroup)this.mPopup.getContentView();
      localObject1 = this.mPromptView;
      i = j;
      if (localObject1 == null) {
        break label272;
      }
      localObject2 = (LinearLayout.LayoutParams)((View)localObject1).getLayoutParams();
      i = ((View)localObject1).getMeasuredHeight() + ((LinearLayout.LayoutParams)localObject2).topMargin + ((LinearLayout.LayoutParams)localObject2).bottomMargin;
      break label272;
    }
    label484:
    int m = measureHeightOfChildren(0, 0, -1, k - i, -1);
    int k = i;
    if (m > 0) {
      k = i + j;
    }
    return m + k;
  }
  
  private int getMaxAvailableHeight(View paramView, int paramInt, boolean paramBoolean)
  {
    Rect localRect = new Rect();
    paramView.getWindowVisibleDisplayFrame(localRect);
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    int i = localRect.bottom;
    if (paramBoolean) {
      i = paramView.getContext().getResources().getDisplayMetrics().heightPixels;
    }
    i = Math.max(i - (arrayOfInt[1] + paramView.getHeight()) - paramInt, arrayOfInt[1] - localRect.top + paramInt);
    paramInt = i;
    if (this.mPopup.getBackground() != null)
    {
      this.mPopup.getBackground().getPadding(this.mTempRect);
      paramInt = i - (this.mTempRect.top + this.mTempRect.bottom);
    }
    return paramInt;
  }
  
  private boolean isInputMethodNotNeeded()
  {
    return this.mPopup.getInputMethodMode() == 2;
  }
  
  private int measureHeightOfChildren(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    Object localObject = this.mAdapter;
    if (localObject == null)
    {
      paramInt2 = this.mDropDownList.getListPaddingTop() + this.mDropDownList.getListPaddingBottom();
      return paramInt2;
    }
    int m = this.mDropDownList.getListPaddingTop() + this.mDropDownList.getListPaddingBottom();
    int i;
    label75:
    int n;
    int j;
    int k;
    if ((this.mDropDownList.getDividerHeight() > 0) && (this.mDropDownList.getDivider() != null))
    {
      i = this.mDropDownList.getDividerHeight();
      n = 0;
      j = paramInt3;
      if (paramInt3 == -1) {
        j = ((ListAdapter)localObject).getCount() - 1;
      }
      k = paramInt2;
      paramInt3 = m;
    }
    for (paramInt2 = n;; paramInt2 = m)
    {
      if (k > j)
      {
        return paramInt3;
        i = 0;
        break label75;
      }
      localObject = this.mAdapter.getView(k, null, this.mDropDownList);
      if (this.mDropDownList.getCacheColorHint() != 0) {
        ((View)localObject).setDrawingCacheBackgroundColor(this.mDropDownList.getCacheColorHint());
      }
      measureScrapChild((View)localObject, k, paramInt1);
      m = paramInt3;
      if (k > 0) {
        m = paramInt3 + i;
      }
      paramInt3 = m + ((View)localObject).getMeasuredHeight();
      if (paramInt3 >= paramInt4)
      {
        if ((paramInt5 >= 0) && (k > paramInt5) && (paramInt2 > 0) && (paramInt3 != paramInt4)) {
          break;
        }
        return paramInt4;
      }
      m = paramInt2;
      if (paramInt5 >= 0)
      {
        m = paramInt2;
        if (k >= paramInt5) {
          m = paramInt3;
        }
      }
      k += 1;
    }
  }
  
  private void measureScrapChild(View paramView, int paramInt1, int paramInt2)
  {
    AbsListView.LayoutParams localLayoutParams2 = (AbsListView.LayoutParams)paramView.getLayoutParams();
    AbsListView.LayoutParams localLayoutParams1 = localLayoutParams2;
    if (localLayoutParams2 == null)
    {
      localLayoutParams1 = new AbsListView.LayoutParams(-1, -2, 0);
      paramView.setLayoutParams(localLayoutParams1);
    }
    paramInt2 = ViewGroup.getChildMeasureSpec(paramInt2, this.mDropDownList.getPaddingLeft() + this.mDropDownList.getPaddingRight(), localLayoutParams1.width);
    paramInt1 = localLayoutParams1.height;
    if (paramInt1 > 0) {}
    for (paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);; paramInt1 = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(paramInt2, paramInt1);
      return;
    }
  }
  
  public void clearListSelection()
  {
    DropDownListView localDropDownListView = this.mDropDownList;
    if (localDropDownListView != null)
    {
      localDropDownListView.mListSelectionHidden = true;
      localDropDownListView.requestLayout();
    }
  }
  
  public void dismiss()
  {
    this.mPopup.dismiss();
    if (this.mPromptView != null)
    {
      ViewParent localViewParent = this.mPromptView.getParent();
      if ((localViewParent instanceof ViewGroup)) {
        ((ViewGroup)localViewParent).removeView(this.mPromptView);
      }
    }
    this.mPopup.setContentView(null);
    this.mDropDownList = null;
    this.mHandler.removeCallbacks(this.mResizePopupRunnable);
  }
  
  public ListView getListView()
  {
    return this.mDropDownList;
  }
  
  public boolean isShowing()
  {
    return this.mPopup.isShowing();
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (this.mObserver == null) {
      this.mObserver = new PopupDataSetObserver(null);
    }
    for (;;)
    {
      this.mAdapter = paramListAdapter;
      if (this.mAdapter != null) {
        paramListAdapter.registerDataSetObserver(this.mObserver);
      }
      if (this.mDropDownList != null) {
        this.mDropDownList.setAdapter(this.mAdapter);
      }
      return;
      if (this.mAdapter != null) {
        this.mAdapter.unregisterDataSetObserver(this.mObserver);
      }
    }
  }
  
  public void setAnchorView(View paramView)
  {
    this.mDropDownAnchorView = paramView;
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    this.mPopup.setBackgroundDrawable(paramDrawable);
  }
  
  public void setContentWidth(int paramInt)
  {
    Drawable localDrawable = this.mPopup.getBackground();
    if (localDrawable != null)
    {
      localDrawable.getPadding(this.mTempRect);
      this.mDropDownWidth = (this.mTempRect.left + this.mTempRect.right + paramInt);
      return;
    }
    this.mDropDownWidth = paramInt;
  }
  
  public void setHorizontalOffset(int paramInt)
  {
    this.mDropDownHorizontalOffset = paramInt;
  }
  
  public void setInputMethodMode(int paramInt)
  {
    this.mPopup.setInputMethodMode(paramInt);
  }
  
  public void setModal(boolean paramBoolean)
  {
    this.mModal = true;
    this.mPopup.setFocusable(paramBoolean);
  }
  
  public void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    this.mPopup.setOnDismissListener(paramOnDismissListener);
  }
  
  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    this.mItemClickListener = paramOnItemClickListener;
  }
  
  public void setPromptPosition(int paramInt)
  {
    this.mPromptPosition = paramInt;
  }
  
  public void setSelection(int paramInt)
  {
    DropDownListView localDropDownListView = this.mDropDownList;
    if ((isShowing()) && (localDropDownListView != null))
    {
      localDropDownListView.mListSelectionHidden = false;
      localDropDownListView.setSelection(paramInt);
      if (localDropDownListView.getChoiceMode() != 0) {
        localDropDownListView.setItemChecked(paramInt, true);
      }
    }
  }
  
  public void setVerticalOffset(int paramInt)
  {
    this.mDropDownVerticalOffset = paramInt;
    this.mDropDownVerticalOffsetSet = true;
  }
  
  public void show()
  {
    int k = 0;
    int m = -1;
    int j = buildDropDown();
    int i = 0;
    int n = 0;
    boolean bool = isInputMethodNotNeeded();
    if (this.mPopup.isShowing())
    {
      label54:
      PopupWindowCompat localPopupWindowCompat;
      if (this.mDropDownWidth == -1)
      {
        i = -1;
        if (this.mDropDownHeight != -1) {
          break label177;
        }
        if (!bool) {
          break label141;
        }
        if (!bool) {
          break label151;
        }
        localPopupWindowCompat = this.mPopup;
        if (this.mDropDownWidth != -1) {
          break label146;
        }
        k = m;
        label76:
        localPopupWindowCompat.setWindowLayoutMode(k, 0);
      }
      for (;;)
      {
        this.mPopup.setOutsideTouchable(true);
        this.mPopup.update(this.mDropDownAnchorView, this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, i, j);
        return;
        if (this.mDropDownWidth == -2)
        {
          i = this.mDropDownAnchorView.getWidth();
          break;
        }
        i = this.mDropDownWidth;
        break;
        label141:
        j = -1;
        break label54;
        label146:
        k = 0;
        break label76;
        label151:
        localPopupWindowCompat = this.mPopup;
        if (this.mDropDownWidth == -1) {
          k = -1;
        }
        localPopupWindowCompat.setWindowLayoutMode(k, -1);
        continue;
        label177:
        if (this.mDropDownHeight != -2) {
          j = this.mDropDownHeight;
        }
      }
    }
    if (this.mDropDownWidth == -1)
    {
      i = -1;
      label207:
      if (this.mDropDownHeight != -1) {
        break label353;
      }
      j = -1;
    }
    for (;;)
    {
      this.mPopup.setWindowLayoutMode(i, j);
      this.mPopup.setOutsideTouchable(true);
      this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
      this.mPopup.showAsDropDown(this.mDropDownAnchorView, this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset);
      this.mDropDownList.setSelection(-1);
      if ((!this.mModal) || (this.mDropDownList.isInTouchMode())) {
        clearListSelection();
      }
      if (this.mModal) {
        break;
      }
      this.mHandler.post(this.mHideSelector);
      return;
      if (this.mDropDownWidth == -2)
      {
        this.mPopup.setWidth(this.mDropDownAnchorView.getWidth());
        break label207;
      }
      this.mPopup.setWidth(this.mDropDownWidth);
      break label207;
      label353:
      if (this.mDropDownHeight == -2)
      {
        this.mPopup.setHeight(j);
        j = n;
      }
      else
      {
        this.mPopup.setHeight(this.mDropDownHeight);
        j = n;
      }
    }
  }
  
  private static class DropDownListView
    extends ListView
  {
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    
    public DropDownListView(Context paramContext, boolean paramBoolean)
    {
      super(null, R.attr.dropDownListViewStyle);
      this.mHijackFocus = paramBoolean;
      setCacheColorHint(0);
    }
    
    public boolean hasFocus()
    {
      return (this.mHijackFocus) || (super.hasFocus());
    }
    
    public boolean hasWindowFocus()
    {
      return (this.mHijackFocus) || (super.hasWindowFocus());
    }
    
    public boolean isFocused()
    {
      return (this.mHijackFocus) || (super.isFocused());
    }
    
    public boolean isInTouchMode()
    {
      return ((this.mHijackFocus) && (this.mListSelectionHidden)) || (super.isInTouchMode());
    }
  }
  
  private class ListSelectorHider
    implements Runnable
  {
    private ListSelectorHider() {}
    
    public void run()
    {
      IcsListPopupWindow.this.clearListSelection();
    }
  }
  
  private class PopupDataSetObserver
    extends DataSetObserver
  {
    private PopupDataSetObserver() {}
    
    public void onChanged()
    {
      if (IcsListPopupWindow.this.isShowing()) {
        IcsListPopupWindow.this.show();
      }
    }
    
    public void onInvalidated()
    {
      IcsListPopupWindow.this.dismiss();
    }
  }
  
  private class PopupScrollListener
    implements AbsListView.OnScrollListener
  {
    private PopupScrollListener() {}
    
    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
      if ((paramInt == 1) && (!IcsListPopupWindow.this.isInputMethodNotNeeded()) && (IcsListPopupWindow.this.mPopup.getContentView() != null))
      {
        IcsListPopupWindow.this.mHandler.removeCallbacks(IcsListPopupWindow.this.mResizePopupRunnable);
        IcsListPopupWindow.this.mResizePopupRunnable.run();
      }
    }
  }
  
  private class PopupTouchInterceptor
    implements View.OnTouchListener
  {
    private PopupTouchInterceptor() {}
    
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      int i = paramMotionEvent.getAction();
      int j = (int)paramMotionEvent.getX();
      int k = (int)paramMotionEvent.getY();
      if ((i == 0) && (IcsListPopupWindow.this.mPopup != null) && (IcsListPopupWindow.this.mPopup.isShowing()) && (j >= 0) && (j < IcsListPopupWindow.this.mPopup.getWidth()) && (k >= 0) && (k < IcsListPopupWindow.this.mPopup.getHeight())) {
        IcsListPopupWindow.this.mHandler.postDelayed(IcsListPopupWindow.this.mResizePopupRunnable, 250L);
      }
      for (;;)
      {
        return false;
        if (i == 1) {
          IcsListPopupWindow.this.mHandler.removeCallbacks(IcsListPopupWindow.this.mResizePopupRunnable);
        }
      }
    }
  }
  
  private class ResizePopupRunnable
    implements Runnable
  {
    private ResizePopupRunnable() {}
    
    public void run()
    {
      if ((IcsListPopupWindow.this.mDropDownList != null) && (IcsListPopupWindow.this.mDropDownList.getCount() > IcsListPopupWindow.this.mDropDownList.getChildCount()) && (IcsListPopupWindow.this.mDropDownList.getChildCount() <= IcsListPopupWindow.this.mListItemExpandMaximum))
      {
        IcsListPopupWindow.this.mPopup.setInputMethodMode(2);
        IcsListPopupWindow.this.show();
      }
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\widget\IcsListPopupWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */