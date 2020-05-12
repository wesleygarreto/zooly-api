package br.com.truvainfo.zoolyapi.repository;

import br.com.truvainfo.zoolyapi.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
	
}