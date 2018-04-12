package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.plus.PlusOneDummyView;

public final class g
  extends com.google.android.gms.dynamic.g<c>
{
  private static final g alC = new g();
  
  private g()
  {
    super("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl");
  }
  
  public static View a(Context paramContext, int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    if (paramString == null) {
      try
      {
        throw new NullPointerException();
      }
      catch (Exception paramString)
      {
        return new PlusOneDummyView(paramContext, paramInt1);
      }
    }
    paramString = (View)e.f(((c)alC.L(paramContext)).a(e.k(paramContext), paramInt1, paramInt2, paramString, paramInt3));
    return paramString;
  }
  
  protected c bI(IBinder paramIBinder)
  {
    return c.a.bF(paramIBinder);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\plus\internal\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */