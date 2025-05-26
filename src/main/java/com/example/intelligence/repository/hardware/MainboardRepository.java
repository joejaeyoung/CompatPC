package com.example.intelligence.repository.hardware;

import com.example.intelligence.domain.hardware.Mainboard;
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
public class MainboardRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Mainboard> getAll() {
        return em.createQuery("select u from Mainboard u", Mainboard.class)
                .getResultList();
    }

    public Optional<Mainboard> findById(Long id) {
        if (id == null) {
            throw new FindNullException("MainBoard 조회 시 null을 입력할 수 없습니다.");
        }
        return Optional.ofNullable(em.find(Mainboard.class, id));
    }

    public Optional<Mainboard> findByName(String name) {
        if (name == null) {
            throw new FindNullException("MainBoard 조회 시 null을 입력할 수 없습니다.");
        }
        return em.createQuery("select u from Mainboard u where u.name = :name", Mainboard.class)
                .setParameter("name", name)
                .getResultStream().findAny();
    }

    @Transactional
    public void saveAll(List<Mainboard> ssds) {
        for (Mainboard c : ssds) {
            em.persist(c);
        }
    }
}
