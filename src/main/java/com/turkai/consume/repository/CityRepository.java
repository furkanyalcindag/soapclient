package com.turkai.consume.repository;

import com.turkai.consume.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    public City findByCountryIdAndPlateNo(int countryId, String plateNo);



}
