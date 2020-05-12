package br.com.truvainfo.zoolyapi.repository;

import br.com.truvainfo.zoolyapi.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}