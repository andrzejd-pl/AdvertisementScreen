package com.dybowski.andrzej.panel.repositories;

import com.dybowski.andrzej.panel.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
