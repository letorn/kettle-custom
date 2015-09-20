package test;

import org.junit.Test;

import util.Carte;
import util.Carte.JobStatus;

public class CarteTest {

	@Test
	public void testInitSync() {
		JobStatus jobStatus = Carte.initSync();
		System.out.println("statusCode: " + jobStatus.getStatusCode());
		System.out.println("finished: " + jobStatus.isFinished());
		System.out.println("hasError: " + jobStatus.hasError());
		System.out.println("error: " + jobStatus.getError());
	}

	@Test
	public void testSyncEnterpriseAndEntpostFromHBToWHDH() {
		String enterpriseId = "0B8DE458DF0110B0E050800A8C0A34B3";
		JobStatus jobStatus = Carte.syncEnterpriseAndEntpostFromHBToWHDH(enterpriseId);
		System.out.println("statusCode: " + jobStatus.getStatusCode());
		System.out.println("finished: " + jobStatus.isFinished());
		System.out.println("hasError: " + jobStatus.hasError());
		System.out.println("error: " + jobStatus.getError());
	}

}
