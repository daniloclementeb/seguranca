package com.google.android.gms.games.internal.constants;

public final class LeaderboardCollection
{
  public static String dH(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unknown leaderboard collection: " + paramInt);
    case 0: 
      return "PUBLIC";
    }
    return "SOCIAL";
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\games\internal\constants\LeaderboardCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */