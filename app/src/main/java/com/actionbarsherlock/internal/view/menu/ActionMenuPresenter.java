package com.actionbarsherlock.internal.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.actionbarsherlock.R.attr;
import com.actionbarsherlock.R.integer;
import com.actionbarsherlock.R.layout;
import com.actionbarsherlock.internal.ResourcesCompat;
import com.actionbarsherlock.internal.view.View_HasStateListenerSupport;
import com.actionbarsherlock.internal.view.View_OnAttachStateChangeListener;
import com.actionbarsherlock.view.ActionProvider;
import com.actionbarsherlock.view.ActionProvider.SubUiVisibilityListener;
import com.actionbarsherlock.view.MenuItem;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ActionMenuPresenter
  extends BaseMenuPresenter
  implements ActionProvider.SubUiVisibilityListener
{
  private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
  private ActionButtonSubmenu mActionButtonPopup;
  private int mActionItemWidthLimit;
  private boolean mExpandedActionViewsExclusive;
  private int mMaxItems;
  private boolean mMaxItemsSet;
  private int mMinCellSize;
  int mOpenSubMenuId;
  private View mOverflowButton;
  private OverflowPopup mOverflowPopup;
  final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback(null);
  private OpenOverflowRunnable mPostedOpenRunnable;
  private boolean mReserveOverflow;
  private boolean mReserveOverflowSet;
  private View mScrapActionButtonView;
  private boolean mStrictWidthLimit;
  private int mWidthLimit;
  private boolean mWidthLimitSet;
  
  public ActionMenuPresenter(Context paramContext)
  {
    super(paramContext, R.layout.abs__action_menu_layout, R.layout.abs__action_menu_item_layout);
  }
  
  private View findViewForItem(MenuItem paramMenuItem)
  {
    ViewGroup localViewGroup = (ViewGroup)this.mMenuView;
    Object localObject;
    if (localViewGroup == null)
    {
      localObject = null;
      return (View)localObject;
    }
    int j = localViewGroup.getChildCount();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return null;
      }
      View localView = localViewGroup.getChildAt(i);
      if ((localView instanceof MenuView.ItemView))
      {
        localObject = localView;
        if (((MenuView.ItemView)localView).getItemData() == paramMenuItem) {
          break;
        }
      }
      i += 1;
    }
  }
  
  public static boolean reserveOverflow(Context paramContext)
  {
    boolean bool = false;
    if (Build.VERSION.SDK_INT < 14) {
      if (Build.VERSION.SDK_INT >= 11) {
        bool = true;
      }
    }
    while (HasPermanentMenuKey.get(paramContext)) {
      return bool;
    }
    return true;
  }
  
  public void bindItemView(MenuItemImpl paramMenuItemImpl, MenuView.ItemView paramItemView)
  {
    paramItemView.initialize(paramMenuItemImpl, 0);
    paramMenuItemImpl = (ActionMenuView)this.mMenuView;
    ((ActionMenuItemView)paramItemView).setItemInvoker(paramMenuItemImpl);
  }
  
  public boolean dismissPopupMenus()
  {
    return hideOverflowMenu() | hideSubMenus();
  }
  
  public boolean filterLeftoverView(ViewGroup paramViewGroup, int paramInt)
  {
    if (paramViewGroup.getChildAt(paramInt) == this.mOverflowButton) {
      return false;
    }
    return super.filterLeftoverView(paramViewGroup, paramInt);
  }
  
  public boolean flagActionItems()
  {
    ArrayList localArrayList = this.mMenu.getVisibleItems();
    int i6 = localArrayList.size();
    int i = this.mMaxItems;
    int i4 = this.mActionItemWidthLimit;
    int i7 = View.MeasureSpec.makeMeasureSpec(0, 0);
    ViewGroup localViewGroup = (ViewGroup)this.mMenuView;
    int j = 0;
    int m = 0;
    int i2 = 0;
    int n = 0;
    int k = 0;
    Object localObject1;
    int i3;
    int i1;
    if (k >= i6)
    {
      k = i;
      if (this.mReserveOverflow) {
        if (n == 0)
        {
          k = i;
          if (j + m <= i) {}
        }
        else
        {
          k = i - 1;
        }
      }
      k -= j;
      localObject1 = this.mActionButtonGroups;
      ((SparseBooleanArray)localObject1).clear();
      i3 = 0;
      j = 0;
      if (this.mStrictWidthLimit)
      {
        j = i4 / this.mMinCellSize;
        i = this.mMinCellSize;
        i3 = this.mMinCellSize + i4 % i / j;
      }
      i = 0;
      m = i4;
      i4 = i;
      i = i2;
      if (i4 >= i6) {
        return true;
      }
    }
    else
    {
      localObject1 = (MenuItemImpl)localArrayList.get(k);
      if (((MenuItemImpl)localObject1).requiresActionButton()) {
        j += 1;
      }
      for (;;)
      {
        i1 = i;
        if (this.mExpandedActionViewsExclusive)
        {
          i1 = i;
          if (((MenuItemImpl)localObject1).isActionViewExpanded()) {
            i1 = 0;
          }
        }
        k += 1;
        i = i1;
        break;
        if (((MenuItemImpl)localObject1).requestsActionButton()) {
          m += 1;
        } else {
          n = 1;
        }
      }
    }
    MenuItemImpl localMenuItemImpl = (MenuItemImpl)localArrayList.get(i4);
    Object localObject2;
    if (localMenuItemImpl.requiresActionButton())
    {
      localObject2 = getItemView(localMenuItemImpl, this.mScrapActionButtonView, localViewGroup);
      if (this.mScrapActionButtonView == null) {
        this.mScrapActionButtonView = ((View)localObject2);
      }
      if (this.mStrictWidthLimit)
      {
        j -= ActionMenuView.measureChildForCells((View)localObject2, i3, j, i7, 0);
        label312:
        i2 = ((View)localObject2).getMeasuredWidth();
        n = m - i2;
        i1 = i;
        if (i == 0) {
          i1 = i2;
        }
        i = localMenuItemImpl.getGroupId();
        if (i != 0) {
          ((SparseBooleanArray)localObject1).put(i, true);
        }
        localMenuItemImpl.setIsActionButton(true);
        i5 = k;
        i2 = j;
      }
    }
    do
    {
      i4 += 1;
      j = i2;
      i = i1;
      k = i5;
      m = n;
      break;
      ((View)localObject2).measure(i7, i7);
      break label312;
      i2 = j;
      i1 = i;
      i5 = k;
      n = m;
    } while (!localMenuItemImpl.requestsActionButton());
    int i5 = localMenuItemImpl.getGroupId();
    boolean bool = ((SparseBooleanArray)localObject1).get(i5);
    int i8;
    label465:
    int i9;
    if (((k > 0) || (bool)) && (m > 0) && ((!this.mStrictWidthLimit) || (j > 0)))
    {
      i8 = 1;
      i2 = j;
      i1 = i;
      i9 = i8;
      n = m;
      if (i8 != 0)
      {
        localObject2 = getItemView(localMenuItemImpl, this.mScrapActionButtonView, localViewGroup);
        if (this.mScrapActionButtonView == null) {
          this.mScrapActionButtonView = ((View)localObject2);
        }
        if (!this.mStrictWidthLimit) {
          break label649;
        }
        i1 = ActionMenuView.measureChildForCells((View)localObject2, i3, j, i7, 0);
        n = j - i1;
        j = n;
        if (i1 == 0)
        {
          i8 = 0;
          j = n;
        }
        label551:
        i2 = ((View)localObject2).getMeasuredWidth();
        n = m - i2;
        i1 = i;
        if (i == 0) {
          i1 = i2;
        }
        if (!this.mStrictWidthLimit) {
          break label666;
        }
        if (n < 0) {
          break label661;
        }
        i = 1;
        label590:
        i9 = i8 & i;
        i2 = j;
      }
      if ((i9 == 0) || (i5 == 0)) {
        break label693;
      }
      ((SparseBooleanArray)localObject1).put(i5, true);
      i = k;
    }
    label649:
    label661:
    label666:
    label693:
    do
    {
      j = i;
      if (i9 != 0) {
        j = i - 1;
      }
      localMenuItemImpl.setIsActionButton(i9);
      i5 = j;
      break;
      i8 = 0;
      break label465;
      ((View)localObject2).measure(i7, i7);
      break label551;
      i = 0;
      break label590;
      if (n + i1 > 0) {}
      for (i = 1;; i = 0)
      {
        i9 = i8 & i;
        i2 = j;
        break;
      }
      i = k;
    } while (!bool);
    ((SparseBooleanArray)localObject1).put(i5, false);
    j = 0;
    for (;;)
    {
      i = k;
      if (j >= i4) {
        break;
      }
      localObject2 = (MenuItemImpl)localArrayList.get(j);
      i = k;
      if (((MenuItemImpl)localObject2).getGroupId() == i5)
      {
        i = k;
        if (((MenuItemImpl)localObject2).isActionButton()) {
          i = k + 1;
        }
        ((MenuItemImpl)localObject2).setIsActionButton(false);
      }
      j += 1;
      k = i;
    }
  }
  
  public View getItemView(MenuItemImpl paramMenuItemImpl, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramMenuItemImpl.getActionView();
    if ((localView == null) || (paramMenuItemImpl.hasCollapsibleActionView()))
    {
      localView = paramView;
      if (!(paramView instanceof ActionMenuItemView)) {
        localView = null;
      }
      localView = super.getItemView(paramMenuItemImpl, localView, paramViewGroup);
    }
    if (paramMenuItemImpl.isActionViewExpanded()) {}
    for (int i = 8;; i = 0)
    {
      localView.setVisibility(i);
      paramMenuItemImpl = (ActionMenuView)paramViewGroup;
      paramView = localView.getLayoutParams();
      if (!paramMenuItemImpl.checkLayoutParams(paramView)) {
        localView.setLayoutParams(paramMenuItemImpl.generateLayoutParams(paramView));
      }
      return localView;
    }
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    paramViewGroup = super.getMenuView(paramViewGroup);
    ((ActionMenuView)paramViewGroup).setPresenter(this);
    return paramViewGroup;
  }
  
  public boolean hideOverflowMenu()
  {
    if ((this.mPostedOpenRunnable != null) && (this.mMenuView != null))
    {
      ((View)this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
      this.mPostedOpenRunnable = null;
      return true;
    }
    OverflowPopup localOverflowPopup = this.mOverflowPopup;
    if (localOverflowPopup != null)
    {
      localOverflowPopup.dismiss();
      return true;
    }
    return false;
  }
  
  public boolean hideSubMenus()
  {
    if (this.mActionButtonPopup != null)
    {
      this.mActionButtonPopup.dismiss();
      return true;
    }
    return false;
  }
  
  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
  {
    super.initForMenu(paramContext, paramMenuBuilder);
    paramMenuBuilder = paramContext.getResources();
    if (!this.mReserveOverflowSet) {
      this.mReserveOverflow = reserveOverflow(this.mContext);
    }
    if (!this.mWidthLimitSet) {
      this.mWidthLimit = (paramMenuBuilder.getDisplayMetrics().widthPixels / 2);
    }
    if (!this.mMaxItemsSet) {
      this.mMaxItems = ResourcesCompat.getResources_getInteger(paramContext, R.integer.abs__max_action_buttons);
    }
    int i = this.mWidthLimit;
    if (this.mReserveOverflow)
    {
      if (this.mOverflowButton == null)
      {
        this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
        int j = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mOverflowButton.measure(j, j);
      }
      i -= this.mOverflowButton.getMeasuredWidth();
    }
    for (;;)
    {
      this.mActionItemWidthLimit = i;
      this.mMinCellSize = ((int)(56.0F * paramMenuBuilder.getDisplayMetrics().density));
      this.mScrapActionButtonView = null;
      return;
      this.mOverflowButton = null;
    }
  }
  
  public boolean isOverflowMenuShowing()
  {
    return (this.mOverflowPopup != null) && (this.mOverflowPopup.isShowing());
  }
  
  public boolean isOverflowReserved()
  {
    return this.mReserveOverflow;
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    dismissPopupMenus();
    super.onCloseMenu(paramMenuBuilder, paramBoolean);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (!this.mMaxItemsSet)
    {
      this.mMaxItems = ResourcesCompat.getResources_getInteger(this.mContext, R.integer.abs__max_action_buttons);
      if (this.mMenu != null) {
        this.mMenu.onItemsChanged(true);
      }
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    if (paramParcelable.openSubMenuId > 0)
    {
      paramParcelable = this.mMenu.findItem(paramParcelable.openSubMenuId);
      if (paramParcelable != null) {
        onSubMenuSelected((SubMenuBuilder)paramParcelable.getSubMenu());
      }
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState();
    localSavedState.openSubMenuId = this.mOpenSubMenuId;
    return localSavedState;
  }
  
  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
  {
    if (!paramSubMenuBuilder.hasVisibleItems()) {
      return false;
    }
    for (Object localObject = paramSubMenuBuilder;; localObject = (SubMenuBuilder)((SubMenuBuilder)localObject).getParentMenu()) {
      if (((SubMenuBuilder)localObject).getParentMenu() == this.mMenu)
      {
        View localView = findViewForItem(((SubMenuBuilder)localObject).getItem());
        localObject = localView;
        if (localView == null)
        {
          if (this.mOverflowButton == null) {
            break;
          }
          localObject = this.mOverflowButton;
        }
        this.mOpenSubMenuId = paramSubMenuBuilder.getItem().getItemId();
        this.mActionButtonPopup = new ActionButtonSubmenu(this.mContext, paramSubMenuBuilder);
        this.mActionButtonPopup.setAnchorView((View)localObject);
        this.mActionButtonPopup.show();
        super.onSubMenuSelected(paramSubMenuBuilder);
        return true;
      }
    }
  }
  
  public void onSubUiVisibilityChanged(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      super.onSubMenuSelected(null);
      return;
    }
    this.mMenu.close(false);
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean)
  {
    this.mExpandedActionViewsExclusive = paramBoolean;
  }
  
  public void setItemLimit(int paramInt)
  {
    this.mMaxItems = paramInt;
    this.mMaxItemsSet = true;
  }
  
  public void setReserveOverflow(boolean paramBoolean)
  {
    this.mReserveOverflow = paramBoolean;
    this.mReserveOverflowSet = true;
  }
  
  public void setWidthLimit(int paramInt, boolean paramBoolean)
  {
    this.mWidthLimit = paramInt;
    this.mStrictWidthLimit = paramBoolean;
    this.mWidthLimitSet = true;
  }
  
  public boolean shouldIncludeItem(int paramInt, MenuItemImpl paramMenuItemImpl)
  {
    return paramMenuItemImpl.isActionButton();
  }
  
  public boolean showOverflowMenu()
  {
    if ((this.mReserveOverflow) && (!isOverflowMenuShowing()) && (this.mMenu != null) && (this.mMenuView != null) && (this.mPostedOpenRunnable == null) && (!this.mMenu.getNonActionItems().isEmpty()))
    {
      this.mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, this.mOverflowButton, true));
      ((View)this.mMenuView).post(this.mPostedOpenRunnable);
      super.onSubMenuSelected(null);
      return true;
    }
    return false;
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    super.updateMenuView(paramBoolean);
    Object localObject;
    int j;
    int i;
    if (this.mMenu != null)
    {
      localObject = this.mMenu.getActionItems();
      j = ((ArrayList)localObject).size();
      i = 0;
      if (i < j) {}
    }
    else
    {
      if (this.mMenu == null) {
        break label228;
      }
      localObject = this.mMenu.getNonActionItems();
      label50:
      j = 0;
      i = j;
      if (this.mReserveOverflow)
      {
        i = j;
        if (localObject != null)
        {
          i = ((ArrayList)localObject).size();
          if (i != 1) {
            break label239;
          }
          if (!((MenuItemImpl)((ArrayList)localObject).get(0)).isActionViewExpanded()) {
            break label234;
          }
          i = 0;
        }
      }
      label96:
      if (i == 0) {
        break label253;
      }
      if (this.mOverflowButton == null) {
        this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
      }
      localObject = (ViewGroup)this.mOverflowButton.getParent();
      if (localObject != this.mMenuView)
      {
        if (localObject != null) {
          ((ViewGroup)localObject).removeView(this.mOverflowButton);
        }
        localObject = (ActionMenuView)this.mMenuView;
        ((ActionMenuView)localObject).addView(this.mOverflowButton, ((ActionMenuView)localObject).generateOverflowButtonLayoutParams());
      }
    }
    for (;;)
    {
      ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
      return;
      ActionProvider localActionProvider = ((MenuItemImpl)((ArrayList)localObject).get(i)).getActionProvider();
      if (localActionProvider != null) {
        localActionProvider.setSubUiVisibilityListener(this);
      }
      i += 1;
      break;
      label228:
      localObject = null;
      break label50;
      label234:
      i = 1;
      break label96;
      label239:
      if (i > 0) {}
      for (i = 1;; i = 0) {
        break;
      }
      label253:
      if ((this.mOverflowButton != null) && (this.mOverflowButton.getParent() == this.mMenuView)) {
        ((ViewGroup)this.mMenuView).removeView(this.mOverflowButton);
      }
    }
  }
  
  private class ActionButtonSubmenu
    extends MenuPopupHelper
  {
    public ActionButtonSubmenu(Context paramContext, SubMenuBuilder paramSubMenuBuilder)
    {
      super(paramSubMenuBuilder);
      boolean bool;
      int j;
      int i;
      if (!((MenuItemImpl)paramSubMenuBuilder.getItem()).isActionButton())
      {
        if (ActionMenuPresenter.this.mOverflowButton == null)
        {
          paramContext = (View)ActionMenuPresenter.this.mMenuView;
          setAnchorView(paramContext);
        }
      }
      else
      {
        setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        bool = false;
        j = paramSubMenuBuilder.size();
        i = 0;
      }
      for (;;)
      {
        if (i >= j) {}
        for (;;)
        {
          setForceShowIcon(bool);
          return;
          paramContext = ActionMenuPresenter.this.mOverflowButton;
          break;
          this$1 = paramSubMenuBuilder.getItem(i);
          if ((!ActionMenuPresenter.this.isVisible()) || (ActionMenuPresenter.this.getIcon() == null)) {
            break label117;
          }
          bool = true;
        }
        label117:
        i += 1;
      }
    }
    
    public void onDismiss()
    {
      super.onDismiss();
      ActionMenuPresenter.this.mActionButtonPopup = null;
      ActionMenuPresenter.this.mOpenSubMenuId = 0;
    }
  }
  
  private static class HasPermanentMenuKey
  {
    public static boolean get(Context paramContext)
    {
      return ViewConfiguration.get(paramContext).hasPermanentMenuKey();
    }
  }
  
  private class OpenOverflowRunnable
    implements Runnable
  {
    private ActionMenuPresenter.OverflowPopup mPopup;
    
    public OpenOverflowRunnable(ActionMenuPresenter.OverflowPopup paramOverflowPopup)
    {
      this.mPopup = paramOverflowPopup;
    }
    
    public void run()
    {
      ActionMenuPresenter.this.mMenu.changeMenuMode();
      View localView = (View)ActionMenuPresenter.this.mMenuView;
      if ((localView != null) && (localView.getWindowToken() != null) && (this.mPopup.tryShow())) {
        ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
      }
      ActionMenuPresenter.this.mPostedOpenRunnable = null;
    }
  }
  
  private class OverflowMenuButton
    extends ImageButton
    implements ActionMenuView.ActionMenuChildView, View_HasStateListenerSupport
  {
    private final Set<View_OnAttachStateChangeListener> mListeners = new HashSet();
    
    public OverflowMenuButton(Context paramContext)
    {
      super(null, R.attr.actionOverflowButtonStyle);
      setClickable(true);
      setFocusable(true);
      setVisibility(0);
      setEnabled(true);
    }
    
    public void addOnAttachStateChangeListener(View_OnAttachStateChangeListener paramView_OnAttachStateChangeListener)
    {
      this.mListeners.add(paramView_OnAttachStateChangeListener);
    }
    
    public boolean needsDividerAfter()
    {
      return false;
    }
    
    public boolean needsDividerBefore()
    {
      return false;
    }
    
    protected void onAttachedToWindow()
    {
      super.onAttachedToWindow();
      Iterator localIterator = this.mListeners.iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return;
        }
        ((View_OnAttachStateChangeListener)localIterator.next()).onViewAttachedToWindow(this);
      }
    }
    
    protected void onDetachedFromWindow()
    {
      super.onDetachedFromWindow();
      Iterator localIterator = this.mListeners.iterator();
      for (;;)
      {
        if (!localIterator.hasNext())
        {
          if (ActionMenuPresenter.this.mOverflowPopup != null) {
            ActionMenuPresenter.this.mOverflowPopup.dismiss();
          }
          return;
        }
        ((View_OnAttachStateChangeListener)localIterator.next()).onViewDetachedFromWindow(this);
      }
    }
    
    public boolean performClick()
    {
      if (super.performClick()) {
        return true;
      }
      playSoundEffect(0);
      ActionMenuPresenter.this.showOverflowMenu();
      return true;
    }
    
    public void removeOnAttachStateChangeListener(View_OnAttachStateChangeListener paramView_OnAttachStateChangeListener)
    {
      this.mListeners.remove(paramView_OnAttachStateChangeListener);
    }
  }
  
  private class OverflowPopup
    extends MenuPopupHelper
  {
    public OverflowPopup(Context paramContext, MenuBuilder paramMenuBuilder, View paramView, boolean paramBoolean)
    {
      super(paramMenuBuilder, paramView, paramBoolean);
      setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
    }
    
    public void onDismiss()
    {
      super.onDismiss();
      ActionMenuPresenter.this.mMenu.close();
      ActionMenuPresenter.this.mOverflowPopup = null;
    }
  }
  
  private class PopupPresenterCallback
    implements MenuPresenter.Callback
  {
    private PopupPresenterCallback() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
    {
      if ((paramMenuBuilder instanceof SubMenuBuilder)) {
        ((SubMenuBuilder)paramMenuBuilder).getRootMenu().close(false);
      }
    }
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      if (paramMenuBuilder == null) {
        return false;
      }
      ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder)paramMenuBuilder).getItem().getItemId();
      return false;
    }
  }
  
  private static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public ActionMenuPresenter.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new ActionMenuPresenter.SavedState(paramAnonymousParcel);
      }
      
      public ActionMenuPresenter.SavedState[] newArray(int paramAnonymousInt)
      {
        return new ActionMenuPresenter.SavedState[paramAnonymousInt];
      }
    };
    public int openSubMenuId;
    
    SavedState() {}
    
    SavedState(Parcel paramParcel)
    {
      this.openSubMenuId = paramParcel.readInt();
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(this.openSubMenuId);
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\view\menu\ActionMenuPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */