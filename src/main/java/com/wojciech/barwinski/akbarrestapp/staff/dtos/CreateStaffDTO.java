package com.wojciech.barwinski.akbarrestapp.staff.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateStaffDTO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Schema(example = "123-456-789")
    @NotEmpty
    private String phone;

    @Schema(example = "example@example.com")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Invalid email format")
    private String email;

    @Size(max = 500)
    private String note;
}
