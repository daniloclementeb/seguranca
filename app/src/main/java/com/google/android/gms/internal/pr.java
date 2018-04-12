package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public abstract interface pr
{
  public static final class a
    extends ph<a>
  {
    public String[] axe;
    public String[] axf;
    public int[] axg;
    
    public a()
    {
      qJ();
    }
    
    public void a(pg parampg)
      throws IOException
    {
      int j = 0;
      int i;
      String str;
      if ((this.axe != null) && (this.axe.length > 0))
      {
        i = 0;
        while (i < this.axe.length)
        {
          str = this.axe[i];
          if (str != null) {
            parampg.b(1, str);
          }
          i += 1;
        }
      }
      if ((this.axf != null) && (this.axf.length > 0))
      {
        i = 0;
        while (i < this.axf.length)
        {
          str = this.axf[i];
          if (str != null) {
            parampg.b(2, str);
          }
          i += 1;
        }
      }
      if ((this.axg != null) && (this.axg.length > 0))
      {
        i = j;
        while (i < this.axg.length)
        {
          parampg.s(3, this.axg[i]);
          i += 1;
        }
      }
      super.a(parampg);
    }
    
    protected int c()
    {
      int i2 = 0;
      int i1 = super.c();
      int j;
      int k;
      String str;
      int n;
      int m;
      if ((this.axe != null) && (this.axe.length > 0))
      {
        i = 0;
        j = 0;
        for (k = 0; i < this.axe.length; k = m)
        {
          str = this.axe[i];
          n = j;
          m = k;
          if (str != null)
          {
            m = k + 1;
            n = j + pg.di(str);
          }
          i += 1;
          j = n;
        }
      }
      for (int i = i1 + j + k * 1;; i = i1)
      {
        j = i;
        if (this.axf != null)
        {
          j = i;
          if (this.axf.length > 0)
          {
            j = 0;
            k = 0;
            for (m = 0; j < this.axf.length; m = n)
            {
              str = this.axf[j];
              i1 = k;
              n = m;
              if (str != null)
              {
                n = m + 1;
                i1 = k + pg.di(str);
              }
              j += 1;
              k = i1;
            }
            j = i + k + m * 1;
          }
        }
        i = j;
        if (this.axg != null)
        {
          i = j;
          if (this.axg.length > 0)
          {
            k = 0;
            i = i2;
            while (i < this.axg.length)
            {
              k += pg.gw(this.axg[i]);
              i += 1;
            }
            i = j + k + this.axg.length * 1;
          }
        }
        return i;
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
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof a));
            paramObject = (a)paramObject;
            bool1 = bool2;
          } while (!pl.equals(this.axe, ((a)paramObject).axe));
          bool1 = bool2;
        } while (!pl.equals(this.axf, ((a)paramObject).axf));
        bool1 = bool2;
      } while (!pl.equals(this.axg, ((a)paramObject).axg));
      return a((ph)paramObject);
    }
    
    public int hashCode()
    {
      return (((pl.hashCode(this.axe) + 527) * 31 + pl.hashCode(this.axf)) * 31 + pl.hashCode(this.axg)) * 31 + qz();
    }
    
    public a qJ()
    {
      this.axe = pq.axb;
      this.axf = pq.axb;
      this.axg = pq.awW;
      this.awJ = null;
      this.awU = -1;
      return this;
    }
    
    public a v(pf parampf)
      throws IOException
    {
      for (;;)
      {
        int i = parampf.qi();
        int j;
        Object localObject;
        switch (i)
        {
        default: 
          if (a(parampf, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          j = pq.b(parampf, 10);
          if (this.axe == null) {}
          for (i = 0;; i = this.axe.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.axe, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = parampf.readString();
              parampf.qi();
              j += 1;
            }
          }
          localObject[j] = parampf.readString();
          this.axe = ((String[])localObject);
          break;
        case 18: 
          j = pq.b(parampf, 18);
          if (this.axf == null) {}
          for (i = 0;; i = this.axf.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.axf, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = parampf.readString();
              parampf.qi();
              j += 1;
            }
          }
          localObject[j] = parampf.readString();
          this.axf = ((String[])localObject);
          break;
        case 24: 
          j = pq.b(parampf, 24);
          if (this.axg == null) {}
          for (i = 0;; i = this.axg.length)
          {
            localObject = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.axg, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = parampf.ql();
              parampf.qi();
              j += 1;
            }
          }
          localObject[j] = parampf.ql();
          this.axg = ((int[])localObject);
          break;
        case 26: 
          int k = parampf.gp(parampf.qp());
          i = parampf.getPosition();
          j = 0;
          while (parampf.qu() > 0)
          {
            parampf.ql();
            j += 1;
          }
          parampf.gr(i);
          if (this.axg == null) {}
          for (i = 0;; i = this.axg.length)
          {
            localObject = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.axg, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length)
            {
              localObject[j] = parampf.ql();
              j += 1;
            }
          }
          this.axg = ((int[])localObject);
          parampf.gq(k);
        }
      }
    }
  }
  
  public static final class b
    extends ph<b>
  {
    public int axh;
    public String axi;
    public String version;
    
    public b()
    {
      qK();
    }
    
    public void a(pg parampg)
      throws IOException
    {
      if (this.axh != 0) {
        parampg.s(1, this.axh);
      }
      if (!this.axi.equals("")) {
        parampg.b(2, this.axi);
      }
      if (!this.version.equals("")) {
        parampg.b(3, this.version);
      }
      super.a(parampg);
    }
    
    protected int c()
    {
      int j = super.c();
      int i = j;
      if (this.axh != 0) {
        i = j + pg.u(1, this.axh);
      }
      j = i;
      if (!this.axi.equals("")) {
        j = i + pg.j(2, this.axi);
      }
      i = j;
      if (!this.version.equals("")) {
        i = j + pg.j(3, this.version);
      }
      return i;
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
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof b));
            paramObject = (b)paramObject;
            bool1 = bool2;
          } while (this.axh != ((b)paramObject).axh);
          if (this.axi != null) {
            break;
          }
          bool1 = bool2;
        } while (((b)paramObject).axi != null);
        if (this.version != null) {
          break label92;
        }
        bool1 = bool2;
      } while (((b)paramObject).version != null);
      label92:
      while (this.version.equals(((b)paramObject).version))
      {
        return a((ph)paramObject);
        if (this.axi.equals(((b)paramObject).axi)) {
          break;
        }
        return false;
      }
      return false;
    }
    
    public int hashCode()
    {
      int j = 0;
      int k = this.axh;
      int i;
      if (this.axi == null)
      {
        i = 0;
        if (this.version != null) {
          break label58;
        }
      }
      for (;;)
      {
        return ((i + (k + 527) * 31) * 31 + j) * 31 + qz();
        i = this.axi.hashCode();
        break;
        label58:
        j = this.version.hashCode();
      }
    }
    
    public b qK()
    {
      this.axh = 0;
      this.axi = "";
      this.version = "";
      this.awJ = null;
      this.awU = -1;
      return this;
    }
    
    public b w(pf parampf)
      throws IOException
    {
      for (;;)
      {
        int i = parampf.qi();
        switch (i)
        {
        default: 
          if (a(parampf, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          i = parampf.ql();
          switch (i)
          {
          default: 
            break;
          case 0: 
          case 1: 
          case 2: 
          case 3: 
          case 4: 
          case 5: 
          case 6: 
          case 7: 
          case 8: 
          case 9: 
          case 10: 
          case 11: 
          case 12: 
          case 13: 
          case 14: 
          case 15: 
          case 16: 
          case 17: 
          case 18: 
          case 19: 
          case 20: 
          case 21: 
          case 22: 
            this.axh = i;
          }
          break;
        case 18: 
          this.axi = parampf.readString();
          break;
        case 26: 
          this.version = parampf.readString();
        }
      }
    }
  }
  
  public static final class c
    extends ph<c>
  {
    public long axj;
    public int axk;
    public int axl;
    public boolean axm;
    public pr.d[] axn;
    public pr.b axo;
    public byte[] axp;
    public byte[] axq;
    public byte[] axr;
    public pr.a axs;
    public String axt;
    public String tag;
    
    public c()
    {
      qL();
    }
    
    public void a(pg parampg)
      throws IOException
    {
      if (this.axj != 0L) {
        parampg.b(1, this.axj);
      }
      if (!this.tag.equals("")) {
        parampg.b(2, this.tag);
      }
      if ((this.axn != null) && (this.axn.length > 0))
      {
        int i = 0;
        while (i < this.axn.length)
        {
          pr.d locald = this.axn[i];
          if (locald != null) {
            parampg.a(3, locald);
          }
          i += 1;
        }
      }
      if (!Arrays.equals(this.axp, pq.axd)) {
        parampg.a(6, this.axp);
      }
      if (this.axs != null) {
        parampg.a(7, this.axs);
      }
      if (!Arrays.equals(this.axq, pq.axd)) {
        parampg.a(8, this.axq);
      }
      if (this.axo != null) {
        parampg.a(9, this.axo);
      }
      if (this.axm) {
        parampg.b(10, this.axm);
      }
      if (this.axk != 0) {
        parampg.s(11, this.axk);
      }
      if (this.axl != 0) {
        parampg.s(12, this.axl);
      }
      if (!Arrays.equals(this.axr, pq.axd)) {
        parampg.a(13, this.axr);
      }
      if (!this.axt.equals("")) {
        parampg.b(14, this.axt);
      }
      super.a(parampg);
    }
    
    protected int c()
    {
      int i = super.c();
      int j = i;
      if (this.axj != 0L) {
        j = i + pg.d(1, this.axj);
      }
      i = j;
      if (!this.tag.equals("")) {
        i = j + pg.j(2, this.tag);
      }
      j = i;
      if (this.axn != null)
      {
        j = i;
        if (this.axn.length > 0)
        {
          j = 0;
          while (j < this.axn.length)
          {
            pr.d locald = this.axn[j];
            int k = i;
            if (locald != null) {
              k = i + pg.c(3, locald);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (!Arrays.equals(this.axp, pq.axd)) {
        i = j + pg.b(6, this.axp);
      }
      j = i;
      if (this.axs != null) {
        j = i + pg.c(7, this.axs);
      }
      i = j;
      if (!Arrays.equals(this.axq, pq.axd)) {
        i = j + pg.b(8, this.axq);
      }
      j = i;
      if (this.axo != null) {
        j = i + pg.c(9, this.axo);
      }
      i = j;
      if (this.axm) {
        i = j + pg.c(10, this.axm);
      }
      j = i;
      if (this.axk != 0) {
        j = i + pg.u(11, this.axk);
      }
      i = j;
      if (this.axl != 0) {
        i = j + pg.u(12, this.axl);
      }
      j = i;
      if (!Arrays.equals(this.axr, pq.axd)) {
        j = i + pg.b(13, this.axr);
      }
      i = j;
      if (!this.axt.equals("")) {
        i = j + pg.j(14, this.axt);
      }
      return i;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label126:
      label190:
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            do
                            {
                              do
                              {
                                return bool1;
                                bool1 = bool2;
                              } while (!(paramObject instanceof c));
                              paramObject = (c)paramObject;
                              bool1 = bool2;
                            } while (this.axj != ((c)paramObject).axj);
                            if (this.tag != null) {
                              break;
                            }
                            bool1 = bool2;
                          } while (((c)paramObject).tag != null);
                          bool1 = bool2;
                        } while (this.axk != ((c)paramObject).axk);
                        bool1 = bool2;
                      } while (this.axl != ((c)paramObject).axl);
                      bool1 = bool2;
                    } while (this.axm != ((c)paramObject).axm);
                    bool1 = bool2;
                  } while (!pl.equals(this.axn, ((c)paramObject).axn));
                  if (this.axo != null) {
                    break label228;
                  }
                  bool1 = bool2;
                } while (((c)paramObject).axo != null);
                bool1 = bool2;
              } while (!Arrays.equals(this.axp, ((c)paramObject).axp));
              bool1 = bool2;
            } while (!Arrays.equals(this.axq, ((c)paramObject).axq));
            bool1 = bool2;
          } while (!Arrays.equals(this.axr, ((c)paramObject).axr));
          if (this.axs != null) {
            break label244;
          }
          bool1 = bool2;
        } while (((c)paramObject).axs != null);
        if (this.axt != null) {
          break label260;
        }
        bool1 = bool2;
      } while (((c)paramObject).axt != null);
      label228:
      label244:
      label260:
      while (this.axt.equals(((c)paramObject).axt))
      {
        return a((ph)paramObject);
        if (this.tag.equals(((c)paramObject).tag)) {
          break;
        }
        return false;
        if (this.axo.equals(((c)paramObject).axo)) {
          break label126;
        }
        return false;
        if (this.axs.equals(((c)paramObject).axs)) {
          break label190;
        }
        return false;
      }
      return false;
    }
    
    public int hashCode()
    {
      int n = 0;
      int i1 = (int)(this.axj ^ this.axj >>> 32);
      int i;
      int i2;
      int i3;
      int j;
      label50:
      int i4;
      int k;
      label68:
      int i5;
      int i6;
      int i7;
      int m;
      if (this.tag == null)
      {
        i = 0;
        i2 = this.axk;
        i3 = this.axl;
        if (!this.axm) {
          break label201;
        }
        j = 1231;
        i4 = pl.hashCode(this.axn);
        if (this.axo != null) {
          break label208;
        }
        k = 0;
        i5 = Arrays.hashCode(this.axp);
        i6 = Arrays.hashCode(this.axq);
        i7 = Arrays.hashCode(this.axr);
        if (this.axs != null) {
          break label219;
        }
        m = 0;
        label105:
        if (this.axt != null) {
          break label231;
        }
      }
      for (;;)
      {
        return ((m + ((((k + ((j + (((i + (i1 + 527) * 31) * 31 + i2) * 31 + i3) * 31) * 31 + i4) * 31) * 31 + i5) * 31 + i6) * 31 + i7) * 31) * 31 + n) * 31 + qz();
        i = this.tag.hashCode();
        break;
        label201:
        j = 1237;
        break label50;
        label208:
        k = this.axo.hashCode();
        break label68;
        label219:
        m = this.axs.hashCode();
        break label105;
        label231:
        n = this.axt.hashCode();
      }
    }
    
    public c qL()
    {
      this.axj = 0L;
      this.tag = "";
      this.axk = 0;
      this.axl = 0;
      this.axm = false;
      this.axn = pr.d.qM();
      this.axo = null;
      this.axp = pq.axd;
      this.axq = pq.axd;
      this.axr = pq.axd;
      this.axs = null;
      this.axt = "";
      this.awJ = null;
      this.awU = -1;
      return this;
    }
    
    public c x(pf parampf)
      throws IOException
    {
      for (;;)
      {
        int i = parampf.qi();
        switch (i)
        {
        default: 
          if (a(parampf, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.axj = parampf.qk();
          break;
        case 18: 
          this.tag = parampf.readString();
          break;
        case 26: 
          int j = pq.b(parampf, 26);
          if (this.axn == null) {}
          pr.d[] arrayOfd;
          for (i = 0;; i = this.axn.length)
          {
            arrayOfd = new pr.d[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.axn, 0, arrayOfd, 0, i);
              j = i;
            }
            while (j < arrayOfd.length - 1)
            {
              arrayOfd[j] = new pr.d();
              parampf.a(arrayOfd[j]);
              parampf.qi();
              j += 1;
            }
          }
          arrayOfd[j] = new pr.d();
          parampf.a(arrayOfd[j]);
          this.axn = arrayOfd;
          break;
        case 50: 
          this.axp = parampf.readBytes();
          break;
        case 58: 
          if (this.axs == null) {
            this.axs = new pr.a();
          }
          parampf.a(this.axs);
          break;
        case 66: 
          this.axq = parampf.readBytes();
          break;
        case 74: 
          if (this.axo == null) {
            this.axo = new pr.b();
          }
          parampf.a(this.axo);
          break;
        case 80: 
          this.axm = parampf.qm();
          break;
        case 88: 
          this.axk = parampf.ql();
          break;
        case 96: 
          this.axl = parampf.ql();
          break;
        case 106: 
          this.axr = parampf.readBytes();
          break;
        case 114: 
          this.axt = parampf.readString();
        }
      }
    }
  }
  
  public static final class d
    extends ph<d>
  {
    private static volatile d[] axu;
    public String fv;
    public String value;
    
    public d()
    {
      qN();
    }
    
    public static d[] qM()
    {
      if (axu == null) {}
      synchronized (pl.awT)
      {
        if (axu == null) {
          axu = new d[0];
        }
        return axu;
      }
    }
    
    public void a(pg parampg)
      throws IOException
    {
      if (!this.fv.equals("")) {
        parampg.b(1, this.fv);
      }
      if (!this.value.equals("")) {
        parampg.b(2, this.value);
      }
      super.a(parampg);
    }
    
    protected int c()
    {
      int j = super.c();
      int i = j;
      if (!this.fv.equals("")) {
        i = j + pg.j(1, this.fv);
      }
      j = i;
      if (!this.value.equals("")) {
        j = i + pg.j(2, this.value);
      }
      return j;
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
          do
          {
            return bool1;
            bool1 = bool2;
          } while (!(paramObject instanceof d));
          paramObject = (d)paramObject;
          if (this.fv != null) {
            break;
          }
          bool1 = bool2;
        } while (((d)paramObject).fv != null);
        if (this.value != null) {
          break label79;
        }
        bool1 = bool2;
      } while (((d)paramObject).value != null);
      label79:
      while (this.value.equals(((d)paramObject).value))
      {
        return a((ph)paramObject);
        if (this.fv.equals(((d)paramObject).fv)) {
          break;
        }
        return false;
      }
      return false;
    }
    
    public int hashCode()
    {
      int j = 0;
      int i;
      if (this.fv == null)
      {
        i = 0;
        if (this.value != null) {
          break label48;
        }
      }
      for (;;)
      {
        return ((i + 527) * 31 + j) * 31 + qz();
        i = this.fv.hashCode();
        break;
        label48:
        j = this.value.hashCode();
      }
    }
    
    public d qN()
    {
      this.fv = "";
      this.value = "";
      this.awJ = null;
      this.awU = -1;
      return this;
    }
    
    public d y(pf parampf)
      throws IOException
    {
      for (;;)
      {
        int i = parampf.qi();
        switch (i)
        {
        default: 
          if (a(parampf, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.fv = parampf.readString();
          break;
        case 18: 
          this.value = parampf.readString();
        }
      }
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\pr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */