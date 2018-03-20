package net.esc20.txeis.sample.services;

import net.esc20.txeis.sample.dao.XMLFileDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class XMLFileService {

	@Autowired
	private XMLFileDao fileDao;
	
	public XMLFileDao getFileDao() {
		return fileDao;
	}

	public void setFileDao(XMLFileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Transactional (propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
	public void writeFile() {
		fileDao.writeFile();
	}
	
}
