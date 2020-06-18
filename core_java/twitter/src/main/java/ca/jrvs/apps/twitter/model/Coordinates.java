package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinates {

  private List<Double> coordinates = new ArrayList<>();
  private String type = "Point";
  //private double longitude;
  //private double latitude;

  public Coordinates(double longitude, double latitude) {
    //this.longitude = longitude;
    //this.latitude = latitude;
    this.coordinates.add(0,longitude);
    this.coordinates.add(1,latitude);
  }

  public List<Coordinates> getCoordinatesComponents() {
    List<Coordinates> coordinates = new ArrayList<>();
    Coordinates coord = new Coordinates(this.coordinates);
    coordinates.add(coord);
    return coordinates;
  }

  public Coordinates(List<Double> coordinates) {
    this.coordinates = coordinates;
  }

  public List<Double> getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(List<Double> coordinates) {
    this.coordinates = coordinates;
  }

  public String getType() {
    return type;
  }

//  @Override
//  public String toString() {
//    return "{\n"
//        + "\t\t\"coordinates\":" + getCoordinates()
//        + ",\n\t\t\"type\":\"" + getType()
//        + "\"\n\t}";
//  }

//  public static void main(String[] args) {
//    Coordinates c = new Coordinates(1d,-1d);
//    System.out.println(c.getCoordinatesComponents().get(0));
//  }
}