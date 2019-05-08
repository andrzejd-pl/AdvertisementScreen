package com.dybowski.andrzej.panel.repositories;

import com.dybowski.andrzej.panel.models.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
