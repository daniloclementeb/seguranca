package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class lt
  implements Parcelable.Creator<ls>
{
  static void a(ls paramls, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramls.uid);
    b.c(paramParcel, 1000, paramls.getVersionCode());
    b.a(paramParcel, 2, paramls.packageName, false);
    b.H(paramParcel, paramInt);
  }
  
  public ls cu(Parcel paramParcel)
  {
    int j = 0;
    int k = a.C(paramParcel);
    String str = null;
    int i = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        j = a.g(paramParcel, m);
        break;
      case 1000: 
        i = a.g(paramParcel, m);
        break;
      case 2: 
        str = a.o(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new ls(i, j, str);
  }
  
  public ls[] ei(int paramInt)
  {
    return new ls[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\lt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */