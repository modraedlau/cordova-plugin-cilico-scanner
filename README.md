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
ionic cordova plugin add cordova-plugin-broadcaster
npm install --save @ionic-native/broadcaster
ionic cordova plugin add https://github.com/modraedlau/cordova-plugin-cilico-scanner.git
```
### home.html
```
<ion-header>
  <ion-navbar>
    <ion-title>Home</ion-title>
  </ion-navbar>
</ion-header>

<ion-content padding>
  <h2>Welcome to Ionic!</h2>
  <p>
    This starter project comes with simple tabs-based layout for apps
    that are going to primarily use a Tabbed UI.
  </p>
  <p>
    Take a look at the <code>src/pages/</code> directory to add or change tabs,
    update any existing page or create new pages.
  </p>
  <p>
    <button ion-button color="primary" (click)="resv()">resv</button>
  </p>
</ion-content>
```
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