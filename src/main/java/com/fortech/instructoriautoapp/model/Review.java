package com.fortech.instructoriautoapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="reviews")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Review extends BaseEntity<Long>{
    @Column(name="instructor_review")
    private String instructorReview;
    @Column(name="experience_rating")
    private Integer experienceRating;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private Instructor instructor;

    //todo: de adaugat un rating - neutru, negativ, pozitiv (valori binare 1 0 2)
    //todo: apoi in service de adaugat o statistica. un rating la afisare instructori.
    //todo: de mapat coloane!!
    //Todo: de verificat ce facem cu lazy-urile!!

}
