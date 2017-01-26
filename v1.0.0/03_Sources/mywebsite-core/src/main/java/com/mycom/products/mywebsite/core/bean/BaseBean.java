package com.mycom.products.mywebsite.core.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseBean implements Serializable {
	private static final long serialVersionUID = 5987804145999725843L;
	public static final String LOGBREAKER = "---------------------------------------";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

	public enum TransactionType {
		INSERT, UPDATE, DELETE;
	}

	private int id;

	@JsonIgnore
	private int recordRegId;
	@JsonIgnore
	private int recordUpdId;
	@JsonIgnore
	private Timestamp recordRegDate;
	@JsonIgnore
	private Timestamp recordUpdDate;
	@JsonIgnore
	private TransactionType transactionType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRecordRegId() {
		return recordRegId;
	}

	public void setRecordRegId(int recordRegId) {
		this.recordRegId = recordRegId;
	}

	public Timestamp getRecordRegDate() {
		return recordRegDate;
	}

	public void setRecordRegDate(Timestamp recordRegDate) {
		this.recordRegDate = recordRegDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public int getRecordUpdId() {
		return recordUpdId;
	}

	public void setRecordUpdId(int recordUpdId) {
		this.recordUpdId = recordUpdId;
	}

	public Timestamp getRecordUpdDate() {
		return recordUpdDate;
	}

	public void setRecordUpdDate(Timestamp recordUpdDate) {
		this.recordUpdDate = recordUpdDate;
	}

	protected String convertDateAsString(Date date) {
		String dateString = "";
		if (date != null) {
			dateString = dateFormatter.format(date);
		}
		return dateString;
	}

	protected Date convertStringAsDate(String dateString) {
		Date date = null;
		if (dateString != null && dateString.length() > 0) {
			try {
				date = dateFormatter.parse(dateString);
			} catch (ParseException e) {
				// Nothing todo
			}
		}
		return date;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Class<?> thisClass = null;
		try {
			thisClass = Class.forName(this.getClass().getName());
			Field[] memberFields = thisClass.getDeclaredFields();
			sb.append(this.getClass().getSimpleName() + " [ ");
			for (Field field : memberFields) {
				field.setAccessible(true);
				sb.append(field.getName() + " = " + field.get(this) + ", ");
			}
			sb.append("]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
