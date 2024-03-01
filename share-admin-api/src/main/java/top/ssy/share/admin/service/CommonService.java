package top.ssy.share.admin.service;

import top.ssy.share.admin.vo.FileUrlVO;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

    /**
     * 图片上传
     *
     * @param uploadFile
     * @return
     */
    FileUrlVO upload(MultipartFile uploadFile);

}
