package com.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte{


		private double agios;

		public CompteCourant() {
			super();
			// TODO Auto-generated constructor stub
		}

		public CompteCourant(String codeCompte, Date dateOuverture, double solde, Client client, double agios) {
			super(codeCompte, dateOuverture, solde, client);
			this.agios = agios;
		}

		public double getAgios() {
			return agios;
		}

		public void setAgios(double agios) {
			this.agios = agios;
		}

		
}
