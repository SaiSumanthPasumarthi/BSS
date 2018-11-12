package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * 
 * @author srinivasa
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class RequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private long requestId;
	private String customerId;
	private BigDecimal amount;
	private String gatewayType;
	private String emailId;
	private String phone;
	private int status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date createdOn;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date modifiedOn;
	private long createdBy;
	private String statusUpdatedBy;
	private String paymentType;
	private String deliveryId;
	private String custtypelov;
	
	public String getCusttypelov() {
		return custtypelov;
	}

	public void setCusttypelov(String custtypelov) {
		this.custtypelov = custtypelov;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the deliveryId
	 */
	public String getDeliveryId() {
		return deliveryId;
	}

	/**
	 * @param deliveryId
	 */
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	/**
	 * @return the requestId
	 */
	public long getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId
	 *            the requestId to set
	 */
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the gatewayType
	 */
	public String getGatewayType() {
		return gatewayType;
	}

	/**
	 * @param gatewayType
	 *            the gatewayType to set
	 */
	public void setGatewayType(String gatewayType) {
		this.gatewayType = gatewayType;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn
	 *            the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the modifiedOn
	 */
	public Date getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn
	 *            the modifiedOn to set
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	/**
	 * @return the createdBy
	 */
	public long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the statusUpdatedBy
	 */
	public String getStatusUpdatedBy() {
		return statusUpdatedBy;
	}

	/**
	 * @param statusUpdatedBy
	 *            the statusUpdatedBy to set
	 */
	public void setStatusUpdatedBy(String statusUpdatedBy) {
		this.statusUpdatedBy = statusUpdatedBy;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((gatewayType == null) ? 0 : gatewayType.hashCode());
		result = prime * result
				+ ((modifiedOn == null) ? 0 : modifiedOn.hashCode());
		result = prime * result + (int) (requestId ^ (requestId >>> 32));
		result = prime * result + status;
		result = prime * result
				+ ((statusUpdatedBy == null) ? 0 : statusUpdatedBy.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestDTO other = (RequestDTO) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (gatewayType == null) {
			if (other.gatewayType != null)
				return false;
		} else if (!gatewayType.equals(other.gatewayType))
			return false;
		if (modifiedOn == null) {
			if (other.modifiedOn != null)
				return false;
		} else if (!modifiedOn.equals(other.modifiedOn))
			return false;
		if (requestId != other.requestId)
			return false;
		if (status != other.status)
			return false;
		if (statusUpdatedBy == null) {
			if (other.statusUpdatedBy != null)
				return false;
		} else if (!statusUpdatedBy.equals(other.statusUpdatedBy))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentRequest [requestId=" + requestId + ", customerId="
				+ customerId + ", amount=" + amount + ", gatewayType="
				+ gatewayType + ", status=" + status + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn
				+ ", statusUpdatedBy=" + statusUpdatedBy + "]";
	}

}