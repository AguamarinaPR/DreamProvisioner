@echo off
set SDK=C:\Software\SDK\android-sdk-windows-1.0_r1
set JDK=C:\Program Files\Java\jdk1.8.0_202\bin

set ANDROIDJAR=%SDK%\android.jar
set TOOLS=%SDK%\tools

if exist gen rmdir /s /q gen
if exist bin rmdir /s /q bin
mkdir gen
mkdir bin
if exist classes.dex del classes.dex
if exist dreamprovisioner.apk del dreamprovisioner.apk

echo [1/6] Generating R.java
"%TOOLS%\aapt.exe" package -f -m -J gen -M AndroidManifest.xml -S res -I "%ANDROIDJAR%" || goto :fail

echo [2/6] Compiling Java
"%JDK%\javac.exe" -source 1.5 -target 1.5 -bootclasspath "%ANDROIDJAR%" -d bin gen\com\aguamarina\provisioner\R.java src\com\aguamarina\provisioner\Provision.java src\com\aguamarina\provisioner\Done1_0.java src\com\aguamarina\provisioner\Done1_1.java || goto :fail

echo [3/6] Converting to dex
"%JDK%\java.exe" -jar "%TOOLS%\lib\dx.jar" --dex --output=classes.dex bin || goto :fail

echo [4/6] Packaging resources
"%TOOLS%\aapt.exe" package -f -M AndroidManifest.xml -S res -I "%ANDROIDJAR%" -F dreamprovisioner-unsigned.apk || goto :fail

echo [5/6] Adding classes.dex
"%TOOLS%\aapt.exe" add dreamprovisioner-unsigned.apk classes.dex || goto :fail

echo [6/6] Signing
if not exist my.keystore "%JDK%\keytool.exe" -genkey -v -keystore my.keystore -alias dream -keyalg RSA -keysize 2048 -validity 10000 -storepass android -keypass android -dname "CN=Dream"
"%JDK%\jarsigner.exe" -keystore my.keystore -storepass android -keypass android -sigalg SHA1withRSA -digestalg SHA1 -signedjar dreamprovisioner.apk dreamprovisioner-unsigned.apk dream || goto :fail

echo.
echo Done: dreamprovisioner.apk
goto :eof

:fail
echo.
echo BUILD FAILED
exit /b 1