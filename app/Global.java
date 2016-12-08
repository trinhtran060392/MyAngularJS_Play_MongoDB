
import play.Application;
import play.GlobalSettings;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.trinhtv3.fsoft.services.module.OrganizationModule;

public class Global extends GlobalSettings{

  private Injector injector;
  
  @Override
  public void onStart(Application application) {
    injector = Guice.createInjector(new OrganizationModule());
    
  }
  
  @Override
  public <T> T getControllerInstance(Class<T> aClass) throws Exception {
    
    return injector.getInstance(aClass);
  }
  
}
