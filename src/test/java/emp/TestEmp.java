package emp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;

public class TestEmp {
  public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    EmpService es = (EmpService) context.getBean("empService");
    Emp emp = es.showOne("c539119d-de94-4f58-b406-52127190d5bb");
    System.out.println(emp);
    
  }
}
