package services;


import java.util.ArrayList;
import java.util.List;

import models.Student;
import models.StudentDAO;
import play.libs.Json;
import play.mvc.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.BasicDBObject;

@Singleton
public class StudentServiceImpl extends Controller implements StudentService {

  
  private StudentDAO studentDAO;
  
  @Inject
  public StudentServiceImpl(StudentDAO studentDAO) {
    this.studentDAO = studentDAO;
  }
  public ArrayNode allStudents() {
    
    System.out.println("using resource");
    int pageNo = Integer.parseInt("1");
    List<Student> list = studentDAO.getStudents();
    
    List<Student> listByPageNumber = new ArrayList<Student>();
    
    for(int i = (pageNo-1) * 5; i < list.size() && i < (pageNo * 5); i++) {
      
      listByPageNumber.add(list.get(i));
    }
    ObjectNode json;
    ArrayNode array = Json.newObject().arrayNode();
    for (Student s: listByPageNumber) {
  
      json = Json.newObject();
      json.put("_id", s.getId());
      json.put("name", s.getName());
      json.put("age", s.getAge());
      json.put("classRoom", s.getClassRoom());
      json.put("score", s.getScore());
      json.put("total", list.size());
      array.add(json);
      
    }
    
    return array;
  }  
  public void createStudent() {
    
    JsonNode json = request().body().asJson();
    String account = json.findPath("name").asText();
    String password = json.findPath("password").asText();
    String age = json.findPath("age").asText();
    String score = json.findPath("score").asText();
    String classRoom = json.findPath("classRoom").asText();
    Student student = new Student(account,age,score,classRoom,password);
    
    studentDAO.addStudent(student);
    
  }
  public ObjectNode getStudent(String id) {
    
    BasicDBObject query = new BasicDBObject();
    query.append("_id", id);
    
    Student student = studentDAO.findById(query);
    
    ObjectNode json = Json.newObject();
    json.put("_id", student.getId());
    json.put("name", student.getName());
    json.put("age", student.getAge());
    json.put("score", student.getScore());
    json.put("classRoom", student.getClassRoom());
    json.put("password", student.getPassword());
    return json;
  }
  
  public void doUpdateStudent(String id) {
    
    JsonNode json = request().body().asJson();
    String account = json.findPath("name").asText();
    String score = json.findPath("score").asText();
    String age = json.findPath("age").asText();
    String classRoom = json.findPath("classRoom").asText();
    String password = json.findPath("password").asText();
    
    Student student = new Student();
    student.put("_id", id);
    student.put("name", account);
    student.put("score", score);
    student.put("age", age);
    student.put("classRoom", classRoom);
    student.put("password", password);
    studentDAO.updateStudent(student);
    
  }
  
  public ObjectNode updateStudent(String id) {
    
    return getStudent(id);
  }
  
  public void deleteStudent(String id) {
    
    Student student = new Student();
    student.put("_id", id);
    studentDAO.deleteStudent(student);
    
  }
  
  public String checkLogin() {
    
    JsonNode json = request().body().asJson();
    String account = json.findPath("name").asText();
    String pass = json.findPath("password").asText();
    
    BasicDBObject query = new BasicDBObject();
    query.put("name", account);
    query.put("password", pass);
    
    List<Student> list = studentDAO.find(query);
    
    if(list.size() == 0) {
    
      return ("false");
    } else {
      return ("true");
    }
  }
  
  public ArrayNode getBoyStudent() {
    
    System.out.println("Boy");
    List<Student> listALl = studentDAO.getStudents();
    List<Student> listBoy = new ArrayList<Student>();
    int pageNo = Integer.parseInt("1");

   
    for (Student s : listALl) {
      if (Integer.parseInt(s.getAge()) > 10) {
        listBoy.add(s);
      }
    }
    
    List<Student> listBoyByPage = new ArrayList<Student>();
    for(int i = (pageNo-1) * 5; i < listBoy.size() && i < (pageNo * 5); i++) {
      
      listBoyByPage.add(listBoy.get(i));
    }
    ArrayNode array = Json.newObject().arrayNode();
    ObjectNode json ;
    
    for (Student s : listBoyByPage) {
      json = Json.newObject();
      json.put("_id", s.getId());
      json.put("name", s.getName());
      json.put("age", s.getAge());
      json.put("classRoom", s.getClassRoom());
      json.put("score", s.getScore());
      json.put("total", listBoy.size());
      array.add(json);
    }
    
    return array;
    
  }
  
  public ArrayNode getGirlStudent() {
      
    int pageNo = Integer.parseInt("1");
    
    List<Student> listALl = studentDAO.getStudents();
    List<Student> listGirl = new ArrayList<Student>();
    List<Student> listGirlByPage = new ArrayList<Student>();
    
   
    for (Student s : listALl) {
      if (Integer.parseInt(s.getAge()) < 10) {
        listGirl.add(s);
      }
    }
    
    for(int i = (pageNo-1) * 5; i < listGirl.size() && i < (pageNo * 5); i++) {
      
      listGirlByPage.add(listGirl.get(i));
    }


    ArrayNode array = Json.newObject().arrayNode();
    ObjectNode json;
    for (Student s : listGirlByPage) {
      json = Json.newObject();
      json.put("_id", s.getId());
      json.put("name", s.getName());
      json.put("age", s.getAge());
      json.put("classRoom", s.getClassRoom());
      json.put("score", s.getScore());
      json.put("total", listGirl.size());
      array.add(json);
    }

    return array;
    
  }

  public ArrayNode getStudentsByPage(String pageNumber) {

    List<Student> listAll = studentDAO.getStudents();

    int pageNumberInt = Integer.parseInt(pageNumber);

    List<Student> listStudentByPage = new ArrayList<Student>();

    for (int i = (pageNumberInt - 1) * 5; i < listAll.size()
        && i < (pageNumberInt * 5); i++) {
      listStudentByPage.add(listAll.get(i));
    }

    ObjectNode json;
    ArrayNode array = Json.newObject().arrayNode();

    for (Student s : listStudentByPage) {
      json = Json.newObject();
      json.put("_id", s.getId());
      json.put("name", s.getName());
      json.put("age", s.getAge());
      json.put("classRoom", s.getClassRoom());
      json.put("score", s.getScore());
      json.put("total", listAll.size());
      array.add(json);

    }

    return array;

  }

}
