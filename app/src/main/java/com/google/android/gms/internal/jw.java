package com.google.android.gms.internal;

import android.os.SystemClock;

public final class jw
  implements ju
{
  private static jw MS;
  
  public static ju hA()
  {
    try
    {
      if (MS == null) {
        MS = new jw();
      }
      jw localjw = MS;
      return localjw;
    }
    finally {}
  }
  
  public long currentTimeMillis()
  {
    return System.currentTimeMillis();
  }
  
  public long elapsedRealtime()
  {
    return SystemClock.elapsedRealtime();
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\jw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */