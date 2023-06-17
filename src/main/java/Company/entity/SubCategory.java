package Company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "subCategories")
@Getter
@Setter
@NoArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue(generator = "subCategory_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "subCategory_gen",
            sequenceName = "subCategory_seq",
            allocationSize = 1)
    private Long id;
    private String name;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,})
    private Category category;


    @OneToMany(mappedBy = "subCategory", cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.REMOVE})
    private List<MenuItem> menuItemList;
}
