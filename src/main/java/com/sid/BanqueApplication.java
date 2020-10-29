package com.sid;

import java.util.Date;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sid.dao.ClientRepository;
import com.sid.dao.CompteRepository;
import com.sid.dao.OperationRepository;
import com.sid.entities.Client;
import com.sid.entities.CompteCourant;
import com.sid.entities.CompteEpargne;
import com.sid.entities.Operation;
import com.sid.entities.Retrait;
import com.sid.entities.Versement;
import com.sid.metier.IBanqueMetier;

@SpringBootApplication
public class BanqueApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private IBanqueMetier banqueApplication;
	@Autowired
	private OperationRepository operationRepository;
	
	public static void main(String[] args) {
		
		/* Option 1
		 * 
		 * ApplicationContext ctx = SpringApplication.run(BanqueApplication.class, args);
		 * ClientRepository clientRepository = ctx.getBean(ClientRepository.class);
		 * clientRepository.save(new Client( "Moussa DIENE", "mosila21"));
		 */
		
		/*
		 * Option 2
		 */
		SpringApplication.run(BanqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Client c1 = clientRepository.save(new Client( "Moussa DIENE" , "mosila21@djine.com"));
//		Client c2 = clientRepository.save(new Client( "Fallou FALL" , "s2f@fallou.com"));
//		Client c3 = clientRepository.save(new Client( "Papis NDOYE" , "pisco@papis.com"));
//		
//		CompteCourant compteCourant1 = compteRepository.save(
//				new CompteCourant("c1",new Date(),90000,c1,6000));
//		
//		operationRepository.save(new Versement(new Date(),7000,compteCourant1)); 
//		operationRepository.save(new Retrait(new Date(),1000,compteCourant1));
//		
//		CompteEpargne compteEpargne2 = compteRepository.save(
//				new CompteEpargne("c2", new Date(), 10000, c2, 1000));
//		
//		operationRepository.save(new Versement(new Date(),7000,compteEpargne2)); 
//		operationRepository.save(new Retrait(new Date(),1000,compteEpargne2));
//		
//		banqueApplication.verser("c1", 20000);
//		banqueApplication.verser("c2", 20000);
		 int i = 0;
		    while (i < 2) {
		        String password = "mosila21";
		        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		        String hashedPassword = passwordEncoder.encode(password);

		        System.out.println(hashedPassword);
		        i++;
		    }
	}

}
