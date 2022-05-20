package br.com.wepdev.microservicoautenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.wepdev.microservicoautenticacao.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	
	// Busca uma permissão por descrição
	@Query("SELECT u FROM User u WHERE u.userName =:userName")
	User findByUserName(@Param("userName") String userName);

}
