package com.actionbarsherlock.internal.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.view.KeyEvent;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import java.util.ArrayList;
import java.util.List;

public class ActionMenu
  implements Menu
{
  private Context mContext;
  private boolean mIsQwerty;
  private ArrayList<ActionMenuItem> mItems;
  
  public ActionMenu(Context paramContext)
  {
    this.mContext = paramContext;
    this.mItems = new ArrayList();
  }
  
  private int findItemIndex(int paramInt)
  {
    ArrayList localArrayList = this.mItems;
    int k = localArrayList.size();
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
      } while (((ActionMenuItem)localArrayList.get(i)).getItemId() == paramInt);
      i += 1;
    }
  }
  
  private ActionMenuItem findItemWithShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = this.mIsQwerty;
    ArrayList localArrayList = this.mItems;
    int k = localArrayList.size();
    int i = 0;
    if (i >= k) {
      paramKeyEvent = null;
    }
    label72:
    for (;;)
    {
      return paramKeyEvent;
      paramKeyEvent = (ActionMenuItem)localArrayList.get(i);
      if (bool) {}
      for (int j = paramKeyEvent.getAlphabeticShortcut();; j = paramKeyEvent.getNumericShortcut())
      {
        if (paramInt == j) {
          break label72;
        }
        i += 1;
        break;
      }
    }
  }
  
  public MenuItem add(int paramInt)
  {
    return add(0, 0, 0, paramInt);
  }
  
  public MenuItem add(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return add(paramInt1, paramInt2, paramInt3, this.mContext.getResources().getString(paramInt4));
  }
  
  public MenuItem add(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    paramCharSequence = new ActionMenuItem(getContext(), paramInt1, paramInt2, 0, paramInt3, paramCharSequence);
    this.mItems.add(paramInt3, paramCharSequence);
    return paramCharSequence;
  }
  
  public MenuItem add(CharSequence paramCharSequence)
  {
    return add(0, 0, 0, paramCharSequence);
  }
  
  public int addIntentOptions(int paramInt1, int paramInt2, int paramInt3, ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt4, MenuItem[] paramArrayOfMenuItem)
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
  
  public SubMenu addSubMenu(int paramInt)
  {
    return null;
  }
  
  public SubMenu addSubMenu(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return null;
  }
  
  public SubMenu addSubMenu(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    return null;
  }
  
  public SubMenu addSubMenu(CharSequence paramCharSequence)
  {
    return null;
  }
  
  public void clear()
  {
    this.mItems.clear();
  }
  
  public void close() {}
  
  public MenuItem findItem(int paramInt)
  {
    return (MenuItem)this.mItems.get(findItemIndex(paramInt));
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  public MenuItem getItem(int paramInt)
  {
    return (MenuItem)this.mItems.get(paramInt);
  }
  
  public boolean hasVisibleItems()
  {
    ArrayList localArrayList = this.mItems;
    int j = localArrayList.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (((ActionMenuItem)localArrayList.get(i)).isVisible()) {
        return true;
      }
      i += 1;
    }
  }
  
  public boolean isShortcutKey(int paramInt, KeyEvent paramKeyEvent)
  {
    return findItemWithShortcut(paramInt, paramKeyEvent) != null;
  }
  
  public boolean performIdentifierAction(int paramInt1, int paramInt2)
  {
    paramInt1 = findItemIndex(paramInt1);
    if (paramInt1 < 0) {
      return false;
    }
    return ((ActionMenuItem)this.mItems.get(paramInt1)).invoke();
  }
  
  public boolean performShortcut(int paramInt1, KeyEvent paramKeyEvent, int paramInt2)
  {
    paramKeyEvent = findItemWithShortcut(paramInt1, paramKeyEvent);
    if (paramKeyEvent == null) {
      return false;
    }
    return paramKeyEvent.invoke();
  }
  
  public void removeGroup(int paramInt)
  {
    ArrayList localArrayList = this.mItems;
    int i = localArrayList.size();
    int j = 0;
    for (;;)
    {
      if (j >= i) {
        return;
      }
      if (((ActionMenuItem)localArrayList.get(j)).getGroupId() == paramInt)
      {
        localArrayList.remove(j);
        i -= 1;
      }
      else
      {
        j += 1;
      }
    }
  }
  
  public void removeItem(int paramInt)
  {
    this.mItems.remove(findItemIndex(paramInt));
  }
  
  public void setGroupCheckable(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    ArrayList localArrayList = this.mItems;
    int j = localArrayList.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      ActionMenuItem localActionMenuItem = (ActionMenuItem)localArrayList.get(i);
      if (localActionMenuItem.getGroupId() == paramInt)
      {
        localActionMenuItem.setCheckable(paramBoolean1);
        localActionMenuItem.setExclusiveCheckable(paramBoolean2);
      }
      i += 1;
    }
  }
  
  public void setGroupEnabled(int paramInt, boolean paramBoolean)
  {
    ArrayList localArrayList = this.mItems;
    int j = localArrayList.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      ActionMenuItem localActionMenuItem = (ActionMenuItem)localArrayList.get(i);
      if (localActionMenuItem.getGroupId() == paramInt) {
        localActionMenuItem.setEnabled(paramBoolean);
      }
      i += 1;
    }
  }
  
  public void setGroupVisible(int paramInt, boolean paramBoolean)
  {
    ArrayList localArrayList = this.mItems;
    int j = localArrayList.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      ActionMenuItem localActionMenuItem = (ActionMenuItem)localArrayList.get(i);
      if (localActionMenuItem.getGroupId() == paramInt) {
        localActionMenuItem.setVisible(paramBoolean);
      }
      i += 1;
    }
  }
  
  public void setQwertyMode(boolean paramBoolean)
  {
    this.mIsQwerty = paramBoolean;
  }
  
  public int size()
  {
    return this.mItems.size();
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\view\menu\ActionMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */