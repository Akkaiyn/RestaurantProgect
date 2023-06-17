package Company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(generator = "subCategory_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "subCategory_gen",
            sequenceName = "subCategory_seq",
            allocationSize = 1)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.REMOVE
    })
    List<SubCategory> subCategoryList;
}
