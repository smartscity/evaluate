package com.smartscity.evaluate.service;

import com.smartscity.evaluate.domain.Evaluation;
import com.smartscity.evaluate.domain.Speaker;
import com.smartscity.evaluate.domain.enumeration.Level;
import com.smartscity.evaluate.repository.EvaluationRepository;
import com.smartscity.evaluate.repository.UserRepository;
import com.smartscity.evaluate.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * Service Implementation for managing {@link Evaluation}.
 */
@Service
@Transactional
public class EvaluationService {

    private final Logger log = LoggerFactory.getLogger(EvaluationService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpeakerService speakerService;

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


        int taskSource  = ObjectUtils.isEmpty(evaluation.getTaskSourceScore())               ? 0 : evaluation.getTaskSourceScore();
        int dais        = ObjectUtils.isEmpty(evaluation.getDiscoveryAndInnovationScore())   ? 0 : evaluation.getDiscoveryAndInnovationScore();
        int als         = ObjectUtils.isEmpty(evaluation.getAdvancedLevelScore())            ? 0 : evaluation.getAdvancedLevelScore();
        int aaps        = ObjectUtils.isEmpty(evaluation.getApplicationAndPromotionScore())  ? 0 : evaluation.getApplicationAndPromotionScore();
        int ps          = ObjectUtils.isEmpty(evaluation.getPaperScore())                    ? 0 : evaluation.getPaperScore();
        int rs          = ObjectUtils.isEmpty(evaluation.getReplyScore())                    ? 0 : evaluation.getReplyScore();

        evaluation.setTotalScore(   taskSource  + dais + als + aaps + ps + rs     );
        return evaluationRepository.save(evaluation);
    }


    /**
     * 1、掐头 去尾
     * 2、计算平均分
     * @param level
     * @return
     */
    @Transactional(readOnly = true)
    public List<Evaluation> findByLevel(Level level) {


        long   MINID = 0l;
        double MIN = 0.0d;
        long   MAXID = 0l;
        double MAX = 0.0d;

        List<Evaluation> evaluations = evaluationRepository.findByLevel(level);
        Map<Long,Evaluation> map = toConvertEval(evaluations);
        for(Evaluation evaluation:evaluations){
//            if(evaluation)
        }


        return evaluations;
    }



    /**
     *
     * 1、获取所有 演讲者信息
     * 2、获取所有 当前专家评价数据
     * 3、合并
     * 4、输出
     * Get all the evaluations.
     *
     * @return the list of entities.
     */
    public List<Evaluation> findAll() {

        List<Speaker> speakers = speakerService.findAll();

        log.debug("Request to get all Evaluations");
//        if (SecurityUtils.getCurrentUserLogin().get().equals("admin")) {
//            return evaluationRepository.findAll();
//        }else {
            List<Evaluation> evaluations = evaluationRepository.findByUserIsCurrentUser();
//        }

        return merge(speakers, evaluations);
    }

    private List<Evaluation> merge(List<Speaker> speakers, List<Evaluation> evaluations) {
        HashMap<Long, Evaluation> map = toConvert(speakers);

        for(Evaluation evaluation:evaluations){
            if(map.containsKey(evaluation.getSpeakerId())){
                map.put(evaluation.getSpeakerId(), evaluation);
            }
        }

        List<Evaluation> array = new ArrayList<>();
        for (Map.Entry<Long, Evaluation> entry : map.entrySet()) {
            array.add(entry.getValue());
        }
        return array;
    }

    private HashMap<Long, Evaluation> toConvertEval(List<Evaluation> evaluations) {
        HashMap<Long, Evaluation> map = new HashMap<>();
        for(Evaluation evaluation : evaluations){
            map.put(evaluation.getId(), evaluation);
        }
        return map;
    }
    private HashMap<Long, Evaluation> toConvert(List<Speaker> speakers) {
        HashMap<Long, Evaluation> map = new HashMap<>();
        for(Speaker speaker : speakers){
            Evaluation evaluation = new Evaluation();
            evaluation.setTitle(        speaker.getTitle());
            evaluation.setOrgName(      speaker.getOrgName());
            evaluation.setActor(        speaker.getActor());
            evaluation.setSpeaker(      speaker.getSpeaker());
            evaluation.setLevel(        speaker.getLevel());
            evaluation.setSpeakerId(    speaker.getId());


            map.put(speaker.getId(), evaluation);
        }
        return map;
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
