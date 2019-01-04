package ustc.sse.xmlmapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ustc.sse.bean.User;

/**
 * @author LRK
 * @project_name mybatishelloworld
 * @package_name ustc.sse.mapper
 * @date 2018/12/26 16:44
 * @description God Bless, No Bug!
 */
public interface UserMapper {

//    @Select("select id,last_name,email,gender from tb_user where id = #{id}")
    User getUserById(String id);
    User getUserByIdAndName(@Param("id") Integer id, @Param("lastName") String lastName);
    void insertUser(User user);
    void deleteUserById(Integer id);
    void updateUser(User user);

}
