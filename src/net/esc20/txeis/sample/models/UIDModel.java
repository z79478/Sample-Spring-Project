package net.esc20.txeis.sample.models;

import net.esc20.txeis.sample.services.UidBatchInfoSearchService;
import net.esc20.txeis.sample.services.UidIdAssignmentService;
import net.esc20.txeis.sample.services.UidNearMatchListService;
import net.esc20.txeis.sample.services.UidNearMatchResolutionService;
import net.esc20.txeis.sample.services.UidSearchStudentService;
import net.esc20.txeis.sample.services.UidXmlService;

public class UIDModel {
	public static final String USERID = "SA.UIDTRNWS.015950.8614";
	public static final String PASSWORD = "&+bFLeBp4ge+hV=9";
	private String id,searchType, ssn, birthdate, district, gender, gradeLvl, localId, raceCode, schoolCode, schoolYear, fName, lName, sis, ethnicity, 
		altId, altSource, residentDistrict, suffix, mName, prevLName, raceCode2, raceCode3, raceCode4, raceCode5, cdfNumbers, cdfValues, batchNum, 
		tranSerialNumber, subType;

	public UidXmlService getXmlService() {
		switch ( searchType ) {
			case "nearMatchList": return new UidNearMatchListService();
			case "searchStudent": return new UidSearchStudentService();
			case "idAssignment": return new UidIdAssignmentService();
			case "nearMatchResolution": return new UidNearMatchResolutionService();
			case "batchInfoSearch": return new UidBatchInfoSearchService();
			default: return null;
		}
	}
	
	public String getUrl() {
		switch ( searchType ) { // these urls will need to be changed for production
			case "nearMatchList": return "https://tea4avdplbprod.tea.state.tx.us/uidtraining/services/NearMatchListV2";
			case "searchStudent": return "https://tea4avdplbprod.tea.state.tx.us/uidtraining/services/StudentSearch";
			case "idAssignment": return "https://tea4avdplbprod.tea.state.tx.us/uidtraining/services/IDAssignmentV2";
			case "nearMatchResolution": return "https://tea4avdplbprod.tea.state.tx.us/uidtraining/services/NearMatchResolutionV2";
			case "batchInfoSearch": return "https://tea4avdplbprod.tea.state.tx.us/uidtraining/services/BatchInfo";
			default: return null;
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGradeLvl() {
		return gradeLvl;
	}

	public void setGradeLvl(String gradeLvl) {
		this.gradeLvl = gradeLvl;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getSis() {
		return sis;
	}

	public void setSis(String sis) {
		this.sis = sis;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getAltId() {
		return altId;
	}

	public void setAltId(String altId) {
		this.altId = altId;
	}

	public String getAltSource() {
		return altSource;
	}

	public void setAltSource(String altSource) {
		this.altSource = altSource;
	}

	public String getResidentDistrict() {
		return residentDistrict;
	}

	public void setResidentDistrict(String residentDistrict) {
		this.residentDistrict = residentDistrict;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getPrevLName() {
		return prevLName;
	}

	public void setPrevLName(String prevLName) {
		this.prevLName = prevLName;
	}

	public String getRaceCode2() {
		return raceCode2;
	}

	public void setRaceCode2(String raceCode2) {
		this.raceCode2 = raceCode2;
	}

	public String getRaceCode3() {
		return raceCode3;
	}

	public void setRaceCode3(String raceCode3) {
		this.raceCode3 = raceCode3;
	}

	public String getRaceCode4() {
		return raceCode4;
	}

	public void setRaceCode4(String raceCode4) {
		this.raceCode4 = raceCode4;
	}

	public String getRaceCode5() {
		return raceCode5;
	}

	public void setRaceCode5(String raceCode5) {
		this.raceCode5 = raceCode5;
	}

	public String getCdfNumbers() {
		return cdfNumbers;
	}

	public void setCdfNumbers(String cdfNumbers) {
		this.cdfNumbers = cdfNumbers;
	}

	public String getCdfValues() {
		return cdfValues;
	}

	public void setCdfValues(String cdfValues) {
		this.cdfValues = cdfValues;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getTranSerialNumber() {
		return tranSerialNumber;
	}

	public void setTranSerialNumber(String tranSerialNumber) {
		this.tranSerialNumber = tranSerialNumber;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
}