package project.club.club.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Club {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(nullable = false ,updatable = false)
    private long id;
    private String nom;
    private String nomPresident;
}
