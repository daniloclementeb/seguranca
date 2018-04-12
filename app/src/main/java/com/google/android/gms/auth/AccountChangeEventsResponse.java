package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class AccountChangeEventsResponse
  implements SafeParcelable
{
  public static final AccountChangeEventsResponseCreator CREATOR = new AccountChangeEventsResponseCreator();
  final int Di;
  final List<AccountChangeEvent> me;
  
  AccountChangeEventsResponse(int paramInt, List<AccountChangeEvent> paramList)
  {
    this.Di = paramInt;
    this.me = ((List)o.i(paramList));
  }
  
  public AccountChangeEventsResponse(List<AccountChangeEvent> paramList)
  {
    this.Di = 1;
    this.me = ((List)o.i(paramList));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public List<AccountChangeEvent> getEvents()
  {
    return this.me;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    AccountChangeEventsResponseCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\auth\AccountChangeEventsResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */