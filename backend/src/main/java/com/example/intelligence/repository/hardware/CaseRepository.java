package com.example.intelligence.repository.hardware;

import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.exception.respository.FindNullException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CaseRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Cases> getAll() {
        return em.createQuery("select u from Cases u", Cases.class)
                .getResultList();
    }

    public Optional<Cases> findById(Long id) {
        if (id == null) {
            throw new FindNullException("Case 조회 시 null을 입력할 수 없습니다.");
        }
        return Optional.ofNullable(em.find(Cases.class, id));
    }

    public Optional<Cases> findByName(String name) {
        if (name == null) {
            throw new FindNullException("Case 조회 시 null을 입력할 수 없습니다.");
        }
        return em.createQuery("select u from Cases u where u.name = :name", Cases.class)
                .setParameter("name", name)
                .getResultStream().findAny();
    }

    @Transactional
    public void saveAll(List<Cases> ssds) {
        for (Cases c : ssds) {
            em.persist(c);
        }
    }
}
