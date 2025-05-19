package com.example.intelligence.repository.hardware;

import com.example.intelligence.domain.hardware.Cooler;
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
public class CoolerRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Cooler> getAll() {
        return em.createQuery("select u from Cooler u", Cooler.class)
                .getResultList();
    }

    public Optional<Cooler> findById(Long id) {
        if (id == null) {
            throw new FindNullException("Case 조회 시 null을 입력할 수 없습니다.");
        }
        return Optional.ofNullable(em.find(Cooler.class, id));
    }

    public Optional<Cooler> findByName(String name) {
        if (name == null) {
            throw new FindNullException("Case 조회 시 null을 입력할 수 없습니다.");
        }
        return em.createQuery("select u from Cooler u where u.name = :name", Cooler.class)
                .setParameter("name", name)
                .getResultStream().findAny();
    }

    @Transactional
    public void saveAll(List<Cooler> ssds) {
        for (Cooler c : ssds) {
            em.persist(c);
        }
    }
}
