package com.colson.common.bean;


import com.colson.common.pkg.BaseDTO;

/**
 * @author songbowen
 * @ClassName:
 * @Description:
 * @date 2018年08月04日 11:29
 */
public class ElementDataBean extends BaseDTO {

	/**
	 * 要素代码
	 */
	private String elementCode;

	/**
	 * 要素代码数据
	 */
	private String elementData;

	/**
	 * 获取要素代码
	 *
	 * @return f_element_code - 要素代码
	 */
	public String getElementCode() {
		return elementCode;
	}

	/**
	 * 设置要素代码
	 *
	 * @param elementCode 要素代码
	 */
	public void setElementCode(String elementCode) {
		this.elementCode = elementCode == null ? null : elementCode.trim();
	}

	/**
	 * 获取要素代码数据
	 *
	 * @return f_element_data - 要素代码数据
	 */
	public String getElementData() {
		return elementData;
	}

	/**
	 * 设置要素代码数据
	 *
	 * @param elementData 要素代码数据
	 */
	public void setElementData(String elementData) {
		this.elementData = elementData == null ? null : elementData.trim();
	}

}
