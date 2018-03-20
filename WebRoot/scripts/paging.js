function page(newPage) {
	var TableData = new Array();
	
	$("#tabledata tr").each(function(row, tr){
		var checked = $(tr).find('td:eq(0) :input').attr('checked') == 'checked' ? 'true' : 'false';
		TableData[row]={"selected": checked, 
				"emp_nbr" : $(tr).find('td:eq(1) :input').val(), 
				"name_f" : $(tr).find('td:eq(2) :input').val(),
				"name_m" : $(tr).find('td:eq(3) :input').val(), 
				"name_l" : $(tr).find('td:eq(4) :input').val()};
	});
	
	TableData.shift();  //Remove the header
	TableData.pop();    //Remove the footer
	
	var stringData = JSON.stringify(TableData); //Convert to JSON Format
	
	//Ajax call to load into div
	$("#tablediv").load("paging/getPage #tablediv", {"pageNum": newPage, "tableData": stringData}, function(response, status, xhr){
		if (status == "error") {
			alert(xhr.status + " " + xhr.statusText);
		}		
	});
	
};