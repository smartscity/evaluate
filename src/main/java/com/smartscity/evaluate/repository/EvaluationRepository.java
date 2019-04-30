package com.smartscity.evaluate.repository;

import com.smartscity.evaluate.domain.Evaluation;
import com.smartscity.evaluate.domain.enumeration.Level;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Evaluation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query("select evaluation from Evaluation evaluation where evaluation.user.login = ?#{principal.username}")
    List<Evaluation> findByUserIsCurrentUser();


    List<Evaluation> findByLevel(Level level);



}
