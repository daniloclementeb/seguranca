package br.com.fiap.fiapmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.TextView;
import br.com.fiap.layouts.TrabalhosPosAdapter;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class TrabalhosPosPager
  extends SherlockFragmentActivity
{
  TrabalhosPosAdapter mAdapter;
  ViewPager mViewPager;
  TextView title;
  String turma;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903120);
    paramBundle = getLayoutInflater().inflate(2130903072, null);
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setCustomView(paramBundle, new ActionBar.LayoutParams(-1, -1));
    localActionBar.setDisplayOptions(16);
    localActionBar.setDisplayUseLogoEnabled(false);
    localActionBar.setDisplayShowTitleEnabled(false);
    localActionBar.setDisplayShowHomeEnabled(false);
    localActionBar.setDisplayHomeAsUpEnabled(false);
    this.turma = getIntent().getStringExtra("turma");
    this.title = ((TextView)findViewById(2131689472));
    this.title.setText("Trabalhos");
    this.mAdapter = new TrabalhosPosAdapter(getSupportFragmentManager(), this.turma);
    this.mViewPager = ((ViewPager)findViewById(2131427575));
    this.mViewPager.setAdapter(this.mAdapter);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\TrabalhosPosPager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */