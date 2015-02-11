import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import controllers.StudentController;
import controllers.StudentControllerIplm;
import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings{

  private Injector injector;
  
  @Override
  public void onStart(Application application) {
    injector = Guice.createInjector(new AbstractModule() {
      
      @Override
      protected void configure() {
        // TODO Auto-generated method stub
        bind(StudentController.class).to(StudentControllerIplm.class);
      }
    });
  }
  
  @Override
  public <T> T getControllerInstance(Class<T> aClass) throws Exception {
    
    return injector.getInstance(aClass);
  }
}
