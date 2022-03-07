package com.country.management.controller;

import com.country.management.dto.CountryDto;
import com.country.management.entity.Country;
import com.country.management.services.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.BDDMockito.given;
import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class CountryControllerTest {


    @InjectMocks
    private CountryController countryController;

    @Mock
    private CountryService countryService;

    @Before
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCountries() throws Exception {
        CountryDto country = new CountryDto();
        country.setCountryId(1L);
        country.setName("India");
        country.setSymbol("IND");

        CountryDto country1 = new CountryDto();
        country1.setCountryId(2L);
        country1.setName("Australia");
        country1.setSymbol("AUS");

        List<CountryDto> allCountry = new ArrayList<CountryDto>();
        allCountry.add(country);
        allCountry.add(country1);
        when(countryService.getAllCountry()).thenReturn(allCountry);
        ResponseEntity<List<CountryDto>> countries = countryController.getCountries();
        assertEquals(2, countries.getBody().size());
        verify(countryService, times(1)).getAllCountry();
    }

    @Test
    public void getCountryById() {

        CountryDto country1 = new CountryDto();
        country1.setCountryId(2L);
        country1.setName("Australia");
        country1.setSymbol("AUS");

        when(countryService.getCountryById(2L)).thenReturn(country1);
        ResponseEntity<CountryDto> countryById = countryController.getCountryById(2L);
        assertEquals(countryById.getBody().getName(),"Australia");
        verify(countryService, times(1)).getCountryById(anyLong());


    }

    @Test
    public void addCountry() {

        CountryDto country1 = new CountryDto();
        country1.setCountryId(5L);
        country1.setName("Newzeland");
        country1.setSymbol("NZ");
        when(countryService.addCountry(country1)).thenReturn(country1);
        ResponseEntity<CountryDto> countryById = countryController.addCountry(country1);
        assertEquals(countryById.getBody().getName(),"Newzeland");
        verify(countryService, times(1)).addCountry(any(CountryDto.class));

    }

    @Test
    public void updateCountry() {
        CountryDto country1 = new CountryDto();
        country1.setCountryId(6L);
        country1.setName("China");
        country1.setSymbol("Ch");
        when(countryService.updateCountry(country1,6L)).thenReturn(country1);
        ResponseEntity<CountryDto> countryById = countryController.updateCountry(country1,6L);
        assertEquals(countryById.getBody().getName(),"China");
        verify(countryService, times(1)).updateCountry(any(CountryDto.class),anyLong());

    }

    @Test
    public void deleteCountry() {
        CountryDto country1 = new CountryDto();
        country1.setCountryId(6L);
        country1.setName("China");
        country1.setSymbol("Ch");
        when(countryService.deleteCountry(anyLong())).thenReturn(country1);
        ResponseEntity<CountryDto> countryById = countryController.deleteCountry(6L);
        assertEquals(countryById.getBody().getName(),"China");
        verify(countryService, times(1)).deleteCountry(anyLong());


    }
}