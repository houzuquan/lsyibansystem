package com.yiban.yibanday.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.yiban.dao.YbUserDao;

public class TagYBUser extends BodyTagSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String action,birthdayType;

    @Override
    public int doStartTag() throws JspException {
        // TODO 自动生成的方法存根
        YbUserDao yb = (YbUserDao) this.pageContext.getSession().getAttribute("User");
        JspWriter out = pageContext.getOut();
        try {
            if (yb != null) {
                switch (action) {
                    case "getStuId":
                        out.print(yb.getStuId());
                        break;
                    case "getBankId":
                        out.print(yb.getBankId());
                        break;
                    case "getBirthday":
                        out.print(yb.getBirthday());
                        break;
                    case "getBirthdayType":
                        if(birthdayType.equals(yb.getBirthdayType())) {
                            out.print("selected=\"selected\"");
                        }else {
                            out.print("");
                        }
                        break;
                    case "getHobby":
                        out.print(yb.getHobby());
                        break;
                    case "getPhone":
                        out.print(yb.getPhone());
                        break;
                    case "getName":
                        out.print(yb.getStuName());
                        break;
                    case "getSectionName":
                        out.print(yb.getSectionName());
                        break;
                    case "getQdNum":
                        out.print(yb.getDKAllCount().get("qdNum"));
                        break;
                    case "getQtNum":
                        out.print(yb.getDKAllCount().get("qtNum"));
                        break;
                    default:
                        out.print("参数错误");
                        break;
                }
            } else {
                out.print("错误");
            }
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    public String getBirthdayType() {
        return birthdayType;
    }
    public void setBirthdayType(String birthdayType) {
        this.birthdayType = birthdayType;
    }
}
