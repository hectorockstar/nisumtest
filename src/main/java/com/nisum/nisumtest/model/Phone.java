package com.nisum.nisumtest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@Entity
@Table(name = "phones")
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    private UUID phoneId;
    private Long number;
    private Integer citycode;
    private Integer countrycode;

    public static Phone getPhoneBuilder(Phone phone, UUID userId){
        return Phone.builder()
                .phoneId(UUID.randomUUID())
                .number(phone.getNumber())
                .citycode(phone.getCitycode())
                .countrycode(phone.getCountrycode())
                .build();
    }

    public static List<Phone> getPhoneListBuilder(List<Phone> phones, UUID userId){
        return phones.stream()
                .map(phone -> Phone.getPhoneBuilder(phone, userId))
                .collect(Collectors.toList());
    }


}
