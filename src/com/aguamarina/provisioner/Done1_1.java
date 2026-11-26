/*BSD 2-Clause License

Copyright (c) 2023, TropicalBananas
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

package com.aguamarina.provisioner;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Done1_1 extends Activity {
    private static final int MENU_SETTINGS = 1;

    private String describe(Exception e) {
        Throwable t = e;
        if (e instanceof java.lang.reflect.InvocationTargetException && e.getCause() != null) {
            t = e.getCause();
        }
        return t.toString();
    }

    private void openLauncher() {
        String err1 = "";
        String err2 = "";
        try {
            // Method 1: name the stock launcher directly
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setClassName("com.android.launcher", "com.android.launcher.Launcher");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } catch (Exception e1) {
            err1 = e1.toString();
            try {
                // Method 2: standard Home intent, no names
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(home);
            } catch (Exception e2) {
                err2 = e2.toString();
                new AlertDialog.Builder(this)
                    .setTitle("Could not open launcher")
                    .setMessage("Method 1: " + err1 + "\n\nMethod 2: " + err2)
                    .setPositiveButton("OK", null)
                    .show();
            }
        }
    }

    private String killSetup() {
        String pkg = "com.android.setupwizard";
        try {
            Class<?> amn = Class.forName("android.app.ActivityManagerNative");
            Object svc = amn.getMethod("getDefault").invoke(null);
            Class<?> iam = Class.forName("android.app.IActivityManager");
            iam.getMethod("restartPackage", String.class).invoke(svc, pkg);
            return pkg + ": Killing process with IActivityManager!";
        } catch (Exception e) {
            return pkg + ": failed (" + describe(e) + ")";
        }
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.done1_1);

        final Button openLauncherBtn = (Button) findViewById(R.id.OpenLauncher);
        openLauncherBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLauncher();
            }
        });

        // Try to kill the setup
        String killResult = killSetup();
        Toast.makeText(this, killResult, Toast.LENGTH_LONG).show();
    }

    // Menu Handling
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, MENU_SETTINGS, 1, "Settings")
            .setIcon(android.R.drawable.ic_menu_preferences);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SETTINGS:
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                return true;
        }
        return false;
    }
}
