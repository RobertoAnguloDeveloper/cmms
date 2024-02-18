package com.cmms.api.services;

import com.cmms.api.models.Question;
import com.cmms.api.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Question> getQuestionById(Integer id) {
        return questionRepository.findById(id);
    }

    @SuppressWarnings("null")
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Integer id, Question question) {
        if (question != null && id != null) {
            Optional<Question> existingQuestion = questionRepository.findById(id);

            if (existingQuestion.isPresent()) {
                Question updatedQuestion = existingQuestion.get();

                // Actualiza los campos no nulos
                if (question.getQuestion() != null) {
                    updatedQuestion.setQuestion(question.getQuestion());
                }

                // Obtiene la fecha y hora actual en UTC+3
                LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC-6"));

                // Actualiza la fecha de modificación
                updatedQuestion.setModifyDate(now.toString());

                return questionRepository.save(updatedQuestion);
            } else {
                System.out.println("Question not found");
                return null; // Manejar el caso de pregunta no encontrada
            }
        } else {
            System.out.println("Question or ID is NULL");
            return null; // Manejar el caso de pregunta nula o sin ID
        }
    }

    @SuppressWarnings("null")
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }
}
