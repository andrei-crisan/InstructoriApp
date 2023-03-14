package com.fortech.instructoriautoapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "instructori")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeInstructor;
    private String prenumeInstructor;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "instructor")
    @JsonManagedReference
    private List<Evaluare> evaluariPrimite;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="instructori_scoli",
            joinColumns = @JoinColumn(name="instructor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="scoala_id", referencedColumnName = "id"))
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Scoala> listaScoliSoferi = new ArrayList<>();

    public void adaugaApartenentaScoala(Scoala scoala) {
        listaScoliSoferi.add(scoala);
    }

    //Todo: REMOVE ONE ITEM FROM LIST, METHOD, IN FIECARE ENTITATE.
    ///Todo: METODA PENTRU CONCATENARE LISTA VECHE CU LISTA NOUA

    //rescriere metoda pentru comparatii obiecte la adaugare.
    public boolean equals(Instructor instructor) {
        return this.numeInstructor.equals(instructor.numeInstructor) &&
                this.prenumeInstructor.equals(instructor.prenumeInstructor);
    }
}
