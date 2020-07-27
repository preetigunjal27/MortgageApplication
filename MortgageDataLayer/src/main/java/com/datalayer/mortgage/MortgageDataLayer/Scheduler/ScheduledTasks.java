package com.datalayer.mortgage.MortgageDataLayer.Scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.datalayer.mortgage.MortgageDataLayer.dao.MortgageDAO;
import com.mortgage.MortgageDataLayer.Model.Mortgage;

@Component
public class ScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 86400000)
	private static void expireMortage() throws Exception {
		log.info("Started scheduler:", dateFormat.format(new Date()));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		currentDate = formatter.parse(formatter.format(currentDate));
		
		MortgageDAO objDao = MortgageDAO.getMortgageDataStorage();
		Mortgage[] data = objDao.getMortgageArray();
		boolean updateMortgages = false;
		for (int i = 0; i < data.length - 1; i++) {
			if(data[i]!=null) {
				String offerDateStr = data[i].getOfferDate();
				Date offerDate = formatter.parse(offerDateStr);
				if (data[i]!=null && offerDate.compareTo(currentDate) < 0 && data[i].getExpired().equalsIgnoreCase("N")) {
					data[i].setExpired("Y");
					updateMortgages = true;
				}
			}
		}
		if(updateMortgages) {
			objDao.updateAllMortgage(data);
			log.info("Updated mortgages for expiry flag.");
		}			
	}	
}
