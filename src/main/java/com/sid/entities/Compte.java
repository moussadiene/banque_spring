package com.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte",discriminatorType = DiscriminatorType.STRING,length = 2)
public abstract class Compte implements Serializable{
	@Id
	private String codeCompte;
	private Date dateOuverture;
	private double solde;
	@ManyToOne
	@JoinColumn(name = "code_client")
	private Client client;
	
	@OneToMany(mappedBy = "compte")
	private Collection<Operation> operations;

	public Compte(String codeCompte, Date dateOuverture, double solde, Client client) {
		super();
		this.codeCompte = codeCompte;
		this.dateOuverture = dateOuverture;
		this.solde = solde;
		this.client = client;
	}

	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCodeCompte() {
		return codeCompte;
	}

	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}

	public Date getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	
	
}
