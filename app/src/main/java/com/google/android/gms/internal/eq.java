package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;

@ez
public final class eq
  extends el.a
{
  private final PlayStorePurchaseListener oD;
  
  public eq(PlayStorePurchaseListener paramPlayStorePurchaseListener)
  {
    this.oD = paramPlayStorePurchaseListener;
  }
  
  public void a(ek paramek)
  {
    this.oD.onInAppPurchaseFinished(new eo(paramek));
  }
  
  public boolean isValidPurchase(String paramString)
  {
    return this.oD.isValidPurchase(paramString);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\eq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */