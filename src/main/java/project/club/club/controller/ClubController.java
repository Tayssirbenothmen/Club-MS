package project.club.club.controller;

import org.springframework.web.bind.annotation.*;
import project.club.club.entities.Club;
import project.club.club.services.ClubService;

import java.util.List;

@RestController
public class ClubController {

private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }
    @GetMapping
    public List<Club> getStudent(){
        return clubService.getClubs();
    }
    @GetMapping("{nom}")
    public Club findByNom(@PathVariable("nom") String nom){
        return clubService.findClubByNom(nom);

    }

    @PostMapping
    public void registerNewClub(@RequestBody Club club){
        clubService.addNewClub(club);
    }

    @DeleteMapping(path ="{clubId}")
    public void deleteClub(@PathVariable("clubId") Long clubId){
        clubService.deleteClub(clubId);
    }

    @PutMapping(path ="{clubId}")
    public void updateClub(@PathVariable("clubId") Long clubId,
                           @RequestParam(required = false) String nom,
                           @RequestParam(required = false) String nomPresident) {
        clubService.updateClub(clubId,nom,nomPresident);
    }
}
