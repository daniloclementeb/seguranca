package br.com.fiap.beans;

import java.util.List;

public class CalendarioGraduacaoBean
{
  int diasemana;
  List<HorarioBean> horarios;
  
  public int getDiasemana()
  {
    return this.diasemana;
  }
  
  public List<HorarioBean> getHorarios()
  {
    return this.horarios;
  }
  
  public void setDiasemana(int paramInt)
  {
    this.diasemana = paramInt;
  }
  
  public void setHorarios(List<HorarioBean> paramList)
  {
    this.horarios = paramList;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\beans\CalendarioGraduacaoBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */