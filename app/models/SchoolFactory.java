package models;

import com.google.inject.assistedinject.Assisted;

public interface SchoolFactory {

  public School create(@Assisted("_id") String name);
}
