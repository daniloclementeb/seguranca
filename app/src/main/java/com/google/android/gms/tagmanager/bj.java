package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class bj
  extends aj
{
  private static final String ID = a.ai.toString();
  private static final String apf = b.bw.toString();
  
  public bj()
  {
    super(ID, new String[] { apf });
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    return di.u(di.j((d.a)paramMap.get(apf)).toLowerCase());
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\tagmanager\bj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */