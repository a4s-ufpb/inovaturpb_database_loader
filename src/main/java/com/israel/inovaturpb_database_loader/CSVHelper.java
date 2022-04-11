package com.israel.inovaturpb_database_loader;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs =  { "Validado por...", "Carimbo de data/hora" , "Endereço de e-mail", "1. Nome do atrativo turístico", "2. Qual o tipo de atrativo turístico?"};

    /**
     * É usado para verificar se o formato do arquivo é CSV ou não.
     * @author pedro
     * @param file
     * @return
     */
    public static boolean hasCSVFormat(MultipartFile file) {
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }

        return false;
    }

    /**
     * Este método é totalmente usado para ler os dados do arquivo CSV.
     * @author pedro
     * @param is
     * @return
     */
    public static List<Atrativo> csvToAtrativos(InputStream is) {


        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Atrativo> atrativoList = new ArrayList<>();

            List<CSVRecord> csvRecords = csvParser.getRecords();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            for (CSVRecord csvRecord : csvRecords) {

                Atrativo atrativo = new Atrativo(
                        csvRecord.get("Validado por..."),
                        df.parse(csvRecord.get("Carimbo de data/hora")),
                        csvRecord.get("Endereço de e-mail"),
                        csvRecord.get("1. Nome do atrativo turístico"),
                        csvRecord.get("2. Qual o tipo de atrativo turístico?")






                );

                atrativoList.add(atrativo);
            }

            return atrativoList;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("falha ao analisar o arquivo CSV: " + e.getMessage());
        }
    }

    /**
     * Este método é usado para gravar os dados no arquivo CSV da tabela do banco de dados MySQL.
     * @author pedro
     * @param atrativoList
     * @return
     */
    public static ByteArrayInputStream atrativosToCSV(List<Atrativo> atrativoList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Atrativo atrativo : atrativoList) {
                List<? extends Serializable> data = Arrays.asList(
                        atrativo.getValidador(),
                        atrativo.getDataHoraCadastro(),
                        atrativo.getEnderecoDeEmail(),
                        atrativo.getNomeDoAtrativo(),
                        atrativo.getTipo()



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
