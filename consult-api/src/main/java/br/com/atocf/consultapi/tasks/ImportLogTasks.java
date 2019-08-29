package br.com.atocf.consultapi.tasks;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.atocf.consultapi.model.FileLog;
import br.com.atocf.consultapi.model.UploadFileLog;
import br.com.atocf.consultapi.repository.FileLogRepository;
import br.com.atocf.consultapi.repository.UploadFileLogRepository;
import br.com.atocf.consultapi.util.DateUtil;

@Component
@EnableBatchProcessing
public class ImportLogTasks {

	@Autowired
	private FileLogRepository fileLogRepository;

	@Autowired
	private UploadFileLogRepository uploadFileLogRepository;

	private static final Logger log = LoggerFactory.getLogger(ImportLogTasks.class);

	@Scheduled(cron = "${scheduler.cron}")
	public void importLogTasksWithCronExpression() {
		log.info("The time is now {}", new Date());
		try {
			List<UploadFileLog> uploadFileLogList = uploadFileLogRepository.findByStatusAndFileName(false, "access.log");
			if (uploadFileLogList.size() > 0) {
				uploadFileLogList.forEach(uploadFileLog -> {
					try {
						readInsertUpdate(uploadFileLog);
					} catch (IOException e) {
						log.info("Erro ao ler o arquivo", uploadFileLog.getId());
					}
				});
			} else {
				log.info("Nenhum arquivo encontrato", new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readInsertUpdate(UploadFileLog uploadFileLog) throws IOException {

		int qtdSuccess = 0;
		int qtdError = 0;

		InputStream is = new ByteArrayInputStream(uploadFileLog.getData());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		while (br.ready()) {
			String[] linhas = br.readLine().split("\\|");
			if (linhas.length == 5) {
				try {
					FileLog accessLog = new FileLog(DateUtil.parseStringtoDate(linhas[0], DateUtil.DATE_TIME_SEC),
							linhas[1], linhas[2].replace("\"", ""), Integer.parseInt(linhas[3]),
							linhas[4].replace("\"", ""));
					qtdSuccess++;
					fileLogRepository.save(accessLog);
				} catch (Exception e) {
					System.out.println(linhas.toString() + " erro na importação da linha!");
					qtdError++;
				}
			} else {
				qtdError++;
			}
		}

		uploadFileLog.setQtd_success(qtdSuccess);
		uploadFileLog.setQtd_error(qtdError);
		uploadFileLog.setStatus(true);
		uploadFileLog.setDt_import(Date.from(Instant.now()));

		uploadFileLogRepository.save(uploadFileLog);

	}

}
