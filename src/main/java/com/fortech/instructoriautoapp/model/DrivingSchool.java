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
//@Table(name = "driving_schools",
//        uniqueConstraints=@UniqueConstraint(columnNames={"school_name", "school_address"}))
public class DrivingSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "school_name")
    private String drivingSchoolName;
    @Column(name = "school_address")
    private String drivingSchoolAddress;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "drivingSchool")
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Instructor> instructors = new ArrayList<>();


}
