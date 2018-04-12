package android.support.v4.app;

import android.view.View;
import com.actionbarsherlock.ActionBarSherlock.OnCreatePanelMenuListener;
import com.actionbarsherlock.ActionBarSherlock.OnMenuItemSelectedListener;
import com.actionbarsherlock.ActionBarSherlock.OnPreparePanelListener;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import java.util.ArrayList;

public abstract class Watson
  extends FragmentActivity
  implements ActionBarSherlock.OnCreatePanelMenuListener, ActionBarSherlock.OnPreparePanelListener, ActionBarSherlock.OnMenuItemSelectedListener
{
  private static final String TAG = "Watson";
  private ArrayList<Fragment> mCreatedMenus;
  
  public abstract MenuInflater getSupportMenuInflater();
  
  public abstract boolean onCreateOptionsMenu(Menu paramMenu);
  
  public boolean onCreatePanelMenu(int paramInt, Menu paramMenu)
  {
    if (paramInt == 0)
    {
      int k = onCreateOptionsMenu(paramMenu);
      MenuInflater localMenuInflater = getSupportMenuInflater();
      paramInt = 0;
      int i = 0;
      Object localObject1 = null;
      Object localObject2 = null;
      int j;
      if (this.mFragments.mAdded != null)
      {
        j = 0;
        paramInt = i;
        localObject1 = localObject2;
        i = j;
        if (i < this.mFragments.mAdded.size()) {}
      }
      else if (this.mCreatedMenus != null)
      {
        i = 0;
      }
      for (;;)
      {
        if (i >= this.mCreatedMenus.size())
        {
          this.mCreatedMenus = ((ArrayList)localObject1);
          return k | paramInt;
          Fragment localFragment = (Fragment)this.mFragments.mAdded.get(i);
          localObject2 = localObject1;
          j = paramInt;
          if (localFragment != null)
          {
            localObject2 = localObject1;
            j = paramInt;
            if (!localFragment.mHidden)
            {
              localObject2 = localObject1;
              j = paramInt;
              if (localFragment.mHasMenu)
              {
                localObject2 = localObject1;
                j = paramInt;
                if (localFragment.mMenuVisible)
                {
                  localObject2 = localObject1;
                  j = paramInt;
                  if ((localFragment instanceof OnCreateOptionsMenuListener))
                  {
                    j = 1;
                    ((OnCreateOptionsMenuListener)localFragment).onCreateOptionsMenu(paramMenu, localMenuInflater);
                    localObject2 = localObject1;
                    if (localObject1 == null) {
                      localObject2 = new ArrayList();
                    }
                    ((ArrayList)localObject2).add(localFragment);
                  }
                }
              }
            }
          }
          i += 1;
          localObject1 = localObject2;
          paramInt = j;
          break;
        }
        paramMenu = (Fragment)this.mCreatedMenus.get(i);
        if ((localObject1 == null) || (!((ArrayList)localObject1).contains(paramMenu))) {
          paramMenu.onDestroyOptionsMenu();
        }
        i += 1;
      }
    }
    return false;
  }
  
  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    if (paramInt == 0)
    {
      if (onOptionsItemSelected(paramMenuItem)) {
        return true;
      }
      if (this.mFragments.mAdded != null) {
        paramInt = 0;
      }
    }
    for (;;)
    {
      if (paramInt >= this.mFragments.mAdded.size()) {
        return false;
      }
      Fragment localFragment = (Fragment)this.mFragments.mAdded.get(paramInt);
      if ((localFragment != null) && (!localFragment.mHidden) && (localFragment.mHasMenu) && (localFragment.mMenuVisible) && ((localFragment instanceof OnOptionsItemSelectedListener)) && (((OnOptionsItemSelectedListener)localFragment).onOptionsItemSelected(paramMenuItem))) {
        break;
      }
      paramInt += 1;
    }
  }
  
  public abstract boolean onOptionsItemSelected(MenuItem paramMenuItem);
  
  public abstract boolean onPrepareOptionsMenu(Menu paramMenu);
  
  public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu)
  {
    if (paramInt == 0)
    {
      int k = onPrepareOptionsMenu(paramMenu);
      paramInt = 0;
      int i = 0;
      int j;
      if (this.mFragments.mAdded != null)
      {
        j = 0;
        paramInt = i;
        i = j;
      }
      for (;;)
      {
        if (i >= this.mFragments.mAdded.size()) {
          return (k | paramInt) & paramMenu.hasVisibleItems();
        }
        paramView = (Fragment)this.mFragments.mAdded.get(i);
        j = paramInt;
        if (paramView != null)
        {
          j = paramInt;
          if (!paramView.mHidden)
          {
            j = paramInt;
            if (paramView.mHasMenu)
            {
              j = paramInt;
              if (paramView.mMenuVisible)
              {
                j = paramInt;
                if ((paramView instanceof OnPrepareOptionsMenuListener))
                {
                  j = 1;
                  ((OnPrepareOptionsMenuListener)paramView).onPrepareOptionsMenu(paramMenu);
                }
              }
            }
          }
        }
        i += 1;
        paramInt = j;
      }
    }
    return false;
  }
  
  public static abstract interface OnCreateOptionsMenuListener
  {
    public abstract void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater);
  }
  
  public static abstract interface OnOptionsItemSelectedListener
  {
    public abstract boolean onOptionsItemSelected(MenuItem paramMenuItem);
  }
  
  public static abstract interface OnPrepareOptionsMenuListener
  {
    public abstract void onPrepareOptionsMenu(Menu paramMenu);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\android\support\v4\app\Watson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */