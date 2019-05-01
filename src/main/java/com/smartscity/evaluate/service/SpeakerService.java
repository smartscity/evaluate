package com.smartscity.evaluate.service;

import com.smartscity.evaluate.domain.Speaker;
import com.smartscity.evaluate.repository.SpeakerRepository;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Speaker}.
 */
@Service
@Transactional
public class SpeakerService {

    private final Logger log = LoggerFactory.getLogger(SpeakerService.class);

    private final SpeakerRepository speakerRepository;

    public SpeakerService(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @Value("${smartscity.filepath:./data/}")
    private String path;



    /**
     * Save a speaker.
     *
     * @param speaker the entity to save.
     * @return the persisted entity.
     */
    public Speaker save(Speaker speaker) {

        if(!ObjectUtils.isEmpty(speaker.getPdf())) {
            try {
                String filename = DigestUtils.md5DigestAsHex(speaker.getPdf());
                String filepath = path + filename + suffix(speaker.getPdfContentType());
                FileUtils.writeByteArrayToFile(new File(filepath), speaker.getPdf());
                speaker.setPath(filename + suffix(speaker.getPdfContentType()));

                speaker.setPdf(null);
            } catch (IOException e) {
                log.error("上传文件异常 {} {}", e.getMessage(), e.getCause().getMessage());
            }
        }
        log.debug("Request to save Speaker : {}", speaker);
        return speakerRepository.save(speaker);
    }

    private String suffix(String contentType) {
        switch (contentType){
            case "application/pdf":
                return ".pdf";
            case "image/png":
                return ".png";
            default:
                return "";
        }
    }

    /**
     * Get all the speakers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Speaker> findAll() {
        log.debug("Request to get all Speakers");
        return speakerRepository.findAll();
    }


    /**
     * Get one speaker by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Speaker> findOne(Long id) {
        log.debug("Request to get Speaker : {}", id);
        return speakerRepository.findById(id);
    }

    /**
     * Delete the speaker by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Speaker : {}", id);
        speakerRepository.deleteById(id);
    }
}
