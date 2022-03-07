package com.country.management.controller;

import com.country.management.dto.CountryDto;
import com.country.management.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;


    @GetMapping
    public ResponseEntity<List<CountryDto>> getCountries(){
        List<CountryDto> allCountry = countryService.getAllCountry();
        return new ResponseEntity<>(allCountry, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable("id") long id){
        final CountryDto countryDto = countryService.getCountryById(id);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CountryDto> addCountry(@Valid @RequestBody CountryDto countryDto){
        final CountryDto savedCountryDto = countryService.addCountry(countryDto);
        return new ResponseEntity<>(savedCountryDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CountryDto> updateCountry(@RequestBody CountryDto countryDto,@PathVariable("id") long id){
        final CountryDto savedCountryDto = countryService.updateCountry(countryDto,id);
        return new ResponseEntity<>(savedCountryDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CountryDto> deleteCountry(@PathVariable("id") long id){
        final CountryDto countryDto = countryService.deleteCountry(id);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }
}
