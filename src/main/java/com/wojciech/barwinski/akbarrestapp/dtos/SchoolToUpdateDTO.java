package com.wojciech.barwinski.akbarrestapp.dtos;

import com.wojciech.barwinski.akbarrestapp.Voivodeship;
import com.wojciech.barwinski.akbarrestapp.validator.toUpdate.CorrectString;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolToUpdateDTO {

    private Long rspo;

    @CorrectString()
    private String name;

    @CorrectString()
    private String type;

    @Schema(example = "example@example.com")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")
    private String email;

    @Schema(example = "http://example.com")
    @Pattern(regexp = "^(https?://|ftp://|www)[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}(:\\d+)?(/\\S*)?$")
    private String website;

    @CorrectString()
    private String status;

    @NotNull
    private Voivodeship voivodeship;

    @CorrectString()
    private String county;

    @CorrectString()
    private String borough;

    @CorrectString()
    private String city;

    @CorrectString()
    private String street;

    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Nieprawidłowy kod pocztowy")
    private String zipCode;

    @CorrectString()
    @Size(max = 500)
    private String addressNote;

    @Valid
    private List<PhoneToUpdateDTO> phones;

    @Valid
    private AdditionalSchoolInformationDTO additionalSchoolInformationDTO;
}
