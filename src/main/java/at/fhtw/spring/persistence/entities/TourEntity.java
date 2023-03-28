package at.fhtw.spring.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
    private String name;
    @Column(name = "t_description")
    private String description;
    @Column(name = "t_start")
    private String from;
    @Column(name = "t_end")
    private String to;
    @Column(name = "transport_type")
    private String type;
    private float distance;
    private Timestamp time;
    @Column(name = "img_path")
    private String imgPath;
}