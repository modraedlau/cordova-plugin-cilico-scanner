# Cordova Sunmi Scanner Plugin
Sunmi scanner plugin for Cordova

## Ionic
```
ionic cordova plugin add https://github.com/modraedlau/cordova-plugin-sunmi-scanner.git
```

## Example
```
ionic start MyIonicProject tabs
cd MyIonicProject
ionic cordova plugin add https://github.com/modraedlau/cordova-plugin-sunmi-scanner.git

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
    <button ion-button color="primary" (click)="scan()">scan</button>
  </p>
</ion-content>
```
### home.ts
```
import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

declare let cordova: any;

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  constructor(public navCtrl: NavController) {

  }

  scan() {
    cordova.plugins.SunmiScanner.scan().then((barcodeData) => {
      alert(barcodeData);
    }).catch((e) => {
      alert(e);
    });
  }

}
```
### build
```
ionic cordova platform add android
```
*MyIonicProject/platforms/android/project.properties*
```
target=android-22
```
```
ionic cordova build android
```