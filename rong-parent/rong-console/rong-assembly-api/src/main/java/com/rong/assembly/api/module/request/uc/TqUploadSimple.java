package com.rong.assembly.api.module.request.uc;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TqUploadSimple extends TqUserAuthBase {
    public enum UploadFileType {
        头像图片(0, 1,"image"),
        从业资格证书1(1, 1,"image"),
        从业资格证书2(2, 1,"image"),
        从业资格证书3(3, 1,"image"),
        从业资格证书4(4, 1,"image"),
        从业资格证书5(5, 1,"image"),
        小程序二维码(6, 1,"image"),
        //7
        certificate1Url(7, 1,"image"),
        certificate2Url(8, 1,"image"),
        certificate3Url(9, 1,"image"),
        certificate4Url(10, 1,"image"),
        certificate5Url(11, 1,"image"),
        certificate6Url(12, 1,"image"),
        certificate7Url(13, 1,"image"),
        certificate8Url(14, 1,"image"),
        certificate9Url(15, 1,"image"),
        certificate10Url(16, 1,"image"),

        //17
        bizCardUrl1(17, 1,"image"),
        bizCardUrl2(18, 1,"image"),

        holdingPhotoUrl1(19, 1,"image"),
        holdingPhotoUrl2(20, 1,"image"),

        transferInfoUrl1(21, 1,"image"),
        transferInfoUrl2(22, 1,"image"),
        transferInfoUrl3(23, 1,"image"),
        transferInfoUrl4(24, 1,"image"),
        storeBanner(25, 100,"image"),
        名片头像图片(26, 20,"image"),
        名片相册(27, -1,"image"),
        路演图片(28, -1,"image"),
        路演文档(29, -1,"doc"),
        路演视频(30, -1,"video")
        ;
        @Getter
        private final int value;
        /**
         * 最大数量，不能为 0
         */
        @Getter
        private final int maxCount;
        @Getter
        private final String dirName;

        UploadFileType(int value, int maxCount, String dirName) {
            this.value = value;
            this.maxCount = maxCount;
            this.dirName = dirName;
        }
    }

    /**
     * 上传图片
     */
    @ApiModelProperty(value = "上传图片", required = true)
    @RequireValidator
    private MultipartFile img;
    /**
     * 图片分类
     */
    @ApiModelProperty(value = "图片分类" +
            "(0：头像，1：从业资格证书1，2：从业资格证书2，3：从业资格证书3，4：从业资格证书4，5：从业资格证书5，6：小程序二维码)" +
            "(7:资料认证1,8:资料认证2,9:资料认证3,10:资料认证4,11:资料认证5,12:资料认证6,13:资料认证7,14:资料认证8,15:资料认证9,16:资料认证10)" +
            "(17:名片图片1,18:名片图片2,19:手持证件照1,20:手持证件照2,21:转账截图1,22:转账截图2,23:转账截图3,24:转账截图4;25:装潢店图片上传，最多20张；)" +
            "(26：名片头像图片，27：名片相册，最多21张)"
            , required = true)
    @RequireValidator
    private Integer type;
    @ApiModelProperty("图片索引：默认是0，有些允许上传多张，比如名片相册，索引允许从0-20，超过20的会覆盖之前的，即picIndex%20")
    private Integer picIndex = 0;

}
