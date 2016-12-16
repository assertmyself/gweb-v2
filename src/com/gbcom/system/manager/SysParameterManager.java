package com.gbcom.system.manager;

import com.gbcom.system.daoservice.SysParameterService;
import com.gbcom.system.domain.SysParameter;
import com.gbcom.system.domain.bean.param.Constraint;
import com.gbcom.system.utils.Constants;
import com.hc.core.utils.StringHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统参数
 * 
 * @author Youyiming
 */
@Service
public class SysParameterManager {
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(SysParameterManager.class);

	@Autowired
	private SysParameterService sysParameterService;

	/**
	 * 获取参数的显示值
	 * 
	 * @param sysParameter
	 *            系统参数
	 * @return .
	 * @throws javax.xml.bind.JAXBException .
	 */
	public String getDisplayValue(SysParameter sysParameter)
			throws JAXBException {
		String value = sysParameter.getValue();

		// String xml = sysParameter.getConstraint();
		// if (!StringHelper.isEmpty(xml)) {
		// Constraint constraint = JaxbUtil.unmarshall(Constraint.class, xml);
		// value = getDisplayValue(sysParameter, constraint);
		// }
		return value;
	}

	/**
	 * 根据参数和约束获得参数的显示值 如果是enum类型或者boolean类型的话，不能直接将value中的值显示在grid上面
	 * 如果是Text类型的话，显示clobvalue在页面上
	 * 
	 * @param sysParameter
	 *            系统参数
	 * @param constraint
	 *            参数对应的约束
	 * @return .
	 */
	public String getDisplayValue(SysParameter sysParameter,
			Constraint constraint) {
		String value = sysParameter.getValue();
		if ("Text".equals(constraint.getType())) {
			value = sysParameter.getClobvalue();
		} else if ("Boolean".equals(constraint.getType())) {
			if (Constants.FLAG_FALSE.equals(value)) {
				value = "否";
			} else {
				value = "是";
			}
		} else if ("Enum".equals(constraint.getType())) {
			Map<String, String> map = getEnumMap(constraint);
			value = map.get(value);
		}
		return value;
	}

	/**
	 * 该方法从Constraint中获取enum类型约束definition对应的map
	 * 
	 * @param constraint
	 *            .
	 * @return .
	 */
	public Map<String, String> getEnumMap(Constraint constraint) {
		Map<String, String> map = new HashMap<String, String>();

		if ("Enum".equals(constraint.getType())) {
			String definition = constraint.getDefinition();
			String[] entrySet = StringUtils.split(definition, ",");
			for (String entry : entrySet) {
				int index = entry.indexOf("=");
				if (-1 != index) {
					String key = entry.substring(0, index);
					String value = entry.substring(index + 1);
					map.put(key, value);
				} else {
					map.put(entry, entry);
				}
			}
		}
		return map;
	}

	/**
	 * 获取系统参数集合 获取用于显示在当前项目系统参数grid页面上的系统参数集合
	 * 
	 * @return .
	 */
	public List<SysParameter> getSysParameters() {
		List<SysParameter> result = new ArrayList<SysParameter>();

		List<SysParameter> sysParameters = sysParameterService
				.findByQuery("from SysParameter where project is null order by code");

		for (SysParameter sysParameter : sysParameters) {
			result.add(sysParameter);
		}

		return result;
	}

	/**
	 * 通过code获得系统参数中的值，如果当前项目中和多项目中同时有对应该code的参数，那么将获取当前项目中的参数值
	 * 
	 * @param code
	 *            String
	 * @return String
	 */
	public String getSysParameterValue(String code) {
		String value = "";
		SysParameter sysParameter = getSysParameter(code);
		if (null != sysParameter) {
			value = getSysParameterValue(sysParameter);
		}
		return value;
	}

	/**
	 * 获得系统参数的值，如果clobvalue中有值，那么返回clobvalue中的值，否则返回value中的值
	 * 
	 * @param sysParameter
	 *            SysParameter
	 * @return String
	 */
	public String getSysParameterValue(SysParameter sysParameter) {
		String value = sysParameter.getClobvalue();
		if (StringHelper.isEmpty(value)) {
			value = sysParameter.getValue();
		}
		return value;
	}

	/**
	 * 通过code获得系统参数 先根据code获取当前项目中的系统参数，如果当前项目中不存在该参数，那么获取全局的系统参数
	 * 
	 * @param code
	 *            系统参数对应的编码
	 * @return SysParameter
	 */
	public SysParameter getSysParameter(String code) {
		SysParameter localParameter = sysParameterService.findUniqueByProperty(
				"code", code);
		if (null != localParameter) {
			return localParameter;
		}
		return getGlobalSysParameter(code);
	}

	/**
	 * 通过code返回全局的系统参数
	 * 
	 * @param code
	 *            系统参数对应的编码
	 * @return SysParameter
	 */
	public SysParameter getGlobalSysParameter(String code) {
		String hql = "from SysParameter where code='" + code + "'";
		SysParameter sysParameter = sysParameterService.findUnique(hql);
		return sysParameter;
	}

}
