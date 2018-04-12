package br.com.fiap.layouts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import br.com.fiap.fragments.BoletimSemestreFragment;

public class BoletimAdapter
  extends FragmentStatePagerAdapter
{
  String ano;
  String turma;
  
  public BoletimAdapter(FragmentManager paramFragmentManager, String paramString1, String paramString2)
  {
    super(paramFragmentManager);
    this.ano = paramString2;
    this.turma = paramString1;
  }
  
  public int getCount()
  {
    return 3;
  }
  
  public Fragment getItem(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return new BoletimSemestreFragment(1, this.ano, this.turma);
    case 1: 
      return new BoletimSemestreFragment(2, this.ano, this.turma);
    }
    return new BoletimSemestreFragment(3, this.ano, this.turma);
  }
  
  public CharSequence getPageTitle(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return "1º Semestre";
    case 1: 
      return "2º Semestre";
    }
    return "Resultado Final";
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\layouts\BoletimAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */