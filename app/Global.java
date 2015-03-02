import models.StudentDAO;
import models.StudentDAOImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import play.Application;
import play.GlobalSettings;
import services.StudentService;
import services.StudentServiceImpl;
import utils.DataFactory;


public class Global extends GlobalSettings{

  private Injector injector;
  
  @Override
  public void onStart(Application application) {
    injector = Guice.createInjector(new AbstractModule() {
      
      @Override
      protected void configure() {
        
        bind(StudentService.class).to(StudentServiceImpl.class);
        bind(StudentDAO.class).to(StudentDAOImpl.class);
        bind(DataFactory.class).toInstance(new DataFactory());
      }
    });
  }
  
  @Override
  public <T> T getControllerInstance(Class<T> aClass) throws Exception {
    
    return injector.getInstance(aClass);
  }
  
}
