package com.smartscity.evaluate.repository;

import com.smartscity.evaluate.domain.Evaluation;
import com.smartscity.evaluate.domain.enumeration.Level;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring Data  repository for the Evaluation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query("select evaluation from Evaluation evaluation where evaluation.user.login = ?#{principal.username}")
    List<Evaluation> findByUserIsCurrentUser();


    @Query(value = "SELECT  title, org_name, actor, FORMAT(sum(total_score) / (SELECT count(*) from jhi_user where login not in ('system', 'user', 'anonymoususer', 'admin')), 2) as score FROM evaluation WHERE jhi_level =:level  GROUP BY speaker_id, title, org_name, actor ORDER BY score desc" , nativeQuery = true)
    List<Map<String, String>> findByLevel(@Param("level") String level);

    @Query(value = "SELECT  title, org_name, actor, FORMAT( sum( total_score ) - max(total_score) - min(total_score) / ((SELECT count(*) from jhi_user where login not in ('system', 'user', 'anonymoususer', 'admin')) - 2 ), 2) as score FROM evaluation WHERE jhi_level =:level  GROUP BY speaker_id, title, org_name, actor ORDER BY score desc" , nativeQuery = true)
    List<Map<String, String>> findByLevelExceptMAXMIN(@Param("level") String level);

    @Query(value = "SELECT * FROM evaluation WHERE speaker_id =:speakerId and user_id =:userId" , nativeQuery = true)
    Evaluation findBySpeakerIdAnduAndUser(@Param("userId") Long userId, @Param("speakerId") Long speakerId);





}
