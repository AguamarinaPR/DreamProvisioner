# Dream Provisioner
The Dream Provisioner is a tool for provisioning T-Mobile G1 devices.
## How do I use my T-Mobile G1 in 2023 or later?
  
Part 1: Flashing T-Mobile G1 rom version RC29  
  
You will need [DREAIMG.nbh](http://archive.org/download/DREAIMG/DREAIMG.nbh)which is the file containing RC29 with Android 1.0.  
  
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

  
Part 2: Bypassing the setup wizard  
  
  
The unskippable setup wizard presents a problem, since you can't use the phone without completing it, and you likely can't complete it as of 2023.  
  
The home and menu buttons are disabled until after the setup has been completed, this is done through a hidden setting labeled `device_provisioned` which when disabled, disables the home and menu buttons. Dream Provisioner sets this setting to true, enabling these buttons.  
  
You will need [ADB](https://developer.android.com/tools/releases/platform-tools) to connect to your handset.  
  
You will also need [Dream Provisioner](http://aguamarina.altervista.org/repo/dreamprovisioner.apk) to provision your handset.  
  
When I did this I used an inactive SIM card to bypass the no SIM card lock screen, although it might work without any sim card, I have yet to test it.  

1.  On your handset, slide open the keyboard, press **enter** and then type: `setprop persist.service.adb.enable 1` and press enter again. you can tap through to the google account setup screen to see what you are typing.
2.  This will enable your phone to connect to your computer via ADB, go to your computer's terminal and run "adb install dreamprovisioner.apk" (assuming dreamprovisioner.apk is in the same directory as ADB).
3.  Then, run the command `adb shell am start -a android.intent.action.MAIN -n com.aguamarina.provisioner/.Provision` to start the Dream Provisioner on your handset, which can guide you through the next steps, or you can continue reading here.
4.  Press the provision button on your handset's screen.
5.  Now, slide open your handset's keyboard and press enter, then type `telnetd` and press enter again.
6.  Afterwards, if your handset is connected to your computer, type `adb forward tcp:2323 tcp:23` into your computer's terminal and press enter.
7.  Now, you can connect to your phone's root shell via telnet by running `telnet 127.0.0.1 2323` on your terminal.
8.  List the running processes by executing `ps` on the telnet shell and look for the processes with the name of com.android.setupwizard which will show a PID next to the name of the user running the process.
9.  Terminate the processes by entering `kill (Replace with PID of com.android.setupwizard)`
10.  If you have done everything correctly, when you press the home button you should be able to see your home screen, and it should stay that way after restarting the handset.

Now you should be able to use your G1 properly as you please, if you see a notification saying setup isn't finished, you can ignore it and continue using your device either way.