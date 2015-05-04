package models;

import com.google.inject.assistedinject.Assisted;

public interface StudentFactory {

  public Student create(@Assisted("_id") String id, @Assisted("name") String name, @Assisted("age") String age,@Assisted("score") String score,@Assisted("classRoom") String classRoom, @Assisted("password") String pass);
  
}
