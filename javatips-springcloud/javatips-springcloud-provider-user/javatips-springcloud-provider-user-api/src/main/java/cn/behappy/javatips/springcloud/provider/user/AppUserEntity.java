package cn.behappy.javatips.springcloud.provider.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value ="app_user")
public class AppUserEntity {

    @TableId
    private Integer id;

    private String name;

    private String mobile;
}
