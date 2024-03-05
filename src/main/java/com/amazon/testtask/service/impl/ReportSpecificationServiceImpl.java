package com.amazon.testtask.service.impl;

import com.amazon.testtask.domain.entity.ReportSpecification;
import com.amazon.testtask.repository.ReportSpecificationRepository;
import com.amazon.testtask.service.ReportSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportSpecificationServiceImpl implements ReportSpecificationService {

    private final ReportSpecificationRepository specificationRepository;

    @Autowired
    public ReportSpecificationServiceImpl(ReportSpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    @Override
    public ReportSpecification getByDataEndTimeAndDataStartTimeAndMarketplaceIdsLikeAndReportType(String dataAndTime, String dataStartTime, List<String> marketplaceIds, String reportType) {
        return specificationRepository.findByDataEndTimeAndDataStartTimeAndMarketplaceIdsLikeAndReportType(dataAndTime, dataStartTime, marketplaceIds, reportType);
    }

    @Override
    public List<ReportSpecification> getByDataStartTime(String date) {
        return specificationRepository.findByDataStartTime(date);
    }

    @Override
    public List<ReportSpecification> getAllReports() {
        return specificationRepository.findAll();
    }
}
