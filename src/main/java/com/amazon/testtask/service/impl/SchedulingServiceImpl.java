package com.amazon.testtask.service.impl;

import com.amazon.testtask.service.FileService;
import com.amazon.testtask.service.SchedulingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    static final Logger log = LoggerFactory.getLogger(SchedulingServiceImpl.class);
    private final FileService fileService;

    @Autowired
    public SchedulingServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Scheduling job for update Database from file, run every 5 minutes
     */
    @Override
    @Scheduled(fixedDelay = 300000)
    public void updateDB() {
        log.info("Start update DB from file");
        fileService.updateDbFromFile();
        log.info("Finish update DB from file");
    }
}
