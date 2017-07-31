package com.baizhi.action;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import com.opensymphony.xwork2.ActionSupport;
@Controller("empAction")
@Scope("prototype")
public class EmpAction extends ActionSupport {
	@Resource
    private EmpService empService;
	
    private List<Emp> list;
    private String id;
    private Emp emp;
    private Emp emp1;
    
    
	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Emp> getList() {
		return list;
	}

	public void setList(List<Emp> list) {
		this.list = list;
	}

	
	//鏌ユ墍鏈�
	public String queryAll(){
		 list = empService.selectEmp();
		 System.out.println(list);
		 return SUCCESS;
	}
  //鏌ヤ竴涓�
	public String queryOne(){
		 emp = empService.showOne(id);
		 return SUCCESS;
	}
	//娣诲姞
	public String regist(){
		empService.insertEmp(emp);
		return SUCCESS;
	}
	//鍒犻櫎
	public String drop(){
		empService.dropEmp(id);
		return SUCCESS;
	}
	//淇敼
	public String update(){
		empService.UpdateEmp(emp);
		return SUCCESS;
	}
	//瀵煎嚭excel琛ㄦ牸
	public String downLode() throws Exception{
		 //鍒涘缓涓�釜宸ヤ綔绌洪棿
		Workbook workbook = new HSSFWorkbook();
		//鍒涘缓宸ヤ綔绨�
		Sheet sheet = workbook.createSheet("鍛樺伐鐨勫熀鏈俊鎭�);
		//鍚堝苟鍗曞厓鏍�
		//鍚堝苟鍖哄煙
		CellRangeAddress address = new CellRangeAddress(0, 1, 0, 5);
		sheet.addMergedRegion(address);
		//璁剧疆鏁翠釜sheet瀹藉害
		sheet.setDefaultColumnWidth(20);
		//鍒涘缓涓�骇鏍囬
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);//灞呬腑瀵归綈
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 18);
		cellStyle.setFont(font);//璁剧疆瀛椾綋
		cell.setCellStyle(cellStyle );//璁剧疆鏍峰紡
		cell.setCellValue("鍛樺伐鍩烘湰淇℃伅");
		//鍒涘缓浜岀骇鏍囬
		Row titleRow = sheet.createRow(2);
		//鍒涘缓鏍囬鍒�
		Field[] declaredFields = Emp.class.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			Cell colCell = titleRow.createCell(i);
			colCell.setCellValue(getValue(declaredFields[i].getName()));
		}
		//鍒涘缓鏁版嵁鍒�
		list = empService.selectEmp();
		for (int i = 0; i < list.size(); i++) {
			Row dataRow = sheet.createRow(i+3); //鏁版嵁琛�
			//鍒涘缓鏁版嵁鍒�
			for (int j = 0; j < declaredFields.length; j++) {
				Cell dataCell = dataRow.createCell(j);
				
				//閫氳繃鍙嶅皠璋冪敤绫讳腑鐨勬柟娉�
				String getMethodName = "get" + 
										declaredFields[j].getName().substring(0, 1).toUpperCase()
										+declaredFields[j].getName().substring(1);
				
				//杩斿洖鏂规硶瀵硅薄 //鍙傛暟涓�鏂规硶鐨勫悕瀛�  //鍙傛暟浜�鏂规硶鍙傛暟鐨勭被鍨�
				Method getMethod = Emp.class.getDeclaredMethod(getMethodName, new Class[]{});
				
				//鎵ц鏂规硶  鍙傛暟涓�鎵ц閭ｄ釜瀵硅薄涓殑鏂规硶    鍙傛暟浜�璇ユ柟娉曠殑鍙傛暟
				Object invoke = getMethod.invoke(list.get(i), new Object[]{});
				//鑰冭檻鏃ユ湡绫诲瀷
				if(declaredFields[j].getType()  == Date.class){
					dataCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(invoke));
				}
				/*//鑰冭檻鍏崇郴灞炴�   瀵硅薄|闆嗗悎
				if(declaredFields[j].getType() == City.class){
					dataCell.setCellValue(((City)invoke).getName());
				}*/
				/*//鑰冭檻闆嗗悎
				if (declaredFields[j].getType()==List.class && declaredFields[j].getName().equals("tags")) {
					List<Tag> list = (List<Tag>)invoke;
					StringBuilder sb = new StringBuilder();
					for (Tag tag : list) {
						sb.append(tag.getName()+" ");
					}
					dataCell.setCellValue(sb.toString());
				}*/
				//鑰冭檻鍩烘湰灞炴�
				if(declaredFields[j].getType() == Integer.class 
							|| declaredFields[j].getType() == String.class  
							|| declaredFields[j].getType() == Double.class){
					dataCell.setCellValue(invoke.toString());
				}
				
			}
		}
		
		//鍐欏嚭Excel
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("content-disposition", "attments;fileName=allEmp.xls");
		ServletOutputStream outputStream = response.getOutputStream();
		
		workbook.write(outputStream);
		outputStream.close();
		
return NONE;
	}

	public static String  getValue(String key){
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", "缂栧彿");
		map.put("name", "濮撳悕");
		map.put("age", "骞撮緞");
		map.put("salary", "宸ヨ祫");
		return map.get(key);
	}
}
