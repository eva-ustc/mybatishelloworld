package ustc.sse.bean;

/**
 * @author LRK
 * @project_name mybatishelloworld
 * @package_name ustc.sse.bean
 * @date 2018/12/30 18:38
 * @description God Bless, No Bug!
 */
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<>();

    public  User getUsers() {
        return users.get();
    }

    public void setUsers(User user) {
        users.set(user);
    }
    public void remove(){
        users.remove();
    }
}
