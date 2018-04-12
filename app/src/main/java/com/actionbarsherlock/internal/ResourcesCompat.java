package com.actionbarsherlock.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.actionbarsherlock.R.bool;
import com.actionbarsherlock.R.integer;

public final class ResourcesCompat
{
  private static final String TAG = "ResourcesCompat";
  
  public static boolean getResources_getBoolean(Context paramContext, int paramInt)
  {
    boolean bool = true;
    if (Build.VERSION.SDK_INT >= 14) {
      bool = paramContext.getResources().getBoolean(paramInt);
    }
    float f2;
    label81:
    do
    {
      float f1;
      do
      {
        do
        {
          return bool;
          paramContext = paramContext.getResources().getDisplayMetrics();
          f2 = paramContext.widthPixels / paramContext.density;
          f1 = paramContext.heightPixels / paramContext.density;
          if (f2 < f1) {
            f1 = f2;
          }
          for (;;)
          {
            if (paramInt != R.bool.abs__action_bar_embed_tabs) {
              break label81;
            }
            if (f2 >= 480.0F) {
              break;
            }
            return false;
          }
          if (paramInt != R.bool.abs__split_action_bar_is_narrow) {
            break;
          }
        } while (f2 < 480.0F);
        return false;
        if (paramInt != R.bool.abs__action_bar_expanded_action_views_exclusive) {
          break;
        }
      } while (f1 < 600.0F);
      return false;
      if (paramInt != R.bool.abs__config_allowActionMenuItemTextWithIcon) {
        break;
      }
    } while (f2 >= 480.0F);
    return false;
    throw new IllegalArgumentException("Unknown boolean resource ID " + paramInt);
  }
  
  public static int getResources_getInteger(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 13) {
      return paramContext.getResources().getInteger(paramInt);
    }
    paramContext = paramContext.getResources().getDisplayMetrics();
    float f = paramContext.widthPixels / paramContext.density;
    if (paramInt == R.integer.abs__max_action_buttons)
    {
      if (f >= 600.0F) {
        return 5;
      }
      if (f >= 500.0F) {
        return 4;
      }
      if (f >= 360.0F) {
        return 3;
      }
      return 2;
    }
    throw new IllegalArgumentException("Unknown integer resource ID " + paramInt);
  }
  
  public static int loadLogoFromManifest(Activity paramActivity)
  {
    int j = 0;
    int k = 0;
    int i = k;
    try
    {
      str2 = paramActivity.getClass().getName();
      i = k;
      str3 = paramActivity.getApplicationInfo().packageName;
      i = k;
      localXmlResourceParser = paramActivity.createPackageContext(str3, 0).getAssets().openXmlResourceParser("AndroidManifest.xml");
      i = k;
      k = localXmlResourceParser.getEventType();
      i = k;
    }
    catch (Exception paramActivity)
    {
      String str2;
      String str3;
      XmlResourceParser localXmlResourceParser;
      String str1;
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
        break label153;
      }
      i = j;
      k = localXmlResourceParser.getAttributeCount() - 1;
      break label335;
    }
    label120:
    label153:
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
        if (!"logo".equals(localXmlResourceParser.getAttributeName(k))) {
          break label344;
        }
        i = j;
        k = localXmlResourceParser.getAttributeResourceValue(k, 0);
      }
      k = j;
      i = j;
    } while (!"activity".equals(paramActivity));
    paramActivity = null;
    str1 = null;
    int n = 0;
    i = j;
    k = localXmlResourceParser.getAttributeCount() - 1;
    label335:
    label344:
    label349:
    label364:
    label378:
    for (;;)
    {
      i = j;
      String str4 = localXmlResourceParser.getAttributeName(k);
      i = j;
      Object localObject;
      int m;
      if ("logo".equals(str4))
      {
        i = j;
        localObject = Integer.valueOf(localXmlResourceParser.getAttributeResourceValue(k, 0));
        m = n;
      }
      for (;;)
      {
        i = j;
        if (localObject == null) {
          break label364;
        }
        i = j;
        if (str1 == null) {
          break label364;
        }
        i = j;
        j = ((Integer)localObject).intValue();
        i = j;
        break label364;
        localObject = paramActivity;
        m = n;
        i = j;
        if ("name".equals(str4))
        {
          i = j;
          str1 = ActionBarSherlockCompat.cleanActivityName(str3, localXmlResourceParser.getAttributeValue(k));
          i = j;
          boolean bool = str2.equals(str1);
          if (!bool) {
            break;
          }
          m = 1;
          localObject = paramActivity;
        }
      }
      if (i != 1) {
        break;
      }
      return j;
      for (;;)
      {
        if (k >= 0) {
          break label349;
        }
        k = j;
        break;
        k -= 1;
      }
      break label120;
      for (;;)
      {
        if (k >= 0) {
          break label378;
        }
        k = j;
        if (n == 0) {
          break;
        }
        return j;
        k -= 1;
        paramActivity = (Activity)localObject;
        n = m;
        j = i;
      }
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\internal\ResourcesCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */