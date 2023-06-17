package Company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table (name = "cheques")
@Getter
@Setter
@NoArgsConstructor
public class Cheque {
    @Id
    @GeneratedValue(generator = "cheque_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cheque_gen",
    sequenceName = "cheque_seq",
    allocationSize = 1)
    private  Long id;
    private int priceAverage;
    private ZonedDateTime createdAt;
    @ManyToMany(mappedBy = "chequeList",cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH})
    private List<MenuItem> menuItemList;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,})
    private User user;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,})
    private Restaurant restaurant;



}
