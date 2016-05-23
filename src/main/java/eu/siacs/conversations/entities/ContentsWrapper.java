package eu.siacs.conversations.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by reza on 5/7/16.
 */
public class ContentsWrapper implements Serializable{
    List<Content> contents;
    public ContentsWrapper(List<Content> contents){
        this.contents = contents;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
