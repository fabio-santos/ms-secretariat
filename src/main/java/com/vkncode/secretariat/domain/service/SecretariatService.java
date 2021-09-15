package com.vkncode.secretariat.domain.service;

import com.vkncode.secretariat.domain.dto.SecretariatDTO;
import com.vkncode.secretariat.domain.dto.UnderInvestigationDTO;
import com.vkncode.secretariat.domain.entity.Secretariat;
import com.vkncode.secretariat.domain.repository.SecretariatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecretariatService {

    private SecretariatRepository repository;

    public List<Secretariat> get() {
        return repository.findAll();
    }

    public Optional<Secretariat> findById(Long id) {
        return repository.findById(id);
    }

    public Secretariat save(SecretariatDTO secretariatDTO) {
        Secretariat secretariat = new Secretariat(secretariatDTO);
        validateDestinationIsAvailable(secretariat);
        return repository.save(secretariat);
    }

    private void validateDestinationIsAvailable(Secretariat secretariat) {
        Optional<Secretariat> search = repository.findByDestination(secretariat.getDestination());

        if(search.isPresent() && search.get().getId() != secretariat.getId()) {
            throw new RuntimeException(secretariat.getDestination() + " already taken");
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Secretariat saveUnderInvestigation(UnderInvestigationDTO underInvestigationDTO, Long id) {
        Secretariat secretariat = repository.findById(id).orElseThrow(() -> new RuntimeException(id + " not available"));
        secretariat.setUnderInvestigation(underInvestigationDTO.isUnderInvestigation());
        return repository.save(secretariat);
    }
}
