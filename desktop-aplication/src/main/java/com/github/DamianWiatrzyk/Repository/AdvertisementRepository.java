package com.github.DamianWiatrzyk.Repository;

import com.github.bosik927.model.advertisement.entity.Advertisement;

import javax.persistence.EntityManager;
import java.util.List;

public class AdvertisementRepository {
    private EntityManager entityManager;

    public AdvertisementRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Advertisement> getAllAdvertisements() {
        return entityManager.createQuery("SELECT a FROM advertisements a", Advertisement.class).getResultList();
    }
}
