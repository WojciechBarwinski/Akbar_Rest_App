package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToUpdateDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToViewDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO;
import com.wojciech.barwinski.akbarrestapp.validator.toUpdate.UpdateSchoolResultDTO;
import com.wojciech.barwinski.akbarrestapp.validator.toUpload.UploadSchoolResultDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceFacade {

    private final BaseSchoolService baseSchoolService;
    private final SchoolUploaderService schoolUploader;
    private final SchoolUpdaterService schoolUpdater;

    public SchoolServiceFacade(BaseSchoolService baseSchoolService, SchoolUploaderService schoolUploader, SchoolUpdaterService schoolUpdater) {
        this.baseSchoolService = baseSchoolService;
        this.schoolUploader = schoolUploader;
        this.schoolUpdater = schoolUpdater;
    }


    public List<SchoolToRosterDTO> getAllSchools() {
        return baseSchoolService.getAllSchools();
    }

    public List<SchoolToRosterDTO> getSchoolsBySearchRequest(SchoolSearchRequest request){
        return baseSchoolService.getSchoolsBySearchRequest(request);
    }

    public SchoolToViewDTO getSchoolById(Long id) {
        return baseSchoolService.getSchoolById(id);
    }

    public SchoolToUpdateDTO getSchoolToUpdateDTO(Long id){
        return baseSchoolService.getSchoolToUpdateById(id);
    }

    public UploadSchoolResultDTO uploadSchools(List<? extends SchoolRepresentation> schoolsToImport) {
        return schoolUploader.uploadSchools(schoolsToImport);
    }

    public UpdateSchoolResultDTO updateSchool(SchoolToUpdateDTO schoolToUpdateDTO){
        return schoolUpdater.updateSchool(schoolToUpdateDTO);
    }
}
