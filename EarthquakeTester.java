import java.io.*;
import java.util.*;
/** THIS PROGRAM WAS MADE BY MAC HAGUE
 * FOR ACO 102
 * CompProgAssignPH1
 */



/**
 * This class provides a testing environment for the Earthquake class and associated classes.
 *
 * A series of input files containing earthquake data are sorted and processed using the Earthquake and
 * EarthquakeDataReader classes, and the resulting data is output to a file using the EarthquakeDataWriter class.
 * The program reads command-line arguments to determine the input and output file paths, and supports multiple
 * input files and sorting criteria.
 */
public class EarthquakeTester {

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Usage: java EarthquakeTester inputFile invalidFile outputFile");
            System.exit(1);
        }

        List<String> invalidInputs = new ArrayList<>();
        String filePath = System.getProperty("user.home") + "\\Downloads\\";
        String fileName = args[0].toString();

        File file = new File(filePath + fileName);
        File errorFile = new File(filePath + args[1].toString());
        File sortedFile = new File(filePath + args[2].toString());

        List<Earthquake> earthquakes = readFile(file, invalidInputs);
        Collections.sort(earthquakes);

        writeFiles(earthquakes, invalidInputs, sortedFile, errorFile);
    }

    public static List<Earthquake> readFile(File inputFile, List<String> invalidInputs) throws FileNotFoundException {
        Scanner scanner = new Scanner(inputFile);
        List<Earthquake> earthquakes = new ArrayList<>();
        int lineNumber = 0;

        while (scanner.hasNextLine()) {
            lineNumber++;
            String line = scanner.nextLine();
            String[] values = line.split(",");

            if (values.length != 7) {
                invalidInputs.add("Line " + lineNumber + ": There are too many or too few fields in this entry.");
                continue;
            }

            String time = values[0];
            if (time.length() != 24) {
                invalidInputs.add("Line " + lineNumber + ": There must be 24 characters in the dateTimeZ string.");
                continue;
            }
            if (!time.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}Z")) {
                invalidInputs.add("Line " + lineNumber + ": The dateTimeZ format is incorrect.");
                continue;
            }

            try {
                double latitude = Double.parseDouble(values[1]);
                if (latitude < -90.0 || latitude > 90.0) {
                    invalidInputs.add("Line " + lineNumber + ": Latitude value out of range.");
                    continue;
                }

                double longitude = Double.parseDouble(values[2]);
                if (longitude < -180.0 || longitude > 180.0) {
                    invalidInputs.add("Line " + lineNumber + ": Longitude value out of range.");
                    continue;
                }

                double magnitude = Double.parseDouble(values[3]);
                if (magnitude < -1.0 || magnitude > 10.0) {
                    invalidInputs.add("Line " + lineNumber + ": Magnitude value out of range.");
                    continue;
                }

                String id = values[4];
                String place = values[5] + "," + values[6];

                Earthquake earthquake;
                if (magnitude < 3.0) {
                    earthquake = new NonClassified(time, latitude, longitude, magnitude, id, place);
                } else {
                    earthquake = new Classified(time, latitude, longitude, magnitude, id, place);
                }
                earthquakes.add(earthquake);
            } catch (NumberFormatException e) {
                invalidInputs.add("Line " + lineNumber + ": The wrong format is used; must be a double.");
            }
        }

        scanner.close();
        return earthquakes;
    }

    public static void writeFiles(List<Earthquake> earthquakes, List<String> invalidInputs, File sortedFile, File errorFile) throws IOException {
        FileWriter fileWriter = new FileWriter(sortedFile);
        for (Earthquake earthquake : earthquakes) {
            fileWriter.write(earthquake.toString() + "\n");
        }
        fileWriter.flush();

        fileWriter = new FileWriter(errorFile);
        for (String error : invalidInputs) {
            fileWriter.write(error + "\n");
        }
        fileWriter.close();
    }
}
