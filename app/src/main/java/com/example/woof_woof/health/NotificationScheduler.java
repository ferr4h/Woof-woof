package com.example.woof_woof.health;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;

public class NotificationScheduler {

    public static void scheduleNotification(Context context, String notificationType, int day, int month, int hours, int minutes) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(context, NotificationReceiver.class);
        notificationIntent.putExtra("notificationType", notificationType);
        notificationIntent.putExtra("hours", hours);
        notificationIntent.putExtra("minutes", minutes);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 45);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}