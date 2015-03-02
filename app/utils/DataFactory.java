package utils;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DataFactory {
  private static final MongoClient client;
  
  static {
    try {
      client = new MongoClient();
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }
  
  public DB getDatabase(String dbName) {
    return client.getDB(dbName);
  }
  
  public void dropDatabase(String dbName) {
    client.dropDatabase(dbName);
  }
}
