package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class pk
{
  private pi<?, ?> awQ;
  private Object awR;
  private List<pp> awS = new ArrayList();
  
  private byte[] toByteArray()
    throws IOException
  {
    byte[] arrayOfByte = new byte[c()];
    a(pg.q(arrayOfByte));
    return arrayOfByte;
  }
  
  void a(pg parampg)
    throws IOException
  {
    if (this.awR != null) {
      this.awQ.a(this.awR, parampg);
    }
    for (;;)
    {
      return;
      Iterator localIterator = this.awS.iterator();
      while (localIterator.hasNext()) {
        ((pp)localIterator.next()).a(parampg);
      }
    }
  }
  
  void a(pp parampp)
  {
    this.awS.add(parampp);
  }
  
  <T> T b(pi<?, T> parampi)
  {
    if (this.awR != null)
    {
      if (this.awQ != parampi) {
        throw new IllegalStateException("Tried to getExtension with a differernt Extension.");
      }
    }
    else
    {
      this.awQ = parampi;
      this.awR = parampi.l(this.awS);
      this.awS = null;
    }
    return (T)this.awR;
  }
  
  int c()
  {
    int j;
    if (this.awR != null)
    {
      j = this.awQ.A(this.awR);
      return j;
    }
    Iterator localIterator = this.awS.iterator();
    for (int i = 0;; i = ((pp)localIterator.next()).c() + i)
    {
      j = i;
      if (!localIterator.hasNext()) {
        break;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!(paramObject instanceof pk));
      paramObject = (pk)paramObject;
      if ((this.awR == null) || (((pk)paramObject).awR == null)) {
        break;
      }
      bool1 = bool2;
    } while (this.awQ != ((pk)paramObject).awQ);
    if (!this.awQ.awK.isArray()) {
      return this.awR.equals(((pk)paramObject).awR);
    }
    if ((this.awR instanceof byte[])) {
      return Arrays.equals((byte[])this.awR, (byte[])((pk)paramObject).awR);
    }
    if ((this.awR instanceof int[])) {
      return Arrays.equals((int[])this.awR, (int[])((pk)paramObject).awR);
    }
    if ((this.awR instanceof long[])) {
      return Arrays.equals((long[])this.awR, (long[])((pk)paramObject).awR);
    }
    if ((this.awR instanceof float[])) {
      return Arrays.equals((float[])this.awR, (float[])((pk)paramObject).awR);
    }
    if ((this.awR instanceof double[])) {
      return Arrays.equals((double[])this.awR, (double[])((pk)paramObject).awR);
    }
    if ((this.awR instanceof boolean[])) {
      return Arrays.equals((boolean[])this.awR, (boolean[])((pk)paramObject).awR);
    }
    return Arrays.deepEquals((Object[])this.awR, (Object[])((pk)paramObject).awR);
    if ((this.awS != null) && (((pk)paramObject).awS != null)) {
      return this.awS.equals(((pk)paramObject).awS);
    }
    try
    {
      bool1 = Arrays.equals(toByteArray(), ((pk)paramObject).toByteArray());
      return bool1;
    }
    catch (IOException paramObject)
    {
      throw new IllegalStateException((Throwable)paramObject);
    }
  }
  
  public int hashCode()
  {
    try
    {
      int i = Arrays.hashCode(toByteArray());
      return i + 527;
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\pk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */