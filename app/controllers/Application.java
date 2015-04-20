package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.StudentService;

import com.google.inject.Inject;

public class Application extends Controller {

  
  private StudentService studentService;
  
  @Inject
  public Application(StudentService studentService) {
    this.studentService = studentService;
  }
  public Result allStudents() {
    return ok(studentService.allStudents());
  }
  
  public Result createStudent() {
    studentService.createStudent();
    return ok();
  }
  
  public Result getStudent(String id) {
    return ok(studentService.getStudent(id));
  }
  
  public Result doUpdateStudent(String id) {
    studentService.doUpdateStudent(id);
    return ok();
  }
  
  public Result updateStudent(String id) {
    return ok(studentService.updateStudent(id));
  }
  
  public Result deleteStudent(String id) {
    
    studentService.deleteStudent(id);
    return ok();
  }
  
  public Result checkLogin() {
    return ok(studentService.checkLogin());
  }
  
  public Result getBoyStudent() {
    return ok(studentService.getBoyStudent());
  }
  
  public Result getGirlStudent() {
    return ok(studentService.getGirlStudent());
  }
  
  public Result getStudentsByPage(String pageNumber) {
    return ok(studentService.getStudentsByPage(pageNumber));
  }
}
