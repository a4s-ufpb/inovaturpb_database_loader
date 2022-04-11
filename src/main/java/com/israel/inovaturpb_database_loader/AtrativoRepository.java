package com.israel.inovaturpb_database_loader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Essa Interface faz o framework enxergar nossa classe, que tem como única função acessar o banco de dados.
 * @author israel
 */
@Repository
public interface AtrativoRepository extends JpaRepository<Atrativo, Integer>{
}
