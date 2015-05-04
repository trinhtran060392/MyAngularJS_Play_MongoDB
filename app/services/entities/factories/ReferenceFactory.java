package services.entities.factories;

import com.google.inject.assistedinject.Assisted;

import services.entities.references.Reference;

public interface ReferenceFactory<R extends Reference<?>> {
  
  R create(@Assisted("id") String id);
}
