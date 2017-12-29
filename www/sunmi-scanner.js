var exec = require('cordova/exec');

var PLUGIN_NAME = 'SunmiScanner';

var SunmiScanner = {
    scan: function (cb) {
        return new Promise(function (resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'scan', []);
        });
    }
};

module.exports = SunmiScanner;
