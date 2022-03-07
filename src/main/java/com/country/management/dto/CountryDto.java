package com.country.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long countryId;
    @NotEmpty(message="name can't be empty")
    private String name;
    @NotEmpty(message="symbol can't be empty")
    private String symbol;

    private String successMessage;


}