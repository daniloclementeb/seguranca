package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class md
  implements Parcelable.Creator<mc>
{
  static void a(mc parammc, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, parammc.getRequestId(), false);
    b.c(paramParcel, 1000, parammc.getVersionCode());
    b.a(paramParcel, 2, parammc.getExpirationTime());
    b.a(paramParcel, 3, parammc.ma());
    b.a(paramParcel, 4, parammc.getLatitude());
    b.a(paramParcel, 5, parammc.getLongitude());
    b.a(paramParcel, 6, parammc.mb());
    b.c(paramParcel, 7, parammc.mc());
    b.c(paramParcel, 8, parammc.getNotificationResponsiveness());
    b.c(paramParcel, 9, parammc.md());
    b.H(paramParcel, paramInt);
  }
  
  public mc cw(Parcel paramParcel)
  {
    int n = a.C(paramParcel);
    int m = 0;
    String str = null;
    int k = 0;
    short s = 0;
    double d2 = 0.0D;
    double d1 = 0.0D;
    float f = 0.0F;
    long l = 0L;
    int j = 0;
    int i = -1;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = a.B(paramParcel);
      switch (a.aD(i1))
      {
      default: 
        a.b(paramParcel, i1);
        break;
      case 1: 
        str = a.o(paramParcel, i1);
        break;
      case 1000: 
        m = a.g(paramParcel, i1);
        break;
      case 2: 
        l = a.i(paramParcel, i1);
        break;
      case 3: 
        s = a.f(paramParcel, i1);
        break;
      case 4: 
        d2 = a.m(paramParcel, i1);
        break;
      case 5: 
        d1 = a.m(paramParcel, i1);
        break;
      case 6: 
        f = a.l(paramParcel, i1);
        break;
      case 7: 
        k = a.g(paramParcel, i1);
        break;
      case 8: 
        j = a.g(paramParcel, i1);
        break;
      case 9: 
        i = a.g(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new a.a("Overread allowed size end=" + n, paramParcel);
    }
    return new mc(m, str, k, s, d2, d1, f, l, j, i);
  }
  
  public mc[] em(int paramInt)
  {
    return new mc[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\md.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */