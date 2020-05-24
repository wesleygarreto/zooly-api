package br.com.truvainfo.zoolyapi.repository;

import br.com.truvainfo.zoolyapi.domain.Biometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiometryRepository extends JpaRepository<Biometry, Integer> {
	
	List<Biometry> findBiometricsByAnimalId(final Integer animalCode);
}