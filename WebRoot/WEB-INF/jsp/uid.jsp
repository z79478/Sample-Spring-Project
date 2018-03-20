<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
	<script type="text/javascript" src="/SampleSpring/scripts/uid.js"></script>
	<script type="text/javascript" src="/SampleSpring/scripts/uid_support.js"></script>
	<script type="text/javascript" src="/SampleSpring/scripts/vkbeautify.0.00.00.beta.js"></script>
	<head><title>UID Test</title></head>
	<body style="padding:30px">
		<p>
			<div style="margin-top:10px; text-align: left;"><a href="/SampleSpring">Home Page</a></div>
		</p>

		<p><a href="javascript:void(0)" onclick="getNearMatchList();">Near Match List</a>&nbsp;
		<a href="javascript:void(0)" onclick="getStudentSearch()">Student Search</a>&nbsp;
		<a href="javascript:void(0)" onclick="idAssign();">ID Assignment</a>&nbsp;
		<a href="javascript:void(0)" onclick="nearMatchResolution()">Near Match Resolution</a>&nbsp;
		<a href="javascript:void(0)" onclick="batchInfo()">Batch Info Search</a></p>
		<p style="color:red">* denotes required fields</p>
		<form method="post" id="nearMatch">
			<spring:bind path="model.searchType">
				<input type="hidden" name="searchType" value="nearMatchList" />
			</spring:bind>
			<label for="id">*ID:</label>
			<spring:bind path="model.id">
				<input type="text" name="id" value="${status.value}" />
			</spring:bind><br />
			<input type="submit" value="Submit" />
			<input type="button" value="Call StudentSearch.searchStudent" onclick="CallUIDWebService($(&quot;#hdnRequestDataHold&quot;).val(), 5, &quot;searchStudent&quot;)">
		</form>
		<form method="post" id="StudentSearch" style="display:none">
			<spring:bind path="model.searchType">
				<input type="hidden" id="searchType" name="searchType" value="searchStudent" />
			</spring:bind>
			<label for="ssn">*SSN:</label>
			<spring:bind path="model.ssn">
				<input type="text" name="ssn" value="${status.value}" />
			</spring:bind><br />
			<label for="birthdate">*Birthdate:</label>
			<spring:bind path="model.birthdate">
				<input type="text" name="birthdate" value="${status.value}" />
			</spring:bind><br />
			<label for="district">*District Code:</label>
			<spring:bind path="model.district">
				<input type="text" name="district" value="${status.value}" />
			</spring:bind><br />
			<label for="gender">*Gender:</label>
			<spring:bind path="model.gender">
				<input type="text" name="gender" value="${status.value}" />
			</spring:bind><br />
			<label for="gradeLvl">*Grade Level:</label>
			<spring:bind path="model.gradeLvl">
				<input type="text" name="gradeLvl" value="${status.value}" />
			</spring:bind><br />
			<label for="localId">*Local Student Id:</label>
			<spring:bind path="model.localId">
				<input type="text" name="localId" value="${status.value}" />
			</spring:bind><br />
			<label for="raceCode">*Race:</label>
			<spring:bind path="model.raceCode">
				<input type="text" name="raceCode" value="${status.value}" />
			</spring:bind><br />
			<label for="schoolCode">*SchoolCode:</label>
			<spring:bind path="model.schoolCode">
				<input type="text" name="schoolCode" value="${status.value}" />
			</spring:bind><br />
			<label for="schoolYear">*School Year:</label>
			<spring:bind path="model.schoolYear">
				<input type="text" name="schoolYear" value="${status.value}" />
			</spring:bind><br />
			<label for="fName">*First Name:</label>
			<spring:bind path="model.fName">
				<input type="text" name="fName" value="${status.value}" />
			</spring:bind><br />
			<label for="mName">Middle Name:</label>
			<spring:bind path="model.mName">
				<input type="text" name="mName" value="${status.value}" />
			</spring:bind><br />
			<label for="lName">*Last Name:</label>
			<spring:bind path="model.lName">
				<input type="text" name="lName" value="${status.value}" />
			</spring:bind><br />
			<label for="suffix">Suffix:</label>
			<spring:bind path="model.suffix">
				<input type="text" name="suffix" value="${status.value}" />
			</spring:bind><br />
			<label for="prevLName">Previous Last Name:</label>
			<spring:bind path="model.prevLName">
				<input type="text" name="prevLName" value="${status.value}" />
			</spring:bind><br />
			<label for="sis">*SIS Code:</label>
			<spring:bind path="model.sis">
				<input type="text" name="sis" value="${status.value}" />
			</spring:bind><br />
			<label for="ethnicity">*Ethnicity:</label>
			<spring:bind path="model.ethnicity">
				<input type="text" name="ethnicity" value="${status.value}" />
			</spring:bind><br />
			<label for="altId">Alternate Id:</label>
			<spring:bind path="model.altId">
				<input type="text" name="altId" value="${status.value}" />
			</spring:bind><br />
			<label for="altSource">Alternate Source:</label>
			<spring:bind path="model.altSource">
				<input type="text" name="altSource" value="${status.value}" />
			</spring:bind><br />
			<label for="residentDistrict">Resident District Code:</label>
			<spring:bind path="model.residentDistrict">
				<input type="text" name="residentDistrict" value="${status.value}" />
			</spring:bind><br />
			<label for="id">State Student ID:</label>
			<spring:bind path="model.id">
				<input type="text" name="id" value="${status.value}" />
			</spring:bind><br />
			<label for="raceCode2">Second Race:</label>
			<spring:bind path="model.raceCode2">
				<input type="text" name="raceCode2" value="${status.value}" />
			</spring:bind><br />
			<label for="raceCode3">Third Race:</label>
			<spring:bind path="model.raceCode3">
				<input type="text" name="raceCode3" value="${status.value}" />
			</spring:bind><br />
			<label for="raceCode4">Fourth Race:</label>
			<spring:bind path="model.raceCode4">
				<input type="text" name="raceCode4" value="${status.value}" />
			</spring:bind><br />
			<label for="raceCode5">Fifth Race:</label>
			<spring:bind path="model.raceCode5">
				<input type="text" name="raceCode5" value="${status.value}" />
			</spring:bind><br />
			<label for="cdfNumbers">CDF Numbers:</label>
			<spring:bind path="model.cdfNumbers">
				<input type="text" name="cdfNumbers" value="${status.value}" />
			</spring:bind><br />
			<label for="cdfValues">CDF Values:</label>
			<spring:bind path="model.cdfValues">
				<input type="text" name="cdfValues" value="${status.value}" />
			</spring:bind><br />
			<input type="submit" value="Submit" />
		</form>
		<form method="post" id="nearMatchRes" style="display:none">
			<spring:bind path="model.searchType">
				<input type="hidden" name="searchType" value="nearMatchResolution" />
			</spring:bind>
			<spring:bind path="model.subType">
				<input type="hidden" id="subType" name="subType" value="cancel" />
			</spring:bind>
			<a href="javascript:void(0)" onclick="resCancel()">Cancel</a>&nbsp;
			<a href="javascript:void(0)" onclick="resAssign()">Assign ID</a>&nbsp;
			<a href="javascript:void(0)" onclick="resCreate()">Create New ID</a><br />
			<label for="tranSerialNumber">*TranSerial Number:</label>
			<spring:bind path="model.tranSerialNumber">
				<input type="text" name="tranSerialNumber" value="${status.value}" />
			</spring:bind><br />
			<div id="stateId" style="display:none">
			<label for="id">*State Student ID:</label>
			<spring:bind path="model.id">
				<input type="text" name="id" value="${status.value}" />
			</spring:bind><br />
			</div>
			<input type="submit" value="Submit" />
		</form>
		<form method="post" id="batchInfoSearch" style="display:none">
			<spring:bind path="model.searchType">
				<input type="hidden" name="searchType" value="batchInfoSearch" />
			</spring:bind>
			<label for="batchNum">*Batch Number:</label>
			<spring:bind path="model.batchNum">
				<input type="text" name="batchNum" value="${status.value}" />
			</spring:bind><br />
			<input type="submit" value="Submit" />
		</form>
		
		<script type="text/javascript">
			function getNearMatchList() {
				$("#nearMatch").css("display", "");
				$("#StudentSearch").css("display", "none");
				$("#batchInfoSearch").css("display", "none");
				$("#nearMatchRes").css("display", "none");
			}
			
			function getStudentSearch() {
				$("#StudentSearch").css("display", "");
				$("#batchInfoSearch").css("display", "none");
				$("#nearMatch").css("display", "none");
				$("#nearMatchRes").css("display", "none");
				$("#searchType").val("searchStudent");
			}
			
			function idAssign() {
				$("#StudentSearch").css("display", "");
				$("#batchInfoSearch").css("display", "none");
				$("#nearMatch").css("display", "none");
				$("#nearMatchRes").css("display", "none");
				$("#searchType").val("idAssignment");
			}
			
			function batchInfo() {
				$("#batchInfoSearch").css("display", "");
				$("#nearMatch").css("display", "none");
				$("#StudentSearch").css("display", "none");
				$("#nearMatchRes").css("display", "none");
			}
			
			function nearMatchResolution() {
				$("#nearMatchRes").css("display", "");
				$("#batchInfoSearch").css("display", "none");
				$("#nearMatch").css("display", "none");
				$("#StudentSearch").css("display", "none");
			}
			
			function resCancel() {
				$("#stateId").css("display", "none");
				$("#subType").val("cancel");
			}
			
			function resAssign() {
				$("#stateId").css("display", "");
				$("#subType").val("assignId");
			}

			function resCreate() {
				$("#stateId").css("display", "");
				$("#subType").val("createNewId");
			}
		</script>
	</body>
</html>