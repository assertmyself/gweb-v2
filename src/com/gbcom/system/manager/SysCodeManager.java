package com.gbcom.system.manager;

import com.gbcom.system.daoservice.SysCodeDetailService;
import com.gbcom.system.domain.SysCodeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project:bcscmis
 * <p/>
 * <p>
 * 系统代码逻辑处理类
 * </p>
 * <p/>
 * Create On 2009-12-31 下午07:16:08
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service
public class SysCodeManager {
    @Autowired
    private SysCodeDetailService sysCodeDetailService;

    /**
     * 根据代码类型和代码值取得详细代码
     *
     * @param mainCode   代码类型编码
     * @param detailCode 代码项编码
     * @return 代码
     */
    public SysCodeDetail getCodeDetailByCode(String mainCode, String detailCode) {
        String hql = "from SysCodeDetail t where t.sysCode.code = '" + mainCode
                + "' and t.code = '" + detailCode + "'";
        List<SysCodeDetail> list = sysCodeDetailService.findByQuery(hql);
        if (list.size() > 0) {
            return list.iterator().next();
        }
        return null;
    }

    /**
     * 根据代码类型和代码值取得详细代码
     *
     * @param mainCode   代码类型编码
     * @param detailName 代码项名称
     * @return 代码
     */
    public SysCodeDetail getCodeDetailByName(String mainCode, String detailName) {
        String hql = "from SysCodeDetail t where t.sysCode.code = '" + mainCode
                + "' and t.name = '" + detailName + "'";
        List<SysCodeDetail> list = sysCodeDetailService.findByQuery(hql);
        if (list.size() > 0) {
            return list.iterator().next();
        }
        return null;
    }

    /**
     * 根据id获取代码记录
     *
     * @param codeDetailId .
     * @return .
     */
    public SysCodeDetail getCodeListById(Long codeDetailId) {
        return sysCodeDetailService.get(codeDetailId);
    }

    /**
     * 根据代码取得代码列表值
     *
     * @param code 代码类型Code
     * @return SysCodeDetail列表
     */
    public List<SysCodeDetail> getCodeListByCode(String code) {
        String hql = "from SysCodeDetail t where t.sysCode.code = '" + code
                + "' order by t.treeId";
        return sysCodeDetailService.findByQuery(hql);
    }


}
