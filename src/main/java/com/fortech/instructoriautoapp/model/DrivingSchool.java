package com.fortech.instructoriautoapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
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
@Builder
@Table(name = "driving_schools",
        uniqueConstraints=@UniqueConstraint(columnNames={"school_name", "school_address"}))
public class DrivingSchool extends BaseEntity<Long> {
    @Column(name = "school_name")
    @NotNull
    private String drivingSchoolName;
    @Column(name = "school_address")
    @NotNull
    private String drivingSchoolAddress;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "drivingSchool")
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Instructor> instructors = new ArrayList<>();
}
