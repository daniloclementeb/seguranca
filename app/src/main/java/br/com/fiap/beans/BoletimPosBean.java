package br.com.fiap.beans;

import java.util.List;

public class BoletimPosBean
{
  int ch;
  int codrelacao;
  Double faltas;
  List<FaltaPosBean> listafaltas;
  String mediafinal;
  String nomemodulo;
  String nota;
  String notaartigo;
  String notasub;
  int nrmodulo;
  String status;
  int usaartigo;
  
  public int getCh()
  {
    return this.ch;
  }
  
  public int getCodrelacao()
  {
    return this.codrelacao;
  }
  
  public Double getFaltas()
  {
    return this.faltas;
  }
  
  public List<FaltaPosBean> getListafaltas()
  {
    return this.listafaltas;
  }
  
  public String getMediafinal()
  {
    return this.mediafinal;
  }
  
  public String getNomemodulo()
  {
    return this.nomemodulo;
  }
  
  public String getNota()
  {
    return this.nota;
  }
  
  public String getNotaartigo()
  {
    return this.notaartigo;
  }
  
  public String getNotasub()
  {
    return this.notasub;
  }
  
  public int getNrmodulo()
  {
    return this.nrmodulo;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public int getUsaartigo()
  {
    return this.usaartigo;
  }
  
  public void setCh(int paramInt)
  {
    this.ch = paramInt;
  }
  
  public void setCodrelacao(int paramInt)
  {
    this.codrelacao = paramInt;
  }
  
  public void setFaltas(Double paramDouble)
  {
    this.faltas = paramDouble;
  }
  
  public void setListafaltas(List<FaltaPosBean> paramList)
  {
    this.listafaltas = paramList;
  }
  
  public void setMediafinal(String paramString)
  {
    this.mediafinal = paramString;
  }
  
  public void setNomemodulo(String paramString)
  {
    this.nomemodulo = paramString;
  }
  
  public void setNota(String paramString)
  {
    this.nota = paramString;
  }
  
  public void setNotaartigo(String paramString)
  {
    this.notaartigo = paramString;
  }
  
  public void setNotasub(String paramString)
  {
    this.notasub = paramString;
  }
  
  public void setNrmodulo(int paramInt)
  {
    this.nrmodulo = paramInt;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
  
  public void setUsaartigo(int paramInt)
  {
    this.usaartigo = paramInt;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\beans\BoletimPosBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */