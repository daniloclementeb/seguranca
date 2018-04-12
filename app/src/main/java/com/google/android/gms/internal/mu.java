package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class mu
  implements Parcelable.Creator<mt>
{
  static void a(mt parammt, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, parammt.uO, false);
    b.c(paramParcel, 1000, parammt.BR);
    b.c(paramParcel, 2, parammt.aig);
    b.H(paramParcel, paramInt);
  }
  
  public mt cE(Parcel paramParcel)
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
        str = a.o(paramParcel, m);
        break;
      case 1000: 
        i = a.g(paramParcel, m);
        break;
      case 2: 
        j = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new mt(i, str, j);
  }
  
  public mt[] eu(int paramInt)
  {
    return new mt[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\mu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */