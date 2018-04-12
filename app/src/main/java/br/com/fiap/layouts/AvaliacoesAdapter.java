package br.com.fiap.layouts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import br.com.fiap.fragments.NacFragment;
import br.com.fiap.fragments.SemestralFragment;

public class AvaliacoesAdapter
  extends FragmentStatePagerAdapter
{
  public AvaliacoesAdapter(FragmentManager paramFragmentManager)
  {
    super(paramFragmentManager);
  }
  
  public int getCount()
  {
    return 4;
  }
  
  public Fragment getItem(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return new NacFragment();
    case 1: 
      return new SemestralFragment(1);
    case 2: 
      return new SemestralFragment(2);
    }
    return new SemestralFragment(3);
  }
  
  public CharSequence getPageTitle(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return "NAC";
    case 1: 
      return "PS";
    case 2: 
      return "SUB";
    }
    return "EXAME";
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\layouts\AvaliacoesAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */