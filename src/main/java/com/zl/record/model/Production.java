package com.zl.record.model;

public class Production {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 规格型号
	 */
	private String specification;
	/**
	 * 生产编号
	 */
	private String productNumber;
	/**
	 * 生产者
	 */
	private String producer;
	/**
	 * 生产日期
	 */
	private String productionDate;
	/**
	 * 检验者
	 */
	private String inspector;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 销售人员
	 */
	private String salesman;
	/**
	 * 销售时间
	 */
	private String saleDate;
	/**
	 * 购买者
	 */
	private String purchaser;
	/**
	 * 装机地址
	 */
	private String salePosition;
	/**
	 * 查询生产日期终点
	 */
	private String endProductionDate;
	/**
	 * 查询销售时间终点
	 */
	private String endSaleDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getSalePosition() {
		return salePosition;
	}

	public void setSalePosition(String salePosition) {
		this.salePosition = salePosition;
	}

	public String getEndProductionDate() {
		return endProductionDate;
	}

	public void setEndProductionDate(String endProductionDate) {
		this.endProductionDate = endProductionDate;
	}

	public String getEndSaleDate() {
		return endSaleDate;
	}

	public void setEndSaleDate(String endSaleDate) {
		this.endSaleDate = endSaleDate;
	}
}
