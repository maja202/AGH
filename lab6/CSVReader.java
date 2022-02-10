package lab6;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader=false;

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();


    CSVReader(String filename,String delimiter) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
    }

    CSVReader(String filename) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filename));
    }

    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    public List<String> getColumnLabels(){
        return columnLabels;
    }

    int getRecordLength(){
        return current.length;
    }

    boolean isMissing(int columnIndex){

        if(columnIndex >= getRecordLength()) {
            return true;
        }

        if(columnIndex == -1) {
            return true;
        }

        if(current[columnIndex] == null) {
            return true;
        }

        return current[columnIndex].length() == 0;
    }


    boolean isMissing(String columnLabel){
        return isMissing(columnLabelsToInt.getOrDefault(columnLabel,-1));
    }


    void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return;
        }

        String[] header = line.split(delimiter);

        for (int i = 0; i < header.length; i++) {
            this.columnLabels.add(header[i]);
            this.columnLabelsToInt.put(header[i], i);
        }
    }

    String[]current;

    boolean next() throws IOException {
        try {
            String line = reader.readLine();

            if (line == null) {
                return false;
            }

            String[] record = line.split(delimiter);
            this.current = new String[record.length];

            for (int i = 0; i < record.length; i++) {
                this.current[i] = record[i];
            }

            return true;

        } catch (IOException e) {
            throw e;
        }

    }

    public int getInt(String columnLabel) {
        return getInt(columnLabelsToInt.getOrDefault(columnLabel,-1));
    }

    public long getLong(String columnLabel) {
        return getLong(columnLabelsToInt.getOrDefault(columnLabel,-1));
    }

    public double getDouble(String columnLabel) {
        return getDouble(columnLabelsToInt.getOrDefault(columnLabel,-1));
    }

    String get(String columnLabel) {
        return current[columnLabelsToInt.get(columnLabel)];
    }

    public int getInt(int column) {
        if(!isMissing(column)) {
            try {
                return Integer.parseInt(getValueFromCurrent(column));
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }

    public long getLong(int column) {
        if(!isMissing(column)) {
            try {
                return Long.parseLong(getValueFromCurrent(column));
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }

    public double getDouble(int column) {
        if(!isMissing(column)) {
            try {
                return Double.parseDouble(getValueFromCurrent(column));
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }

    String get(int columnIndex) {
        return current[columnIndex];
    }

    private String getValueFromCurrent(int column) {
        if( column == -1 || column >= current.length )
            return null;
        return current[column];
    }

    public LocalTime getTime(String columnLabel,String pattern) {
        return getTime(columnLabelsToInt.getOrDefault(columnLabel,-1),pattern);
    }

    public LocalTime getTime(int column,String pattern) {
        if(!isMissing(column)) {
            try {
                return LocalTime.parse(getValueFromCurrent(column), DateTimeFormatter.ofPattern(pattern));
            } catch (Exception e) {
                return LocalTime.MIN;
            }
        }
        return LocalTime.MIN;
    }

    public LocalDate getDate(String columnLabel, String pattern) {
        return getDate(columnLabelsToInt.getOrDefault(columnLabel,-1),pattern);
    }

    public LocalDate getDate(int column,String pattern) {
        if(!isMissing(column)) {
            try {
                return LocalDate.parse(getValueFromCurrent(column), DateTimeFormatter.ofPattern(pattern));
            } catch (Exception e) {
                return LocalDate.MIN;
            }
        }
        return LocalDate.MIN;
    }

}