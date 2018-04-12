package br.com.fiap.fragments;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fiap.beans.AulaBean;
import br.com.fiap.beans.BannerBean;
import br.com.fiap.beans.CalendarioGraduacaoBean;
import br.com.fiap.beans.CalendarioPosGraduacaoBean;
import br.com.fiap.beans.HorarioBean;
import br.com.fiap.beans.TurmaBean;
import br.com.fiap.services.HomeService;
import br.com.fiap.services.TurmaService;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class HomeFragment
  extends Fragment
{
  static final int RESULT_LOAD_IMAGE = 1;
  TextView aula1;
  TextView aula2;
  TextView aviso;
  BannerBean b;
  ImageView banner;
  List<BannerBean> banners;
  Drawable[] bns;
  List<CalendarioGraduacaoBean> calendarioGrad;
  List<CalendarioPosGraduacaoBean> calendarioPosGrad;
  TextView cursoAluno;
  String diadasemana;
  TextView diasemana;
  ImageView foto;
  TextView horario1;
  TextView horario2;
  List<TurmaBean> listaTurmas;
  String mCurrentPhotoPath;
  View myView;
  TextView nomeAluno;
  ProgressDialog p;
  String pano;
  String pchave;
  String pcurso;
  String pnome;
  String pnometurma;
  String prm;
  TextView prof1;
  TextView prof2;
  String ptipo;
  TextView rmAluno;
  TextView sala1;
  TextView sala2;
  SharedPreferences sb;
  TextView status;
  TextView turmaAluno;
  TurmaService turmaService;
  URL url;
  String urlimagem;
  String[] urls;
  HomeService webservice;
  
  private Object getObjeto(URL paramURL)
    throws MalformedURLException, IOException
  {
    return paramURL.getContent();
  }
  
  public void carregaFoto(String paramString)
  {
    try
    {
      Object localObject = new BitmapFactory.Options();
      ((BitmapFactory.Options)localObject).inSampleSize = 2;
      localObject = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject), 200, 200, true);
      Matrix localMatrix = new Matrix();
      switch (new ExifInterface(paramString).getAttributeInt("Orientation", 1))
      {
      }
      for (;;)
      {
        localMatrix.postRotate(0.0F);
        for (;;)
        {
          paramString = Bitmap.createBitmap((Bitmap)localObject, 0, 0, 200, 200, localMatrix, true);
          this.foto.setImageBitmap(paramString);
          return;
          localMatrix.postRotate(180.0F);
          continue;
          localMatrix.postRotate(90.0F);
          continue;
          localMatrix.postRotate(270.0F);
        }
      }
      return;
    }
    catch (RuntimeException paramString)
    {
      return;
    }
    catch (IOException paramString) {}
  }
  
  public View graduacao()
  {
    this.webservice = new HomeService();
    this.turmaService = new TurmaService();
    this.myView = LayoutInflater.from(getActivity()).inflate(2130903081, null);
    this.foto = ((ImageView)this.myView.findViewById(2131427436));
    if (!this.urlimagem.equalsIgnoreCase("")) {
      carregaFoto(this.urlimagem);
    }
    this.p = ProgressDialog.show(getActivity(), null, getResources().getText(2131099724), true);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.pnome = this.sb.getString("pnome", "");
    this.pnometurma = this.sb.getString("pnometurma", "");
    this.pcurso = this.sb.getString("pcurso", "");
    this.pano = this.sb.getString("pano", "");
    new Thread()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            HomeFragment.this.listaTurmas = HomeFragment.this.turmaService.turma(HomeFragment.this.prm, HomeFragment.this.pchave);
            if (HomeFragment.this.listaTurmas.size() > 0)
            {
              new TurmaBean();
              localObject = (TurmaBean)HomeFragment.this.listaTurmas.get(0);
              SharedPreferences.Editor localEditor = HomeFragment.this.sb.edit();
              localEditor.putString("pnometurma", ((TurmaBean)localObject).getNomeTurma());
              localEditor.putString("pcurso", ((TurmaBean)localObject).getCurso());
              localEditor.putString("pano", ((TurmaBean)localObject).getAno());
              localEditor.commit();
              HomeFragment.this.pnometurma = HomeFragment.this.sb.getString("pnometurma", "");
              HomeFragment.this.pcurso = HomeFragment.this.sb.getString("pcurso", "");
              HomeFragment.this.pano = HomeFragment.this.sb.getString("pano", "");
            }
            HomeFragment.this.calendarioGrad = HomeFragment.this.webservice.horarios(HomeFragment.this.prm, HomeFragment.this.pnometurma, HomeFragment.this.pano, HomeFragment.this.pchave);
            HomeFragment.this.banners = HomeFragment.this.webservice.banners(HomeFragment.this.prm, HomeFragment.this.pchave);
            HomeFragment.this.bns = new Drawable[HomeFragment.this.banners.size()];
            HomeFragment.this.urls = new String[HomeFragment.this.banners.size()];
            if (HomeFragment.this.urls != null)
            {
              i = 0;
              int j = HomeFragment.this.banners.size();
              if (i < j) {
                continue;
              }
            }
          }
          catch (Exception localException)
          {
            Object localObject;
            int i;
            continue;
          }
          HomeFragment.this.p.dismiss();
          return;
          HomeFragment.this.b = new BannerBean();
          HomeFragment.this.b = ((BannerBean)HomeFragment.this.banners.get(i));
          HomeFragment.this.url = new URL(HomeFragment.this.b.getUrl());
          localObject = Drawable.createFromStream((InputStream)HomeFragment.this.getObjeto(HomeFragment.this.url), "src");
          HomeFragment.this.bns[i] = localObject;
          HomeFragment.this.urls[i] = HomeFragment.this.b.getUrltarget();
          i += 1;
        }
      }
    }.start();
    this.p.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(final DialogInterface paramAnonymousDialogInterface)
      {
        int i;
        for (;;)
        {
          int j;
          label254:
          Object localObject2;
          try
          {
            if (HomeFragment.this.calendarioGrad.size() <= 0) {
              break;
            }
            HomeFragment.this.nomeAluno = ((TextView)HomeFragment.this.myView.findViewById(2131427437));
            HomeFragment.this.rmAluno = ((TextView)HomeFragment.this.myView.findViewById(2131427439));
            HomeFragment.this.turmaAluno = ((TextView)HomeFragment.this.myView.findViewById(2131427442));
            HomeFragment.this.cursoAluno = ((TextView)HomeFragment.this.myView.findViewById(2131427443));
            HomeFragment.this.banner = ((ImageView)HomeFragment.this.myView.findViewById(2131427452));
            HomeFragment.this.nomeAluno.setText(HomeFragment.this.pnome);
            HomeFragment.this.rmAluno.setText(HomeFragment.this.prm);
            HomeFragment.this.turmaAluno.setText(HomeFragment.this.pnometurma);
            HomeFragment.this.cursoAluno.setText(HomeFragment.this.pcurso);
            j = Calendar.getInstance().get(7);
            switch (j)
            {
            case 1: 
              paramAnonymousDialogInterface = new CalendarioGraduacaoBean();
              i = 0;
              if (i < HomeFragment.this.calendarioGrad.size()) {
                break label1091;
              }
              new ArrayList();
              paramAnonymousDialogInterface = paramAnonymousDialogInterface.getHorarios();
              if (paramAnonymousDialogInterface != null)
              {
                if (paramAnonymousDialogInterface.size() != 2) {
                  break label1134;
                }
                localObject2 = LayoutInflater.from(HomeFragment.this.getActivity());
                localObject1 = (LinearLayout)HomeFragment.this.myView.findViewById(2131427444);
                localObject2 = ((LayoutInflater)localObject2).inflate(2130903104, null);
                HomeFragment.this.diasemana = ((TextView)((View)localObject2).findViewById(2131427458));
                HomeFragment.this.aula1 = ((TextView)((View)localObject2).findViewById(2131427491));
                HomeFragment.this.aula2 = ((TextView)((View)localObject2).findViewById(2131427496));
                HomeFragment.this.prof1 = ((TextView)((View)localObject2).findViewById(2131427492));
                HomeFragment.this.prof2 = ((TextView)((View)localObject2).findViewById(2131427497));
                HomeFragment.this.horario1 = ((TextView)((View)localObject2).findViewById(2131427493));
                HomeFragment.this.horario2 = ((TextView)((View)localObject2).findViewById(2131427498));
                HomeFragment.this.sala1 = ((TextView)((View)localObject2).findViewById(2131427494));
                HomeFragment.this.sala2 = ((TextView)((View)localObject2).findViewById(2131427499));
                AulaBean localAulaBean = (AulaBean)((HorarioBean)paramAnonymousDialogInterface.get(0)).getAula().get(0);
                paramAnonymousDialogInterface = (AulaBean)((HorarioBean)paramAnonymousDialogInterface.get(1)).getAula().get(0);
                HomeFragment.this.diasemana.setText(HomeFragment.this.diadasemana);
                HomeFragment.this.aula1.setText(localAulaBean.getNomedisciplina());
                HomeFragment.this.aula2.setText(paramAnonymousDialogInterface.getNomedisciplina());
                HomeFragment.this.prof1.setText(localAulaBean.getNomeprof());
                HomeFragment.this.prof2.setText(paramAnonymousDialogInterface.getNomeprof());
                HomeFragment.this.horario1.setText(localAulaBean.getHorainicio() + " - " + localAulaBean.getHorafim());
                HomeFragment.this.horario2.setText(paramAnonymousDialogInterface.getHorainicio() + " - " + paramAnonymousDialogInterface.getHorafim());
                if ((!localAulaBean.getPredio().equalsIgnoreCase("null")) || (!localAulaBean.getSala().equalsIgnoreCase("null"))) {
                  HomeFragment.this.sala1.setText(localAulaBean.getSala() + " - " + "Unidade " + localAulaBean.getPredio());
                }
                if ((!paramAnonymousDialogInterface.getPredio().equalsIgnoreCase("null")) || (!paramAnonymousDialogInterface.getSala().equalsIgnoreCase("null"))) {
                  HomeFragment.this.sala2.setText(paramAnonymousDialogInterface.getSala() + " - " + "Unidade " + paramAnonymousDialogInterface.getPredio());
                }
                ((LinearLayout)localObject1).addView((View)localObject2);
              }
              label838:
              paramAnonymousDialogInterface = new Handler();
              paramAnonymousDialogInterface.postDelayed(new Runnable()
              {
                int i = HomeFragment.this.bns.length - 1;
                
                public void run()
                {
                  if (HomeFragment.this.bns.length > 0)
                  {
                    HomeFragment.this.banner.setImageDrawable(HomeFragment.this.bns[this.i]);
                    final String str = HomeFragment.this.urls[this.i];
                    HomeFragment.this.banner.setOnClickListener(new View.OnClickListener()
                    {
                      public void onClick(View paramAnonymous3View)
                      {
                        if (!HomeFragment.this.urls[HomeFragment.2.1.this.i].equalsIgnoreCase("#"))
                        {
                          paramAnonymous3View = new Intent("android.intent.action.VIEW", Uri.parse(str));
                          HomeFragment.this.startActivity(paramAnonymous3View);
                        }
                      }
                    });
                    this.i -= 1;
                    if (this.i < 0) {
                      this.i = (HomeFragment.this.bns.length - 1);
                    }
                  }
                  paramAnonymousDialogInterface.postDelayed(this, 10000L);
                }
              }, 5L);
              return;
            }
          }
          catch (Exception paramAnonymousDialogInterface)
          {
            Toast.makeText(HomeFragment.this.getActivity(), HomeFragment.this.getResources().getText(2131099733), 1).show();
            HomeFragment.this.getActivity().finish();
            paramAnonymousDialogInterface.printStackTrace();
            return;
          }
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099725));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099726));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099727, "UTF-8"));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099728));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099729));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099730));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099731));
          continue;
          label1091:
          if (((CalendarioGraduacaoBean)HomeFragment.this.calendarioGrad.get(i)).getDiasemana() != j) {
            break label1989;
          }
          paramAnonymousDialogInterface = (CalendarioGraduacaoBean)HomeFragment.this.calendarioGrad.get(i);
          break label1989;
          label1134:
          if (paramAnonymousDialogInterface.size() == 1)
          {
            localObject2 = LayoutInflater.from(HomeFragment.this.getActivity());
            localObject1 = (LinearLayout)HomeFragment.this.myView.findViewById(2131427444);
            localObject2 = ((LayoutInflater)localObject2).inflate(2130903107, null);
            HomeFragment.this.diasemana = ((TextView)((View)localObject2).findViewById(2131427458));
            HomeFragment.this.aula1 = ((TextView)((View)localObject2).findViewById(2131427491));
            HomeFragment.this.prof1 = ((TextView)((View)localObject2).findViewById(2131427492));
            HomeFragment.this.horario1 = ((TextView)((View)localObject2).findViewById(2131427493));
            HomeFragment.this.sala1 = ((TextView)((View)localObject2).findViewById(2131427494));
            paramAnonymousDialogInterface = (AulaBean)((HorarioBean)paramAnonymousDialogInterface.get(0)).getAula().get(0);
            HomeFragment.this.diasemana.setText(HomeFragment.this.diadasemana);
            HomeFragment.this.aula1.setText(paramAnonymousDialogInterface.getNomedisciplina());
            HomeFragment.this.prof1.setText(paramAnonymousDialogInterface.getNomeprof());
            HomeFragment.this.horario1.setText(paramAnonymousDialogInterface.getHorainicio() + " - " + paramAnonymousDialogInterface.getHorafim());
            if ((!paramAnonymousDialogInterface.getPredio().equalsIgnoreCase("null")) || (!paramAnonymousDialogInterface.getSala().equalsIgnoreCase("null"))) {
              HomeFragment.this.sala1.setText(paramAnonymousDialogInterface.getSala() + " - " + "Unidade " + paramAnonymousDialogInterface.getPredio());
            }
            ((LinearLayout)localObject1).addView((View)localObject2);
          }
        }
        HomeFragment.this.nomeAluno = ((TextView)HomeFragment.this.myView.findViewById(2131427437));
        HomeFragment.this.rmAluno = ((TextView)HomeFragment.this.myView.findViewById(2131427439));
        HomeFragment.this.turmaAluno = ((TextView)HomeFragment.this.myView.findViewById(2131427442));
        HomeFragment.this.cursoAluno = ((TextView)HomeFragment.this.myView.findViewById(2131427443));
        HomeFragment.this.banner = ((ImageView)HomeFragment.this.myView.findViewById(2131427452));
        Object localObject1 = LayoutInflater.from(HomeFragment.this.getActivity());
        paramAnonymousDialogInterface = (LinearLayout)HomeFragment.this.myView.findViewById(2131427444);
        localObject1 = ((LayoutInflater)localObject1).inflate(2130903103, null);
        HomeFragment.this.diasemana = ((TextView)((View)localObject1).findViewById(2131427458));
        HomeFragment.this.aviso = ((TextView)((View)localObject1).findViewById(2131427460));
        HomeFragment.this.nomeAluno.setText(HomeFragment.this.pnome);
        HomeFragment.this.rmAluno.setText(HomeFragment.this.prm);
        HomeFragment.this.turmaAluno.setText(HomeFragment.this.pnometurma);
        HomeFragment.this.cursoAluno.setText(HomeFragment.this.pcurso);
        switch (Calendar.getInstance().get(7))
        {
        }
        for (;;)
        {
          HomeFragment.this.diasemana.setText(HomeFragment.this.diadasemana);
          HomeFragment.this.aviso.setText(HomeFragment.this.getResources().getText(2131099732));
          paramAnonymousDialogInterface.addView((View)localObject1);
          break label838;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099725));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099726));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099727));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099728));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099729));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099730));
          continue;
          HomeFragment.this.diadasemana = String.valueOf(HomeFragment.this.getResources().getText(2131099731));
          continue;
          break;
          label1989:
          i += 1;
          break label254;
        }
      }
    });
    this.foto.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        HomeFragment.this.tirarFoto();
      }
    });
    return this.myView;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1) {}
    for (;;)
    {
      Matrix localMatrix;
      try
      {
        getActivity();
        Object localObject1;
        Object localObject2;
        if ((paramInt2 == -1) && (paramIntent != null))
        {
          localObject1 = paramIntent.getData();
          paramIntent = new String[1];
          paramIntent[0] = "_data";
          localObject2 = getActivity().getContentResolver().query((Uri)localObject1, paramIntent, null, null, null);
          ((Cursor)localObject2).moveToFirst();
          localObject1 = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex(paramIntent[0]));
          ((Cursor)localObject2).close();
          paramIntent = this.sb.edit();
          paramIntent.putString("foto", (String)localObject1);
          paramIntent.commit();
          paramIntent = new BitmapFactory.Options();
          paramIntent.inSampleSize = 2;
          localObject2 = Bitmap.createScaledBitmap(BitmapFactory.decodeFile((String)localObject1, paramIntent), 200, 200, true);
          paramIntent = null;
          localMatrix = new Matrix();
        }
        try
        {
          switch (new ExifInterface((String)localObject1).getAttributeInt("Orientation", 1))
          {
          case 4: 
          case 5: 
          case 7: 
            localMatrix.postRotate(0.0F);
            localObject1 = Bitmap.createBitmap((Bitmap)localObject2, 0, 0, 200, 200, localMatrix, true);
            paramIntent = (Intent)localObject1;
          }
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
          continue;
        }
        this.foto.setImageBitmap(paramIntent);
        return;
      }
      catch (Exception paramIntent)
      {
        paramIntent.printStackTrace();
        return;
      }
      localMatrix.postRotate(180.0F);
      continue;
      localMatrix.postRotate(90.0F);
      continue;
      localMatrix.postRotate(270.0F);
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = getActivity();
    getActivity();
    this.sb = paramLayoutInflater.getSharedPreferences("user", 0);
    this.ptipo = this.sb.getString("ptipo", "");
    this.urlimagem = this.sb.getString("foto", "");
    if (this.ptipo.equalsIgnoreCase("G")) {
      this.myView = graduacao();
    }
    for (;;)
    {
      return this.myView;
      if (this.ptipo.equalsIgnoreCase("P")) {
        this.myView = posgraduacao();
      }
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }
  
  public View posgraduacao()
  {
    this.myView = LayoutInflater.from(getActivity()).inflate(2130903083, null);
    this.foto = ((ImageView)this.myView.findViewById(2131427436));
    try
    {
      if (!this.urlimagem.equalsIgnoreCase("")) {
        carregaFoto(this.urlimagem);
      }
      this.webservice = new HomeService();
      this.turmaService = new TurmaService();
      this.p = ProgressDialog.show(getActivity(), null, getResources().getText(2131099724), true);
      this.prm = this.sb.getString("prm", "");
      this.pchave = this.sb.getString("pchave", "");
      this.pnome = this.sb.getString("pnome", "");
      this.pnometurma = this.sb.getString("pnometurma", "");
      this.pcurso = this.sb.getString("pcurso", "");
      this.pano = this.sb.getString("pano", "");
      this.nomeAluno = ((TextView)this.myView.findViewById(2131427437));
      this.rmAluno = ((TextView)this.myView.findViewById(2131427439));
      this.turmaAluno = ((TextView)this.myView.findViewById(2131427442));
      this.cursoAluno = ((TextView)this.myView.findViewById(2131427443));
      this.banner = ((ImageView)this.myView.findViewById(2131427452));
      new Thread()
      {
        public void run()
        {
          for (;;)
          {
            try
            {
              HomeFragment.this.listaTurmas = HomeFragment.this.turmaService.turma(HomeFragment.this.prm, HomeFragment.this.pchave);
              if (HomeFragment.this.listaTurmas.size() > 0)
              {
                new TurmaBean();
                localObject = (TurmaBean)HomeFragment.this.listaTurmas.get(0);
                SharedPreferences.Editor localEditor = HomeFragment.this.sb.edit();
                localEditor.putString("pnometurma", ((TurmaBean)localObject).getNomeTurma());
                localEditor.putString("pcurso", ((TurmaBean)localObject).getCurso());
                localEditor.putString("pano", ((TurmaBean)localObject).getAno());
                localEditor.commit();
                HomeFragment.this.pnometurma = HomeFragment.this.sb.getString("pnometurma", "");
                HomeFragment.this.pcurso = HomeFragment.this.sb.getString("pcurso", "");
                HomeFragment.this.pano = HomeFragment.this.sb.getString("pano", "");
              }
              Log.i("turma", ">");
              HomeFragment.this.banners = HomeFragment.this.webservice.banners(HomeFragment.this.prm, HomeFragment.this.pchave);
              HomeFragment.this.calendarioPosGrad = HomeFragment.this.webservice.horariosPos(HomeFragment.this.prm, HomeFragment.this.pnometurma, HomeFragment.this.pano, HomeFragment.this.pchave);
              HomeFragment.this.bns = new Drawable[HomeFragment.this.banners.size()];
              HomeFragment.this.urls = new String[HomeFragment.this.banners.size()];
              i = 0;
              int j = HomeFragment.this.banners.size();
              if (i < j) {
                continue;
              }
            }
            catch (Exception localException)
            {
              Object localObject;
              int i;
              continue;
            }
            HomeFragment.this.p.dismiss();
            return;
            HomeFragment.this.b = new BannerBean();
            HomeFragment.this.b = ((BannerBean)HomeFragment.this.banners.get(i));
            HomeFragment.this.url = new URL(HomeFragment.this.b.getUrl());
            localObject = Drawable.createFromStream((InputStream)HomeFragment.this.getObjeto(HomeFragment.this.url), "src name");
            HomeFragment.this.bns[i] = localObject;
            HomeFragment.this.urls[i] = HomeFragment.this.b.getUrltarget();
            i += 1;
          }
        }
      }.start();
      this.p.setOnDismissListener(new DialogInterface.OnDismissListener()
      {
        public void onDismiss(final DialogInterface paramAnonymousDialogInterface)
        {
          LinearLayout localLinearLayout = null;
          int i = 0;
          label24:
          Object localObject1;
          if (i >= HomeFragment.this.calendarioPosGrad.size())
          {
            paramAnonymousDialogInterface = localLinearLayout;
            localObject1 = paramAnonymousDialogInterface;
            if (paramAnonymousDialogInterface == null)
            {
              localObject1 = new CalendarioPosGraduacaoBean();
              ((CalendarioPosGraduacaoBean)localObject1).setCodrelacao(0);
              ((CalendarioPosGraduacaoBean)localObject1).setNomedisciplina(String.valueOf(HomeFragment.this.getResources().getText(2131099734)));
              new Date();
              ((CalendarioPosGraduacaoBean)localObject1).setDataaula(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
            }
            HomeFragment.this.nomeAluno.setText(HomeFragment.this.pnome);
            HomeFragment.this.rmAluno.setText(HomeFragment.this.prm);
            HomeFragment.this.turmaAluno.setText(HomeFragment.this.pnometurma);
            HomeFragment.this.cursoAluno.setText(HomeFragment.this.pcurso);
            if (((CalendarioPosGraduacaoBean)localObject1).getCodrelacao() != 0) {
              break label920;
            }
            paramAnonymousDialogInterface = LayoutInflater.from(HomeFragment.this.getActivity());
            localLinearLayout = (LinearLayout)HomeFragment.this.myView.findViewById(2131427444);
            localObject3 = paramAnonymousDialogInterface.inflate(2130903103, null);
            HomeFragment.this.diasemana = ((TextView)((View)localObject3).findViewById(2131427458));
            HomeFragment.this.aviso = ((TextView)((View)localObject3).findViewById(2131427460));
            localObject2 = new SimpleDateFormat("dd/MM/yyyy");
            paramAnonymousDialogInterface = null;
          }
          try
          {
            localObject2 = ((SimpleDateFormat)localObject2).parse(((CalendarioPosGraduacaoBean)localObject1).getDataaula());
            paramAnonymousDialogInterface = (DialogInterface)localObject2;
          }
          catch (Exception localException2)
          {
            for (;;) {}
          }
          Object localObject2 = new GregorianCalendar();
          ((GregorianCalendar)localObject2).setTime(paramAnonymousDialogInterface);
          switch (((GregorianCalendar)localObject2).get(7))
          {
          }
          for (;;)
          {
            HomeFragment.this.aviso.setText(((CalendarioPosGraduacaoBean)localObject1).getNomedisciplina());
            localLinearLayout.addView((View)localObject3);
            paramAnonymousDialogInterface = new Handler();
            paramAnonymousDialogInterface.postDelayed(new Runnable()
            {
              int i = HomeFragment.this.bns.length - 1;
              
              public void run()
              {
                HomeFragment.this.banner.setImageDrawable(HomeFragment.this.bns[this.i]);
                final String str = HomeFragment.this.urls[this.i];
                HomeFragment.this.banner.setOnClickListener(new View.OnClickListener()
                {
                  public void onClick(View paramAnonymous3View)
                  {
                    if (!HomeFragment.this.urls[HomeFragment.5.1.this.i].equalsIgnoreCase("#"))
                    {
                      paramAnonymous3View = new Intent("android.intent.action.VIEW", Uri.parse(str));
                      HomeFragment.this.startActivity(paramAnonymous3View);
                    }
                  }
                });
                this.i -= 1;
                if (this.i < 0) {
                  this.i = (HomeFragment.this.bns.length - 1);
                }
                paramAnonymousDialogInterface.postDelayed(this, 10000L);
              }
            }, 5L);
            return;
            localObject1 = ((CalendarioPosGraduacaoBean)HomeFragment.this.calendarioPosGrad.get(i)).getDataaula();
            localObject3 = new SimpleDateFormat("dd/MM/yyyy");
            localObject2 = new Date();
            paramAnonymousDialogInterface = new Date();
            try
            {
              localObject1 = ((SimpleDateFormat)localObject3).parse((String)localObject1);
              paramAnonymousDialogInterface = (DialogInterface)localObject1;
              localObject3 = ((SimpleDateFormat)localObject3).parse(((SimpleDateFormat)localObject3).format((Date)localObject2));
              localObject2 = localObject3;
              paramAnonymousDialogInterface = (DialogInterface)localObject1;
              if (((Date)localObject2).equals(paramAnonymousDialogInterface))
              {
                paramAnonymousDialogInterface = (CalendarioPosGraduacaoBean)HomeFragment.this.calendarioPosGrad.get(i);
                Log.i("before", String.valueOf(i));
              }
            }
            catch (ParseException localParseException)
            {
              for (;;)
              {
                localParseException.printStackTrace();
              }
              if (((Date)localObject2).before(paramAnonymousDialogInterface))
              {
                paramAnonymousDialogInterface = (CalendarioPosGraduacaoBean)HomeFragment.this.calendarioPosGrad.get(i);
                Log.i("equals", String.valueOf(i));
                break label24;
              }
              i += 1;
            }
            break;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099725)));
            continue;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099726)));
            continue;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099727)));
            continue;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099728)));
            continue;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099729)));
            continue;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099730)));
            continue;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099731)));
          }
          label920:
          paramAnonymousDialogInterface = LayoutInflater.from(HomeFragment.this.getActivity());
          localLinearLayout = (LinearLayout)HomeFragment.this.myView.findViewById(2131427444);
          Object localObject3 = paramAnonymousDialogInterface.inflate(2130903105, null);
          HomeFragment.this.diasemana = ((TextView)((View)localObject3).findViewById(2131427458));
          HomeFragment.this.aula1 = ((TextView)((View)localObject3).findViewById(2131427491));
          HomeFragment.this.prof1 = ((TextView)((View)localObject3).findViewById(2131427492));
          HomeFragment.this.status = ((TextView)((View)localObject3).findViewById(2131427500));
          HomeFragment.this.horario1 = ((TextView)((View)localObject3).findViewById(2131427493));
          HomeFragment.this.sala1 = ((TextView)((View)localObject3).findViewById(2131427494));
          HomeFragment.this.horario1.setVisibility(8);
          if (localParseException != null)
          {
            localObject2 = new SimpleDateFormat("dd/MM/yyyy");
            paramAnonymousDialogInterface = null;
          }
          try
          {
            localObject2 = ((SimpleDateFormat)localObject2).parse(localParseException.getDataaula());
            paramAnonymousDialogInterface = (DialogInterface)localObject2;
          }
          catch (Exception localException1)
          {
            for (;;) {}
          }
          localObject2 = new GregorianCalendar();
          ((GregorianCalendar)localObject2).setTime(paramAnonymousDialogInterface);
          switch (((GregorianCalendar)localObject2).get(7))
          {
          default: 
            label1168:
            HomeFragment.this.aula1.setText(localParseException.getNomedisciplina());
            HomeFragment.this.prof1.setText(localParseException.getProfessor());
            if (localParseException.getCancelada())
            {
              HomeFragment.this.status.setText(HomeFragment.this.getResources().getText(2131099736));
              HomeFragment.this.horario1.setVisibility(8);
              HomeFragment.this.sala1.setVisibility(8);
              label1250:
              if ((!localParseException.getHorainicio().equalsIgnoreCase("null")) && (!localParseException.getHorafim().equalsIgnoreCase("null"))) {
                break label1793;
              }
              HomeFragment.this.horario1.setText("-");
              label1289:
              if ((!localParseException.getPredio().equalsIgnoreCase("null")) || (localParseException.getSala().equalsIgnoreCase("null"))) {
                break label1835;
              }
              HomeFragment.this.sala1.setText(localParseException.getSala());
            }
            break;
          }
          for (;;)
          {
            localLinearLayout.addView((View)localObject3);
            break;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099725)));
            break label1168;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099726)));
            break label1168;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099727)));
            break label1168;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099728)));
            break label1168;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099729)));
            break label1168;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099730)));
            break label1168;
            HomeFragment.this.diasemana.setText(localParseException.getDataaula() + " - " + String.valueOf(HomeFragment.this.getResources().getText(2131099731)));
            break label1168;
            if (localParseException.getRemarcada())
            {
              HomeFragment.this.status.setText(HomeFragment.this.getResources().getText(2131099737));
              HomeFragment.this.status.setBackgroundColor(HomeFragment.this.getResources().getColor(2131165233));
              HomeFragment.this.horario1.setVisibility(8);
              break label1250;
            }
            HomeFragment.this.status.setVisibility(8);
            break label1250;
            label1793:
            HomeFragment.this.horario1.setText(localParseException.getHorainicio() + " - " + localParseException.getHorafim());
            break label1289;
            label1835:
            if ((!localParseException.getPredio().equalsIgnoreCase("null")) && (!localParseException.getSala().equalsIgnoreCase("null"))) {
              HomeFragment.this.sala1.setText(localParseException.getSala() + " - " + "Unidade " + localParseException.getPredio());
            }
          }
        }
      });
      this.foto.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          HomeFragment.this.tirarFoto();
        }
      });
      return this.myView;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void tirarFoto()
  {
    startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\HomeFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */