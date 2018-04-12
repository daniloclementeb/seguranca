package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class oe
  implements Parcelable.Creator<nz.b.b>
{
  static void a(nz.b.b paramb, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    Set localSet = paramb.amc;
    if (localSet.contains(Integer.valueOf(1))) {
      b.c(paramParcel, 1, paramb.BR);
    }
    if (localSet.contains(Integer.valueOf(2))) {
      b.c(paramParcel, 2, paramb.lg);
    }
    if (localSet.contains(Integer.valueOf(3))) {
      b.a(paramParcel, 3, paramb.uR, true);
    }
    if (localSet.contains(Integer.valueOf(4))) {
      b.c(paramParcel, 4, paramb.lf);
    }
    b.H(paramParcel, paramInt);
  }
  
  public nz.b.b dh(Parcel paramParcel)
  {
    int i = 0;
    int m = a.C(paramParcel);
    HashSet localHashSet = new HashSet();
    String str = null;
    int j = 0;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        k = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2: 
        j = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 3: 
        str = a.o(paramParcel, n);
        localHashSet.add(Integer.valueOf(3));
        break;
      case 4: 
        i = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(4));
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new nz.b.b(localHashSet, k, j, str, i);
  }
  
  public nz.b.b[] eZ(int paramInt)
  {
    return new nz.b.b[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\oe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */