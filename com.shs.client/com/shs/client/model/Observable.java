package com.shs.client.model;

interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver(String str);
}
