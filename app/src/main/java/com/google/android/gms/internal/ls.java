package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ls
  implements SafeParcelable
{
  public static final lt CREATOR = new lt();
  private final int BR;
  public final String packageName;
  public final int uid;
  
  ls(int paramInt1, int paramInt2, String paramString)
  {
    this.BR = paramInt1;
    this.uid = paramInt2;
    this.packageName = paramString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ls)) {}
    do
    {
      return false;
      paramObject = (ls)paramObject;
    } while ((((ls)paramObject).uid != this.uid) || (!n.equal(((ls)paramObject).packageName, this.packageName)));
    return true;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return this.uid;
  }
  
  public String toString()
  {
    return String.format("%d:%s", new Object[] { Integer.valueOf(this.uid), this.packageName });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    lt.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\ls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */