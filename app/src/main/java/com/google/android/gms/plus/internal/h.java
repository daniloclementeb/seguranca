package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;

public class h
  implements SafeParcelable
{
  public static final j CREATOR = new j();
  private final int BR;
  private final String Dd;
  private final String[] alD;
  private final String[] alE;
  private final String[] alF;
  private final String alG;
  private final String alH;
  private final String alI;
  private final String alJ;
  private final PlusCommonExtras alK;
  
  h(int paramInt, String paramString1, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString2, String paramString3, String paramString4, String paramString5, PlusCommonExtras paramPlusCommonExtras)
  {
    this.BR = paramInt;
    this.Dd = paramString1;
    this.alD = paramArrayOfString1;
    this.alE = paramArrayOfString2;
    this.alF = paramArrayOfString3;
    this.alG = paramString2;
    this.alH = paramString3;
    this.alI = paramString4;
    this.alJ = paramString5;
    this.alK = paramPlusCommonExtras;
  }
  
  public h(String paramString1, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString2, String paramString3, String paramString4, PlusCommonExtras paramPlusCommonExtras)
  {
    this.BR = 1;
    this.Dd = paramString1;
    this.alD = paramArrayOfString1;
    this.alE = paramArrayOfString2;
    this.alF = paramArrayOfString3;
    this.alG = paramString2;
    this.alH = paramString3;
    this.alI = paramString4;
    this.alJ = null;
    this.alK = paramPlusCommonExtras;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof h)) {}
    do
    {
      return false;
      paramObject = (h)paramObject;
    } while ((this.BR != ((h)paramObject).BR) || (!n.equal(this.Dd, ((h)paramObject).Dd)) || (!Arrays.equals(this.alD, ((h)paramObject).alD)) || (!Arrays.equals(this.alE, ((h)paramObject).alE)) || (!Arrays.equals(this.alF, ((h)paramObject).alF)) || (!n.equal(this.alG, ((h)paramObject).alG)) || (!n.equal(this.alH, ((h)paramObject).alH)) || (!n.equal(this.alI, ((h)paramObject).alI)) || (!n.equal(this.alJ, ((h)paramObject).alJ)) || (!n.equal(this.alK, ((h)paramObject).alK)));
    return true;
  }
  
  public String getAccountName()
  {
    return this.Dd;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.BR), this.Dd, this.alD, this.alE, this.alF, this.alG, this.alH, this.alI, this.alJ, this.alK });
  }
  
  public String[] ng()
  {
    return this.alD;
  }
  
  public String[] nh()
  {
    return this.alE;
  }
  
  public String[] ni()
  {
    return this.alF;
  }
  
  public String nj()
  {
    return this.alG;
  }
  
  public String nk()
  {
    return this.alH;
  }
  
  public String nl()
  {
    return this.alI;
  }
  
  public String nm()
  {
    return this.alJ;
  }
  
  public PlusCommonExtras nn()
  {
    return this.alK;
  }
  
  public Bundle no()
  {
    Bundle localBundle = new Bundle();
    localBundle.setClassLoader(PlusCommonExtras.class.getClassLoader());
    this.alK.o(localBundle);
    return localBundle;
  }
  
  public String toString()
  {
    return n.h(this).a("versionCode", Integer.valueOf(this.BR)).a("accountName", this.Dd).a("requestedScopes", this.alD).a("visibleActivities", this.alE).a("requiredFeatures", this.alF).a("packageNameForAuth", this.alG).a("callingPackageName", this.alH).a("applicationName", this.alI).a("extra", this.alK.toString()).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    j.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\plus\internal\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */