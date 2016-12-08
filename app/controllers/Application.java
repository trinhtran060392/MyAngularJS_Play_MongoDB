package controllers;

import java.util.List;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import com.trinhtv3.fsoft.services.StudentService;
import com.trinhtv3.fsoft.services.entity.Student;
import com.trinhtv3.fsoft.services.entity.factories.StudentFactory;

public class Application extends Controller {

  @Inject
  private StudentFactory studentFactory;
  
  @Inject
  private StudentService studentService;
  
  public Result allStudents() {
    
    List<Student> list = studentService.getAll();
    
    ObjectNode object;
    ArrayNode array = Json.newObject().arrayNode();
    
    for (Student s : list) {
      object = Json.newObject();
      object.put("_id", s.getId());
      object.put("name", s.getName());
      object.put("age", s.getAge());
      
      array.add(object);
    }
    
    return ok(array);
  }
  
  public Result createStudent() {
    
    JsonNode json = request().body().asJson();
    String name = json.findPath("name").asText();
    String password = json.findPath("password").asText();
    String age = json.findPath("age").asText();
    String score = json.findPath("score").asText();
    String classRoom = json.findPath("classRoom").asText();
    
    Student student = studentFactory.create(name, age, score, classRoom, password);
    
    studentService.create(student);
    
    return ok();
  }
  
  public Result getStudent(String id) {
    
    Student student = studentService.findById(id);
    
    ObjectNode json = Json.newObject();
    json.put("_id", student.getId());
    json.put("name", student.getName());
    json.put("age", student.getAge());
    json.put("score", student.getScore());
    json.put("classRoom", student.getClassRoom());
    json.put("password", student.getPassword());
    return ok(json);
  }
  
  public Result doUpdateStudent(String id) {
    JsonNode json = request().body().asJson();
    String name = json.findPath("name").asText();
    String score = json.findPath("score").asText();
    String age = json.findPath("age").asText();
    String classRoom = json.findPath("classRoom").asText();
    String password = json.findPath("password").asText();
    
    Student student = studentService.findById(id);
    
    student.put("_id", id);
    student.setName(name);
    student.put("score", score);
    student.put("age", age);
    student.put("classRoom", classRoom);
    student.put("password", password);
    studentService.update(student);
    
    return ok();
    
  }
  
  public Result deleteStudent(String id) {
    
    Student student = studentService.findById(id);
    studentService.delete(student);
    
    return ok();
  }
  
  public Result checkLogin() {
    return ok();
  }
  
  public Result getBoyStudent() {
    return ok();
  }
  
  public Result getGirlStudent() {
    return ok();
  }
  
  public Result getStudentsByPage() {
    return ok();
  }
}