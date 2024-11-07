package com.topkey.salesorder.entity.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class F4211Id implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String sdkCoo;
	private String sdDcto;
	private Long sdDoco;
	private Long sdLnid;
}
