package eu.faircode.netguard;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class InfoUpdateActivity extends AppCompatActivity {

    public static void checkLauncher(Activity activity, PackageManager packageManager) {
        boolean isLauncherInstalled;
        try {
            packageManager.getPackageInfo("com.cando.chatsie", 0);
            isLauncherInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            isLauncherInstalled = false;
        }

        if (!isLauncherInstalled) {
            Intent intent = new Intent(activity, InfoUpdateActivity.class);
            activity.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_update);

        findViewById(R.id.btnNotNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btnPlayStore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appPackageName = "com.cando.chatsie";

                try {
                    startActivity(
                            new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=" + appPackageName)
                            )
                    );
                    finish();
                } catch (ActivityNotFoundException e) {
                    startActivity(
                            new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)
                            )
                    );
                    finish();
                }
            }
        });

    }
}
