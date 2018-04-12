package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

public class mb
  implements Parcelable.Creator<ma>
{
  static void a(ma paramma, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramma.UI, paramInt, false);
    b.c(paramParcel, 1000, paramma.getVersionCode());
    b.a(paramParcel, 2, paramma.afi);
    b.a(paramParcel, 3, paramma.afj);
    b.a(paramParcel, 4, paramma.afk);
    b.c(paramParcel, 5, paramma.afl, false);
    b.a(paramParcel, 6, paramma.mTag, false);
    b.H(paramParcel, i);
  }
  
  public ma cv(Parcel paramParcel)
  {
    String str = null;
    boolean bool1 = true;
    boolean bool3 = false;
    int j = a.C(paramParcel);
    Object localObject = ma.afh;
    boolean bool2 = true;
    LocationRequest localLocationRequest = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localLocationRequest = (LocationRequest)a.a(paramParcel, k, LocationRequest.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        bool3 = a.c(paramParcel, k);
        break;
      case 3: 
        bool2 = a.c(paramParcel, k);
        break;
      case 4: 
        bool1 = a.c(paramParcel, k);
        break;
      case 5: 
        localObject = a.c(paramParcel, k, ls.CREATOR);
        break;
      case 6: 
        str = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ma(i, localLocationRequest, bool3, bool2, bool1, (List)localObject, str);
  }
  
  public ma[] ej(int paramInt)
  {
    return new ma[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\mb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */