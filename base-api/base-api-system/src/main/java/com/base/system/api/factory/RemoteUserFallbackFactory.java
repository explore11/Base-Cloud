package com.base.system.api.factory;

import com.base.common.core.domain.Result;
import com.base.common.core.enums.ResultCode;
import com.base.common.entity.system.SysUser;
import com.base.system.api.RemoteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.base.system.api.model.LoginUser;

/**
 * 用户服务降级处理
 *
 * @author swq
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService() {
            @Override
            public Result<LoginUser> getUserInfo(String username, String source) {
                throwable.printStackTrace();
                return Result.failure(ResultCode.USER_INFO_FAILURE.code(), ResultCode.USER_INFO_FAILURE.message());
            }

            @Override
            public Result<Boolean> registerUserInfo(SysUser sysUser, String source) {
                return Result.failure("注册用户失败:");
            }
        };
    }
}
