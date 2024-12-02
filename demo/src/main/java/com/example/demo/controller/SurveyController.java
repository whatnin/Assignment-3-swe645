package com.example.demo.controller;

import com.example.demo.entity.Survey;
import com.example.demo.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SurveyController {
    @Autowired
    private SurveyRepository surveyRepository;

    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id) {
        return surveyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyRepository.save(survey);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @RequestBody Survey surveyDetails) {
        return surveyRepository.findById(id).map(survey -> {
            survey.setName(surveyDetails.getName());
            survey.setEmail(surveyDetails.getEmail());
            survey.setVisitDate(surveyDetails.getVisitDate());
            survey.setFeedback(surveyDetails.getFeedback());
            survey.setRecommendation(surveyDetails.isRecommendation());
            return ResponseEntity.ok(surveyRepository.save(survey));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSurvey(@PathVariable Long id) {
        return surveyRepository.findById(id).map(survey -> {
            surveyRepository.delete(survey);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
