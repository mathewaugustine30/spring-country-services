package com.country.management.services;

import com.country.management.dto.CountryDto;
import com.country.management.entity.Country;
import com.country.management.exception.ResourceNotFoundException;
import com.country.management.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    public static final String DELETED = "Deleted";
    public static final String UPDATED = "Updated";
    public static final String ADDED = "Added";
    public static final String EMPTY = "";
    private static final String COUNTRY = "Country";
    private static final String SUCCESS = " Successfully";

    @Autowired
    private CountryRepository countryRepo;

    @Override
    public List<CountryDto> getAllCountry() {
        List<CountryDto> countryDtoList = new ArrayList<CountryDto>();
         Iterable<Country> countriesIterable = countryRepo.findAll();
        countriesIterable.forEach(country -> {
            final CountryDto countryDto = new CountryDto();
            countryEntityToDto(countryDto,country,EMPTY);
            countryDtoList.add(countryDto);
        });

        return countryDtoList;
    }
    private void countryEntityToDto(CountryDto countryDto,Country country, String type){
        countryDto.setCountryId(country.getCountryId());
        countryDto.setName(country.getName());
        countryDto.setSymbol(country.getSymbol());
        countryDto.setSuccessMessage(COUNTRY + " " +type+SUCCESS);
    }

    private void countryDtoToEntity(CountryDto countryDto,Country country){
        country.setName(countryDto.getName());
        country.setSymbol(countryDto.getSymbol());
    }
    @Override
    public CountryDto getCountryById(long countryId) {
        CountryDto countryDto = new CountryDto();
        Country country = countryRepo.findById(countryId).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, "id", countryId));
        countryEntityToDto(countryDto,country,EMPTY);
        return countryDto;
    }

    @Override
    public CountryDto addCountry(CountryDto countryDto) {

        Country country = new Country();
        countryDtoToEntity(countryDto,country);
        countryRepo.save(country);
        countryEntityToDto(countryDto,country, ADDED);
        return countryDto;
    }

    @Override
    public CountryDto updateCountry(CountryDto countryDto, long id) {
        Country country = countryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, "id", countryDto.getCountryId()));
        countryDtoToEntity(countryDto,country);
        countryRepo.save(country);
        countryEntityToDto(countryDto,country, UPDATED);
        return countryDto;

    }

    @Override
    public CountryDto deleteCountry(long countryId) {
        CountryDto countryDto = new CountryDto();
        Country countryToDelete = countryRepo.findById(countryId).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, "id", countryId));
        countryRepo.delete(countryToDelete);
        countryEntityToDto(countryDto,countryToDelete, DELETED);
        return countryDto;
    }

}
