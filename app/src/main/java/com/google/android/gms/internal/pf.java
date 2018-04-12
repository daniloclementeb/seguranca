package com.google.android.gms.internal;

import java.io.IOException;

public final class pf
{
  private int awA;
  private int awB;
  private int awC;
  private int awD;
  private int awE = Integer.MAX_VALUE;
  private int awF;
  private int awG = 64;
  private int awH = 67108864;
  private int awz;
  private final byte[] buffer;
  
  private pf(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.buffer = paramArrayOfByte;
    this.awz = paramInt1;
    this.awA = (paramInt1 + paramInt2);
    this.awC = paramInt1;
  }
  
  public static long A(long paramLong)
  {
    return paramLong >>> 1 ^ -(1L & paramLong);
  }
  
  public static pf a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new pf(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public static int go(int paramInt)
  {
    return paramInt >>> 1 ^ -(paramInt & 0x1);
  }
  
  public static pf p(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  private void qt()
  {
    this.awA += this.awB;
    int i = this.awA;
    if (i > this.awE)
    {
      this.awB = (i - this.awE);
      this.awA -= this.awB;
      return;
    }
    this.awB = 0;
  }
  
  public void a(pn parampn)
    throws IOException
  {
    int i = qp();
    if (this.awF >= this.awG) {
      throw pm.qG();
    }
    i = gp(i);
    this.awF += 1;
    parampn.b(this);
    gm(0);
    this.awF -= 1;
    gq(i);
  }
  
  public void a(pn parampn, int paramInt)
    throws IOException
  {
    if (this.awF >= this.awG) {
      throw pm.qG();
    }
    this.awF += 1;
    parampn.b(this);
    gm(pq.x(paramInt, 4));
    this.awF -= 1;
  }
  
  public int getPosition()
  {
    return this.awC - this.awz;
  }
  
  public void gm(int paramInt)
    throws pm
  {
    if (this.awD != paramInt) {
      throw pm.qE();
    }
  }
  
  public boolean gn(int paramInt)
    throws IOException
  {
    switch (pq.gH(paramInt))
    {
    default: 
      throw pm.qF();
    case 0: 
      ql();
      return true;
    case 1: 
      qs();
      return true;
    case 2: 
      gt(qp());
      return true;
    case 3: 
      qj();
      gm(pq.x(pq.gI(paramInt), 4));
      return true;
    case 4: 
      return false;
    }
    qr();
    return true;
  }
  
  public int gp(int paramInt)
    throws pm
  {
    if (paramInt < 0) {
      throw pm.qB();
    }
    paramInt = this.awC + paramInt;
    int i = this.awE;
    if (paramInt > i) {
      throw pm.qA();
    }
    this.awE = paramInt;
    qt();
    return i;
  }
  
  public void gq(int paramInt)
  {
    this.awE = paramInt;
    qt();
  }
  
  public void gr(int paramInt)
  {
    if (paramInt > this.awC - this.awz) {
      throw new IllegalArgumentException("Position " + paramInt + " is beyond current " + (this.awC - this.awz));
    }
    if (paramInt < 0) {
      throw new IllegalArgumentException("Bad position " + paramInt);
    }
    this.awC = (this.awz + paramInt);
  }
  
  public byte[] gs(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw pm.qB();
    }
    if (this.awC + paramInt > this.awE)
    {
      gt(this.awE - this.awC);
      throw pm.qA();
    }
    if (paramInt <= this.awA - this.awC)
    {
      byte[] arrayOfByte = new byte[paramInt];
      System.arraycopy(this.buffer, this.awC, arrayOfByte, 0, paramInt);
      this.awC += paramInt;
      return arrayOfByte;
    }
    throw pm.qA();
  }
  
  public void gt(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw pm.qB();
    }
    if (this.awC + paramInt > this.awE)
    {
      gt(this.awE - this.awC);
      throw pm.qA();
    }
    if (paramInt <= this.awA - this.awC)
    {
      this.awC += paramInt;
      return;
    }
    throw pm.qA();
  }
  
  public int qi()
    throws IOException
  {
    if (qv())
    {
      this.awD = 0;
      return 0;
    }
    this.awD = qp();
    if (this.awD == 0) {
      throw pm.qD();
    }
    return this.awD;
  }
  
  public void qj()
    throws IOException
  {
    int i;
    do
    {
      i = qi();
    } while ((i != 0) && (gn(i)));
  }
  
  public long qk()
    throws IOException
  {
    return qq();
  }
  
  public int ql()
    throws IOException
  {
    return qp();
  }
  
  public boolean qm()
    throws IOException
  {
    return qp() != 0;
  }
  
  public int qn()
    throws IOException
  {
    return go(qp());
  }
  
  public long qo()
    throws IOException
  {
    return A(qq());
  }
  
  public int qp()
    throws IOException
  {
    int i = qw();
    if (i >= 0) {}
    int k;
    do
    {
      return i;
      i &= 0x7F;
      j = qw();
      if (j >= 0) {
        return i | j << 7;
      }
      i |= (j & 0x7F) << 7;
      j = qw();
      if (j >= 0) {
        return i | j << 14;
      }
      i |= (j & 0x7F) << 14;
      k = qw();
      if (k >= 0) {
        return i | k << 21;
      }
      j = qw();
      k = i | (k & 0x7F) << 21 | j << 28;
      i = k;
    } while (j >= 0);
    int j = 0;
    for (;;)
    {
      if (j >= 5) {
        break label133;
      }
      i = k;
      if (qw() >= 0) {
        break;
      }
      j += 1;
    }
    label133:
    throw pm.qC();
  }
  
  public long qq()
    throws IOException
  {
    int i = 0;
    long l = 0L;
    while (i < 64)
    {
      int j = qw();
      l |= (j & 0x7F) << i;
      if ((j & 0x80) == 0) {
        return l;
      }
      i += 7;
    }
    throw pm.qC();
  }
  
  public int qr()
    throws IOException
  {
    return qw() & 0xFF | (qw() & 0xFF) << 8 | (qw() & 0xFF) << 16 | (qw() & 0xFF) << 24;
  }
  
  public long qs()
    throws IOException
  {
    int i = qw();
    int j = qw();
    int k = qw();
    int m = qw();
    int n = qw();
    int i1 = qw();
    int i2 = qw();
    int i3 = qw();
    long l = i;
    return (j & 0xFF) << 8 | l & 0xFF | (k & 0xFF) << 16 | (m & 0xFF) << 24 | (n & 0xFF) << 32 | (i1 & 0xFF) << 40 | (i2 & 0xFF) << 48 | (i3 & 0xFF) << 56;
  }
  
  public int qu()
  {
    if (this.awE == Integer.MAX_VALUE) {
      return -1;
    }
    int i = this.awC;
    return this.awE - i;
  }
  
  public boolean qv()
  {
    return this.awC == this.awA;
  }
  
  public byte qw()
    throws IOException
  {
    if (this.awC == this.awA) {
      throw pm.qA();
    }
    byte[] arrayOfByte = this.buffer;
    int i = this.awC;
    this.awC = (i + 1);
    return arrayOfByte[i];
  }
  
  public byte[] r(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return pq.axd;
    }
    byte[] arrayOfByte = new byte[paramInt2];
    int i = this.awz;
    System.arraycopy(this.buffer, i + paramInt1, arrayOfByte, 0, paramInt2);
    return arrayOfByte;
  }
  
  public byte[] readBytes()
    throws IOException
  {
    int i = qp();
    if ((i <= this.awA - this.awC) && (i > 0))
    {
      byte[] arrayOfByte = new byte[i];
      System.arraycopy(this.buffer, this.awC, arrayOfByte, 0, i);
      this.awC = (i + this.awC);
      return arrayOfByte;
    }
    return gs(i);
  }
  
  public double readDouble()
    throws IOException
  {
    return Double.longBitsToDouble(qs());
  }
  
  public float readFloat()
    throws IOException
  {
    return Float.intBitsToFloat(qr());
  }
  
  public String readString()
    throws IOException
  {
    int i = qp();
    if ((i <= this.awA - this.awC) && (i > 0))
    {
      String str = new String(this.buffer, this.awC, i, "UTF-8");
      this.awC = (i + this.awC);
      return str;
    }
    return new String(gs(i), "UTF-8");
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\pf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */