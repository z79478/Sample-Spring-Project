package net.esc20.txeis.sample.dao;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Component
public class XMLFileDao extends NamedParameterJdbcDaoSupport {
	private static Document doc;
	private static Element root;
	private static NamedParameterJdbcTemplate template;
	
	@Autowired
	public XMLFileDao(DataSource dataSource) {
		this.setDataSource(dataSource);
		XMLFileDao.template = this.getNamedParameterJdbcTemplate();
	}

	
	public void writeFile() {
		try {
			//Open file
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elements
			doc = docBuilder.newDocument();
			root = doc.createElement("InterchangeEducationOrganization");
			doc.appendChild(root);

			Attr attr = doc.createAttribute("xmlns:xsi");
			attr.setValue("http://www.w3.org/2001/XMLSchema-instance");
			root.setAttributeNode(attr);
	 	 	 		
			//Write campus information
			String sql = "select campus_id, campus_name from cr_demo where sch_yr = '2014' order by campus_id";
			Map<String, String> paramMap = new HashMap<String, String>();
			
			this.getNamedParameterJdbcTemplate().query(sql, paramMap, new CampusHandler());
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\temp\\Courses.xml"));
			transformer.transform(source, result);
			
			this.getConnection().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	static class CampusHandler implements RowCallbackHandler {		
		@Override
		public void processRow(ResultSet rs) throws SQLException {
			// staff elements
			Element campus = doc.createElement("campus");
			root.appendChild(campus);
	 
			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue(rs.getString(1));
			campus.setAttributeNode(attr);
			
			Element name = doc.createElement("campusname");
			name.appendChild(doc.createTextNode(rs.getString(2).trim()));
			campus.appendChild(name);
			
			//Retrieve Courses
			String sql = "select c.crs_nbr, d.title, c.campus_id from cr_crs_offered_cyr c, dr_crs_offered_cyr d " +
					"where c.sch_yr = '2014' and c.campus_id = :campus and d.sch_yr = '2014' and c.crs_nbr = d.crs_nbr order by c.crs_nbr";
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("campus", rs.getString(1));
			template.query(sql, paramMap, new CourseHandler(campus));

		}
	}
	
	static class CourseHandler implements RowCallbackHandler {
		Element campus;
		
		CourseHandler(Element campus) {
			this.campus = campus;
		}
		@Override
		public void processRow(ResultSet rs) throws SQLException {
			// Create course element
			Element course = doc.createElement("course");
			
			Element nbr = doc.createElement("coursenbr");
			nbr.appendChild(doc.createTextNode(rs.getString(1).trim()));
			course.appendChild(nbr);

			Element name = doc.createElement("coursename");
			name.appendChild(doc.createTextNode(rs.getString(2).trim()));
			course.appendChild(name);
			campus.appendChild(course);
			
			String sql = "select c.sec_nbr, c.instr_id, i.name_l + ', ' + i.name_f from cr_met_cyr c, cr_instr_cyr i " +
					"where c.sch_yr = '2014' and c.campus_id = :campus and c.crs_nbr = :course " +
					"and i.sch_yr = '2014' and i.campus_id = c.campus_id and i.instr_id = c.instr_id ";
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("campus", rs.getString(3));
			paramMap.put("course", rs.getString(1));
			template.query(sql, paramMap, new SectionHandler(course));
		}
		
	}
	
	static class SectionHandler implements RowCallbackHandler {
		Element course;
		
		SectionHandler(Element course) {
			this.course = course;
		}
		
		@Override
		public void processRow(ResultSet rs) throws SQLException {
			Element section = doc.createElement("section");
			course.appendChild(section);
			
			Element sectionid = doc.createElement("section-id");
			sectionid.appendChild(doc.createTextNode(rs.getString(1)));
			section.appendChild(sectionid);
			
			Element instr = doc.createElement("instructor");
			Attr attr = doc.createAttribute("id");
			attr.setValue(rs.getString(2));
			instr.setAttributeNode(attr);
			instr.appendChild(doc.createTextNode(rs.getString(3).trim()));
			section.appendChild(instr);
		}
	}
	
	
}
