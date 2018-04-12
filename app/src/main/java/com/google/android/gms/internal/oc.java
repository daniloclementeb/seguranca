package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class oc
  implements Parcelable.Creator<nz.b>
{
  static void a(nz.b paramb, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    Set localSet = paramb.amc;
    if (localSet.contains(Integer.valueOf(1))) {
      b.c(paramParcel, 1, paramb.BR);
    }
    if (localSet.contains(Integer.valueOf(2))) {
      b.a(paramParcel, 2, paramb.anv, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(3))) {
      b.a(paramParcel, 3, paramb.anw, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(4))) {
      b.c(paramParcel, 4, paramb.anx);
    }
    b.H(paramParcel, i);
  }
  
  public nz.b df(Parcel paramParcel)
  {
    nz.b.b localb = null;
    int i = 0;
    int k = a.C(paramParcel);
    HashSet localHashSet = new HashSet();
    nz.b.a locala = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        j = a.g(paramParcel, m);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2: 
        locala = (nz.b.a)a.a(paramParcel, m, nz.b.a.CREATOR);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 3: 
        localb = (nz.b.b)a.a(paramParcel, m, nz.b.b.CREATOR);
        localHashSet.add(Integer.valueOf(3));
        break;
      case 4: 
        i = a.g(paramParcel, m);
        localHashSet.add(Integer.valueOf(4));
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new nz.b(localHashSet, j, locala, localb, i);
  }
  
  public nz.b[] eX(int paramInt)
  {
    return new nz.b[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\oc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */