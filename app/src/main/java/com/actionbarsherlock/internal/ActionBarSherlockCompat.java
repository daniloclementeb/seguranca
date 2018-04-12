package com.actionbarsherlock.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.ActionBarSherlock.Implementation;
import com.actionbarsherlock.ActionBarSherlock.OnActionModeFinishedListener;
import com.actionbarsherlock.ActionBarSherlock.OnActionModeStartedListener;
import com.actionbarsherlock.R.attr;
import com.actionbarsherlock.R.bool;
import com.actionbarsherlock.R.id;
import com.actionbarsherlock.R.layout;
import com.actionbarsherlock.R.styleable;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.internal.app.ActionBarImpl;
import com.actionbarsherlock.internal.view.StandaloneActionMode;
import com.actionbarsherlock.internal.view.menu.ActionMenuPresenter;
import com.actionbarsherlock.internal.view.menu.MenuBuilder;
import com.actionbarsherlock.internal.view.menu.MenuBuilder.Callback;
import com.actionbarsherlock.internal.view.menu.MenuItemImpl;
import com.actionbarsherlock.internal.view.menu.MenuPresenter.Callback;
import com.actionbarsherlock.internal.widget.ActionBarContainer;
import com.actionbarsherlock.internal.widget.ActionBarContextView;
import com.actionbarsherlock.internal.widget.ActionBarView;
import com.actionbarsherlock.internal.widget.IcsProgressBar;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.ActionMode.Callback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@ActionBarSherlock.Implementation(api=7)
public class ActionBarSherlockCompat
  extends ActionBarSherlock
  implements MenuBuilder.Callback, com.actionbarsherlock.view.Window.Callback, MenuPresenter.Callback, MenuItem.OnMenuItemClickListener
{
  protected static final int DEFAULT_FEATURES = 0;
  private static final String PANELS_TAG = "sherlock:Panels";
  private ActionBarImpl aActionBar;
  private ActionMode mActionMode;
  private ActionBarContextView mActionModeView;
  private IcsProgressBar mCircularProgressBar;
  private boolean mClosingActionMenu;
  private ViewGroup mContentParent;
  private ViewGroup mDecor;
  private int mFeatures = 0;
  private IcsProgressBar mHorizontalProgressBar;
  private boolean mIsDestroyed = false;
  private boolean mIsTitleReady = false;
  private MenuBuilder mMenu;
  private Bundle mMenuFrozenActionViewState;
  private boolean mMenuIsPrepared;
  private boolean mMenuRefreshContent;
  protected HashMap<android.view.MenuItem, MenuItemImpl> mNativeItemMap;
  private boolean mReserveOverflow;
  private boolean mReserveOverflowSet = false;
  private int mUiOptions = 0;
  private ActionBarView wActionBar;
  
  public ActionBarSherlockCompat(Activity paramActivity, int paramInt)
  {
    super(paramActivity, paramInt);
  }
  
  public static String cleanActivityName(String paramString1, String paramString2)
  {
    String str;
    if (paramString2.charAt(0) == '.') {
      str = paramString1 + paramString2;
    }
    do
    {
      return str;
      str = paramString2;
    } while (paramString2.indexOf('.', 1) != -1);
    return paramString1 + "." + paramString2;
  }
  
  private ViewGroup generateLayout()
  {
    Object localObject = this.mActivity.getTheme().obtainStyledAttributes(R.styleable.SherlockTheme);
    if (!((TypedArray)localObject).hasValue(R.styleable.SherlockTheme_windowActionBar)) {
      throw new IllegalStateException("You must use Theme.Sherlock, Theme.Sherlock.Light, Theme.Sherlock.Light.DarkActionBar, or a derivative.");
    }
    int i;
    if (((TypedArray)localObject).getBoolean(R.styleable.SherlockTheme_windowNoTitle, false))
    {
      requestFeature(1);
      if (((TypedArray)localObject).getBoolean(R.styleable.SherlockTheme_windowActionBarOverlay, false)) {
        requestFeature(9);
      }
      if (((TypedArray)localObject).getBoolean(R.styleable.SherlockTheme_windowActionModeOverlay, false)) {
        requestFeature(10);
      }
      ((TypedArray)localObject).recycle();
      if (hasFeature(1)) {
        break label198;
      }
      if (!hasFeature(9)) {
        break label191;
      }
      i = R.layout.abs__screen_action_bar_overlay;
    }
    for (;;)
    {
      localObject = this.mActivity.getLayoutInflater().inflate(i, null);
      this.mDecor.addView((View)localObject, new ViewGroup.LayoutParams(-1, -1));
      localObject = (ViewGroup)this.mDecor.findViewById(R.id.abs__content);
      if (localObject != null) {
        break label229;
      }
      throw new RuntimeException("Couldn't find content container view");
      if (!((TypedArray)localObject).getBoolean(R.styleable.SherlockTheme_windowActionBar, false)) {
        break;
      }
      requestFeature(8);
      break;
      label191:
      i = R.layout.abs__screen_action_bar;
      continue;
      label198:
      if ((hasFeature(10)) && (!hasFeature(1))) {
        i = R.layout.abs__screen_simple_overlay_action_mode;
      } else {
        i = R.layout.abs__screen_simple;
      }
    }
    label229:
    this.mDecor.setId(-1);
    ((ViewGroup)localObject).setId(16908290);
    if (hasFeature(5))
    {
      IcsProgressBar localIcsProgressBar = getCircularProgressBar(false);
      if (localIcsProgressBar != null) {
        localIcsProgressBar.setIndeterminate(true);
      }
    }
    return (ViewGroup)localObject;
  }
  
  private IcsProgressBar getCircularProgressBar(boolean paramBoolean)
  {
    if (this.mCircularProgressBar != null) {
      return this.mCircularProgressBar;
    }
    if ((this.mContentParent == null) && (paramBoolean)) {
      installDecor();
    }
    this.mCircularProgressBar = ((IcsProgressBar)this.mDecor.findViewById(R.id.abs__progress_circular));
    if (this.mCircularProgressBar != null) {
      this.mCircularProgressBar.setVisibility(4);
    }
    return this.mCircularProgressBar;
  }
  
  private int getFeatures()
  {
    return this.mFeatures;
  }
  
  private IcsProgressBar getHorizontalProgressBar(boolean paramBoolean)
  {
    if (this.mHorizontalProgressBar != null) {
      return this.mHorizontalProgressBar;
    }
    if ((this.mContentParent == null) && (paramBoolean)) {
      installDecor();
    }
    this.mHorizontalProgressBar = ((IcsProgressBar)this.mDecor.findViewById(R.id.abs__progress_horizontal));
    if (this.mHorizontalProgressBar != null) {
      this.mHorizontalProgressBar.setVisibility(4);
    }
    return this.mHorizontalProgressBar;
  }
  
  private void hideProgressBars(IcsProgressBar paramIcsProgressBar1, IcsProgressBar paramIcsProgressBar2)
  {
    int i = this.mFeatures;
    Animation localAnimation = AnimationUtils.loadAnimation(this.mActivity, 17432577);
    localAnimation.setDuration(1000L);
    if (((i & 0x20) != 0) && (paramIcsProgressBar2.getVisibility() == 0))
    {
      paramIcsProgressBar2.startAnimation(localAnimation);
      paramIcsProgressBar2.setVisibility(4);
    }
    if (((i & 0x4) != 0) && (paramIcsProgressBar1.getVisibility() == 0))
    {
      paramIcsProgressBar1.startAnimation(localAnimation);
      paramIcsProgressBar1.setVisibility(4);
    }
  }
  
  private void initActionBar()
  {
    if (this.mDecor == null) {
      installDecor();
    }
    if ((this.aActionBar != null) || (!hasFeature(8)) || (hasFeature(1)) || (this.mActivity.isChild())) {}
    do
    {
      return;
      this.aActionBar = new ActionBarImpl(this.mActivity, this.mFeatures);
    } while (this.mIsDelegate);
    this.wActionBar.setWindowTitle(this.mActivity.getTitle());
  }
  
  private boolean initializePanelMenu()
  {
    Activity localActivity = this.mActivity;
    Object localObject = localActivity;
    if (this.wActionBar != null)
    {
      localObject = new TypedValue();
      localActivity.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, (TypedValue)localObject, true);
      int i = ((TypedValue)localObject).resourceId;
      localObject = localActivity;
      if (i != 0) {
        localObject = new ContextThemeWrapper(localActivity, i);
      }
    }
    this.mMenu = new MenuBuilder((Context)localObject);
    this.mMenu.setCallback(this);
    return true;
  }
  
  private void installDecor()
  {
    boolean bool1 = true;
    if (this.mDecor == null) {
      this.mDecor = ((ViewGroup)this.mActivity.getWindow().getDecorView().findViewById(16908290));
    }
    Object localObject;
    int i;
    label98:
    label220:
    boolean bool2;
    if (this.mContentParent == null)
    {
      localObject = null;
      if (this.mDecor.getChildCount() > 0)
      {
        localObject = new ArrayList(1);
        i = 0;
        int j = this.mDecor.getChildCount();
        if (i < j) {
          break label342;
        }
      }
      this.mContentParent = generateLayout();
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        if (((Iterator)localObject).hasNext()) {
          break label378;
        }
      }
      this.wActionBar = ((ActionBarView)this.mDecor.findViewById(R.id.abs__action_bar));
      if (this.wActionBar != null)
      {
        this.wActionBar.setWindowCallback(this);
        if (this.wActionBar.getTitle() == null) {
          this.wActionBar.setWindowTitle(this.mActivity.getTitle());
        }
        if (hasFeature(2)) {
          this.wActionBar.initProgress();
        }
        if (hasFeature(5)) {
          this.wActionBar.initIndeterminateProgress();
        }
        i = loadUiOptionsFromManifest(this.mActivity);
        if (i != 0) {
          this.mUiOptions = i;
        }
        if ((this.mUiOptions & 0x1) == 0) {
          break label402;
        }
        if (!bool1) {
          break label407;
        }
        bool2 = ResourcesCompat.getResources_getBoolean(this.mActivity, R.bool.abs__split_action_bar_is_narrow);
        label236:
        localObject = (ActionBarContainer)this.mDecor.findViewById(R.id.abs__split_action_bar);
        if (localObject == null) {
          break label432;
        }
        this.wActionBar.setSplitView((ActionBarContainer)localObject);
        this.wActionBar.setSplitActionBar(bool2);
        this.wActionBar.setSplitWhenNarrow(bool1);
        this.mActionModeView = ((ActionBarContextView)this.mDecor.findViewById(R.id.abs__action_context_bar));
        this.mActionModeView.setSplitView((ActionBarContainer)localObject);
        this.mActionModeView.setSplitActionBar(bool2);
        this.mActionModeView.setSplitWhenNarrow(bool1);
      }
    }
    for (;;)
    {
      this.mDecor.post(new Runnable()
      {
        public void run()
        {
          if ((!ActionBarSherlockCompat.this.mIsDestroyed) && (!ActionBarSherlockCompat.this.mActivity.isFinishing()) && (ActionBarSherlockCompat.this.mMenu == null)) {
            ActionBarSherlockCompat.this.dispatchInvalidateOptionsMenu();
          }
        }
      });
      return;
      label342:
      View localView = this.mDecor.getChildAt(0);
      this.mDecor.removeView(localView);
      ((List)localObject).add(localView);
      i += 1;
      break;
      label378:
      localView = (View)((Iterator)localObject).next();
      this.mContentParent.addView(localView);
      break label98;
      label402:
      bool1 = false;
      break label220;
      label407:
      bool2 = this.mActivity.getTheme().obtainStyledAttributes(R.styleable.SherlockTheme).getBoolean(R.styleable.SherlockTheme_windowSplitActionBar, false);
      break label236;
      label432:
      if (bool2) {
        Log.e("ActionBarSherlock", "Requested split action bar with incompatible window decor! Ignoring request.");
      }
    }
  }
  
  private boolean isReservingOverflow()
  {
    if (!this.mReserveOverflowSet)
    {
      this.mReserveOverflow = ActionMenuPresenter.reserveOverflow(this.mActivity);
      this.mReserveOverflowSet = true;
    }
    return this.mReserveOverflow;
  }
  
  private static int loadUiOptionsFromManifest(Activity paramActivity)
  {
    int j = 0;
    int k = 0;
    int i = k;
    try
    {
      str1 = paramActivity.getClass().getName();
      i = k;
      str2 = paramActivity.getApplicationInfo().packageName;
      i = k;
      localXmlResourceParser = paramActivity.createPackageContext(str2, 0).getAssets().openXmlResourceParser("AndroidManifest.xml");
      i = k;
      k = localXmlResourceParser.getEventType();
      i = k;
    }
    catch (Exception paramActivity)
    {
      String str1;
      String str2;
      XmlResourceParser localXmlResourceParser;
      paramActivity.printStackTrace();
      return i;
    }
    k = j;
    if (i == 2)
    {
      i = j;
      paramActivity = localXmlResourceParser.getName();
      i = j;
      if (!"application".equals(paramActivity)) {
        break label156;
      }
      i = j;
      k = localXmlResourceParser.getAttributeCount() - 1;
      break label340;
    }
    label122:
    label156:
    do
    {
      for (;;)
      {
        i = k;
        j = localXmlResourceParser.nextToken();
        i = j;
        j = k;
        break;
        i = j;
        if (!"uiOptions".equals(localXmlResourceParser.getAttributeName(k))) {
          break label349;
        }
        i = j;
        k = localXmlResourceParser.getAttributeIntValue(k, 0);
      }
      k = j;
      i = j;
    } while (!"activity".equals(paramActivity));
    Object localObject1 = null;
    paramActivity = null;
    int n = 0;
    i = j;
    k = localXmlResourceParser.getAttributeCount() - 1;
    label340:
    label349:
    label354:
    label369:
    label384:
    for (;;)
    {
      i = j;
      String str3 = localXmlResourceParser.getAttributeName(k);
      i = j;
      Object localObject2;
      int m;
      if ("uiOptions".equals(str3))
      {
        i = j;
        localObject2 = Integer.valueOf(localXmlResourceParser.getAttributeIntValue(k, 0));
        m = n;
      }
      for (;;)
      {
        i = j;
        if (localObject2 == null) {
          break label369;
        }
        i = j;
        if (paramActivity == null) {
          break label369;
        }
        i = j;
        j = ((Integer)localObject2).intValue();
        i = j;
        break label369;
        localObject2 = localObject1;
        m = n;
        i = j;
        if ("name".equals(str3))
        {
          i = j;
          paramActivity = cleanActivityName(str2, localXmlResourceParser.getAttributeValue(k));
          i = j;
          boolean bool = str1.equals(paramActivity);
          if (!bool) {
            break;
          }
          m = 1;
          localObject2 = localObject1;
        }
      }
      if (i != 1) {
        break;
      }
      return j;
      for (;;)
      {
        if (k >= 0) {
          break label354;
        }
        k = j;
        break;
        k -= 1;
      }
      break label122;
      for (;;)
      {
        if (k >= 0) {
          break label384;
        }
        k = j;
        if (n == 0) {
          break;
        }
        return j;
        k -= 1;
        localObject1 = localObject2;
        n = m;
        j = i;
      }
    }
  }
  
  private void onIntChanged(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 2) || (paramInt1 == 5)) {
      updateProgressBars(paramInt2);
    }
  }
  
  private boolean preparePanel()
  {
    boolean bool3 = false;
    boolean bool2 = false;
    if (this.mMenuIsPrepared) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          if ((this.mMenu != null) && (!this.mMenuRefreshContent)) {
            break label121;
          }
          if (this.mMenu != null) {
            break;
          }
          bool1 = bool2;
        } while (!initializePanelMenu());
        bool1 = bool2;
      } while (this.mMenu == null);
      if (this.wActionBar != null) {
        this.wActionBar.setMenu(this.mMenu, this);
      }
      this.mMenu.stopDispatchingItemsChanged();
      if (callbackCreateOptionsMenu(this.mMenu)) {
        break;
      }
      this.mMenu = null;
      bool1 = bool2;
    } while (this.wActionBar == null);
    this.wActionBar.setMenu(null, this);
    return false;
    this.mMenuRefreshContent = false;
    label121:
    this.mMenu.stopDispatchingItemsChanged();
    if (this.mMenuFrozenActionViewState != null)
    {
      this.mMenu.restoreActionViewStates(this.mMenuFrozenActionViewState);
      this.mMenuFrozenActionViewState = null;
    }
    if (!callbackPrepareOptionsMenu(this.mMenu))
    {
      if (this.wActionBar != null) {
        this.wActionBar.setMenu(null, this);
      }
      this.mMenu.startDispatchingItemsChanged();
      return false;
    }
    KeyCharacterMap localKeyCharacterMap = KeyCharacterMap.load(-1);
    MenuBuilder localMenuBuilder = this.mMenu;
    boolean bool1 = bool3;
    if (localKeyCharacterMap.getKeyboardType() != 1) {
      bool1 = true;
    }
    localMenuBuilder.setQwertyMode(bool1);
    this.mMenu.startDispatchingItemsChanged();
    this.mMenuIsPrepared = true;
    return true;
  }
  
  private void reopenMenu(boolean paramBoolean)
  {
    if ((this.wActionBar != null) && (this.wActionBar.isOverflowReserved()))
    {
      if ((this.wActionBar.isOverflowMenuShowing()) && (paramBoolean)) {
        break label61;
      }
      if ((this.wActionBar.getVisibility() == 0) && (callbackPrepareOptionsMenu(this.mMenu))) {
        this.wActionBar.showOverflowMenu();
      }
    }
    return;
    label61:
    this.wActionBar.hideOverflowMenu();
  }
  
  private void setFeatureInt(int paramInt1, int paramInt2)
  {
    updateInt(paramInt1, paramInt2, false);
  }
  
  private void showProgressBars(IcsProgressBar paramIcsProgressBar1, IcsProgressBar paramIcsProgressBar2)
  {
    int i = this.mFeatures;
    if (((i & 0x20) != 0) && (paramIcsProgressBar2.getVisibility() == 4)) {
      paramIcsProgressBar2.setVisibility(0);
    }
    if (((i & 0x4) != 0) && (paramIcsProgressBar1.getProgress() < 10000)) {
      paramIcsProgressBar1.setVisibility(0);
    }
  }
  
  private void updateInt(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (this.mContentParent == null) {}
    while (((getFeatures() & 1 << paramInt1) == 0) && (!paramBoolean)) {
      return;
    }
    onIntChanged(paramInt1, paramInt2);
  }
  
  private void updateProgressBars(int paramInt)
  {
    IcsProgressBar localIcsProgressBar1 = getCircularProgressBar(true);
    IcsProgressBar localIcsProgressBar2 = getHorizontalProgressBar(true);
    int i = this.mFeatures;
    if (paramInt == -1) {
      if ((i & 0x4) != 0)
      {
        paramInt = localIcsProgressBar2.getProgress();
        if ((localIcsProgressBar2.isIndeterminate()) || (paramInt < 10000))
        {
          paramInt = 0;
          localIcsProgressBar2.setVisibility(paramInt);
        }
      }
      else if ((i & 0x20) != 0)
      {
        localIcsProgressBar1.setVisibility(0);
      }
    }
    label109:
    do
    {
      do
      {
        return;
        paramInt = 4;
        break;
        if (paramInt != -2) {
          break label109;
        }
        if ((i & 0x4) != 0) {
          localIcsProgressBar2.setVisibility(8);
        }
      } while ((i & 0x20) == 0);
      localIcsProgressBar1.setVisibility(8);
      return;
      if (paramInt == -3)
      {
        localIcsProgressBar2.setIndeterminate(true);
        return;
      }
      if (paramInt == -4)
      {
        localIcsProgressBar2.setIndeterminate(false);
        return;
      }
      if ((paramInt >= 0) && (paramInt <= 10000))
      {
        localIcsProgressBar2.setProgress(paramInt + 0);
        if (paramInt < 10000)
        {
          showProgressBars(localIcsProgressBar2, localIcsProgressBar1);
          return;
        }
        hideProgressBars(localIcsProgressBar2, localIcsProgressBar1);
        return;
      }
    } while ((20000 > paramInt) || (paramInt > 30000));
    localIcsProgressBar2.setSecondaryProgress(paramInt - 20000);
    showProgressBars(localIcsProgressBar2, localIcsProgressBar1);
  }
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (this.mContentParent == null) {
      installDecor();
    }
    this.mContentParent.addView(paramView, paramLayoutParams);
    initActionBar();
  }
  
  void checkCloseActionMenu(com.actionbarsherlock.view.Menu paramMenu)
  {
    if (this.mClosingActionMenu) {
      return;
    }
    this.mClosingActionMenu = true;
    this.wActionBar.dismissPopupMenus();
    this.mClosingActionMenu = false;
  }
  
  public boolean dispatchCloseOptionsMenu()
  {
    if (!isReservingOverflow()) {}
    while (this.wActionBar == null) {
      return false;
    }
    return this.wActionBar.hideOverflowMenu();
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.aActionBar != null) {
      this.aActionBar.onConfigurationChanged(paramConfiguration);
    }
  }
  
  public boolean dispatchCreateOptionsMenu(android.view.Menu paramMenu)
  {
    return true;
  }
  
  public void dispatchDestroy()
  {
    this.mIsDestroyed = true;
  }
  
  public void dispatchInvalidateOptionsMenu()
  {
    if (this.mMenu != null)
    {
      Bundle localBundle = new Bundle();
      this.mMenu.saveActionViewStates(localBundle);
      if (localBundle.size() > 0) {
        this.mMenuFrozenActionViewState = localBundle;
      }
      this.mMenu.stopDispatchingItemsChanged();
      this.mMenu.clear();
    }
    this.mMenuRefreshContent = true;
    if (this.wActionBar != null)
    {
      this.mMenuIsPrepared = false;
      preparePanel();
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getKeyCode() == 4)
    {
      int i = paramKeyEvent.getAction();
      if (this.mActionMode != null) {
        if (i == 1) {
          this.mActionMode.finish();
        }
      }
      do
      {
        return true;
        if ((this.wActionBar == null) || (!this.wActionBar.hasExpandedActionView())) {
          break;
        }
      } while (i != 1);
      this.wActionBar.collapseActionView();
      return true;
    }
    return false;
  }
  
  public boolean dispatchMenuOpened(int paramInt, android.view.Menu paramMenu)
  {
    if ((paramInt == 8) || (paramInt == 0))
    {
      if (this.aActionBar != null) {
        this.aActionBar.dispatchMenuVisibilityChanged(true);
      }
      return true;
    }
    return false;
  }
  
  public boolean dispatchOpenOptionsMenu()
  {
    if (!isReservingOverflow()) {
      return false;
    }
    return this.wActionBar.showOverflowMenu();
  }
  
  public boolean dispatchOptionsItemSelected(android.view.MenuItem paramMenuItem)
  {
    throw new IllegalStateException("Native callback invoked. Create a test case and report!");
  }
  
  public void dispatchPanelClosed(int paramInt, android.view.Menu paramMenu)
  {
    if (((paramInt == 8) || (paramInt == 0)) && (this.aActionBar != null)) {
      this.aActionBar.dispatchMenuVisibilityChanged(false);
    }
  }
  
  public void dispatchPause()
  {
    if ((this.wActionBar != null) && (this.wActionBar.isOverflowMenuShowing())) {
      this.wActionBar.hideOverflowMenu();
    }
  }
  
  public void dispatchPostCreate(Bundle paramBundle)
  {
    if (this.mIsDelegate) {
      this.mIsTitleReady = true;
    }
    if (this.mDecor == null) {
      initActionBar();
    }
  }
  
  public void dispatchPostResume()
  {
    if (this.aActionBar != null) {
      this.aActionBar.setShowHideAnimationEnabled(true);
    }
  }
  
  public boolean dispatchPrepareOptionsMenu(android.view.Menu paramMenu)
  {
    if (this.mActionMode != null) {}
    for (;;)
    {
      return false;
      this.mMenuIsPrepared = false;
      if ((preparePanel()) && (!isReservingOverflow()))
      {
        if (this.mNativeItemMap == null) {
          this.mNativeItemMap = new HashMap();
        }
        while (this.mMenu != null)
        {
          return this.mMenu.bindNativeOverflow(paramMenu, this, this.mNativeItemMap);
          this.mNativeItemMap.clear();
        }
      }
    }
  }
  
  public void dispatchRestoreInstanceState(Bundle paramBundle)
  {
    this.mMenuFrozenActionViewState = ((Bundle)paramBundle.getParcelable("sherlock:Panels"));
  }
  
  public void dispatchSaveInstanceState(Bundle paramBundle)
  {
    if (this.mMenu != null)
    {
      this.mMenuFrozenActionViewState = new Bundle();
      this.mMenu.saveActionViewStates(this.mMenuFrozenActionViewState);
    }
    paramBundle.putParcelable("sherlock:Panels", this.mMenuFrozenActionViewState);
  }
  
  public void dispatchStop()
  {
    if (this.aActionBar != null) {
      this.aActionBar.setShowHideAnimationEnabled(false);
    }
  }
  
  public void dispatchTitleChanged(CharSequence paramCharSequence, int paramInt)
  {
    if (((!this.mIsDelegate) || (this.mIsTitleReady)) && (this.wActionBar != null)) {
      this.wActionBar.setWindowTitle(paramCharSequence);
    }
  }
  
  public void ensureActionBar()
  {
    if (this.mDecor == null) {
      initActionBar();
    }
  }
  
  public ActionBar getActionBar()
  {
    initActionBar();
    return this.aActionBar;
  }
  
  protected Context getThemedContext()
  {
    return this.aActionBar.getThemedContext();
  }
  
  public boolean hasFeature(int paramInt)
  {
    return (this.mFeatures & 1 << paramInt) != 0;
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    checkCloseActionMenu(paramMenuBuilder);
  }
  
  public boolean onMenuItemClick(android.view.MenuItem paramMenuItem)
  {
    MenuItemImpl localMenuItemImpl = (MenuItemImpl)this.mNativeItemMap.get(paramMenuItem);
    if (localMenuItemImpl != null) {
      localMenuItemImpl.invoke();
    }
    for (;;)
    {
      return true;
      Log.e("ActionBarSherlock", "Options item \"" + paramMenuItem + "\" not found in mapping");
    }
  }
  
  public boolean onMenuItemSelected(int paramInt, com.actionbarsherlock.view.MenuItem paramMenuItem)
  {
    return callbackOptionsItemSelected(paramMenuItem);
  }
  
  public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, com.actionbarsherlock.view.MenuItem paramMenuItem)
  {
    return callbackOptionsItemSelected(paramMenuItem);
  }
  
  public void onMenuModeChange(MenuBuilder paramMenuBuilder)
  {
    reopenMenu(true);
  }
  
  public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
  {
    return true;
  }
  
  public boolean requestFeature(int paramInt)
  {
    if (this.mContentParent != null) {
      throw new AndroidRuntimeException("requestFeature() must be called before adding content");
    }
    switch (paramInt)
    {
    case 3: 
    case 4: 
    case 6: 
    case 7: 
    default: 
      return false;
    }
    this.mFeatures |= 1 << paramInt;
    return true;
  }
  
  public void setContentView(int paramInt)
  {
    if (this.mContentParent == null) {
      installDecor();
    }
    for (;;)
    {
      this.mActivity.getLayoutInflater().inflate(paramInt, this.mContentParent);
      android.view.Window.Callback localCallback = this.mActivity.getWindow().getCallback();
      if (localCallback != null) {
        localCallback.onContentChanged();
      }
      initActionBar();
      return;
      this.mContentParent.removeAllViews();
    }
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (this.mContentParent == null) {
      installDecor();
    }
    for (;;)
    {
      this.mContentParent.addView(paramView, paramLayoutParams);
      paramView = this.mActivity.getWindow().getCallback();
      if (paramView != null) {
        paramView.onContentChanged();
      }
      initActionBar();
      return;
      this.mContentParent.removeAllViews();
    }
  }
  
  public void setProgress(int paramInt)
  {
    setFeatureInt(2, paramInt + 0);
  }
  
  public void setProgressBarIndeterminate(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = -3;; i = -4)
    {
      setFeatureInt(2, i);
      return;
    }
  }
  
  public void setProgressBarIndeterminateVisibility(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = -1;; i = -2)
    {
      setFeatureInt(5, i);
      return;
    }
  }
  
  public void setProgressBarVisibility(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = -1;; i = -2)
    {
      setFeatureInt(2, i);
      return;
    }
  }
  
  public void setSecondaryProgress(int paramInt)
  {
    setFeatureInt(2, paramInt + 20000);
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    dispatchTitleChanged(paramCharSequence, 0);
  }
  
  public void setUiOptions(int paramInt)
  {
    this.mUiOptions = paramInt;
  }
  
  public void setUiOptions(int paramInt1, int paramInt2)
  {
    this.mUiOptions = (this.mUiOptions & (paramInt2 ^ 0xFFFFFFFF) | paramInt1 & paramInt2);
  }
  
  public ActionMode startActionMode(ActionMode.Callback paramCallback)
  {
    if (this.mActionMode != null) {
      this.mActionMode.finish();
    }
    ActionModeCallbackWrapper localActionModeCallbackWrapper = new ActionModeCallbackWrapper(paramCallback);
    Object localObject = null;
    initActionBar();
    if (this.aActionBar != null) {
      localObject = this.aActionBar.startActionMode(localActionModeCallbackWrapper);
    }
    if (localObject != null) {
      this.mActionMode = ((ActionMode)localObject);
    }
    for (;;)
    {
      if ((this.mActionMode != null) && ((this.mActivity instanceof ActionBarSherlock.OnActionModeStartedListener))) {
        ((ActionBarSherlock.OnActionModeStartedListener)this.mActivity).onActionModeStarted(this.mActionMode);
      }
      return this.mActionMode;
      if (this.mActionModeView == null)
      {
        localObject = (ViewStub)this.mDecor.findViewById(R.id.abs__action_mode_bar_stub);
        if (localObject != null) {
          this.mActionModeView = ((ActionBarContextView)((ViewStub)localObject).inflate());
        }
      }
      if (this.mActionModeView != null)
      {
        this.mActionModeView.killMode();
        localObject = new StandaloneActionMode(this.mActivity, this.mActionModeView, localActionModeCallbackWrapper, true);
        if (paramCallback.onCreateActionMode((ActionMode)localObject, ((ActionMode)localObject).getMenu()))
        {
          ((ActionMode)localObject).invalidate();
          this.mActionModeView.initForMode((ActionMode)localObject);
          this.mActionModeView.setVisibility(0);
          this.mActionMode = ((ActionMode)localObject);
          this.mActionModeView.sendAccessibilityEvent(32);
        }
        else
        {
          this.mActionMode = null;
        }
      }
    }
  }
  
  private class ActionModeCallbackWrapper
    implements ActionMode.Callback
  {
    private final ActionMode.Callback mWrapped;
    
    public ActionModeCallbackWrapper(ActionMode.Callback paramCallback)
    {
      this.mWrapped = paramCallback;
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, com.actionbarsherlock.view.MenuItem paramMenuItem)
    {
      return this.mWrapped.onActionItemClicked(paramActionMode, paramMenuItem);
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, com.actionbarsherlock.view.Menu paramMenu)
    {
      return this.mWrapped.onCreateActionMode(paramActionMode, paramMenu);
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      this.mWrapped.onDestroyActionMode(paramActionMode);
      if (ActionBarSherlockCompat.this.mActionModeView != null)
      {
        ActionBarSherlockCompat.this.mActionModeView.setVisibility(8);
        ActionBarSherlockCompat.this.mActionModeView.removeAllViews();
      }
      if ((ActionBarSherlockCompat.this.mActivity instanceof ActionBarSherlock.OnActionModeFinishedListener)) {
        ((ActionBarSherlock.OnActionModeFinishedListener)ActionBarSherlockCompat.this.mActivity).onActionModeFinished(ActionBarSherlockCompat.this.mActionMode);
      }
      ActionBarSherlockCompat.this.mActionMode = null;
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, com.actionbarsherlock.view.Menu paramMenu)
    {
      return this.mWrapped.onPrepareActionMode(paramActionMode, paramMenu);
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\ActionBarSherlockCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */