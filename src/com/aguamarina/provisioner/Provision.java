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
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Provision extends Activity {
    private static final int MENU_ABOUT = 1;

    @Override
    public void onCreate(Bundle icicle) {
    	super.onCreate(icicle);
    	setContentView(R.layout.main);
        final Button activation = (Button) findViewById(R.id.Activation);
        activation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Provision the device
            	Settings.System.putInt(getContentResolver(), "setup_wizard_has_run", 1);
            	Settings.System.putInt(getContentResolver(), "device_provisioned", 1);
            	startActivity(new Intent(Provision.this,Done.class));
            }
        });
    }
    
    // Menu Handling
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(1, MENU_ABOUT, 1, "About")
	        .setIcon(android.R.drawable.ic_menu_info_details);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()){
	    case MENU_ABOUT:
	        AlertDialog.Builder aboutnotif = new AlertDialog.Builder(this);
	        aboutnotif.setIcon(R.drawable.icon)
	                  .setTitle("Dream Provisioner")
	                  .setMessage("The Dream Provisioner is a tool only for activating a T-Mobile G1 handset.\n\nThe Dream Provisioner is part of the Aguamarina project which aims to revitalize legacy android devices, found on GitHub under the organization AguamarinaPR")
	                  .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                      public void onClick(DialogInterface dialog, int id) {
	                           dialog.cancel();
	                   }
	               });
	        aboutnotif.show();
	        return true;
	    }
	    return false;
	

	}
}
