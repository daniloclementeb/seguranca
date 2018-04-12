package br.com.fiap.beans;

import java.util.List;

public class HorarioBean
{
  List<AulaBean> aula;
  int horario;
  
  public List<AulaBean> getAula()
  {
    return this.aula;
  }
  
  public int getHorario()
  {
    return this.horario;
  }
  
  public void setAula(List<AulaBean> paramList)
  {
    this.aula = paramList;
  }
  
  public void setHorario(int paramInt)
  {
    this.horario = paramInt;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\beans\HorarioBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */