package models;

import com.mongodb.BasicDBObject;

public class School extends BasicDBObject {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public School(String name) {
    
    this.put("_id", name);
  }
  
  public String getName() {
    return this.getString("_id");
  }
  
  public School transform(BasicDBObject object) {
    this.put("_id", object.get("_id"));
    
    return this;
  }
}
