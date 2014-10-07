package org.wsbn.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.wsbn.persistence.PersistenceManager;
import org.wsbn.dto.iDto;

public class Dao
{

	// STATE
	protected EntityManager mEm = null;

	// CONTRUCTORS
	{
		if(mEm == null || mEm.isOpen() == false)
		{
			mEm = PersistenceManager.createEntityManager();
		}
		
	}
	
	public Dao()
	{
		
	}

	protected Object disableEntity(iDto pDto)
	{

		EntityTransaction oTrans = null;
		pDto = (iDto) this.find(pDto);
		pDto.setDisabled(true);

		try {
			oTrans = mEm.getTransaction();
			oTrans.begin();
			mEm.persist(pDto);
			oTrans.commit();
		}
		catch (Exception e) {
			oTrans.rollback();
		}

		finally {

		}

		return pDto;

	}

	protected Object enabledEntity(iDto pDto)
	{

		EntityTransaction oTrans = null;
		pDto = (iDto) this.find(pDto);
		pDto.setDisabled(false);

		try {
			oTrans = mEm.getTransaction();
			oTrans.begin();
			mEm.persist(pDto);
			oTrans.commit();
		}
		catch (Exception e) {
			oTrans.rollback();
		}

		finally {

		}

		return pDto;

	}

	protected Object find(iDto pDto)
	{
		Object oResponse = null;

		try {

			oResponse = mEm.find(pDto.getClass(), pDto.getRid());

		}
		catch (Exception e) {

		}

		finally {

		}

		return oResponse;

	}

	protected void closeEntityManager()
	{
		if (this.mEm != null && this.mEm.isOpen()) {
			this.mEm.close();
		}

	}

	protected void openEntityManager()
	{
		if (this.mEm == null || this.mEm.isOpen() == false) {
			mEm = PersistenceManager.createEntityManager();
		}
	}

}
