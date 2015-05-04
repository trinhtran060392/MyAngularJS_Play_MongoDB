package services.entities;

import com.google.inject.assistedinject.Assisted;
import com.mongodb.BasicDBObject;

public class School extends BasicDBObject {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public School(@Assisted("_id") String name,@Assisted("address") String address) {
    
    this.put("_id", name);
    this.put("address", address);
  }
  
  public String getName() {
    return this.getString("_id");
  }
  
  public String getAddress() {
    return this.getString("address");
  }
}
