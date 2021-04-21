package com.caid.utopia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.AircraftType;


public class UtopiaAdminAircraftTests extends UtopiaAdminApplicationTests {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	/* Controller Tests */
	@Test
	@Transactional
	void ChangeAirplaneStatus() throws Exception {
		AircraftType aircraftType = new AircraftType();
		String uri = "/admin/admin/AircraftType";
		aircraftType.setAircraftType(-2);
		aircraftType.setaircraftTypeName("testAircraftType");
		aircraftType.setSeatMaximum(100);
		aircraftType.setManufacturer("testManufacturer");
		String inputJson = super.mapToJson(aircraftType);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		Aircraft aircraft = new Aircraft();
		aircraft.setAircraftType(aircraftType);
		aircraft.setSeatCount(100);
		aircraft.setFirstClassCount(20);
		aircraft.setSecondClassCount(20);
		aircraft.setThirdClassCount(20);
		aircraft.setAircraftStatus("Active");
		uri = "/admin/Aircraft";
		inputJson = super.mapToJson(aircraft);
		mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		aircraft = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Aircraft.class);
		/* deactivate */
		uri = "/admin/Aircraft/Deactivate";
		inputJson = super.mapToJson(aircraft);
		mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);
		/* activate */
		uri = "/admin/Aircraft/Activate";
		mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);
	}
	
	@Test
	@Transactional
	void CreateAircraftTest() throws Exception {
		String uri = "/admin/admin/AircraftType";
		AircraftType aircraftType = new AircraftType();
		aircraftType.setAircraftType(-2);
		aircraftType.setaircraftTypeName("testAircraftType");
		aircraftType.setSeatMaximum(100);
		aircraftType.setManufacturer("testManufacturer");
		String inputJson = super.mapToJson(aircraftType);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		Aircraft aircraft = new Aircraft();
		aircraft.setAircraftType(aircraftType);
		aircraft.setSeatCount(100);
		aircraft.setFirstClassCount(20);
		aircraft.setSecondClassCount(20);
		aircraft.setThirdClassCount(20);
		aircraft.setAircraftStatus("Active");
		uri = "/admin/Aircraft";
		inputJson = super.mapToJson(aircraft);
		mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	@Test
	@Transactional
	void CreateAircraftTypeTest() throws Exception {
		String uri = "/admin/admin/AircraftType";
		AircraftType aircraftType = new AircraftType();
		aircraftType.setAircraftType(-2);
		aircraftType.setaircraftTypeName("testAircraftType");
		aircraftType.setSeatMaximum(100);
		aircraftType.setManufacturer("testManufacturer");
		String inputJson = super.mapToJson(aircraftType);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	void ReadAircraftTest() throws Exception {
		String uri = "/admin/Aircraft";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Aircraft[] aircraft = super.mapFromJson(content, Aircraft[].class);
		assertTrue(aircraft.length >= 0);
	}
	
	@Test
	void ReadAircraftTypeTest() throws Exception {
		String uri = "/admin/admin/AircraftType";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		AircraftType[] aircraftType = super.mapFromJson(content, AircraftType[].class);
		assertTrue(aircraftType.length >= 0);
	}
	
	/* Update Planes */
	@Test
	@Transactional
	void UpdateAircraftTest() throws Exception {
		String uri = "/admin/Aircraft";
		Aircraft aircraft = new Aircraft();
		aircraft.setAircraftId(-1);
		aircraft.setSeatCount(5000);
		String inputJson = super.mapToJson(aircraft);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);
	}
	
	/* Update Planes Type */
	@Test
	@Transactional
	void UpdateAircraftTypeTest() throws Exception {
		String uri = "/admin/admin/AircraftType";
		AircraftType aircraftType = new AircraftType();
		aircraftType.setAircraftType(-1);
		aircraftType.setSeatMaximum(1000);
		String inputJson = super.mapToJson(aircraftType);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);
	}
	
	/* Delete Planes */
	@Test
	@Transactional
	void DeleteAircraftTest() throws Exception {
		String uri = "/admin/Aircraft";
		Aircraft aircraft = new Aircraft();
		aircraft.setAircraftId(-1);
		String inputJson = super.mapToJson(aircraft);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);
	}
	
	@Test
	@Transactional
	void DeleteAircraftTypeTest() throws Exception {
		String uri = "/admin/admin/AircraftType";
		AircraftType aircraftType = new AircraftType();
		aircraftType.setAircraftType(-1);
		String inputJson = super.mapToJson(aircraftType);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);
	}
}
