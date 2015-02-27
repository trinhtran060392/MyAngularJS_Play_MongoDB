package modules;

import models.StudentDAO;
import models.StudentDAOImpl;
import services.StudentService;
import services.StudentServiceImpl;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule{

  @Override
  protected void configure() {
    bind(StudentService.class).to(StudentServiceImpl.class);
    bind(StudentDAO.class).to(StudentDAOImpl.class);
  }

}
