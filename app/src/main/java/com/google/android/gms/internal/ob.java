package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class ob
  implements Parcelable.Creator<nz.a>
{
  static void a(nz.a parama, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    Set localSet = parama.amc;
    if (localSet.contains(Integer.valueOf(1))) {
      b.c(paramParcel, 1, parama.BR);
    }
    if (localSet.contains(Integer.valueOf(2))) {
      b.c(paramParcel, 2, parama.ant);
    }
    if (localSet.contains(Integer.valueOf(3))) {
      b.c(paramParcel, 3, parama.anu);
    }
    b.H(paramParcel, paramInt);
  }
  
  public nz.a de(Parcel paramParcel)
  {
    int k = 0;
    int m = a.C(paramParcel);
    HashSet localHashSet = new HashSet();
    int j = 0;
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
        i = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2: 
        j = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 3: 
        k = a.g(paramParcel, n);
        localHashSet.add(Integer.valueOf(3));
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new nz.a(localHashSet, i, j, k);
  }
  
  public nz.a[] eW(int paramInt)
  {
    return new nz.a[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\ob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */