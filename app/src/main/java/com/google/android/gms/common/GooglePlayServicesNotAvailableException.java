package com.google.android.gms.common;

public final class GooglePlayServicesNotAvailableException
  extends Exception
{
  public final int errorCode;
  
  public GooglePlayServicesNotAvailableException(int paramInt)
  {
    this.errorCode = paramInt;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\common\GooglePlayServicesNotAvailableException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */