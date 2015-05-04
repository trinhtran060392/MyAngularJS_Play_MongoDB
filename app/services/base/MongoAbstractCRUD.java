package services.base;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public abstract class MongoAbstractCRUD<T extends DBObject> {

  protected DBCollection col;
  
  public long count() {
    return this.col.count();
  }
  
  public void create(T... obj) {
    
    this.col.insert(obj);
  }
  
  public void update(T obj) {
    
    this.col.save(obj);
    
  }
  
  public void delete(T obj) {
    this.col.remove(obj);
  }
  
  public void delete(String id) {
    this.col.remove(new BasicDBObject("_id", id));
  }
  
  public T get(String id) {
    DBObject obj = this.col.findOne(new BasicDBObject("_id", id));
    
    return obj == null ? null : transform(obj);
  }
  
  public abstract T transform(DBObject source);
  
}
