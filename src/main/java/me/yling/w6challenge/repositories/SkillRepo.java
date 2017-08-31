package me.yling.w6challenge.repositories;

import me.yling.w6challenge.models.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepo extends CrudRepository<Skill,Long> {
}
