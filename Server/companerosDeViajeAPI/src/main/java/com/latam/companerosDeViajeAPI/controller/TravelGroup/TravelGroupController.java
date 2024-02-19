package com.latam.companerosDeViajeAPI.controller.TravelGroup;

import com.latam.companerosDeViajeAPI.dto.auth.AuthResponseDto;
import com.latam.companerosDeViajeAPI.dto.auth.LoginRequestDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupCreatedDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupDTO;
import com.latam.companerosDeViajeAPI.service.TravelGroup.TravelGroupServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("travel-group")

public class TravelGroupController {
    private TravelGroupServiceImp travelGroupServiceImp;

    public TravelGroupController(TravelGroupServiceImp travelGroupServiceImp) {
        this.travelGroupServiceImp = travelGroupServiceImp;
    }

    @PostMapping(value = "create")
    public ResponseEntity<TravelGroupCreatedDto> createTravelGroup(@RequestBody TravelGroupDTO travelGroupDTO, HttpServletRequest request) {
        return ResponseEntity.ok(travelGroupServiceImp.createTravelGroup(travelGroupDTO, request));
    }
}
