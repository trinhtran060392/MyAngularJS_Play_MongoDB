package services.entities.references;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import services.StudentService;
import services.entities.Student;

public class StudentReference extends Reference<Student> {

  private StudentService service;
  
  @Inject
  public StudentReference(StudentService service, @Assisted("id") String id) {
    super(id);
    this.service = service;
  }
  @Override
  public Student get() {
    return this.service.get(id);
  }

}
