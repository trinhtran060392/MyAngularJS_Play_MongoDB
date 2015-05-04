package services.entities.factories;

import services.entities.Student;

import com.google.inject.assistedinject.Assisted;

public interface StudentFactory {

  public Student create(@Assisted("name") String name, @Assisted("age") String age,@Assisted("score") String score,@Assisted("classRoom") String classRoom, @Assisted("password") String pass);
  
}
