package com.atguigu.entity;


public class HouseBroker extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	//房源id
	private Long houseId;
	//经纪人id
	private Long brokerId;
	//经纪人名称
	private String brokerName;
	//经纪人头像
	private String brokerHeadUrl;

	public HouseBroker() {
	}

	public HouseBroker(Long houseId, Long brokerId, String brokerName, String brokerHeadUrl) {
		this.houseId = houseId;
		this.brokerId = brokerId;
		this.brokerName = brokerName;
		this.brokerHeadUrl = brokerHeadUrl;
	}

	@Override
	public String toString() {
		return "HouseBroker{" +
				"houseId=" + houseId +
				", brokerId=" + brokerId +
				", brokerName='" + brokerName + '\'' +
				", brokerHeadUrl='" + brokerHeadUrl + '\'' +
				'}';
	}

	public void setHouseId(Long value) {
		this.houseId = value;
	}
	
	public Long getHouseId() {
		return this.houseId;
	}
	
	public void setBrokerId(Long value) {
		this.brokerId = value;
	}
	
	public Long getBrokerId() {
		return this.brokerId;
	}
	
	public void setBrokerName(String value) {
		this.brokerName = value;
	}
	
	public String getBrokerName() {
		return this.brokerName;
	}
	
	public void setBrokerHeadUrl(String value) {
		this.brokerHeadUrl = value;
	}
	
	public String getBrokerHeadUrl() {
		return this.brokerHeadUrl;
	}

}

