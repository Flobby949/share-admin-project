package top.ssy.share.admin.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MenuSerializer extends JsonSerializer {

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Integer.parseInt(o.toString()) == 0) {
            jsonGenerator.writeBoolean(true);
        } else {
            jsonGenerator.writeBoolean(false);
        }
    }
}
