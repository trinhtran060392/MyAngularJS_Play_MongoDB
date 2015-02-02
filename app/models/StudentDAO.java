package models;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import utils.DataFactory;

public class StudentDAO {

   public static List<Student> getStudents() {
     
     List<Student> list = new ArrayList<Student>();
     DBCollection coll = DataFactory.getDatabase("AngularJSDemo").getCollection("students");
     
     DBCursor cursor = coll.find();
     Student student;
     while(cursor.hasNext()) { 
       student = new Student();
       student.from(cursor.next());
       list.add(student);
       
     }
     
     return list;
   }
   
   public static void addStudent(Student student) {
     
     DBCollection coll = DataFactory.getDatabase("AngularJSDemo").getCollection("students");
     coll.insert(student);
   }
   
   public static void updateStudent(Student student) {
     
     DBCollection coll = DataFactory.getDatabase("AngularJSDemo").getCollection("students");
     coll.save(student);
   }
   
   public static void deleteStudent(Student student) {
     
     DBCollection coll = DataFactory.getDatabase("AngularJSDemo").getCollection("students");
     coll.remove(student);
   }
   
   public static Student findById(DBObject query) {
     
     DBCollection coll = DataFactory.getDatabase("AngularJSDemo").getCollection("students");
     DBObject source = coll.findOne(query);
     return source == null ? null : new Student().from(source); 
     
   }
   
   public static List<Student> find(DBObject query) {
     
     DBCollection coll = DataFactory.getDatabase("AngularJSDemo").getCollection("students");
     List<Student> list = new ArrayList<Student>();
     DBCursor cursor = coll.find(query);
     while(cursor.hasNext()) {
       Student student = new Student().from(cursor.next());
       list.add(student);
       
     }
     
     return list;
   }
}
