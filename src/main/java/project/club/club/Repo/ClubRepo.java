package project.club.club.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.club.club.entities.Club;

import java.util.Optional;

@Repository
public interface ClubRepo extends JpaRepository<Club, Long> {

    Optional<Club> findByNom (String nom);
}
