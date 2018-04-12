package br.com.fiap.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import br.com.fiap.fiapmobile.PainelActivity;
import br.com.fiap.layouts.ItemListView;
import br.com.fiap.layouts.MenuAdapter;
import java.util.ArrayList;
import java.util.List;

public class MenuFragment
  extends ListFragment
{
  String pano;
  String pchave;
  String pcurso;
  String pnome;
  String pnometurma;
  String prm;
  String ptipo;
  SharedPreferences sb;
  
  private void switchFragment(Fragment paramFragment, String paramString)
  {
    if (getActivity() == null) {}
    while (!(getActivity() instanceof PainelActivity)) {
      return;
    }
    ((PainelActivity)getActivity()).switchContent(paramFragment, paramString);
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    paramBundle = new ArrayList();
    ItemListView localItemListView;
    if (this.ptipo.equalsIgnoreCase("G"))
    {
      localItemListView = new ItemListView();
      localItemListView.setIconeRid(2130837675);
      localItemListView.setTexto("Home");
      paramBundle.add(localItemListView);
      localItemListView = new ItemListView();
      localItemListView.setIconeRid(2130837616);
      localItemListView.setTexto("Aulas");
      paramBundle.add(localItemListView);
      localItemListView = new ItemListView();
      localItemListView.setIconeRid(2130837618);
      localItemListView.setTexto("Avisos");
      paramBundle.add(localItemListView);
      localItemListView = new ItemListView();
      localItemListView.setIconeRid(2130837691);
      localItemListView.setTexto("Material de Apoio");
      paramBundle.add(localItemListView);
      localItemListView = new ItemListView();
      localItemListView.setIconeRid(2130837622);
      localItemListView.setTexto("Notas e Faltas");
      paramBundle.add(localItemListView);
      localItemListView = new ItemListView();
      localItemListView.setIconeRid(2130837703);
      localItemListView.setTexto("Provas");
      paramBundle.add(localItemListView);
    }
    for (;;)
    {
      setListAdapter(new MenuAdapter(getActivity(), paramBundle));
      return;
      if (this.ptipo.equalsIgnoreCase("P"))
      {
        localItemListView = new ItemListView();
        localItemListView.setIconeRid(2130837675);
        localItemListView.setTexto("Home");
        paramBundle.add(localItemListView);
        localItemListView = new ItemListView();
        localItemListView.setIconeRid(2130837616);
        localItemListView.setTexto("Aulas");
        paramBundle.add(localItemListView);
        localItemListView = new ItemListView();
        localItemListView.setIconeRid(2130837618);
        localItemListView.setTexto("Avisos");
        paramBundle.add(localItemListView);
        localItemListView = new ItemListView();
        localItemListView.setIconeRid(2130837691);
        localItemListView.setTexto("Material de Apoio");
        paramBundle.add(localItemListView);
        localItemListView = new ItemListView();
        localItemListView.setIconeRid(2130837715);
        localItemListView.setTexto("Trabalhos");
        paramBundle.add(localItemListView);
        localItemListView = new ItemListView();
        localItemListView.setIconeRid(2130837622);
        localItemListView.setTexto("Notas e Faltas");
        paramBundle.add(localItemListView);
      }
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramViewGroup = getActivity();
    getActivity();
    this.sb = paramViewGroup.getSharedPreferences("user", 0);
    this.ptipo = this.sb.getString("ptipo", "");
    return paramLayoutInflater.inflate(2130903085, null);
  }
  
  public void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    paramListView = null;
    paramView = null;
    switch (paramInt)
    {
    }
    for (;;)
    {
      if (paramListView != null) {
        switchFragment(paramListView, paramView);
      }
      return;
      paramListView = new HomeFragment();
      paramView = "Home";
      continue;
      paramListView = new AulasFragment();
      paramView = "Aulas";
      continue;
      paramListView = new AvisoFragment();
      paramView = "Avisos";
      continue;
      paramListView = new ApostilasFragment();
      paramView = "Material de Apoio";
      continue;
      if (this.ptipo.equalsIgnoreCase("P"))
      {
        paramListView = new TrabalhosPosTurmaFragment();
        paramView = "Trabalhos";
      }
      else if (this.ptipo.equalsIgnoreCase("G"))
      {
        paramListView = new BoletimFragment();
        paramView = "Notas e Faltas";
        continue;
        if (this.ptipo.equalsIgnoreCase("P"))
        {
          paramListView = new BoletimFragment();
          paramView = "Notas e Faltas";
        }
        else if (this.ptipo.equalsIgnoreCase("G"))
        {
          paramListView = new AvaliacaoesFragment();
          paramView = "Provas";
        }
      }
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\MenuFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */