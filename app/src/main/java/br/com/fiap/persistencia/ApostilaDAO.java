package br.com.fiap.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import br.com.fiap.beans.ApostilaBean;
import java.util.ArrayList;
import java.util.List;

public class ApostilaDAO
{
  private static final String DATABASE_NAME = "fiap.db";
  private static final int DATABASE_VERSION = 1;
  private static final String INSERT = "insert into TBHOME (apostilaid, titulo, extencao, tamanho, medida, data, url, rm) values (?, ?, ?, ?, ?, ?, ?)";
  private static final String TABLE_NAME = "TBHOME";
  private Context context;
  private SQLiteDatabase db;
  private SQLiteStatement insertStmt;
  
  public ApostilaDAO(Context paramContext)
  {
    this.context = paramContext;
    this.db = new OpenHelper(this.context).getWritableDatabase();
    this.insertStmt = this.db.compileStatement("insert into TBHOME (apostilaid, titulo, extencao, tamanho, medida, data, url, rm) values (?, ?, ?, ?, ?, ?, ?)");
  }
  
  public void deleteAll()
  {
    this.db.delete("TBHOME", null, null);
  }
  
  public void encerrarDB()
  {
    this.db.close();
  }
  
  public long insert(ApostilaBean paramApostilaBean, int paramInt)
  {
    this.insertStmt.bindLong(1, paramApostilaBean.getApostilaid());
    this.insertStmt.bindString(2, paramApostilaBean.getTitulo());
    this.insertStmt.bindString(3, paramApostilaBean.getExtencao());
    this.insertStmt.bindString(4, paramApostilaBean.getTamanho());
    this.insertStmt.bindString(5, paramApostilaBean.getMedida());
    this.insertStmt.bindString(6, paramApostilaBean.getData());
    this.insertStmt.bindString(7, paramApostilaBean.getUrl());
    this.insertStmt.bindLong(8, paramInt);
    return this.insertStmt.executeInsert();
  }
  
  public List<ApostilaBean> select(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    ApostilaBean localApostilaBean = new ApostilaBean();
    paramString = this.db.query("TBHOME", new String[] { "apostilaid", "titulo", "extencao", "tamanho", "medida", "data", "url" }, "rm = ?", new String[] { paramString }, null, null, "apostilaid");
    if (paramString.moveToFirst()) {
      do
      {
        localApostilaBean.setApostilaid(paramString.getInt(0));
        localApostilaBean.setTitulo(paramString.getString(1));
        localApostilaBean.setExtencao(paramString.getString(2));
        localApostilaBean.setTamanho(paramString.getString(3));
        localApostilaBean.setMedida(paramString.getString(4));
        localApostilaBean.setData(paramString.getString(5));
        localApostilaBean.setUrl(paramString.getString(6));
        localArrayList.add(localApostilaBean);
      } while (paramString.moveToNext());
    }
    if ((paramString != null) && (!paramString.isClosed())) {
      paramString.close();
    }
    return localArrayList;
  }
  
  private static class OpenHelper
    extends SQLiteOpenHelper
  {
    OpenHelper(Context paramContext)
    {
      super("fiap.db", null, 1);
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE TBHOME (apostilaid INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, extencao TEXT, tamanho TEXT, medida TEXT, data TEXT, url TEXT)");
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS TBHOME");
      onCreate(paramSQLiteDatabase);
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\persistencia\ApostilaDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */