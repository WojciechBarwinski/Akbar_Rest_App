package com.wojciech.barwinski.akbarrestapp.customReader;

import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.exception.InvalidCsvDataException;
import com.wojciech.barwinski.akbarrestapp.exception.ReaderException;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvCustomReaderTest {


    CsvCustomReader csvReader = new CsvCustomReader();

    @Test
    void shouldReadCorrectlyCsvFile() throws IOException {
        String csvData = getCorrectCsvData();
        List<SchoolRepresentation> expectedSchoolRepresentation = getExpectedSchoolRepresentation();
        InputStream inputStream = new ByteArrayInputStream(csvData.getBytes());
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", inputStream);

        List<SchoolRepresentation> readerResult = csvReader.getSchoolCsvRepresentationsFromFile(file);

        for (int i = 0; i < expectedSchoolRepresentation.size(); i++) {
            SchoolRepresentation readerSchool = readerResult.get(i);
            SchoolRepresentation expectedSchool = expectedSchoolRepresentation.get(i);

            assertEquals(readerSchool.getRspo(), expectedSchool.getRspo());
            assertEquals(readerSchool.getName(), expectedSchool.getName());
            assertEquals(readerSchool.getVoivodeship(), expectedSchool.getVoivodeship());
            assertEquals(readerSchool.getPhone(), expectedSchool.getPhone());
        }
    }

    @Test
    void shouldReadCsvFileWithWrongColumnsNameAndThrowException() throws IOException {
        String csvData = getCsvDataWithIncorrectColumnsNames();

        InputStream inputStream = new ByteArrayInputStream(csvData.getBytes());
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", inputStream);

        assertThrows(InvalidCsvDataException.class, () -> csvReader.getSchoolCsvRepresentationsFromFile(file));
    }

    @Test
    void shouldNotReadFileAndThrowException() throws IOException {
        InputStream inputStream = null;
        MockMultipartFile incorrectFile = new MockMultipartFile("file", "test.csv", "text/csv", inputStream);

        assertThrows(ReaderException.class, () -> csvReader.getSchoolCsvRepresentationsFromFile(incorrectFile));
    }


    private String getCorrectCsvData() {
        return "Numer RSPO;Typ;Nazwa;Ulica;Numer budynku;Numer lokalu;Kod pocztowy;Miejscowość;Telefon;E-mail;Strona www;Województwo;Powiat;Gmina;Publiczność status;\n" +
                "123;Typ1;Szkoła Podstawowa;Ulica Szkolna;1;2;12345;Miastowo;123456789;szkola@example.com;www.example.com;Województwo1;Powiat1;Gmina1;Publiczna;\n" +
                "456;Typ2;Liceum Ogólnokształcące;Inna Ulica;10;20;54321;Miasteczko;987654321;liceum@example.com;www.liceum.com;Województwo2;Powiat2;Gmina2;Prywatna;\n";

    }

    private String getCsvDataWithIncorrectColumnsNames() {
        return "Numer RSPO;;Nazwa;Ulica;Numer123 budynku;Numerrrrr lokalu;Kod pocztowy;Miejscowość;Telefon;E-mail;Strona www;Województwo;Powiat;Gmina;Publiczność status;\n" +
                "123;Typ1;Szkoła Podstawowa;Ulica Szkolna;1;2;12345;Miastowo;123456789;szkola@example.com;www.example.com;Województwo1;Powiat1;Gmina1;Publiczna;\n" +
                "456;Typ2;Liceum Ogólnokształcące;Inna Ulica;10;20;54321;Miasteczko;987654321;liceum@example.com;www.liceum.com;Województwo2;Powiat2;Gmina2;Prywatna;\n";

    }

    private List<SchoolRepresentation> getExpectedSchoolRepresentation() {
        List<SchoolRepresentation> schoolRepresentations = new ArrayList<>();

        schoolRepresentations.add(SchoolRepresentation.builder()
                .rspo("123")
                .type("Typ1")
                .name("Szkoła Podstawowa")
                .street("Ulica Szkolna")
                .buildingNumber("1")
                .localNumber("2")
                .zipCode("12345")
                .city("Miastowo")
                .phone("123456789")
                .email("szkola@example.com")
                .website("www.example.com")
                .voivodeship("Województwo1")
                .county("Powiat1")
                .borough("Gmina1")
                .status("Publiczna")
                .build());

        schoolRepresentations.add(SchoolRepresentation.builder()
                .rspo("456")
                .type("Typ2")
                .name("Liceum Ogólnokształcące")
                .street("Inna Ulica")
                .buildingNumber("10")
                .localNumber("20")
                .zipCode("54321")
                .city("Miasteczko")
                .phone("987654321")
                .email("liceum@example.com")
                .website("www.liceum.com")
                .voivodeship("Województwo2")
                .county("Powiat2")
                .borough("Gmina2")
                .status("Prywatna")
                .build());

        return schoolRepresentations;
    }
}