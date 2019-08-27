package com.colson.intergration.util;

import com.colson.intergration.bean.ElementDataBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementWarpUp {

	private Map<String,ElementDataBean> elementDataMap = new HashMap<String, ElementDataBean>();

	public ElementWarpUp(List<ElementDataBean> elementDataList) {

		if(elementDataList == null || elementDataList.isEmpty()){
			throw new IllegalArgumentException("elementDataList can't be null or empty");
		}

		for(ElementDataBean element : elementDataList){
			elementDataMap.put(element.getElementCode(),element);
		}
	}

	public String getElementData(String elementCode){
		ElementDataBean elementData = elementDataMap.get(elementCode);
		if(elementData == null){
			throw new IllegalArgumentException("elementData not exist");
		}
		return elementData.getElementData();
	}

	public String getElementDataOptional(String elementCode){
		ElementDataBean elementData = elementDataMap.get(elementCode);
		return elementData == null ? null : elementData.getElementData();
	}


}
