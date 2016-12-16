package com.gbcom.system.manager;

import com.gbcom.system.daoservice.SysParameterService;
import com.gbcom.system.domain.SysParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Date: 12-2-17 Time: 上午10:41 To change this template use File | Settings |
 * File Templates.
 */
@Service
public class ParamManager {
	@Autowired
	private SysParameterService sysParameterService;

	/**
	 * 取系统参数值
	 * 
	 * @param code
	 *            String
	 * @return String
	 */
	public String getParamValueByCode(String code) {
		SysParameter parameter = getParameterByCode(code);
		return parameter.getValue();
	}

	/**
	 * 根据代码取系统参数对象
	 * 
	 * @param code
	 *            String
	 * @return SysParameter
	 */
	public SysParameter getParameterByCode(String code) {
		return sysParameterService.findUnique("from SysParameter where code = "
				+ code);
	}

	/**
	 * 取系统参数的长参数值
	 * 
	 * @param code
	 *            String
	 * @return String
	 */
	public String getParamLongValueByCode(String code) {
		SysParameter parameter = getParameterByCode(code);
		return parameter.getClobvalue();
	}

}
