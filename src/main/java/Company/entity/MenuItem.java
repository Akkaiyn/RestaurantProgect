package Company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "menuItems")
@Getter
@Setter
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(generator = "menuItem_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "menuItem_gen",
            sequenceName = "menuItem_seq",
            allocationSize = 1)
    private Long id;
    private String name;
    private String image;
    private int price;
    private String description;
    private boolean isVegetarian;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,})
    private SubCategory subCategory;
    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.REMOVE})
    private StopList stopList;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,})
    private Restaurant restaurant;

    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,})
    private List<Cheque> chequeList;


}
