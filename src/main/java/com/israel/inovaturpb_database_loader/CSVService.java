package com.israel.inovaturpb_database_loader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
    @Autowired
    AtrativoRepository repository;

    /**
     * Salva os dados do arquivo CSV no banco de dados.
     * @param file
     */
    public void save(MultipartFile file) {
        try {
            List<Atrativo> atrativos = CSVHelper.csvToAtrativos(file.getInputStream());
            repository.saveAll(atrativos);
        } catch (IOException e) {
            throw new RuntimeException("falha ao armazenar dados csv: " + e.getMessage());
        }
    }

    /**
     * Vai ler os dados do banco de dados e retornará na forma de ByteArrayInputStream.
     * @return
     */
    public ByteArrayInputStream load() {
        List<Atrativo> atrativos = repository.findAll();

        ByteArrayInputStream in = CSVHelper.atrativosToCSV(atrativos);
        return in;
    }

    /**
     * Ler os dados do banco de dados e retornará a Lista de Atrativos.
     * @return
     */
    public List<Atrativo> getAllAtrativos() {
        return repository.findAll();
    }
}
