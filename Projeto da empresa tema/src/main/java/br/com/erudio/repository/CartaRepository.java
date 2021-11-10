package br.com.erudio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.erudio.data.model.Carta;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long>{
	
	@Query("SELECT c FROM Carta c WHERE c.nome LIKE LOWER(CONCAT ('%', :nome, '%'))")
	Page<Carta> findCartaByName(@Param("nome") String nome, Pageable pageable);

}
