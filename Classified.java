/** THIS PROGRAM WAS MADE BY MAC HAGUE
 * FOR ACO 102
 * CompProgAssignPH1
 */

/**
 * This class represents a set of classified earthquake data, including the earthquake's magnitude, location,
 * date/time, and classification level. Classified data is data that has been reviewed and classified by a
 * seismologist or other expert, and is typically collected through manual or semi-automated means.
 *
 * The Classified class supports reading earthquake data from a CSV file and storing it as a collection of
 * ClassifiedEarthquake objects. It also provides methods for sorting and filtering the data based on various
 * criteria.
 */
public class Classified extends Earthquake {
    private String magnitudeClass;
    public Classified(String d, double lat, double lon, double magnitude, String i, String p) {
        super(d, lat, lon, magnitude, i, p);
        classify();
    }

    @Override
    public String toString() {
        return super.getDateTimeZ() +
                "," + super.getLatitude() +
                "," + super.getLongitude() +
                "," + super.getMagnitude() +
                "," + super.getId() +
                "," + super.getPlace() +
                "," + magnitudeClass;
    }


    public void classify() {
        double magnitude = super.getMagnitude();
        if (magnitude < 4.0) {
            this.magnitudeClass = "Minor";
        } else if (magnitude < 5.0) {
            this.magnitudeClass = "Light";
        } else if (magnitude < 6.0) {
            this.magnitudeClass = "Moderate";
        } else if (magnitude < 7.0) {
            this.magnitudeClass = "Strong";
        } else if (magnitude < 8.0) {
            this.magnitudeClass = "Major";
        } else {
            this.magnitudeClass = "Great";
        }
    }

    public String getMagnitudeClass() {
        return magnitudeClass;
    }
}
