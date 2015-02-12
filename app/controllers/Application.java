package controllers;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import play.mvc.Controller;
import play.mvc.Result;
import services.StudentService;
import services.StudentServiceIplm;

public class Application extends Controller {

  //@Inject
  //private StudentService studentService;
  
  Injector injector = Guice.createInjector(new AbstractModule(){

    @Override
    protected void configure() {
      // TODO Auto-generated method stub
      bind(StudentService.class).to(StudentServiceIplm.class);
    }
    
  });
  
  StudentService studentService = injector.getInstance(StudentService.class);
  public Result allStudents(String pageNumber) {
    return ok(studentService.allStudents(pageNumber));
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
  
  public Result getBoyStudent(String pageNumber) {
    return ok(studentService.getBoyStudent(pageNumber));
  }
  
  public Result getGirlStudent(String pageNumber) {
    return ok(studentService.getGirlStudent(pageNumber));
  }
  
  public Result getStudentsByPage(String pageNumber) {
    return ok(studentService.getStudentsByPage(pageNumber));
  }
}
