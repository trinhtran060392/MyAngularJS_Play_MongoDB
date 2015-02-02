package controllers;


import java.util.ArrayList;
import java.util.List;

import models.Student;
import models.StudentDAO;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.BasicDBObject;

public class StudentController extends Controller{

  public static Result allStudents() {
    
    List<Student> list = StudentDAO.getStudents();
    
    ObjectNode json ;
    ArrayNode array = Json.newObject().arrayNode();
    for (Student s: list) {
      
      json = Json.newObject();
      json.put("_id", s.getId());
      json.put("name", s.getName());
      json.put("age", s.getAge());
      json.put("classRoom", s.getClassRoom());
      json.put("score", s.getScore());
      json.put("total", list.size());
      array.add(json);
    }
    
    return ok(array);
  }  
  public static Result createStudent() {
    
    JsonNode json = request().body().asJson();
    String account = json.findPath("name").asText();
    String password = json.findPath("password").asText();
    String age = json.findPath("age").asText();
    String score = json.findPath("score").asText();
    String classRoom = json.findPath("classRoom").asText();
    Student student = new Student(account,age,score,classRoom,password);
    
    StudentDAO.addStudent(student);
    
    return ok();
  }
  public static Result getStudent(String id) {
    
    BasicDBObject query = new BasicDBObject();
    query.append("_id", id);
    
    Student student = StudentDAO.findById(query);
    
    ObjectNode json = Json.newObject();
    json.put("_id", student.getId());
    json.put("name", student.getName());
    json.put("age", student.getAge());
    json.put("score", student.getScore());
    json.put("classRoom", student.getClassRoom());
    json.put("password", student.getPassword());
    return ok(json);
  }
  
  public static Result doUpdateStudent(String id) {
    
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
    StudentDAO.updateStudent(student);
    
    return ok();
  }
  
  public static Result updateStudent(String id) {
    
    return getStudent(id);
  }
  
  public static Result deleteStudent(String id) {
    
    Student student = new Student();
    student.put("_id", id);
    StudentDAO.deleteStudent(student);
    
    return ok();
  }
  
  public static Result checkLogin() {
    
    JsonNode json = request().body().asJson();
    String account = json.findPath("name").asText();
    String pass = json.findPath("password").asText();
    
    BasicDBObject query = new BasicDBObject();
    query.put("name", account);
    query.put("password", pass);
    
    List<Student> list = StudentDAO.find(query);
    
    if(list.size() == 0) {
    
      return ok("false");
    } else {
      return ok("true");
    }
  }
  
  public static Result getBoyStudent() {
    
    List<Student> listALl = StudentDAO.getStudents();
    List<Student> listBoy = new ArrayList<Student>();
    for (Student s : listALl) {
      if (Integer.parseInt(s.getAge()) > 10) {
        listBoy.add(s);
      }
    }
    
    ArrayNode array = Json.newObject().arrayNode();
    ObjectNode json ;
    
    for (Student s : listBoy) {
      json = Json.newObject();
      json.put("_id", s.getId());
      json.put("name", s.getName());
      json.put("age", s.getAge());
      json.put("classRoom", s.getClassRoom());
      json.put("score", s.getScore());
      json.put("total", listBoy.size());
      array.add(json);
    }
    
    return ok(array);
    
  }
  
public static Result getGirlStudent() {
    
    List<Student> listALl = StudentDAO.getStudents();
    List<Student> listGirl = new ArrayList<Student>();
    for (Student s : listALl) {
      if (Integer.parseInt(s.getAge()) < 10) {
        listGirl.add(s);
      }
    }
    
    ArrayNode array = Json.newObject().arrayNode();
    ObjectNode json ;
    
    for (Student s : listGirl) {
      json = Json.newObject();
      json.put("_id", s.getId());
      json.put("name", s.getName());
      json.put("age", s.getAge());
      json.put("classRoom", s.getClassRoom());
      json.put("score", s.getScore());
      json.put("total", listGirl.size());
      array.add(json);
    }
    
    return ok(array);
    
  }
}
