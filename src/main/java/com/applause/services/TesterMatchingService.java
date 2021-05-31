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

/**
 * Service for retrieving testers data
 */
@Service
@RequiredArgsConstructor
public class TesterMatchingService {

    private final TesterRepository testerRepository;
    private final DeviceRepository deviceRepository;

    /**
     * Converts data retrieved from database
     *
     * @param countries list of countries of wanted testers
     * @param devices   list of devices testers found bugs on
     * @return list of tester data matching for filters
     */
    public List<TesterMatchingDto> findByCountriesAndDevices(List<String> countries, List<String> devices) {
        List<TesterMatchingProjection> testers = testerRepository.findPerCountriesAndDevices(
                CollectionUtils.isEmpty(countries) ? null : countries,
                CollectionUtils.isEmpty(devices) ? null : devices);
        return testers.stream()
                      .map(this::convertToTransferModel)
                      .collect(Collectors.toList());
    }

    /**
     * Returns list of all available countries
     *
     * @return available countries
     */
    public List<String> getAvailableCountries() {
        return testerRepository.getAvailableCountries();
    }

    /**
     * Returns list of all available devices
     *
     * @return available devices
     */
    public List<String> getAvailableDevices() {
        return deviceRepository.getAvailableDevices();
    }

    private TesterMatchingDto convertToTransferModel(TesterMatchingProjection tester) {
        return new TesterMatchingDto(NameUtils.createFullName(tester.getFirstName(), tester.getLastName()), tester.getBugsNumber());
    }
}
