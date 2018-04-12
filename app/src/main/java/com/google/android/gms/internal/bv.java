package com.google.android.gms.internal;

import java.util.Map;

@ez
public final class bv
  implements by
{
  private final bw pz;
  
  public bv(bw parambw)
  {
    this.pz = parambw;
  }
  
  public void a(gv paramgv, Map<String, String> paramMap)
  {
    paramgv = (String)paramMap.get("name");
    if (paramgv == null)
    {
      gs.W("App event with no name parameter.");
      return;
    }
    this.pz.onAppEvent(paramgv, (String)paramMap.get("info"));
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\bv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */