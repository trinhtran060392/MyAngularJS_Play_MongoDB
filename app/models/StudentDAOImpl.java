/**
 * author: trinhtv3
 * dateofbirth: 060392
 */

package models;

import java.util.ArrayList;
import java.util.List;

import utils.DataFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Singleton
public class StudentDAOImpl implements StudentDAO{

   
   private DataFactory dataFactory;
   
   @Inject
   public StudentDAOImpl(DataFactory dataFactory) {
     this.dataFactory = dataFactory;
   }
   public List<Student> getStudents() {
     
     List<Student> list = new ArrayList<Student>();
     DBCollection coll = dataFactory.getDatabase("AngularJSDemo").getCollection("students");
     
     DBCursor cursor = coll.find();
     Student student;
     while(cursor.hasNext()) { 
       student = new Student();
       student.from(cursor.next());
       list.add(student);
       
     }
     
     return list;
   }
   
   public void addStudent(Student student) {
     
     DBCollection coll = dataFactory.getDatabase("AngularJSDemo").getCollection("students");
     coll.insert(student);
   }
   
   public void updateStudent(Student student) {
     
     DBCollection coll = dataFactory.getDatabase("AngularJSDemo").getCollection("students");
     coll.save(student);
   }
   
   public void deleteStudent(Student student) {
     
     DBCollection coll = dataFactory.getDatabase("AngularJSDemo").getCollection("students");
     coll.remove(student);
   }
   
   public Student findById(DBObject query) {
     
     DBCollection coll = dataFactory.getDatabase("AngularJSDemo").getCollection("students");
     DBObject source = coll.findOne(query);
     return source == null ? null : new Student().from(source); 
     
   }
   
   public List<Student> find(DBObject query) {
     
     DBCollection coll = dataFactory.getDatabase("AngularJSDemo").getCollection("students");
     List<Student> list = new ArrayList<Student>();
     DBCursor cursor = coll.find(query);
     while(cursor.hasNext()) {
       Student student = new Student().from(cursor.next());
       list.add(student);
       
     }
     
     return list;
   }
}
