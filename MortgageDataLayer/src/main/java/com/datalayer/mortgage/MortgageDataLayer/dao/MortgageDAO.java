package com.datalayer.mortgage.MortgageDataLayer.dao;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mortgage.MortgageDataLayer.Model.Mortgage;

public class MortgageDAO {
	private static MortgageDAO instance = null;
	private int size;
	// holds the total capacity of array
	private int capacity;

	private Mortgage dataArray[];   // dynamic Array .. Initial size 2, get doubled.
	
	public static synchronized MortgageDAO getMortgageDataStorage() {

		if (instance == null) {
			instance = new MortgageDAO();
			instance.dataArray = new Mortgage[2];
			instance.size = 0;
			instance.capacity = 2;
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			Date createDate = cal.getTime(); //smallest created date.
									
			cal.add(Calendar.MONTH, 7);
			Date offerdate = cal.getTime();
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Mortgage demoObj1 = new Mortgage("M98",1,"B1","Ol-1",formatter.format(createDate),formatter.format(offerdate),"N");
			instance.addMortgage(demoObj1);		
			
			Mortgage demoObj2 = new Mortgage("M99",1,"B1","Ol-1",formatter.format(createDate),formatter.format(offerdate),"N");
			instance.addMortgage(demoObj2);		
		}
		return instance;
	}

	public Mortgage addMortgage(Mortgage mortgage) {

		if (size == capacity) {
			ensureCapacity(2);
		}
		int indexOfSameVersion = checkIfMortgageExist(mortgage);   //override existing
		if (indexOfSameVersion >= 0) {
			dataArray[indexOfSameVersion] = mortgage;   //index > =0 on old index override.  if returns -1, create new
			return dataArray[indexOfSameVersion];
		}
		dataArray[size] = mortgage;
		size++;
		return dataArray[size-1];
	}

	public int getSize() {
		return size;
	}

	public Mortgage[] getAllMortgage() {
		if(size == 0) return dataArray;
		Mortgage[] temp = new Mortgage[size];
		for (int i = 0; i < size; i++) {
			if(dataArray[i]!=null)
			    temp[i] = dataArray[i];
		}
		return temp;
	}

	public Mortgage[] getMortgageArray() {
		return dataArray;
	}
	public void updateAllMortgage(Mortgage[] mortgages) {
		dataArray = mortgages;
	}

	public int checkIfMortgageExist(Mortgage mortgage) {
		for (int i = 0; i < dataArray.length; i++) {
			Mortgage storedMortgage = dataArray[i];
			if (storedMortgage != null
					&& computeUniqueStorageIndex(mortgage).equals(computeUniqueStorageIndex(storedMortgage))) {
				return i;
			}
		}
		return -1;
	}

	public String computeUniqueStorageIndex(Mortgage mortgage) {
		return mortgage.getMortgageId() + "$" + String.valueOf(mortgage.getVersion());
	}

	// to add an element at a particular index
	public void addElement(int index, Mortgage element) {
		// double the capacity if all the allocated space is utilized
		if (size == capacity) {
			ensureCapacity(1);
		}
		// shift all elements from the given index to right
		for (int i = size - 1; i >= index; i--) {
			dataArray[i + 1] = dataArray[i];
		}
		// insert the element at the specified index
		dataArray[index] = element;
		size++;
	}

	// to get an element at an index
	public Mortgage getMortgage(int index) {
		return dataArray[index];
	}

	public boolean removeMortgage(Mortgage mortgage) {
		if (mortgage == null) {
			return false;
		}
		for (int i = 0; i < size - 1; i++) {
			if (computeUniqueStorageIndex(mortgage).equals(computeUniqueStorageIndex(dataArray[i]))) {
				removeMortgage(i);
				return true;
			}
		}
		return false;
	}

	// to remove an mortgage at a particular index
	public boolean removeMortgage(int index) {
		if (index >= size || index < 0) {
			System.out.println("No mortgage at this index");
			return false;
		} else {
			for (int i = index; i < size - 1; i++) {
				dataArray[i] = dataArray[i + 1];
			}
			dataArray[size - 1] = null;
			size--;
		}
		return true;
	}

	/*
	 * method to increase the capacity, if necessary, to ensure it can hold at least
	 * the number of elements specified by minimum capacity arguement
	 */
	public void ensureCapacity(int minCapacity) {   // dynamic array size increased and copied array
		Mortgage temp[] = new Mortgage[capacity * minCapacity];
		for (int i = 0; i < capacity; i++) {
			temp[i] = dataArray[i];
		}
		dataArray = temp;
		capacity = capacity * minCapacity;
	}

	/*
	 * Trim the capacity of dynamic array to the current size. i.e. remove unused
	 * space
	 */
	public void trimToSize() {
		System.out.println("Trimming the array");
		Mortgage temp[] = new Mortgage[size];
		for (int i = 0; i < size; i++) {
			temp[i] = dataArray[i];
		}
		dataArray = temp;
		capacity = dataArray.length;
	}

	// to get the current capacity
	public int capacity() {
		return capacity;
	}
	
	public Mortgage[] getMortgageById(String mId) throws RemoteException {
		Mortgage[] data = getAllMortgage();
		Mortgage[] output = new Mortgage[5];
		int i = 0;
		for (Mortgage mortgage : data) {
			if (mortgage != null && mortgage.getMortgageId().equalsIgnoreCase(mId)) {
				output[i] = mortgage;
				i++;
			}
		}
		return output;
	}

	/*
	 * Returns maximum version number available in storage
	 */
	public Mortgage getMaxVersionMortgageById(String mId) throws RemoteException {
		Mortgage[] data = getAllMortgage();
		int maxVersion = 0; // To hold max version number.
		Mortgage ouptut = null;
		for (Mortgage mortgage : data) {
			if (mortgage != null && mortgage.getMortgageId().equalsIgnoreCase(mId)
					&& maxVersion < mortgage.getVersion()) {
				maxVersion = mortgage.getVersion(); // next max found.
				ouptut = mortgage;
			}
		}
		return ouptut;
	}

	/*
	 * Returns Mortgage of matching id and version from storage.
	 */
	public Mortgage getMortgageByIdAndVersion(String mId, int version) throws RemoteException {
		Mortgage[] data = getAllMortgage();

		for (Mortgage mortgage : data) {
			if (mortgage != null && mortgage.getMortgageId().equalsIgnoreCase(mId)
					&& mortgage.getVersion() == version) {
				return mortgage;
			}
		}
		return null;
	}

}	