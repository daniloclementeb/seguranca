package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class pp
{
  final byte[] awV;
  final int tag;
  
  pp(int paramInt, byte[] paramArrayOfByte)
  {
    this.tag = paramInt;
    this.awV = paramArrayOfByte;
  }
  
  void a(pg parampg)
    throws IOException
  {
    parampg.gA(this.tag);
    parampg.t(this.awV);
  }
  
  int c()
  {
    return 0 + pg.gB(this.tag) + this.awV.length;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof pp)) {
        return false;
      }
      paramObject = (pp)paramObject;
    } while ((this.tag == ((pp)paramObject).tag) && (Arrays.equals(this.awV, ((pp)paramObject).awV)));
    return false;
  }
  
  public int hashCode()
  {
    return (this.tag + 527) * 31 + Arrays.hashCode(this.awV);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\pp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */