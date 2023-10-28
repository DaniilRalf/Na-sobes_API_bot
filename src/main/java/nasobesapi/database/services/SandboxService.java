package nasobesapi.database.services;

import nasobesapi.database.models.SandboxModel;
import nasobesapi.database.repositories.SandboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SandboxService {

    private final SandboxRepository sandboxRepository;

    @Autowired
    public SandboxService(SandboxRepository sandboxRepository) {
        this.sandboxRepository = sandboxRepository;
    }

    public Long getCountQuestion(String devSpecification, String grade) {
        return this.sandboxRepository.getCount(devSpecification, grade);
    }

    public Optional<SandboxModel> findByNumberQuestion(Integer number, String devSpecification, String grade) {
        return this.sandboxRepository.findByNumber(number, devSpecification, grade);
    }

}
