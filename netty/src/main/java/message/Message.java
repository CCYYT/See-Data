package message;

public class Message {

    private byte type;//数据类型

    private byte codec;//编解码格式
    private Object text;//消息正文

    public Message(byte type, Object text) {
        this.type = type;
        this.text = text;
    }

    public Message(byte type, byte codec, Object text) {
        this.type = type;
        this.codec = codec;
        this.text = text;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }

    public byte getCodec() {
        return codec;
    }

    public void setCodec(byte codec) {
        this.codec = codec;
    }
}
