package com.smartscity.evaluate.web.rest;

import com.smartscity.evaluate.EvaluateApp;

import com.smartscity.evaluate.domain.Speaker;
import com.smartscity.evaluate.repository.SpeakerRepository;
import com.smartscity.evaluate.service.SpeakerService;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.smartscity.evaluate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.smartscity.evaluate.domain.enumeration.Level;
import com.smartscity.evaluate.domain.enumeration.Review;
/**
 * Integration tests for the {@Link SpeakerResource} REST controller.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EvaluateApp.class)
public class SpeakerResourceIT {

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

    private static final byte[] DEFAULT_PDF = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PDF = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PDF_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PDF_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final Review DEFAULT_REVIEW = Review.UNDO;
    private static final Review UPDATED_REVIEW = Review.APPROVAL;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private SpeakerService speakerService;

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

    private MockMvc restSpeakerMockMvc;

    private Speaker speaker;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SpeakerResource speakerResource = new SpeakerResource(speakerService);
        this.restSpeakerMockMvc = MockMvcBuilders.standaloneSetup(speakerResource)
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
    public static Speaker createEntity(EntityManager em) {
        Speaker speaker = new Speaker()
            .title(DEFAULT_TITLE)
            .orgName(DEFAULT_ORG_NAME)
            .actor(DEFAULT_ACTOR)
            .speaker(DEFAULT_SPEAKER)
            .level(DEFAULT_LEVEL)
            .pdf(DEFAULT_PDF)
            .pdfContentType(DEFAULT_PDF_CONTENT_TYPE)
            .path(DEFAULT_PATH)
            .review(DEFAULT_REVIEW)
            .remark(DEFAULT_REMARK);
        return speaker;
    }

    @Before
    public void initTest() {
        speaker = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpeaker() throws Exception {
        int databaseSizeBeforeCreate = speakerRepository.findAll().size();

        // Create the Speaker
        restSpeakerMockMvc.perform(post("/api/speakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(speaker)))
            .andExpect(status().isCreated());

        // Validate the Speaker in the database
        List<Speaker> speakerList = speakerRepository.findAll();
        assertThat(speakerList).hasSize(databaseSizeBeforeCreate + 1);
        Speaker testSpeaker = speakerList.get(speakerList.size() - 1);
        assertThat(testSpeaker.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testSpeaker.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
        assertThat(testSpeaker.getActor()).isEqualTo(DEFAULT_ACTOR);
        assertThat(testSpeaker.getSpeaker()).isEqualTo(DEFAULT_SPEAKER);
        assertThat(testSpeaker.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testSpeaker.getPdf()).isEqualTo(DEFAULT_PDF);
        assertThat(testSpeaker.getPdfContentType()).isEqualTo(DEFAULT_PDF_CONTENT_TYPE);
        assertThat(testSpeaker.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testSpeaker.getReview()).isEqualTo(DEFAULT_REVIEW);
        assertThat(testSpeaker.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createSpeakerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = speakerRepository.findAll().size();

        // Create the Speaker with an existing ID
        speaker.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpeakerMockMvc.perform(post("/api/speakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(speaker)))
            .andExpect(status().isBadRequest());

        // Validate the Speaker in the database
        List<Speaker> speakerList = speakerRepository.findAll();
        assertThat(speakerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSpeakers() throws Exception {
        // Initialize the database
        speakerRepository.saveAndFlush(speaker);

        // Get all the speakerList
        restSpeakerMockMvc.perform(get("/api/speakers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(speaker.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].orgName").value(hasItem(DEFAULT_ORG_NAME.toString())))
            .andExpect(jsonPath("$.[*].actor").value(hasItem(DEFAULT_ACTOR.toString())))
            .andExpect(jsonPath("$.[*].speaker").value(hasItem(DEFAULT_SPEAKER.toString())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].pdfContentType").value(hasItem(DEFAULT_PDF_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].pdf").value(hasItem(Base64Utils.encodeToString(DEFAULT_PDF))))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())))
            .andExpect(jsonPath("$.[*].review").value(hasItem(DEFAULT_REVIEW.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }
    
    @Test
    @Transactional
    public void getSpeaker() throws Exception {
        // Initialize the database
        speakerRepository.saveAndFlush(speaker);

        // Get the speaker
        restSpeakerMockMvc.perform(get("/api/speakers/{id}", speaker.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(speaker.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.orgName").value(DEFAULT_ORG_NAME.toString()))
            .andExpect(jsonPath("$.actor").value(DEFAULT_ACTOR.toString()))
            .andExpect(jsonPath("$.speaker").value(DEFAULT_SPEAKER.toString()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL.toString()))
            .andExpect(jsonPath("$.pdfContentType").value(DEFAULT_PDF_CONTENT_TYPE))
            .andExpect(jsonPath("$.pdf").value(Base64Utils.encodeToString(DEFAULT_PDF)))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH.toString()))
            .andExpect(jsonPath("$.review").value(DEFAULT_REVIEW.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSpeaker() throws Exception {
        // Get the speaker
        restSpeakerMockMvc.perform(get("/api/speakers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpeaker() throws Exception {
        // Initialize the database
        speakerService.save(speaker);

        int databaseSizeBeforeUpdate = speakerRepository.findAll().size();

        // Update the speaker
        Speaker updatedSpeaker = speakerRepository.findById(speaker.getId()).get();
        // Disconnect from session so that the updates on updatedSpeaker are not directly saved in db
        em.detach(updatedSpeaker);
        updatedSpeaker
            .title(UPDATED_TITLE)
            .orgName(UPDATED_ORG_NAME)
            .actor(UPDATED_ACTOR)
            .speaker(UPDATED_SPEAKER)
            .level(UPDATED_LEVEL)
            .pdf(UPDATED_PDF)
            .pdfContentType(UPDATED_PDF_CONTENT_TYPE)
            .path(UPDATED_PATH)
            .review(UPDATED_REVIEW)
            .remark(UPDATED_REMARK);

        restSpeakerMockMvc.perform(put("/api/speakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSpeaker)))
            .andExpect(status().isOk());

        // Validate the Speaker in the database
        List<Speaker> speakerList = speakerRepository.findAll();
        assertThat(speakerList).hasSize(databaseSizeBeforeUpdate);
        Speaker testSpeaker = speakerList.get(speakerList.size() - 1);
        assertThat(testSpeaker.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testSpeaker.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testSpeaker.getActor()).isEqualTo(UPDATED_ACTOR);
        assertThat(testSpeaker.getSpeaker()).isEqualTo(UPDATED_SPEAKER);
        assertThat(testSpeaker.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testSpeaker.getPdf()).isEqualTo(UPDATED_PDF);
        assertThat(testSpeaker.getPdfContentType()).isEqualTo(UPDATED_PDF_CONTENT_TYPE);
        assertThat(testSpeaker.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testSpeaker.getReview()).isEqualTo(UPDATED_REVIEW);
        assertThat(testSpeaker.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingSpeaker() throws Exception {
        int databaseSizeBeforeUpdate = speakerRepository.findAll().size();

        // Create the Speaker

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpeakerMockMvc.perform(put("/api/speakers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(speaker)))
            .andExpect(status().isBadRequest());

        // Validate the Speaker in the database
        List<Speaker> speakerList = speakerRepository.findAll();
        assertThat(speakerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpeaker() throws Exception {
        // Initialize the database
        speakerService.save(speaker);

        int databaseSizeBeforeDelete = speakerRepository.findAll().size();

        // Delete the speaker
        restSpeakerMockMvc.perform(delete("/api/speakers/{id}", speaker.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Speaker> speakerList = speakerRepository.findAll();
        assertThat(speakerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Speaker.class);
        Speaker speaker1 = new Speaker();
        speaker1.setId(1L);
        Speaker speaker2 = new Speaker();
        speaker2.setId(speaker1.getId());
        assertThat(speaker1).isEqualTo(speaker2);
        speaker2.setId(2L);
        assertThat(speaker1).isNotEqualTo(speaker2);
        speaker1.setId(null);
        assertThat(speaker1).isNotEqualTo(speaker2);
    }
}