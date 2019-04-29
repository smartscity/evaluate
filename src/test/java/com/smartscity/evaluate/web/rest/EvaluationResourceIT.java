package com.smartscity.evaluate.web.rest;

import com.smartscity.evaluate.EvaluateApp;

import com.smartscity.evaluate.domain.Evaluation;
import com.smartscity.evaluate.repository.EvaluationRepository;
import com.smartscity.evaluate.service.EvaluationService;
import com.smartscity.evaluate.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.smartscity.evaluate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.smartscity.evaluate.domain.enumeration.Level;
import com.smartscity.evaluate.domain.enumeration.TaskSource;
import com.smartscity.evaluate.domain.enumeration.DiscoveryAndInnovation;
import com.smartscity.evaluate.domain.enumeration.AdvancedLevel;
import com.smartscity.evaluate.domain.enumeration.ApplicationAndPromotion;
/**
 * Integration tests for the {@Link EvaluationResource} REST controller.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EvaluateApp.class)
public class EvaluationResourceIT {

    private static final Integer DEFAULT_SPEAKER_ID = 1;
    private static final Integer UPDATED_SPEAKER_ID = 2;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACTOR = "AAAAAAAAAA";
    private static final String UPDATED_ACTOR = "BBBBBBBBBB";

    private static final String DEFAULT_SPEAKER = "AAAAAAAAAA";
    private static final String UPDATED_SPEAKER = "BBBBBBBBBB";

    private static final Level DEFAULT_LEVEL = Level.FIRST;
    private static final Level UPDATED_LEVEL = Level.SECOND;

    private static final TaskSource DEFAULT_TASK_SOURCE = TaskSource.NATIONAL_PLAN;
    private static final TaskSource UPDATED_TASK_SOURCE = TaskSource.BUWEI_PLAN;

    private static final Integer DEFAULT_TASK_SOURCE_SCORE = 1;
    private static final Integer UPDATED_TASK_SOURCE_SCORE = 2;

    private static final DiscoveryAndInnovation DEFAULT_DISCOVERY_AND_INNOVATION = DiscoveryAndInnovation.HIGH;
    private static final DiscoveryAndInnovation UPDATED_DISCOVERY_AND_INNOVATION = DiscoveryAndInnovation.MIDDLE;

    private static final Integer DEFAULT_DISCOVERY_AND_INNOVATION_SCORE = 1;
    private static final Integer UPDATED_DISCOVERY_AND_INNOVATION_SCORE = 2;

    private static final AdvancedLevel DEFAULT_ADVANCED_LEVEL = AdvancedLevel.LEADING;
    private static final AdvancedLevel UPDATED_ADVANCED_LEVEL = AdvancedLevel.ADVANCED;

    private static final Integer DEFAULT_ADVANCED_LEVEL_SCORE = 1;
    private static final Integer UPDATED_ADVANCED_LEVEL_SCORE = 2;

    private static final ApplicationAndPromotion DEFAULT_APPLICATION_AND_PROMOTION = ApplicationAndPromotion.EXCELLENCE;
    private static final ApplicationAndPromotion UPDATED_APPLICATION_AND_PROMOTION = ApplicationAndPromotion.OBVIOUS;

    private static final Integer DEFAULT_APPLICATION_AND_PROMOTION_SCORE = 1;
    private static final Integer UPDATED_APPLICATION_AND_PROMOTION_SCORE = 2;

    private static final Integer DEFAULT_PAPER_SCORE = 1;
    private static final Integer UPDATED_PAPER_SCORE = 2;

    private static final Integer DEFAULT_REPLY_SCORE = 1;
    private static final Integer UPDATED_REPLY_SCORE = 2;

    private static final Integer DEFAULT_TOTAL_SCORE = 1;
    private static final Integer UPDATED_TOTAL_SCORE = 2;

    private static final Double DEFAULT_AVG_SCORE = 1D;
    private static final Double UPDATED_AVG_SCORE = 2D;

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_EVALUATOR = "AAAAAAAAAA";
    private static final String UPDATED_EVALUATOR = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restEvaluationMockMvc;

    private Evaluation evaluation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EvaluationResource evaluationResource = new EvaluationResource(evaluationService);
        this.restEvaluationMockMvc = MockMvcBuilders.standaloneSetup(evaluationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Evaluation createEntity(EntityManager em) {
        Evaluation evaluation = new Evaluation()
            .speakerId(DEFAULT_SPEAKER_ID)
            .title(DEFAULT_TITLE)
            .orgName(DEFAULT_ORG_NAME)
            .actor(DEFAULT_ACTOR)
            .speaker(DEFAULT_SPEAKER)
            .level(DEFAULT_LEVEL)
            .taskSource(DEFAULT_TASK_SOURCE)
            .taskSourceScore(DEFAULT_TASK_SOURCE_SCORE)
            .discoveryAndInnovation(DEFAULT_DISCOVERY_AND_INNOVATION)
            .discoveryAndInnovationScore(DEFAULT_DISCOVERY_AND_INNOVATION_SCORE)
            .advancedLevel(DEFAULT_ADVANCED_LEVEL)
            .advancedLevelScore(DEFAULT_ADVANCED_LEVEL_SCORE)
            .applicationAndPromotion(DEFAULT_APPLICATION_AND_PROMOTION)
            .applicationAndPromotionScore(DEFAULT_APPLICATION_AND_PROMOTION_SCORE)
            .paperScore(DEFAULT_PAPER_SCORE)
            .replyScore(DEFAULT_REPLY_SCORE)
            .totalScore(DEFAULT_TOTAL_SCORE)
            .avgScore(DEFAULT_AVG_SCORE)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME)
            .evaluator(DEFAULT_EVALUATOR)
            .remark(DEFAULT_REMARK);
        return evaluation;
    }

    @Before
    public void initTest() {
        evaluation = createEntity(em);
    }

    @Test
    @Transactional
    public void createEvaluation() throws Exception {
        int databaseSizeBeforeCreate = evaluationRepository.findAll().size();

        // Create the Evaluation
        restEvaluationMockMvc.perform(post("/api/evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evaluation)))
            .andExpect(status().isCreated());

        // Validate the Evaluation in the database
        List<Evaluation> evaluationList = evaluationRepository.findAll();
        assertThat(evaluationList).hasSize(databaseSizeBeforeCreate + 1);
        Evaluation testEvaluation = evaluationList.get(evaluationList.size() - 1);
        assertThat(testEvaluation.getSpeakerId()).isEqualTo(DEFAULT_SPEAKER_ID);
        assertThat(testEvaluation.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testEvaluation.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
        assertThat(testEvaluation.getActor()).isEqualTo(DEFAULT_ACTOR);
        assertThat(testEvaluation.getSpeaker()).isEqualTo(DEFAULT_SPEAKER);
        assertThat(testEvaluation.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testEvaluation.getTaskSource()).isEqualTo(DEFAULT_TASK_SOURCE);
        assertThat(testEvaluation.getTaskSourceScore()).isEqualTo(DEFAULT_TASK_SOURCE_SCORE);
        assertThat(testEvaluation.getDiscoveryAndInnovation()).isEqualTo(DEFAULT_DISCOVERY_AND_INNOVATION);
        assertThat(testEvaluation.getDiscoveryAndInnovationScore()).isEqualTo(DEFAULT_DISCOVERY_AND_INNOVATION_SCORE);
        assertThat(testEvaluation.getAdvancedLevel()).isEqualTo(DEFAULT_ADVANCED_LEVEL);
        assertThat(testEvaluation.getAdvancedLevelScore()).isEqualTo(DEFAULT_ADVANCED_LEVEL_SCORE);
        assertThat(testEvaluation.getApplicationAndPromotion()).isEqualTo(DEFAULT_APPLICATION_AND_PROMOTION);
        assertThat(testEvaluation.getApplicationAndPromotionScore()).isEqualTo(DEFAULT_APPLICATION_AND_PROMOTION_SCORE);
        assertThat(testEvaluation.getPaperScore()).isEqualTo(DEFAULT_PAPER_SCORE);
        assertThat(testEvaluation.getReplyScore()).isEqualTo(DEFAULT_REPLY_SCORE);
        assertThat(testEvaluation.getTotalScore()).isEqualTo(DEFAULT_TOTAL_SCORE);
        assertThat(testEvaluation.getAvgScore()).isEqualTo(DEFAULT_AVG_SCORE);
        assertThat(testEvaluation.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testEvaluation.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testEvaluation.getEvaluator()).isEqualTo(DEFAULT_EVALUATOR);
        assertThat(testEvaluation.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createEvaluationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = evaluationRepository.findAll().size();

        // Create the Evaluation with an existing ID
        evaluation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEvaluationMockMvc.perform(post("/api/evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evaluation)))
            .andExpect(status().isBadRequest());

        // Validate the Evaluation in the database
        List<Evaluation> evaluationList = evaluationRepository.findAll();
        assertThat(evaluationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEvaluations() throws Exception {
        // Initialize the database
        evaluationRepository.saveAndFlush(evaluation);

        // Get all the evaluationList
        restEvaluationMockMvc.perform(get("/api/evaluations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluation.getId().intValue())))
            .andExpect(jsonPath("$.[*].speakerId").value(hasItem(DEFAULT_SPEAKER_ID)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].orgName").value(hasItem(DEFAULT_ORG_NAME.toString())))
            .andExpect(jsonPath("$.[*].actor").value(hasItem(DEFAULT_ACTOR.toString())))
            .andExpect(jsonPath("$.[*].speaker").value(hasItem(DEFAULT_SPEAKER.toString())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].taskSource").value(hasItem(DEFAULT_TASK_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].taskSourceScore").value(hasItem(DEFAULT_TASK_SOURCE_SCORE)))
            .andExpect(jsonPath("$.[*].discoveryAndInnovation").value(hasItem(DEFAULT_DISCOVERY_AND_INNOVATION.toString())))
            .andExpect(jsonPath("$.[*].discoveryAndInnovationScore").value(hasItem(DEFAULT_DISCOVERY_AND_INNOVATION_SCORE)))
            .andExpect(jsonPath("$.[*].advancedLevel").value(hasItem(DEFAULT_ADVANCED_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].advancedLevelScore").value(hasItem(DEFAULT_ADVANCED_LEVEL_SCORE)))
            .andExpect(jsonPath("$.[*].applicationAndPromotion").value(hasItem(DEFAULT_APPLICATION_AND_PROMOTION.toString())))
            .andExpect(jsonPath("$.[*].applicationAndPromotionScore").value(hasItem(DEFAULT_APPLICATION_AND_PROMOTION_SCORE)))
            .andExpect(jsonPath("$.[*].paperScore").value(hasItem(DEFAULT_PAPER_SCORE)))
            .andExpect(jsonPath("$.[*].replyScore").value(hasItem(DEFAULT_REPLY_SCORE)))
            .andExpect(jsonPath("$.[*].totalScore").value(hasItem(DEFAULT_TOTAL_SCORE)))
            .andExpect(jsonPath("$.[*].avgScore").value(hasItem(DEFAULT_AVG_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].evaluator").value(hasItem(DEFAULT_EVALUATOR.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }
    
    @Test
    @Transactional
    public void getEvaluation() throws Exception {
        // Initialize the database
        evaluationRepository.saveAndFlush(evaluation);

        // Get the evaluation
        restEvaluationMockMvc.perform(get("/api/evaluations/{id}", evaluation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(evaluation.getId().intValue()))
            .andExpect(jsonPath("$.speakerId").value(DEFAULT_SPEAKER_ID))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.orgName").value(DEFAULT_ORG_NAME.toString()))
            .andExpect(jsonPath("$.actor").value(DEFAULT_ACTOR.toString()))
            .andExpect(jsonPath("$.speaker").value(DEFAULT_SPEAKER.toString()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL.toString()))
            .andExpect(jsonPath("$.taskSource").value(DEFAULT_TASK_SOURCE.toString()))
            .andExpect(jsonPath("$.taskSourceScore").value(DEFAULT_TASK_SOURCE_SCORE))
            .andExpect(jsonPath("$.discoveryAndInnovation").value(DEFAULT_DISCOVERY_AND_INNOVATION.toString()))
            .andExpect(jsonPath("$.discoveryAndInnovationScore").value(DEFAULT_DISCOVERY_AND_INNOVATION_SCORE))
            .andExpect(jsonPath("$.advancedLevel").value(DEFAULT_ADVANCED_LEVEL.toString()))
            .andExpect(jsonPath("$.advancedLevelScore").value(DEFAULT_ADVANCED_LEVEL_SCORE))
            .andExpect(jsonPath("$.applicationAndPromotion").value(DEFAULT_APPLICATION_AND_PROMOTION.toString()))
            .andExpect(jsonPath("$.applicationAndPromotionScore").value(DEFAULT_APPLICATION_AND_PROMOTION_SCORE))
            .andExpect(jsonPath("$.paperScore").value(DEFAULT_PAPER_SCORE))
            .andExpect(jsonPath("$.replyScore").value(DEFAULT_REPLY_SCORE))
            .andExpect(jsonPath("$.totalScore").value(DEFAULT_TOTAL_SCORE))
            .andExpect(jsonPath("$.avgScore").value(DEFAULT_AVG_SCORE.doubleValue()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.evaluator").value(DEFAULT_EVALUATOR.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEvaluation() throws Exception {
        // Get the evaluation
        restEvaluationMockMvc.perform(get("/api/evaluations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEvaluation() throws Exception {
        // Initialize the database
        evaluationService.save(evaluation);

        int databaseSizeBeforeUpdate = evaluationRepository.findAll().size();

        // Update the evaluation
        Evaluation updatedEvaluation = evaluationRepository.findById(evaluation.getId()).get();
        // Disconnect from session so that the updates on updatedEvaluation are not directly saved in db
        em.detach(updatedEvaluation);
        updatedEvaluation
            .speakerId(UPDATED_SPEAKER_ID)
            .title(UPDATED_TITLE)
            .orgName(UPDATED_ORG_NAME)
            .actor(UPDATED_ACTOR)
            .speaker(UPDATED_SPEAKER)
            .level(UPDATED_LEVEL)
            .taskSource(UPDATED_TASK_SOURCE)
            .taskSourceScore(UPDATED_TASK_SOURCE_SCORE)
            .discoveryAndInnovation(UPDATED_DISCOVERY_AND_INNOVATION)
            .discoveryAndInnovationScore(UPDATED_DISCOVERY_AND_INNOVATION_SCORE)
            .advancedLevel(UPDATED_ADVANCED_LEVEL)
            .advancedLevelScore(UPDATED_ADVANCED_LEVEL_SCORE)
            .applicationAndPromotion(UPDATED_APPLICATION_AND_PROMOTION)
            .applicationAndPromotionScore(UPDATED_APPLICATION_AND_PROMOTION_SCORE)
            .paperScore(UPDATED_PAPER_SCORE)
            .replyScore(UPDATED_REPLY_SCORE)
            .totalScore(UPDATED_TOTAL_SCORE)
            .avgScore(UPDATED_AVG_SCORE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .evaluator(UPDATED_EVALUATOR)
            .remark(UPDATED_REMARK);

        restEvaluationMockMvc.perform(put("/api/evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEvaluation)))
            .andExpect(status().isOk());

        // Validate the Evaluation in the database
        List<Evaluation> evaluationList = evaluationRepository.findAll();
        assertThat(evaluationList).hasSize(databaseSizeBeforeUpdate);
        Evaluation testEvaluation = evaluationList.get(evaluationList.size() - 1);
        assertThat(testEvaluation.getSpeakerId()).isEqualTo(UPDATED_SPEAKER_ID);
        assertThat(testEvaluation.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testEvaluation.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testEvaluation.getActor()).isEqualTo(UPDATED_ACTOR);
        assertThat(testEvaluation.getSpeaker()).isEqualTo(UPDATED_SPEAKER);
        assertThat(testEvaluation.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testEvaluation.getTaskSource()).isEqualTo(UPDATED_TASK_SOURCE);
        assertThat(testEvaluation.getTaskSourceScore()).isEqualTo(UPDATED_TASK_SOURCE_SCORE);
        assertThat(testEvaluation.getDiscoveryAndInnovation()).isEqualTo(UPDATED_DISCOVERY_AND_INNOVATION);
        assertThat(testEvaluation.getDiscoveryAndInnovationScore()).isEqualTo(UPDATED_DISCOVERY_AND_INNOVATION_SCORE);
        assertThat(testEvaluation.getAdvancedLevel()).isEqualTo(UPDATED_ADVANCED_LEVEL);
        assertThat(testEvaluation.getAdvancedLevelScore()).isEqualTo(UPDATED_ADVANCED_LEVEL_SCORE);
        assertThat(testEvaluation.getApplicationAndPromotion()).isEqualTo(UPDATED_APPLICATION_AND_PROMOTION);
        assertThat(testEvaluation.getApplicationAndPromotionScore()).isEqualTo(UPDATED_APPLICATION_AND_PROMOTION_SCORE);
        assertThat(testEvaluation.getPaperScore()).isEqualTo(UPDATED_PAPER_SCORE);
        assertThat(testEvaluation.getReplyScore()).isEqualTo(UPDATED_REPLY_SCORE);
        assertThat(testEvaluation.getTotalScore()).isEqualTo(UPDATED_TOTAL_SCORE);
        assertThat(testEvaluation.getAvgScore()).isEqualTo(UPDATED_AVG_SCORE);
        assertThat(testEvaluation.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testEvaluation.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testEvaluation.getEvaluator()).isEqualTo(UPDATED_EVALUATOR);
        assertThat(testEvaluation.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = evaluationRepository.findAll().size();

        // Create the Evaluation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEvaluationMockMvc.perform(put("/api/evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evaluation)))
            .andExpect(status().isBadRequest());

        // Validate the Evaluation in the database
        List<Evaluation> evaluationList = evaluationRepository.findAll();
        assertThat(evaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEvaluation() throws Exception {
        // Initialize the database
        evaluationService.save(evaluation);

        int databaseSizeBeforeDelete = evaluationRepository.findAll().size();

        // Delete the evaluation
        restEvaluationMockMvc.perform(delete("/api/evaluations/{id}", evaluation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Evaluation> evaluationList = evaluationRepository.findAll();
        assertThat(evaluationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Evaluation.class);
        Evaluation evaluation1 = new Evaluation();
        evaluation1.setId(1L);
        Evaluation evaluation2 = new Evaluation();
        evaluation2.setId(evaluation1.getId());
        assertThat(evaluation1).isEqualTo(evaluation2);
        evaluation2.setId(2L);
        assertThat(evaluation1).isNotEqualTo(evaluation2);
        evaluation1.setId(null);
        assertThat(evaluation1).isNotEqualTo(evaluation2);
    }
}