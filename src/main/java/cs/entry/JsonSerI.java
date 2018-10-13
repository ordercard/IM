package cs.entry;

import com.alibaba.fastjson.JSONObject;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 10:25 2018/10/11 2018
 * @Modify:
 */
public class JsonSerI implements  Serializer{
    @Override
    public byte getSerializerAlgorithm() {
        return SeriAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSONObject.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSONObject.parseObject(bytes,clazz);
    }
}
