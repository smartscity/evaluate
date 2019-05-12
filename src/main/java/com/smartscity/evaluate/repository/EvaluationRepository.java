package com.smartscity.evaluate.repository;

import com.smartscity.evaluate.domain.Evaluation;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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



    @Query(value = "select eval.user_id, u.last_name,  u.first_name, eval.title, eval.org_name, CASE   WHEN eval.jhi_level = 'FIRST' THEN '一等奖' WHEN eval.jhi_level = 'SECOND' THEN '二等奖' WHEN eval.jhi_level = 'THIRD' THEN '三等奖' ELSE '未知'  END level , eval.actor, eval.speaker, eval.task_source_score, eval.discovery_and_innovation_score, eval.advanced_level_score, eval.application_and_promotion_score, eval.paper_score, eval.reply_score, eval.total_score from evaluate.evaluation eval left join jhi_user u on u.id = eval.user_id ORDER BY eval.user_id, eval.speaker_id" , nativeQuery = true)
    List<Map<String, String>> findALLMAP();



//    @Query(value = "select u.last_name lastName,  u.first_name firstName, eval.title, eval.org_name orgName, CASE   WHEN eval.jhi_level = 'FIRST' THEN '一等奖' WHEN eval.jhi_level = 'SECOND' THEN '二等奖' WHEN eval.jhi_level = 'THIRD' THEN '三等奖' ELSE '未知'  END level , eval.actor, eval.speaker, eval.task_source_score taskSourceScore, eval.discovery_and_innovation_score discoveryAndInnovationScore, eval.advanced_level_score advancedLevelScore, eval.application_and_promotion_score applicationAndPromotionScore, eval.paper_score paperScore, eval.reply_score replyScore, eval.total_score totalScore from evaluate.evaluation eval left join jhi_user u on u.id = eval.user_id ORDER BY eval.user_id, eval.speaker_id" , nativeQuery = true)
    List<Evaluation> findAllByOrderByUserAscSpeakerIdAsc();





}
