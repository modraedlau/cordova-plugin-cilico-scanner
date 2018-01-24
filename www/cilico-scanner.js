var exec = require('cordova/exec');

var PLUGIN_NAME = 'CilicoScanner';

var CilicoScanner = {
    register: function (cb) {
        return new Promise(function (resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, 'register', []);
        });
    }
};

module.exports = CilicoScanner;
