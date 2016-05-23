package eu.siacs.conversations.entities;

import java.io.Serializable;

/**
 * Created by reza on 5/8/16.
 */
public class Content implements Serializable{
    public static final int TYPE_TEXT=1;
    public static final int TYPE_IMAGE=2;
    String uuid;
    String body;
    int type;
    String relativeFilePath;
    String imageHttpURL;
    int imageSize;
    int imageWidth;
    int imageHeight;

    public Content(String uuid,String body, int type, String relativeFilePath, String imageHttpURL, int imageSize, int imageWidth, int imageHeight) {
        this.uuid = uuid;
        this.body = body;
        this.type = type;
        this.relativeFilePath = relativeFilePath;
        this.imageHttpURL = imageHttpURL;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.imageSize = imageSize;

    }

    public Content(String uuid, String imageHttpURL, int imageSize, int imageWidth, int imageHeight) {
        this.uuid = uuid;
        this.type = TYPE_IMAGE;
        this.imageHttpURL = imageHttpURL;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.imageSize = imageSize;
    }

    public Content(String uuid, String body, int type, String relativeFilePath) {
        this.uuid = uuid;
        this.body = body;
        this.type = type;
        this.relativeFilePath = relativeFilePath;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRelativeFilePath() {
        return relativeFilePath;
    }

    public void setRelativeFilePath(String relativeFilePath) {
        this.relativeFilePath = relativeFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content = (Content) o;

        if (type != content.type) return false;
        if (body != null ? !body.equals(content.body) : content.body != null) return false;
        return relativeFilePath != null ? relativeFilePath.equals(content.relativeFilePath) : content.relativeFilePath == null;

    }

    @Override
    public int hashCode() {
        int result = body != null ? body.hashCode() : 0;
        result = 31 * result + type;
        result = 31 * result + (relativeFilePath != null ? relativeFilePath.hashCode() : 0);
        return result;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImageHttpURL() {
        return imageHttpURL;
    }

    public void setImageHttpURL(String imageHttpURL) {
        this.imageHttpURL = imageHttpURL;
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    @Override
    public String toString() {
        return "Content{" +
                "uuid='" + uuid + '\'' +
                ", body='" + body + '\'' +
                ", type=" + type +
                ", relativeFilePath='" + relativeFilePath + '\'' +
                ", imageHttpURL='" + imageHttpURL + '\'' +
                ", imageSize=" + imageSize +
                ", imageWidth=" + imageWidth +
                ", imageHeight=" + imageHeight +
                '}';
    }

}
