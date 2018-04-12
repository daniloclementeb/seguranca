package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class mi
  implements Parcelable.Creator<mh>
{
  static void a(mh parammh, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, parammh.mc());
    b.c(paramParcel, 1000, parammh.getVersionCode());
    b.c(paramParcel, 2, parammh.mg());
    b.a(paramParcel, 3, parammh.mh(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public mh cy(Parcel paramParcel)
  {
    int j = 0;
    int m = a.C(paramParcel);
    int k = -1;
    mj localmj = null;
    int i = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        j = a.g(paramParcel, n);
        break;
      case 1000: 
        i = a.g(paramParcel, n);
        break;
      case 2: 
        k = a.g(paramParcel, n);
        break;
      case 3: 
        localmj = (mj)a.a(paramParcel, n, mj.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new mh(i, j, k, localmj);
  }
  
  public mh[] eo(int paramInt)
  {
    return new mh[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\mi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */