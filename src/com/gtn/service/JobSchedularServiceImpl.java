package com.gtn.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtn.dao.GenericDao;
import com.gtn.dto.JobSchedularDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.JobSchedular;

@Service
public class JobSchedularServiceImpl implements JobSchedularService{

	@Autowired
	private GenericDao dao;
	
	@Override
	public JobSchedular getCron(String jobCode) throws ApplicationException {
		JobSchedular job = (JobSchedular) dao.read(JobSchedular.class, jobCode);
		return job;
	}
	
	@Override
	public Collection<JobSchedular> searchScheduledJobs(JobSchedularDto job) throws ApplicationException{
		
		try{
			StringBuilder queryString = new StringBuilder("select Object(c) from JobSchedular c where ");
			
			if(!isEmpty(job.getJobCode())){
				queryString.append("upper(c.jobCode) like '%").append(job.getJobCode().toUpperCase());
				queryString.append("%' and ");
			}
			if(!isEmpty(job.getJobName())){
				queryString.append("upper(c.jobName) like '%").append(job.getJobName().toUpperCase());
				queryString.append("%' and ");
			}
			if(!isEmpty(job.getJobDesc())){
				queryString.append("upper(c.jobDesc) like '%").append(job.getJobDesc().toUpperCase());
				queryString.append("%' and ");
			}
			
			queryString.append(" c.jobCode is not null");
			
			System.out.println("queryString --------------- " + queryString.toString());
			
			Object[] params = {};
			Collection<JobSchedular> result = null;
			result = dao.findDynamicQuery(queryString.toString(), params);
			
			return result;
		}catch(Exception e){
			throw new ApplicationException("Error searching jobs - JobSchedularServiceImpl", e);
		}
	}

	private boolean isEmpty(String s){
		if(s!=null && !s.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
}
