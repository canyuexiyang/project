package com.guhui.order.api.vo.base;

import org.sagacity.sqltoy.config.annotation.Column;
import org.sagacity.sqltoy.config.annotation.Entity;
import org.sagacity.sqltoy.config.annotation.Id;

import java.io.Serializable;


/**
 * @project spring-boot-common-service
 * @version 1.0.0
 * Table: gd_store   
 */
@Entity(tableName="gd_store",pk_constraint="PRIMARY")
public abstract class AbstractGdStoreVO implements Serializable,
	Cloneable {
	 /*--------------- properties string,handier to copy ---------------------*/
	 //full properties 
	 //gdId,isDelete,productClassId,remark,storeNum
	 
	 //not null properties
	 //gdId

	/**
	 * 
	 */
	private static final long serialVersionUID = 6754342366862750298L;
	
	@Id(strategy="identity")
	@Column(name="gd_id",length=10L,type=java.sql.Types.INTEGER,nullable=false,autoIncrement=true)
	protected Integer gdId;
	
	@Column(name="is_delete",length=10L,type=java.sql.Types.INTEGER,nullable=true)
	protected Integer isDelete;
	
	@Column(name="product_class_id",length=10L,type=java.sql.Types.INTEGER,nullable=true)
	protected Integer productClassId;
	
	@Column(name="remark",length=255L,type=java.sql.Types.VARCHAR,nullable=true)
	protected String remark;
	
	@Column(name="store_num",length=10L,type=java.sql.Types.INTEGER,nullable=true)
	protected Integer storeNum;
	


	/** default constructor */
	public AbstractGdStoreVO() {
	}
	
	/** pk constructor */
	public AbstractGdStoreVO(Integer gdId)
	{
		this.gdId=gdId;
	}


	/** full constructor */
	public AbstractGdStoreVO(Integer gdId,Integer isDelete,Integer productClassId,String remark,Integer storeNum)
	{
		this.gdId=gdId;
		this.isDelete=isDelete;
		this.productClassId=productClassId;
		this.remark=remark;
		this.storeNum=storeNum;
	}
	
	/**
	 *@param gdId the gdId to set
	 */
	public void setGdId(Integer gdId) {
		this.gdId=gdId;
	}
		
	/**
	 *@return the GdId
	 */
	public Integer getGdId() {
	    return this.gdId;
	}
	
	/**
	 *@param isDelete the isDelete to set
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete=isDelete;
	}
		
	/**
	 *@return the IsDelete
	 */
	public Integer getIsDelete() {
	    return this.isDelete;
	}
	
	/**
	 *@param productClassId the productClassId to set
	 */
	public void setProductClassId(Integer productClassId) {
		this.productClassId=productClassId;
	}
		
	/**
	 *@return the ProductClassId
	 */
	public Integer getProductClassId() {
	    return this.productClassId;
	}
	
	/**
	 *@param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark=remark;
	}
		
	/**
	 *@return the Remark
	 */
	public String getRemark() {
	    return this.remark;
	}
	
	/**
	 *@param storeNum the storeNum to set
	 */
	public void setStoreNum(Integer storeNum) {
		this.storeNum=storeNum;
	}
		
	/**
	 *@return the StoreNum
	 */
	public Integer getStoreNum() {
	    return this.storeNum;
	}



	/**
     * @todo vo columns to String
     */
	public String toString() {
		StringBuilder columnsBuffer=new StringBuilder();
		columnsBuffer.append("gdId=").append(getGdId()).append("\n");
		columnsBuffer.append("isDelete=").append(getIsDelete()).append("\n");
		columnsBuffer.append("productClassId=").append(getProductClassId()).append("\n");
		columnsBuffer.append("remark=").append(getRemark()).append("\n");
		columnsBuffer.append("storeNum=").append(getStoreNum()).append("\n");
		return columnsBuffer.toString();
	}
}
