package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;

class bn
{
  int nP()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public bm ox()
  {
    if (nP() < 8) {
      return new av();
    }
    return new aw();
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\tagmanager\bn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */