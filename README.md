# Dream Provisioner
The Dream Provisioner is a tool for provisioning T-Mobile G1 devices. Here's two ways to run the provisioner:

## Method 1: Shell exploit [WORKS ONLY ON ANDROID 1.0 (RC29)]

### Part 1: Flashing T-Mobile G1 ROM version RC29, if not already installed
  
You will need [DREAIMG.nbh](http://archive.org/download/DREAIMG/DREAIMG.nbh), which is the file containing RC29 with Android 1.0.  
  
RC29 or lower is required for this due to a root shell always being open, which will allow you to execute root commands simply by typing them in.  

1.  Insert a micro SD card into your computer.
2.  Format the card as FAT32. **(This will destroy any data on the card.)**
3.  Once the card has completed formatting, copy the **DREAIMG.nbh** file you downloaded earlier to the card.
4.  Eject the micro SD card from your computer and insert it into your handset.
5.  Hold the camera button and **power button (end call button)** at the same time and you will see the bootloader.
6.  Press the power button once to start the update/downgrade.
7.  Once the update has finished, press the trackball once.
8.  Attempt to reboot by holding the **Call, Menu and Power** butttons. If this doesn't work, remove the battery and insert it again.
9.  Start the phone up, and you can now see that it is running Android 1.0 with the RC29 rom, however, you're not done yet.

### Part 2: Running the provisioner

The unskippable setup wizard presents a problem, since you can't use the phone without completing it, and you likely can't complete it as of 2023.  

The home and menu buttons are disabled until after the setup has been completed, this is done through a hidden setting labeled `device_provisioned` which when disabled, disables the home and menu buttons. Dream Provisioner sets this setting to true, enabling these buttons.
  
You will need [ADB](https://developer.android.com/tools/releases/platform-tools) to connect to your handset.  

You will also need [Dream Provisioner](http://aguamarina.altervista.org/repo/dreamprovisioner.apk) to provision your handset.  

When I did this I used an inactive SIM card to bypass the no SIM card lock screen, although it might work without any sim card, I have yet to test it.  

1.  On your handset, slide open the keyboard, press **enter** and then type: `setprop persist.service.adb.enable 1` and press enter again. you can tap through to the google account setup screen to see what you are typing.
2.  This will enable your phone to connect to your computer via ADB, go to your computer's terminal and run "adb install dreamprovisioner.apk" (assuming dreamprovisioner.apk is in the same directory as ADB).
3.  Then, run the command ```adb shell am start -a android.intent.action.MAIN -n com.aguamarina.provisioner/.Provision``` to start the Dream Provisioner on your handset, which can guide you through the next steps, or you can continue reading here.
4.  Press the provision button on your handset's screen.

Now you should be able to use your G1 properly as you please, if you see a notification saying setup isn't finished, you can ignore it and continue using your device either way.

## Method 2: Browser exploit (works on Android 1.0 and 1.1)

If your handset is running Android 1.1 and you wish to not change the ROM, you can also download and launch the provisioner.

**NOTE: You will need a SIM card with an active data plan to be inserted into the device.**

1. In the setup screen, navigate to either the screen for creating or signing into a Google account. Find the "Additional" terms link at the bottom and press it.
2. A webview showing a page that lists various languages should show up. Select one, regardless of which.
3. A full browser should then open up. Press the "Menu" key, choose "Go to URL" and type out the URL to a publicly hosted "DreamProvisioner.apk". The official one is hosted at:
```
http://aguamarina.altervista.org/repo/dreamprovisioner.apk
```
4. After the APK is downloaded, open it directly from the "Downloads" menu. Press "Settings" and then enable "Unknown sources", disregarding the warning. While in that settings screen, you could optionally go to "Development" and enable "USB debugging".
5. Go back to "Downloads" and re-open the APK. Install the app.
6. Open the app and tap "Provision device"

# How to fully bypass the setup?

Even after your handset is provisioned, the setup wizard's main activity is still set as a home launcher with a priority, so after boot or when pressing Home, it will be opened instead of the launcher. To permanently bypass the setup, you have to root your device.

When the device is rooted, you can either disable "com.android.setupwizard.SetupWizardActivity", or or delete/rename the setup wizard itself at "/system/app/SetupWizard.apk".

If you have done everything correctly, when you press the home button you should be able to see your home screen, and it should stay that way after restarting the handset.
