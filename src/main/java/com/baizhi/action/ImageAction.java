package com.baizhi.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.baizhi.util.VerifyCodeUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
@Controller("imageAction")
@Scope("prototype")
public class ImageAction extends ActionSupport{
    
	public String random() throws IOException{
		//生成随机数
		String code = VerifyCodeUtil.generateVerifyCode(4);
		//将随机数放入值栈中
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.setValue("#session.code", code);
		//生成图片
		BufferedImage image = VerifyCodeUtil.getImage(200, 35, code);
		//输出图片
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("image/png");
		ServletOutputStream os = response.getOutputStream();
		ImageIO.write(image, "png", os);
		return NONE;
	}
}
