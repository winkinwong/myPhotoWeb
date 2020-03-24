package com.kin.web.myphoto.pc.accountManager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.kin.web.myphoto.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleFunction extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "role_function_id", type = IdType.AUTO)
    private Long roleFunctionId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 功能id
     */
    private Long functionId;



}
