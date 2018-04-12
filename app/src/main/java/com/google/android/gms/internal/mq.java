package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class mq
  implements Parcelable.Creator<mp>
{
  static void a(mp parammp, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, parammp.uO, false);
    b.c(paramParcel, 1000, parammp.BR);
    b.H(paramParcel, paramInt);
  }
  
  public mp cC(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    String str = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        str = a.o(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new mp(i, str);
  }
  
  public mp[] es(int paramInt)
  {
    return new mp[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\mq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */