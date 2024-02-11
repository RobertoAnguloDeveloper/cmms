package com.cmms.api.controllers;

import com.cmms.api.models.Answer;
import com.cmms.api.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/answer")
@CrossOrigin(origins = "*")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/save")
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {
        Answer createdAnswer = answerService.createAnswer(answer);
        return ResponseEntity.ok(createdAnswer);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Answer>> getAllAnswers() {
        List<Answer> answers = answerService.getAllAnswers();
        if (answers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(answers);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable("id") Integer id) {
        Optional<Answer> answerOptional = answerService.getAnswerById(id);

        return answerOptional.map(answer -> ResponseEntity.ok(answer))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<Answer> updateAnswer(@RequestBody Answer answer) {
        Answer updatedAnswer = answerService.updateAnswer(answer);
        if (updatedAnswer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedAnswer);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable("id") Integer id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
