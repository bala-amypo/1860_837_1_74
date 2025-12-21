package com.example.demo.util;

import com.example.demo.model.Claim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HqlQueryHelper {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Claim> findClaimsByDescriptionKeyword(String keyword) {
        return entityManager.createQuery(
                        "SELECT c FROM Claim c WHERE LOWER(c.description) LIKE LOWER(:kw)",
                        Claim.class)
                .setParameter("kw", "%" + keyword + "%")
                .getResultList();
    }

    public List<Claim> findHighValueClaims(Double minAmount) {
        return entityManager.createQuery(
                        "SELECT c FROM Claim c WHERE c.claimAmount > :amt",
                        Claim.class)
                .setParameter("amt", minAmount)
                .getResultList();
    }
}
