package br.com.fiap.layouts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.fiap.beans.AvisoBean;
import java.util.List;

public class AvisoAdapter
  extends BaseAdapter
{
  private List<AvisoBean> avisos;
  private ViewHolder holder;
  private LayoutInflater mInflater;
  
  public AvisoAdapter(Context paramContext, List<AvisoBean> paramList)
  {
    this.mInflater = LayoutInflater.from(paramContext);
    this.avisos = paramList;
  }
  
  public int getCount()
  {
    return this.avisos.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.avisos.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(2130903070, null);
      this.holder = new ViewHolder();
      this.holder.tvTitulo = ((TextView)paramView.findViewById(2131427421));
      this.holder.tvDataTamanho = ((TextView)paramView.findViewById(2131427422));
      this.holder.icone = ((ImageView)paramView.findViewById(2131427420));
      paramView.setTag(this.holder);
    }
    for (;;)
    {
      paramViewGroup = (AvisoBean)this.avisos.get(paramInt);
      this.holder.tvTitulo.setText(paramViewGroup.getTitulo());
      this.holder.tvDataTamanho.setText(paramViewGroup.getData());
      if (paramViewGroup.getStatus() != 0) {
        break;
      }
      Log.i("titulonoadapter", paramViewGroup.getTitulo());
      this.holder.icone.setImageResource(2130837694);
      return paramView;
      this.holder = ((ViewHolder)paramView.getTag());
    }
    this.holder.icone.setImageResource(0);
    return paramView;
  }
  
  public void replace(int paramInt, AvisoBean paramAvisoBean)
  {
    this.avisos.set(paramInt, paramAvisoBean);
    notifyDataSetChanged();
  }
  
  static class ViewHolder
  {
    private ImageView icone;
    private TextView tvDataTamanho;
    private TextView tvTitulo;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\layouts\AvisoAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */