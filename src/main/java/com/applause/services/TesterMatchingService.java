package com.applause.services;

import com.applause.model.dto.TesterMatchingDto;
import com.applause.model.projections.TesterMatchingProjection;
import com.applause.model.repositories.DeviceRepository;
import com.applause.model.repositories.TesterRepository;
import com.applause.utils.NameUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TesterMatchingService {

    private final TesterRepository testerRepository;
    private final DeviceRepository deviceRepository;

    public List<TesterMatchingDto> findByCountriesAndDevices(List<String> countries, List<String> devices) {
        List<TesterMatchingProjection> testers = testerRepository.findPerCountriesAndDevices(
                CollectionUtils.isEmpty(countries) ? null : countries,
                CollectionUtils.isEmpty(devices) ? null : devices);
        return testers.stream()
                      .map(this::convertToTransferModel)
                      .collect(Collectors.toList());
    }

    public List<String> getAvailableCountries() {
        return testerRepository.getAvailableCountries();
    }

    public List<String> getAvailableDevices() {
        return deviceRepository.getAvailableDevices();
    }

    private TesterMatchingDto convertToTransferModel(TesterMatchingProjection tester) {
        return new TesterMatchingDto(NameUtils.createFullName(tester.getFirstName(), tester.getLastName()), tester.getBugsNumber());
    }
}
