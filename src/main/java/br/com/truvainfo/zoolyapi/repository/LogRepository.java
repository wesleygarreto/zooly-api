package br.com.truvainfo.zoolyapi.repository;

import br.com.truvainfo.zoolyapi.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
	
	List<Log> findAllByUserId(final Integer userId);

}
