package com.example.intelligence.repository.hardware;

import com.example.intelligence.domain.hardware.RAM;
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
public class RamRepository {

    @PersistenceContext
    private EntityManager em;

    public List<RAM> getAll() {
        return em.createQuery("select u from RAM u", RAM.class)
                .getResultList();
    }

    public Optional<RAM> findById(Long id) {
        if (id == null) {
            throw new FindNullException("Case 조회 시 null을 입력할 수 없습니다.");
        }
        return Optional.ofNullable(em.find(RAM.class, id));
    }

    public Optional<RAM> findByName(String name) {
        if (name == null) {
            throw new FindNullException("Case 조회 시 null을 입력할 수 없습니다.");
        }
        return em.createQuery("select u from RAM u where u.name = :name", RAM.class)
                .setParameter("name", name)
                .getResultStream().findAny();
    }

    @Transactional
    public void saveAll(List<RAM> rams) {
        for (RAM c : rams) {
            em.persist(c);
        }
    }
}
