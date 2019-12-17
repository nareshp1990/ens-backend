package com.ens.service;

import com.ens.domain.entity.location.Area;
import com.ens.domain.entity.location.Country;
import com.ens.domain.entity.location.District;
import com.ens.domain.entity.location.State;
import com.ens.domain.entity.news.NewsItem;
import com.ens.domain.entity.user.User;
import com.ens.exception.BadRequestException;
import com.ens.exception.ResourceNotFoundException;
import com.ens.repo.location.AreaRepository;
import com.ens.repo.location.CountryRepository;
import com.ens.repo.location.DistrictRepository;
import com.ens.repo.location.StateRepository;
import com.ens.repo.news.NewsItemRepository;
import com.ens.repo.user.UserRepository;
import com.ens.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsItemRepository newsItemRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private AreaRepository areaRepository;

    public void validatePageNumberAndSize(int page, int size) {

        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }

    }

    public User validateUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    public NewsItem validateNewsItem(Long newsItemId){
        return newsItemRepository.findById(newsItemId).orElseThrow(() -> new ResourceNotFoundException("News", "id", newsItemId));
    }

    public Country validateCountry(Long countryId){
        return countryRepository.findById(countryId).orElseThrow(() -> new ResourceNotFoundException("Country", "id", countryId));
    }

    public State validateState(Long stateId){
        return stateRepository.findById(stateId).orElseThrow(() -> new ResourceNotFoundException("State", "id", stateId));
    }

    public District validateDistrict(Long districtId){
        return districtRepository.findById(districtId).orElseThrow(() -> new ResourceNotFoundException("District", "id", districtId));
    }

    public Area validateArea(Long areaId){
        return areaRepository.findById(areaId).orElseThrow(() -> new ResourceNotFoundException("Area", "id", areaId));
    }

}
