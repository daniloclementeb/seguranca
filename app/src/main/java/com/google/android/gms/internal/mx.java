package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

public class mx
  implements SafeParcelable
{
  public static final my CREATOR = new my();
  public final String Dv;
  public final String aij;
  public final String aik;
  public final int versionCode;
  
  public mx(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.versionCode = paramInt;
    this.aij = paramString1;
    this.aik = paramString2;
    this.Dv = paramString3;
  }
  
  public mx(String paramString1, Locale paramLocale, String paramString2)
  {
    this.versionCode = 0;
    this.aij = paramString1;
    this.aik = paramLocale.toString();
    this.Dv = paramString2;
  }
  
  public int describeContents()
  {
    my localmy = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof mx))) {
        return false;
      }
      paramObject = (mx)paramObject;
    } while ((this.aik.equals(((mx)paramObject).aik)) && (this.aij.equals(((mx)paramObject).aij)) && (n.equal(this.Dv, ((mx)paramObject).Dv)));
    return false;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.aij, this.aik, this.Dv });
  }
  
  public String toString()
  {
    return n.h(this).a("clientPackageName", this.aij).a("locale", this.aik).a("accountName", this.Dv).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    my localmy = CREATOR;
    my.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\mx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */