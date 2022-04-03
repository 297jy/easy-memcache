package com.zhuanyi.hexo.admin.dao;

import com.zhuanyi.hexo.admin.config.SystemConfig;
import com.zhuanyi.hexo.admin.obj.dto.SystemSettingDTO;

public interface SystemSettingDao {

    SystemConfig getSystemSetting();

    boolean updateSystemSetting(SystemSettingDTO systemSettingDTO);

}
