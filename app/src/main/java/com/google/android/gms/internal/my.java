package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class my
  implements Parcelable.Creator<mx>
{
  static void a(mx parammx, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, parammx.aij, false);
    b.c(paramParcel, 1000, parammx.versionCode);
    b.a(paramParcel, 2, parammx.aik, false);
    b.a(paramParcel, 3, parammx.Dv, false);
    b.H(paramParcel, paramInt);
  }
  
  public mx cF(Parcel paramParcel)
  {
    String str3 = null;
    int j = a.C(paramParcel);
    int i = 0;
    String str2 = null;
    String str1 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        str1 = a.o(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        str2 = a.o(paramParcel, k);
        break;
      case 3: 
        str3 = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new mx(i, str1, str2, str3);
  }
  
  public mx[] ev(int paramInt)
  {
    return new mx[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\my.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */