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
@Table(name="scoli")
public class Scoala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numeScoala;
    private String adresaScoala;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},mappedBy = "listaScoliSoferi")
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Instructor> listaInstructori = new ArrayList<>();
}
