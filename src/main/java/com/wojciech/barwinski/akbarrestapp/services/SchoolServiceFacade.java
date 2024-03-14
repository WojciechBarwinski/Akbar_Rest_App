package com.wojciech.barwinski.akbarrestapp.services;

import com.wojciech.barwinski.akbarrestapp.customReader.schoolRepresentations.SchoolRepresentation;
import com.wojciech.barwinski.akbarrestapp.dtos.FullSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.dtos.SchoolSearchRequest;
import com.wojciech.barwinski.akbarrestapp.dtos.ShortSchoolDTO;
import com.wojciech.barwinski.akbarrestapp.validator.dtos.UploadSchoolResultDTO;
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


    public List<ShortSchoolDTO> getAllSchools() {
        return baseSchoolService.getAllSchools();
    }

    public List<ShortSchoolDTO> getSchoolsBySearchRequest(SchoolSearchRequest request){
        return baseSchoolService.getSchoolsBySearchRequest(request);
    }

    public FullSchoolDTO getSchoolById(Long id) {
        return baseSchoolService.getSchoolById(id);
    }

    public UploadSchoolResultDTO uploadSchools(List<? extends SchoolRepresentation> schoolsToImport) {
        return schoolUploader.uploadSchools(schoolsToImport);
    }

    public FullSchoolDTO updateSchool(FullSchoolDTO fullSchoolDTO){
        return schoolUpdater.updateSchool(fullSchoolDTO);
    }
}
