package com.applause.services;

import com.applause.model.dto.TesterMatchingDto;
import com.applause.model.projections.TesterMatchingProjection;
import com.applause.model.repositories.TesterRepository;
import com.applause.utils.NameUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TesterMatchingService {

    private final TesterRepository testerRepository;

    public List<TesterMatchingDto> findByCountriesAndDevices(List<String> countries, List<String> devices) {
        List<TesterMatchingProjection> testers = testerRepository.findByCountriesAndDevices(countries, devices);
        return testers.stream().map(this::convertToTransferModel).collect(Collectors.toList());
    }

    private TesterMatchingDto convertToTransferModel(TesterMatchingProjection tester) {
        return new TesterMatchingDto(NameUtils.createFullName(tester.getFirstName(), tester.getLastName()), tester.getBugsNumber());
    }
}
