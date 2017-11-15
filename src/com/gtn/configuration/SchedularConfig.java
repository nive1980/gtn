package com.gtn.configuration;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.gtn.dto.AlertDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.Alert;
import com.gtn.model.UserBilling;
import com.gtn.service.AlertService;
import com.gtn.service.BillingService;

@Configuration
@EnableScheduling
@PropertySource("/resources/application.properties")
//@PropertySource("file:${CATALINA_HOME}/conf/catalina.properties")

public class SchedularConfig {
	//miliseconds
	@Autowired
	private BillingService billingService;
	@Autowired
	private AlertService alertService;
	
	 	@Scheduled(cron = "${cron.alerts}")
	    public void addAlertsForExpiredBillingDetails() throws ApplicationException {
		 Collection<UserBilling> userBillingDetails = billingService.getExpiredBillingDetails();
		 if(userBillingDetails != null && !userBillingDetails.isEmpty()){
		 for(UserBilling userBilling : userBillingDetails){
				Alert alert = new Alert();
				alert.setDescription("Card No : "+userBilling.getCardNo()+ " is expired. Please add a valid card information.");
				alert.setEffStartDate(new Date());
				alert.setEffToDate(DateUtils.addDays(new Date(), 1));
				alert.setStatus("A");
				alert.setUserId(Long.parseLong(userBilling.getUserId()));
				alertService.saveAlert(alert);
			}
		 }
	        
	    }
	 

}
