package com.example.woof_woof.health;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.woof_woof.R;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String notificationType = intent.getStringExtra("notificationType");
        int hours = intent.getIntExtra("hours", -1);
        int minutes = intent.getIntExtra("minutes", -1);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(notificationType)
                .setContentText("Сегодня " + hours + ":" + minutes)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, builder.build());
    }
}