package br.com.truvainfo.zoolyapi.repository;

import br.com.truvainfo.zoolyapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(final String email);
	
	Optional<User> findByEmailAndPassword(final String email, final String password);
	
}
