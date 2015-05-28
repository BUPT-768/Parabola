package bupt768.parabola.MyViews;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mock on 2015/5/28.
 */
public class Message implements Serializable{
    private String targetID;
    private String sourceID;
    private MessageType messagetype;
    private Map<String,Integer> action=new HashMap<String,Integer>();


    public Message(MessageType messageType,String targetID,Map map){
        this.messagetype=messageType;
        this.targetID=targetID;
        this.action=map;
    }
    public String getTargetID(){
        return targetID;
    }
    public String getSourceID(){
        return sourceID;
    }
    public MessageType getMessagetype(){
        return messagetype;
    }
    public Map<String,Integer>getAction(){
        return action;
    }

}
