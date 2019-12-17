package com.ens.service.location;

import com.ens.domain.entity.location.Area;
import com.ens.domain.entity.location.Country;
import com.ens.domain.entity.location.District;
import com.ens.domain.entity.location.State;
import com.ens.domain.entity.user.User;
import com.ens.domain.payload.location.AreaRequest;
import com.ens.domain.payload.location.CountryRequest;
import com.ens.domain.payload.location.DistrictRequest;
import com.ens.domain.payload.location.StateRequest;
import com.ens.exception.ResourceNotFoundException;
import com.ens.repo.location.AreaRepository;
import com.ens.repo.location.CountryRepository;
import com.ens.repo.location.DistrictRepository;
import com.ens.repo.location.StateRepository;
import com.ens.repo.user.UserRepository;
import com.ens.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LocationService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void save(CountryRequest countryRequest,Long userId){

        User user = validationService.validateUser(userId);

        List<Country> countries = countryRequest.getCountryNames().stream()
                .map(name -> new Country(name, user)).collect(Collectors.toList());

        countryRepository.saveAll(countries);
    }

    public List<Country> getCountries(){
        return countryRepository.findAll();
    }

    @Transactional
    public void save(StateRequest stateRequest,Long userId){
        User user = validationService.validateUser(userId);
        Country country = countryRepository.findById(stateRequest.getCountryId()).orElseThrow(() -> new ResourceNotFoundException());
        List<State> states = stateRequest.getStateNames().stream()
                .map(name -> new State(country, name, user)).collect(
                        Collectors.toList());
        stateRepository.saveAll(states);
    }

    public List<State> getStates(Long countryId){
        return stateRepository.findByCountryId(countryId);
    }

    @Transactional
    public void save(DistrictRequest districtRequest, Long userId){
        User user = validationService.validateUser(userId);
        Country country = countryRepository.findById(districtRequest.getCountryId()).orElseThrow(() -> new ResourceNotFoundException());
        State state = stateRepository.findById(districtRequest.getStateId()).orElseThrow(() -> new ResourceNotFoundException());
        List<District> districts = districtRequest.getDistrictNames().stream()
                .map(name -> new District(country, state, name, user)).collect(
                        Collectors.toList());
        districtRepository.saveAll(districts);
    }

    public List<District> getAllDistricts(Long stateId){
        return districtRepository.findByStateId(stateId);
    }

    @Transactional
    public void save(AreaRequest areaRequest, Long userId){
        User user = validationService.validateUser(userId);
        Country country = countryRepository.findById(areaRequest.getCountryId()).orElseThrow(() -> new ResourceNotFoundException());
        State state = stateRepository.findById(areaRequest.getStateId()).orElseThrow(() -> new ResourceNotFoundException());
        District district = districtRepository.findById(areaRequest.getDistrictId()).orElseThrow(() -> new ResourceNotFoundException());
        List<Area> areas = areaRequest.getAreaNames().stream()
                .map(name -> new Area(country, state, district, name, user))
                .collect(Collectors.toList());
        areaRepository.saveAll(areas);
    }

    public List<Area> getAllAreas(Long districtId){
        return areaRepository.findByDistrictId(districtId);
    }


}
