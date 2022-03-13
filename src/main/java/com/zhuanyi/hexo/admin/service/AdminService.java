package com.zhuanyi.hexo.admin.service;

import com.zhuanyi.hexo.admin.form.LoginForm;
import com.zhuanyi.hexo.admin.vo.AdminInfoVO;

public interface AdminService {

    String login(LoginForm loginForm);

     AdminInfoVO getAdminInfo();

     boolean logout();

}
