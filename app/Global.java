import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import play.Application;
import play.GlobalSettings;

import services.StudentService;
import services.StudentServiceIplm;

public class Global extends GlobalSettings{

  private Injector injector;
  
  @Override
  public void onStart(Application application) {
    injector = Guice.createInjector(new AbstractModule() {
      
      @Override
      protected void configure() {
        // TODO Auto-generated method stub
        bind(StudentService.class).to(StudentServiceIplm.class);
      }
    });
  }
  
  @Override
  public <T> T getControllerInstance(Class<T> aClass) throws Exception {
    
    return injector.getInstance(aClass);
  }
}
