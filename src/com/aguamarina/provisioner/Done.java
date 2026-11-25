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
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

public class Done extends Activity {
    private static final int MENU_SETTINGS = 1;
    private static final int MENU_SETTINGS_EXPLICIT = 2;

    @Override
    public void onCreate(Bundle icicle) {
    	super.onCreate(icicle);
    	setContentView(R.layout.done);
    }

    // Menu Handling
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, MENU_SETTINGS, 1, "Settings")
            .setIcon(android.R.drawable.ic_menu_preferences);
        menu.add(2, MENU_SETTINGS_EXPLICIT, 2, "Settings (Explicit)")
            .setIcon(android.R.drawable.ic_menu_preferences);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SETTINGS:
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                return true;
            case MENU_SETTINGS_EXPLICIT:
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.setClassName("com.android.settings", "com.android.settings.Settings");
                startActivity(i);
                return true;
        }
        return false;
    }
}
