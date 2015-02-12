package modules;

import models.StudentDAO;
import models.StudentDAOImpl;

import com.google.inject.AbstractModule;

public class StudentModule extends AbstractModule{

  @Override
  protected void configure() {
    // TODO Auto-generated method stub
    bind(StudentDAO.class).to(StudentDAOImpl.class);
    
  }

}
