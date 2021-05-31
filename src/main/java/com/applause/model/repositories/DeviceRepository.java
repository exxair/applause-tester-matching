package com.applause.model.repositories;

import com.applause.model.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA interface for Device entity database queries
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    /**
     * Returns names of all available devices
     *
     * @return list of device names
     */
    @Query("select d.description from Device d")
    List<String> getAvailableDevices();
}
