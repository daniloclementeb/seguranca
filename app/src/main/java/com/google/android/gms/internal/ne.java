package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.panorama.PanoramaApi.PanoramaResult;

class ne
  implements PanoramaApi.PanoramaResult
{
  private final Status CM;
  private final Intent akC;
  
  public ne(Status paramStatus, Intent paramIntent)
  {
    this.CM = ((Status)o.i(paramStatus));
    this.akC = paramIntent;
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  public Intent getViewerIntent()
  {
    return this.akC;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\ne.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */