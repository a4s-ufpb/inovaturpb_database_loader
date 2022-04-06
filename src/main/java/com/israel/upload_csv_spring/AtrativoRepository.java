package com.israel.upload_csv_spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Essa Interface faz o framework enxergar nossa classe, que tem como única função acessar o banco de dados.

@Repository
public interface AtrativoRepository extends JpaRepository<Atrativo, Integer>{
}
