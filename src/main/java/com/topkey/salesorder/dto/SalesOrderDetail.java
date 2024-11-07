package com.topkey.salesorder.dto;


import com.topkey.salesorder.entity.F4211;
import com.topkey.salesorder.entity.F554211F;
import com.topkey.salesorder.util.JdeDateConverterUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalesOrderDetail {
//    private F4211 f4211;
//    private F554211F f554211f;
	private String fnSequ; // 新增 fnUorg 欄位

	private String sdDoco;

	private String sdLnid;

	private String sdVr01;

	private String sdFup;

	private String sdAexp;

	private String sdUorg;

	private String sdEcst;

	private String sdShan;

	private String sdPddj;
	
	private String sdPpdj;
	
	private String sdRsdj;

	private String sdMot;

	private String sdDtys;

	private String sdLocn;

	private Object sdLitm;

	private String sdDsc1;

	public SalesOrderDetail(F4211 f4211, F554211F f554211f) {
		super();
		this.fnSequ = f554211f.getFnSequ().toString();
		this.sdDoco = f4211.getSdDoco().toString();
		this.sdLitm = f4211.getSdLitm();
		this.sdDsc1 = f4211.getSdDsc1();
		this.sdLnid = f4211.getSdLnid().toString();
		this.sdVr01 = f4211.getSdVr01();
		this.sdFup = formatDecimal(f4211.getSdFup(), 4);
		this.sdAexp = formatDecimal(f4211.getSdAexp(), 2);
		this.sdUorg = formatDecimal(f4211.getSdUorg(), 4);
		this.sdEcst = formatDecimal(f4211.getSdEcst(), 2);
		this.sdShan = f4211.getSdShan();
		this.sdPddj = JdeDateConverterUtil.convertFromJdeDate(f4211.getSdPddj());
		this.sdPpdj = JdeDateConverterUtil.convertFromJdeDate(f4211.getSdPpdj());
		this.sdRsdj = JdeDateConverterUtil.convertFromJdeDate(f4211.getSdRsdj());
		this.sdMot = f4211.getSdMot();
		this.sdDtys = f4211.getSdDtys();
		this.sdLocn = f4211.getSdLocn();
	}

	public static String formatDecimal(String str, int loc) {
		if (str == null || str.length() <= loc) {
			return str;
		}
		return str.substring(0, str.length() - loc) + "." + str.substring(str.length() - loc);
	}
	// 建構子、Getter 和 Setter
}
