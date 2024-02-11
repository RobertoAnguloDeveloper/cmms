package com.cmms.api.services;

import com.cmms.api.models.Answer;
import com.cmms.api.repositories.AnswerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @SuppressWarnings("null")
    public Answer updateAnswer(Answer answer) {
        if (answer != null && answer.getId() != null) {
            Optional<Answer> existingAnswer = answerRepository.findById(answer.getId());

            if (existingAnswer.isPresent()) {
                Answer updatedAnswer = existingAnswer.get();

                // Actualiza los campos según sea necesario
                updatedAnswer.setAnswer(answer.getAnswer());
                // Actualiza otros campos aquí si es necesario

                return answerRepository.save(updatedAnswer);
            } else {
                return null; // Manejar el caso de respuesta no encontrada
            }
        } else {
            return null; // Manejar el caso de respuesta nula o sin ID
        }
    }

    @SuppressWarnings("null")
    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(id);
    }
}
