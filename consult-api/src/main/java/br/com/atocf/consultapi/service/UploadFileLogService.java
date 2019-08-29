package br.com.atocf.consultapi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.atocf.consultapi.exception.FileStorageException;
import br.com.atocf.consultapi.exception.ResourceNotFoundException;
import br.com.atocf.consultapi.model.UploadFileLog;
import br.com.atocf.consultapi.repository.UploadAccessLogRepository;

@Service
public class UploadFileLogService {

	@Autowired
	private UploadAccessLogRepository repository;

	public UploadFileLog storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("O nome do arquivo contém uma sequência de caminho inválida " + fileName);
			}

			UploadFileLog uploadAccessLog = new UploadFileLog(fileName, file.getContentType(), file.getSize(), file.getBytes());

			return repository.save(uploadAccessLog);
		} catch (IOException ex) {
			throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
		}
	}

	public UploadFileLog getFile(long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Arquivo não encontrado com o id " + id));
	}
}
