package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class mp
  implements SafeParcelable
{
  public static final mq CREATOR = new mq();
  public static final mp afC = bZ("accounting");
  public static final mp afD = bZ("airport");
  public static final mp afE = bZ("amusement_park");
  public static final mp afF = bZ("aquarium");
  public static final mp afG = bZ("art_gallery");
  public static final mp afH = bZ("atm");
  public static final mp afI = bZ("bakery");
  public static final mp afJ = bZ("bank");
  public static final mp afK = bZ("bar");
  public static final mp afL = bZ("beauty_salon");
  public static final mp afM = bZ("bicycle_store");
  public static final mp afN = bZ("book_store");
  public static final mp afO = bZ("bowling_alley");
  public static final mp afP = bZ("bus_station");
  public static final mp afQ = bZ("cafe");
  public static final mp afR = bZ("campground");
  public static final mp afS = bZ("car_dealer");
  public static final mp afT = bZ("car_rental");
  public static final mp afU = bZ("car_repair");
  public static final mp afV = bZ("car_wash");
  public static final mp afW = bZ("casino");
  public static final mp afX = bZ("cemetery");
  public static final mp afY = bZ("church");
  public static final mp afZ = bZ("city_hall");
  public static final mp agA;
  public static final mp agB;
  public static final mp agC;
  public static final mp agD;
  public static final mp agE;
  public static final mp agF;
  public static final mp agG;
  public static final mp agH;
  public static final mp agI;
  public static final mp agJ;
  public static final mp agK;
  public static final mp agL;
  public static final mp agM;
  public static final mp agN;
  public static final mp agO;
  public static final mp agP;
  public static final mp agQ;
  public static final mp agR;
  public static final mp agS;
  public static final mp agT;
  public static final mp agU;
  public static final mp agV;
  public static final mp agW;
  public static final mp agX;
  public static final mp agY;
  public static final mp agZ;
  public static final mp aga = bZ("clothing_store");
  public static final mp agb = bZ("convenience_store");
  public static final mp agc = bZ("courthouse");
  public static final mp agd = bZ("dentist");
  public static final mp age = bZ("department_store");
  public static final mp agf = bZ("doctor");
  public static final mp agg = bZ("electrician");
  public static final mp agh = bZ("electronics_store");
  public static final mp agi = bZ("embassy");
  public static final mp agj = bZ("establishment");
  public static final mp agk = bZ("finance");
  public static final mp agl = bZ("fire_station");
  public static final mp agm = bZ("florist");
  public static final mp agn = bZ("food");
  public static final mp ago = bZ("funeral_home");
  public static final mp agp = bZ("furniture_store");
  public static final mp agq = bZ("gas_station");
  public static final mp agr = bZ("general_contractor");
  public static final mp ags = bZ("grocery_or_supermarket");
  public static final mp agt = bZ("gym");
  public static final mp agu = bZ("hair_care");
  public static final mp agv = bZ("hardware_store");
  public static final mp agw = bZ("health");
  public static final mp agx = bZ("hindu_temple");
  public static final mp agy = bZ("home_goods_store");
  public static final mp agz = bZ("hospital");
  public static final mp ahA;
  public static final mp ahB;
  public static final mp ahC;
  public static final mp ahD;
  public static final mp ahE;
  public static final mp ahF;
  public static final mp ahG;
  public static final mp ahH;
  public static final mp ahI;
  public static final mp ahJ;
  public static final mp ahK;
  public static final mp ahL;
  public static final mp ahM;
  public static final mp ahN;
  public static final mp ahO;
  public static final mp ahP;
  public static final mp ahQ;
  public static final mp ahR;
  public static final mp ahS;
  public static final mp ahT;
  public static final mp ahU;
  public static final mp ahV;
  public static final mp ahW;
  public static final mp ahX;
  public static final mp aha;
  public static final mp ahb;
  public static final mp ahc;
  public static final mp ahd;
  public static final mp ahe;
  public static final mp ahf;
  public static final mp ahg;
  public static final mp ahh;
  public static final mp ahi;
  public static final mp ahj;
  public static final mp ahk;
  public static final mp ahl;
  public static final mp ahm;
  public static final mp ahn;
  public static final mp aho;
  public static final mp ahp;
  public static final mp ahq;
  public static final mp ahr;
  public static final mp ahs;
  public static final mp aht;
  public static final mp ahu;
  public static final mp ahv;
  public static final mp ahw;
  public static final mp ahx;
  public static final mp ahy;
  public static final mp ahz;
  final int BR;
  final String uO;
  
  static
  {
    agA = bZ("insurance_agency");
    agB = bZ("jewelry_store");
    agC = bZ("laundry");
    agD = bZ("lawyer");
    agE = bZ("library");
    agF = bZ("liquor_store");
    agG = bZ("local_government_office");
    agH = bZ("locksmith");
    agI = bZ("lodging");
    agJ = bZ("meal_delivery");
    agK = bZ("meal_takeaway");
    agL = bZ("mosque");
    agM = bZ("movie_rental");
    agN = bZ("movie_theater");
    agO = bZ("moving_company");
    agP = bZ("museum");
    agQ = bZ("night_club");
    agR = bZ("painter");
    agS = bZ("park");
    agT = bZ("parking");
    agU = bZ("pet_store");
    agV = bZ("pharmacy");
    agW = bZ("physiotherapist");
    agX = bZ("place_of_worship");
    agY = bZ("plumber");
    agZ = bZ("police");
    aha = bZ("post_office");
    ahb = bZ("real_estate_agency");
    ahc = bZ("restaurant");
    ahd = bZ("roofing_contractor");
    ahe = bZ("rv_park");
    ahf = bZ("school");
    ahg = bZ("shoe_store");
    ahh = bZ("shopping_mall");
    ahi = bZ("spa");
    ahj = bZ("stadium");
    ahk = bZ("storage");
    ahl = bZ("store");
    ahm = bZ("subway_station");
    ahn = bZ("synagogue");
    aho = bZ("taxi_stand");
    ahp = bZ("train_station");
    ahq = bZ("travel_agency");
    ahr = bZ("university");
    ahs = bZ("veterinary_care");
    aht = bZ("zoo");
    ahu = bZ("administrative_area_level_1");
    ahv = bZ("administrative_area_level_2");
    ahw = bZ("administrative_area_level_3");
    ahx = bZ("colloquial_area");
    ahy = bZ("country");
    ahz = bZ("floor");
    ahA = bZ("geocode");
    ahB = bZ("intersection");
    ahC = bZ("locality");
    ahD = bZ("natural_feature");
    ahE = bZ("neighborhood");
    ahF = bZ("political");
    ahG = bZ("point_of_interest");
    ahH = bZ("post_box");
    ahI = bZ("postal_code");
    ahJ = bZ("postal_code_prefix");
    ahK = bZ("postal_town");
    ahL = bZ("premise");
    ahM = bZ("room");
    ahN = bZ("route");
    ahO = bZ("street_address");
    ahP = bZ("sublocality");
    ahQ = bZ("sublocality_level_1");
    ahR = bZ("sublocality_level_2");
    ahS = bZ("sublocality_level_3");
    ahT = bZ("sublocality_level_4");
    ahU = bZ("sublocality_level_5");
    ahV = bZ("subpremise");
    ahW = bZ("transit_station");
    ahX = bZ("other");
  }
  
  mp(int paramInt, String paramString)
  {
    o.aZ(paramString);
    this.BR = paramInt;
    this.uO = paramString;
  }
  
  public static mp bZ(String paramString)
  {
    return new mp(0, paramString);
  }
  
  public int describeContents()
  {
    mq localmq = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof mp)) && (this.uO.equals(((mp)paramObject).uO));
  }
  
  public int hashCode()
  {
    return this.uO.hashCode();
  }
  
  public String toString()
  {
    return this.uO;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    mq localmq = CREATOR;
    mq.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\internal\mp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */