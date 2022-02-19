package project.club.club.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.club.club.Repo.ClubRepo;
import project.club.club.entities.Club;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepo clubRepo ;

    @Autowired
    public ClubService(ClubRepo clubRepo) {
        this.clubRepo = clubRepo;
    }
    public List<Club> getClubs(){

        return clubRepo.findAll();
    }

    public Club findClubByNom(String nom){
        return clubRepo.findByNom(nom).orElseThrow(()
                -> new IllegalStateException("club by name " + nom +"was not found" ));
    }

    public void addNewClub(Club club) {
        Optional<Club> clubOptional = clubRepo.findByNom(club.getNom());
        if (clubOptional.isPresent()){
            throw new IllegalStateException("name taken");
        }
        clubRepo.save(club);
    }

    public void deleteClub(Long clubId) {
        boolean exists = clubRepo.existsById(clubId);
        if (!exists) {
            throw new IllegalStateException(
                    "club with id "+clubId+" deos not exist");
        }
        clubRepo.deleteById(clubId);

    }

    @Transactional
    public void updateClub(Long clubId,
                              String nom,
                              String nomPresident) {
        Club club = clubRepo.findById(clubId).orElseThrow(() -> new IllegalStateException(
                "club with id "+ clubId + "does not exist"));

        if (nomPresident != null && nomPresident.length()>0 &&
                !Objects.equals(club.getNomPresident(), nomPresident)) {
            club.setNomPresident(nomPresident);
        }

        if (nom != null && nom.length()>0 &&
                !Objects.equals(club.getNom(), nom)) {
            Optional<Club> clubOptional = clubRepo.findByNom(nom);
            if (clubOptional.isPresent()){
                throw new IllegalStateException("name taken");
            }
            club.setNom(nom);
        }

    }
}
