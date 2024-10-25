package com.cibertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_profesion")
public class Profesion {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Cambiar de Long a Integer

    @Column(nullable = false)
    private String des_profesion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDes_profesion() {
		return des_profesion;
	}

	public void setDes_profesion(String des_profesion) {
		this.des_profesion = des_profesion;
	}
    
   
    
}

