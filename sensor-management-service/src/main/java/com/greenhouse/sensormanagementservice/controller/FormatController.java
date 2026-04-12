package com.greenhouse.sensormanagementservice.controller;

import com.greenhouse.sensormanagementservice.dto.FormatRequest;
import com.greenhouse.sensormanagementservice.dto.FormatResponse;
import com.greenhouse.sensormanagementservice.entity.Format;
import com.greenhouse.sensormanagementservice.service.FormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/formats")
@RequiredArgsConstructor
public class FormatController {

    private final FormatService formatService;

    @PostMapping
    public FormatResponse createFormat(@RequestBody FormatRequest request) {

        Format savedFormat = formatService.saveFormat(request.getName());
        FormatResponse response = new FormatResponse();
        response.setId(savedFormat.getId());
        response.setName(savedFormat.getName());

        return response;
    }
}