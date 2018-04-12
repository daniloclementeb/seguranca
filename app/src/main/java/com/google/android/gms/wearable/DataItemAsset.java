package com.google.android.gms.wearable;

import com.google.android.gms.common.data.Freezable;

public abstract interface DataItemAsset
  extends Freezable<DataItemAsset>
{
  public abstract String getDataItemKey();
  
  public abstract String getId();
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\wearable\DataItemAsset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */