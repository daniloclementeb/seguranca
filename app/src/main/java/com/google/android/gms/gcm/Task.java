package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Task
  implements Parcelable
{
  private final String adB = null;
  private final boolean adC = false;
  private final boolean adD = false;
  private final String mTag = null;
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getServiceName()
  {
    return this.adB;
  }
  
  public String getTag()
  {
    return this.mTag;
  }
  
  public boolean isPersisted()
  {
    return this.adD;
  }
  
  public boolean isUpdateCurrent()
  {
    return this.adC;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    paramParcel.writeString(this.adB);
    paramParcel.writeString(this.mTag);
    if (this.adC)
    {
      paramInt = 1;
      paramParcel.writeInt(paramInt);
      if (!this.adD) {
        break label52;
      }
    }
    label52:
    for (paramInt = i;; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      return;
      paramInt = 0;
      break;
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\gcm\Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */