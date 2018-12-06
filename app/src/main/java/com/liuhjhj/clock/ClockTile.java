package com.liuhjhj.clock;

import android.app.Notification;
import android.content.Intent;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class ClockTile extends TileService {
    public ClockTile() {
        super();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        Tile tile = getQsTile();
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }

    @Override
    public void onClick() {
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.deskclock");
        if (intent != null) {
            intent.putExtra("type", "110");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
            startActivity(intent);

        }

    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        Tile tile = getQsTile();
        String str = Settings.System.getString(this.getContentResolver(),
                Settings.System.NEXT_ALARM_FORMATTED);
        if (str.equals("")){
            str = "NoClock";
        }
        tile.setLabel(str);
        tile.updateTile();
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
    }
}
