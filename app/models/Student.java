package models;

import java.util.UUID;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Student extends BasicDBObject{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public Student(){}
  
  public Student(String name, String age, String score, String classRoom, String password) {
    this.put("_id", UUID.randomUUID().toString());
    this.put("name", name);
    this.put("age", age);
    this.put("score", score);
    this.put("classRoom", classRoom);
    this.put("password", password);
  }
  
  
  public String getId() {
    return this.getString("_id");
  }
  
  public String getName() {
    return this.getString("name");
    
  }
  
  public String getAge() {
    return this.getString("age");
  }
  
  public String getScore() {
    return this.getString("score");
  }
  
  public String getClassRoom() {
    return this.getString("classRoom");
  }
  
  public String getPassword() {
    return this.getString("password");
  }
  
  public Student from(DBObject object) {
    
    this.put("_id", object.get("_id"));
    this.put("name", object.get("name"));
    this.put("age", object.get("age"));
    this.put("score", object.get("score"));
    this.put("classRoom", object.get("classRoom"));
    this.put("password", object.get("password"));
    return this;
  }
}
