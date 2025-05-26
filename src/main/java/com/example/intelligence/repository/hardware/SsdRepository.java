package com.example.intelligence.repository.hardware;

import com.example.intelligence.domain.hardware.SSD;
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
public class SsdRepository {

    @PersistenceContext
    private EntityManager em;

    public List<SSD> getAll() {
        return em.createQuery("select u from SSD u", SSD.class)
                .getResultList();
    }

    public Optional<SSD> findById(Long id) {
        if (id == null) {
            throw new FindNullException("SSD 조회 시 null을 입력할 수 없습니다.");
        }
        return Optional.ofNullable(em.find(SSD.class, id));
    }

    public Optional<SSD> findByName(String name) {
        if (name == null) {
            throw new FindNullException("SSD 조회 시 null을 입력할 수 없습니다.");
        }
        return em.createQuery("select u from SSD u where u.name = :name", SSD.class)
                .setParameter("name", name)
                .getResultStream().findAny();
    }

    @Transactional
    public void saveAll(List<SSD> ssds) {
        for (SSD c : ssds) {
            em.persist(c);
        }
    }
}
