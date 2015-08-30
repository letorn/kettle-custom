package org.pentaho.di.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pentaho.di.core.Const;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.core.logging.LoggingObjectType;
import org.pentaho.di.core.logging.SimpleLoggingObject;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobAdapter;
import org.pentaho.di.job.JobConfiguration;
import org.pentaho.di.job.JobExecutionConfiguration;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.RepositoryDirectory;
import org.pentaho.di.repository.RepositoryDirectoryInterface;

public class HBServlet extends BaseHttpServlet implements CartePluginInterface {

	private static final long serialVersionUID = 1192413943669836775L;

	private static Class<?> PKG = RunJobServlet.class; // i18n

	public static final String CONTEXT_PATH = "/kettle/hb";

	public HBServlet() {
	}

	public HBServlet(JobMap jobMap) {
		super(jobMap);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------------------------------------------------");
		if (isJettyMode() && !request.getContextPath().startsWith(CONTEXT_PATH)) {
			return;
		}

		if (log.isDebug()) {
			logDebug(BaseMessages.getString(PKG, "RunJobServlet.Log.RunJobRequested"));
		}

		// Options taken from PAN
		//
		String jobOption = "hb-enterprise-entpost";
		String levelOption = "debug";

		response.setStatus(HttpServletResponse.SC_OK);

		PrintWriter out = response.getWriter();

		try {

			SlaveServerConfig serverConfig = transformationMap.getSlaveServerConfig();
			Repository slaveServerRepository = serverConfig.getRepository();
			if (slaveServerRepository == null) {
				throw new KettleException("Unable to connect to repository in Slave Server Config: " + serverConfig.getRepositoryId());
			}
			final JobMeta jobMeta = loadJob(slaveServerRepository, jobOption);

			// Set the request body as variables in the job
			//
			jobMeta.setParameterValue("orgains", "07957472-7");

			JobExecutionConfiguration jobExecutionConfiguration = new JobExecutionConfiguration();
			LogLevel logLevel = LogLevel.getLogLevelForCode(levelOption);
			jobExecutionConfiguration.setLogLevel(logLevel);

			// Create new repository connection for this job
			//
			final Repository repository = jobExecutionConfiguration.connectRepository(serverConfig.getRepositoryId(), serverConfig.getRepositoryUsername(), serverConfig.getRepositoryPassword());

			JobConfiguration jobConfiguration = new JobConfiguration(jobMeta, jobExecutionConfiguration);

			String carteObjectId = UUID.randomUUID().toString();
			SimpleLoggingObject servletLoggingObject = new SimpleLoggingObject(CONTEXT_PATH, LoggingObjectType.CARTE, null);
			servletLoggingObject.setContainerObjectId(carteObjectId);
			servletLoggingObject.setLogLevel(logLevel);

			// Create the transformation and store in the list...
			//
			final Job job = new Job(repository, jobMeta, servletLoggingObject);

			// Setting variables
			//
			job.initializeVariablesFrom(null);
			job.getJobMeta().setInternalKettleVariables(job);
			job.injectVariables(jobConfiguration.getJobExecutionConfiguration().getVariables());

			// Also copy the parameters over...
			//
			job.copyParametersFrom(jobMeta);
			job.clearParameters();
			jobMeta.activateParameters();

			job.setSocketRepository(getSocketRepository());

			JobMap jobMap = getJobMap();
			synchronized (jobMap) {
				jobMap.addJob(job.getJobname(), carteObjectId, job, jobConfiguration);
			}

			// Disconnect from the job's repository when the job finishes.
			//
			job.addJobListener(new JobAdapter() {
				public void jobFinished(Job job) {
					repository.disconnect();
				}
			});

			String message = "Job '" + job.getJobname() + "' was added to the list with id " + carteObjectId;
			logBasic(message);

			try {
				job.start();
				job.waitUntilFinished();
				// response.sendRedirect("http://www.baidu.com");
				WebResult webResult = new WebResult(WebResult.STRING_OK, "Job started", carteObjectId);
				out.println(webResult.getXML());
				out.flush();
			} catch (Exception executionException) {
				String logging = KettleLogStore.getAppender().getBuffer(job.getLogChannelId(), false).toString();
				throw new KettleException("Error executing Job: " + logging, executionException);
			}
		} catch (Exception ex) {
			out.println(new WebResult(WebResult.STRING_ERROR, BaseMessages.getString(PKG, "RunJobServlet.Error.UnexpectedError", Const.CR + Const.getStackTracker(ex))));
		}
	}

	private JobMeta loadJob(Repository repository, String job) throws KettleException {
		if (repository == null) {
			throw new KettleException("Repository required.");
		} else {
			synchronized (repository) {
				String directoryPath;
				String name;
				int lastSlash = job.lastIndexOf(RepositoryDirectory.DIRECTORY_SEPARATOR);
				if (lastSlash < 0) {
					directoryPath = "/";
					name = job;
				} else {
					directoryPath = job.substring(0, lastSlash);
					name = job.substring(lastSlash + 1);
				}
				RepositoryDirectoryInterface directory = repository.loadRepositoryDirectoryTree().findDirectory(directoryPath);
				ObjectId jobID = repository.getJobId(name, directory);
				JobMeta transJob = repository.loadJob(jobID, null);
				return transJob;
			}
		}
	}

	public String toString() {
		return "HB Service";
	}

	public String getService() {
		return CONTEXT_PATH + " (" + toString() + ")";
	}

	@Override
	public String getContextPath() {
		return CONTEXT_PATH;
	}

}