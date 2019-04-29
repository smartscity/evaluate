package com.smartscity.evaluate.service;

import com.smartscity.evaluate.domain.Evaluation;
import com.smartscity.evaluate.repository.EvaluationRepository;
import com.smartscity.evaluate.repository.UserRepository;
import com.smartscity.evaluate.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Evaluation}.
 */
@Service
@Transactional
public class EvaluationService {

    private final Logger log = LoggerFactory.getLogger(EvaluationService.class);

    @Autowired
    UserRepository userRepository;

    private final EvaluationRepository evaluationRepository;

    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    /**
     * Save a evaluation.
     *
     * @param evaluation the entity to save.
     * @return the persisted entity.
     */
    public Evaluation save(Evaluation evaluation) {
        log.debug("Request to save Evaluation : {}", evaluation);
        evaluation.setUser(userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get());
        return evaluationRepository.save(evaluation);
    }

    /**
     * Get all the evaluations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Evaluation> findAll() {
        log.debug("Request to get all Evaluations");
        if (SecurityUtils.getCurrentUserLogin().get().equals("admin")) {
            return evaluationRepository.findAll();
        }else {
            return evaluationRepository.findByUserIsCurrentUser();
        }
    }


    /**
     * Get one evaluation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Evaluation> findOne(Long id) {
        log.debug("Request to get Evaluation : {}", id);
        return evaluationRepository.findById(id);
    }

    /**
     * Delete the evaluation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Evaluation : {}", id);
        evaluationRepository.deleteById(id);
    }
}
