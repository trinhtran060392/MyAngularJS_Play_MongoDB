package services.entities.factories;

import services.entities.School;

import com.google.inject.assistedinject.Assisted;

public interface SchoolFactory {

  public School create(@Assisted("_id") String name, @Assisted("address") String address);
}
