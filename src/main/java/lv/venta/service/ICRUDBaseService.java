package lv.venta.service;

import java.util.ArrayList;

public interface ICRUDBaseService<Ttype> {
//retrieve all, by id un delete by id
	public abstract ArrayList<Ttype>retrieveAll()
	throws Exception;
	public abstract Ttype retrieveById(long id)
	throws Exception;
	public abstract void deleteByID(long id)
	throws Exception;
}
