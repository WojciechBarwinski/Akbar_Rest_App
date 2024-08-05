package com.wojciech.barwinski.akbarrestapp.dtos;

import com.wojciech.barwinski.akbarrestapp.validator.toUpdate.CorrectString;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneToUpdateDTO {

    @NotNull
    private Long id;

    private boolean isToRemove;

    @CorrectString
    private String number;

    private boolean isMain;

    @CorrectString
    private String owner;

    @Size(max = 500)
    private String phoneNote;

}
