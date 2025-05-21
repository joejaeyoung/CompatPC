package com.example.intelligence.repository.hardware;

import com.example.intelligence.domain.hardware.PSU;
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
public class PsuRepository {

    @PersistenceContext
    private EntityManager em;

    public List<PSU> getAll() {
        return em.createQuery("select u from PSU u", PSU.class)
                .getResultList();
    }

    public Optional<PSU> findById(Long id) {
        if (id == null) {
            throw new FindNullException("Case 조회 시 null을 입력할 수 없습니다.");
        }
        return Optional.ofNullable(em.find(PSU.class, id));
    }

    public Optional<PSU> findByName(String name) {
        if (name == null) {
            throw new FindNullException("Case 조회 시 null을 입력할 수 없습니다.");
        }
        return em.createQuery("select u from PSU u where u.name = :name", PSU.class)
                .setParameter("name", name)
                .getResultStream().findAny();
    }

    @Transactional
    public void saveAll(List<PSU> ssds) {
        for (PSU c : ssds) {
            em.persist(c);
        }
    }
}
