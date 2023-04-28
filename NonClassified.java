/** THIS PROGRAM WAS MADE BY MAC HAGUE
 * FOR ACO 101
 * CompProgAssignPH1
 */

/**
 * This class represents a set of non-classified earthquake data, including the earthquake's magnitude, location,
 * and date/time. Non-classified data is data that has not been assigned a classification by a seismologist or
 * other expert, and is typically collected through crowd-sourced or automated means.
 *
 * The NonClassified class supports reading earthquake data from a CSV file and storing it as a collection of
 * NonClassifiedEarthquake objects. It also provides methods for sorting and filtering the data based on various
 * criteria.
 */
public class NonClassified extends Earthquake {
    public NonClassified(String d, double lat, double lon, double magnitude, String i, String p) {
        super(d, lat, lon, magnitude, i, p);
    }

    @Override
    public String toString() {
        return super.getDateTimeZ() +
                "," + super.getLatitude() +
                "," + super.getLongitude() +
                "," + super.getMagnitude() +
                "," + super.getId() +
                "," + super.getPlace();
    }
}
