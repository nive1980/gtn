package com.gtn.service;

import java.util.Collection;

import com.gtn.dto.JobSchedularDto;
import com.gtn.exception.ApplicationException;
import com.gtn.model.JobSchedular;

public interface JobSchedularService {
	public JobSchedular getCron(String jobCode) throws ApplicationException;
	public Collection<JobSchedular> searchScheduledJobs(JobSchedularDto job) throws ApplicationException;
}
