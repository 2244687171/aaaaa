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

	
	//查所有
	public String queryAll(){
		 list = empService.selectEmp();
		 System.out.println(list);
		 return SUCCESS;
	}
  //查一个
	public String queryOne(){
		 emp = empService.showOne(id);
		 return SUCCESS;
	}
	//添加
	public String regist(){
		empService.insertEmp(emp);
		return SUCCESS;
	}
	//删除
	public String drop(){
		empService.dropEmp(id);
		return SUCCESS;
	}
	//修改
	public String update(){
		empService.UpdateEmp(emp);
		return SUCCESS;
	}
	//导出excel表格
	public String downLode() throws Exception{
		 //创建一个工作空间
		Workbook workbook = new HSSFWorkbook();
		//创建工作簿
		Sheet sheet = workbook.createSheet("员工的基本信息");
		//合并单元格
		//合并区域
		CellRangeAddress address = new CellRangeAddress(0, 1, 0, 5);
		sheet.addMergedRegion(address);
		//设置整个sheet宽度
		sheet.setDefaultColumnWidth(20);
		//创建一级标题
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);//居中对齐
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 18);
		cellStyle.setFont(font);//设置字体
		cell.setCellStyle(cellStyle );//设置样式
		cell.setCellValue("员工基本信息");
		//创建二级标题
		Row titleRow = sheet.createRow(2);
		//创建标题列
		Field[] declaredFields = Emp.class.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			Cell colCell = titleRow.createCell(i);
			colCell.setCellValue(getValue(declaredFields[i].getName()));
		}
		//创建数据列
		list = empService.selectEmp();
		for (int i = 0; i < list.size(); i++) {
			Row dataRow = sheet.createRow(i+3); //数据行
			//创建数据列
			for (int j = 0; j < declaredFields.length; j++) {
				Cell dataCell = dataRow.createCell(j);
				
				//通过反射调用类中的方法
				String getMethodName = "get" + 
										declaredFields[j].getName().substring(0, 1).toUpperCase()
										+declaredFields[j].getName().substring(1);
				
				//返回方法对象 //参数一:方法的名字   //参数二:方法参数的类型
				Method getMethod = Emp.class.getDeclaredMethod(getMethodName, new Class[]{});
				
				//执行方法  参数一:执行那个对象中的方法    参数二:该方法的参数
				Object invoke = getMethod.invoke(list.get(i), new Object[]{});
				//考虑日期类型
				if(declaredFields[j].getType()  == Date.class){
					dataCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(invoke));
				}
				/*//考虑关系属性   对象|集合
				if(declaredFields[j].getType() == City.class){
					dataCell.setCellValue(((City)invoke).getName());
				}*/
				/*//考虑集合
				if (declaredFields[j].getType()==List.class && declaredFields[j].getName().equals("tags")) {
					List<Tag> list = (List<Tag>)invoke;
					StringBuilder sb = new StringBuilder();
					for (Tag tag : list) {
						sb.append(tag.getName()+" ");
					}
					dataCell.setCellValue(sb.toString());
				}*/
				//考虑基本属性
				if(declaredFields[j].getType() == Integer.class 
							|| declaredFields[j].getType() == String.class  
							|| declaredFields[j].getType() == Double.class){
					dataCell.setCellValue(invoke.toString());
				}
				
			}
		}
		
		//写出Excel
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
		map.put("id", "编号");
		map.put("name", "姓名");
		map.put("age", "年龄");
		map.put("salary", "工资");
		return map.get(key);
	}
}
