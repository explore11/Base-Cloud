package com.base.system.api.factory;

import com.base.common.core.domain.Result;
import com.base.common.core.enums.ResultCode;
import com.base.common.entity.system.SysFile;
import com.base.system.api.RemoteFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务降级处理
 *
 * @author swq
 */
@Component
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

    @Override
    public RemoteFileService create(Throwable throwable) {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileService() {
            @Override
            public Result<SysFile> upload(MultipartFile file) {
                throwable.printStackTrace();
                return Result.failure(ResultCode.SPECIFIED_UPLOAD_FILE_FAILURE.code(), ResultCode.SPECIFIED_UPLOAD_FILE_FAILURE.message());
            }
        };
    }
}
