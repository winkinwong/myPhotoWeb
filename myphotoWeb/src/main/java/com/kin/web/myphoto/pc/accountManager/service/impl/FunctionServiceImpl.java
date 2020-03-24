package com.kin.web.myphoto.pc.accountManager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kin.web.myphoto.exception.Error;
import com.kin.web.myphoto.exception.FunctionException;
import com.kin.web.myphoto.pc.accountManager.entity.Function;
import com.kin.web.myphoto.pc.accountManager.entity.RoleFunction;
import com.kin.web.myphoto.pc.accountManager.mapper.FunctionMapper;
import com.kin.web.myphoto.pc.accountManager.service.IFunctionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kin.web.myphoto.pc.accountManager.service.IRoleFunctionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class FunctionServiceImpl extends ServiceImpl<FunctionMapper, Function> implements IFunctionService {

    @Resource
    private FunctionMapper functionMapper;
    @Resource
    private IRoleFunctionService roleFunctionService;

    @Override
    public List<Function> listFunctionByPage(Long roleId) {
        return functionMapper.listFunctionNotInRoleId(roleId);
    }

    @Override
    public int addFunction(Function function) {
        //添加权限之前需要进行排重
        QueryWrapper<Function> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("function_name",function.getFunctionName());
        Function checkFunction = functionMapper.selectOne(queryWrapper);
        if (checkFunction!=null){
            throw new FunctionException(Error.FUNCTION_EXISTS);
        }
        return functionMapper.insert(function);
    }

    @Override
    public int delFunction(Long functionId) {
        //删除前需要查询是否有该权限
        QueryWrapper<Function> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("function_id",functionId);
        Function checkFunction = functionMapper.selectOne(queryWrapper);
        if (checkFunction==null){
            throw new FunctionException(Error.FUNCTION_NOT_FOUND);
        }
        //删除前需要查询中间表是否存在记录，即角色是否有与该权限关联，若有无法删除
        List<RoleFunction> roleFunctions = roleFunctionService.listRFByFunctionId(checkFunction.getFunctionId());
        if (roleFunctions.size()>0){
            throw new FunctionException(Error.FUNCTION_RELATION_EXISTS);
        }
        return functionMapper.delete(queryWrapper);
    }

    @Override
    public List<Function> listFunctionByFunctionIds(List<Long> functionIds) {
        QueryWrapper<Function> queryWrapper = new QueryWrapper<>();
        for (Long functionId:functionIds){
            queryWrapper.eq("function_id",functionId);
        }
        List<Function> functions = functionMapper.selectList(queryWrapper);
        return functions;
    }
}
