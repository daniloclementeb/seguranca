package com.google.android.gms.games.internal.notification;

import com.google.android.gms.common.data.DataBuffer;

public final class GameNotificationBuffer
  extends DataBuffer<GameNotification>
{
  public GameNotification dO(int paramInt)
  {
    return new GameNotificationRef(this.II, paramInt);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\games\internal\notification\GameNotificationBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */