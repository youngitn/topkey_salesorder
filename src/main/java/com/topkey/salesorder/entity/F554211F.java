package com.topkey.salesorder.entity;

import com.topkey.salesorder.entity.pk.F554211FId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "F554211F", schema = "${jde-table.schema}")
@IdClass(F554211FId.class)
public class F554211F {

	@Id
	@Column(name = "FNUKID")
	private Long fnUkid;
	
	@Id
    @Column(name = "FNSEQU") 
    private Long fnSequ;

	@Column(name = "FNDOCO")
	private Long fnDoco;

	@Column(name = "FNLNID")
	private Long fnLnid;

	@Column(name = "FNKCOO")
	private String fnKcoo;

	@Column(name = "FNDCTO")
	private String fnDcto;

	// Getters and setters
	// ...
}
