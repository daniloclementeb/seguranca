package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class og
  implements Parcelable.Creator<nz.d>
{
  static void a(nz.d paramd, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    Set localSet = paramd.amc;
    if (localSet.contains(Integer.valueOf(1))) {
      b.c(paramParcel, 1, paramd.BR);
    }
    if (localSet.contains(Integer.valueOf(2))) {
      b.a(paramParcel, 2, paramd.amA, true);
    }
    if (localSet.contains(Integer.valueOf(3))) {
      b.a(paramParcel, 3, paramd.anA, true);
    }
    if (localSet.contains(Integer.valueOf(4))) {
      b.a(paramParcel, 4, paramd.amD, true);
    }
    if (localSet.contains(Integer.valueOf(5))) {
      b.a(paramParcel, 5, paramd.anB, true);
    }
    if (localSet.contains(Integer.valueOf(6))) {
      b.a(paramParcel, 6, paramd.anC, true);
    }
    if (localSet.contains(Integer.valueOf(7))) {
      b.a(paramParcel, 7, paramd.anD, true);
    }
    b.H(paramParcel, paramInt);
  }
  
  public nz.d dj(Parcel paramParcel)
  {
    String str1 = null;
    int j = a.C(paramParcel);
    HashSet localHashSet = new HashSet();
    int i = 0;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    String str6 = null;
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
        str6 = a.o(paramParcel, k);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 3: 
        str5 = a.o(paramParcel, k);
        localHashSet.add(Integer.valueOf(3));
        break;
      case 4: 
        str4 = a.o(paramParcel, k);
        localHashSet.add(Integer.valueOf(4));
        break;
      case 5: 
        str3 = a.o(paramParcel, k);
        localHashSet.add(Integer.valueOf(5));
        break;
      case 6: 
        str2 = a.o(paramParcel, k);
        localHashSet.add(Integer.valueOf(6));
        break;
      case 7: 
        str1 = a.o(paramParcel, k);
        localHashSet.add(Integer.valueOf(7));
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new nz.d(localHashSet, i, str6, str5, str4, str3, str2, str1);
  }
  
  public nz.d[] fb(int paramInt)
  {
    return new nz.d[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\og.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */