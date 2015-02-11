package controllers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

  @Inject
  private StudentController studentController;
  
  public Result allStudents(String pageNumber) {
    return ok(studentController.allStudents(pageNumber));
  }
  public Result createStudent() {
    studentController.createStudent();
    return ok();
  }
  public Result getStudent(String id) {
    return ok(studentController.getStudent(id));
  }
  public Result doUpdateStudent(String id) {
    studentController.doUpdateStudent(id);
    return ok();
  }
  public Result updateStudent(String id) {
    return ok(studentController.updateStudent(id));
  }
  public Result deleteStudent(String id) {
    
    studentController.deleteStudent(id);
    return ok();
  }
  public Result checkLogin() {
    return ok(studentController.checkLogin());
  }
  public Result getBoyStudent(String pageNumber) {
    return ok(studentController.getBoyStudent(pageNumber));
  }
  
  public Result getGirlStudent(String pageNumber) {
    return ok(studentController.getGirlStudent(pageNumber));
  }
  public Result getStudentsByPage(String pageNumber) {
    return ok(studentController.getStudentsByPage(pageNumber));
  }
}
