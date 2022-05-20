package br.com.wepdev.microservicoautenticacao.repository;

import org.springframework.stereotype.Repository;

import br.com.wepdev.microservicoautenticacao.entity.Permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{
	
	
	// Busca uma permissão por descrição
	@Query("SELECT p FROM Permission p WHERE p.description =:description")
	Permission findByDescription(@Param("description") String description);

}
