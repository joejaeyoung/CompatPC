package com.example.intelligence.repository.hardware;

import com.example.intelligence.domain.hardware.CPU;
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
public class CpuRepository {

    @PersistenceContext
    private EntityManager em;

    public List<CPU> getAll() {
        return em.createQuery("select u from CPU u", CPU.class)
                .getResultList();
    }

    public Optional<CPU> findById(Long id) {
        if (id == null) {
            throw new FindNullException("CPU 조회 시 null을 입력할 수 없습니다.");
        }
        return Optional.ofNullable(em.find(CPU.class, id));
    }

    public Optional<CPU> findByName(String name) {
        if (name == null) {
            throw new FindNullException("CPU 조회 시 null을 입력할 수 없습니다.");
        }
        return em.createQuery("select u from CPU u where u.name = :name", CPU.class)
                .setParameter("name", name)
                .getResultStream().findAny();
    }

    @Transactional
    public void saveAll(List<CPU> cpus) {
        for (CPU c : cpus) {
            em.persist(c);
        }
    }
}
