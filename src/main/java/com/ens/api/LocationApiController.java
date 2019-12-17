package com.ens.api;

import com.ens.domain.payload.ApiResponse;
import com.ens.domain.payload.location.AreaRequest;
import com.ens.domain.payload.location.CountryRequest;
import com.ens.domain.payload.location.DistrictRequest;
import com.ens.domain.payload.location.StateRequest;
import com.ens.service.location.LocationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "location service", description = "The location service API", tags = {"location"})
@RestController
@Slf4j
@RequestMapping("/v1/api/locations")
public class LocationApiController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/{userId}/countries")
    public ResponseEntity createCountry(@Valid @RequestBody CountryRequest countryRequest, @PathVariable Long userId) {
        locationService.save(countryRequest, userId);
        return ResponseEntity.ok(new ApiResponse(true, "Created Successfully"));
    }

    @GetMapping("/{userId}/countries")
    public ResponseEntity getCountries(@PathVariable Long userId){
        return ResponseEntity.ok(locationService.getCountries());
    }

    @PostMapping("/{userId}/states")
    public ResponseEntity createCountry(@Valid @RequestBody StateRequest stateRequest, @PathVariable Long userId) {
        locationService.save(stateRequest, userId);
        return ResponseEntity.ok(new ApiResponse(true, "Created Successfully"));
    }

    @GetMapping("/{userId}/states/{countryId}")
    public ResponseEntity getStates(@PathVariable Long countryId, @PathVariable Long userId){
        return ResponseEntity.ok(locationService.getStates(countryId));
    }

    @PostMapping("/{userId}/districts")
    public ResponseEntity createCountry(@Valid @RequestBody DistrictRequest districtRequest, @PathVariable Long userId) {
        locationService.save(districtRequest, userId);
        return ResponseEntity.ok(new ApiResponse(true, "Created Successfully"));
    }

    @GetMapping("/{userId}/districts/{stateId}")
    public ResponseEntity getDistricts(@PathVariable Long stateId, @PathVariable Long userId){
        return ResponseEntity.ok(locationService.getAllDistricts(stateId));
    }

    @PostMapping("/{userId}/areas")
    public ResponseEntity createCountry(@Valid @RequestBody AreaRequest areaRequest, @PathVariable Long userId) {
        locationService.save(areaRequest, userId);
        return ResponseEntity.ok(new ApiResponse(true, "Created Successfully"));
    }

    @GetMapping("/{userId}/areas/{districtId}")
    public ResponseEntity getAreas(@PathVariable Long districtId, @PathVariable Long userId){
        return ResponseEntity.ok(locationService.getAllAreas(districtId));
    }


}
