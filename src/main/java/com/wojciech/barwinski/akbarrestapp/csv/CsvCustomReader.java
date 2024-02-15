package com.wojciech.barwinski.akbarrestapp.csv;


import com.opencsv.bean.CsvToBeanBuilder;
import com.wojciech.barwinski.akbarrestapp.csv.Validators.ColumnsNameValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@Component
public class CsvCustomReader {

    private static final String FILE_PATH = "src/main/resources/testSchools.csv";
    private final SchoolMapper schoolMapper;
    private final ColumnsNameValidator validator;

    public CsvCustomReader(SchoolMapper schoolMapper, ColumnsNameValidator validator) {
        this.schoolMapper = schoolMapper;
        this.validator = validator;
    }

    public List<SchoolCsvRepresentation> parseCsvByMultipartFile(MultipartFile file) {
        try {BufferedReader readOneLine = new BufferedReader(new FileReader(FILE_PATH));
            List<String> missingNames = validator.checkIfColumnsNamesAreCorrect(readOneLine.readLine());

            if (missingNames.isEmpty()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                return parseCsv(reader);
            } else {
                throw new RuntimeException("Brakuje następujących nazw kolumn " + missingNames);
            }
        } catch (IOException e) {
            //TODO correct exception
            throw new RuntimeException(e);
        }
    }

    public List<SchoolCsvRepresentation> parseCsvByFilePath() {
        try {BufferedReader readOneLine = new BufferedReader(new FileReader(FILE_PATH));
            List<String> missingNames = validator.checkIfColumnsNamesAreCorrect(readOneLine.readLine());

            if (missingNames.isEmpty()) {
                BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
                return parseCsv(reader);
            } else {
                throw new RuntimeException("Brakuje następujących nazw kolumn " + missingNames);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<SchoolCsvRepresentation> parseCsv(BufferedReader reader) throws IOException {
        return new CsvToBeanBuilder<SchoolCsvRepresentation>(reader)
                .withType(SchoolCsvRepresentation.class)
                .withSeparator(';')
                .build()
                .parse();
    }

    /*private Set<School> mapCsvToSchool(List<SchoolCsvRepresentation> csv) {
        return csv.stream()
                .map(schoolMapper::mapSchoolCsvRepToSchool)
                .collect(Collectors.toSet());
    }*/

}
