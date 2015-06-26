package jstockenterprisefx.base.jpa.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessConstraintViolation extends Exception {

	private List<String> businessContraintsViolated = new ArrayList<>();

	public BusinessConstraintViolation(final String businessConstraint,
			final String message) {
		super(message);
		addBusinessContraintsViolated(businessConstraint);
	}

	public BusinessConstraintViolation(final List<String> businessConstraints,
			final String message) {
		super(message);
		setBusinessContraintsViolated(businessConstraints);
	}

	public BusinessConstraintViolation(final String message,
			final Throwable cause) {
		super(message, cause);
	}

	public BusinessConstraintViolation(final String message) {
		super(message);
	}

	public List<String> getBusinessContraintsViolated() {
		return businessContraintsViolated;
	}

	public void setBusinessContraintsViolated(
			final List<String> businessContraintsViolated) {
		this.businessContraintsViolated = businessContraintsViolated;
	}

	public void addBusinessContraintsViolated(final String businessConstraint) {
		businessContraintsViolated.add(businessConstraint);
	}

}
