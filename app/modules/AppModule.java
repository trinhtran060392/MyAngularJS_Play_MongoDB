package modules;

import models.StudentDAO;
import models.StudentDAOImpl;
import services.StudentService;
import services.StudentServiceIplm;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule{

  @Override
  protected void configure() {
    bind(StudentService.class).to(StudentServiceIplm.class);
    bind(StudentDAO.class).to(StudentDAOImpl.class);
  }

}
