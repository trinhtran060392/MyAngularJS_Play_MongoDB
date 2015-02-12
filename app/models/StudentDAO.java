package models;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.mongodb.DBObject;

@ImplementedBy(StudentDAOImpl.class)
public interface StudentDAO {

   public List<Student> getStudents();
   
   public void addStudent(Student student);
   
   public void updateStudent(Student student);
   
   public void deleteStudent(Student student);
   
   public Student findById(DBObject query);
   
   public List<Student> find(DBObject query);
}

