package com.google.android.gms.internal;

import java.io.IOException;

public abstract class ph<M extends ph<M>>
  extends pn
{
  protected pj awJ;
  
  public final <T> T a(pi<M, T> parampi)
  {
    if (this.awJ == null) {}
    pk localpk;
    do
    {
      return null;
      localpk = this.awJ.gE(pq.gI(parampi.tag));
    } while (localpk == null);
    return (T)localpk.b(parampi);
  }
  
  public void a(pg parampg)
    throws IOException
  {
    if (this.awJ == null) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < this.awJ.size())
      {
        this.awJ.gF(i).a(parampg);
        i += 1;
      }
    }
  }
  
  protected final boolean a(pf parampf, int paramInt)
    throws IOException
  {
    int i = parampf.getPosition();
    if (!parampf.gn(paramInt)) {
      return false;
    }
    int j = pq.gI(paramInt);
    pp localpp = new pp(paramInt, parampf.r(i, parampf.getPosition() - i));
    parampf = null;
    if (this.awJ == null) {
      this.awJ = new pj();
    }
    for (;;)
    {
      Object localObject = parampf;
      if (parampf == null)
      {
        localObject = new pk();
        this.awJ.a(j, (pk)localObject);
      }
      ((pk)localObject).a(localpp);
      return true;
      parampf = this.awJ.gE(j);
    }
  }
  
  protected final boolean a(M paramM)
  {
    if ((this.awJ == null) || (this.awJ.isEmpty())) {
      return (paramM.awJ == null) || (paramM.awJ.isEmpty());
    }
    return this.awJ.equals(paramM.awJ);
  }
  
  protected int c()
  {
    int j = 0;
    if (this.awJ != null)
    {
      int i = 0;
      for (;;)
      {
        k = i;
        if (j >= this.awJ.size()) {
          break;
        }
        i += this.awJ.gF(j).c();
        j += 1;
      }
    }
    int k = 0;
    return k;
  }
  
  protected final int qz()
  {
    if ((this.awJ == null) || (this.awJ.isEmpty())) {
      return 0;
    }
    return this.awJ.hashCode();
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\ph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */