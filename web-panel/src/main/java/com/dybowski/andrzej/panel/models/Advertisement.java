package com.dybowski.andrzej.panel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "advertisement")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class Advertisement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisement_id")
    private Long id;

    @Column(name = "advertisement_name")
    private String name;

    @Column(name = "advertisement_data_type")
    private String dataType;

    @Column(name = "advertisement_source")
    private String source;

    @Column(name = "advertisement_time")
    private Long time;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDataType() {
        return dataType;
    }

    public String getSource() {
        return source;
    }

    public Long getTime() {
        return time;
    }
}
