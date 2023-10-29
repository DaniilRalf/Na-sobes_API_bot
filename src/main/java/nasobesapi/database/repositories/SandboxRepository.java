package nasobesapi.database.repositories;

import nasobesapi.database.models.SandboxModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SandboxRepository extends JpaRepository<SandboxModel, Integer> {

    @Query("select count(u) from SandboxModel u where u.devSpecification = ?1 and u.grade = ?2")
    Long getCount(String devSpecification, String grade);

    @Query("select u from SandboxModel u where u.number = ?1 and u.devSpecification = ?2 and u.grade = ?3")
    Optional<SandboxModel> findByNumber(Integer number, String devSpecification, String grade);

}
