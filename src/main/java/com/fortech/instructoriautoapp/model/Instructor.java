package com.fortech.instructoriautoapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "instructors")
public class Instructor extends BaseEntity<Long> {
    @Column(name = "instructor_name")
    private String instructorName;
    @Column(name = "instructor_surname")
    private String instructorSurname;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, mappedBy = "instructor")
    @JsonManagedReference
    private List<Review> reviews;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "instructors_driving_schools",
            joinColumns = @JoinColumn(name = "instructor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "driving_school_id", referencedColumnName = "id"))
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private DrivingSchool drivingSchool;
}
