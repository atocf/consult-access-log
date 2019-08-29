package br.com.atocf.consultapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.atocf.consultapi.model.UploadFileLog;

@Repository
public interface UploadFileLogRepository extends JpaRepository<UploadFileLog, Long> {
	
	List<UploadFileLog> findByStatusAndFileName(Boolean status, String fileName);	
}
