package com.google.android.gms.wearable;

public abstract interface MessageEvent
{
  public abstract byte[] getData();
  
  public abstract String getPath();
  
  public abstract int getRequestId();
  
  public abstract String getSourceNodeId();
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\wearable\MessageEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */