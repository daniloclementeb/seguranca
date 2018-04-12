package com.actionbarsherlock.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.Handler;
import android.text.TextUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class ActivityChooserModel
  extends DataSetObservable
{
  private static final String ATTRIBUTE_ACTIVITY = "activity";
  private static final String ATTRIBUTE_TIME = "time";
  private static final String ATTRIBUTE_WEIGHT = "weight";
  private static final boolean DEBUG = false;
  private static final int DEFAULT_ACTIVITY_INFLATION = 5;
  private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0F;
  public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
  public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
  private static final String HISTORY_FILE_EXTENSION = ".xml";
  private static final int INVALID_INDEX = -1;
  private static final String LOG_TAG = ActivityChooserModel.class.getSimpleName();
  private static final Executor SERIAL_EXECUTOR = Executors.newSingleThreadExecutor();
  private static final String TAG_HISTORICAL_RECORD = "historical-record";
  private static final String TAG_HISTORICAL_RECORDS = "historical-records";
  private static final Map<String, ActivityChooserModel> sDataModelRegistry;
  private static final Object sRegistryLock = new Object();
  private final List<ActivityResolveInfo> mActivites = new ArrayList();
  private OnChooseActivityListener mActivityChoserModelPolicy;
  private ActivitySorter mActivitySorter = new DefaultSorter(null);
  private boolean mCanReadHistoricalData = true;
  private final Context mContext;
  private final Handler mHandler = new Handler();
  private final List<HistoricalRecord> mHistoricalRecords = new ArrayList();
  private boolean mHistoricalRecordsChanged = true;
  private final String mHistoryFileName;
  private int mHistoryMaxSize = 50;
  private final Object mInstanceLock = new Object();
  private Intent mIntent;
  private boolean mReadShareHistoryCalled = false;
  
  static
  {
    sDataModelRegistry = new HashMap();
  }
  
  private ActivityChooserModel(Context paramContext, String paramString)
  {
    this.mContext = paramContext.getApplicationContext();
    if ((!TextUtils.isEmpty(paramString)) && (!paramString.endsWith(".xml")))
    {
      this.mHistoryFileName = (paramString + ".xml");
      return;
    }
    this.mHistoryFileName = paramString;
  }
  
  private boolean addHisoricalRecord(HistoricalRecord paramHistoricalRecord)
  {
    synchronized (this.mInstanceLock)
    {
      boolean bool = this.mHistoricalRecords.add(paramHistoricalRecord);
      if (bool)
      {
        this.mHistoricalRecordsChanged = true;
        pruneExcessiveHistoricalRecordsLocked();
        persistHistoricalData();
        sortActivities();
      }
      return bool;
    }
  }
  
  public static ActivityChooserModel get(Context paramContext, String paramString)
  {
    synchronized (sRegistryLock)
    {
      ActivityChooserModel localActivityChooserModel2 = (ActivityChooserModel)sDataModelRegistry.get(paramString);
      ActivityChooserModel localActivityChooserModel1 = localActivityChooserModel2;
      if (localActivityChooserModel2 == null)
      {
        localActivityChooserModel1 = new ActivityChooserModel(paramContext, paramString);
        sDataModelRegistry.put(paramString, localActivityChooserModel1);
      }
      localActivityChooserModel1.readHistoricalData();
      return localActivityChooserModel1;
    }
  }
  
  private void loadActivitiesLocked()
  {
    this.mActivites.clear();
    if (this.mIntent != null)
    {
      List localList = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
      int j = localList.size();
      int i = 0;
      for (;;)
      {
        if (i >= j)
        {
          sortActivities();
          return;
        }
        ResolveInfo localResolveInfo = (ResolveInfo)localList.get(i);
        this.mActivites.add(new ActivityResolveInfo(localResolveInfo));
        i += 1;
      }
    }
    notifyChanged();
  }
  
  private void persistHistoricalData()
  {
    synchronized (this.mInstanceLock)
    {
      if (!this.mReadShareHistoryCalled) {
        throw new IllegalStateException("No preceding call to #readHistoricalData");
      }
    }
    if (!this.mHistoricalRecordsChanged) {
      return;
    }
    this.mHistoricalRecordsChanged = false;
    this.mCanReadHistoricalData = true;
    if (!TextUtils.isEmpty(this.mHistoryFileName)) {
      SERIAL_EXECUTOR.execute(new HistoryPersister(null));
    }
  }
  
  private void pruneExcessiveHistoricalRecordsLocked()
  {
    List localList = this.mHistoricalRecords;
    int j = localList.size() - this.mHistoryMaxSize;
    if (j <= 0) {}
    for (;;)
    {
      return;
      this.mHistoricalRecordsChanged = true;
      int i = 0;
      while (i < j)
      {
        HistoricalRecord localHistoricalRecord = (HistoricalRecord)localList.remove(0);
        i += 1;
      }
    }
  }
  
  private void readHistoricalData()
  {
    synchronized (this.mInstanceLock)
    {
      if ((!this.mCanReadHistoricalData) || (!this.mHistoricalRecordsChanged)) {
        return;
      }
      this.mCanReadHistoricalData = false;
      this.mReadShareHistoryCalled = true;
      if (!TextUtils.isEmpty(this.mHistoryFileName)) {
        SERIAL_EXECUTOR.execute(new HistoryLoader(null));
      }
      return;
    }
  }
  
  private void sortActivities()
  {
    synchronized (this.mInstanceLock)
    {
      if ((this.mActivitySorter != null) && (!this.mActivites.isEmpty()))
      {
        this.mActivitySorter.sort(this.mIntent, this.mActivites, Collections.unmodifiableList(this.mHistoricalRecords));
        notifyChanged();
      }
      return;
    }
  }
  
  public Intent chooseActivity(int paramInt)
  {
    Object localObject = (ActivityResolveInfo)this.mActivites.get(paramInt);
    localObject = new ComponentName(((ActivityResolveInfo)localObject).resolveInfo.activityInfo.packageName, ((ActivityResolveInfo)localObject).resolveInfo.activityInfo.name);
    Intent localIntent1 = new Intent(this.mIntent);
    localIntent1.setComponent((ComponentName)localObject);
    if (this.mActivityChoserModelPolicy != null)
    {
      Intent localIntent2 = new Intent(localIntent1);
      if (this.mActivityChoserModelPolicy.onChooseActivity(this, localIntent2)) {
        return null;
      }
    }
    addHisoricalRecord(new HistoricalRecord((ComponentName)localObject, System.currentTimeMillis(), 1.0F));
    return localIntent1;
  }
  
  public ResolveInfo getActivity(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      ResolveInfo localResolveInfo = ((ActivityResolveInfo)this.mActivites.get(paramInt)).resolveInfo;
      return localResolveInfo;
    }
  }
  
  public int getActivityCount()
  {
    synchronized (this.mInstanceLock)
    {
      int i = this.mActivites.size();
      return i;
    }
  }
  
  public int getActivityIndex(ResolveInfo paramResolveInfo)
  {
    List localList = this.mActivites;
    int k = localList.size();
    int i = 0;
    for (;;)
    {
      int j;
      if (i >= k) {
        j = -1;
      }
      do
      {
        return j;
        j = i;
      } while (((ActivityResolveInfo)localList.get(i)).resolveInfo == paramResolveInfo);
      i += 1;
    }
  }
  
  public ResolveInfo getDefaultActivity()
  {
    synchronized (this.mInstanceLock)
    {
      if (!this.mActivites.isEmpty())
      {
        ResolveInfo localResolveInfo = ((ActivityResolveInfo)this.mActivites.get(0)).resolveInfo;
        return localResolveInfo;
      }
      return null;
    }
  }
  
  public int getHistoryMaxSize()
  {
    synchronized (this.mInstanceLock)
    {
      int i = this.mHistoryMaxSize;
      return i;
    }
  }
  
  public int getHistorySize()
  {
    synchronized (this.mInstanceLock)
    {
      int i = this.mHistoricalRecords.size();
      return i;
    }
  }
  
  public Intent getIntent()
  {
    synchronized (this.mInstanceLock)
    {
      Intent localIntent = this.mIntent;
      return localIntent;
    }
  }
  
  public void setActivitySorter(ActivitySorter paramActivitySorter)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mActivitySorter == paramActivitySorter) {
        return;
      }
      this.mActivitySorter = paramActivitySorter;
      sortActivities();
      return;
    }
  }
  
  public void setDefaultActivity(int paramInt)
  {
    ActivityResolveInfo localActivityResolveInfo1 = (ActivityResolveInfo)this.mActivites.get(paramInt);
    ActivityResolveInfo localActivityResolveInfo2 = (ActivityResolveInfo)this.mActivites.get(0);
    if (localActivityResolveInfo2 != null) {}
    for (float f = localActivityResolveInfo2.weight - localActivityResolveInfo1.weight + 5.0F;; f = 1.0F)
    {
      addHisoricalRecord(new HistoricalRecord(new ComponentName(localActivityResolveInfo1.resolveInfo.activityInfo.packageName, localActivityResolveInfo1.resolveInfo.activityInfo.name), System.currentTimeMillis(), f));
      return;
    }
  }
  
  public void setHistoryMaxSize(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mHistoryMaxSize == paramInt) {
        return;
      }
      this.mHistoryMaxSize = paramInt;
      pruneExcessiveHistoricalRecordsLocked();
      sortActivities();
      return;
    }
  }
  
  public void setIntent(Intent paramIntent)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mIntent == paramIntent) {
        return;
      }
      this.mIntent = paramIntent;
      loadActivitiesLocked();
      return;
    }
  }
  
  public void setOnChooseActivityListener(OnChooseActivityListener paramOnChooseActivityListener)
  {
    this.mActivityChoserModelPolicy = paramOnChooseActivityListener;
  }
  
  public static abstract interface ActivityChooserModelClient
  {
    public abstract void setActivityChooserModel(ActivityChooserModel paramActivityChooserModel);
  }
  
  public final class ActivityResolveInfo
    implements Comparable<ActivityResolveInfo>
  {
    public final ResolveInfo resolveInfo;
    public float weight;
    
    public ActivityResolveInfo(ResolveInfo paramResolveInfo)
    {
      this.resolveInfo = paramResolveInfo;
    }
    
    public int compareTo(ActivityResolveInfo paramActivityResolveInfo)
    {
      return Float.floatToIntBits(paramActivityResolveInfo.weight) - Float.floatToIntBits(this.weight);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (ActivityResolveInfo)paramObject;
      } while (Float.floatToIntBits(this.weight) == Float.floatToIntBits(((ActivityResolveInfo)paramObject).weight));
      return false;
    }
    
    public int hashCode()
    {
      return Float.floatToIntBits(this.weight) + 31;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("resolveInfo:").append(this.resolveInfo.toString());
      localStringBuilder.append("; weight:").append(new BigDecimal(this.weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface ActivitySorter
  {
    public abstract void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1);
  }
  
  private final class DefaultSorter
    implements ActivityChooserModel.ActivitySorter
  {
    private static final float WEIGHT_DECAY_COEFFICIENT = 0.95F;
    private final Map<String, ActivityChooserModel.ActivityResolveInfo> mPackageNameToActivityMap = new HashMap();
    
    private DefaultSorter() {}
    
    public void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1)
    {
      paramIntent = this.mPackageNameToActivityMap;
      paramIntent.clear();
      int j = paramList.size();
      int i = 0;
      float f1;
      if (i >= j)
      {
        i = paramList1.size();
        f1 = 1.0F;
        i -= 1;
      }
      for (;;)
      {
        if (i < 0)
        {
          Collections.sort(paramList);
          return;
          localObject = (ActivityChooserModel.ActivityResolveInfo)paramList.get(i);
          ((ActivityChooserModel.ActivityResolveInfo)localObject).weight = 0.0F;
          paramIntent.put(((ActivityChooserModel.ActivityResolveInfo)localObject).resolveInfo.activityInfo.packageName, localObject);
          i += 1;
          break;
        }
        Object localObject = (ActivityChooserModel.HistoricalRecord)paramList1.get(i);
        ActivityChooserModel.ActivityResolveInfo localActivityResolveInfo = (ActivityChooserModel.ActivityResolveInfo)paramIntent.get(((ActivityChooserModel.HistoricalRecord)localObject).activity.getPackageName());
        float f2 = f1;
        if (localActivityResolveInfo != null)
        {
          localActivityResolveInfo.weight += ((ActivityChooserModel.HistoricalRecord)localObject).weight * f1;
          f2 = f1 * 0.95F;
        }
        i -= 1;
        f1 = f2;
      }
    }
  }
  
  public static final class HistoricalRecord
  {
    public final ComponentName activity;
    public final long time;
    public final float weight;
    
    public HistoricalRecord(ComponentName paramComponentName, long paramLong, float paramFloat)
    {
      this.activity = paramComponentName;
      this.time = paramLong;
      this.weight = paramFloat;
    }
    
    public HistoricalRecord(String paramString, long paramLong, float paramFloat)
    {
      this(ComponentName.unflattenFromString(paramString), paramLong, paramFloat);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (HistoricalRecord)paramObject;
        if (this.activity == null)
        {
          if (((HistoricalRecord)paramObject).activity != null) {
            return false;
          }
        }
        else if (!this.activity.equals(((HistoricalRecord)paramObject).activity)) {
          return false;
        }
        if (this.time != ((HistoricalRecord)paramObject).time) {
          return false;
        }
      } while (Float.floatToIntBits(this.weight) == Float.floatToIntBits(((HistoricalRecord)paramObject).weight));
      return false;
    }
    
    public int hashCode()
    {
      if (this.activity == null) {}
      for (int i = 0;; i = this.activity.hashCode()) {
        return ((i + 31) * 31 + (int)(this.time ^ this.time >>> 32)) * 31 + Float.floatToIntBits(this.weight);
      }
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("; activity:").append(this.activity);
      localStringBuilder.append("; time:").append(this.time);
      localStringBuilder.append("; weight:").append(new BigDecimal(this.weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  private final class HistoryLoader
    implements Runnable
  {
    private HistoryLoader() {}
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 17	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   4: invokestatic 37	com/actionbarsherlock/widget/ActivityChooserModel:access$0	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Landroid/content/Context;
      //   7: aload_0
      //   8: getfield 17	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   11: invokestatic 40	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   14: invokevirtual 46	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
      //   17: astore_2
      //   18: invokestatic 52	android/util/Xml:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
      //   21: astore_3
      //   22: aload_3
      //   23: aload_2
      //   24: aconst_null
      //   25: invokeinterface 58 3 0
      //   30: iconst_0
      //   31: istore_1
      //   32: goto +408 -> 440
      //   35: ldc 60
      //   37: aload_3
      //   38: invokeinterface 64 1 0
      //   43: invokevirtual 70	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   46: ifne +65 -> 111
      //   49: new 31	org/xmlpull/v1/XmlPullParserException
      //   52: dup
      //   53: ldc 72
      //   55: invokespecial 75	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
      //   58: athrow
      //   59: astore_3
      //   60: invokestatic 78	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   63: new 80	java/lang/StringBuilder
      //   66: dup
      //   67: ldc 82
      //   69: invokespecial 83	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   72: aload_0
      //   73: getfield 17	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   76: invokestatic 40	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   79: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   82: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   85: aload_3
      //   86: invokestatic 96	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   89: pop
      //   90: aload_2
      //   91: ifnull +7 -> 98
      //   94: aload_2
      //   95: invokevirtual 101	java/io/FileInputStream:close	()V
      //   98: return
      //   99: astore_2
      //   100: return
      //   101: aload_3
      //   102: invokeinterface 105 1 0
      //   107: istore_1
      //   108: goto +332 -> 440
      //   111: new 107	java/util/ArrayList
      //   114: dup
      //   115: invokespecial 108	java/util/ArrayList:<init>	()V
      //   118: astore 4
      //   120: aload_3
      //   121: invokeinterface 105 1 0
      //   126: istore_1
      //   127: iload_1
      //   128: iconst_1
      //   129: if_icmpne +77 -> 206
      //   132: aload_0
      //   133: getfield 17	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   136: invokestatic 112	com/actionbarsherlock/widget/ActivityChooserModel:access$2	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/Object;
      //   139: astore_3
      //   140: aload_3
      //   141: monitorenter
      //   142: new 114	java/util/LinkedHashSet
      //   145: dup
      //   146: aload 4
      //   148: invokespecial 117	java/util/LinkedHashSet:<init>	(Ljava/util/Collection;)V
      //   151: astore 4
      //   153: aload_0
      //   154: getfield 17	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   157: invokestatic 121	com/actionbarsherlock/widget/ActivityChooserModel:access$3	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/util/List;
      //   160: astore 5
      //   162: aload 5
      //   164: invokeinterface 126 1 0
      //   169: iconst_1
      //   170: isub
      //   171: istore_1
      //   172: iload_1
      //   173: ifge +171 -> 344
      //   176: aload 5
      //   178: invokeinterface 126 1 0
      //   183: aload 4
      //   185: invokeinterface 129 1 0
      //   190: if_icmpne +180 -> 370
      //   193: aload_3
      //   194: monitorexit
      //   195: aload_2
      //   196: ifnull -98 -> 98
      //   199: aload_2
      //   200: invokevirtual 101	java/io/FileInputStream:close	()V
      //   203: return
      //   204: astore_2
      //   205: return
      //   206: iload_1
      //   207: iconst_3
      //   208: if_icmpeq -88 -> 120
      //   211: iload_1
      //   212: iconst_4
      //   213: if_icmpeq -93 -> 120
      //   216: ldc -125
      //   218: aload_3
      //   219: invokeinterface 64 1 0
      //   224: invokevirtual 70	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   227: ifne +55 -> 282
      //   230: new 31	org/xmlpull/v1/XmlPullParserException
      //   233: dup
      //   234: ldc -123
      //   236: invokespecial 75	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
      //   239: athrow
      //   240: astore_3
      //   241: invokestatic 78	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   244: new 80	java/lang/StringBuilder
      //   247: dup
      //   248: ldc 82
      //   250: invokespecial 83	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   253: aload_0
      //   254: getfield 17	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   257: invokestatic 40	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   260: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   263: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   266: aload_3
      //   267: invokestatic 96	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   270: pop
      //   271: aload_2
      //   272: ifnull -174 -> 98
      //   275: aload_2
      //   276: invokevirtual 101	java/io/FileInputStream:close	()V
      //   279: return
      //   280: astore_2
      //   281: return
      //   282: aload 4
      //   284: new 135	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord
      //   287: dup
      //   288: aload_3
      //   289: aconst_null
      //   290: ldc -119
      //   292: invokeinterface 141 3 0
      //   297: aload_3
      //   298: aconst_null
      //   299: ldc -113
      //   301: invokeinterface 141 3 0
      //   306: invokestatic 149	java/lang/Long:parseLong	(Ljava/lang/String;)J
      //   309: aload_3
      //   310: aconst_null
      //   311: ldc -105
      //   313: invokeinterface 141 3 0
      //   318: invokestatic 157	java/lang/Float:parseFloat	(Ljava/lang/String;)F
      //   321: invokespecial 160	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord:<init>	(Ljava/lang/String;JF)V
      //   324: invokeinterface 163 2 0
      //   329: pop
      //   330: goto -210 -> 120
      //   333: astore_3
      //   334: aload_2
      //   335: ifnull +7 -> 342
      //   338: aload_2
      //   339: invokevirtual 101	java/io/FileInputStream:close	()V
      //   342: aload_3
      //   343: athrow
      //   344: aload 4
      //   346: aload 5
      //   348: iload_1
      //   349: invokeinterface 167 2 0
      //   354: checkcast 135	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord
      //   357: invokeinterface 168 2 0
      //   362: pop
      //   363: iload_1
      //   364: iconst_1
      //   365: isub
      //   366: istore_1
      //   367: goto -195 -> 172
      //   370: aload 5
      //   372: invokeinterface 171 1 0
      //   377: aload 5
      //   379: aload 4
      //   381: invokeinterface 175 2 0
      //   386: pop
      //   387: aload_0
      //   388: getfield 17	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   391: iconst_1
      //   392: invokestatic 179	com/actionbarsherlock/widget/ActivityChooserModel:access$4	(Lcom/actionbarsherlock/widget/ActivityChooserModel;Z)V
      //   395: aload_0
      //   396: getfield 17	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   399: invokestatic 183	com/actionbarsherlock/widget/ActivityChooserModel:access$5	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Landroid/os/Handler;
      //   402: new 11	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader$1
      //   405: dup
      //   406: aload_0
      //   407: invokespecial 186	com/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader$1:<init>	(Lcom/actionbarsherlock/widget/ActivityChooserModel$HistoryLoader;)V
      //   410: invokevirtual 192	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   413: pop
      //   414: aload_3
      //   415: monitorexit
      //   416: aload_2
      //   417: ifnull -319 -> 98
      //   420: aload_2
      //   421: invokevirtual 101	java/io/FileInputStream:close	()V
      //   424: return
      //   425: astore_2
      //   426: return
      //   427: astore 4
      //   429: aload_3
      //   430: monitorexit
      //   431: aload 4
      //   433: athrow
      //   434: astore_2
      //   435: return
      //   436: astore_2
      //   437: goto -95 -> 342
      //   440: iload_1
      //   441: iconst_1
      //   442: if_icmpeq -407 -> 35
      //   445: iload_1
      //   446: iconst_2
      //   447: if_icmpne -346 -> 101
      //   450: goto -415 -> 35
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	453	0	this	HistoryLoader
      //   31	417	1	i	int
      //   17	78	2	localFileInputStream	java.io.FileInputStream
      //   99	101	2	localFileNotFoundException	java.io.FileNotFoundException
      //   204	72	2	localIOException1	java.io.IOException
      //   280	141	2	localIOException2	java.io.IOException
      //   425	1	2	localIOException3	java.io.IOException
      //   434	1	2	localIOException4	java.io.IOException
      //   436	1	2	localIOException5	java.io.IOException
      //   21	17	3	localXmlPullParser	org.xmlpull.v1.XmlPullParser
      //   59	62	3	localXmlPullParserException	org.xmlpull.v1.XmlPullParserException
      //   240	70	3	localIOException6	java.io.IOException
      //   333	97	3	localObject2	Object
      //   118	262	4	localObject3	Object
      //   427	5	4	localObject4	Object
      //   160	218	5	localList	List
      // Exception table:
      //   from	to	target	type
      //   18	30	59	org/xmlpull/v1/XmlPullParserException
      //   35	59	59	org/xmlpull/v1/XmlPullParserException
      //   101	108	59	org/xmlpull/v1/XmlPullParserException
      //   111	120	59	org/xmlpull/v1/XmlPullParserException
      //   120	127	59	org/xmlpull/v1/XmlPullParserException
      //   132	142	59	org/xmlpull/v1/XmlPullParserException
      //   216	240	59	org/xmlpull/v1/XmlPullParserException
      //   282	330	59	org/xmlpull/v1/XmlPullParserException
      //   431	434	59	org/xmlpull/v1/XmlPullParserException
      //   0	18	99	java/io/FileNotFoundException
      //   199	203	204	java/io/IOException
      //   18	30	240	java/io/IOException
      //   35	59	240	java/io/IOException
      //   101	108	240	java/io/IOException
      //   111	120	240	java/io/IOException
      //   120	127	240	java/io/IOException
      //   132	142	240	java/io/IOException
      //   216	240	240	java/io/IOException
      //   282	330	240	java/io/IOException
      //   431	434	240	java/io/IOException
      //   275	279	280	java/io/IOException
      //   18	30	333	finally
      //   35	59	333	finally
      //   60	90	333	finally
      //   101	108	333	finally
      //   111	120	333	finally
      //   120	127	333	finally
      //   132	142	333	finally
      //   216	240	333	finally
      //   241	271	333	finally
      //   282	330	333	finally
      //   431	434	333	finally
      //   420	424	425	java/io/IOException
      //   142	172	427	finally
      //   176	195	427	finally
      //   344	363	427	finally
      //   370	416	427	finally
      //   429	431	427	finally
      //   94	98	434	java/io/IOException
      //   338	342	436	java/io/IOException
    }
  }
  
  private final class HistoryPersister
    implements Runnable
  {
    private HistoryPersister() {}
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   4: invokestatic 35	com/actionbarsherlock/widget/ActivityChooserModel:access$2	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/Object;
      //   7: astore 4
      //   9: aload 4
      //   11: monitorenter
      //   12: new 37	java/util/ArrayList
      //   15: dup
      //   16: aload_0
      //   17: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   20: invokestatic 41	com/actionbarsherlock/widget/ActivityChooserModel:access$3	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/util/List;
      //   23: invokespecial 44	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
      //   26: astore 5
      //   28: aload 4
      //   30: monitorexit
      //   31: aload_0
      //   32: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   35: invokestatic 48	com/actionbarsherlock/widget/ActivityChooserModel:access$0	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Landroid/content/Context;
      //   38: aload_0
      //   39: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   42: invokestatic 52	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   45: iconst_0
      //   46: invokevirtual 58	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
      //   49: astore_3
      //   50: invokestatic 64	android/util/Xml:newSerializer	()Lorg/xmlpull/v1/XmlSerializer;
      //   53: astore 4
      //   55: aload 4
      //   57: aload_3
      //   58: aconst_null
      //   59: invokeinterface 70 3 0
      //   64: aload 4
      //   66: ldc 72
      //   68: iconst_1
      //   69: invokestatic 78	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   72: invokeinterface 82 3 0
      //   77: aload 4
      //   79: aconst_null
      //   80: ldc 84
      //   82: invokeinterface 88 3 0
      //   87: pop
      //   88: aload 5
      //   90: invokeinterface 94 1 0
      //   95: istore_2
      //   96: iconst_0
      //   97: istore_1
      //   98: iload_1
      //   99: iload_2
      //   100: if_icmplt +68 -> 168
      //   103: aload 4
      //   105: aconst_null
      //   106: ldc 84
      //   108: invokeinterface 97 3 0
      //   113: pop
      //   114: aload 4
      //   116: invokeinterface 100 1 0
      //   121: aload_3
      //   122: ifnull +7 -> 129
      //   125: aload_3
      //   126: invokevirtual 105	java/io/FileOutputStream:close	()V
      //   129: return
      //   130: astore_3
      //   131: aload 4
      //   133: monitorexit
      //   134: aload_3
      //   135: athrow
      //   136: astore_3
      //   137: invokestatic 109	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   140: new 111	java/lang/StringBuilder
      //   143: dup
      //   144: ldc 113
      //   146: invokespecial 116	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   149: aload_0
      //   150: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   153: invokestatic 52	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   156: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   159: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   162: aload_3
      //   163: invokestatic 129	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   166: pop
      //   167: return
      //   168: aload 5
      //   170: iconst_0
      //   171: invokeinterface 133 2 0
      //   176: checkcast 135	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord
      //   179: astore 6
      //   181: aload 4
      //   183: aconst_null
      //   184: ldc -119
      //   186: invokeinterface 88 3 0
      //   191: pop
      //   192: aload 4
      //   194: aconst_null
      //   195: ldc -117
      //   197: aload 6
      //   199: getfield 142	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord:activity	Landroid/content/ComponentName;
      //   202: invokevirtual 147	android/content/ComponentName:flattenToString	()Ljava/lang/String;
      //   205: invokeinterface 151 4 0
      //   210: pop
      //   211: aload 4
      //   213: aconst_null
      //   214: ldc -103
      //   216: aload 6
      //   218: getfield 156	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord:time	J
      //   221: invokestatic 161	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   224: invokeinterface 151 4 0
      //   229: pop
      //   230: aload 4
      //   232: aconst_null
      //   233: ldc -93
      //   235: aload 6
      //   237: getfield 166	com/actionbarsherlock/widget/ActivityChooserModel$HistoricalRecord:weight	F
      //   240: invokestatic 169	java/lang/String:valueOf	(F)Ljava/lang/String;
      //   243: invokeinterface 151 4 0
      //   248: pop
      //   249: aload 4
      //   251: aconst_null
      //   252: ldc -119
      //   254: invokeinterface 97 3 0
      //   259: pop
      //   260: iload_1
      //   261: iconst_1
      //   262: iadd
      //   263: istore_1
      //   264: goto -166 -> 98
      //   267: astore 4
      //   269: invokestatic 109	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   272: new 111	java/lang/StringBuilder
      //   275: dup
      //   276: ldc 113
      //   278: invokespecial 116	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   281: aload_0
      //   282: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   285: invokestatic 52	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   288: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   291: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   294: aload 4
      //   296: invokestatic 129	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   299: pop
      //   300: aload_3
      //   301: ifnull -172 -> 129
      //   304: aload_3
      //   305: invokevirtual 105	java/io/FileOutputStream:close	()V
      //   308: return
      //   309: astore_3
      //   310: return
      //   311: astore 4
      //   313: invokestatic 109	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   316: new 111	java/lang/StringBuilder
      //   319: dup
      //   320: ldc 113
      //   322: invokespecial 116	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   325: aload_0
      //   326: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   329: invokestatic 52	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   332: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   335: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   338: aload 4
      //   340: invokestatic 129	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   343: pop
      //   344: aload_3
      //   345: ifnull -216 -> 129
      //   348: aload_3
      //   349: invokevirtual 105	java/io/FileOutputStream:close	()V
      //   352: return
      //   353: astore_3
      //   354: return
      //   355: astore 4
      //   357: invokestatic 109	com/actionbarsherlock/widget/ActivityChooserModel:access$8	()Ljava/lang/String;
      //   360: new 111	java/lang/StringBuilder
      //   363: dup
      //   364: ldc 113
      //   366: invokespecial 116	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   369: aload_0
      //   370: getfield 15	com/actionbarsherlock/widget/ActivityChooserModel$HistoryPersister:this$0	Lcom/actionbarsherlock/widget/ActivityChooserModel;
      //   373: invokestatic 52	com/actionbarsherlock/widget/ActivityChooserModel:access$1	(Lcom/actionbarsherlock/widget/ActivityChooserModel;)Ljava/lang/String;
      //   376: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   379: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   382: aload 4
      //   384: invokestatic 129	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   387: pop
      //   388: aload_3
      //   389: ifnull -260 -> 129
      //   392: aload_3
      //   393: invokevirtual 105	java/io/FileOutputStream:close	()V
      //   396: return
      //   397: astore_3
      //   398: return
      //   399: astore 4
      //   401: aload_3
      //   402: ifnull +7 -> 409
      //   405: aload_3
      //   406: invokevirtual 105	java/io/FileOutputStream:close	()V
      //   409: aload 4
      //   411: athrow
      //   412: astore_3
      //   413: goto -4 -> 409
      //   416: astore_3
      //   417: return
      //   418: astore_3
      //   419: goto -288 -> 131
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	422	0	this	HistoryPersister
      //   97	167	1	i	int
      //   95	6	2	j	int
      //   49	77	3	localFileOutputStream	java.io.FileOutputStream
      //   130	5	3	localObject1	Object
      //   136	169	3	localFileNotFoundException	java.io.FileNotFoundException
      //   309	40	3	localIOException1	java.io.IOException
      //   353	40	3	localIOException2	java.io.IOException
      //   397	9	3	localIOException3	java.io.IOException
      //   412	1	3	localIOException4	java.io.IOException
      //   416	1	3	localIOException5	java.io.IOException
      //   418	1	3	localObject2	Object
      //   7	243	4	localObject3	Object
      //   267	28	4	localIllegalArgumentException	IllegalArgumentException
      //   311	28	4	localIllegalStateException	IllegalStateException
      //   355	28	4	localIOException6	java.io.IOException
      //   399	11	4	localObject4	Object
      //   26	143	5	localArrayList	ArrayList
      //   179	57	6	localHistoricalRecord	ActivityChooserModel.HistoricalRecord
      // Exception table:
      //   from	to	target	type
      //   12	28	130	finally
      //   131	134	130	finally
      //   31	50	136	java/io/FileNotFoundException
      //   55	96	267	java/lang/IllegalArgumentException
      //   103	121	267	java/lang/IllegalArgumentException
      //   168	260	267	java/lang/IllegalArgumentException
      //   304	308	309	java/io/IOException
      //   55	96	311	java/lang/IllegalStateException
      //   103	121	311	java/lang/IllegalStateException
      //   168	260	311	java/lang/IllegalStateException
      //   348	352	353	java/io/IOException
      //   55	96	355	java/io/IOException
      //   103	121	355	java/io/IOException
      //   168	260	355	java/io/IOException
      //   392	396	397	java/io/IOException
      //   55	96	399	finally
      //   103	121	399	finally
      //   168	260	399	finally
      //   269	300	399	finally
      //   313	344	399	finally
      //   357	388	399	finally
      //   405	409	412	java/io/IOException
      //   125	129	416	java/io/IOException
      //   28	31	418	finally
    }
  }
  
  public static abstract interface OnChooseActivityListener
  {
    public abstract boolean onChooseActivity(ActivityChooserModel paramActivityChooserModel, Intent paramIntent);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\actionbarsherlock\widget\ActivityChooserModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */