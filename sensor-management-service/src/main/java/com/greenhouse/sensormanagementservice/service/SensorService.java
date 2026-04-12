package com.greenhouse.sensormanagementservice.service;

import com.greenhouse.sensormanagementservice.dto.SensorRequestDTO;
import com.greenhouse.sensormanagementservice.dto.SensorResponseDTO;
import com.greenhouse.sensormanagementservice.entity.Format;
import com.greenhouse.sensormanagementservice.entity.Sensor;
import com.greenhouse.sensormanagementservice.exceptions.BusinessException;
import com.greenhouse.sensormanagementservice.mapper.SensorMapper;
import com.greenhouse.sensormanagementservice.repository.FormatRepository;
import com.greenhouse.sensormanagementservice.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final FormatRepository formatRepository;
    private final SensorMapper sensorMapper;

    private final String MESSAGE_FORMAT_NOT_FOUND = "Format not found. The sensor could not be saved.";

    public SensorResponseDTO saveSensor(SensorRequestDTO sensorRequestDTO){

        Format format = formatRepository
                .findById(sensorRequestDTO.getFormatId())
                .orElseThrow(() -> new BusinessException(MESSAGE_FORMAT_NOT_FOUND));

        // TODO: Call another microservice to validate the existance of the greenhouse id

        Sensor sensor = sensorMapper.toEntity(sensorRequestDTO);

        sensor.setFormat(format);

        Sensor sensorSaved = sensorRepository.save(sensor);

        return sensorMapper.toResponseDto(sensorSaved);
    }

}
