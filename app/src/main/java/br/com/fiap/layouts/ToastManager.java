package br.com.fiap.layouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastManager
{
  public static void show(Context paramContext, String paramString)
  {
    View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130903118, null);
    ((TextView)localView.findViewById(2131427572)).setText(paramString);
    paramContext = new Toast(paramContext);
    paramContext.setGravity(17, 0, 0);
    paramContext.setDuration(1);
    paramContext.setView(localView);
    paramContext.show();
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\layouts\ToastManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */