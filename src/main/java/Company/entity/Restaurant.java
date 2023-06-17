package Company.entity;

import Company.enums.RestType;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_gen")
    @SequenceGenerator(name = "restaurant_gen",
    sequenceName = "restaurant_seq",
    allocationSize = 1)
    private  Long id;
    private String  name;
    private String location;
    private RestType restType;
    private int numberOfEmployees;
    private double service;

    @OneToMany(mappedBy = "restaurant",cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.REMOVE})
    private List<MenuItem>  menuItemList;
    @OneToMany(mappedBy = "restaurant",cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.REMOVE})
    private List<User> userList;




}
