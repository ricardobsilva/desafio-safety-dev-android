package netodevel.com.br.safety.broadcast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author NetoDevel
 */
public class VolumeBroadCastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        int volume = (Integer)intent.getExtras().get("android.media.EXTRA_VOLUME_STREAM_VALUE");






    }
}
