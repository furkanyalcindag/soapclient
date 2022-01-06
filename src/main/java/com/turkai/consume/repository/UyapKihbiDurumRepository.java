package com.turkai.consume.repository;

import com.turkai.consume.model.UyapKihbiDurum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UyapKihbiDurumRepository extends JpaRepository<UyapKihbiDurum, Long> {

    List<UyapKihbiDurum> findByDurumTipiOrderBySiraAsc(String tipi);


}
