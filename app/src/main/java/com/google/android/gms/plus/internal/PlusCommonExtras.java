package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.c;

public class PlusCommonExtras
  implements SafeParcelable
{
  public static final f CREATOR = new f();
  public static String TAG = "PlusCommonExtras";
  private final int BR;
  private String alA;
  private String alB;
  
  public PlusCommonExtras()
  {
    this.BR = 1;
    this.alA = "";
    this.alB = "";
  }
  
  PlusCommonExtras(int paramInt, String paramString1, String paramString2)
  {
    this.BR = paramInt;
    this.alA = paramString1;
    this.alB = paramString2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof PlusCommonExtras)) {}
    do
    {
      return false;
      paramObject = (PlusCommonExtras)paramObject;
    } while ((this.BR != ((PlusCommonExtras)paramObject).BR) || (!n.equal(this.alA, ((PlusCommonExtras)paramObject).alA)) || (!n.equal(this.alB, ((PlusCommonExtras)paramObject).alB)));
    return true;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.BR), this.alA, this.alB });
  }
  
  public String ne()
  {
    return this.alA;
  }
  
  public String nf()
  {
    return this.alB;
  }
  
  public void o(Bundle paramBundle)
  {
    paramBundle.putByteArray("android.gms.plus.internal.PlusCommonExtras.extraPlusCommon", c.a(this));
  }
  
  public String toString()
  {
    return n.h(this).a("versionCode", Integer.valueOf(this.BR)).a("Gpsrc", this.alA).a("ClientCallingPackage", this.alB).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\plus\internal\PlusCommonExtras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */