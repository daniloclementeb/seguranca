package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class oj
  implements Parcelable.Creator<nz.h>
{
  static void a(nz.h paramh, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    Set localSet = paramh.amc;
    if (localSet.contains(Integer.valueOf(1))) {
      b.c(paramParcel, 1, paramh.BR);
    }
    if (localSet.contains(Integer.valueOf(3))) {
      b.c(paramParcel, 3, paramh.nD());
    }
    if (localSet.contains(Integer.valueOf(4))) {
      b.a(paramParcel, 4, paramh.mValue, true);
    }
    if (localSet.contains(Integer.valueOf(5))) {
      b.a(paramParcel, 5, paramh.anH, true);
    }
    if (localSet.contains(Integer.valueOf(6))) {
      b.c(paramParcel, 6, paramh.FD);
    }
    b.H(paramParcel, paramInt);
  }
  
  public nz.h dm(Parcel paramParcel)
  {
    String str1 = null;
    int i = 0;
    int m = a.C(paramParcel);
    HashSet localHashSet = new HashSet();
    int j = 0;
    String str2 = null;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      case 2: 
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        k = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 3: 
        i = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(3));
        break;
      case 4: 
        str1 = a.o(paramParcel, n);
        localHashSet.add(Integer.valueOf(4));
        break;
      case 5: 
        str2 = a.o(paramParcel, n);
        localHashSet.add(Integer.valueOf(5));
        break;
      case 6: 
        j = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(6));
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new nz.h(localHashSet, k, str2, j, str1, i);
  }
  
  public nz.h[] fe(int paramInt)
  {
    return new nz.h[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\oj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */