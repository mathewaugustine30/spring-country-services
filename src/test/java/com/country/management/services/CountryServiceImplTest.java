package com.country.management.services;

import com.country.management.dto.CountryDto;
import com.country.management.entity.Country;
import com.country.management.exception.ResourceNotFoundException;
import com.country.management.repository.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceImplTest {

    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private CountryRepository countryRepository;

    @Test
    public void getAllCountry() {

        Country country = new Country();
        country.setCountryId(1L);
        country.setName("India");
        country.setSymbol("IND");

        Country country1 = new Country();
        country1.setCountryId(2L);
        country1.setName("Australia");
        country1.setSymbol("AUS");

        List<Country> allCountry = new ArrayList<Country>();
        allCountry.add(country);
        allCountry.add(country1);
        when(countryRepository.findAll()).thenReturn(allCountry);

        List<CountryDto> allCountry1 = countryService.getAllCountry();
        assertEquals(2, allCountry1.size());
        verify(countryRepository, times(1)).findAll();

    }

    @Test
    public void getCountryById() {

        Country country = new Country();
        country.setCountryId(1L);
        country.setName("India");
        country.setSymbol("IND");

        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));

        CountryDto countryDto = countryService.getCountryById(1L);
        assertEquals("IND",countryDto.getSymbol());
        verify(countryRepository, times(1)).findById(1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getCountryByIdThrowing_ResourceNotFoundException() {

        Country country = new Country();
        country.setCountryId(1L);
        country.setName("India");
        country.setSymbol("IND");

        //when(countryRepository.findById(1L)).thenReturn(Optional.of(country));

        CountryDto countryDto = countryService.getCountryById(1L);
        verify(countryRepository, times(0)).findById(1L);
    }

    @Test
    public void addCountry() {

        Country country = new Country();
        //country.setCountryId(1L);
        country.setName("India");
        country.setSymbol("IND");

        CountryDto countryDto = new CountryDto();
        countryDto.setCountryId(1L);
        countryDto.setName("India");
        countryDto.setSymbol("IND");

        //when(countryRepository.save(country)).thenReturn(country);

        CountryDto countryDtoReturned = countryService.addCountry(countryDto);
        assertEquals("IND",countryDtoReturned.getSymbol());
        verify(countryRepository, times(1)).save(country);
    }

    @Test
    public void updateCountry() {

        Country country = new Country();
        country.setCountryId(1L);
        country.setName("India");
        country.setSymbol("IND");

        CountryDto countryDto = new CountryDto();
        countryDto.setCountryId(1L);
        countryDto.setName("India");
        countryDto.setSymbol("IND-1");

        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));

        CountryDto countryDtoReturned = countryService.updateCountry(countryDto,1L);
        assertEquals("IND-1",countryDtoReturned.getSymbol());
        verify(countryRepository, times(1)).save(country);
    }


    @Test
    public void deleteCountry() {

        Country country = new Country();
        country.setCountryId(1L);
        country.setName("India");
        country.setSymbol("IND");
        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));

        CountryDto countryDtoReturned = countryService.deleteCountry(1L);
        verify(countryRepository, times(1)).delete(country);
    }


    @Test(expected = ResourceNotFoundException.class)
    public void deleteCountry_ExceptionThrown_ForInvalidUser() {
        Country country = new Country();
/*        country.setCountryId(1L);
        country.setName("India");
        country.setSymbol("IND");*/
        //when(countryRepository.findById(1L)).thenReturn(null);
        CountryDto countryDtoReturned = countryService.deleteCountry(1L);
        verify(countryRepository, times(1)).findById(anyLong());
        verify(countryRepository, times(0)).delete(country);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateCountry_ExceptionThrown_ForInvalidUser() {

        Country country = new Country();
        country.setCountryId(1L);
        country.setName("India");
        country.setSymbol("IND");
        CountryDto countryDto = new CountryDto();
        countryDto.setCountryId(1L);
        countryDto.setName("India");
        countryDto.setSymbol("IND-1");
        CountryDto countryDtoReturned = countryService.updateCountry(countryDto,1L);
        verify(countryRepository, times(0)).save(country);
        verify(countryRepository, times(1)).findById(anyLong());

    }
}