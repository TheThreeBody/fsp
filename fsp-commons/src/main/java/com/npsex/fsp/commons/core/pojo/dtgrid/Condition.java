package com.npsex.fsp.commons.core.pojo.dtgrid;

/**
 * 
 * @ClassName: Condition
 * @Description: dtgrid表格条件查询对象，拷贝自大连首闻科技有限公司的DLShouWen Grid
 * @author DongWen
 * @date 2016年7月12日 下午3:07:47
 *
 */
public class Condition {
	
	/**
	 * 左括号
	 */
	private String leftParentheses;
	
	/**
	 * 字段名称
	 */
	private String field;
	
	/**
	 * 条件
	 */
	private String condition;
	
	/**
	 * 值
	 */
	private String value;
	
	/**
	 * 右括号
	 */
	private String rightParentheses;
	
	/**
	 * 查询逻辑
	 */
	private String logic;

	public String getLeftParentheses() {
		return leftParentheses;
	}

	public void setLeftParentheses(String leftParentheses) {
		this.leftParentheses = leftParentheses;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRightParentheses() {
		return rightParentheses;
	}

	public void setRightParentheses(String rightParentheses) {
		this.rightParentheses = rightParentheses;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}
	
}
