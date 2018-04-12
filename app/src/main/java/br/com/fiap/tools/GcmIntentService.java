package br.com.fiap.tools;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import br.com.fiap.fiapmobile.PainelActivity;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GcmIntentService
  extends IntentService
{
  public static final int NOTIFICATION_ID = 1;
  private String TAG = "FIAP_GCM";
  NotificationCompat.Builder builder;
  private NotificationManager mNotificationManager;
  
  public GcmIntentService()
  {
    super("GcmIntentService");
  }
  
  private void sendNotification(String paramString1, String paramString2)
  {
    if (paramString2.equalsIgnoreCase("NOTAS"))
    {
      this.mNotificationManager = ((NotificationManager)getSystemService("notification"));
      paramString2 = new Intent(this, PainelActivity.class);
      paramString2.putExtra("push", "notas");
      paramString2 = PendingIntent.getActivity(this, 0, paramString2, 268435456);
      paramString1 = new NotificationCompat.Builder(this).setSmallIcon(2130837676).setContentTitle("FIAPP - Notas").setStyle(new NotificationCompat.BigTextStyle().bigText(paramString1)).setContentText(paramString1);
      paramString1.setVibrate(new long[] { 500L, 500L });
      paramString1.setLights(-65536, 1000, 1000);
      paramString1.setAutoCancel(true);
      paramString1.setContentIntent(paramString2);
      this.mNotificationManager.notify(1, paramString1.build());
      return;
    }
    if (paramString2.equalsIgnoreCase("AVISOS"))
    {
      this.mNotificationManager = ((NotificationManager)getSystemService("notification"));
      paramString2 = new Intent(this, PainelActivity.class);
      paramString2.putExtra("push", "avisos");
      paramString2 = PendingIntent.getActivity(this, 0, paramString2, 268435456);
      paramString1 = new NotificationCompat.Builder(this).setSmallIcon(2130837676).setContentTitle("FIAPP - Avisos").setStyle(new NotificationCompat.BigTextStyle().bigText(paramString1)).setContentText(paramString1);
      paramString1.setVibrate(new long[] { 500L, 500L });
      paramString1.setLights(-65536, 1000, 1000);
      paramString1.setAutoCancel(true);
      paramString1.setContentIntent(paramString2);
      this.mNotificationManager.notify(1, paramString1.build());
      return;
    }
    this.mNotificationManager = ((NotificationManager)getSystemService("notification"));
    paramString2 = PendingIntent.getActivity(this, 0, new Intent(this, PainelActivity.class), 268435456);
    paramString1 = new NotificationCompat.Builder(this).setSmallIcon(2130837676).setContentTitle("FIAPP").setStyle(new NotificationCompat.BigTextStyle().bigText(paramString1)).setContentText(paramString1);
    paramString1.setVibrate(new long[] { 500L, 500L });
    paramString1.setLights(-65536, 1000, 1000);
    paramString1.setAutoCancel(true);
    paramString1.setContentIntent(paramString2);
    this.mNotificationManager.notify(1, paramString1.build());
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    String str1 = GoogleCloudMessaging.getInstance(this).getMessageType(paramIntent);
    if (!localBundle.isEmpty())
    {
      if (!"send_error".equals(str1)) {
        break label63;
      }
      Log.i(this.TAG, "ERRO: " + localBundle.toString());
    }
    for (;;)
    {
      GcmBroadcastReceiver.completeWakefulIntent(paramIntent);
      return;
      label63:
      if ("deleted_messages".equals(str1))
      {
        Log.i(this.TAG, "DELETED: " + localBundle.toString());
      }
      else if ("gcm".equals(str1))
      {
        str1 = localBundle.getString("message");
        String str2 = localBundle.getString("collapse_key");
        sendNotification(str1, str2);
        Log.i(this.TAG, "Received: " + localBundle.toString());
        Log.i(this.TAG, "Mensagem: " + str1);
        Log.i(this.TAG, "Collapse: " + str2);
      }
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\tools\GcmIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */