package cz.edu.upce.fei.datamanager.data.service.impl;

import com.vaadin.flow.router.NotFoundException;
import cz.edu.upce.fei.datamanager.data.entity.SensorData;
import cz.edu.upce.fei.datamanager.data.repository.SensorDataRepository;
import cz.edu.upce.fei.datamanager.data.service.SensorDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class SensorDataServiceTest {

    @MockBean
    private SensorDataRepository sensorDataRepository;

    @Autowired
    private SensorDataService sensorDataService;

    @Test
    void getLatestData_Found() {
        // when
        when(sensorDataRepository.findLastValueBySensorId(1L)).thenReturn(Optional.of(new SensorData()));

        // then
        SensorData sensorData = sensorDataService.getLatestData(1L);

        // verify
        Assertions.assertNotNull(sensorData);
    }
    @Test
    void getLatestData_NotFound() {
        // when
        when(sensorDataRepository.findLastValueBySensorId(1L)).thenReturn(Optional.empty());

        // then - verify
        Assertions.assertThrows(NotFoundException.class, () -> sensorDataService.getLatestData(1L));
    }

}