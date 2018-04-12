package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class LeaderboardScoreBuffer
  extends DataBuffer<LeaderboardScore>
{
  private final LeaderboardScoreBufferHeader aby;
  
  public LeaderboardScoreBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
    this.aby = new LeaderboardScoreBufferHeader(paramDataHolder.gy());
  }
  
  public LeaderboardScore get(int paramInt)
  {
    return new LeaderboardScoreRef(this.II, paramInt);
  }
  
  public LeaderboardScoreBufferHeader lA()
  {
    return this.aby;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\games\leaderboard\LeaderboardScoreBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */