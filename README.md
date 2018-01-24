# Cordova Cilico Scanner Plugin
Cilico scanner plugin for Cordova

## Ionic
```
ionic cordova plugin add https://github.com/modraedlau/cordova-plugin-cilico-scanner.git
```

## Example
```
ionic start MyIonicProject tabs
cd MyIonicProject
ionic cordova plugin add https://github.com/modraedlau/cordova-plugin-cilico-scanner.git
```
### Javascript
```
console.log( "register CilicoScanner received!" );

var listener = function(e) {
  //log: CilicoScanner received! codeInfo: {"code": "123456789"}
  console.log("CilicoScanner received! codeInfo: " + JSON.stringify(e));
}

window.broadcaster.addEventListener("CilicoScanner", listener);
```
### build
add android platform
```
ionic cordova platform add android
```
*in MyIonicProject/platforms/android/project.properties*
```
target=android-22
```
build android
```
ionic cordova build android
```