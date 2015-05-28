package bupt768.parabola.MyViews;

import android.util.Log;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Mock on 2015/5/27.
 */
public class Manager {

    private static ConcurrentHashMap<String,ISprite> M_Sprite =new ConcurrentHashMap<>();
    final String TAG_MANAGER="Manger";
    public Manager(){

        }
    public void addSprite(String id,ISprite iSprite){
        if(!M_Sprite.containsKey(id)){
            M_Sprite.put(id,iSprite);
        }
    }
    public ISprite getSprite(String id){
        return M_Sprite.get(id);
    }
    public void remove(String id){
        if(M_Sprite.containsKey(id)){
            M_Sprite.remove(id);
        }
    }
    public ConcurrentHashMap<String,ISprite> getSprites(){
        return M_Sprite;
    }
    public void getMessage(Message message){
        String targetID=message.getTargetID();
        Log.d(TAG_MANAGER,"getMessageID:"+targetID);
        sendMessage(targetID,message);
    }
    public void sendMessage(String targetID,Message message){
        if(M_Sprite.containsKey(targetID)){
            Log.d(TAG_MANAGER,"send");
            M_Sprite.get(targetID).dealMessage(message);
        }
    }

}
