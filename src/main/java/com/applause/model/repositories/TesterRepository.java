package com.applause.model.repositories;

import com.applause.model.entities.Tester;
import com.applause.model.projections.TesterMatchingProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA interface for Tester entity database queries
 */
@Repository
public interface TesterRepository extends JpaRepository<Tester, Long> {

    /**
     * Looks for testers from given list of countries and with given devices
     *
     * @param countries list of countries of wanted testers
     * @param devices   list of devices testers found bugs on
     * @return list of testers matching criteria
     */
    @Query("select t.firstName as firstName, t.lastName as lastName, count(b) as bugsNumber from Tester t " +
            "join t.devices d " +
            "join t.bugs b with b.device = d " +
            "where ((:countries) = null or t.country in :countries) " +
            "and ((:devices) = null or d.description in :devices) " +
            "group by t.testerId " +
            "order by bugsNumber DESC")
    List<TesterMatchingProjection> findPerCountriesAndDevices(List<String> countries, List<String> devices);

    /**
     * Returns names of all available tester countries
     *
     * @return list of countries
     */
    @Query("select distinct t.country from Tester t")
    List<String> getAvailableCountries();
}
