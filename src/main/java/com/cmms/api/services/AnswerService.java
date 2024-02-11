package com.cmms.api.services;

import com.cmms.api.models.Answer;
import com.cmms.api.repositories.AnswerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Answer> getAnswerById(Integer id) {
        return answerRepository.findById(id);
    }

    @SuppressWarnings("null")
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Integer id, Answer answer) {
        if (answer != null && id != null) {
            Optional<Answer> existingAnswer = answerRepository.findById(id);

            if (existingAnswer.isPresent()) {
                Answer updatedAnswer = existingAnswer.get();

                // Actualiza los campos no nulos
                if(answer.getAnswer() != null){
                    updatedAnswer.setAnswer(answer.getAnswer());
                }

                // Actualiza la fecha de modificación
                updatedAnswer.setModifyDate(LocalDateTime.now().toString());

                return answerRepository.save(updatedAnswer);
            } else {
                System.out.println("Answer not found");
                return null; // Manejar el caso de respuesta no encontrada
            }
        } else {
            System.out.println("No Answer sent");
            return null; // Manejar el caso de respuesta nula o sin ID
        }
    }

    @SuppressWarnings("null")
    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(id);
    }
}
