package com.vkncode.secretariat.domain.repository;

import com.vkncode.secretariat.domain.entity.Destination;
import com.vkncode.secretariat.domain.entity.Secretariat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecretariatRepository extends JpaRepository<Secretariat, Long> {
    Optional<Secretariat> findByDestination(Destination destination);
}
