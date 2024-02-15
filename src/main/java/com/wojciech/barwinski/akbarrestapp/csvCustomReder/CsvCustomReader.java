package com.wojciech.barwinski.akbarrestapp.csvCustomReder;


import com.wojciech.barwinski.akbarrestapp.exception.CsvReaderException;
import com.wojciech.barwinski.akbarrestapp.mappers.MapperFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Component
public class CsvCustomReader {

    private final ColumnsNameValidator validator;
    private final MapperFacade mapperFacade;

    public CsvCustomReader() {
        this.validator = new ColumnsNameValidator();
        this.mapperFacade = MapperFacade.getInstance();
    }

    //TODO zminic przyjmowany multipartFile!
    public List<SchoolCsvRepresentationDTO> getSchoolCsvRepresentationsFromFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            CsvReaderException lackOfFileException = new CsvReaderException("File is null/non exist or is empty");
            log.warn(lackOfFileException.getMessage(), lackOfFileException);
            throw lackOfFileException;
        }

        log.debug("Start read CSV file to check columns name");
        try (BufferedReader firstLine = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<String> missingColumnsNames = validator.checkForMissingColumnNames(firstLine.readLine());

            if (missingColumnsNames.isEmpty()) {
                log.debug("All important columns exist");
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
                    log.trace("     Read file for map...");
                    return mapperFacade.mapCsvToSchoolCsvRepresentations(reader);
                }
            } else {
                Exception wrongColumnsNameException = new CsvReaderException("Brakuje następujących nazw kolumn " + missingColumnsNames);
                log.warn(wrongColumnsNameException.getMessage(), wrongColumnsNameException);
                throw wrongColumnsNameException;
            }

        } catch (Exception e) {
            CsvReaderException wrongFileException = new CsvReaderException("Bład z wczytaniem pliku " + file.getName());
            log.warn(wrongFileException.getMessage(), wrongFileException);
            throw wrongFileException;
        }
    }

}


