package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class mh
  implements SafeParcelable
{
  public static final mi CREATOR = new mi();
  private final int BR;
  private final int aeh;
  private final int afp;
  private final mj afq;
  
  mh(int paramInt1, int paramInt2, int paramInt3, mj parammj)
  {
    this.BR = paramInt1;
    this.aeh = paramInt2;
    this.afp = paramInt3;
    this.afq = parammj;
  }
  
  public int describeContents()
  {
    mi localmi = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof mh)) {
        return false;
      }
      paramObject = (mh)paramObject;
    } while ((this.aeh == ((mh)paramObject).aeh) && (this.afp == ((mh)paramObject).afp) && (this.afq.equals(((mh)paramObject).afq)));
    return false;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.aeh), Integer.valueOf(this.afp) });
  }
  
  public int mc()
  {
    return this.aeh;
  }
  
  public int mg()
  {
    return this.afp;
  }
  
  public mj mh()
  {
    return this.afq;
  }
  
  public String toString()
  {
    return n.h(this).a("transitionTypes", Integer.valueOf(this.aeh)).a("loiteringTimeMillis", Integer.valueOf(this.afp)).a("placeFilter", this.afq).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    mi localmi = CREATOR;
    mi.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\mh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */