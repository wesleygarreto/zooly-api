package br.com.truvainfo.zoolyapi.repository;

import br.com.truvainfo.zoolyapi.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	
	Optional<UserRole> findByDescription(final String description);
	
}
