package com.github.DamianWiatrzyk.Repository;

import com.github.bosik927.model.news.entity.News;

import javax.persistence.EntityManager;
import java.util.List;

public class NewsRepository {
    private EntityManager entityManager;

    public NewsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<News> getAllNews() {
        return entityManager.createQuery("SELECT a FROM news a", News.class).getResultList();
    }
}
