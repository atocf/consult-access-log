package br.com.atocf.consultapi.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.atocf.consultapi.model.FileLog;

@Repository
public interface FileLogRepository extends JpaRepository<FileLog, Long> {
	
	Page<FileLog> findByIp(String ip, Pageable paging);
	
	@Query(" FROM FileLog f " +
           " WHERE f.dt_access BETWEEN :dt_de AND :dt_ate")
	Page<FileLog> findByDt_access(@Param("dt_de") Date dt_de, @Param("dt_ate") Date dt_ate, Pageable paging) ;
	
	@Query(" FROM FileLog f " +
	       " WHERE f.ip = :ip and f.dt_access BETWEEN :dt_de AND :dt_ate")
		Page<FileLog> findByIpAndDt_access(@Param("ip") String ip, @Param("dt_de") Date dt_de, @Param("dt_ate") Date dt_ate, Pageable paging) ;
	 
}