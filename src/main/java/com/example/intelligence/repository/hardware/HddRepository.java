package com.example.intelligence.repository.hardware;

import com.example.intelligence.domain.hardware.HDD;
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
public class HddRepository {

    @PersistenceContext
    private EntityManager em;

    public List<HDD> getAll() {
        return em.createQuery("select u from HDD u", HDD.class)
                .getResultList();
    }

    public Optional<HDD> findById(Long id) {
        if (id == null) {
            throw new FindNullException("HDD 조회 시 null을 입력할 수 없습니다.");
        }
        return Optional.ofNullable(em.find(HDD.class, id));
    }

    public Optional<HDD> findByName(String name) {
        if (name == null) {
            throw new FindNullException("HDD 조회 시 null을 입력할 수 없습니다.");
        }
        return em.createQuery("select u from HDD u where u.name = :name", HDD.class)
                .setParameter("name", name)
                .getResultStream().findAny();
    }

    @Transactional
    public void saveAll(List<HDD> ssds) {
        for (HDD c : ssds) {
            em.persist(c);
        }
    }
}
