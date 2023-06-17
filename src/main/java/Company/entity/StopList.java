package Company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "stopLists")
@Getter
@Setter
@NoArgsConstructor
public class StopList {
    @Id
    @GeneratedValue(generator = "stopList_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "stopList_gen",
            sequenceName = "stopList_seq",
            allocationSize = 1)
    private Long id;
    private String reason;
    private ZonedDateTime date;
    @OneToOne(mappedBy = "stopList",cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH})
    private MenuItem menuItem;


}
