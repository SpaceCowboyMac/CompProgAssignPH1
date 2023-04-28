/** THIS PROGRAM WAS MADE BY MAC HAGUE
 * FOR ACO 102
 * CompProgAssignPH1
 */



/**
 * This class represents an earthquake event, including its magnitude, location, and time.
 *
 * The Earthquake class supports creating new earthquake objects with a specific magnitude, location, and date/time.
 * It also provides methods for retrieving and setting the various properties of the earthquake, and for comparing the
 * earthquake to other earthquake objects based on various criteria.
 */
public class Earthquake implements Comparable<Earthquake> {
    private String dateTimeZ;
    private Double latitude;
    private Double longitude;
    private Double magnitude;
    private String id;
    private String place;

    public Earthquake(String d, double lat, double lon, double m, String i, String p) {
        this.dateTimeZ = d;
        this.latitude = lat;
        this.longitude = lon;
        this.magnitude = m;
        this.id = i;
        this.place = p;
    }

    @Override
    public String toString() {
        return dateTimeZ +
                "," + latitude +
                "," + longitude +
                "," + magnitude +
                "," + id +
                "," + place;
    }

    public String getDateTimeZ() {
        return dateTimeZ;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public String getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public int compareTo(Earthquake other) {
        if (this.magnitude < other.magnitude) {
            return 1;
        } else if (this.magnitude > other.magnitude) {
            return -1;
        } else {
            return 0;
        }
    }
}
