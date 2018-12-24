package gr.uom.adroid.mylocation;

public class Location {

    long lat, ing;

    public Location (long lat , long ing ){
        this.lat = lat;
        this.ing = ing;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public void setIng(long ing) {
        this.ing = ing;
    }

    public long getLat() {

        return lat;
    }

    public long getIng() {
        return ing;
    }
}
