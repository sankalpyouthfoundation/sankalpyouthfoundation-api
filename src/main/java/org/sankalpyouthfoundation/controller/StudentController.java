package org.sankalpyouthfoundation.controller;

import org.sankalpyouthfoundation.model.Competition;
import org.sankalpyouthfoundation.model.StudentResponse;
import org.sankalpyouthfoundation.model.StudentRegisterRequest;
import org.sankalpyouthfoundation.model.StudentSearch;
import org.sankalpyouthfoundation.service.StudentService;
import org.sankalpyouthfoundation.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/api/students")
    public ResponseEntity<Object> saveStudent(@RequestBody StudentRegisterRequest student){
        List<StudentResponse> data = this.studentService.findByContactAndFirstname(student.getContact(), student.getFirstname());
        LocalDateTime localDateTime = LocalDateTime.now();
        if(null != data && !data.isEmpty()){
            for(StudentResponse obj: data){
                boolean isAlreadyParticipated  = checkCompetitionParticipation(student, obj);
                if(isAlreadyParticipated && obj.getFirstname().equalsIgnoreCase(student.getFirstname()) && obj.getLastname().equalsIgnoreCase(student.getLastname())){
                    Message message = new Message("Duplicate Competition","Student already registered for this competition!");
                    return ResponseEntity.status(HttpStatus.OK).body(message);
                }else {
                    StudentResponse studentResponse = getStudentResponse(isAlreadyParticipated, obj, student, localDateTime);
                    StudentResponse response = this.studentService.save(studentResponse);
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            }
        }
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentRecordId(student.getFirstname().trim() + student.getFatherName().replace(" ","") + student.getDateofBirth().replace("-",""));
        studentResponse.setTimestamp(localDateTime);
        studentResponse.setFirstname(student.getFirstname());
        studentResponse.setLastname(student.getLastname());
        studentResponse.setDateofBirth(student.getDateofBirth());
        studentResponse.setFatherName(student.getFatherName());
        studentResponse.setContact(student.getContact());
        studentResponse.setSchoolName(student.getSchoolName());
        studentResponse.setStudentClass(student.getStudentClass());
        studentResponse.setAddress(student.getAddress());
        List<Competition> competitionList = new ArrayList<>();
        competitionList.add(student.getCompetition());
        studentResponse.setCompetitions(competitionList);
        StudentResponse response = this.studentService.save(studentResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/students")
    public ResponseEntity<Object> findStudentByContactandFirstName(@RequestBody StudentSearch studentSearch){
        List<StudentResponse> result = this.studentService.findByContactAndFirstname(studentSearch.getContact(), studentSearch.getFirstname());
        if(null != result && !result.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        Message message = new Message("error", "No record found!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    private boolean checkCompetitionParticipation(StudentRegisterRequest student, StudentResponse obj) {
        List<Competition> competitionList = obj.getCompetitions();
        for(Competition competition : competitionList){
            if(competition.getName().equalsIgnoreCase(student.getCompetition().getName())){
                return true;
            }
        }
        return false;
    }

    private StudentResponse getStudentResponse(boolean isAlreadyParticipated, StudentResponse response, StudentRegisterRequest student, LocalDateTime localDateTime) {
        if(!isAlreadyParticipated){
            List<Competition> competitionList = response.getCompetitions();
            competitionList.add(student.getCompetition());
            return response;
        }
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentRecordId(student.getFirstname().trim() + student.getFatherName().replace(" ","") + student.getDateofBirth().replace("-",""));
        studentResponse.setTimestamp(localDateTime);
        studentResponse.setFirstname(student.getFirstname());
        studentResponse.setLastname(student.getLastname());
        studentResponse.setDateofBirth(student.getDateofBirth());
        studentResponse.setFatherName(student.getFatherName());
        studentResponse.setContact(student.getContact());
        studentResponse.setSchoolName(student.getSchoolName());
        studentResponse.setStudentClass(student.getStudentClass());
        studentResponse.setAddress(student.getAddress());
        List<Competition> competitionList = new ArrayList<>();
        competitionList.add(student.getCompetition());
        studentResponse.setCompetitions(competitionList);
        return studentResponse;
    }
}
