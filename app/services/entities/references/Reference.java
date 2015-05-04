package services.entities.references;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public abstract class Reference<T extends DBObject> {

  protected final String id;
  
  public Reference(String id) {
    this.id = id;
  }
  
  public abstract T get();
  
  public String getId() {
    return this.id;
  }
  
  public DBObject toJSon() {
    return new BasicDBObject("_id", id);
  }
  
}
