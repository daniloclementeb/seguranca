package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class of
  implements Parcelable.Creator<nz.c>
{
  static void a(nz.c paramc, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    Set localSet = paramc.amc;
    if (localSet.contains(Integer.valueOf(1))) {
      b.c(paramParcel, 1, paramc.BR);
    }
    if (localSet.contains(Integer.valueOf(2))) {
      b.a(paramParcel, 2, paramc.uR, true);
    }
    b.H(paramParcel, paramInt);
  }
  
  public nz.c di(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    HashSet localHashSet = new HashSet();
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
        i = a.g(paramParcel, k);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2: 
        str = a.o(paramParcel, k);
        localHashSet.add(Integer.valueOf(2));
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new nz.c(localHashSet, i, str);
  }
  
  public nz.c[] fa(int paramInt)
  {
    return new nz.c[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\of.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */