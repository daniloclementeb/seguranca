package com.google.android.gms.plus.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;

public class i
{
  private String Dd;
  private String[] alF;
  private String alG;
  private String alH;
  private String alI;
  private PlusCommonExtras alK;
  private final ArrayList<String> alL = new ArrayList();
  private String[] alM;
  
  public i(Context paramContext)
  {
    this.alH = paramContext.getPackageName();
    this.alG = paramContext.getPackageName();
    this.alK = new PlusCommonExtras();
    this.alL.add("https://www.googleapis.com/auth/plus.login");
  }
  
  public i ch(String paramString)
  {
    this.Dd = paramString;
    return this;
  }
  
  public i g(String... paramVarArgs)
  {
    this.alL.clear();
    this.alL.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public i h(String... paramVarArgs)
  {
    this.alM = paramVarArgs;
    return this;
  }
  
  public i np()
  {
    this.alL.clear();
    return this;
  }
  
  public h nq()
  {
    if (this.Dd == null) {
      this.Dd = "<<default account>>";
    }
    String[] arrayOfString = (String[])this.alL.toArray(new String[this.alL.size()]);
    return new h(this.Dd, arrayOfString, this.alM, this.alF, this.alG, this.alH, this.alI, this.alK);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\plus\internal\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */