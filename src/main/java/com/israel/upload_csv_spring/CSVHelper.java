package com.israel.upload_csv_spring;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Id", "Nome_Atrativo", "E-mail", "Data/Hora" };

  public static boolean hasCSVFormat(MultipartFile file) {
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  public static List<Atrativo> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Atrativo> atrativoList = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
    	  Atrativo atrativo = new Atrativo(
              Long.parseLong(csvRecord.get("Id")),
              csvRecord.get("Nome_Atrativo"),
              csvRecord.get("E-mail"),
              csvRecord.get("Data/Hora")
            );

    	  atrativoList.add(atrativo);
      }

      return atrativoList;
    } catch (IOException e) {
      throw new RuntimeException("falha ao analisar o arquivo CSV: " + e.getMessage());
    }
  }

  public static ByteArrayInputStream tutorialsToCSV(List<Atrativo> atrativoList) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Atrativo atrativo : atrativoList) {
        List<String> data = Arrays.asList(
              String.valueOf(atrativo.getId()),
              atrativo.getNome(),
              atrativo.getEmail(),
              atrativo.getDataHora()
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("falha ao importar dados para o arquivo CSV: " + e.getMessage());
    }
  }
}
