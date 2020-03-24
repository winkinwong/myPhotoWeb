package com.kin.web.myphoto.pc.accountManager.mapper;

import com.kin.web.myphoto.pc.accountManager.entity.Function;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
public interface FunctionMapper extends BaseMapper<Function> {
    List<Function> listFunctionNotInRoleId(Long roleId);
}
