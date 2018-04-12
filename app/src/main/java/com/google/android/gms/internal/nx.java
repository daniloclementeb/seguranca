package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class nx
  implements Parcelable.Creator<nw>
{
  static void a(nw paramnw, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    Set localSet = paramnw.amc;
    if (localSet.contains(Integer.valueOf(1))) {
      b.c(paramParcel, 1, paramnw.BR);
    }
    if (localSet.contains(Integer.valueOf(2))) {
      b.a(paramParcel, 2, paramnw.BL, true);
    }
    if (localSet.contains(Integer.valueOf(4))) {
      b.a(paramParcel, 4, paramnw.amX, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(5))) {
      b.a(paramParcel, 5, paramnw.amP, true);
    }
    if (localSet.contains(Integer.valueOf(6))) {
      b.a(paramParcel, 6, paramnw.amY, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(7))) {
      b.a(paramParcel, 7, paramnw.uO, true);
    }
    b.H(paramParcel, i);
  }
  
  public nw dc(Parcel paramParcel)
  {
    String str1 = null;
    int j = a.C(paramParcel);
    HashSet localHashSet = new HashSet();
    int i = 0;
    nu localnu1 = null;
    String str2 = null;
    nu localnu2 = null;
    String str3 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      case 3: 
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        i = a.g(paramParcel, k);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2: 
        str3 = a.o(paramParcel, k);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 4: 
        localnu2 = (nu)a.a(paramParcel, k, nu.CREATOR);
        localHashSet.add(Integer.valueOf(4));
        break;
      case 5: 
        str2 = a.o(paramParcel, k);
        localHashSet.add(Integer.valueOf(5));
        break;
      case 6: 
        localnu1 = (nu)a.a(paramParcel, k, nu.CREATOR);
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
    return new nw(localHashSet, i, str3, localnu2, str2, localnu1, str1);
  }
  
  public nw[] eU(int paramInt)
  {
    return new nw[paramInt];
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\nx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */