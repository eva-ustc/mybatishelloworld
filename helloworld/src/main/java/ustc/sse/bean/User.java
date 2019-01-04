package ustc.sse.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author LRK
 * @project_name mybatishelloworld
 * @package_name ustc.sse.bean
 * @date 2018/12/26 15:50
 * @description God Bless, No Bug!
 */
@Setter@Getter@ToString
public class User implements Serializable {
    private String id;
    private String lastName;
    private String email;
    private String gender;

}
