package br.com.truvainfo.zoolyapi.repository;

import br.com.truvainfo.zoolyapi.domain.Biometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometryRepository extends JpaRepository<Biometry, Integer> {

}