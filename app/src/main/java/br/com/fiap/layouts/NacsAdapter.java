package br.com.fiap.layouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.fiap.beans.ListaNacBean;
import java.util.List;

public class NacsAdapter
  extends BaseAdapter
{
  private ViewHolder holder;
  private LayoutInflater mInflater;
  private List<ListaNacBean> nacs;
  
  public NacsAdapter(Context paramContext, List<ListaNacBean> paramList)
  {
    this.mInflater = LayoutInflater.from(paramContext);
    this.nacs = paramList;
  }
  
  public int getCount()
  {
    return this.nacs.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.nacs.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(2130903095, null);
      this.holder = new ViewHolder();
      this.holder.data = ((TextView)paramView.findViewById(2131427479));
      this.holder.nome = ((TextView)paramView.findViewById(2131427478));
      this.holder.valor = ((TextView)paramView.findViewById(2131427481));
      this.holder.nota = ((TextView)paramView.findViewById(2131427483));
      this.holder.titledescarte = ((TextView)paramView.findViewById(2131427484));
      this.holder.descarte = ((TextView)paramView.findViewById(2131427485));
      paramView.setTag(this.holder);
      paramViewGroup = (ListaNacBean)this.nacs.get(paramInt);
      if (!paramViewGroup.getData().equalsIgnoreCase("null")) {
        break label278;
      }
      this.holder.data.setText("");
      label168:
      this.holder.nome.setText(paramViewGroup.getDesc());
      if (!paramViewGroup.getNota().equalsIgnoreCase("null")) {
        break label295;
      }
      this.holder.nota.setText("-");
      label206:
      this.holder.valor.setText(String.valueOf(Double.valueOf(paramViewGroup.getValor())));
      if (!paramViewGroup.getValido().equalsIgnoreCase("false")) {
        break label318;
      }
      this.holder.titledescarte.setText("Não válido");
      this.holder.descarte.setText("");
    }
    label278:
    label295:
    label318:
    do
    {
      return paramView;
      this.holder = ((ViewHolder)paramView.getTag());
      break;
      this.holder.data.setText(paramViewGroup.getData());
      break label168;
      this.holder.nota.setText(String.valueOf(Double.valueOf(paramViewGroup.getNota())));
      break label206;
      if ((paramViewGroup.getDescarte().equalsIgnoreCase("true")) && (paramViewGroup.getValido().equalsIgnoreCase("true")))
      {
        this.holder.titledescarte.setText("Descartado?");
        this.holder.descarte.setText("Sim");
        return paramView;
      }
    } while ((!paramViewGroup.getDescarte().equalsIgnoreCase("false")) || (!paramViewGroup.getValido().equalsIgnoreCase("true")));
    this.holder.titledescarte.setText("Descartado?");
    this.holder.descarte.setText("Não");
    return paramView;
  }
  
  static class ViewHolder
  {
    private TextView data;
    private TextView descarte;
    private TextView nome;
    private TextView nota;
    private TextView titledescarte;
    private TextView valor;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\layouts\NacsAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */