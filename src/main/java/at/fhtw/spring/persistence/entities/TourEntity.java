package at.fhtw.spring.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "img_path")
    private String imgPath;
}