package com.google.android.gms.ads.purchase;

public abstract interface PlayStorePurchaseListener
{
  public abstract boolean isValidPurchase(String paramString);
  
  public abstract void onInAppPurchaseFinished(InAppPurchaseResult paramInAppPurchaseResult);
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\ads\purchase\PlayStorePurchaseListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */