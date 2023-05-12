package at.fhtw.spring.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tour")
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "t_id")
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Column(unique = true)
    private String name;
    @Column(name = "t_description")
    private String description;
    @Column(name = "t_start")
    private String from;
    @Column(name = "t_end")
    private String to;
    @Column(name = "transport_type")
    private String type;
    private Float distance;
    private Integer time;   //seconds
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TourLogEntity> logs;
}