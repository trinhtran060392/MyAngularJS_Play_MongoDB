package services.entities.references;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import services.SchoolService;
import services.entities.School;

public class SchoolReference extends Reference<School>{

  private SchoolService schoolService;
  
  @Inject
  public SchoolReference(@Assisted("id") String id, SchoolService service) {
    
    super(id);
    this.schoolService = service;
  }
  @Override
  public School get() {
    
    return schoolService.get(id);
  }

}
