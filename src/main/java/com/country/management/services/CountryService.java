package com.country.management.services;

import com.country.management.dto.CountryDto;
import com.country.management.entity.Country;

import java.util.List;

public interface CountryService {

    List<CountryDto> getAllCountry();
    CountryDto getCountryById(long countryId);
    CountryDto addCountry(CountryDto country);
    CountryDto updateCountry(CountryDto countryDto,long id);
    CountryDto deleteCountry(long countryId);
}
