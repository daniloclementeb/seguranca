package com.google.android.gms.games.internal.constants;

public final class PlatformType
{
  public static String dH(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unknown platform type: " + paramInt);
    case 0: 
      return "ANDROID";
    case 1: 
      return "IOS";
    }
    return "WEB_APP";
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\games\internal\constants\PlatformType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */