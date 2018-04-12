package br.com.fiap.layouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.fiap.beans.FaltaBean;
import java.util.List;

public class FaltasAdapter
  extends BaseAdapter
{
  private List<FaltaBean> faltas;
  private ViewHolder holder;
  private LayoutInflater mInflater;
  
  public FaltasAdapter(Context paramContext, List<FaltaBean> paramList)
  {
    this.mInflater = LayoutInflater.from(paramContext);
    this.faltas = paramList;
  }
  
  public int getCount()
  {
    return this.faltas.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.faltas.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(2130903080, null);
      this.holder = new ViewHolder();
      this.holder.data = ((TextView)paramView.findViewById(2131427431));
      this.holder.qtd = ((TextView)paramView.findViewById(2131427432));
      paramView.setTag(this.holder);
    }
    for (;;)
    {
      paramViewGroup = (FaltaBean)this.faltas.get(paramInt);
      this.holder.data.setText(paramViewGroup.getData());
      this.holder.qtd.setText(String.valueOf(paramViewGroup.getQtd()));
      return paramView;
      this.holder = ((ViewHolder)paramView.getTag());
    }
  }
  
  static class ViewHolder
  {
    private TextView data;
    private TextView qtd;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\layouts\FaltasAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */