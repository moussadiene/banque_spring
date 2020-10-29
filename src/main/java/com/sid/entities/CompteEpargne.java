package com.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double renumeration;

	public CompteEpargne(String codeCompte, Date dateOuverture, double solde, Client client, double renumeration) {
		super(codeCompte, dateOuverture, solde, client);
		this.renumeration = renumeration;
	}

	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}


	public double getRenumeration() {
		return renumeration;
	}

	public void setRenumeration(double renumeration) {
		this.renumeration = renumeration;
	}
	
}
