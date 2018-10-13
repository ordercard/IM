package cs.entry;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 10:25 2018/10/11 2018
 * @Modify:
 */
public interface Serializer {

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);



    Serializer DEFAULT = new JsonSerI();

}
