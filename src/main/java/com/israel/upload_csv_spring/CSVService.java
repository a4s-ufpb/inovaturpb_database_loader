package com.israel.upload_csv_spring;

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

  public void save(MultipartFile file) {
    try {
      List<Atrativo> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("falha ao armazenar dados csv: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<Atrativo> tutorials = repository.findAll();

    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
    return in;
  }

  public List<Atrativo> getAllTutorials() {
    return repository.findAll();
  }
}
