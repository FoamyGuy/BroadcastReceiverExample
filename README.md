BroadcastReceiverExample
========================

Demonstration of basics with BroadcastReceivers on the Android platform.

To broadcast intent from ADB:

    adb shell am broadcast -a com.makemyandroidapp.broadcastexample.MY_CUSTOM_ACTION
    //with extra:
    adb shell am broadcast -a com.makemyandroidapp.broadcastexample.CHANGE_COLOR -e color '#4488BB'
