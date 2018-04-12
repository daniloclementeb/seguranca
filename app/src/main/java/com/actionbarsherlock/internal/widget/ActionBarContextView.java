package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbarsherlock.R.attr;
import com.actionbarsherlock.R.id;
import com.actionbarsherlock.R.layout;
import com.actionbarsherlock.R.styleable;
import com.actionbarsherlock.internal.nineoldandroids.animation.Animator;
import com.actionbarsherlock.internal.nineoldandroids.animation.Animator.AnimatorListener;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorSet;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorSet.Builder;
import com.actionbarsherlock.internal.nineoldandroids.animation.ObjectAnimator;
import com.actionbarsherlock.internal.nineoldandroids.view.animation.AnimatorProxy;
import com.actionbarsherlock.internal.nineoldandroids.widget.NineLinearLayout;
import com.actionbarsherlock.internal.view.menu.ActionMenuPresenter;
import com.actionbarsherlock.internal.view.menu.ActionMenuView;
import com.actionbarsherlock.internal.view.menu.MenuBuilder;
import com.actionbarsherlock.view.ActionMode;

public class ActionBarContextView
  extends AbsActionBarView
  implements Animator.AnimatorListener
{
  private static final int ANIMATE_IDLE = 0;
  private static final int ANIMATE_IN = 1;
  private static final int ANIMATE_OUT = 2;
  private boolean mAnimateInOnLayout;
  private int mAnimationMode;
  private NineLinearLayout mClose;
  private Animator mCurrentAnimation;
  private View mCustomView;
  private Drawable mSplitBackground;
  private CharSequence mSubtitle;
  private int mSubtitleStyleRes;
  private TextView mSubtitleView;
  private CharSequence mTitle;
  private LinearLayout mTitleLayout;
  private int mTitleStyleRes;
  private TextView mTitleView;
  
  public ActionBarContextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionBarContextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.actionModeStyle);
  }
  
  public ActionBarContextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SherlockActionMode, paramInt, 0);
    setBackgroundDrawable(paramContext.getDrawable(R.styleable.SherlockActionMode_background));
    this.mTitleStyleRes = paramContext.getResourceId(R.styleable.SherlockActionMode_titleTextStyle, 0);
    this.mSubtitleStyleRes = paramContext.getResourceId(R.styleable.SherlockActionMode_subtitleTextStyle, 0);
    this.mContentHeight = paramContext.getLayoutDimension(R.styleable.SherlockActionMode_height, 0);
    this.mSplitBackground = paramContext.getDrawable(R.styleable.SherlockActionMode_backgroundSplit);
    paramContext.recycle();
  }
  
  private void finishAnimation()
  {
    Animator localAnimator = this.mCurrentAnimation;
    if (localAnimator != null)
    {
      this.mCurrentAnimation = null;
      localAnimator.end();
    }
  }
  
  private void initTitle()
  {
    int m = 8;
    if (this.mTitleLayout == null)
    {
      LayoutInflater.from(getContext()).inflate(R.layout.abs__action_bar_title_item, this);
      this.mTitleLayout = ((LinearLayout)getChildAt(getChildCount() - 1));
      this.mTitleView = ((TextView)this.mTitleLayout.findViewById(R.id.abs__action_bar_title));
      this.mSubtitleView = ((TextView)this.mTitleLayout.findViewById(R.id.abs__action_bar_subtitle));
      if (this.mTitleStyleRes != 0) {
        this.mTitleView.setTextAppearance(this.mContext, this.mTitleStyleRes);
      }
      if (this.mSubtitleStyleRes != 0) {
        this.mSubtitleView.setTextAppearance(this.mContext, this.mSubtitleStyleRes);
      }
    }
    this.mTitleView.setText(this.mTitle);
    this.mSubtitleView.setText(this.mSubtitle);
    int i;
    int j;
    label167:
    Object localObject;
    if (TextUtils.isEmpty(this.mTitle))
    {
      i = 0;
      if (!TextUtils.isEmpty(this.mSubtitle)) {
        break label234;
      }
      j = 0;
      localObject = this.mSubtitleView;
      if (j == 0) {
        break label239;
      }
    }
    label234:
    label239:
    for (int k = 0;; k = 8)
    {
      ((TextView)localObject).setVisibility(k);
      localObject = this.mTitleLayout;
      if (i == 0)
      {
        i = m;
        if (j == 0) {}
      }
      else
      {
        i = 0;
      }
      ((LinearLayout)localObject).setVisibility(i);
      if (this.mTitleLayout.getParent() == null) {
        addView(this.mTitleLayout);
      }
      return;
      i = 1;
      break;
      j = 1;
      break label167;
    }
  }
  
  private Animator makeInAnimation()
  {
    this.mClose.setTranslationX(-this.mClose.getWidth() - ((ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams()).leftMargin);
    Object localObject1 = ObjectAnimator.ofFloat(this.mClose, "translationX", new float[] { 0.0F });
    ((ObjectAnimator)localObject1).setDuration(200L);
    ((ObjectAnimator)localObject1).addListener(this);
    ((ObjectAnimator)localObject1).setInterpolator(new DecelerateInterpolator());
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localObject1 = localAnimatorSet.play((Animator)localObject1);
    int i;
    int j;
    if (this.mMenuView != null)
    {
      i = this.mMenuView.getChildCount();
      if (i > 0)
      {
        j = i - 1;
        i = 0;
      }
    }
    for (;;)
    {
      if (j < 0) {
        return localAnimatorSet;
      }
      Object localObject2 = AnimatorProxy.wrap(this.mMenuView.getChildAt(j));
      ((AnimatorProxy)localObject2).setScaleY(0.0F);
      localObject2 = ObjectAnimator.ofFloat(localObject2, "scaleY", new float[] { 0.0F, 1.0F });
      ((ObjectAnimator)localObject2).setDuration(100L);
      ((ObjectAnimator)localObject2).setStartDelay(i * 70);
      ((AnimatorSet.Builder)localObject1).with((Animator)localObject2);
      j -= 1;
      i += 1;
    }
  }
  
  private Animator makeOutAnimation()
  {
    Object localObject1 = ObjectAnimator.ofFloat(this.mClose, "translationX", new float[] { -this.mClose.getWidth() - ((ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams()).leftMargin });
    ((ObjectAnimator)localObject1).setDuration(200L);
    ((ObjectAnimator)localObject1).addListener(this);
    ((ObjectAnimator)localObject1).setInterpolator(new DecelerateInterpolator());
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localObject1 = localAnimatorSet.play((Animator)localObject1);
    int i;
    if ((this.mMenuView != null) && (this.mMenuView.getChildCount() > 0)) {
      i = 0;
    }
    for (;;)
    {
      if (i >= 0) {
        return localAnimatorSet;
      }
      Object localObject2 = AnimatorProxy.wrap(this.mMenuView.getChildAt(i));
      ((AnimatorProxy)localObject2).setScaleY(0.0F);
      localObject2 = ObjectAnimator.ofFloat(localObject2, "scaleY", new float[] { 0.0F });
      ((ObjectAnimator)localObject2).setDuration(100L);
      ((ObjectAnimator)localObject2).setStartDelay(i * 70);
      ((AnimatorSet.Builder)localObject1).with((Animator)localObject2);
      i += 1;
    }
  }
  
  public void closeMode()
  {
    if (this.mAnimationMode == 2) {
      return;
    }
    if (this.mClose == null)
    {
      killMode();
      return;
    }
    finishAnimation();
    this.mAnimationMode = 2;
    this.mCurrentAnimation = makeOutAnimation();
    this.mCurrentAnimation.start();
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new ViewGroup.MarginLayoutParams(-1, -2);
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new ViewGroup.MarginLayoutParams(getContext(), paramAttributeSet);
  }
  
  public CharSequence getSubtitle()
  {
    return this.mSubtitle;
  }
  
  public CharSequence getTitle()
  {
    return this.mTitle;
  }
  
  public boolean hideOverflowMenu()
  {
    if (this.mActionMenuPresenter != null) {
      return this.mActionMenuPresenter.hideOverflowMenu();
    }
    return false;
  }
  
  public void initForMode(final ActionMode paramActionMode)
  {
    ViewGroup.LayoutParams localLayoutParams;
    if (this.mClose == null)
    {
      this.mClose = ((NineLinearLayout)LayoutInflater.from(this.mContext).inflate(R.layout.abs__action_mode_close_item, this, false));
      addView(this.mClose);
      this.mClose.findViewById(R.id.abs__action_mode_close_button).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramActionMode.finish();
        }
      });
      paramActionMode = (MenuBuilder)paramActionMode.getMenu();
      if (this.mActionMenuPresenter != null) {
        this.mActionMenuPresenter.dismissPopupMenus();
      }
      this.mActionMenuPresenter = new ActionMenuPresenter(this.mContext);
      this.mActionMenuPresenter.setReserveOverflow(true);
      localLayoutParams = new ViewGroup.LayoutParams(-2, -1);
      if (this.mSplitActionBar) {
        break label190;
      }
      paramActionMode.addMenuPresenter(this.mActionMenuPresenter);
      this.mMenuView = ((ActionMenuView)this.mActionMenuPresenter.getMenuView(this));
      this.mMenuView.setBackgroundDrawable(null);
      addView(this.mMenuView, localLayoutParams);
    }
    for (;;)
    {
      this.mAnimateInOnLayout = true;
      return;
      if (this.mClose.getParent() != null) {
        break;
      }
      addView(this.mClose);
      break;
      label190:
      this.mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
      this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
      localLayoutParams.width = -1;
      localLayoutParams.height = this.mContentHeight;
      paramActionMode.addMenuPresenter(this.mActionMenuPresenter);
      this.mMenuView = ((ActionMenuView)this.mActionMenuPresenter.getMenuView(this));
      this.mMenuView.setBackgroundDrawable(this.mSplitBackground);
      this.mSplitView.addView(this.mMenuView, localLayoutParams);
    }
  }
  
  public boolean isOverflowMenuShowing()
  {
    if (this.mActionMenuPresenter != null) {
      return this.mActionMenuPresenter.isOverflowMenuShowing();
    }
    return false;
  }
  
  public void killMode()
  {
    finishAnimation();
    removeAllViews();
    if (this.mSplitView != null) {
      this.mSplitView.removeView(this.mMenuView);
    }
    this.mCustomView = null;
    this.mMenuView = null;
    this.mAnimateInOnLayout = false;
  }
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (this.mAnimationMode == 2) {
      killMode();
    }
    this.mAnimationMode = 0;
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {}
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mActionMenuPresenter != null)
    {
      this.mActionMenuPresenter.hideOverflowMenu();
      this.mActionMenuPresenter.hideSubMenus();
    }
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (paramAccessibilityEvent.getEventType() == 32)
    {
      paramAccessibilityEvent.setClassName(getClass().getName());
      paramAccessibilityEvent.setPackageName(getContext().getPackageName());
      paramAccessibilityEvent.setContentDescription(this.mTitle);
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getPaddingLeft();
    int j = getPaddingTop();
    int k = paramInt4 - paramInt2 - getPaddingTop() - getPaddingBottom();
    paramInt2 = i;
    if (this.mClose != null)
    {
      paramInt2 = i;
      if (this.mClose.getVisibility() != 8)
      {
        ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams();
        paramInt2 = i + localMarginLayoutParams.leftMargin;
        paramInt4 = paramInt2 + positionChild(this.mClose, paramInt2, j, k) + localMarginLayoutParams.rightMargin;
        paramInt2 = paramInt4;
        if (this.mAnimateInOnLayout)
        {
          this.mAnimationMode = 1;
          this.mCurrentAnimation = makeInAnimation();
          this.mCurrentAnimation.start();
          this.mAnimateInOnLayout = false;
          paramInt2 = paramInt4;
        }
      }
    }
    paramInt4 = paramInt2;
    if (this.mTitleLayout != null)
    {
      paramInt4 = paramInt2;
      if (this.mCustomView == null) {
        paramInt4 = paramInt2 + positionChild(this.mTitleLayout, paramInt2, j, k);
      }
    }
    if (this.mCustomView != null) {
      positionChild(this.mCustomView, paramInt4, j, k);
    }
    paramInt1 = paramInt3 - paramInt1 - getPaddingRight();
    if (this.mMenuView != null) {
      positionChildInverse(this.mMenuView, paramInt1, j, k);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (View.MeasureSpec.getMode(paramInt1) != 1073741824) {
      throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
    }
    if (View.MeasureSpec.getMode(paramInt2) == 0) {
      throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
    }
    int m = View.MeasureSpec.getSize(paramInt1);
    int i;
    int n;
    int k;
    int j;
    if (this.mContentHeight > 0)
    {
      i = this.mContentHeight;
      n = getPaddingTop() + getPaddingBottom();
      paramInt2 = m - getPaddingLeft() - getPaddingRight();
      k = i - n;
      j = View.MeasureSpec.makeMeasureSpec(k, Integer.MIN_VALUE);
      paramInt1 = paramInt2;
      Object localObject;
      if (this.mClose != null)
      {
        paramInt1 = measureChildView(this.mClose, paramInt2, j, 0);
        localObject = (ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams();
        paramInt1 -= ((ViewGroup.MarginLayoutParams)localObject).leftMargin + ((ViewGroup.MarginLayoutParams)localObject).rightMargin;
      }
      paramInt2 = paramInt1;
      if (this.mMenuView != null)
      {
        paramInt2 = paramInt1;
        if (this.mMenuView.getParent() == this) {
          paramInt2 = measureChildView(this.mMenuView, paramInt1, j, 0);
        }
      }
      paramInt1 = paramInt2;
      if (this.mTitleLayout != null)
      {
        paramInt1 = paramInt2;
        if (this.mCustomView == null) {
          paramInt1 = measureChildView(this.mTitleLayout, paramInt2, j, 0);
        }
      }
      if (this.mCustomView != null)
      {
        localObject = this.mCustomView.getLayoutParams();
        if (((ViewGroup.LayoutParams)localObject).width == -2) {
          break label410;
        }
        paramInt2 = 1073741824;
        label299:
        if (((ViewGroup.LayoutParams)localObject).width < 0) {
          break label417;
        }
        paramInt1 = Math.min(((ViewGroup.LayoutParams)localObject).width, paramInt1);
        label317:
        if (((ViewGroup.LayoutParams)localObject).height == -2) {
          break label420;
        }
        j = 1073741824;
        label332:
        if (((ViewGroup.LayoutParams)localObject).height < 0) {
          break label428;
        }
        k = Math.min(((ViewGroup.LayoutParams)localObject).height, k);
        label352:
        this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1, paramInt2), View.MeasureSpec.makeMeasureSpec(k, j));
      }
      if (this.mContentHeight > 0) {
        break label464;
      }
      paramInt2 = 0;
      k = getChildCount();
      paramInt1 = 0;
    }
    for (;;)
    {
      if (paramInt1 >= k)
      {
        setMeasuredDimension(m, paramInt2);
        return;
        i = View.MeasureSpec.getSize(paramInt2);
        break;
        label410:
        paramInt2 = Integer.MIN_VALUE;
        break label299;
        label417:
        break label317;
        label420:
        j = Integer.MIN_VALUE;
        break label332;
        label428:
        break label352;
      }
      j = getChildAt(paramInt1).getMeasuredHeight() + n;
      i = paramInt2;
      if (j > paramInt2) {
        i = j;
      }
      paramInt1 += 1;
      paramInt2 = i;
    }
    label464:
    setMeasuredDimension(m, i);
  }
  
  public void setContentHeight(int paramInt)
  {
    this.mContentHeight = paramInt;
  }
  
  public void setCustomView(View paramView)
  {
    if (this.mCustomView != null) {
      removeView(this.mCustomView);
    }
    this.mCustomView = paramView;
    if (this.mTitleLayout != null)
    {
      removeView(this.mTitleLayout);
      this.mTitleLayout = null;
    }
    if (paramView != null) {
      addView(paramView);
    }
    requestLayout();
  }
  
  public void setSplitActionBar(boolean paramBoolean)
  {
    ViewGroup.LayoutParams localLayoutParams;
    ViewGroup localViewGroup;
    if (this.mSplitActionBar != paramBoolean) {
      if (this.mActionMenuPresenter != null)
      {
        localLayoutParams = new ViewGroup.LayoutParams(-2, -1);
        if (paramBoolean) {
          break label91;
        }
        this.mMenuView = ((ActionMenuView)this.mActionMenuPresenter.getMenuView(this));
        this.mMenuView.setBackgroundDrawable(null);
        localViewGroup = (ViewGroup)this.mMenuView.getParent();
        if (localViewGroup != null) {
          localViewGroup.removeView(this.mMenuView);
        }
        addView(this.mMenuView, localLayoutParams);
      }
    }
    for (;;)
    {
      super.setSplitActionBar(paramBoolean);
      return;
      label91:
      this.mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
      this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
      localLayoutParams.width = -1;
      localLayoutParams.height = this.mContentHeight;
      this.mMenuView = ((ActionMenuView)this.mActionMenuPresenter.getMenuView(this));
      this.mMenuView.setBackgroundDrawable(this.mSplitBackground);
      localViewGroup = (ViewGroup)this.mMenuView.getParent();
      if (localViewGroup != null) {
        localViewGroup.removeView(this.mMenuView);
      }
      this.mSplitView.addView(this.mMenuView, localLayoutParams);
    }
  }
  
  public void setSubtitle(CharSequence paramCharSequence)
  {
    this.mSubtitle = paramCharSequence;
    initTitle();
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    this.mTitle = paramCharSequence;
    initTitle();
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return false;
  }
  
  public boolean showOverflowMenu()
  {
    if (this.mActionMenuPresenter != null) {
      return this.mActionMenuPresenter.showOverflowMenu();
    }
    return false;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\widget\ActionBarContextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */