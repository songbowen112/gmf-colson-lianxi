package com.colson.intergration.util;

import com.colson.intergration.bean.ElementDataBean;
import com.colson.intergration.constant.PecElementCode;

import java.util.List;

public class CapitalAdapter {

	private ElementWarpUp elementWarpUp;

	private CapitalAdapter(ElementWarpUp elementWarpUp) {
		this.elementWarpUp = elementWarpUp;
	}

	public static CapitalAdapter of(List<ElementDataBean> dataList){
		return new CapitalAdapter(new ElementWarpUp(dataList));
	}

	public String getCapitalCode(){
		return this.elementWarpUp.getElementData(PecElementCode.CAPITAL_CODE);
	}

	public String getCapitalCode(String defaultValue){
		String data = this.elementWarpUp.getElementDataOptional(PecElementCode.CAPITAL_CODE);
		return data == null ? defaultValue:data;
	}

	public String getCapitalMechanismCode(){
		return this.elementWarpUp.getElementData(PecElementCode.CAPITAL_MECHANISM_CODE);
	}

	public String getCapitalMechanismCode(String defaultValue){
		String data = this.elementWarpUp.getElementDataOptional(PecElementCode.CAPITAL_MECHANISM_CODE);
		return data == null ? defaultValue:data;
	}

	public String getCapitalShortName(){
		return this.elementWarpUp.getElementData(PecElementCode.CAPITAL_SHORT_NAME);
	}

	public String getCapitalShortName(String defaultValue){
		String data = this.elementWarpUp.getElementDataOptional(PecElementCode.CAPITAL_SHORT_NAME);
		return data == null ? defaultValue:data;
	}

	public Integer getCapitalYearDays(){
		String data = this.elementWarpUp.getElementData(PecElementCode.CAPITAL_YEAR_DAYS);
		return Integer.valueOf(data);
	}

	public String getCapitalFullName(){
		return this.elementWarpUp.getElementData(PecElementCode.CAPITAL_FULL_NAME);
	}

	public Integer getCapitalCompensationType(){
		String data = this.elementWarpUp.getElementData(PecElementCode.CAPITAL_COMPENSATION_TYPE);
		return Integer.valueOf(data);
	}

	public Integer getCapitalOverdueCompensationDays(){
		String data = this.elementWarpUp.getElementData(PecElementCode.CAPITAL_OVERDUE_COMPENSATION_DAYS);
		return Integer.valueOf(data);
	}

	public Boolean getCapitalPreSettle(){
		String data = this.elementWarpUp.getElementData(PecElementCode.CAPITAL_PRE_SETTLE);
		return Boolean.valueOf(Integer.valueOf(data) == 1);
	}

	public Integer getCapitalPreSettleType(){
		String data = this.elementWarpUp.getElementData(PecElementCode.CAPITAL_PRE_SETTLE_TYPE);
		return Integer.valueOf(data);
	}

	public Boolean getCapitalHaveEntryInterface(){
		String data = this.elementWarpUp.getElementData(PecElementCode.CAPITAL_HAVE_ENTRY_INTERFACE);
		return Boolean.valueOf(Integer.valueOf(data) == 1);
	}

	public Boolean getCapitalHaveEntryInterface(Boolean defaultValue){
		String data = this.elementWarpUp.getElementDataOptional(PecElementCode.CAPITAL_HAVE_ENTRY_INTERFACE);
		return data == null?defaultValue:Boolean.valueOf(Integer.valueOf(data) == 1);
	}

	public Integer getCapitalEntryInterfaceType(){
		String data = this.elementWarpUp.getElementData(PecElementCode.CAPITAL_ENTRY_INTERFACE_TYPE);
		return Integer.valueOf(data);
	}

	public Integer getCapitalGraceDays(){
		String data = this.elementWarpUp.getElementData(PecElementCode.CAPITAL_GRACE_DAYS);
		return Integer.valueOf(data);
	}


}
