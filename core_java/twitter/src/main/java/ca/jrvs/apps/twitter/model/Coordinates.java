package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinates {

  private List<Double> coordinates;
  private String type = "Point";

  public List<Double> getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(List<Double> coordinates) {
    this.coordinates = coordinates;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}