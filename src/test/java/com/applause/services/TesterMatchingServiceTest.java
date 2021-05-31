package com.applause.services;

import com.applause.model.projections.TesterMatchingProjection;
import com.applause.model.repositories.DeviceRepository;
import com.applause.model.repositories.TesterRepository;
import lombok.Builder;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TesterMatchingServiceTest {

    private static final String FIRST_TESTER_NAME = "Jan";
    private static final String FIRST_TESTER_SURNAME = "Kowalski";
    private static final Integer FIRST_TESTER_BUGS = 100;
    private static final String SECOND_TESTER_NAME = "Anna";
    private static final String SECOND_TESTER_SURNAME = "Nowak";
    private static final Integer SECOND_TESTER_BUGS = 25;


    @InjectMocks
    private TesterMatchingService sut;

    @Mock
    private TesterRepository testerRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @Test
    void shouldReturnMatchingTesters() {
        //given
        var countries = Collections.singletonList("PL");
        var devices = Collections.singletonList("iPhone X");
        when(testerRepository.findPerCountriesAndDevices(countries, devices)).thenReturn(getFindPerCountriesAndDevicesResult());

        //when
        var result = sut.findByCountriesAndDevices(countries, devices);

        //then
        assertThat(result).isNotNull().hasSize(2);
        assertThat(result.get(0).getTesterName()).isEqualTo(FIRST_TESTER_NAME + " " + FIRST_TESTER_SURNAME);
        assertThat(result.get(0).getBugsFound()).isEqualTo(FIRST_TESTER_BUGS);
        assertThat(result.get(1).getTesterName()).isEqualTo(SECOND_TESTER_NAME + " " + SECOND_TESTER_SURNAME);
        assertThat(result.get(1).getBugsFound()).isEqualTo(SECOND_TESTER_BUGS);
    }

    @Test
    void shouldReturnEmptyList() {
        //given
        List<String> countries = Collections.emptyList();
        List<String> devices = Collections.emptyList();
        when(testerRepository.findPerCountriesAndDevices(null, null)).thenReturn(Collections.emptyList());

        //when
        var result = sut.findByCountriesAndDevices(countries, devices);

        //then
        assertThat(result).isNotNull().isEmpty();
    }

    @Test
    void shouldCallGetAvailableCountries() {
        //when
        sut.getAvailableCountries();

        //then
        verify(testerRepository).getAvailableCountries();
    }

    @Test
    void shouldCallGetAvailableDevices() {
        //when
        sut.getAvailableDevices();

        //then
        verify(deviceRepository).getAvailableDevices();
    }

    private List<TesterMatchingProjection> getFindPerCountriesAndDevicesResult() {
        return List.of(TesterMatchingProjectionImpl.builder().firstName(FIRST_TESTER_NAME).lastName(FIRST_TESTER_SURNAME).bugsNumber(FIRST_TESTER_BUGS).build(),
                TesterMatchingProjectionImpl.builder().firstName(SECOND_TESTER_NAME).lastName(SECOND_TESTER_SURNAME).bugsNumber(SECOND_TESTER_BUGS).build());
    }

    @Builder
    @Getter
    private static class TesterMatchingProjectionImpl implements TesterMatchingProjection {
        private final String firstName;
        private final String lastName;
        private final Integer bugsNumber;
    }
}