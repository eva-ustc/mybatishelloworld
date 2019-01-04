package ustc.sse.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.ehcache.EhcacheCache;
import ustc.sse.bean.Department;

/**
 * @author LRK
 * @project_name mybatishelloworld
 * @package_name ustc.sse.mapper
 * @date 2018/12/27 14:23
 * @description God Bless, No Bug!
 */
@CacheNamespace(implementation = EhcacheCache.class)
public interface DeptMapper {

    @Select("SELECT * FROM tb_dept WHERE id=#{id}")
    Department getDeptById(Integer id);

    @Insert({"INSERT INTO tb_dept(dept_name) VALUES(#{deptName})"})
    @Options(useGeneratedKeys = true,keyProperty = "id")
    Boolean insertDept(Department department);
}
