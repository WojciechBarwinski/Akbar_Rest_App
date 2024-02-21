package com.wojciech.barwinski.akbarrestapp.csvCustomReder;

import com.wojciech.barwinski.akbarrestapp.exception.CsvReaderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static com.wojciech.barwinski.akbarrestapp.csvCustomReder.ColumnsNameValidator.validateColumnsName;

@Slf4j
@Component
public class CsvCustomReader {

    public List<SchoolCsvRepresentationDTO> getSchoolCsvRepresentationsFromFile(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            log.debug("Start read CSV file to check columns name");

            reader.mark(1);
            validateColumnsName(reader.readLine());
            reader.reset();

            log.debug("All necessary columns exist");
            return CsvFileMapper.mapCsvToSchoolCsvRepresentations(reader);

        } catch (Exception e) {
            throw csvReaderExceptionWhenReadingFile(file);
        }
    }

    private CsvReaderException csvReaderExceptionWhenReadingFile(MultipartFile file) {
        CsvReaderException wrongFileException = new CsvReaderException("Bład z wczytaniem pliku " + file.getOriginalFilename());
        log.warn(wrongFileException.getMessage(), wrongFileException);
        return wrongFileException;
    }

}


