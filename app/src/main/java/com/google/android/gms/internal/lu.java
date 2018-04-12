package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationServices.a;

public class lu
  implements FusedLocationProviderApi
{
  public Location getLastLocation(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient = LocationServices.e(paramGoogleApiClient);
    try
    {
      paramGoogleApiClient = paramGoogleApiClient.getLastLocation();
      return paramGoogleApiClient;
    }
    catch (Exception paramGoogleApiClient) {}
    return null;
  }
  
  public PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, final PendingIntent paramPendingIntent)
  {
    paramGoogleApiClient.b(new a(paramPendingIntent)
    {
      protected void a(lz paramAnonymouslz)
        throws RemoteException
      {
        paramAnonymouslz.removeLocationUpdates(paramPendingIntent);
        b(Status.Jv);
      }
    });
  }
  
  public PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, final LocationListener paramLocationListener)
  {
    paramGoogleApiClient.b(new a(paramLocationListener)
    {
      protected void a(lz paramAnonymouslz)
        throws RemoteException
      {
        paramAnonymouslz.removeLocationUpdates(paramLocationListener);
        b(Status.Jv);
      }
    });
  }
  
  public PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, final LocationRequest paramLocationRequest, final PendingIntent paramPendingIntent)
  {
    paramGoogleApiClient.b(new a(paramLocationRequest)
    {
      protected void a(lz paramAnonymouslz)
        throws RemoteException
      {
        paramAnonymouslz.requestLocationUpdates(paramLocationRequest, paramPendingIntent);
        b(Status.Jv);
      }
    });
  }
  
  public PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, final LocationRequest paramLocationRequest, final LocationListener paramLocationListener)
  {
    paramGoogleApiClient.b(new a(paramLocationRequest)
    {
      protected void a(lz paramAnonymouslz)
        throws RemoteException
      {
        paramAnonymouslz.requestLocationUpdates(paramLocationRequest, paramLocationListener, null);
        b(Status.Jv);
      }
    });
  }
  
  public PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, final LocationRequest paramLocationRequest, final LocationListener paramLocationListener, final Looper paramLooper)
  {
    paramGoogleApiClient.b(new a(paramLocationRequest)
    {
      protected void a(lz paramAnonymouslz)
        throws RemoteException
      {
        paramAnonymouslz.requestLocationUpdates(paramLocationRequest, paramLocationListener, paramLooper);
        b(Status.Jv);
      }
    });
  }
  
  public PendingResult<Status> setMockLocation(GoogleApiClient paramGoogleApiClient, final Location paramLocation)
  {
    paramGoogleApiClient.b(new a(paramLocation)
    {
      protected void a(lz paramAnonymouslz)
        throws RemoteException
      {
        paramAnonymouslz.setMockLocation(paramLocation);
        b(Status.Jv);
      }
    });
  }
  
  public PendingResult<Status> setMockMode(GoogleApiClient paramGoogleApiClient, final boolean paramBoolean)
  {
    paramGoogleApiClient.b(new a(paramBoolean)
    {
      protected void a(lz paramAnonymouslz)
        throws RemoteException
      {
        paramAnonymouslz.setMockMode(paramBoolean);
        b(Status.Jv);
      }
    });
  }
  
  private static abstract class a
    extends LocationServices.a<Status>
  {
    public Status d(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\lu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */