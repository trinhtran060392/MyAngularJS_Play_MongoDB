
import play.Application;
import play.GlobalSettings;
import services.SchoolService;
import services.StudentService;
import services.entities.factories.ReferenceFactory;
import services.entities.factories.SchoolFactory;
import services.entities.factories.StudentFactory;
import services.entities.references.SchoolReference;
import services.entities.references.StudentReference;
import utils.DataFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class Global extends GlobalSettings{

  private Injector injector;
  
  @Override
  public void onStart(Application application) {
    injector = Guice.createInjector(new AbstractModule() {
      
      @Override
      protected void configure() {
        
        install(new FactoryModuleBuilder().build(SchoolFactory.class));
        install(new FactoryModuleBuilder().build(StudentFactory.class));
        
        install(new FactoryModuleBuilder().build(new TypeLiteral<ReferenceFactory<SchoolReference>>(){}));
        install(new FactoryModuleBuilder().build(new TypeLiteral<ReferenceFactory<StudentReference>>(){}));
        
        bind(StudentService.class);
        bind(SchoolService.class);
        
        bind(DataFactory.class).toInstance(new DataFactory());
      }
    });
  }
  
  @Override
  public <T> T getControllerInstance(Class<T> aClass) throws Exception {
    
    return injector.getInstance(aClass);
  }
  
}
