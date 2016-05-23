package eu.siacs.conversations.entities;

/**
 * Created by reza on 5/13/16.
 */
public class JsonContent {
    public static final int MESSAGE = 1;
    public static final Integer SMS_CODE = 2;
    private Integer type;
    private String content;

    public JsonContent(Integer type, String content) {
        this.type = type;
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonContent that = (JsonContent) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
