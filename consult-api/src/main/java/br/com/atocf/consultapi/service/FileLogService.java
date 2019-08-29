package br.com.atocf.consultapi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.atocf.consultapi.model.FileLog;
import br.com.atocf.consultapi.repository.FileLogRepository;

@Service
public class FileLogService {
	
	@Autowired
	private FileLogRepository repository;
	
	public Page<FileLog> findAll(int page, int size, String ip, Date dt_de, Date dt_ate) {
		Pageable paging = PageRequest.of(page, size, Sort.by("ip").ascending());
		if(ip != null && dt_de != null && dt_ate != null) {
			return repository.findByIpAndDt_access(ip, dt_de, dt_ate, paging);
		} else if(ip != null) {
			return repository.findByIp(ip, paging);
		} else if(dt_de != null && dt_ate != null) {
			return repository.findByDt_access(dt_de, dt_ate, paging);
		}
		return repository.findAll(paging);
	}
}
