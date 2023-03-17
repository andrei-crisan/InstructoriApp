package com.fortech.instructoriautoapp.model;

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
@Table(name = "driving_schools")
public class DrivingSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "school_name")
    private String drivingSchoolName;
    @Column(name = "school_address")
    private String drivingSchoolAddress;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "drivingSchools")
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Instructor> instructors = new ArrayList<>();
}
