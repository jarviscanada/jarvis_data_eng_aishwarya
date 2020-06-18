package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMention {

  private int[] indices = new int[2];
  private String name;
  private String screenName;
  private long id;
  private String idStr;
  private String tweet;

  public UserMention(String tweet) {
    this.tweet = tweet;
  }

  public String getIndices() {
    return Arrays.toString(indices);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getScreenName() {
    return screenName;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getIdStr() {
    return idStr;
  }

  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }

  public List<UserMention> userMentionComponents() {
    Pattern pattern = Pattern.compile("(@\\w+)\\b", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(this.tweet);
    List<UserMention> users = new ArrayList<>();
    while (matcher.find()) {
      UserMention userMention = new UserMention(tweet);
      userMention.indices[0] = this.tweet.indexOf(matcher.group(1));
      userMention.indices[1] = (userMention.indices[0] + matcher.group(1).length());
      userMention.screenName = matcher.group(1).substring(1);
      users.add(userMention);
    }
    return users;
    //Don't know how to get name and id of the user
  }

//  @Override
//  public String toString() {
//    return "\n\t\t\t{\n"
//        + "\t\t\t\t\t\"name\":\"" + getName()
//        + "\",\n\t\t\t\t\t\"indices\":" + getIndices()
//        + ",\n\t\t\t\t\t\"screen_name\":\"" + getScreenName()
//        + "\",\n\t\t\t\t\t\"id\":" + getId()
//        + "\n\t\t\t\t\t\"id_str\":\"" + getIdStr()
//        + "\"\n\t\t\t}";
//  }

//  public static void main(String[] args) {
//    String tweet = "Good Morning @someone and @others";
//    UserMention us = new UserMention(tweet);
//    System.out.println(us.userMentionComponents());
//  }
}