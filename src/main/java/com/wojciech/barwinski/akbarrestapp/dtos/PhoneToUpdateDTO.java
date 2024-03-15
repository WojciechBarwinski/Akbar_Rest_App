package com.wojciech.barwinski.akbarrestapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneToUpdateDTO {

    private Long id;
    private boolean isToRemove;
    private String number;
    private boolean isMain;
    private String owner;
    private String phoneNote;

}
