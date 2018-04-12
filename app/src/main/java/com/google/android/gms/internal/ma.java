package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public class ma
  implements SafeParcelable
{
  public static final mb CREATOR = new mb();
  static final List<ls> afh = ;
  private final int BR;
  LocationRequest UI;
  boolean afi;
  boolean afj;
  boolean afk;
  List<ls> afl;
  final String mTag;
  
  ma(int paramInt, LocationRequest paramLocationRequest, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, List<ls> paramList, String paramString)
  {
    this.BR = paramInt;
    this.UI = paramLocationRequest;
    this.afi = paramBoolean1;
    this.afj = paramBoolean2;
    this.afk = paramBoolean3;
    this.afl = paramList;
    this.mTag = paramString;
  }
  
  private ma(String paramString, LocationRequest paramLocationRequest)
  {
    this(1, paramLocationRequest, false, true, true, afh, paramString);
  }
  
  public static ma a(String paramString, LocationRequest paramLocationRequest)
  {
    return new ma(paramString, paramLocationRequest);
  }
  
  public static ma b(LocationRequest paramLocationRequest)
  {
    return a(null, paramLocationRequest);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ma)) {}
    do
    {
      return false;
      paramObject = (ma)paramObject;
    } while ((!n.equal(this.UI, ((ma)paramObject).UI)) || (this.afi != ((ma)paramObject).afi) || (this.afj != ((ma)paramObject).afj) || (this.afk != ((ma)paramObject).afk) || (!n.equal(this.afl, ((ma)paramObject).afl)));
    return true;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return this.UI.hashCode();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.UI.toString());
    localStringBuilder.append(" requestNlpDebugInfo=");
    localStringBuilder.append(this.afi);
    localStringBuilder.append(" restorePendingIntentListeners=");
    localStringBuilder.append(this.afj);
    localStringBuilder.append(" triggerUpdate=");
    localStringBuilder.append(this.afk);
    localStringBuilder.append(" clients=");
    localStringBuilder.append(this.afl);
    if (this.mTag != null)
    {
      localStringBuilder.append(" tag=");
      localStringBuilder.append(this.mTag);
    }
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    mb.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\ma.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */