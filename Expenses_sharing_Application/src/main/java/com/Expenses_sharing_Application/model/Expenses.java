package com.Expenses_sharing_Application.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Expenses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Description is mandatory")
	private String description;

	@NotNull(message = "Amount is mandatory")
	@DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
	private BigDecimal amount;

	@NotBlank(message = "splitMethod is mandatory")
	private String splitMethod; // EQUAL, EXACT, PERCENTAGE

	@ElementCollection
	@MapKeyColumn(name = "user_id")
	@Column(name = "share_amount")
	private Map<Long, BigDecimal> userShares; // User ID to share amount

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSplitMethod() {
		return splitMethod;
	}

	public void setSplitMethod(String splitMethod) {
		this.splitMethod = splitMethod;
	}

	public Map<Long, BigDecimal> getUserShares() {
		return userShares;
	}

	public void setUserShares(Map<Long, BigDecimal> userShares) {
		this.userShares = userShares;
	}

	public Map<Long, BigDecimal> getExactAmounts() {
		if ("EXACT".equals(splitMethod)) {
			return userShares;
		}
		return null;
	}

	public Map<Long, BigDecimal> getPercentages() {
		if ("PERCENTAGE".equals(splitMethod)) {
			return userShares;
		}
		return null;
	}

	public List<Long> getUserIds() {
		return userShares.keySet().stream().toList();
	}

}
