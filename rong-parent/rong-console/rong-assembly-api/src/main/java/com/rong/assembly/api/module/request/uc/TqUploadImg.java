package com.rong.assembly.api.module.request.uc;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TqUploadImg extends TqUserAuthBase {
    public enum UploadImgType {
        头像图片(0, 0),
        从业资格证书1(1, 0),
        从业资格证书2(2, 0),
        从业资格证书3(3, 0),
        从业资格证书4(4, 0),
        从业资格证书5(5, 0),
        小程序二维码(6, 0),
        //7
        certificate1Url(7, 0),
        certificate2Url(8, 0),
        certificate3Url(9, 0),
        certificate4Url(10, 0),
        certificate5Url(11, 0),
        certificate6Url(12, 0),
        certificate7Url(13, 0),
        certificate8Url(14, 0),
        certificate9Url(15, 0),
        certificate10Url(16, 0),

        //17
        bizCardUrl1(17, 0),
        bizCardUrl2(18, 0),

        holdingPhotoUrl1(19, 0),
        holdingPhotoUrl2(20, 0),

        transferInfoUrl1(21, 0),
        transferInfoUrl2(22, 0),
        transferInfoUrl3(23, 0),
        transferInfoUrl4(24, 0),
        storeBanner(25, 100),
        名片头像图片(26, 20),
        名片相册(27, 20)
        ;
        @Getter
        private final int value;
        @Getter
        private final int maxIndex;

        UploadImgType(int value, int maxIndex) {
            this.value = value;
            this.maxIndex = maxIndex;
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
