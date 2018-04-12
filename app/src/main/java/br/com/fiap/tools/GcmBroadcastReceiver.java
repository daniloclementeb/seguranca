package br.com.fiap.tools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GcmBroadcastReceiver
  extends WakefulBroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    startWakefulService(paramContext, paramIntent.setComponent(new ComponentName(paramContext.getPackageName(), GcmIntentService.class.getName())));
    setResultCode(-1);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\tools\GcmBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */