package com.shs.server.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.shs.commons.model.Alert;
import com.shs.commons.model.User;

public class AlertRequestManager {
	private Connection connDB;
	private JsonReader reader;
	private JsonWriter writer;
	private String  request;
	private Alert alert;
	AlertManager alertManager;
	
	public AlertRequestManager(Connection connDB, JsonReader reader, JsonWriter writer, Alert alert, String request) {
		super();
		this.connDB = connDB;
		this.reader = reader;
		this.writer = writer;
		this.request = request;
		this.alert = alert;
	}
	
	
	
	public String requestManager() throws SQLException, IOException, ParseException {		
		boolean response = false;
		String message=null, error="no row(s)";
		String[] res=request.split("-");
		switch (request) {
			case "insert-alert":
				try{
					response=AlertManager.create(alert);
				}
			    catch(SQLException e) {
			    	error="Error insertion "+e;
			    }
				break;
			case "update-alert":
				try{
					response=AlertManager.update(alert);
				}
			    catch(SQLException e) {
			    	error="Error updating "+e;
			    }
				break;
			case "delete-alert":
				try{
					response=AlertManager.delete(alert);
				}
			    catch(SQLException e) {
			    	error="Error delete "+e;
			    }
				break;
			case "deleteAll-alert":
				try{
					response=AlertManager.deleteAll();
				}
			    catch(SQLException e) {
			    	error="Error delete all "+e;
			    }				
				break;	
			case "select-alert":
					Alert sendAlert=null;
					try{
						sendAlert=AlertManager.getAlert(alert.getId());
					}
				    catch(SQLException e) {
				    	error="Error delete "+e;
				    } catch (ParseException e) {
						error+=" and Date Parse error "+e;
					}
					break;
			case "selectAll-alert":
				try{
					List<Alert> alerts = AlertManager.getAlerts();
					writer.beginObject();
					if(!alerts.isEmpty()) {
						response=true;
						Gson gson = new Gson();
						for (Alert alert : alerts) {
							writer.name("alert").value(gson.toJson(alert));
						}
					}else {
						writer.name("null").value("null");	
					}
					writer.endObject();
				}catch(SQLException e) {
		        	error="Error select all "+e;
		        }catch (ParseException e) {
					error+=" and Date Parse error "+e;
				}	
					
				break;
			default:
				break;
			}
			
		if(response)
			message=request+"-succusful";
		else
			message=request+"-failed: "+error;
		
		//Creation response Json
		if(!res[0].equals("select") && !res[0].equals("selectAll")) {
			writer.beginObject();
			writer.name("response").value(message);
			writer.endObject();	
		}
		writer.flush();
		
		return message;
	}





}
