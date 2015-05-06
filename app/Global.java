
import play.Application;
import play.GlobalSettings;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Global extends GlobalSettings{

  private Injector injector;
  
  @Override
  public void onStart(Application application) {
    injector = Guice.createInjector(new AbstractModule() {
      
      @Override
      protected void configure() {
        
      }
    });
  }
  
  @Override
  public <T> T getControllerInstance(Class<T> aClass) throws Exception {
    
    return injector.getInstance(aClass);
  }
  
}
