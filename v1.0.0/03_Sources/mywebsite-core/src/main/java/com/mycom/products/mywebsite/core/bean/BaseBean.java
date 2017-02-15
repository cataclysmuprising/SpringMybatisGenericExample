/*
 * @author Mg Than Htike Aung {@literal <rage.cataclysm@gmail.com@address>}
 * @Since 1.0
 * 
 */
package com.mycom.products.mywebsite.core.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseBean implements Serializable {
	private static final long serialVersionUID = 5987804145999725843L;
	public static final String LOGBREAKER = "--------------------------------------------------";

	public enum TransactionType {
		INSERT, UPDATE, DELETE;
	}

	private long id;

	@JsonIgnore
	private long recordRegId;
	@JsonIgnore
	private long recordUpdId;
	@JsonIgnore
	private LocalDateTime recordRegDate;
	@JsonIgnore
	private LocalDateTime recordUpdDate;
	@JsonIgnore
	private TransactionType transactionType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRecordRegId() {
		return recordRegId;
	}

	public void setRecordRegId(long recordRegId) {
		this.recordRegId = recordRegId;
	}

	public LocalDateTime getRecordRegDate() {
		return recordRegDate;
	}

	public void setRecordRegDate(LocalDateTime recordRegDate) {
		this.recordRegDate = recordRegDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public long getRecordUpdId() {
		return recordUpdId;
	}

	public void setRecordUpdId(long recordUpdId) {
		this.recordUpdId = recordUpdId;
	}

	public LocalDateTime getRecordUpdDate() {
		return recordUpdDate;
	}

	public void setRecordUpdDate(LocalDateTime recordUpdDate) {
		this.recordUpdDate = recordUpdDate;
	}

	protected String convertDateAsString(LocalDate date) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dateString = "";
		if (date != null) {
			dateString = dateFormatter.format(date);
		}
		return dateString;
	}

	protected LocalDate convertStringAsDate(String dateString) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = null;
		if (dateString != null && dateString.length() > 0) {
			date = LocalDate.parse(dateString, dateFormatter);
		}
		return date;
	}

	@Override
	public String toString() {
		return String.format("*** id=%s, recordRegId=%s, recordUpdId=%s, recordRegDate=%s, recordUpdDate=%s ***", id, recordRegId, recordUpdId, recordRegDate, recordUpdDate);
	}

}
