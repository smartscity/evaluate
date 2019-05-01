package com.smartscity.evaluate.web.rest;

import com.smartscity.evaluate.domain.Speaker;
import com.smartscity.evaluate.service.SpeakerService;
import com.smartscity.evaluate.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.smartscity.evaluate.domain.Speaker}.
 */
@RestController
@RequestMapping("/api")
public class SpeakerResource {

    @Value("${smartscity.filepath:./data/}")
    private String path;


    private final Logger log = LoggerFactory.getLogger(SpeakerResource.class);

    private static final String ENTITY_NAME = "speaker";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpeakerService speakerService;

    public SpeakerResource(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    /**
     * {@code POST  /speakers} : Create a new speaker.
     *
     * @param speaker the speaker to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new speaker, or with status {@code 400 (Bad Request)} if the speaker has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/speakers")
    public ResponseEntity<Speaker> createSpeaker(@RequestBody Speaker speaker) throws URISyntaxException {
        log.debug("REST request to save Speaker : {}", speaker);
        if (speaker.getId() != null) {
            throw new BadRequestAlertException("A new speaker cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Speaker result = speakerService.save(speaker);
        return ResponseEntity.created(new URI("/api/speakers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
        try (
            //jdk7新特性，可以直接写到try()括号里面，java会自动关闭
            InputStream inputStream = new FileInputStream(new File(path, id));
            OutputStream outputStream = response.getOutputStream()
        ) {
            //指明为下载
            response.setContentType("application/pdf");
            String fileName = id;
            response.addHeader("Content-Disposition", "inline;fileName=" + fileName);   // 设置文件名
//            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());

            //把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);

            outputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@code PUT  /speakers} : Updates an existing speaker.
     *
     * @param speaker the speaker to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated speaker,
     * or with status {@code 400 (Bad Request)} if the speaker is not valid,
     * or with status {@code 500 (Internal Server Error)} if the speaker couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/speakers")
    public ResponseEntity<Speaker> updateSpeaker(@RequestBody Speaker speaker) throws URISyntaxException {
        log.debug("REST request to update Speaker : {}", speaker);
        if (speaker.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Speaker result = speakerService.save(speaker);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, speaker.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /speakers} : get all the speakers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of speakers in body.
     */
    @GetMapping("/speakers")
    public List<Speaker> getAllSpeakers() {
        log.debug("REST request to get all Speakers");
        return speakerService.findAll();
    }

    /**
     * {@code GET  /speakers/:id} : get the "id" speaker.
     *
     * @param id the id of the speaker to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the speaker, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/speakers/{id}")
    public ResponseEntity<Speaker> getSpeaker(@PathVariable Long id) {
        log.debug("REST request to get Speaker : {}", id);
        Optional<Speaker> speaker = speakerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(speaker);
    }

    /**
     * {@code DELETE  /speakers/:id} : delete the "id" speaker.
     *
     * @param id the id of the speaker to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/speakers/{id}")
    public ResponseEntity<Void> deleteSpeaker(@PathVariable Long id) {
        log.debug("REST request to delete Speaker : {}", id);
        speakerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
