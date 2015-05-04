/**
 * author: trinhtv3
 */

package services;

import com.google.inject.Inject;
import com.mongodb.DBObject;

import services.base.MongoAbstractCRUD;
import services.entities.Student;
import services.entities.factories.StudentFactory;
import utils.DataFactory;


public class StudentService extends MongoAbstractCRUD<Student> {

  private final String col_name = "students";
  
  @Inject
  private StudentFactory factory;
  
  @Inject
  public StudentService(DataFactory mongo) {
    this.col = mongo.getDatabase("angularTest").getCollection(col_name);
    
  }
  @Override
  public Student transform(DBObject source) {
    Student student = factory.create((String)source.get("name"), (String)source.get("age"), (String)source.get("score"),(String) source.get("classRoom"), (String)source.get("pass"));
    
    return student;
  }


}




