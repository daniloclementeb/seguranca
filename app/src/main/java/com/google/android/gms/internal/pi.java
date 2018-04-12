package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class pi<M extends ph<M>, T>
{
  protected final Class<T> awK;
  protected final boolean awL;
  protected final int tag;
  protected final int type;
  
  private pi(int paramInt1, Class<T> paramClass, int paramInt2, boolean paramBoolean)
  {
    this.type = paramInt1;
    this.awK = paramClass;
    this.tag = paramInt2;
    this.awL = paramBoolean;
  }
  
  public static <M extends ph<M>, T extends pn> pi<M, T> a(int paramInt1, Class<T> paramClass, int paramInt2)
  {
    return new pi(paramInt1, paramClass, paramInt2, false);
  }
  
  private T m(List<pp> paramList)
  {
    int j = 0;
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramList.size())
    {
      localObject = (pp)paramList.get(i);
      if (((pp)localObject).awV.length != 0) {
        a((pp)localObject, localArrayList);
      }
      i += 1;
    }
    int k = localArrayList.size();
    if (k == 0)
    {
      paramList = null;
      return paramList;
    }
    Object localObject = this.awK.cast(Array.newInstance(this.awK.getComponentType(), k));
    i = j;
    for (;;)
    {
      paramList = (List<pp>)localObject;
      if (i >= k) {
        break;
      }
      Array.set(localObject, i, localArrayList.get(i));
      i += 1;
    }
  }
  
  private T n(List<pp> paramList)
  {
    if (paramList.isEmpty()) {
      return null;
    }
    paramList = (pp)paramList.get(paramList.size() - 1);
    return (T)this.awK.cast(u(pf.p(paramList.awV)));
  }
  
  int A(Object paramObject)
  {
    if (this.awL) {
      return B(paramObject);
    }
    return C(paramObject);
  }
  
  protected int B(Object paramObject)
  {
    int j = 0;
    int m = Array.getLength(paramObject);
    int i = 0;
    while (i < m)
    {
      int k = j;
      if (Array.get(paramObject, i) != null) {
        k = j + C(Array.get(paramObject, i));
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  protected int C(Object paramObject)
  {
    int i = pq.gI(this.tag);
    switch (this.type)
    {
    default: 
      throw new IllegalArgumentException("Unknown type " + this.type);
    case 10: 
      return pg.b(i, (pn)paramObject);
    }
    return pg.c(i, (pn)paramObject);
  }
  
  protected void a(pp parampp, List<Object> paramList)
  {
    paramList.add(u(pf.p(parampp.awV)));
  }
  
  void a(Object paramObject, pg parampg)
    throws IOException
  {
    if (this.awL)
    {
      c(paramObject, parampg);
      return;
    }
    b(paramObject, parampg);
  }
  
  protected void b(Object paramObject, pg parampg)
  {
    for (;;)
    {
      try
      {
        parampg.gA(this.tag);
        switch (this.type)
        {
        case 10: 
          throw new IllegalArgumentException("Unknown type " + this.type);
        }
      }
      catch (IOException paramObject)
      {
        throw new IllegalStateException((Throwable)paramObject);
      }
      paramObject = (pn)paramObject;
      int i = pq.gI(this.tag);
      parampg.b((pn)paramObject);
      parampg.w(i, 4);
      return;
      parampg.c((pn)paramObject);
      return;
    }
  }
  
  protected void c(Object paramObject, pg parampg)
  {
    int j = Array.getLength(paramObject);
    int i = 0;
    while (i < j)
    {
      Object localObject = Array.get(paramObject, i);
      if (localObject != null) {
        b(localObject, parampg);
      }
      i += 1;
    }
  }
  
  final T l(List<pp> paramList)
  {
    if (paramList == null) {
      return null;
    }
    if (this.awL) {
      return (T)m(paramList);
    }
    return (T)n(paramList);
  }
  
  protected Object u(pf parampf)
  {
    Class localClass;
    if (this.awL) {
      localClass = this.awK.getComponentType();
    }
    for (;;)
    {
      try
      {
        switch (this.type)
        {
        case 10: 
          throw new IllegalArgumentException("Unknown type " + this.type);
        }
      }
      catch (InstantiationException parampf)
      {
        throw new IllegalArgumentException("Error creating instance of class " + localClass, parampf);
        localClass = this.awK;
        continue;
        pn localpn = (pn)localClass.newInstance();
        parampf.a(localpn, pq.gI(this.tag));
        return localpn;
        localpn = (pn)localClass.newInstance();
        parampf.a(localpn);
        return localpn;
      }
      catch (IllegalAccessException parampf)
      {
        throw new IllegalArgumentException("Error creating instance of class " + localClass, parampf);
      }
      catch (IOException parampf)
      {
        throw new IllegalArgumentException("Error reading extension field", parampf);
      }
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\pi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */