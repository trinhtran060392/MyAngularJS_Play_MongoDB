package services;

import com.google.inject.Inject;
import com.mongodb.DBObject;

import services.base.MongoAbstractCRUD;
import services.entities.School;
import services.entities.factories.SchoolFactory;
import utils.DataFactory;

public class SchoolService extends MongoAbstractCRUD<School> {

  private final String col_name = "schools";
  
  @Inject
  private SchoolFactory factory;
  
  public SchoolService(DataFactory mongo) {
    
    this.col = mongo.getDatabase("angularTest").getCollection(col_name);
  
  }
  @Override
  public School transform(DBObject source) {
    School school = factory.create((String) source.get("name"), (String) source.get("address"));
    
    return school;
  }

  
}
