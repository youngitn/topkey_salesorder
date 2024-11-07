package com.topkey.salesorder.entity;

import com.topkey.salesorder.entity.pk.F4211Id;

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
@Table(name = "F4211", schema = "${jde-table.schema}")
@IdClass(F4211Id.class)
public class F4211 {

	@Id
	@Column(name = "SDDOCO")
	private Long sdDoco; // 假設 SDDOCO 是主鍵

	@Id
	@Column(name = "SDLNID")
	private Long sdLnid;
	@Id
	@Column(name = "SDKCOO")
	private String sdkCoo;
	@Id
	@Column(name = "SDDCTO")
	private String sdDcto;
	
	@Column(name = "SDLITM")
	private String sdLitm;
	
	@Column(name = "SDDSC1")
	private String sdDsc1;
	
	@Column(name = "SDVR01")
	private String sdVr01;

	@Column(name = "SDFUP")
	private String sdFup;

	@Column(name = "SDAEXP")
	private String sdAexp;

	@Column(name = "SDUORG")
	private String sdUorg;

	@Column(name = "SDECST")
	private String sdEcst;

	@Column(name = "SDSHAN")
	private String sdShan;

	@Column(name = "SDPDDJ")
	private String sdPddj;
	
	@Column(name = "SDPPDJ")
	private String sdPpdj;
	
	@Column(name = "SDRSDJ")
	private String sdRsdj;

	@Column(name = "SDMOT")
	private String sdMot;

	@Column(name = "SDDTYS")
	private String sdDtys;

	@Column(name = "SDLOCN")
	private String sdLocn;

}
