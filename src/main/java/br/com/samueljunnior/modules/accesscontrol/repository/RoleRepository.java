package br.com.samueljunnior.modules.accesscontrol.repository;

import br.com.samueljunnior.modules.accesscontrol.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
