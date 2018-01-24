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
### home.
### home.ts
```
import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Broadcaster } from '@ionic-native/broadcaster';

// declare let cordova: any;

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  constructor(public navCtrl: NavController, private broadcaster: Broadcaster) {

  }

  resv() {
    this.broadcaster.addEventListener('CilicoScanner').subscribe((event) => alert(event));
  }

}
```
### build
add android platform
```
ionic cordova platform add android
```
build android
```
ionic cordova build android
```