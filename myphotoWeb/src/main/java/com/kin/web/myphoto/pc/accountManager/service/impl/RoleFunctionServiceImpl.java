package com.kin.web.myphoto.pc.accountManager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kin.web.myphoto.exception.Error;
import com.kin.web.myphoto.exception.FunctionException;
import com.kin.web.myphoto.exception.RoleFunctionException;
import com.kin.web.myphoto.pc.accountManager.entity.Function;
import com.kin.web.myphoto.pc.accountManager.entity.RoleFunction;
import com.kin.web.myphoto.pc.accountManager.mapper.FunctionMapper;
import com.kin.web.myphoto.pc.accountManager.mapper.RoleFunctionMapper;
import com.kin.web.myphoto.pc.accountManager.service.IRoleFunctionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
@Service
public class RoleFunctionServiceImpl extends ServiceImpl<RoleFunctionMapper, RoleFunction> implements IRoleFunctionService {

    @Resource
    private RoleFunctionMapper roleFunctionMapper;
    @Resource
    private FunctionMapper functionMapper;
    @Override
    public List<RoleFunction> listRFByFunctionId(Long functionId) {
        QueryWrapper<RoleFunction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("function_id",functionId);
        List<RoleFunction> roleFunctions = roleFunctionMapper.selectList(queryWrapper);
        return roleFunctions;
    }

    @Override
    public List<Long> listFunctionIds(Long roleId) {
        QueryWrapper<RoleFunction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        List<RoleFunction> roleFunctions = roleFunctionMapper.selectList(queryWrapper);
        if (roleFunctions.size()<=0){
            throw new RoleFunctionException(Error.ROLEFUNCTION_IS_NULL);
        }
        List<Long> functionIds = new ArrayList<>();
        for(RoleFunction roleFunction:roleFunctions){
            functionIds.add(roleFunction.getFunctionId());
        }
        return functionIds;
    }

    @Override
    public int addRoleFunction(Long roleId, Long functionId) {
        //添加前需要判断系统是否有此权限，这里无需判断重复，由于列表显示不会出现已添加的权限
        Function checkFunction = functionMapper.selectOne(new QueryWrapper<Function>().eq("function_id",functionId));
        if (checkFunction==null){
            throw new FunctionException(Error.FUNCTION_NOT_FOUND);
        }
        RoleFunction roleFunction = new RoleFunction();
        roleFunction.setFunctionId(functionId);
        roleFunction.setRoleId(roleId);
        return roleFunctionMapper.insert(roleFunction);
    }

    @Override
    public int delRoleFunction(Long functionId) {
        //查询当前权限是否存在
        QueryWrapper<RoleFunction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("function_id",functionId);
        RoleFunction roleFunction = roleFunctionMapper.selectOne(queryWrapper);
        if (roleFunction==null){
            throw new RoleFunctionException(Error.ROLEFUNCTION_NOT_FOUND);
        }
        return roleFunctionMapper.delete(queryWrapper);
    }
}
