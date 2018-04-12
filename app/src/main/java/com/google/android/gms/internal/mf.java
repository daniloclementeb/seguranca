package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class mf
  implements SafeParcelable
{
  public static final mg CREATOR = new mg();
  final int BR;
  private final boolean afn;
  private final List<mp> afo;
  
  mf(int paramInt, boolean paramBoolean, List<mp> paramList)
  {
    this.BR = paramInt;
    this.afn = paramBoolean;
    this.afo = paramList;
  }
  
  public int describeContents()
  {
    mg localmg = CREATOR;
    return 0;
  }
  
  public boolean me()
  {
    return this.afn;
  }
  
  public List<mp> mf()
  {
    return this.afo;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    mg localmg = CREATOR;
    mg.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\mf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */