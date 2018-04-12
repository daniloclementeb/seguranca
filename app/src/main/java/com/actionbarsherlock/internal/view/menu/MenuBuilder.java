package com.actionbarsherlock.internal.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyCharacterMap.KeyData;
import android.view.KeyEvent;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import com.actionbarsherlock.R.bool;
import com.actionbarsherlock.view.ActionProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuBuilder
  implements com.actionbarsherlock.view.Menu
{
  private static final String ACTION_VIEW_STATES_KEY = "android:menu:actionviewstates";
  private static final String EXPANDED_ACTION_VIEW_ID = "android:menu:expandedactionview";
  private static final String PRESENTER_KEY = "android:menu:presenters";
  private static final int[] sCategoryToOrder;
  private ArrayList<MenuItemImpl> mActionItems;
  private Callback mCallback;
  private final Context mContext;
  private ContextMenu.ContextMenuInfo mCurrentMenuInfo;
  private int mDefaultShowAsAction = 0;
  private MenuItemImpl mExpandedItem;
  Drawable mHeaderIcon;
  CharSequence mHeaderTitle;
  View mHeaderView;
  private boolean mIsActionItemsStale;
  private boolean mIsClosing = false;
  private boolean mIsVisibleItemsStale;
  private ArrayList<MenuItemImpl> mItems;
  private boolean mItemsChangedWhileDispatchPrevented = false;
  private ArrayList<MenuItemImpl> mNonActionItems;
  private boolean mOptionalIconsVisible = false;
  private CopyOnWriteArrayList<WeakReference<MenuPresenter>> mPresenters = new CopyOnWriteArrayList();
  private boolean mPreventDispatchingItemsChanged = false;
  private boolean mQwertyMode;
  private final Resources mResources;
  private boolean mShortcutsVisible;
  private ArrayList<MenuItemImpl> mTempShortcutItemList = new ArrayList();
  private ArrayList<MenuItemImpl> mVisibleItems;
  
  static
  {
    int[] arrayOfInt = new int[6];
    arrayOfInt[0] = 1;
    arrayOfInt[1] = 4;
    arrayOfInt[2] = 5;
    arrayOfInt[3] = 3;
    arrayOfInt[4] = 2;
    sCategoryToOrder = arrayOfInt;
  }
  
  public MenuBuilder(Context paramContext)
  {
    this.mContext = paramContext;
    this.mResources = paramContext.getResources();
    this.mItems = new ArrayList();
    this.mVisibleItems = new ArrayList();
    this.mIsVisibleItemsStale = true;
    this.mActionItems = new ArrayList();
    this.mNonActionItems = new ArrayList();
    this.mIsActionItemsStale = true;
    setShortcutsVisibleInner(true);
  }
  
  private com.actionbarsherlock.view.MenuItem addInternal(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    int i = getOrdering(paramInt3);
    paramCharSequence = new MenuItemImpl(this, paramInt1, paramInt2, paramInt3, i, paramCharSequence, this.mDefaultShowAsAction);
    if (this.mCurrentMenuInfo != null) {
      paramCharSequence.setMenuInfo(this.mCurrentMenuInfo);
    }
    this.mItems.add(findInsertIndex(this.mItems, i), paramCharSequence);
    onItemsChanged(true);
    return paramCharSequence;
  }
  
  private void dispatchPresenterUpdate(boolean paramBoolean)
  {
    if (this.mPresenters.isEmpty()) {
      return;
    }
    stopDispatchingItemsChanged();
    Iterator localIterator = this.mPresenters.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        startDispatchingItemsChanged();
        return;
      }
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      MenuPresenter localMenuPresenter = (MenuPresenter)localWeakReference.get();
      if (localMenuPresenter == null) {
        this.mPresenters.remove(localWeakReference);
      } else {
        localMenuPresenter.updateMenuView(paramBoolean);
      }
    }
  }
  
  private void dispatchRestoreInstanceState(Bundle paramBundle)
  {
    paramBundle = paramBundle.getSparseParcelableArray("android:menu:presenters");
    if ((paramBundle == null) || (this.mPresenters.isEmpty())) {}
    for (;;)
    {
      return;
      Iterator localIterator = this.mPresenters.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (WeakReference)localIterator.next();
        MenuPresenter localMenuPresenter = (MenuPresenter)((WeakReference)localObject).get();
        if (localMenuPresenter == null)
        {
          this.mPresenters.remove(localObject);
        }
        else
        {
          int i = localMenuPresenter.getId();
          if (i > 0)
          {
            localObject = (Parcelable)paramBundle.get(i);
            if (localObject != null) {
              localMenuPresenter.onRestoreInstanceState((Parcelable)localObject);
            }
          }
        }
      }
    }
  }
  
  private void dispatchSaveInstanceState(Bundle paramBundle)
  {
    if (this.mPresenters.isEmpty()) {
      return;
    }
    SparseArray localSparseArray = new SparseArray();
    Iterator localIterator = this.mPresenters.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        paramBundle.putSparseParcelableArray("android:menu:presenters", localSparseArray);
        return;
      }
      Object localObject = (WeakReference)localIterator.next();
      MenuPresenter localMenuPresenter = (MenuPresenter)((WeakReference)localObject).get();
      if (localMenuPresenter == null)
      {
        this.mPresenters.remove(localObject);
      }
      else
      {
        int i = localMenuPresenter.getId();
        if (i > 0)
        {
          localObject = localMenuPresenter.onSaveInstanceState();
          if (localObject != null) {
            localSparseArray.put(i, localObject);
          }
        }
      }
    }
  }
  
  private boolean dispatchSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
  {
    boolean bool2;
    if (this.mPresenters.isEmpty())
    {
      bool2 = false;
      return bool2;
    }
    boolean bool1 = false;
    Iterator localIterator = this.mPresenters.iterator();
    for (;;)
    {
      bool2 = bool1;
      if (!localIterator.hasNext()) {
        break;
      }
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      MenuPresenter localMenuPresenter = (MenuPresenter)localWeakReference.get();
      if (localMenuPresenter == null) {
        this.mPresenters.remove(localWeakReference);
      } else if (!bool1) {
        bool1 = localMenuPresenter.onSubMenuSelected(paramSubMenuBuilder);
      }
    }
  }
  
  private static int findInsertIndex(ArrayList<MenuItemImpl> paramArrayList, int paramInt)
  {
    int i = paramArrayList.size() - 1;
    for (;;)
    {
      if (i < 0) {
        return 0;
      }
      if (((MenuItemImpl)paramArrayList.get(i)).getOrdering() <= paramInt) {
        return i + 1;
      }
      i -= 1;
    }
  }
  
  private static int getOrdering(int paramInt)
  {
    int i = (0xFFFF0000 & paramInt) >> 16;
    if ((i < 0) || (i >= sCategoryToOrder.length)) {
      throw new IllegalArgumentException("order does not contain a valid category.");
    }
    return sCategoryToOrder[i] << 16 | 0xFFFF & paramInt;
  }
  
  private void removeItemAtInt(int paramInt, boolean paramBoolean)
  {
    if ((paramInt < 0) || (paramInt >= this.mItems.size())) {}
    do
    {
      return;
      this.mItems.remove(paramInt);
    } while (!paramBoolean);
    onItemsChanged(true);
  }
  
  private void setHeaderInternal(int paramInt1, CharSequence paramCharSequence, int paramInt2, Drawable paramDrawable, View paramView)
  {
    Resources localResources = getResources();
    if (paramView != null)
    {
      this.mHeaderView = paramView;
      this.mHeaderTitle = null;
      this.mHeaderIcon = null;
      onItemsChanged(false);
      return;
    }
    if (paramInt1 > 0)
    {
      this.mHeaderTitle = localResources.getText(paramInt1);
      label47:
      if (paramInt2 <= 0) {
        break label81;
      }
      this.mHeaderIcon = localResources.getDrawable(paramInt2);
    }
    for (;;)
    {
      this.mHeaderView = null;
      break;
      if (paramCharSequence == null) {
        break label47;
      }
      this.mHeaderTitle = paramCharSequence;
      break label47;
      label81:
      if (paramDrawable != null) {
        this.mHeaderIcon = paramDrawable;
      }
    }
  }
  
  private void setShortcutsVisibleInner(boolean paramBoolean)
  {
    boolean bool = true;
    if ((paramBoolean) && (this.mResources.getConfiguration().keyboard != 1) && (this.mResources.getBoolean(R.bool.abs__config_showMenuShortcutsWhenKeyboardPresent))) {}
    for (paramBoolean = bool;; paramBoolean = false)
    {
      this.mShortcutsVisible = paramBoolean;
      return;
    }
  }
  
  public com.actionbarsherlock.view.MenuItem add(int paramInt)
  {
    return addInternal(0, 0, 0, this.mResources.getString(paramInt));
  }
  
  public com.actionbarsherlock.view.MenuItem add(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return addInternal(paramInt1, paramInt2, paramInt3, this.mResources.getString(paramInt4));
  }
  
  public com.actionbarsherlock.view.MenuItem add(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    return addInternal(paramInt1, paramInt2, paramInt3, paramCharSequence);
  }
  
  public com.actionbarsherlock.view.MenuItem add(CharSequence paramCharSequence)
  {
    return addInternal(0, 0, 0, paramCharSequence);
  }
  
  public int addIntentOptions(int paramInt1, int paramInt2, int paramInt3, ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt4, com.actionbarsherlock.view.MenuItem[] paramArrayOfMenuItem)
  {
    PackageManager localPackageManager = this.mContext.getPackageManager();
    List localList = localPackageManager.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, 0);
    if (localList != null) {}
    for (int i = localList.size();; i = 0)
    {
      if ((paramInt4 & 0x1) == 0) {
        removeGroup(paramInt1);
      }
      paramInt4 = 0;
      if (paramInt4 < i) {
        break;
      }
      return i;
    }
    ResolveInfo localResolveInfo = (ResolveInfo)localList.get(paramInt4);
    if (localResolveInfo.specificIndex < 0) {}
    for (paramComponentName = paramIntent;; paramComponentName = paramArrayOfIntent[localResolveInfo.specificIndex])
    {
      paramComponentName = new Intent(paramComponentName);
      paramComponentName.setComponent(new ComponentName(localResolveInfo.activityInfo.applicationInfo.packageName, localResolveInfo.activityInfo.name));
      paramComponentName = add(paramInt1, paramInt2, paramInt3, localResolveInfo.loadLabel(localPackageManager)).setIcon(localResolveInfo.loadIcon(localPackageManager)).setIntent(paramComponentName);
      if ((paramArrayOfMenuItem != null) && (localResolveInfo.specificIndex >= 0)) {
        paramArrayOfMenuItem[localResolveInfo.specificIndex] = paramComponentName;
      }
      paramInt4 += 1;
      break;
    }
  }
  
  public void addMenuPresenter(MenuPresenter paramMenuPresenter)
  {
    this.mPresenters.add(new WeakReference(paramMenuPresenter));
    paramMenuPresenter.initForMenu(this.mContext, this);
    this.mIsActionItemsStale = true;
  }
  
  public com.actionbarsherlock.view.SubMenu addSubMenu(int paramInt)
  {
    return addSubMenu(0, 0, 0, this.mResources.getString(paramInt));
  }
  
  public com.actionbarsherlock.view.SubMenu addSubMenu(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return addSubMenu(paramInt1, paramInt2, paramInt3, this.mResources.getString(paramInt4));
  }
  
  public com.actionbarsherlock.view.SubMenu addSubMenu(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    paramCharSequence = (MenuItemImpl)addInternal(paramInt1, paramInt2, paramInt3, paramCharSequence);
    SubMenuBuilder localSubMenuBuilder = new SubMenuBuilder(this.mContext, this, paramCharSequence);
    paramCharSequence.setSubMenu(localSubMenuBuilder);
    return localSubMenuBuilder;
  }
  
  public com.actionbarsherlock.view.SubMenu addSubMenu(CharSequence paramCharSequence)
  {
    return addSubMenu(0, 0, 0, paramCharSequence);
  }
  
  public boolean bindNativeOverflow(android.view.Menu paramMenu, MenuItem.OnMenuItemClickListener paramOnMenuItemClickListener, HashMap<android.view.MenuItem, MenuItemImpl> paramHashMap)
  {
    Object localObject = getNonActionItems();
    boolean bool2;
    if ((localObject == null) || (((List)localObject).size() == 0))
    {
      bool2 = false;
      return bool2;
    }
    boolean bool1 = false;
    paramMenu.clear();
    Iterator localIterator1 = ((List)localObject).iterator();
    MenuItemImpl localMenuItemImpl1;
    do
    {
      bool2 = bool1;
      if (!localIterator1.hasNext()) {
        break;
      }
      localMenuItemImpl1 = (MenuItemImpl)localIterator1.next();
    } while (!localMenuItemImpl1.isVisible());
    bool1 = true;
    Iterator localIterator2;
    if (localMenuItemImpl1.hasSubMenu())
    {
      localObject = paramMenu.addSubMenu(localMenuItemImpl1.getGroupId(), localMenuItemImpl1.getItemId(), localMenuItemImpl1.getOrder(), localMenuItemImpl1.getTitle());
      localIterator2 = ((SubMenuBuilder)localMenuItemImpl1.getSubMenu()).getVisibleItems().iterator();
      label134:
      if (localIterator2.hasNext()) {}
    }
    for (localObject = ((android.view.SubMenu)localObject).getItem();; localObject = paramMenu.add(localMenuItemImpl1.getGroupId(), localMenuItemImpl1.getItemId(), localMenuItemImpl1.getOrder(), localMenuItemImpl1.getTitle()))
    {
      ((android.view.MenuItem)localObject).setIcon(localMenuItemImpl1.getIcon());
      ((android.view.MenuItem)localObject).setOnMenuItemClickListener(paramOnMenuItemClickListener);
      ((android.view.MenuItem)localObject).setEnabled(localMenuItemImpl1.isEnabled());
      ((android.view.MenuItem)localObject).setIntent(localMenuItemImpl1.getIntent());
      ((android.view.MenuItem)localObject).setNumericShortcut(localMenuItemImpl1.getNumericShortcut());
      ((android.view.MenuItem)localObject).setAlphabeticShortcut(localMenuItemImpl1.getAlphabeticShortcut());
      ((android.view.MenuItem)localObject).setTitleCondensed(localMenuItemImpl1.getTitleCondensed());
      ((android.view.MenuItem)localObject).setCheckable(localMenuItemImpl1.isCheckable());
      ((android.view.MenuItem)localObject).setChecked(localMenuItemImpl1.isChecked());
      if (localMenuItemImpl1.isExclusiveCheckable()) {
        paramMenu.setGroupCheckable(localMenuItemImpl1.getGroupId(), true, true);
      }
      paramHashMap.put(localObject, localMenuItemImpl1);
      break;
      MenuItemImpl localMenuItemImpl2 = (MenuItemImpl)localIterator2.next();
      android.view.MenuItem localMenuItem = ((android.view.SubMenu)localObject).add(localMenuItemImpl2.getGroupId(), localMenuItemImpl2.getItemId(), localMenuItemImpl2.getOrder(), localMenuItemImpl2.getTitle());
      localMenuItem.setIcon(localMenuItemImpl2.getIcon());
      localMenuItem.setOnMenuItemClickListener(paramOnMenuItemClickListener);
      localMenuItem.setEnabled(localMenuItemImpl2.isEnabled());
      localMenuItem.setIntent(localMenuItemImpl2.getIntent());
      localMenuItem.setNumericShortcut(localMenuItemImpl2.getNumericShortcut());
      localMenuItem.setAlphabeticShortcut(localMenuItemImpl2.getAlphabeticShortcut());
      localMenuItem.setTitleCondensed(localMenuItemImpl2.getTitleCondensed());
      localMenuItem.setCheckable(localMenuItemImpl2.isCheckable());
      localMenuItem.setChecked(localMenuItemImpl2.isChecked());
      if (localMenuItemImpl2.isExclusiveCheckable()) {
        ((android.view.SubMenu)localObject).setGroupCheckable(localMenuItemImpl2.getGroupId(), true, true);
      }
      paramHashMap.put(localMenuItem, localMenuItemImpl2);
      break label134;
    }
  }
  
  public void changeMenuMode()
  {
    if (this.mCallback != null) {
      this.mCallback.onMenuModeChange(this);
    }
  }
  
  public void clear()
  {
    if (this.mExpandedItem != null) {
      collapseItemActionView(this.mExpandedItem);
    }
    this.mItems.clear();
    onItemsChanged(true);
  }
  
  public void clearAll()
  {
    this.mPreventDispatchingItemsChanged = true;
    clear();
    clearHeader();
    this.mPreventDispatchingItemsChanged = false;
    this.mItemsChangedWhileDispatchPrevented = false;
    onItemsChanged(true);
  }
  
  public void clearHeader()
  {
    this.mHeaderIcon = null;
    this.mHeaderTitle = null;
    this.mHeaderView = null;
    onItemsChanged(false);
  }
  
  public void close()
  {
    close(true);
  }
  
  final void close(boolean paramBoolean)
  {
    if (this.mIsClosing) {
      return;
    }
    this.mIsClosing = true;
    Iterator localIterator = this.mPresenters.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        this.mIsClosing = false;
        return;
      }
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      MenuPresenter localMenuPresenter = (MenuPresenter)localWeakReference.get();
      if (localMenuPresenter == null) {
        this.mPresenters.remove(localWeakReference);
      } else {
        localMenuPresenter.onCloseMenu(this, paramBoolean);
      }
    }
  }
  
  public boolean collapseItemActionView(MenuItemImpl paramMenuItemImpl)
  {
    boolean bool2;
    if ((this.mPresenters.isEmpty()) || (this.mExpandedItem != paramMenuItemImpl))
    {
      bool2 = false;
      return bool2;
    }
    boolean bool1 = false;
    stopDispatchingItemsChanged();
    Iterator localIterator = this.mPresenters.iterator();
    label37:
    if (!localIterator.hasNext()) {}
    for (;;)
    {
      startDispatchingItemsChanged();
      bool2 = bool1;
      if (!bool1) {
        break;
      }
      this.mExpandedItem = null;
      return bool1;
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      MenuPresenter localMenuPresenter = (MenuPresenter)localWeakReference.get();
      if (localMenuPresenter == null)
      {
        this.mPresenters.remove(localWeakReference);
        break label37;
      }
      bool2 = localMenuPresenter.collapseItemActionView(this, paramMenuItemImpl);
      bool1 = bool2;
      if (!bool2) {
        break label37;
      }
      bool1 = bool2;
    }
  }
  
  boolean dispatchMenuItemSelected(MenuBuilder paramMenuBuilder, com.actionbarsherlock.view.MenuItem paramMenuItem)
  {
    return (this.mCallback != null) && (this.mCallback.onMenuItemSelected(paramMenuBuilder, paramMenuItem));
  }
  
  public boolean expandItemActionView(MenuItemImpl paramMenuItemImpl)
  {
    boolean bool2;
    if (this.mPresenters.isEmpty())
    {
      bool2 = false;
      return bool2;
    }
    boolean bool1 = false;
    stopDispatchingItemsChanged();
    Iterator localIterator = this.mPresenters.iterator();
    label29:
    if (!localIterator.hasNext()) {}
    for (;;)
    {
      startDispatchingItemsChanged();
      bool2 = bool1;
      if (!bool1) {
        break;
      }
      this.mExpandedItem = paramMenuItemImpl;
      return bool1;
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      MenuPresenter localMenuPresenter = (MenuPresenter)localWeakReference.get();
      if (localMenuPresenter == null)
      {
        this.mPresenters.remove(localWeakReference);
        break label29;
      }
      bool2 = localMenuPresenter.expandItemActionView(this, paramMenuItemImpl);
      bool1 = bool2;
      if (!bool2) {
        break label29;
      }
      bool1 = bool2;
    }
  }
  
  public int findGroupIndex(int paramInt)
  {
    return findGroupIndex(paramInt, 0);
  }
  
  public int findGroupIndex(int paramInt1, int paramInt2)
  {
    int j = size();
    int i = paramInt2;
    if (paramInt2 < 0) {
      i = 0;
    }
    paramInt2 = i;
    for (;;)
    {
      if (paramInt2 >= j) {
        i = -1;
      }
      do
      {
        return i;
        i = paramInt2;
      } while (((MenuItemImpl)this.mItems.get(paramInt2)).getGroupId() == paramInt1);
      paramInt2 += 1;
    }
  }
  
  public com.actionbarsherlock.view.MenuItem findItem(int paramInt)
  {
    int j = size();
    int i = 0;
    for (;;)
    {
      Object localObject;
      if (i >= j) {
        localObject = null;
      }
      MenuItemImpl localMenuItemImpl;
      do
      {
        return (com.actionbarsherlock.view.MenuItem)localObject;
        localMenuItemImpl = (MenuItemImpl)this.mItems.get(i);
        localObject = localMenuItemImpl;
      } while (localMenuItemImpl.getItemId() == paramInt);
      if (localMenuItemImpl.hasSubMenu())
      {
        localObject = localMenuItemImpl.getSubMenu().findItem(paramInt);
        if (localObject != null) {
          return (com.actionbarsherlock.view.MenuItem)localObject;
        }
      }
      i += 1;
    }
  }
  
  public int findItemIndex(int paramInt)
  {
    int k = size();
    int i = 0;
    for (;;)
    {
      int j;
      if (i >= k) {
        j = -1;
      }
      do
      {
        return j;
        j = i;
      } while (((MenuItemImpl)this.mItems.get(i)).getItemId() == paramInt);
      i += 1;
    }
  }
  
  MenuItemImpl findItemWithShortcutForKey(int paramInt, KeyEvent paramKeyEvent)
  {
    ArrayList localArrayList = this.mTempShortcutItemList;
    localArrayList.clear();
    findItemsWithShortcutForKey(localArrayList, paramInt, paramKeyEvent);
    if (localArrayList.isEmpty()) {}
    for (;;)
    {
      return null;
      int k = paramKeyEvent.getMetaState();
      KeyCharacterMap.KeyData localKeyData = new KeyCharacterMap.KeyData();
      paramKeyEvent.getKeyData(localKeyData);
      int m = localArrayList.size();
      if (m == 1) {
        return (MenuItemImpl)localArrayList.get(0);
      }
      boolean bool = isQwertyMode();
      int i = 0;
      while (i < m)
      {
        paramKeyEvent = (MenuItemImpl)localArrayList.get(i);
        if (bool) {}
        for (int j = paramKeyEvent.getAlphabeticShortcut(); ((j == localKeyData.meta[0]) && ((k & 0x2) == 0)) || ((j == localKeyData.meta[2]) && ((k & 0x2) != 0)) || ((bool) && (j == 8) && (paramInt == 67)); j = paramKeyEvent.getNumericShortcut()) {
          return paramKeyEvent;
        }
        i += 1;
      }
    }
  }
  
  void findItemsWithShortcutForKey(List<MenuItemImpl> paramList, int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = isQwertyMode();
    int k = paramKeyEvent.getMetaState();
    KeyCharacterMap.KeyData localKeyData = new KeyCharacterMap.KeyData();
    if ((!paramKeyEvent.getKeyData(localKeyData)) && (paramInt != 67)) {
      return;
    }
    int m = this.mItems.size();
    int i = 0;
    label49:
    MenuItemImpl localMenuItemImpl;
    if (i < m)
    {
      localMenuItemImpl = (MenuItemImpl)this.mItems.get(i);
      if (localMenuItemImpl.hasSubMenu()) {
        ((MenuBuilder)localMenuItemImpl.getSubMenu()).findItemsWithShortcutForKey(paramList, paramInt, paramKeyEvent);
      }
      if (!bool) {
        break label184;
      }
    }
    label184:
    for (int j = localMenuItemImpl.getAlphabeticShortcut();; j = localMenuItemImpl.getNumericShortcut())
    {
      if (((k & 0x5) == 0) && (j != 0) && ((j == localKeyData.meta[0]) || (j == localKeyData.meta[2]) || ((bool) && (j == 8) && (paramInt == 67))) && (localMenuItemImpl.isEnabled())) {
        paramList.add(localMenuItemImpl);
      }
      i += 1;
      break label49;
      break;
    }
  }
  
  public void flagActionItems()
  {
    if (!this.mIsActionItemsStale) {
      return;
    }
    int i = 0;
    Object localObject1 = this.mPresenters.iterator();
    if (!((Iterator)localObject1).hasNext())
    {
      if (i == 0) {
        break label168;
      }
      this.mActionItems.clear();
      this.mNonActionItems.clear();
      localObject1 = getVisibleItems();
      int k = ((ArrayList)localObject1).size();
      i = 0;
      if (i < k) {
        break label120;
      }
    }
    for (;;)
    {
      this.mIsActionItemsStale = false;
      return;
      Object localObject2 = (WeakReference)((Iterator)localObject1).next();
      MenuPresenter localMenuPresenter = (MenuPresenter)((WeakReference)localObject2).get();
      if (localMenuPresenter == null)
      {
        this.mPresenters.remove(localObject2);
        break;
      }
      i |= localMenuPresenter.flagActionItems();
      break;
      label120:
      localObject2 = (MenuItemImpl)((ArrayList)localObject1).get(i);
      if (((MenuItemImpl)localObject2).isActionButton()) {
        this.mActionItems.add(localObject2);
      }
      for (;;)
      {
        int j;
        i += 1;
        break;
        this.mNonActionItems.add(localObject2);
      }
      label168:
      this.mActionItems.clear();
      this.mNonActionItems.clear();
      this.mNonActionItems.addAll(getVisibleItems());
    }
  }
  
  ArrayList<MenuItemImpl> getActionItems()
  {
    flagActionItems();
    return this.mActionItems;
  }
  
  protected String getActionViewStatesKey()
  {
    return "android:menu:actionviewstates";
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  public MenuItemImpl getExpandedItem()
  {
    return this.mExpandedItem;
  }
  
  public Drawable getHeaderIcon()
  {
    return this.mHeaderIcon;
  }
  
  public CharSequence getHeaderTitle()
  {
    return this.mHeaderTitle;
  }
  
  public View getHeaderView()
  {
    return this.mHeaderView;
  }
  
  public com.actionbarsherlock.view.MenuItem getItem(int paramInt)
  {
    return (com.actionbarsherlock.view.MenuItem)this.mItems.get(paramInt);
  }
  
  ArrayList<MenuItemImpl> getNonActionItems()
  {
    flagActionItems();
    return this.mNonActionItems;
  }
  
  boolean getOptionalIconsVisible()
  {
    return this.mOptionalIconsVisible;
  }
  
  Resources getResources()
  {
    return this.mResources;
  }
  
  public MenuBuilder getRootMenu()
  {
    return this;
  }
  
  ArrayList<MenuItemImpl> getVisibleItems()
  {
    if (!this.mIsVisibleItemsStale) {
      return this.mVisibleItems;
    }
    this.mVisibleItems.clear();
    int j = this.mItems.size();
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        this.mIsVisibleItemsStale = false;
        this.mIsActionItemsStale = true;
        return this.mVisibleItems;
      }
      MenuItemImpl localMenuItemImpl = (MenuItemImpl)this.mItems.get(i);
      if (localMenuItemImpl.isVisible()) {
        this.mVisibleItems.add(localMenuItemImpl);
      }
      i += 1;
    }
  }
  
  public boolean hasVisibleItems()
  {
    int j = size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (((MenuItemImpl)this.mItems.get(i)).isVisible()) {
        return true;
      }
      i += 1;
    }
  }
  
  boolean isQwertyMode()
  {
    return this.mQwertyMode;
  }
  
  public boolean isShortcutKey(int paramInt, KeyEvent paramKeyEvent)
  {
    return findItemWithShortcutForKey(paramInt, paramKeyEvent) != null;
  }
  
  public boolean isShortcutsVisible()
  {
    return this.mShortcutsVisible;
  }
  
  void onItemActionRequestChanged(MenuItemImpl paramMenuItemImpl)
  {
    this.mIsActionItemsStale = true;
    onItemsChanged(true);
  }
  
  void onItemVisibleChanged(MenuItemImpl paramMenuItemImpl)
  {
    this.mIsVisibleItemsStale = true;
    onItemsChanged(true);
  }
  
  void onItemsChanged(boolean paramBoolean)
  {
    if (!this.mPreventDispatchingItemsChanged)
    {
      if (paramBoolean)
      {
        this.mIsVisibleItemsStale = true;
        this.mIsActionItemsStale = true;
      }
      dispatchPresenterUpdate(paramBoolean);
      return;
    }
    this.mItemsChangedWhileDispatchPrevented = true;
  }
  
  public boolean performIdentifierAction(int paramInt1, int paramInt2)
  {
    return performItemAction(findItem(paramInt1), paramInt2);
  }
  
  public boolean performItemAction(com.actionbarsherlock.view.MenuItem paramMenuItem, int paramInt)
  {
    Object localObject = (MenuItemImpl)paramMenuItem;
    boolean bool1;
    if ((localObject == null) || (!((MenuItemImpl)localObject).isEnabled())) {
      bool1 = false;
    }
    boolean bool2;
    do
    {
      do
      {
        do
        {
          return bool1;
          bool2 = ((MenuItemImpl)localObject).invoke();
          if (!((MenuItemImpl)localObject).hasCollapsibleActionView()) {
            break;
          }
          bool2 |= ((MenuItemImpl)localObject).expandActionView();
          bool1 = bool2;
        } while (!bool2);
        close(true);
        return bool2;
        if (!paramMenuItem.hasSubMenu()) {
          break;
        }
        close(false);
        localObject = (SubMenuBuilder)paramMenuItem.getSubMenu();
        paramMenuItem = paramMenuItem.getActionProvider();
        if ((paramMenuItem != null) && (paramMenuItem.hasSubMenu())) {
          paramMenuItem.onPrepareSubMenu((com.actionbarsherlock.view.SubMenu)localObject);
        }
        bool2 |= dispatchSubMenuSelected((SubMenuBuilder)localObject);
        bool1 = bool2;
      } while (bool2);
      close(true);
      return bool2;
      bool1 = bool2;
    } while ((paramInt & 0x1) != 0);
    close(true);
    return bool2;
  }
  
  public boolean performShortcut(int paramInt1, KeyEvent paramKeyEvent, int paramInt2)
  {
    paramKeyEvent = findItemWithShortcutForKey(paramInt1, paramKeyEvent);
    boolean bool = false;
    if (paramKeyEvent != null) {
      bool = performItemAction(paramKeyEvent, paramInt2);
    }
    if ((paramInt2 & 0x2) != 0) {
      close(true);
    }
    return bool;
  }
  
  public void removeGroup(int paramInt)
  {
    int j = findGroupIndex(paramInt);
    int k;
    int i;
    if (j >= 0)
    {
      k = this.mItems.size();
      i = 0;
    }
    for (;;)
    {
      if ((i >= k - j) || (((MenuItemImpl)this.mItems.get(j)).getGroupId() != paramInt))
      {
        onItemsChanged(true);
        return;
      }
      removeItemAtInt(j, false);
      i += 1;
    }
  }
  
  public void removeItem(int paramInt)
  {
    removeItemAtInt(findItemIndex(paramInt), true);
  }
  
  public void removeItemAt(int paramInt)
  {
    removeItemAtInt(paramInt, true);
  }
  
  public void removeMenuPresenter(MenuPresenter paramMenuPresenter)
  {
    Iterator localIterator = this.mPresenters.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      MenuPresenter localMenuPresenter = (MenuPresenter)localWeakReference.get();
      if ((localMenuPresenter == null) || (localMenuPresenter == paramMenuPresenter)) {
        this.mPresenters.remove(localWeakReference);
      }
    }
  }
  
  public void restoreActionViewStates(Bundle paramBundle)
  {
    if (paramBundle == null) {}
    SparseArray localSparseArray;
    do
    {
      return;
      localSparseArray = paramBundle.getSparseParcelableArray(getActionViewStatesKey());
    } while ((Build.VERSION.SDK_INT < 11) && (localSparseArray == null));
    int j = size();
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        i = paramBundle.getInt("android:menu:expandedactionview");
        if (i <= 0) {
          break;
        }
        paramBundle = findItem(i);
        if (paramBundle == null) {
          break;
        }
        paramBundle.expandActionView();
        return;
      }
      com.actionbarsherlock.view.MenuItem localMenuItem = getItem(i);
      View localView = localMenuItem.getActionView();
      if ((localView != null) && (localView.getId() != -1)) {
        localView.restoreHierarchyState(localSparseArray);
      }
      if (localMenuItem.hasSubMenu()) {
        ((SubMenuBuilder)localMenuItem.getSubMenu()).restoreActionViewStates(paramBundle);
      }
      i += 1;
    }
  }
  
  public void restorePresenterStates(Bundle paramBundle)
  {
    dispatchRestoreInstanceState(paramBundle);
  }
  
  public void saveActionViewStates(Bundle paramBundle)
  {
    Object localObject1 = null;
    int j = size();
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        if (localObject1 != null) {
          paramBundle.putSparseParcelableArray(getActionViewStatesKey(), (SparseArray)localObject1);
        }
        return;
      }
      com.actionbarsherlock.view.MenuItem localMenuItem = getItem(i);
      View localView = localMenuItem.getActionView();
      Object localObject3 = localObject1;
      if (localView != null)
      {
        localObject3 = localObject1;
        if (localView.getId() != -1)
        {
          Object localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new SparseArray();
          }
          localView.saveHierarchyState((SparseArray)localObject2);
          localObject3 = localObject2;
          if (localMenuItem.isActionViewExpanded())
          {
            paramBundle.putInt("android:menu:expandedactionview", localMenuItem.getItemId());
            localObject3 = localObject2;
          }
        }
      }
      if (localMenuItem.hasSubMenu()) {
        ((SubMenuBuilder)localMenuItem.getSubMenu()).saveActionViewStates(paramBundle);
      }
      i += 1;
      localObject1 = localObject3;
    }
  }
  
  public void savePresenterStates(Bundle paramBundle)
  {
    dispatchSaveInstanceState(paramBundle);
  }
  
  public void setCallback(Callback paramCallback)
  {
    this.mCallback = paramCallback;
  }
  
  public void setCurrentMenuInfo(ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    this.mCurrentMenuInfo = paramContextMenuInfo;
  }
  
  public MenuBuilder setDefaultShowAsAction(int paramInt)
  {
    this.mDefaultShowAsAction = paramInt;
    return this;
  }
  
  void setExclusiveItemChecked(com.actionbarsherlock.view.MenuItem paramMenuItem)
  {
    int j = paramMenuItem.getGroupId();
    int k = this.mItems.size();
    int i = 0;
    if (i >= k) {
      return;
    }
    MenuItemImpl localMenuItemImpl = (MenuItemImpl)this.mItems.get(i);
    if ((localMenuItemImpl.getGroupId() != j) || (!localMenuItemImpl.isExclusiveCheckable())) {}
    while (!localMenuItemImpl.isCheckable())
    {
      i += 1;
      break;
    }
    if (localMenuItemImpl == paramMenuItem) {}
    for (boolean bool = true;; bool = false)
    {
      localMenuItemImpl.setCheckedInt(bool);
      break;
    }
  }
  
  public void setGroupCheckable(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    int j = this.mItems.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      MenuItemImpl localMenuItemImpl = (MenuItemImpl)this.mItems.get(i);
      if (localMenuItemImpl.getGroupId() == paramInt)
      {
        localMenuItemImpl.setExclusiveCheckable(paramBoolean2);
        localMenuItemImpl.setCheckable(paramBoolean1);
      }
      i += 1;
    }
  }
  
  public void setGroupEnabled(int paramInt, boolean paramBoolean)
  {
    int j = this.mItems.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      MenuItemImpl localMenuItemImpl = (MenuItemImpl)this.mItems.get(i);
      if (localMenuItemImpl.getGroupId() == paramInt) {
        localMenuItemImpl.setEnabled(paramBoolean);
      }
      i += 1;
    }
  }
  
  public void setGroupVisible(int paramInt, boolean paramBoolean)
  {
    int m = this.mItems.size();
    int j = 0;
    int i = 0;
    for (;;)
    {
      if (i >= m)
      {
        if (j != 0) {
          onItemsChanged(true);
        }
        return;
      }
      MenuItemImpl localMenuItemImpl = (MenuItemImpl)this.mItems.get(i);
      int k = j;
      if (localMenuItemImpl.getGroupId() == paramInt)
      {
        k = j;
        if (localMenuItemImpl.setVisibleInt(paramBoolean)) {
          k = 1;
        }
      }
      i += 1;
      j = k;
    }
  }
  
  protected MenuBuilder setHeaderIconInt(int paramInt)
  {
    setHeaderInternal(0, null, paramInt, null, null);
    return this;
  }
  
  protected MenuBuilder setHeaderIconInt(Drawable paramDrawable)
  {
    setHeaderInternal(0, null, 0, paramDrawable, null);
    return this;
  }
  
  protected MenuBuilder setHeaderTitleInt(int paramInt)
  {
    setHeaderInternal(paramInt, null, 0, null, null);
    return this;
  }
  
  protected MenuBuilder setHeaderTitleInt(CharSequence paramCharSequence)
  {
    setHeaderInternal(0, paramCharSequence, 0, null, null);
    return this;
  }
  
  protected MenuBuilder setHeaderViewInt(View paramView)
  {
    setHeaderInternal(0, null, 0, null, paramView);
    return this;
  }
  
  void setOptionalIconsVisible(boolean paramBoolean)
  {
    this.mOptionalIconsVisible = paramBoolean;
  }
  
  public void setQwertyMode(boolean paramBoolean)
  {
    this.mQwertyMode = paramBoolean;
    onItemsChanged(false);
  }
  
  public void setShortcutsVisible(boolean paramBoolean)
  {
    if (this.mShortcutsVisible == paramBoolean) {
      return;
    }
    setShortcutsVisibleInner(paramBoolean);
    onItemsChanged(false);
  }
  
  public int size()
  {
    return this.mItems.size();
  }
  
  public void startDispatchingItemsChanged()
  {
    this.mPreventDispatchingItemsChanged = false;
    if (this.mItemsChangedWhileDispatchPrevented)
    {
      this.mItemsChangedWhileDispatchPrevented = false;
      onItemsChanged(true);
    }
  }
  
  public void stopDispatchingItemsChanged()
  {
    if (!this.mPreventDispatchingItemsChanged)
    {
      this.mPreventDispatchingItemsChanged = true;
      this.mItemsChangedWhileDispatchPrevented = false;
    }
  }
  
  public static abstract interface Callback
  {
    public abstract boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, com.actionbarsherlock.view.MenuItem paramMenuItem);
    
    public abstract void onMenuModeChange(MenuBuilder paramMenuBuilder);
  }
  
  public static abstract interface ItemInvoker
  {
    public abstract boolean invokeItem(MenuItemImpl paramMenuItemImpl);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\view\menu\MenuBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */