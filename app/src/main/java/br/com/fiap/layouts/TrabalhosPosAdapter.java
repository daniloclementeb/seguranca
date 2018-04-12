package br.com.fiap.layouts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import br.com.fiap.fragments.TrabalhosPosFragment;

public class TrabalhosPosAdapter
  extends FragmentStatePagerAdapter
{
  String turma;
  
  public TrabalhosPosAdapter(FragmentManager paramFragmentManager, String paramString)
  {
    super(paramFragmentManager);
    this.turma = paramString;
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
      return new TrabalhosPosFragment(1, this.turma);
    case 1: 
      return new TrabalhosPosFragment(2, this.turma);
    }
    return new TrabalhosPosFragment(3, this.turma);
  }
  
  public CharSequence getPageTitle(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return "Pendentes";
    case 1: 
      return "Entregues";
    }
    return "Corrigidos";
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\layouts\TrabalhosPosAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */