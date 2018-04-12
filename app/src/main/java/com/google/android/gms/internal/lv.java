package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.LocationClient.OnAddGeofencesResultListener;
import com.google.android.gms.location.LocationClient.OnRemoveGeofencesResultListener;
import com.google.android.gms.location.LocationServices.a;
import com.google.android.gms.location.LocationStatusCodes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class lv
  implements GeofencingApi
{
  public PendingResult<Status> addGeofences(GoogleApiClient paramGoogleApiClient, final List<Geofence> paramList, final PendingIntent paramPendingIntent)
  {
    ArrayList localArrayList;
    if (paramList != null)
    {
      localArrayList = new ArrayList(paramList.size());
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Geofence localGeofence = (Geofence)paramList.next();
        o.b(localGeofence instanceof mc, "Geofence must be created using Geofence.Builder.");
        localArrayList.add((mc)localGeofence);
      }
    }
    for (paramList = localArrayList;; paramList = null) {
      paramGoogleApiClient.b(new a(paramList)
      {
        protected void a(lz paramAnonymouslz)
          throws RemoteException
        {
          LocationClient.OnAddGeofencesResultListener local1 = new LocationClient.OnAddGeofencesResultListener()
          {
            public void onAddGeofencesResult(int paramAnonymous2Int, String[] paramAnonymous2ArrayOfString)
            {
              lv.1.this.b(LocationStatusCodes.eg(paramAnonymous2Int));
            }
          };
          paramAnonymouslz.addGeofences(paramList, paramPendingIntent, local1);
        }
      });
    }
  }
  
  public PendingResult<Status> removeGeofences(GoogleApiClient paramGoogleApiClient, final PendingIntent paramPendingIntent)
  {
    paramGoogleApiClient.b(new a(paramPendingIntent)
    {
      protected void a(lz paramAnonymouslz)
        throws RemoteException
      {
        LocationClient.OnRemoveGeofencesResultListener local1 = new LocationClient.OnRemoveGeofencesResultListener()
        {
          public void onRemoveGeofencesByPendingIntentResult(int paramAnonymous2Int, PendingIntent paramAnonymous2PendingIntent)
          {
            lv.2.this.b(LocationStatusCodes.eg(paramAnonymous2Int));
          }
          
          public void onRemoveGeofencesByRequestIdsResult(int paramAnonymous2Int, String[] paramAnonymous2ArrayOfString)
          {
            Log.wtf("GeofencingImpl", "Request ID callback shouldn't have been called");
          }
        };
        paramAnonymouslz.removeGeofences(paramPendingIntent, local1);
      }
    });
  }
  
  public PendingResult<Status> removeGeofences(GoogleApiClient paramGoogleApiClient, final List<String> paramList)
  {
    paramGoogleApiClient.b(new a(paramList)
    {
      protected void a(lz paramAnonymouslz)
        throws RemoteException
      {
        LocationClient.OnRemoveGeofencesResultListener local1 = new LocationClient.OnRemoveGeofencesResultListener()
        {
          public void onRemoveGeofencesByPendingIntentResult(int paramAnonymous2Int, PendingIntent paramAnonymous2PendingIntent)
          {
            Log.wtf("GeofencingImpl", "PendingIntent callback shouldn't have been called");
          }
          
          public void onRemoveGeofencesByRequestIdsResult(int paramAnonymous2Int, String[] paramAnonymous2ArrayOfString)
          {
            lv.3.this.b(LocationStatusCodes.eg(paramAnonymous2Int));
          }
        };
        paramAnonymouslz.removeGeofences(paramList, local1);
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


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\lv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */