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
### app.module.ts
```
import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { AboutPage } from '../pages/about/about';
import { ContactPage } from '../pages/contact/contact';
import { HomePage } from '../pages/home/home';
import { TabsPage } from '../pages/tabs/tabs';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { Broadcaster } from '@ionic-native/broadcaster';

@NgModule({
  declarations: [
    MyApp,
    AboutPage,
    ContactPage,
    HomePage,
    TabsPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    AboutPage,
    ContactPage,
    HomePage,
    TabsPage
  ],
  providers: [
    Broadcaster,
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}

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

declare let cordova: any;

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  constructor(public navCtrl: NavController, private broadcaster: Broadcaster) {

  }

  resv() {
    cordova.plugins.CilicoScanner.register();
    this.broadcaster.addEventListener('CilicoScanner').subscribe((event) => alert(event.code));
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