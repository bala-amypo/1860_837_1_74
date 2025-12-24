package com.example.demo.util;

import com.example.demo.model.Claim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HqlQueryHelper {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Claim> findHighValueClaims(double minAmount) {
        String hql = "FROM Claim c WHERE c.claimAmount > :minAmount";
        TypedQuery<Claim> query = entityManager.createQuery(hql, Claim.class);
        query.setParameter("minAmount", minAmount);
        return query.getResultList();
    }

    public List<Claim> findClaimsByDescriptionKeyword(String keyword) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Claim> cq = cb.createQuery(Claim.class);
        Root<Claim> claim = cq.from(Claim.class);
        
        if (keyword != null && !keyword.isEmpty()) {
            cq.where(cb.like(claim.get("description"), "%" + keyword + "%"));
        }
        
        return entityManager.createQuery(cq).getResultList();
    }
}
