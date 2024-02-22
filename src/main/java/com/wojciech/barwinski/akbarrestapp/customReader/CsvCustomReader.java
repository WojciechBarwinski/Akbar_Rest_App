package com.wojciech.barwinski.akbarrestapp.customReader;

import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.exception.InvalidCsvDataException;
import com.wojciech.barwinski.akbarrestapp.exception.ReaderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static com.wojciech.barwinski.akbarrestapp.customReader.ColumnsNameValidator.validateColumnsName;

@Slf4j
@Component
public class CsvCustomReader {

    public List<SchoolRepresentation> getSchoolCsvRepresentationsFromFile(MultipartFile file) {
        log.debug("Start read CSV file to check columns name");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            reader.mark(1);
            validateColumnsName(reader.readLine());
            reader.reset();
            log.debug("All necessary columns exist");

            return CsvFileMapper.mapCsvToSchoolRepresentations(reader);

        } catch (InvalidCsvDataException e) {
            throw e;
        } catch (Exception e) {
            throw csvReaderExceptionWhenReadingFile(file);
        }
    }

    private ReaderException csvReaderExceptionWhenReadingFile(MultipartFile file) {
        ReaderException wrongFileException = new ReaderException("Bład z wczytaniem pliku " + file.getOriginalFilename());
        log.warn(wrongFileException.getMessage(), wrongFileException);
        return wrongFileException;
    }

}


