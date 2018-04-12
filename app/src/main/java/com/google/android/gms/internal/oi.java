package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class oi
  implements Parcelable.Creator<nz.g>
{
  static void a(nz.g paramg, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    Set localSet = paramg.amc;
    if (localSet.contains(Integer.valueOf(1))) {
      b.c(paramParcel, 1, paramg.BR);
    }
    if (localSet.contains(Integer.valueOf(2))) {
      b.a(paramParcel, 2, paramg.anG);
    }
    if (localSet.contains(Integer.valueOf(3))) {
      b.a(paramParcel, 3, paramg.mValue, true);
    }
    b.H(paramParcel, paramInt);
  }
  
  public nz.g dl(Parcel paramParcel)
  {
    boolean bool = false;
    int j = a.C(paramParcel);
    HashSet localHashSet = new HashSet();
    String str = null;
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
        i = a.g(paramParcel, k);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2: 
        bool = a.c(paramParcel, k);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 3: 
        str = a.o(paramParcel, k);
        localHashSet.add(Integer.valueOf(3));
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new nz.g(localHashSet, i, bool, str);
  }
  
  public nz.g[] fd(int paramInt)
  {
    return new nz.g[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\oi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */