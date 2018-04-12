package com.google.android.gms.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

@Deprecated
public final class CustomEventExtras
  implements NetworkExtras
{
  private final HashMap<String, Object> xj = new HashMap();
  
  public Object getExtra(String paramString)
  {
    return this.xj.get(paramString);
  }
  
  public void setExtra(String paramString, Object paramObject)
  {
    this.xj.put(paramString, paramObject);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\ads\mediation\customevent\CustomEventExtras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */