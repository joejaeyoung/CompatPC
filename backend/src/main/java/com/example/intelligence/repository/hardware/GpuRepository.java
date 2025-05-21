package com.example.intelligence.repository.hardware;

import com.example.intelligence.domain.hardware.GPU;
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
public class GpuRepository {

    @PersistenceContext
    private EntityManager em;

    public List<GPU> getAll() {
        return em.createQuery("select u from GPU u", GPU.class)
                .getResultList();
    }

    public Optional<GPU> findById(Long id) {
        if (id == null) {
            throw new FindNullException("Case 조회 시 null을 입력할 수 없습니다.");
        }
        return Optional.ofNullable(em.find(GPU.class, id));
    }

    public Optional<GPU> findByName(String name) {
        if (name == null) {
            throw new FindNullException("Case 조회 시 null을 입력할 수 없습니다.");
        }
        return em.createQuery("select u from GPU u where u.name = :name", GPU.class)
                .setParameter("name", name)
                .getResultStream().findAny();
    }

    @Transactional
    public void saveAll(List<GPU> ssds) {
        for (GPU c : ssds) {
            em.persist(c);
        }
    }
}
