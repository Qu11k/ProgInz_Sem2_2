package lv.venta.service;

import java.util.ArrayList;

public interface ICRUDBaseService<Ttype> {
//create,retrieve all, by id un delete by id
	public abstract void create(Ttype newObject)
	throws Exception;
	public abstract ArrayList<Ttype>retrieveAll()
	throws Exception;
	public abstract Ttype retrieveById(long id)
	throws Exception;
	public abstract void deleteByID(long id)
	throws Exception;
}
