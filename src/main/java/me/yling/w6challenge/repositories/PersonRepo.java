package me.yling.w6challenge.repositories;

import me.yling.w6challenge.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person, Long> {
}
